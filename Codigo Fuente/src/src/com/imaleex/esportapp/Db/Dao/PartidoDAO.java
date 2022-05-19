package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Jornada;
import com.imaleex.esportapp.Models.Partido;

import java.sql.Connection;
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
                equipo1.setId(rs.getInt("equipo1"));
                Equipo equipo2 = new Equipo();
                equipo2.setId(rs.getInt("equipo2"));
                partido.setLocal(equipo1);
                partido.setVisitante(equipo2);
                try {
                    partido.setMarcadorLocal(rs.getInt("golesLocal"));
                }catch (NullPointerException e){
                    partido.setMarcadorLocal(0);
                }
                try {
                    partido.setMarcadorVisitante(rs.getInt("golesVisitante"));
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
            try {
            stmt.setTime(3, java.sql.Time.valueOf(partido.getHora()));
            }catch (NullPointerException e){
                stmt.setTime(3, null);
            }
            try {
            stmt.setInt(4, partido.getMarcadorLocal());
            }catch (NullPointerException e){
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            try {
            stmt.setInt(5, partido.getMarcadorVisitante());
            }catch (NullPointerException e){
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            stmt.setInt(6, partido.getJornada().getId());
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
                partido.setMarcadorLocal(rs.getInt("resultado_local"));
                partido.setMarcadorVisitante(rs.getInt("resultado_visitante"));
                Jornada jornada2 = new Jornada();
                jornada2.setId(rs.getInt("id_jornada"));
                partido.setJornada(jornada2);
                partidos.add(partido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partidos;
    }

}
