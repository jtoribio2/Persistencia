import config.AppConfig;
import config.DAOFactory;
import controller.SectorController;
import dao.impl.mysql.SectorMySQLDAO;
import dao.interfaces.SectorDAO;
import dao.interfaces.ViaDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escola;
import model.entity.Via;
import service.SectorService;


public class Main {
    public static void main(String[] args) {
        Via via= new Via(
                50,          // 🔥 se ignora (auto_increment)
                6,          // id_sector
                2,          // tipo GEL
                "Via4",
                30,
                "WI4",
                "N2",
                "Reunión",
                "No"
        );

        AppConfig.getSectorController().buscarPorNombre("Sector A");
        System.out.println(AppConfig.getEscolaController().isGel(AppConfig.getEscolaController().getEscola(6)));
        System.out.println(AppConfig.getEscolaController().isGel(AppConfig.getSectorController().mostrarEscola(via.getId_sector())));
        AppConfig.getViaController().crear(via);
    }
}
