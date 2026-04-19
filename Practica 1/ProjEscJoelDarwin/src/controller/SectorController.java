package controller;

import model.entity.Sector;
import service.SectorService;

import java.util.List;

public class SectorController {

    private final SectorService service;

    public SectorController(SectorService service) {
        this.service = service;
    }

    // Añadir un sector
    public void crearSector(Sector s) {
        service.crearSector(s);
        System.out.println("Sector creado correctamente");
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
        service.modificarSector(s);
        System.out.println("Sector modificado correctamente");
    }

    // eliminar un sector de la base de datos
    public void eliminarSector(int id) {
        service.eliminarSector(id);
        System.out.println("Sector eliminado correctamente");
    }
}