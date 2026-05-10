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

    //DAOS
    private static final SectorDAO sectorDAO =
            DAOFactory.getSectorDAO(dbType, provider);

    private static final EscolaDAO escolaDAO =
            DAOFactory.getEscolaDAO(dbType, provider); //le decimos que va usar el dao de msql y el provedor de mysql

    private static final EscaladorDAO escaladorDAO =
            DAOFactory.getEscaladorDAO(dbType, provider); //le decimos que va usar el dao de msql y el provedor de mysql

    private static final LlarDAO llarDAO =
            DAOFactory.getLlarDAO(dbType, provider); //le decimos que va usar el dao de msql y el provedor de mysql

    private static final ViaDAO viaDAO =
            DAOFactory.getViaDAO(dbType, provider);

    //  SERVICE
    private static final ViaService viaService =
            new ViaService(
                    viaDAO,
                    sectorDAO,
                    escolaDAO
            );

    private static final SectorService sectorService =
            new SectorService(
                    sectorDAO,
                    viaDAO,
                    viaService
            ); // le decimos al service que sus metodos usaran este dao


    private static final EscolaService escolaService =
            new EscolaService(escolaDAO, sectorDAO); // le decimos al service que sus metodos usaran este dao

    private static final EscaladorService Escaladorservice =
            new EscaladorService(escaladorDAO); // le decimos al service que sus metodos usaran este dao

    private static final LlarService Llarservice =
            new LlarService(llarDAO); // le decimos al service que sus metodos usaran este dao


    //CONTROLLERS
    private static final SectorController sectorController =
            new SectorController(
                    sectorService,
                    escolaService
            ); // creamos el controller que podra usar los metodos de su entidad y sabra que tecnologia usar

    public static SectorController getSectorController() {
        return sectorController;
    }

    private static final EscolaController escolaController =
            new EscolaController(escolaService, sectorService); // creamos el controler que podra usar los metodos de su entidad y sabra que tecnologia y donde ir a buscar la informacion

    public static EscolaController getEscolaController() {
        return escolaController;
    }

    private static final EscaladorController escaladorController =
            new EscaladorController(Escaladorservice); // creamos el controler que podra usar los metodos de su entidad y sabra que tecnologia y donde ir a buscar la informacion

    public static EscaladorController getEscaladorController() {
        return escaladorController;
    }


    private static final LlarController llarController =
            new LlarController(Llarservice); // creamos el controller que podra usar los metodos de su entidad y sabra que tecnologia usar

    public static LlarController getLlarController() {
        return llarController;
    }

    private static final ViaController viaController =
            new ViaController(viaService, sectorService, escolaService);

    public static ViaController getViaController() {
        return viaController;
    }
}