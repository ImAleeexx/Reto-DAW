package com.imaleex.esportapp.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alex Cortes
 * @author Aritz Castillo
 */
public class DaoUtils {

    public static boolean checkNullableInteger(ResultSet rs, String strColName) throws SQLException {
        int nValue = rs.getInt(strColName);
        return rs.wasNull();
    }

    public static int getRowCount(ResultSet resultSet) {
        if (resultSet == null){
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
