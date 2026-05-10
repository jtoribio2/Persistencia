package service;

import dao.interfaces.SectorDAO;
import dao.interfaces.EscolaDAO;
import config.AppConfig;
import controller.SectorController;
import dao.interfaces.ViaDAO;
import model.dto.ViaPerDifDTO;
import model.dto.ViesAptRecentDTO;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;
import model.dto.*;
import java.util.List;
import java.util.Scanner;

public class ViaService {
    private static Scanner scan = new Scanner(System.in);
    private final ViaDAO viaDAO;
    private final SectorDAO sectorDAO;
    private final EscolaDAO escolaDAO;


    public ViaService(
            ViaDAO viaDAO,
            SectorDAO sectorDAO,
            EscolaDAO escolaDAO
    ) {

        this.viaDAO = viaDAO;
        this.sectorDAO = sectorDAO;
        this.escolaDAO = escolaDAO;
    }
/**@param v Via **/
    // inserta una via
public void crear(Via v) throws Exception {

    if (v == null) {
        throw new RuntimeException("La vía no puede ser null");
    }

    Escola escola =
            sectorDAO.buscarEscola(
                    v.getId_sector()
            );

    if (escola == null) {

        throw new RuntimeException(
                "No se encontró escola"
        );
    }

    boolean escolaGel =
            escolaDAO.isGel(escola);

    List<Via> viesSector =
            viaDAO.buscarViesPorSector(
                    v.getId_sector()
            );

    boolean primeraVia =
            viesSector.isEmpty();

    if (!primeraVia) {

        if (escolaGel) {

            if (v.getId_tipus_via() != 3) {

                throw new RuntimeException(
                        "No pots inserir vies classiques/esportives en escola GEL"
                );
            }

        } else {

            if (v.getId_tipus_via() == 3) {

                throw new RuntimeException(
                        "No pots inserir vies GEL en escola classica/esportiva"
                );
            }
        }
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
/**Obtindre totes les vies
 * @return List Via
 * **/
    // Lista todas la vias
    public List<Via> obtenerTodos() throws Exception  {
        List<Via> lista = viaDAO.obtindreTots();
        if(lista.isEmpty()){
            throw new RuntimeException("No hi ha vies inserides a la BD");
        }
        return lista;
    }
/**
 * @param id int
 * @return via
 * **/
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
/**@param id INT**/
    // Borra una via per id
    public void eliminar(int id) throws Exception {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }
        viaDAO.EliminarViaLlars(id);
        viaDAO.ElimnarViaDisponibilitat(id);
        viaDAO.ElimnarViaEscaladors(id);

        viaDAO.eliminar(id);
    }
/**@param v Via **/
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
/**@param nombre String
 * @return  List via
 * **/
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

/**@param via Via
 * @return Sector
 * **/
    public Sector buscarSector(Via via) {

        Sector sector = viaDAO.buscarSector(via);

        if (sector == null) {
            throw new RuntimeException("No se encontró sector");
        }

        return sector;
    }
/**
 * @param via Via
 * @return Escola
 * **/
    public Escola buscarEscola(Via via) {

        Escola escola = viaDAO.buscarEscola(via);

        if (escola == null) {
            throw new RuntimeException("No se encontró escola");
        }

        return escola;
    }
/**
 * @param dades String
 * @return List ViaOerDificultatDTO
 *
 * **/
    public List<ViaPerDifDTO> viesPerDificultat(String dades) throws  Exception{
        if(dades.isBlank())throw new Exception("Has deixat en blanc el camp");
      return   viaDAO.viesPerDificultat(dades);
    }

/**
 * @return List VeisPerEstatTancatDTO
 * **/
    public List<ViesPerEstatTancatDTO> viesPerEstatTancat(){
        return viaDAO.viesPerEstatTancat();
    }
/**@return Vies per estat apte List**/
    public List<ViesPerEstatApteDTO> viesPerEstatApte(){
        return viaDAO.viesPerEstatApte();
    }
/**
 * @param escola int
 * @return List Via llargues DTO
 *
 * **/
    public List<ViesLlarguesDTO> mostrarViesLlargues(int escola) throws Exception{
        if(escola < 0) throw new Exception("ID INCORRECTE");
        return viaDAO.mostrarViesLlargues(escola);
    }
/**
 * @param dia int
 * @return List viesAPtesrecentDto
 * **/
    public List<ViesAptRecentDTO> viesAptesRecent(int dia) throws Exception{
        if (dia<=0) throw new Exception("EL TERMINI MINIM PER MIRAR VIES DISPONIBLES RECENTMENT HAN DE SER MES GRANS QUE 0");
        return viaDAO.viasAptesRecent(dia);
    }
/**@param sector int **/
    public void ElimnarViasPorSector(int sector ) throws  Exception{
        if(sector < 0) throw  new Exception("Error");
        viaDAO.EliminarViasPorSector(sector);
    }
}