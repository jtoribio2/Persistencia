package config;

import controller.SectorController;
import dao.interfaces.SectorDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import service.SectorService;

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
}