package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            InputStream input = DBConnection.class.getClassLoader() //le decimos desde donde buscar
                    .getResourceAsStream("resources/properties/db-mysql.properties"); // le decimos donde esta el archivo que indica donde esta la base de datos

            if (input == null) {
                throw new RuntimeException("No se encontró db-mysql.properties");
            }

            Properties prop = new Properties();
            prop.load(input); // leemos el codigo y lo metemos en un objeto properties que es un objeto tipo variante valor

            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password"); // le metemos a las variantes de la clase estaticas la ifnormacion para poder hacer la conecion
        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuración de BD", e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    } // creamos el metodo para poder conectarnos a la base mediante un try(Connection conn = DBConnection.getConnection()) que automaticamente abre conexion y lo cierra cuando salimos del try
}