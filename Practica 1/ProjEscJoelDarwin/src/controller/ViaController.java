package controller;

import model.dto.*;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;
import service.EscolaService;
import service.SectorService;
import service.ViaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViaController {
    private final ViaService service;
    private final EscolaService escolaService;
    private final SectorService sectorService;
    Scanner sc = new Scanner(System.in);
    public ViaController(ViaService service,
                         SectorService sectorService,
                         EscolaService escolaService) {

        this.service = service;
        this.sectorService = sectorService;
        this.escolaService = escolaService;
    }
/**Mostrar tots les Vies **/
    public void mostrarTots() {

        try {

            List<Via> lista =
                    service.obtenerTodos();

            for (Via v : lista) {

                System.out.println(v);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // BUSCAR POR NOMBRE
    /**
     * @param nombre String
     * **/
    public void buscarPorNombre(String nombre) {
        try {
            List<Via> lista = service.buscarPorNombre(nombre);
            lista.forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void crear() {
        // enseñamos las escolas y el usuario elije una para meter la via
        List<Escola> escolas = escolaService.obtenerTodos();

        System.out.println("Elige una escola:");

        for (Escola e : escolas) {
            System.out.println(e.getId_escola() + " - " + e.getNom());
        }
        try {
            int idEscola = sc.nextInt();


            // enseñamos los sectores de la escola de arriba, y el usuario elige uno
            List<Sector> sectores = sectorService.buscarPorEscola(idEscola);

            System.out.println("Elige un sector:");

            for (Sector s : sectores) {
                System.out.println(s.getId_sector() + " - " + s.getNom());
            }

            int idSector = sc.nextInt();

            sc.nextLine();

            // datos de la via
            System.out.println("Nombre:");
            String nom = sc.nextLine();

            System.out.println("Llargada:");
            int llargada = sc.nextInt();
            sc.nextLine();

            System.out.println("tipus de via (1 Clasica, 2 Esportiva, 3 Gel):");
            int tipus_via = sc.nextInt();
            sc.nextLine();
            System.out.println("Dificultat (ex: 6a ):");
            String dificultat = sc.nextLine();

            System.out.println("Oreienciacio (ex: N)");
            String orientacio = sc.nextLine();

            System.out.println("Ancoratge:");
            String ancoratge = sc.nextLine();

            System.out.println("Tipus de roca:  [conglomerat, granit, calcaria, arenisca, altres]");
            String troca = sc.nextLine();

            // creamos la via
            Via v = new Via(
                    idSector,
                    tipus_via,
                    nom,
                    llargada,
                    dificultat,
                    orientacio,
                    ancoratge,
                    troca
            );

            //  llamamos al service para introducir la via con su sector elegido

            try {
                service.crear(v);

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("Vía creada correctamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
    /**
     * Metode que elimina amb id
     *
     * **/
    public void eliminar() {
        try {
            System.out.println("Introduir numero via  ");
            List<Via> vias = service.obtenerTodos();

            for(Via v : vias){
                Sector s = sectorService.obtenerPorId(v.getId_sector());
                System.out.println(v.getId_via()+".:" + " " + v.getNom() + "  Sector: " +  s.getNom());

            }
          int id = sc.nextInt();
            sc.nextLine();
            service.eliminar(id);
            System.out.println("Vía eliminada correctamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void modificar() {

        Scanner sc = new Scanner(System.in);

        try {

            mostrarTots();

            System.out.println(
                    "Introdueix ID via a modificar:"
            );

            int id = sc.nextInt();
            sc.nextLine();

            Via actual =
                    service.obtenerPorId(id);

            if (actual == null) {

                System.out.println(
                        "No existeix aquesta via"
                );

                return;
            }

            System.out.println("\nVIA ACTUAL:");
            System.out.println(actual);

            System.out.println("\nNou nom:");
            String nom = sc.nextLine();

            System.out.println("Nova llargada:");
            int llargada = sc.nextInt();
            sc.nextLine();

            System.out.println(
                    "Nova dificultat:"
            );

            String dificultat = sc.nextLine();

            System.out.println(
                    "Nova orientacio:"
            );

            String orientacio = sc.nextLine();

            System.out.println(
                    "Nou ancoratge:"
            );

            String ancoratge = sc.nextLine();

            System.out.println(
                    "Nou tipus roca:"
            );

            String troca = sc.nextLine();

            Via modificada =
                    new Via(

                            id,
                            actual.getId_sector(),
                            actual.getId_tipus_via(),

                            nom,
                            llargada,
                            dificultat,
                            orientacio,
                            ancoratge,
                            troca
                    );

            service.modificar(modificada);

            System.out.println(
                    "Via modificada correctament"
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    /**
     * Metode per obtindre per id
     * @param id int
     * **/
    public void obtenerPorId(int id) {
        try {
            Via v = service.obtenerPorId(id);
            System.out.println(v);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }


  /**
   * @param via Via
   * @return Retornar sector
   * **/
    public Sector mostrarSector(Via via) {
        Sector s = service.buscarSector(via);
        return s;
    }
/**
 * @param  via Via
 * @return retorna una Escola en Especifica
 * **/
    public Escola mostrarEscola(Via via) {
        Escola e = service.buscarEscola(via);
        return e;
    }
    /**
    Parametre EX:  rango (6a 9a) *
     * **/
    public void viesPerDificultat(){
        try{
            System.out.println("VIAS PER DIFICULTAT Ex(6a 9c)");
            String format =sc.nextLine();
            List<ViaPerDifDTO> viesperdif = service.viesPerDificultat(format);
            for (int i = 0 ; i < viesperdif.size(); i++){
                System.out.println(viesperdif.get(i));
            }
        }
        catch (Exception e ){
            System.out.println(e);
        }
    }



/**
 * Mostra el nom de les vies
 * **/
    public void  viesPerEstatTancada(){
        try{
           List<ViesPerEstatTancatDTO> vias = service.viesPerEstatTancat();
           for(ViesPerEstatTancatDTO v : vias){
               System.out.println(v);
           }
        }
        catch (Exception e ){
            System.out.println(e);
        }
    }

/**
 * Vias que son aptes
 * **/
    public void  viesPerEstatApte(){
        try{
            List<ViesPerEstatApteDTO> via =  service.viesPerEstatApte();
            for(ViesPerEstatApteDTO v : via){
                System.out.println(v);
            }
        }
        catch (Exception e ){
            System.out.println(e);
        }
    }

/**
 * Mostrar vias llargues
 * **/
    public void mostrarViesLlargues(){
        try{
            System.out.println("Selecciona una escola especifica(Mostrara una de le vies mes llargues)");
       List<Escola> es =      escolaService.obtenerTodos();
       for(Escola ea : es){
           System.out.println(ea.getId_escola() + " " + ea.getNom());
       }
            int escola  = sc.nextInt();
            sc.nextLine();
            List<ViesLlarguesDTO> via = service.mostrarViesLlargues(escola);
            if(via.isEmpty()) System.out.println("No existeix la escola triada");
            for(ViesLlarguesDTO v : via){
                System.out.println(v);
            }
        }
        catch (Exception e ){
            System.out.println(e);

        }
    }
/**
 *  Mostrar viasAptes recents depentn del dia
 * **/
    public void viesAptesRecent(){
        try {
            System.out.println("Quants dies vols saber de vies passsats a aptes?");
            int dia = sc.nextInt();
            sc.nextLine();
            List<ViesAptRecentDTO> via = service.viesAptesRecent(dia);
            if (via.isEmpty()){
                System.out.println("No hi ha vies que hagin pasat a disponible en els derrers " + dia + " dies" );
            }
            else {
                for (int i = 0; i < via.size(); i++) {
                    System.out.println(via.get(i));
                }
            }
        }
        catch (Exception e ){
            System.out.println(e);
        }
    }
}