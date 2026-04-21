package config;

import dao.impl.mysql.*;
import dao.interfaces.*;
// import dao.impl.postgres.SectorPostgresDAO;

import db.ConnectionProvider;
import model.entity.Escalador;

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

    //
    //Escola
    public static EscaladorDAO getEscaladorDAO(String dbType, ConnectionProvider provider) {
        switch (dbType) {

            case "mysql":
                return new EscaladorMySQLDAO(provider);

            case "postgres":
                throw new RuntimeException("Postgres aún no implementado");

            default:
                throw new RuntimeException("BD no soportada: " + dbType);
        }
    }

//LLAR
public static LlarDAO getLlarDAO(String dbType, ConnectionProvider provider) {
    switch (dbType) {

        case "mysql":
            return new LlarMySQLDAO(provider);

        case "postgres":
            throw new RuntimeException("Postgres aún no implementado");

        default:
            throw new RuntimeException("BD no soportada: " + dbType);
    }
}

    //VIA
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