package config;

import controller.*;
import dao.interfaces.*;
import db.ConnectionFactory;
import db.ConnectionProvider;
import service.*;

//APP CONFIG DEFINE CON QUE TECNOLOGIAS TRABAJAREMOS
public class AppConfig {

    private static final String dbType = "mysql"; //todo añadir diferentes tecnologias

    private static final ConnectionProvider provider =
            ConnectionFactory.getProvider(dbType);  //todo añadir diferentes tecnologias


    // ESCOLA

    private static final EscolaDAO escolaDAO =
            DAOFactory.getEscolaDAO(dbType, provider); //le decimos que va usar el dao de mysql y el provider de mysql

    private static final EscolaService escolaService =
            new EscolaService(escolaDAO); // le decimos al service que sus metodos usaran este dao

    private static final EscolaController escolaController =
            new EscolaController(escolaService); // creamos el controller que podra usar los metodos de su entidad y sabra que tecnologia usar

    public static EscolaController getEscolaController() {
        return escolaController;
    }



    // SECTOR aqui tenemos la seleccion de la tecnologia que vamos a usar en sector
    private static final ViaDAO viaDAO =
            DAOFactory.getViaDAO(dbType, provider);

    private static final SectorDAO sectorDAO =
            DAOFactory.getSectorDAO(dbType, provider); //le decimos que va usar el dao de mysql y el provider de mysql

    private static final SectorService sectorService =
            new SectorService(sectorDAO, viaDAO); // le decimos al service que sus metodos usaran este dao

    private static final SectorController sectorController =
            new SectorController(
                    sectorService,
                    escolaService
            ); // creamos el controller que podra usar los metodos de su entidad y sabra que tecnologia usar

    public static SectorController getSectorController() {
        return sectorController;
    }

    // VIA



    private static final ViaService viaService =
            new ViaService(viaDAO);

    private static final ViaController viaController =
            new ViaController(viaService, sectorService, escolaService);

    public static ViaController getViaController() {
        return viaController;
    }

    // ESCALADOR

    private static final EscaladorDAO escaladorDAO =
            DAOFactory.getEscaladorDAO(dbType, provider); //le decimos que va usar el dao de mysql y el provider de mysql

    private static final EscaladorService escaladorService =
            new EscaladorService(escaladorDAO); // le decimos al service que sus metodos usaran este dao

    private static final EscaladorController escaladorController =
            new EscaladorController(escaladorService); // creamos el controller que podra usar los metodos de su entidad y sabra que tecnologia usar

    public static EscaladorController getEscaladorController() {
        return escaladorController;
    }


    // LLAR

    private static final LlarDAO llarDAO =
            DAOFactory.getLlarDAO(dbType, provider); //le decimos que va usar el dao de mysql y el provider de mysql

    private static final LlarService llarService =
            new LlarService(llarDAO); // le decimos al service que sus metodos usaran este dao

    private static final LlarController llarController =
            new LlarController(llarService); // creamos el controller que podra usar los metodos de su entidad y sabra que tecnologia usar

    public static LlarController getLlarController() {
        return llarController;
    }
}