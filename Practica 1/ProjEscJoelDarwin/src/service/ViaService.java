package service;

import config.AppConfig;
import controller.SectorController;
import dao.interfaces.ViaDAO;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;

import java.util.List;

public class ViaService {

    private final ViaDAO viaDAO;


    public ViaService(ViaDAO viaDAO) {
        this.viaDAO = viaDAO;
    }

    // inserta una via
    public void crear(Via v) throws Exception {

        if (v == null) {
            throw new RuntimeException("La vía no puede ser null");
        }

        Escola escola = viaDAO.buscarEscola(v);
        //excepcion de añadir una via de en escolas de gel
        if (AppConfig.getEscolaController().isGel(escola)){
            int tipus = v.getId_tipus_via();
            if (tipus == 1 || tipus == 2){
                throw  new RuntimeException("no pots inserir una via clasica o esportiva en una escola de gel");
            }
        }

        if (!AppConfig.getEscolaController().isGel(escola)){
            int tipus = v.getId_tipus_via();
            if (tipus == 3){
                throw  new RuntimeException("no pots inserir una via de gel en una escola que vies clasiques y esportives");
            }
        }

        if (escola == null) {
            throw new RuntimeException("No se pudo determinar la escola de la vía");
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
    public List<Via> obtenerTodos() throws Exception  {
        List<Via> lista = viaDAO.obtindreTots();
        if(lista.isEmpty()){
            throw new RuntimeException("No hi ha vies inserides a la BD");
        }
        return lista;
    }

    // busca una via por id
    public Via obtenerPorId(int id) throws Exception {

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
    public void eliminar(int id) throws Exception {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        viaDAO.eliminar(id);
    }

    // Modifica una via
    public void modificar(Via v) throws Exception {

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
    public List<Via> buscarPorNombre(String nombre) throws Exception  {

        if (nombre == null || nombre.isEmpty()) {
            throw new RuntimeException("El nombre no puede estar vacío");
        }

        List<Via> lista = viaDAO.buscarPorNombre(nombre);

        if (lista.isEmpty()) {
            System.out.println("No se encontraron vías con ese nombre");
        }

        return lista;
    }

    public String viesDisponibles(Escola e ) throws Exception{
        return viaDAO.viesDisponibles(e);
    }

    public Sector buscarSector(Via via) {

        Sector sector = viaDAO.buscarSector(via);

        if (sector == null) {
            throw new RuntimeException("No se encontró sector");
        }

        return sector;
    }

    public Escola buscarEscola(Via via) {

        Escola escola = viaDAO.buscarEscola(via);

        if (escola == null) {
            throw new RuntimeException("No se encontró escola");
        }

        return escola;
    }
}