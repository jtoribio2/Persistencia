package controller;

import model.dto.EscaladorNivellDTO;
import model.entity.Escalador;
 import service.EscaladorService;


import java.util.List;

public class EscaladorController {

   private  EscaladorService dao ;
/**
 * @param s Service
 * **/
public EscaladorController(EscaladorService s){this.dao = s;}
    public  void addEscalador(Escalador e ) {

    try{
        dao.crearEscalador(e);
    }
    catch (Exception e2 ) {
        System.out.println(e2);
    }
}
    /**
     @param e Escalador *
     **/
    public   void SetEscalador( Escalador e ) {
      try{
        dao.modificarEscalador(e);
      }
      catch (Exception e2  ){
          System.out.println(e2);
      }
    }
/**
 * @param id Integer
 * **/
    public  void removeEscalador(Integer id ) {
        try {
            dao.eliminarEscalador(id);
            System.out.println("ESCALADOR ELIMINADO");
        }
        catch (Exception e ){
            System.out.println(id);
        }
    }
/**
 * @param dni String
 * @return Escalador
 * **/
    public Escalador getEscaldorDni(String dni){
        try {
            Escalador es = dao.obtenerPorDni(dni);
            System.out.println("ESCALADOR ELIMINAT");
            return es;
        }
        catch (Exception e ){
        System.out.println(e);
        return null;
        }
    }
    /**
     * @param dni String
     * **/
    public  void removedni(String dni){
       try {
           dao.eliminarPerDni(dni);
       }
       catch (Exception e ){
           System.out.println(e);
       }
    }
    /**
     * @return List<Escalador> Un arrayList que devuelve Escaldores
     * **/
    public  List<Escalador> getList(){


        try {
            List<Escalador> e = dao.obtenerTodos();

            return e;
        }
        catch (Exception e2 ){
            System.out.println(e2);
            return null;
        }


    }
/**
 * @param id Integer
 * @return Escalador
 * **/
    public  Escalador getEscalador(Integer id  ) {
    try {
        return dao.obtenerPorId(id);
    }
    catch(Exception e ){
        return null;
    }

    }
/**@param dni String**/
    public void buscarPorNivel(String dni){
           try {
               List<EscaladorNivellDTO> llista = dao.buscarPorNivell(dni);
               if (llista.isEmpty()) {
                   System.out.println("No hi han escaladors amb el mateix nivell que tu asolit o superior");
               } else {
                   for (int i = 0; i < llista.size(); i++) {
                       System.out.println(llista.get(i));
                   }
               }
           }catch (Exception e ){
               System.out.println(e);
           }
    }

}
