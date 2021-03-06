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
import com.imaleex.esportapp.Utils.Faker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class EquipoDAO {


    //Metodo para buscar un equipo

    /**
     * Metodo para buscar un equipo por su nombre
     * @param nombreEquipo nombre del equipo
     * @return Equipo con el nombre dado
     * @throws DataNotFoundException si no se encuentra el equipo
     */
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

    /**
     * Metodo para buscar un equipo por su dueno
     * @param dueno dueno del equipo
     * @return equipo del dueno
     * @throws DbException si hay un error en la base de datos o en la conexion
     */
    public static Equipo searchEquipoByDueno(Dueno dueno) throws DbException {
        String sql = "SELECT * FROM equipos  WHERE dueno = ? LIMIT 1";
        Equipo equipo = new Equipo();
        if (dueno == null) {
            return null;
        }
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, dueno.getId());

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
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Error al buscar el equipo");
        }
        return equipo;
    }

    /**
     * Metodo para buscar un equipo por su entrenador
     * @param entrenador entrenador del equipo
     * @return equipo del entrenador
     * @throws DbException si hay un error en la base de datos o en la conexion
     */
    public static Equipo searchEquipoByEntrenador(Entrenador entrenador) throws DbException {
        String sql = "SELECT * FROM equipos  WHERE entrenador = ? OR asistente = ? LIMIT 1";
        Equipo equipo = new Equipo();
        if (entrenador == null) {
            return null;
        }
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, entrenador.getId());
            stmt.setInt(2, entrenador.getId());

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
                if (DaoUtils.checkNullableInteger(rs, "entrenador")) {
                    equipo.setDueno(new Dueno(rs.getInt("entrenador")));
                }

            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Error al buscar el equipo");
        }
        return equipo;
    }

    /**
     * Metodo para insertar un equipo en la base de datos
     * @param equipo equipo a insertar
     * @return el equipo insertado con el id asignado
     * @throws DbException si hay un error en la base de datos o en la conexion
     */
    public static Equipo insertEquipo(Equipo equipo) throws DbException {
        String sql = "INSERT INTO equipos (nombre, entrenador, asistente, dueno) VALUES (?, ?, ?, ?)";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
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
            //Ejecutamos el statement
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new DbException("No se pudo insertar el equipo");
            }
            //Actualizamos id
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                equipo.setId(rs.getInt(1));
            }
            return equipo;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al insertar el equipo");
        }
    }


    /**
     * Metodo para actualizar un equipo en la base de datos
     * @param equipo Equipo a actualizar
     * @throws DbException si hay un error en la base de datos o en la conexion
     */
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

    /**
     * Metodo para eliminar un equipo de la base de datos
     * @param equipo Equipo a eliminar
     * @throws DbException si hay un error en la base de datos o en la conexion o si el equipo no existe
     */
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


    /**
     * Metodo para obtener un equipo de la base de datos
     * @param equipo Equipo al que se le quieren obtener los jugadores
     * @return  Lista de jugadores del equipo
     * @throws DbException si hay un error en la base de datos o en la conexion
     */
    public static ArrayList<Jugador> getJugadoresByEquipo(Equipo equipo) throws DbException {
        String sql = "SELECT p.dni, p.nombre, p.telefono, j.* FROM jugadores j, personas p  WHERE j.id_equipo = ? AND p.id = j.id";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, equipo.getId());
            //Ejecutamos el statement
            ResultSet rs = stmt.executeQuery();
            ArrayList<Jugador> jugadores = new ArrayList<>();

            if (rs.next()) {
                do {
                    Jugador jugador = new Jugador();
                    jugador.setId(rs.getInt("id"));
                    jugador.setNombre(rs.getString("nombre"));
                    jugador.setDni(rs.getString("dni"));
                    jugador.setTelefono(rs.getString("telefono"));
                    jugador.setNickname(rs.getString("nickname"));
                    try {
                        jugador.setRol(Rol.valueOf(rs.getString("rol")));
                    } catch (NullPointerException e) {
                    }
                    jugador.setEquipo(equipo);
                    jugadores.add(jugador);
                } while (rs.next());
            }
            return jugadores;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al obtener los jugadores del equipo");
        }
    }

    /**
     * Metodo para obtener una lista de equipos de la base de datos
     * @return Lista de todos los equipos
     * @throws DbException si hay un error en la base de datos o en la conexion
     */
    public static ArrayList<Equipo> listEquipos() throws DbException {
        String sql = "SELECT * FROM equipos";
        ArrayList<Equipo> equipos = new ArrayList<>();
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

        } catch (SQLException e) {
            throw new DbException("Error al obtener los equipos");
        }
        return equipos;
    }


    /**
     * Metodo para obtener un equipo de la base de datos
     * @param id id del equipo
     * @return Equipo con el id pasado por parametro
     * @throws DataNotFoundException si no se encuentra el equipo o hay un error en la base de datos o en la conexion
     */
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

    /**
     * Metodo para cargar un objeto de la base de datos
     * @param equipo Equipo a cargar
     * @param rs ResultSet con los datos del equipo
     * @throws SQLException si hay un error en la base de datos o en la conexion
     */
    private static void loadObject(Equipo equipo, ResultSet rs) throws SQLException {
        equipo.setId(rs.getInt("id"));
        equipo.setNombre(rs.getString("nombre"));
        if (DaoUtils.checkNullableInteger(rs, "entrenador")) {
            equipo.setEntrenador(new Entrenador(rs.getInt("entrenador")));
        }
        if (DaoUtils.checkNullableInteger(rs, "asistente")) {
            equipo.setEntrenador(new Entrenador(rs.getInt("asistente")));
        }
        if (DaoUtils.checkNullableInteger(rs, "dueno")) {
            equipo.setDueno(new Dueno(rs.getInt("dueno")));
        }
    }


    /**
     * Metodo para crear equipos aleatorios
     * @param numEquipos numero de equipos a crear
     */
    public static void generateRandomTeams(int numEquipos) {
        for (int i = 0; i < numEquipos; i++) {
            Equipo equipo = new Equipo();
            equipo.setId(i);
            equipo.setNombre("Equipo " + Faker.streetName());
            try {
                insertEquipo(equipo);
            } catch (DbException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
