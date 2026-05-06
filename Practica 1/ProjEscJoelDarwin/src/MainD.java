import config.AppConfig;
import config.DAOFactory;
import controller.Menus.MainMenu;
import controller.SectorController;
import dao.impl.mysql.SectorMySQLDAO;
import dao.interfaces.SectorDAO;
import dao.interfaces.ViaDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escola;
import model.entity.Via;
import service.SectorService;
import model.entity.*;
import  java.util.*;

public class MainD {
    public static void main(String[] args) {
        System.out.println("SE EJECUTA");
        /*AppConfig.getViaController().viesPerEstatTancada();
        AppConfig.getViaController().viesPerEstatApte();
        AppConfig.getEscolaController().viaDisponibles(AppConfig.getEscolaController().getEscola(1));
        AppConfig.getViaController().mostrarViesLlargues(1);*/

        MainMenu menuPrincipal = new MainMenu();

        menuPrincipal.PrintMenuMain();
    }
}
