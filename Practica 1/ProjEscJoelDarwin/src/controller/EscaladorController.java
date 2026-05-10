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

    /**
     * Elimina escalador per dni
     * **/
    public  void removedni(){
       try {
           System.out.println("Inroduir DNI");
           String dni = sc.nextLine();
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

/**Bucar per nivell**/
    public void buscarPorNivel(){
           try {
               System.out.println("Introduir Dni del escalador(Les lletres en Majuscules) ");
               String dni = sc.nextLine();
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

    /**@param dni String**/
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

    /**MOdificar **/
    public void modificar() {

        Scanner sc = new Scanner(System.in);

        try {

            mostrarTots();

            System.out.println(
                    "Introdueix ID escalador a modificar:"
            );

            int id = sc.nextInt();
            sc.nextLine();

            Escalador actual =
                    service.obtenerPorId(id);

            if (actual == null) {

                System.out.println(
                        "No existeix aquest escalador"
                );

                return;
            }

            System.out.println("\nESCALADOR ACTUAL:");
            System.out.println(actual);

            System.out.println("\nNou nom:");
            String nom = sc.nextLine();

            System.out.println("Nova edat:");
            int edat = sc.nextInt();

            System.out.println("""

        Nou estil:
        1. Esportiva
        2. Classica
        3. Gel
        """);

            int estil = sc.nextInt();

            Escalador modificat =
                    new Escalador(

                            id,
                            actual.getDni(),
                            nom,
                            edat,
                            estil
                    );

            service.modificarEscalador(modificat);

            System.out.println(
                    "Escalador modificat correctament"
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

}
