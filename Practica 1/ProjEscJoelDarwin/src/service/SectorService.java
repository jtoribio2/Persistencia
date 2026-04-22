package service;

import dao.interfaces.SectorDAO;
import model.entity.Sector;

import java.util.List;

public class SectorService {

    private final SectorDAO sectorDAO;

    public SectorService(SectorDAO sectorDAO) {
        this.sectorDAO = sectorDAO;
    }

    // inserta un sector a la base de datos
    public void crearSector(Sector s) {

        if (s == null) {
            throw new RuntimeException("El sector no puede ser null");
        }

        if (s.getNom() == null || s.getNom().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (s.getPopularitat() < 1 || s.getPopularitat() > 3) {
            throw new RuntimeException("Popularidad inválida (1-3)");
        }

        if (s.getId_escoles() <= 0) {
            throw new RuntimeException("Debe indicar una escola válida");
        }
        sectorDAO.inserir(s);
    }

    // devuelve una lista donde aparecen todos los sectores de la bd
    public List<Sector> obtenerTodos() {
        return sectorDAO.obtindreTots();
    }

    // devuelve un sector a traves de su id
    public Sector obtenerPorId(int id) {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        return sectorDAO.obtenir(id);
    }

    // elimina un sector a traves de un id
    public void eliminarSector(int id) {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        sectorDAO.eliminar(id);
    }

    // modifica un sector y lo busca a traves de su id en el caso que no existe te dice que no ha podido modificarlo
    public void modificarSector(Sector s)throws Exception {

        if (s == null) {
            throw new RuntimeException("Sector no puede ser null");
        }

        if (s.getId_sector() <= 0) {
            throw new RuntimeException("ID inválido");
        }

        sectorDAO.modificar(s);
    }

    public List<Sector> buscarPorNombre(String nombre)throws Exception {

        List<Sector> lista = sectorDAO.buscarPorNombre(nombre);

        if (nombre == null || nombre.isEmpty()) {
            throw new RuntimeException("Nombre vacío");
        }

        if (lista.isEmpty()) {
            throw new RuntimeException("No se encontraron sectores con ese nombre");
        }

        return lista;
    }
}