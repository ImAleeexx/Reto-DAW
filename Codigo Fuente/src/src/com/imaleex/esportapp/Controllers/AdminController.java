package com.imaleex.esportapp.Controllers;

import com.imaleex.esportapp.Db.Dao.EquipoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.DuenoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.EntrenadorDAO;
import com.imaleex.esportapp.Db.Dao.UserDAO;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Exceptions.UserNotFoundException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Models.Users.Usuario;

/**
 * @author Alex Cortes
 */
public class AdminController {

    public static Dueno buscarDueno(String dni) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return DuenoDAO.searchDueno(dni);
    }

    public static Usuario buscarUsuario(String username) throws UserNotFoundException {
        assert Main.user.getType() == 1;
        return UserDAO.searchUsername(username);
    }

    public static Entrenador buscarEntrenador(String nombre) throws DataNotFoundException, DbException {
        assert Main.user.getType() == 1;
        return EntrenadorDAO.searchEntrendorByNombre(nombre);
    }

    public static Equipo buscarEquipo(String equipo) throws  DataNotFoundException {
        assert Main.user.getType() == 1;
        return EquipoDAO.searchEquipo(equipo);
    }
}
