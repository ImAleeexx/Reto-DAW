package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Jornada;
import com.imaleex.esportapp.Models.Partido;

import java.sql.Connection;
import java.sql.SQLException;

public class PartidoDAO {

    public static Partido searchPartido(int id)throws DbException {
        String sql = "SELECT * FROM partido  WHERE id LIKE ? ";
        Partido partido = new Partido();
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            java.sql.ResultSet rs = stmt.executeQuery();

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

}
