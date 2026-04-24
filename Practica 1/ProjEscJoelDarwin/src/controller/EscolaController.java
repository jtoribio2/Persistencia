package controller;

import model.entity.Escola;
import service.EscolaService;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class EscolaController {

    private   final EscolaService service2;
    public EscolaController(EscolaService e ){this.service2= e;}

    public   void addEscola(Escola e ) {
       // if(e == null) throw new Exception("ERROR DADES BUIDES");
        try{
           service2.crearEscola(e);
        }
        catch (Exception err ){
            System.out.println(err);
        }
    }

    public  void SetEscola(Escola e )  {
       // if(e == null) throw new Exception("ERROR DADES BUIDES");
        try {
            service2.modificarSector(e);
        }
        catch(Exception err){
            System.out.println(err);
        }
    }

    public  void removeEscola(Integer id  ) {
       try {
           service2.eliminarSector(id);
       }
       catch (Exception err){
           System.out.println(err);
       }
    }

    public  List<Escola> getList(){
       //Hacer comprovaciones o algo
        List<Escola> e = service2.obtenerTodos();
        return e ;
    }

    public  Escola getEscola(Integer id ) {
        try {
            return service2.obtenerPorId(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

public boolean isGel(Escola o ) {
try {
    return service2.isGel(o);
}
catch (Exception e ){
    System.out.println(e);
    return false;
}

}

}
