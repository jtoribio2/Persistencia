package service;

import dao.interfaces.SectorDAO;
import dao.interfaces.ViaDAO;
import model.dto.SectorViaDispDTO;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;

import java.util.List;

public class SectorService {

    private final SectorDAO sectorDAO;
    private final ViaDAO viaDAO;

    public SectorService(SectorDAO sectorDAO, ViaDAO viaDAO) {
        this.sectorDAO = sectorDAO;
        this.viaDAO = viaDAO;
    }

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

            viaDAO.inserir(v);

        } catch (Exception e) {

            sectorDAO.eliminar(idSectorGenerado);

            throw new RuntimeException(
                    "No se pudo crear la vía. Sector eliminado."
            );
        }
    }

    public List<Sector> obtenerTodos() {
        return sectorDAO.obtindreTots();
    }

    public Sector obtenerPorId(int id) throws Exception {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        return sectorDAO.obtenir(id);
    }

    public void eliminarSector(int id)throws Exception {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        sectorDAO.eliminar(id);
    }

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

    public List<SectorViaDispDTO> sectorViesDisponibles(int quantitat) throws Exception {

        if (quantitat < 0) {
            throw new Exception("INCORRECTE");
        }

        return sectorDAO.sectorViesDisponibles(quantitat);
    }
    /**Eliminar sector con vias dpeendinedo de la escola **/
    public void  ElimnarSVC(int Escola) throws Exception{
        if(Escola < 0 ) throw  new Exception("Error no se pudo eliminar tneto los sectore con sus vias");
        List<Sector> sl = sectorDAO.buscarPorEscola(Escola);
        for(int i = 0; i < sl.size(); i++){
            viaDAO.EliminarViasPorSector(sl.get(i).getId_sector()); //Elimanr vias
            sectorDAO.eliminar(sl.get(i).getId_sector()); //Eliminr sector
        }
    }
}