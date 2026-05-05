package controller;

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
    public void buscarPorNombre(String nombre) {
        try {
            List<Via> lista = service.buscarPorNombre(nombre);
            lista.forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // CREAR

    public void crear() {
        // enseñamos las escolas y el usuario elije una para meter la via
        List<Escola> escolas = escolaService.obtenerTodos();

        System.out.println("Elige una escola:");

        for (Escola e : escolas) {
            System.out.println(e.getId_escola() + " - " + e.getNom());
        }

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

        System.out.println("Tipus roca");
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

    // eliminar
    public void eliminar(int id) {
        try {
            service.eliminar(id);
            System.out.println("Vía eliminada correctamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // MODIFICAR
    public void modificar(Via v) {
        try {
            service.modificar(v);
            System.out.println("Vía modificada correctamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // BUSCAR POR ID
    public void obtenerPorId(int id) {
        try {
            Via v = service.obtenerPorId(id);
            System.out.println(v);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    //LISTAR VIAS DISPONIBLES
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

    public Sector mostrarSector(Via via) {
        Sector s = service.buscarSector(via);
        return s;
    }

    public Escola mostrarEscola(Via via) {
        Escola e = service.buscarEscola(via);
        return e;
    }

    public List<Via> viesPerDificultat(String format){
        try{
            return service.viesPerDificultat(format);
        }
        catch (Exception e ){
            System.out.println(e);
            return null;
        }
    }




    public List<Via> viesPerEstatTancada(){
        try{
            return service.viesPerEstatTancat();
        }
        catch (Exception e ){
            System.out.println(e);
            return null;
        }
    }


    public List<Via> viesPerEstatApte(){
        try{
            return service.viesPerEstatApte();
        }
        catch (Exception e ){
            System.out.println(e);
            return null;
        }
    }


    public List<Via> mostrarViesLlargues(int escola){
        try{
            return service.mostrarViesLlargues(escola);
        }
        catch (Exception e ){
            System.out.println(e);
            return null;
        }
    }

    public List<Via> viesAptesRecent(){
        return service.viesAptesRecent();
    }
}