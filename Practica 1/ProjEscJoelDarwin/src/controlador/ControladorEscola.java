package controlador;

import model.entity.Escola;
import services.Escola_service;

import java.util.ArrayList;
import java.util.List;

public class ControladorEscola {
 private static Escola_service dao = new Escola_service(); // CREAMOS EL OBJETO  DAO PARA SACAR LOS METODOS

    public static  void addEscola(Escola e ) throws  Exception{
       if(e == null) throw new Exception("ERROR DADES BUIDES");
       dao.inserir(e);
    }

    public static  void SetEscola(Escola e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        dao.modificar(e);
    }

    public static void removeEscola(Integer id  ) throws  Exception{
        if(id < 0 ) throw  new Exception("Error id ");
        dao.eliminar(id);
    }

    public static List<Escola> getList(){
       //Hacer comprovaciones o algo
        List<Escola> e = dao.obtindreTots();
        return e;
    }

    public static Escola getEscola(Integer id ) throws Exception{
        if(id < 0 ) throw new  Exception("ERROR ID ");
        return  dao.obtenir(id);
    }


//OTROS.... CONFIRMAMELO
}
