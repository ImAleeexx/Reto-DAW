package com.imaleex.esportapp.Models;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;

import java.time.LocalTime;

/**
 * <h1>Clase Partido</h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public class Partido {

    private int id;
    private LocalTime hora;
    private Integer marcadorLocal;
    private Integer marcadorVisitante;
    private Equipo local;
    private Equipo visitante;
    private Jornada jornada;


    /**
     * Constructor vacio
     */
    public Partido() {
    }

    /**
     * Constructor con todos los parametros
     * @param id id del partido
     * @param hora hora del partido
     * @param marcadorLocal marcador del equipo local
     * @param marcadorVisitante marcador del equipo visitante
     * @param local equipo local
     * @param visitante equipo visitante
     * @param jornada jornada del partido
     */
    public Partido(int id, LocalTime hora, int marcadorLocal, int marcadorVisitante, Equipo local, Equipo visitante, Jornada jornada) {
        this.id = id;
        this.hora = hora;
        this.marcadorLocal = marcadorLocal;
        this.marcadorVisitante = marcadorVisitante;
        this.local = local;
        this.visitante = visitante;
        this.jornada = jornada;
    }


    /**
     * Metodo que devuelve el id del partido
     * @return id del partido
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo que setea el id del partido
     * @param id id del partido
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo que devuelve la hora del partido
     * @return hora del partido
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Metodo que setea la hora del partido
     * @param hora hora del partido
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Metodo que devuelve el marcador local
     * @return  marcador local o null si no se ha jugado
     *
     */
    public Integer getMarcadorLocal() {
        try {
            if (marcadorLocal > -1) {
                return marcadorLocal;
            }
        } catch (NullPointerException ignored) {
        }
        return null;
    }

    /**
     * Metodo que setea el marcador local
     * @param marcadorLocal marcador local
     */
    public void setMarcadorLocal(Integer marcadorLocal) {
        this.marcadorLocal = marcadorLocal;
    }

    /**
     * Metodo que devuelve el marcador visitante
     * @return marcador visitante o null si no se ha jugado
     */
    public Integer getMarcadorVisitante() {
        try {
            if (marcadorVisitante > -1) {
                return marcadorVisitante;
            }
        } catch (NullPointerException ignored) {
        }
        return null;
    }

    /**
     * Metodo que setea el marcador visitante
     * @param marcadorVisitante marcador visitante
     */
    public void setMarcadorVisitante(Integer marcadorVisitante) {
        this.marcadorVisitante = marcadorVisitante;
    }

    /**
     * Metodo que devuelve el equipo local
     * @return equipo local o null si no hay equipo local
     */
    public Equipo getLocal() {
        try {
            Integer id = this.local.getId();
            if (local.getNombre() == null && !id.equals(0)) {
                setLocal(AdminController.buscarEquipoId(id));
            }
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return local;
    }

    /**
     * Metodo que setea el equipo local
     * @param local equipo local
     */
    public void setLocal(Equipo local) {
        this.local = local;
    }

    /**
     * Metodo que devuelve el equipo visitante
     * @return equipo visitante o null si no hay equipo visitante
     */
    public Equipo getVisitante() {
        try {
            Integer id = this.visitante.getId();
            if (visitante.getNombre() == null && !id.equals(0)) {
                setVisitante(AdminController.buscarEquipoId(id));
            }
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return visitante;
    }

    /**
     * Metodo que setea el equipo visitante
     * @param visitante equipo visitante
     */
    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    /**
     * Metodo que devuelve la jornada del partido
     * @return jornada del partido
     */
    public Jornada getJornada() {
        return jornada;
    }

    /**
     * Metodo que setea la jornada del partido
     * @param jornada jornada del partido
     */
    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }


    /**
     * Metodo que devuelve el ganador del partido
     * @return equipo ganador o null si no hay ganador
     */
    public Equipo getGanador() {
        if (getMarcadorLocal() != null && getMarcadorVisitante() != null) {
            if (getMarcadorLocal() > getMarcadorVisitante()) {
                return getLocal();
            } else {
                return getVisitante();
            }
        }
        return null;
    }

    /**
     * Metodo que devuelve el perdedor del partido
     * @return equipo perdedor o null si no hay perdedor
     */
    public Equipo getPerdedor() {
        if (getMarcadorLocal() != null && getMarcadorVisitante() != null) {
            if (getMarcadorLocal() < getMarcadorVisitante()) {
                return getLocal();
            } else {
                return getVisitante();
            }
        }
        return null;
    }
}
