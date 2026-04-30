import config.AppConfig;
import config.DAOFactory;
import controller.SectorController;
import dao.impl.mysql.SectorMySQLDAO;
import dao.interfaces.SectorDAO;
import dao.interfaces.ViaDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escalador;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;
import service.SectorService;

import java.util.List;


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

        List<Via> aptes = AppConfig.getViaController().viesPerEstatApte();
        List<Via> tancat = AppConfig.getViaController().viesPerEstatTancada();
        for(Via v : aptes){System.out.println("NOM: "+ v.getNom());}
        for(Via v : tancat){System.out.println("NOM: "+ v.getNom());}
        //MOSTRAR VIAS LARGUES DEUN A ESCOLA ESPECIFICA
        List<Via> llargues = AppConfig.getViaController().mostrarViesLlargues(1);
        for(Via v : llargues){System.out.println("NOM: "+ v.getNom());}
        // SECTOR Me muestra las vias correctamente
        List<Sector> ViasDisponibles = AppConfig.getSectorController().sectorViesDisponibles(1);
        for(Sector s : ViasDisponibles){System.out.println(s.getNom());}

        List<Escalador> escaladors = AppConfig.getEscaladorController().escaladorsEqNivell();
        for(Escalador e : escaladors){System.out.println(e.getNom());}
    }
}
