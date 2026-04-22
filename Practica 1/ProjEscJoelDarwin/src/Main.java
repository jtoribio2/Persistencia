import config.AppConfig;
import config.DAOFactory;
import controller.SectorController;
import dao.impl.mysql.SectorMySQLDAO;
import dao.interfaces.SectorDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escola;
import service.SectorService;

public class Main {
    public static void main(String[] args) {
        AppConfig.getSectorController().buscarPorNombre("Pati");
    }
}
