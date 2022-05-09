package com.imaleex.esportapp.Models;

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
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
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
