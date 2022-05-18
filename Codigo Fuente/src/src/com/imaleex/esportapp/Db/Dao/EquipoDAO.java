package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Models.Personas.Jugador;
import com.imaleex.esportapp.Models.Personas.Rol;
import com.imaleex.esportapp.Utils.DaoUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class EquipoDAO {


    //Metodo para buscar un equipo
    public static Equipo searchEquipo(String nombreEquipo) throws DataNotFoundException {

        String sql = "SELECT * FROM equipos  WHERE nombre LIKE ? LIMIT 1";
        Equipo equipo = new Equipo();

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, '%' + nombreEquipo + '%');

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            if (rs.next()) {
                equipo.setId(rs.getInt("id"));
                equipo.setNombre(rs.getString("nombre"));
                if (DaoUtils.checkNullableInteger(rs, "entrenador")) {
                    equipo.setEntrenador(new Entrenador(rs.getInt("entrenador")));
                }
                if (DaoUtils.checkNullableInteger(rs, "asistente")) {
                    equipo.setEntrenadorAsistente(new Entrenador(rs.getInt("asistente")));
                }
                if (DaoUtils.checkNullableInteger(rs, "dueno")) {
                    equipo.setDueno(new Dueno(rs.getInt("dueno")));
                }

            } else {
                throw new DataNotFoundException("El equipo no existe");
            }


        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return equipo;
    }

    public static Equipo insertEquipo(Equipo equipo) throws DbException {
        String sql = "INSERT INTO equipos (nombre, entrenador, asistente, dueno) VALUES (?, ?, ?, ?)";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, equipo.getNombre());
            stmt.setInt(2, equipo.getEntrenador().getId());
            stmt.setInt(3, equipo.getEntrenadorAsistente().getId());
            stmt.setInt(4, equipo.getDueno().getId());
            //Ejecutamos el statement
            int id = stmt.executeUpdate();
            //Actualizamos id
            equipo.setId(id);
            return equipo;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al insertar el equipo");
        }
    }


    public static void updateEquipo(Equipo equipo) throws DbException {
        String sql = "UPDATE equipos SET nombre = ?, entrenador = ?, asistente = ?, dueno = ? WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, equipo.getNombre());
            try {
                stmt.setInt(2, equipo.getEntrenador().getId());
            } catch (NullPointerException e) {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            try {
                stmt.setInt(3, equipo.getEntrenadorAsistente().getId());
            } catch (NullPointerException e) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            try {
                stmt.setInt(4, equipo.getDueno().getId());
            } catch (NullPointerException e) {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            stmt.setInt(5, equipo.getId());
            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al actualizar el equipo");
        }
    }

    public static void deleteEquipo(Equipo equipo) throws DbException {
        String sql = "DELETE FROM equipos WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, equipo.getId());
            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al borrar el equipo");
        }
    }


    public static void getJugadoresByEquipo(Equipo equipo) throws DbException {
        String sql = "SELECT p.dni, p.nombre, p.telefono, j.* FROM jugadores j, personas p  WHERE j.id_equipo = ? AND p.id = j.id";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, equipo.getId());
            //Ejecutamos el statement
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                equipo.setJugadores(new ArrayList<Jugador>());
                do {
                    Jugador jugador = new Jugador();
                    jugador.setId(rs.getInt("id"));
                    jugador.setNombre(rs.getString("nombre"));
                    jugador.setDni(rs.getString("dni"));
                    jugador.setTelefono(rs.getString("telefono"));
                    jugador.setNickname(rs.getString("nickname"));
                    jugador.setEquipo(new Equipo());
                    jugador.getEquipo().setId(rs.getInt("id_equipo"));
                    jugador.setRol(Rol.valueOf(rs.getString("rol")));
                    equipo.addJugador(jugador);
                } while (rs.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al obtener los jugadores del equipo");
        }
    }

    public static ArrayList<Equipo> listEquipos() {
        String sql = "SELECT * FROM equipos";
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            //Ejecutamos el statement
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                do {
                    Equipo equipo = new Equipo();
                    loadObject(equipo, rs);
                    equipos.add(equipo);
                } while (rs.next());
            }

        } catch (SQLException | DbException e) {
            e.printStackTrace();
        }
        return equipos;
    }


    public static Equipo searchEquipoById(Integer id) throws DataNotFoundException {
        String sql = "SELECT * FROM equipos WHERE id = ?";
        Equipo equipo = null;
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            //Ejecutamos el statement
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                equipo = new Equipo();
                loadObject(equipo, rs);
            } else {
                throw new DataNotFoundException("No se ha encontrado el equipo");
            }

        } catch (SQLException | DbException e) {
            e.printStackTrace();
        }
        return equipo;
    }

    private static void loadObject(Equipo equipo, ResultSet rs) throws SQLException {
        equipo.setId(rs.getInt("id"));
        equipo.setNombre(rs.getString("nombre"));
        if (DaoUtils.checkNullableInteger(rs, "entrenador")){
            equipo.setEntrenador(new Entrenador(rs.getInt("entrenador")));
        }
        if (DaoUtils.checkNullableInteger(rs, "asistente")){
            equipo.setEntrenador(new Entrenador(rs.getInt("asistente")));
        }
        if (DaoUtils.checkNullableInteger(rs, "dueno")){
            equipo.setDueno(new Dueno(rs.getInt("dueno")));
        }
    }
}
