package config;

import dao.impl.mysql.EscolaMySQLDAO;
import dao.impl.mysql.ViaMySQLDAO;
import dao.interfaces.EscolaDAO;
import dao.interfaces.SectorDAO;
import dao.impl.mysql.SectorMySQLDAO;
// import dao.impl.postgres.SectorPostgresDAO;

import dao.interfaces.ViaDAO;
import db.ConnectionProvider;

public class DAOFactory {
    // Esto nos permite que se elija automaticamente el DAO que necesitamos segun la tecnologia que hayamos elegido

    //Sector
    public static SectorDAO getSectorDAO(String dbType, ConnectionProvider provider) {
        switch (dbType) {

            case "mysql":
                return new SectorMySQLDAO(provider);

            case "postgres":
                throw new RuntimeException("Postgres aún no implementado");

            default:
                throw new RuntimeException("BD no soportada: " + dbType);
        }
    }

    //Escola
    public static EscolaDAO getEscolaDAO(String dbType, ConnectionProvider provider) {
        switch (dbType) {

            case "mysql":
                return new EscolaMySQLDAO(provider);

            case "postgres":
                throw new RuntimeException("Postgres aún no implementado");

            default:
                throw new RuntimeException("BD no soportada: " + dbType);
        }
    }

    public static ViaDAO getViaDAO(String dbType, ConnectionProvider provider) {

        switch (dbType) {

            case "mysql":
                return new ViaMySQLDAO(provider);

            case "postgres":
                // return new ViaPostgresDAO(provider);
                throw new RuntimeException("Postgres no implementado para Via");

            default:
                throw new RuntimeException("BD no soportada: " + dbType);
        }
    }


}