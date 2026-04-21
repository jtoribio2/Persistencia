package config;

import controller.EscaladorController;
import controller.EscolaController;
import controller.SectorController;
import controller.ViaController;
import dao.interfaces.EscaladorDAO;
import dao.interfaces.EscolaDAO;
import dao.interfaces.SectorDAO;
import dao.interfaces.ViaDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escalador;
import service.EscolaService;
import service.SectorService;
import service.ViaService;
import service.*;

//APP CONFIG DEFINE CON QUE TECNOLOGIAS TRABAJAREMOS
public class AppConfig {

    private static final String dbType = "mysql"; //todo añadir diferentes tecnologias

    private static final ConnectionProvider provider =
            ConnectionFactory.getProvider(dbType);  //todo añadir diferentes tecnologias

    //  SECTOR aqui tenemos la selecion de la tecnologia que vamos a usar en sector
    private static final SectorDAO sectorDAO =
            DAOFactory.getSectorDAO(dbType, provider); //le decimos que va usar el dao de msql y el provedor de mysql

    private static final SectorService sectorService =
            new SectorService(sectorDAO); // le decimos al service que sus metodos usaran este dao

    private static final SectorController sectorController =
            new SectorController(sectorService); // creamos el controler que podra usar los metodos de su entidad y sabra que tecnologia y donde ir a buscar la informacion

    public static SectorController getSectorController() {
        return sectorController;
    }

    //ESCOLA

    private static final EscolaDAO escolaDAO =
            DAOFactory.getEscolaDAO(dbType, provider); //le decimos que va usar el dao de msql y el provedor de mysql

    private static final EscolaService escolaService =
            new EscolaService(escolaDAO); // le decimos al service que sus metodos usaran este dao

    private static final EscolaController escolaController =
            new EscolaController(escolaService); // creamos el controler que podra usar los metodos de su entidad y sabra que tecnologia y donde ir a buscar la informacion

    public static EscolaController getEscolaController() {
        return escolaController;
    }

    //ESCALADOR

    private static final EscaladorDAO escaladorDAO =
            DAOFactory.getEscaladorDAO(dbType, provider); //le decimos que va usar el dao de msql y el provedor de mysql

    private static final EscaladorService Escaladorservice =
            new EscaladorService(escaladorDAO); // le decimos al service que sus metodos usaran este dao

    private static final EscaladorController escalorController =
            new EscaladorController(Escaladorservice); // creamos el controler que podra usar los metodos de su entidad y sabra que tecnologia y donde ir a buscar la informacion

    public static EscaladorController getEscaladorController() {
        return escalorController;
    }

    // VIA
    private static final ViaDAO viaDAO =
            DAOFactory.getViaDAO(dbType, provider);

    private static final ViaService viaService =
            new ViaService(viaDAO);

    private static final ViaController viaController =
            new ViaController(viaService);

    public static ViaController getViaController() {
        return viaController;
    }
}