package controlador;


import model.entity.Llar;
import services.Llar_service;

import java.util.List;

public class ControladorLlar {
    private static  Llar_service dao = new Llar_service();

    public static  void addLlar(Llar e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        dao.inserir(e);
    }

    public static  void SetLlar(Llar e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        dao.modificar(e);
    }

    public static void removeLlar(Integer id  ) throws  Exception{
        if(id < 0 ) throw  new Exception("Error id ");
        dao.eliminar(id);
    }

    public static List<Llar> getList(){
        //Hacer comprovaciones o algo
        List<Llar> e = dao.obtindreTots();
        return e;
    }

    public static Llar getEscola(Integer id ) throws Exception{
        if(id < 0 ) throw new  Exception("ERROR ID ");
        return  dao.obtenir(id);
    }

}
