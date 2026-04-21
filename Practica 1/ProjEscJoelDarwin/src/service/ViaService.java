package service;

import dao.interfaces.ViaDAO;
import model.entity.Via;

import java.util.List;

public class ViaService {

    private final ViaDAO viaDAO;

    public ViaService(ViaDAO viaDAO) {
        this.viaDAO = viaDAO;
    }

    // inserta una via
    public void crear(Via v) {

        if (v == null) {
            throw new RuntimeException("La vía no puede ser null");
        }

        if (v.getNom() == null || v.getNom().isEmpty()) {
            throw new RuntimeException("El nombre de la vía es obligatorio");
        }

        if (v.getId_sector() <= 0) {
            throw new RuntimeException("El sector es obligatorio");
        }

        if (v.getId_tipus_via() <= 0) {
            throw new RuntimeException("El tipo de vía es obligatorio");
        }

        if (v.getLlargada() <= 0) {
            throw new RuntimeException("La longitud debe ser mayor que 0");
        }

        viaDAO.inserir(v);
    }

    // Lista todas la vias
    public List<Via> obtenerTodos() {
        return viaDAO.obtindreTots();
    }

    // busca una via por id
    public Via obtenerPorId(int id) {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        Via via = viaDAO.obtenir(id);

        if (via == null) {
            throw new RuntimeException("No se encontró la vía con id " + id);
        }

        return via;
    }

    // Borra una via per id
    public void eliminar(int id) {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        viaDAO.eliminar(id);
    }

    // Modifica una via
    public void modificar(Via v) {

        if (v == null) {
            throw new RuntimeException("La vía no puede ser null");
        }

        if (v.getId_via() <= 0) {
            throw new RuntimeException("ID inválido");
        }

        if (v.getNom() == null || v.getNom().isEmpty()) {
            throw new RuntimeException("El nombre de la vía es obligatorio");
        }

        viaDAO.modificar(v);
    }

    // Busca por nombre y devuelve una lista con todas las vias que se llamana asi
    public List<Via> buscarPorNombre(String nombre) {

        if (nombre == null || nombre.isEmpty()) {
            throw new RuntimeException("El nombre no puede estar vacío");
        }

        List<Via> lista = viaDAO.buscarPorNombre(nombre);

        if (lista.isEmpty()) {
            System.out.println("No se encontraron vías con ese nombre");
        }

        return lista;
    }
}