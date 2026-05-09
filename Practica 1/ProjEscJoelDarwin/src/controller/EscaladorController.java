package controller;

import model.dto.EscaladorNivellDTO;
import model.entity.Escalador;
 import service.EscaladorService;


import java.util.List;
import java.util.Scanner;

public class EscaladorController {
    private static Scanner sc = new Scanner(System.in);
   private  EscaladorService service;
/**
 * @param s Service
 * **/
public EscaladorController(EscaladorService s){this.service = s;}

    public void crear() {
        try {

            System.out.println("DNI:");
            String dni = sc.nextLine();

            System.out.println("Nom:");
            String nom = sc.nextLine();

            System.out.println("Edat:");
            int edat = sc.nextInt();
            sc.nextLine();
            System.out.println("""
                
        Estil:
        1. Esportiva
        2. Classica
        3. Gel
        """);

            int estil = sc.nextInt();
            sc.nextLine();

            Escalador e = new Escalador(
                    0,
                    dni,
                    nom,
                    edat,
                    estil
            );

            service.crearEscalador(e);

            System.out.println("Escalador creat correctament");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public  void addEscalador(Escalador e ) {

    try{
        service.crearEscalador(e);
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
        service.modificarEscalador(e);
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
            service.eliminarEscalador(id);
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
            Escalador es = service.obtenerPorDni(dni);
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
           service.eliminarPerDni(dni);
       }
       catch (Exception e ){
           System.out.println(e);
       }
    }
    public void mostrarTots() {

        try {

            List<Escalador> lista =
                    service.obtenerTodos();

            for (Escalador e : lista) {

                System.out.println(e);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
/**
 * @param id Integer
 * @return Escalador
 * **/
    public  Escalador getEscalador(Integer id  ) {
    try {
        return service.obtenerPorId(id);
    }
    catch(Exception e ){
        return null;
    }

    }
/**@param dni String**/
    public void buscarPorNivel(String dni){
           try {
               List<EscaladorNivellDTO> llista = service.buscarPorNivell(dni);
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

    public void buscarPerDni(String dni) {

        try {

            Escalador e = service.obtenerPorDni(dni);

            if (e == null) {

                System.out.println(
                        "No existeix cap escalador amb aquest DNI"
                );

            } else {

                System.out.println(e);
            }

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

}
