package controller;

import model.entity.Sector;
import service.SectorService;

import java.util.List;

public class SectorController {

    private final SectorService service;

    public SectorController(SectorService service) {
        this.service = service;
    }

    public Sector getSector(Integer id){
        try {
            return service.obtenerPorId(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Añadir un sector
    public void crearSector(Sector s) {
        try {
            service.crearSector(s);
            System.out.println("Sector creado correctamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // mostrar todos los sectores de la base de datos
    public void mostrarTots() {
        List<Sector> lista = service.obtenerTodos();

        for (Sector s : lista) {
            System.out.println(s.getId_sector() + " - " + s.getNom());
        }
    }

    // modificar un sector de la base de datos
    public void modificarSector(Sector s) {
        try {
            service.modificarSector(s);
            System.out.println("Sector modificado correctamente");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // eliminar un sector de la base de datos
    public void eliminarSector(int id) {
        try {
            service.eliminarSector(id);
            System.out.println("Sector eliminado correctamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    //Busca por nombre un
    public void buscarPorNombre(String nombre) {
        List<Sector> lista=null;
        try {
            lista = service.buscarPorNombre(nombre);
            lista.forEach(s -> System.out.println(s));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}