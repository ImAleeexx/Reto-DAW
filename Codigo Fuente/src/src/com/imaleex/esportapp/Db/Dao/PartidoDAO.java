package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Jornada;
import com.imaleex.esportapp.Models.Partido;
import com.imaleex.esportapp.Utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartidoDAO {

    public static Partido searchPartido(int id)throws DbException {
        String sql = "SELECT * FROM partidos  WHERE id LIKE ? ";
        Partido partido = new Partido();
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                partido.setId(rs.getInt("id"));
                Equipo equipo1 = new Equipo();
                equipo1.setId(rs.getInt("id_local"));
                Equipo equipo2 = new Equipo();
                equipo2.setId(rs.getInt("id_visitante"));
                partido.setLocal(equipo1);
                partido.setVisitante(equipo2);
                try {
                    partido.setMarcadorLocal(rs.getInt("resultado_local"));
                }catch (NullPointerException e){
                    partido.setMarcadorLocal(0);
                }
                try {
                    partido.setMarcadorVisitante(rs.getInt("resultado_visitante"));
                }catch (NullPointerException e){
                    partido.setMarcadorVisitante(0);
                }
                partido.setHora(rs.getTime("hora").toLocalTime());
                Jornada jornada = new Jornada();
                jornada.setId(rs.getInt("id_jornada"));
                partido.setJornada(jornada);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partido;
    }

    public static Partido insertPartido(Partido partido) throws DbException {
        String sql = "INSERT INTO partidos (id_local, id_visitante, hora, resultado_local, resultado_visitante, id_jornada) VALUES (?,?,?,?,?,?)";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, partido.getLocal().getId());
            stmt.setInt(2, partido.getVisitante().getId());
            stmt.setTime(3, java.sql.Time.valueOf(partido.getHora()));
            System.out.println(partido.getMarcadorLocal());
            System.out.println(partido.getMarcadorVisitante());
            stmt.setObject(4, partido.getMarcadorLocal(), java.sql.Types.INTEGER);
            stmt.setObject(5, partido.getMarcadorVisitante(), java.sql.Types.INTEGER);
            stmt.setObject(6, partido.getJornada().getId(), java.sql.Types.INTEGER);
            if (stmt.executeUpdate() != 1) {
                throw new DbException("Error al insertar el partido");
            }
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next() ) {
                partido.setId(rs.getInt(1));
            }
            return partido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePartido(Partido partido) throws DbException {
        String sql = "UPDATE partidos SET id_local = ?, id_visitante = ?, hora = ?, resultado_local = ?, resultado_visitante = ?, id_jornada = ? WHERE id = ?";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, partido.getLocal().getId());
            stmt.setInt(2, partido.getVisitante().getId());
            stmt.setTime(3, java.sql.Time.valueOf(partido.getHora()));
            stmt.setInt(4, partido.getMarcadorLocal());
            stmt.setInt(5, partido.getMarcadorVisitante());
            stmt.setInt(6, partido.getJornada().getId());
            stmt.setInt(7, partido.getId());
            if (stmt.executeUpdate() != 1) {
                throw new DbException("Error al actualizar el partido");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deletePartido(Partido partido) throws DbException {
        String sql = "DELETE FROM partidos WHERE id = ?";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, partido.getId());
            if (stmt.executeUpdate() != 1) {
                throw new DbException("Error al eliminar el partido");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Partido> listaPartidosByJornada(Jornada jornada) throws DbException {
        ArrayList<Partido> partidos = new ArrayList<>();
        String sql = "SELECT * FROM partidos WHERE id_jornada = ?";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, jornada.getId());
            loadListaPartidos(partidos, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partidos;
    }


    public static ArrayList<Partido> listaPartidosJugados() throws DbException {
        ArrayList<Partido> partidos = new ArrayList<>();
        String sql = "SELECT * FROM partidos WHERE resultado_local IS NOT NULL AND resultado_visitante IS NOT NULL";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            loadListaPartidos(partidos, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partidos;
    }

    private static void loadListaPartidos(ArrayList<Partido> partidos, PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Partido partido = new Partido();
            partido.setId(rs.getInt("id"));
            Equipo local = new Equipo();
            local.setId(rs.getInt("id_local"));
            partido.setLocal(local);
            Equipo visitante = new Equipo();
            visitante.setId(rs.getInt("id_visitante"));
            partido.setVisitante(visitante);
            partido.setHora(rs.getTime("hora").toLocalTime());
            if (DaoUtils.checkNullableInteger(rs, "resultado_local")) {
                partido.setMarcadorLocal(rs.getInt("resultado_local"));
            }
            if (DaoUtils.checkNullableInteger(rs, "resultado_visitante")) {
                partido.setMarcadorVisitante(rs.getInt("resultado_visitante"));
            }
            Jornada jornada = new Jornada();
            jornada.setId(rs.getInt("id_jornada"));
            partido.setJornada(jornada);
            partidos.add(partido);
        }
    }

}
