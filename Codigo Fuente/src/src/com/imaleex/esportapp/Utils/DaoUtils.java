package com.imaleex.esportapp.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alex Cortes
 * @author Aritz Castillo
 */
public class DaoUtils {

    /**
     * Metodo para saber si un int primitivo es nulo al obtenerlo de un ResultSet
     * @param rs ResultSet para obtener el valor de una columna
     * @param strColName Nombre de la columna
     * @return true si el valor de la columna es null
     * @throws SQLException Si ocurre un error al obtener el valor de la columna
     */
    public static boolean checkNullableInteger(ResultSet rs, String strColName) throws SQLException {
        int nValue = rs.getInt(strColName);
        return !rs.wasNull();
    }

    /**
     * Metodo para obtener el numero de filas de un ResultSet
     * @param resultSet ResultSet para obtener el numero de filas
     * @return Numero de filas del ResultSet
     */
    public static int getRowCount(ResultSet resultSet) {
        if (resultSet == null) {
            return 0;
        }
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            try {
                resultSet.beforeFirst();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
        }

        return 0;
    }
}
