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
    /**@param id Integer @return Sector obtindre un sector en especific**/
    public Sector getSector(Integer id) {
        try {
            return service.obtenerPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**@param s Sector  crear sector**/
    public void crearSector(Sector s) {
        try {
            service.crearSector(s);
            System.out.println("Sector creado correctamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
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
    /**@param s Sector  modificar sector **/
    // modificar un sector de la base de datos
    public void modificarSector(Sector s) {
        try {
            service.modificarSector(s);
            System.out.println("Sector modificado correctamente");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**@param id Int Elimnar sector especific **/
    // eliminar un sector de la base de datos
    public void eliminarSector(int id) {
        try {
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
    /**@param quantitat int **/
    public void sectorViesDisponibles(int quantitat) {
        try {
            List<SectorViaDispDTO> lista = service.sectorViesDisponibles(quantitat);
            if (lista.isEmpty()) {
                System.out.println("No hi han Sectors amb " + quantitat + " vies disponibles");
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

            System.out.println("Latitud:");
            float lat = sc.nextFloat();

            System.out.println("Longitud:");
            float lon = sc.nextFloat();
            sc.nextLine();

            System.out.println("Aproximacio:");
            String aprox = sc.nextLine();

            System.out.println("Popularitat (1-3):");
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

            System.out.println("Llargada:");
            int llargada = sc.nextInt();

            System.out.println("Tipus via:");
            int tipus = sc.nextInt();
            sc.nextLine();

            System.out.println("Dificultat:");
            String dif = sc.nextLine();

            System.out.println("Orientacio:");
            String ori = sc.nextLine();

            System.out.println("Ancoratge:");
            String anc = sc.nextLine();

            System.out.println("Troca:");
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