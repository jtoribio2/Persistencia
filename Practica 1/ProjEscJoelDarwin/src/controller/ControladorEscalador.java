package controller;

import model.entity.Escalador;
import service.Escalador_service;


import java.util.List;

public class ControladorEscalador  {
    private static Escalador_service dao = new Escalador_service();
    public static  void addEscalador(Escalador e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        dao.inserir(e);
    }

    public static  void SetEscalador(Escalador  e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        dao.modificar(e);
    }

    public static void removeEscalador(Integer id  ) throws  Exception{
        if(id < 0 ) throw  new Exception("Error id ");
        dao.eliminar(id);
    }

    public static List<Escalador> getList(){
        //Hacer comprovaciones o algo
        List<Escalador> e = dao.obtindreTots();
        return e;
    }

    public static Escalador getEscalador(Integer id ) throws Exception{
        if(id < 0 ) throw new  Exception("ERROR ID ");
        return  dao.obtenir(id);
    }
}
