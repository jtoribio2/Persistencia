import config.AppConfig;
import config.DAOFactory;
import controller.SectorController;
import dao.impl.mysql.SectorMySQLDAO;
import dao.interfaces.SectorDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escola;
import model.entity.Via;
import service.SectorService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppConfig.getSectorController().buscarPorNombre("Pati");
        //MOSTRAR RANGO
         List<Via> llistes =  AppConfig.getViaController().viesPerDificultat("6a 6c");

         for(int i = 0; i < llistes.size(); i++){
             System.out.println("NOM" + " " + llistes.get(i).getNom());
             System.out.println("SECTOR" + " " + AppConfig.getSectorController().getSector(llistes.get(i).getId_sector()).getNom());
             System.out.println("ESCOLA" + " " + AppConfig.getEscolaController().getEscola( AppConfig.getSectorController().getSector(llistes.get(i).getId_sector()).getId_escoles()).getNom());
         }

         //ESCOLES ACON TESTRICCION ACTIVA
        List<Escola> escolesFiltrar = AppConfig.getEscolaController().escolesDisponibles();
         for (Escola e : escolesFiltrar){
             System.out.println(e.getNom());
         }

    }
}
