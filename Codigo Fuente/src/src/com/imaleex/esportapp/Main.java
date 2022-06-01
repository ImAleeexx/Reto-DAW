package com.imaleex.esportapp;

import com.imaleex.esportapp.Db.Dao.EquipoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.DuenoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.EntrenadorDAO;
import com.imaleex.esportapp.Db.Dao.Personas.JugadorDAO;
import com.imaleex.esportapp.Db.Dao.Personas.PersonaDAO;
import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Exceptions.UserNotFoundException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Models.Personas.Jugador;
import com.imaleex.esportapp.Models.Personas.Rol;
import com.imaleex.esportapp.Models.Users.Usuario;
import com.imaleex.esportapp.Utils.CryptoUtils;
import com.imaleex.esportapp.Utils.Faker;
import com.imaleex.esportapp.Utils.WindowUtils;
import com.imaleex.esportapp.Views.AdminView;
import com.imaleex.esportapp.Views.Login;
import com.imaleex.esportapp.Views.UserView;

import javax.swing.*;

import java.util.ArrayList;

import static com.imaleex.esportapp.Db.Dao.UserDAO.searchUsername;

public class Main {

    public static Usuario user;
    private static JFrame login;

    public static void main(String[] args) {
        initDbConnection();
        displayLoginModal();

        //JornadaDAO.generateMatchCalendar();

    }

    private static void initDbConnection() {
        try {
            //Get password from enviroment variables
            String password = System.getenv("DB_PASSWORD");
            if (password == null) {
                throw new DbException("No se encontró la contraseña de la base de datos en la variable de entorno DB_PASSWORD");
            }
            Db.setDbParams("esports", password, "esports", "server.imaleex.com");
            Db db = Db.getInstance(1);
            System.out.println("Conectado a la base de datos");
        } catch (DbException e) {
            WindowUtils.showErrorMessage(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

    }


    /*---------------------------------------------------------------------------------------------------------------------*/
    /* Login */
    public static boolean login(String username, String password) {
        Usuario usuario;
        try {
            usuario = searchUsername(username);
            if (usuario != null) {
                if (CryptoUtils.checkHash(password, usuario.getClave())) {
                    user = usuario;
                    return true;
                }
            } else {
                throw new UserNotFoundException("El usuario no existe");
            }
        } catch (UserNotFoundException ignored) {
        }
        return false;
    }

    public static void mostrarVentana() {
        if (user.checkAdmin()) {
            AdminView.main();
        } else {
            UserView.main();
        }
    }

    private static void displayLoginModal() {
        login = new JFrame("Login");
        login.setContentPane(new Login().getJPanel());
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setLocationRelativeTo(null);
        login.pack();
        login.setVisible(true);
    }

    public static void closeLogin() {
        login.dispose();
    }



    public static void generateTeams(){
        try {
            ArrayList<Equipo> equipos = EquipoDAO.listEquipos();
            for (int i=equipos.size(); i<10; i++){
                Equipo equipo =  new Equipo();
                equipo.setNombre("Equipo " + Faker.streetName());
                EquipoDAO.insertEquipo(equipo);
            }
            WindowUtils.showInfoMessage("Se ha populado la base de datos con 10 equipos");
        } catch (DbException e) {
            WindowUtils.showErrorMessage(e.getMessage());
        }
    }


    public static void generatePlayers(){
        try {
            ArrayList<Equipo> equipos = EquipoDAO.listEquipos();
            for (Equipo equipo: equipos){
                for (int i=equipo.getJugadores().size(); i<5; i++){
                    Jugador jugador =  new Jugador();
                    jugador.setNombre(Faker.firstName() + " " + Faker.lastName());
                    jugador.setTelefono(String.valueOf(Faker.randomNumberBetween(600000000, 699999999)));
                    jugador.setEquipo(equipo);
                    jugador.setDni(Faker.getDni());
                    jugador.setSueldo(Faker.randomNumberBetween(2000, 3000));
                    jugador.setRol((Rol) Faker.randomElement(Rol.values()));
                    jugador.setNickname(Faker.getRandomUsername(jugador.getNombre()));
                    PersonaDAO.createPersona(jugador);
                    JugadorDAO.insertJugador(jugador);
                }
            }
            WindowUtils.showInfoMessage("Se ha populado cada equipo con 5 jugadores");
        } catch (DbException e) {
            WindowUtils.showErrorMessage(e.getMessage());
        }
    }

    public static void generateEntrenadores(){
        try {
            ArrayList<Equipo> equipos = EquipoDAO.listEquipos();
            for (Equipo equipo: equipos){
                if (equipo.getEntrenador() == null) {
                    Entrenador entrenador = new Entrenador();
                    entrenador.setNombre(Faker.firstName() + " " + Faker.lastName());
                    entrenador.setTelefono(String.valueOf(Faker.randomNumberBetween(600000000, 699999999)));
                    entrenador.setDni(Faker.getDni());
                    entrenador.setSueldo(Faker.randomNumberBetween(2000, 3000));
                    PersonaDAO.createPersona(entrenador);
                    EntrenadorDAO.insertEntrenador(entrenador);
                    equipo.setEntrenador(entrenador);
                }
                if (equipo.getEntrenadorAsistente() == null) {
                    Entrenador entrenador = new Entrenador();
                    entrenador.setNombre(Faker.firstName() + " " + Faker.lastName());
                    entrenador.setTelefono(String.valueOf(Faker.randomNumberBetween(600000000, 699999999)));
                    entrenador.setDni(Faker.getDni());
                    entrenador.setSueldo(Faker.randomNumberBetween(2000, 3000));
                    PersonaDAO.createPersona(entrenador);
                    EntrenadorDAO.insertEntrenador(entrenador);
                    equipo.setEntrenadorAsistente(entrenador);
                }
                EquipoDAO.updateEquipo(equipo);
            }
            WindowUtils.showInfoMessage("Se ha populado cada equipo con 1 entrenador y 1 entrenador asistente");
        } catch (DbException e) {
            WindowUtils.showErrorMessage(e.getMessage());
        }
    }

    public static void generateDuenos(){
        try {
            ArrayList<Equipo> equipos = EquipoDAO.listEquipos();
            for (Equipo equipo: equipos){
                if (equipo.getDueno() == null) {
                    Dueno dueno = new Dueno();
                    dueno.setNombre(Faker.firstName() + " " + Faker.lastName());
                    dueno.setTelefono(String.valueOf(Faker.randomNumberBetween(600000000, 699999999)));
                    dueno.setDni(Faker.getDni());
                    dueno.setEmail(Faker.getEmail(dueno.getNombre()));
                    PersonaDAO.createPersona(dueno);
                    DuenoDAO.insertDueno(dueno);
                    equipo.setDueno(dueno);
                    EquipoDAO.updateEquipo(equipo);
                }

            }
            WindowUtils.showInfoMessage("Se ha populado cada equipo con un dueño");
        } catch (DbException e) {
            WindowUtils.showErrorMessage(e.getMessage());
        }
    }

}
