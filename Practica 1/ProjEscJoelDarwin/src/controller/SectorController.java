package controller;

import model.dto.SectorViaDispDTO;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;
import service.EscolaService;
import service.SectorService;

import java.util.List;
import java.util.Scanner;

public class SectorController {

    private final SectorService service;
    private final EscolaService escolaService;
    private final Scanner sc = new Scanner(System.in);

    public SectorController(
            SectorService service,
            EscolaService escolaService
    ) {
        this.service = service;
        this.escolaService = escolaService;
    }
    /**Mostrar Tots sectors**/
    public void mostrarTots() {

        try {

            List<Sector> lista =
                    service.obtenerTodos();

            for (Sector s : lista) {

                System.out.println(s);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    /**Modificar Sector **/
    public void modificar() {

        Scanner sc = new Scanner(System.in);

        try {

            mostrarTots();

            System.out.println(
                    "Introdueix ID sector a modificar:"
            );

            int id = sc.nextInt();
            sc.nextLine();

            Sector actual =
                    service.obtenerPorId(id);

            if (actual == null) {

                System.out.println(
                        "No existeix aquest sector"
                );

                return;
            }

            System.out.println("\nSECTOR ACTUAL:");
            System.out.println(actual);

            System.out.println("\nNou nom:");
            String nom = sc.nextLine();

            System.out.println("Nova latitut:");
            float latitut = sc.nextFloat();

            System.out.println("Nova longitut:");
            float longitut = sc.nextFloat();
            sc.nextLine();

            System.out.println("Nova aproximacio:");
            String aproximacio = sc.nextLine();

            System.out.println("""

        Nova popularitat:
        1. Baixa
        2. Mitjana
        3. Alta
        """);

            int popularitat = sc.nextInt();

            Sector modificat =
                    new Sector(

                            id,
                            actual.getId_escoles(),
                            nom,
                            latitut,
                            longitut,
                            aproximacio,
                            popularitat
                    );

            service.modificarSector(modificat);

            System.out.println(
                    "Sector modificat correctament"
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    /** Elimnar sector especific **/
    // eliminar un sector de la base de datos
    public void eliminarSector() {
        try {
            System.out.println("Elija el sector a elminar");
          List<Sector>  s =  service.obtenerTodos();
          for(Sector S : s ){
              System.out.println(S.getId_sector() + " " + S.getNom());
          }
            int id =   sc.nextInt();
          sc.nextLine();
           service.eliminarSector(id);

            System.out.println("Sector eliminado correctamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    /**@param nombre String buscar sector per controller **/
    //Busca por nombre un
    public void buscarPorNombre(String nombre) {
        List<Sector> lista = null;
        try {
            lista = service.buscarPorNombre(nombre);
            lista.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**@param idSector int @return Escola **/
    public Escola mostrarEscola(int idSector) {
        return service.buscarEscola(idSector);
    }
    /**Sectors amb mes vies llargues int **/
    public void sectorViesDisponibles() {
        try {
            System.out.println("Quantes vies Disponibles ha de tindre el sector");
            int id = sc.nextInt();
            sc.nextLine();

            List<SectorViaDispDTO> lista = service.sectorViesDisponibles(id);
            if (lista.isEmpty()) {
                System.out.println("No hi han Sectors amb " + id + " vies disponibles");
            } else {
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println(lista.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
/**Crea el sector amb una via (Si un es crea i altre no s'elimnia**/
    public void crearSectorConVia() {

        try {

            // enseñamos la escola que queremos elegir para el sector
            List<Escola> escolas = escolaService.obtenerTodos();

            System.out.println("Elige una escola:");

            for (Escola e : escolas) {
                System.out.println(
                        e.getId_escola() + " - " + e.getNom()
                );
            }

            int idEscola = sc.nextInt();
            sc.nextLine();

            // introducimos los datos del sector
            System.out.println("Nombre sector:");
            String nomSector = sc.nextLine();

            System.out.println("Latitud (x,x...):");
            float lat = sc.nextFloat();

            System.out.println("Longitud(x,x...):");
            float lon = sc.nextFloat();
            sc.nextLine();

            System.out.println("Aproximacio(x min...):");
            String aprox = sc.nextLine();

            System.out.println("Popularitat (1-3)\n1.Baixa\n2.Mitjana\n3.Alta:");
            int pop = sc.nextInt();
            sc.nextLine();

            Sector s = new Sector(
                    0,
                    idEscola,
                    nomSector,
                    lat,
                    lon,
                    aprox,
                    pop
            );

            // creamos la via
            System.out.println("Nom via:");
            String nomVia = sc.nextLine();

            System.out.println("Llargada(x metres ):");
            int llargada = sc.nextInt();
            sc.nextLine();

            System.out.println("Tipus via\n1.Clasica\n2.Esportiva\n3.Gel:");
            int tipus = sc.nextInt();
            sc.nextLine();

            System.out.println("Dificultat:\n Nivell: 4,4+,5,5+,6a,6a+,6b,6b+,6c,6c+,7a,7a+,7b,7b+,7c,7c+,8a,8a+,8b");
            String dif = sc.nextLine();

            System.out.println("Orientacio(N,S,W,E): ");
            String ori = sc.nextLine();

            System.out.println("Ancoratge:");
            String anc = sc.nextLine();

            System.out.println("Troca:  [conglomerat, granit, calcaria, arenisca, altres]");
            String troca = sc.nextLine();

            Via v = new Via(
                    0,
                    tipus,
                    nomVia,
                    llargada,
                    dif,
                    ori,
                    anc,
                    troca
            );
            try {
                service.crearSectorConVia(s, v);

                System.out.println("Sector y vía creados correctamente");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

}