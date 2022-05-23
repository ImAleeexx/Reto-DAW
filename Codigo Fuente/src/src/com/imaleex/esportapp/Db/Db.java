package com.imaleex.esportapp.Db;

import com.imaleex.esportapp.Exceptions.DbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alex Cortes
 */
public class Db {
    // Static var used for storing the single instance allowed for this class
    private static Db instance = null;
    private static String dbUser, dbPassword, dbName, dbHost = null;

    /**
     * Metodo que devuelve la conexion a la base de datos
     * @return la conexion de la base de datos
     */
    public Connection getConnection() {
        return connection;
    }

    //Instance vars
    private final Connection connection;

    /**
     * Constructor privado de la clase Db que inicializa la conexion a la base de datos
     * @param dbUser usuario de la base de datos
     * @param dbPassword contraseña de la base de datos
     * @param dbName nom de la base de datos
     * @param dbHost hostname or ip of the database
     * @param dbType 0 for mysql, 1 for oracle
     * @throws SQLException si no se puede conectar con la base de datos
     * @throws ClassNotFoundException si no se encuentra el driver de la base de datos
     * @throws DbException si no se puede crear la instancia de la base de datos
     */
    // Private constructor to prevent instances (singleton)
    private Db(String dbUser, String dbPassword, String dbName, String dbHost, int dbType) throws SQLException, ClassNotFoundException, DbException {
        switch (dbType) {
            case 1:
                this.connection = startMysqlConnection(dbUser, dbPassword, dbName, dbHost);
                break;
            case 2:
                this.connection = startOracleConnection(dbUser, dbPassword, dbName, dbHost);
                break;
            default:
                throw new DbException("The db type is not supported");

        }
    }

    /**
     * Metodo que inicializa la conexion a la base de datos Mysql
     * @param dbUser usuario de la base de datos
     * @param dbPassword contraseña de la base de datos
     * @param dbName nom de la base de datos
     * @param dbHost hostname or ip of the database
     * @return la conexion de la base de datos
     * @throws ClassNotFoundException si no se encuentra el driver de la base de datos
     * @throws SQLException si no se puede conectar con la base de datos
     */
    private Connection startMysqlConnection(String dbUser, String dbPassword, String dbName, String dbHost) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + dbHost + ":3306/" + dbName;
        return DriverManager.getConnection(url, dbUser, dbPassword);
    }

    /**
     * Metodo que inicializa la conexion a la base de datos Oracle
     * @param dbUser usuario de la base de datos
     * @param dbPassword contraseña de la base de datos
     * @param dbName nom de la base de datos
     * @param dbHost hostname or ip of the database
     * @return la conexion de la base de datos
     * @throws ClassNotFoundException si no se encuentra el driver de la base de datos
     * @throws SQLException si no se puede conectar con la base de datos
     */
    private Connection startOracleConnection(String dbUser, String dbPassword, String dbName, String dbHost) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@" + dbHost + ":1521:" + dbName;
        return DriverManager.getConnection(url, dbUser, dbPassword);
    }


    /**
     * Metodo para configurar el driver de la base de datos
     * @param dbUser usuario de la base de datos
     * @param dbPassword contraseña de la base de datos
     * @param dbName nom de la base de datos
     * @param dbHost hostname or ip of the database
     */
    public static void setDbParams(String dbUser, String dbPassword, String dbName, String dbHost) {
        Db.dbHost = dbHost;
        Db.dbPassword = dbPassword;
        Db.dbName = dbName;
        Db.dbUser = dbUser;
    }


    /**
     * Metodo que obtiene la instancia de la base de datos
     * @param dbType tipo de base de datos 0:mysql 1:oracle
     * @return la instancia de la base de datos
     * @throws DbException si no se puede inicializar la base de datos
     */
    // Static method that returns real unique instance of db class
    public static Db getInstance(int dbType) throws DbException {
        try {
            if (instance == null)
                if ((dbUser != null || dbName != null || dbHost != null))
                    instance = new Db(dbUser, dbPassword, dbName, dbHost, dbType);
                else
                    throw new DbException("The db parameters are not correctly setted");

            return instance;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new DbException("The db connector could not connect with the database, the Db object has not been created");
        } catch (ClassNotFoundException classNotFoundException) {
            throw new DbException("The db connector class could not be loaded, check the project modules and jre folder");
        }
    }

    /**
     * Metodo que obtiene la conexion de la base de datos
     * @param dbType tipo de base de datos 0:mysql 1:oracle
     * @return la instancia de la base de datos
     * @throws DbException si no se puede inicializar la base de datos
     */
    public static Connection getConnection(int dbType) throws DbException {
        return getInstance(dbType).getConnection();
    }


}