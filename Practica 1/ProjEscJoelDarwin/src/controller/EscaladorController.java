package controller;

import model.entity.Escalador;
 import service.EscaladorService;


import java.util.List;

public class EscaladorController {

   private  EscaladorService dao ;

public EscaladorController(EscaladorService s){this.dao = s;}
    public  void addEscalador(Escalador e ) {

    try{dao.crearEscalador(e);}
    catch (Exception e2 ) {
        System.out.println(e2);
    }
}

    public   void SetEscalador( Escalador e ) {
      try{
        dao.modificarEscalador(e);
      }
      catch (Exception e2  ){
          System.out.println(e2);
      }
    }

    public  void removeEscalador(Integer id ) {
        try {
            dao.eliminarEscalador(id);
        }
        catch (Exception e ){
            System.out.println(id);
        }
    }

    public Escalador getEscaldorDni(String dni){
        try {
            return dao.obtenerPorDni(dni);
        }
        catch (Exception e ){
        return null;
        }
    }

    public  void removedni(String dni){
       try {
           dao.eliminarPerDni(dni);
       }
       catch (Exception e ){
           System.out.println(e);
       }
    }

    public  List<Escalador> getList(){
        //Hacer comprovaciones o algo
        List<Escalador> e = null;
        try {
             e = dao.obtenerTodos();
        }
        catch (Exception e2 ){

            return null;
        }

        return e;
    }

    public  Escalador getEscalador(Integer id  ) {
    try {
        return dao.obtenerPorId(id);
    }
    catch(Exception e ){
        return null;
    }

    }
}
