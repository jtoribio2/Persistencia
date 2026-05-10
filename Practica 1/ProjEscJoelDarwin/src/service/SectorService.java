package service;

import dao.interfaces.SectorDAO;
import dao.interfaces.ViaDAO;
import model.dto.SectorViaDispDTO;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;

import java.util.ArrayList;
import java.util.List;

public class SectorService {

    private final SectorDAO sectorDAO;
    private final ViaDAO viaDAO;
    private final ViaService viaService;

    public SectorService(
            SectorDAO sectorDAO,
            ViaDAO viaDAO,
            ViaService viaService
    ) {

        this.sectorDAO = sectorDAO;
        this.viaDAO = viaDAO;
        this.viaService = viaService;
    }

    /**
     * @param s Sector
     **/
    public void crearSector(Sector s) throws Exception {

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

    /**
     * @param s Sector
     * @param v Via
     **/
    public void crearSectorConVia(Sector s, Via v) throws Exception {

        if (s == null) {
            throw new RuntimeException("Sector null");
        }

        if (v == null) {
            throw new RuntimeException("Via null");
        }

        int idSectorGenerado =
                sectorDAO.inserirRetornantId(s);

        try {

            v.setId_sector(idSectorGenerado);

            viaService.crear(v);

        } catch (Exception e) {

            sectorDAO.eliminar(idSectorGenerado);

            throw new RuntimeException(
                    e.getMessage()
            );
        }
    }

    /**
     * @return List Sector
     **/
    public List<Sector> obtenerTodos() {
        return sectorDAO.obtindreTots();
    }

    /**
     * @param id INT
     * @return Sector
     **/
    public Sector obtenerPorId(int id) throws Exception {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        return sectorDAO.obtenir(id);
    }

    /**
     * @param id Sector
     **/
    public void eliminarSector(int id) throws Exception {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        List<Via> vias = viaDAO.buscarViesPorSector(id);
        for (Via v : vias) {
            viaDAO.ElimnarViaEscaladors(v.getId_via());
            viaDAO.ElimnarViaDisponibilitat(v.getId_via());
            viaDAO.EliminarViaLlars(v.getId_via());
        }
        viaDAO.EliminarViasPorSector(id);
        sectorDAO.eliminar(id);
    }

    public void modificarSector(Sector s) throws Exception {

        if (s == null) {
            throw new RuntimeException("Sector no puede ser null");
        }

        if (s.getId_sector() <= 0) {
            throw new RuntimeException("ID inválido");
        }

        sectorDAO.modificar(s);
    }

    /**
     * @param nombre String
     * @return Lit sector
     **/
    public List<Sector> buscarPorNombre(String nombre) throws Exception {

        List<Sector> lista = sectorDAO.buscarPorNombre(nombre);

        if (nombre == null || nombre.isEmpty()) {
            throw new RuntimeException("Nombre vacío");
        }

        if (lista.isEmpty()) {
            throw new RuntimeException("No se encontraron sectores con ese nombre");
        }

        return lista;
    }

    /**
     * @param idSector int
     * @return Escola
     *
     **/
    public Escola buscarEscola(int idSector) {

        if (idSector <= 0) {
            throw new RuntimeException("ID inválido");
        }

        Escola escola = sectorDAO.buscarEscola(idSector);

        if (escola == null) {
            throw new RuntimeException("No se encontró escola para ese sector");
        }

        return escola;
    }

    /**
     * @param idEscola int
     **/
    public List<Sector> buscarPorEscola(int idEscola) {

        if (idEscola <= 0) {
            throw new RuntimeException("ID de escola inválido");
        }

        List<Sector> lista = sectorDAO.buscarPorEscola(idEscola);

        if (lista.isEmpty()) {
            System.out.println("No hay sectores para esta escola");
        }

        return lista;
    }

    /**
     * @param quantitat int
     * @return List Sector Via DispDto
     **/
    public List<SectorViaDispDTO> sectorViesDisponibles(int quantitat) throws Exception {

        if (quantitat < 0) {
            throw new Exception("INCORRECTE");
        }

        return sectorDAO.sectorViesDisponibles(quantitat);
    }

    /**
     * Eliminar sector con vias dpeendinedo de la escola
     *
     * @param escola int
     *
     */
    public void ElimnarSVC(int escola) throws Exception {
        if (escola < 0) throw new Exception("Error: id de escola no válido");

        List<Sector> sectores = sectorDAO.buscarPorEscola(escola);

        for (Sector sector : sectores) {
            int id_sector = sector.getId_sector();
            List<Via> vies = viaDAO.buscarViesPorSector(id_sector);

            // Para cada via del sector, borrar sus dependencias
            for (Via via : vies) {
                int id_via = via.getId_via();
                viaDAO.EliminarViaLlars(id_via);
                viaDAO.ElimnarViaEscaladors(id_via);
                viaDAO.ElimnarViaDisponibilitat(id_via);
            }

            // Una vez borradas las dependencias, borrar las vias y el sector
            viaDAO.EliminarViasPorSector(id_sector);
            sectorDAO.eliminar(id_sector);
        }
    }

}