package service;

import dao.interfaces.EscolaDAO;
import dao.interfaces.SectorDAO;
import model.dto.EscolaDisponibleDTO;
import model.dto.EscolesRestricDTO;
import model.entity.Escola;

import java.util.List;

// AQUI IMPLEMENTAREMOS  LOS METODOS DE LA INTERFICIE DESDE EL CONTROLADOR LLAMAREMOS ESOS METODOS
// TODOS LOS METODOS ABRIRAN LA CONEXION
public class EscolaService {


    private final EscolaDAO escoladao;
    private final SectorDAO sectordao;

    public EscolaService(EscolaDAO edao, SectorDAO sd) {
        this.escoladao = edao;
        this.sectordao = sd;
    }
/**@param e Escola **/
    // inserta un sector a la base de datos
    public void crearEscola(Escola e) throws Exception {

        if (e == null) {
            throw new Exception("El sector no puede ser null");
        }

        if (e.getNom() == null || e.getNom().isEmpty()) {
            throw new Exception("El nombre es obligatorio");
        }

        if (e.getLloc() == null || e.getLloc().isEmpty()) {
            throw new Exception("El nombre es obligatorio");
        }

        if (e.getAproximacio() == null || e.getAproximacio().isEmpty()) {
            throw new Exception("El nombre es obligatorio");
        }


        if (e.getPopularitat() < 1
                || e.getPopularitat() > 3) {
            throw new Exception("Debe indicar una escola válida");
        }
        escoladao.inserir(e);
    }
/**Obtindre Escola**/
    // devuelve una lista donde aparecen todos los sectores de la bd
    public List<Escola> obtenerTodos() {
        return escoladao.obtindreTots();
    }
/**@param id Int @return Retornar Escola**/
    // devuelve un sector a traves de su id
    public Escola obtenerPorId(int id) throws Exception {

        if (id <= 0) {
            throw new Exception("ID inválido");
        }

        return escoladao.obtenir(id);
    }
/**@param id Int  eliminar Escola **/
    // elimina un sector a traves de un id
    public void eliminarEscola(int id) throws Exception {

        if (id <= 0) {
            throw new Exception("ID inválido");
        }

        escoladao.eliminar(id);
    }

    // modifica un sector y lo busca a traves de su id en el caso que no existe te dice que no ha podido modificarlo
    public void modificarSector(Escola e) throws Exception {

        if (e == null) {
            throw new Exception("Sector no puede ser null");
        }

        if (e.getId_escola() <= 0) {
            throw new Exception("ID inválido");
        }

        escoladao.modificar(e);
    }

/**@param o Escola
 * @return Boolean**/
    public boolean isGel(Escola o) throws Exception {

        if (o == null) throw new Exception("Escola no puede ser null");

        return escoladao.isGel(o);
    }
/**@return EscolesRestrictDto**/
    public List<EscolesRestricDTO> escolesDisponibles() {
        List<EscolesRestricDTO> escoles = escoladao.escolesDisponibles();
        return escoles;
    }

/**@param es Escola **/
    public List<EscolaDisponibleDTO> viasDisponibles(Escola es) throws Exception {
        if (es == null) throw new Exception("ERROR");
        List<EscolaDisponibleDTO> viaD = escoladao.viesDisponibles(es);

        return viaD;
    }
/**@param es Escola @return Retornar id **/
    public int retornaId(Escola es) throws Exception {
        if (es == null) throw new RuntimeException("Escoal null");
        int id = escoladao.inserirRetornantId(es);
        return id;
    }
/**@param es Escola**/
public void crearEscolaId(Escola es)
        throws Exception {

    if (es == null) {

        throw new RuntimeException(
                "Escola null"
        );
    }

    if (es.getNom() == null
            || es.getNom().isBlank()) {

        throw new RuntimeException(
                "Nom obligatori"
        );
    }

    if (es.getLloc() == null
            || es.getLloc().isBlank()) {

        throw new RuntimeException(
                "Lloc obligatori"
        );
    }

    if (es.getAproximacio() == null
            || es.getAproximacio().isBlank()) {

        throw new RuntimeException(
                "Aproximacio obligatoria"
        );
    }

    if (es.getPopularitat() < 1
            || es.getPopularitat() > 3) {

        throw new RuntimeException(
                "Popularitat incorrecta"
        );
    }

    int idEscola =
            escoladao.inserirRetornantId(es);

    es.setId_escola(idEscola);
}
/**@param nombre String **/
    public List<Escola> buscarPorNombre(String nombre)
            throws Exception {

        if (nombre == null || nombre.isBlank()) {

            throw new Exception(
                    "Nom no introduit"
            );
        }

        List<Escola> lista =
                escoladao.buscarPorNombre(nombre);

        if (lista.isEmpty()) {

            throw new Exception(
                    "No s'han trobat escoles"
            );
        }

        return lista;
    }
/**@param e Escola **/
    public void modificarEscola(Escola e)
            throws Exception {

        if (e == null) {

            throw new Exception(
                    "Error introduint dades"
            );
        }

        if (e.getId_escola() <= 0) {

            throw new Exception(
                    "ID inválid"
            );
        }

        if (e.getNom() == null
                || e.getNom().isBlank()) {

            throw new Exception(
                    "Nom incorrecte"
            );
        }

        if (e.getLloc() == null
                || e.getLloc().isBlank()) {

            throw new Exception(
                    "Lloc incorrecte"
            );
        }

        if (e.getAproximacio() == null
                || e.getAproximacio().isBlank()) {

            throw new Exception(
                    "Aproximacio incorrecta"
            );
        }

        if (e.getPopularitat() < 1
                || e.getPopularitat() > 3) {

            throw new Exception(
                    "Popularitat incorrecta"
            );
        }

        escoladao.modificar(e);
    }
}

