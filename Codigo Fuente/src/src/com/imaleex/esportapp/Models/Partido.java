package com.imaleex.esportapp.Models;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Personas.Entrenador;

import java.time.LocalTime;

/**
 * @author Alex Cortes
 */
public class Partido {

    private int id;
    private LocalTime hora;
    private int marcadorLocal;
    private int marcadorVisitante;
    private Equipo local;
    private Equipo visitante;
    private Jornada jornada;


    public Partido() {
    }

    public Partido(int id, LocalTime hora, int marcadorLocal, int marcadorVisitante, Equipo local, Equipo visitante, Jornada jornada) {
        this.id = id;
        this.hora = hora;
        this.marcadorLocal = marcadorLocal;
        this.marcadorVisitante = marcadorVisitante;
        this.local = local;
        this.visitante = visitante;
        this.jornada = jornada;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getMarcadorLocal() {
        return marcadorLocal;
    }

    public void setMarcadorLocal(int marcadorLocal) {
        this.marcadorLocal = marcadorLocal;
    }

    public int getMarcadorVisitante() {
        return marcadorVisitante;
    }

    public void setMarcadorVisitante(int marcadorVisitante) {
        this.marcadorVisitante = marcadorVisitante;
    }

    public Equipo getLocal() {
        try {
            Integer id = this.local.getId();
            if (local.getNombre() == null && !id.equals(0)) {
                setLocal(AdminController.buscarEquipoId(id));
            }
        } catch ( DataNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
        try {
            Integer id = this.visitante.getId();
            if (visitante.getNombre() == null && !id.equals(0)) {
                setVisitante(AdminController.buscarEquipoId(id));
            }
        } catch ( DataNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return visitante;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }


}
