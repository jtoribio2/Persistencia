package db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {
    //METODO REUTILIZABLE  PARA CONECTARSE EN DIFERENTES BD
    Connection getConnection() throws SQLException;

}