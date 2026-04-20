package controller;

import model.entity.Escola;
import service.EscolaService;


import java.util.List;

public class EscolaController {

    private   final EscolaService service2;
    public EscolaController(EscolaService e ){this.service2= e;}

    public   void addEscola(Escola e ) throws  Exception{
       if(e == null) throw new Exception("ERROR DADES BUIDES");
       service2.crearEscola(e);
    }

    public  void SetEscola(Escola e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        service2.modificarSector(e);
    }

    public  void removeEscola(Integer id  ) throws  Exception{
        if(id < 0 ) throw  new Exception("Error id ");
        service2.eliminarSector(id);
    }

    public  List<Escola> getList(){
       //Hacer comprovaciones o algo
        List<Escola> e = service2.obtenerTodos();
        return e ;
    }

    public  Escola getEscola(Integer id ) throws Exception{
        if(id < 0 ) throw new  Exception("ERROR ID ");
        return   service2.obtenerPorId(id);
    }


//OTROS.... CONFIRMAMELO
}
