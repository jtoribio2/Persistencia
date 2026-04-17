package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection implements ConnectionProvider {

    private String url;
    private String user;
    private String password;
    /**
     * CONSTRUCTOR
     * @param url String Direcccion del servidor(ip,puerto...)
     * @param user String Usuari de la BD
     * @param password String Contraseña del usuario
     **/
    public PostgresConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    /**
     @return Devuelve un objeto Connection
     **/
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}