package controller;

import model.dto.*;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;
import service.EscolaService;
import service.SectorService;
import service.ViaService;

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

/**Listar les vies **/
    // LISTAR TODAS
    public void listar() {
        try {
            List<Via> lista = service.obtenerTodos();
            lista.forEach(System.out::println);
        }catch (Exception e){
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

    // CREAR
    /**
     * Funcio que permet crear Via controller
     * **/
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

            System.out.println("tipus de via (1 Clasica, 2 Esportiva, 3 Gel:");
            int tipus_via = sc.nextInt();

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

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("Vía creada correctamente");
    }



    // eliminar
    /**
     * Metode que elimina amb id
     * @param id int
     * **/
    public void eliminar(int id) {
        try {
            service.eliminar(id);
            System.out.println("Vía eliminada correctamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // MODIFICAR
    /**
     * Metode que modifica vies
     * @param v
     * **/
    public void modificar(Via v) {
        try {
            service.modificar(v);
            System.out.println("Vía modificada correctamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // BUSCAR POR ID
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

    //LISTAR VIAS DISPONIBLES
    /*
    public String viesDisponibles(Escola e ){
        try {
            String missatge = service.viesDisponibles(e);
            return missatge;
        }
        catch (Exception err ){
            System.out.println(err);
            return null;
        }
    }
*/
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
    // rango (6a 9a)
    /**
    Parametre EX:  rango (6a 9a) *
     @param format String

     * **/
    public void viesPerDificultat(String format){
        try{
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
 * @param escola INT id
 * **/
    public void mostrarViesLlargues(int escola){
        try{
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
 * @param dia int Mostrar viasAptes recents depentn del dia
 * **/
    public void viesAptesRecent(int dia){
        try {
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