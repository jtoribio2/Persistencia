package controlador;


import model.entity.Sector;
import services.Sector_service;

import java.util.List;

public class ControladorSector {
    private static Sector_service dao = new Sector_service();
    public static  void addSector(Sector e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        dao.inserir(e);
    }

    public static  void SetSector(Sector  e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        dao.modificar(e);
    }

    public static void removeLlar(Integer id  ) throws  Exception{
        if(id < 0 ) throw  new Exception("Error id ");
        dao.eliminar(id);
    }

    public static List<Sector> getList(){
        //Hacer comprovaciones o algo
        List<Sector> e = dao.obtindreTots();
        return e;
    }

    public static Sector  getSector(Integer id ) throws Exception{
        if(id < 0 ) throw new  Exception("ERROR ID ");
        return  dao.obtenir(id);
    }

}
