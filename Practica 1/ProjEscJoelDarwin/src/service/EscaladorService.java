package service;



import dao.interfaces.EscaladorDAO;
import model.dto.EscaladorNivellDTO;
import model.entity.Escalador;

import java.util.List;

// AQUI IMPLEMENTAREMOS  LOS METODOS DE LA INTERFICIE DESDE EL CONTROLADOR LLAMAREMOS ESOS METODOS
// TODOS LOS METODOS ABRIRAN LA CONEXION

public class EscaladorService  {
    private final EscaladorDAO escaladorDao;

    /**
     * @param edao COLOCAMOS OBJETO
     * **/
    public EscaladorService(EscaladorDAO edao) {
        this.escaladorDao = edao;
    }
    // insertae EscLADOR
    /**
     * @param e ESCALADOR
     * **/
    public void crearEscalador(Escalador e ) throws Exception {

        if (e == null) {
            throw new Exception("Error dades de escalador no introduides");
        }

        if (e.getNom() == null || e.getNom().isEmpty())   {
            throw new Exception("Falta el nom");
        }

        if (e.getDni() == null
                || e.getDni().isEmpty()
                || !e.getDni().matches("^[0-9]{8}[A-Z]$")) {

            throw new Exception("El DNI es INCORRECTE");
        }

        if(e.getEdat() < 0 || e.getEdat() > 100) throw new Exception("Error edad");


        if (e.getEstil() < 1 || e.getEstil() > 3) {

            throw new Exception("Debe indicar una estilo válida");
        }

        escaladorDao.inserir(e);
    }

    // devuelve una lista donde aparecen todos los sectores de la bd
    /**
     *@return Escalador Retorna UNA LLISTA ESCALADOR
     **/
    public List<Escalador> obtenerTodos() throws  Exception {

        List<Escalador> e = escaladorDao.obtindreTots();

        if(e.isEmpty()) throw new Exception("ERROR ESTA BUIDA LA BD ");

        return e ;

    }

    // devuelve un sector a traves de su id
    /**
     * @param id INT
     * @return Escaladro retorna el objecte escalador
     * **/
    public Escalador  obtenerPorId(Integer id ) throws Exception{

        if (id < 0) {
            throw new Exception("ID inválido");
        }

        return escaladorDao.obtenir(id);
    }
    /**
     @param dni String*
     @return Retorna objecte Escalador
     **/
    public Escalador obtenerPorDni(String dni ) throws Exception{

        if (!dni.matches("^[0-9]{8}[A-Z]$") || dni.length() > 9) {
            throw new Exception("DNI INCORRECTE");
        }
        return escaladorDao.obtenirPerDni(dni);
    }

    /**
     * @param dni String
     *
     * **/
    public void eliminarPerDni(String dni) throws Exception{

        if(dni.isBlank()){throw new Exception("DNI NO INTRODUIT");}
        if (!dni.matches("^[0-9]{8}[A-Z]$") ) {
            throw new Exception("DNI INCORRECTE");
        }
       Escalador E =  escaladorDao.obtenirPerDni(dni);
        //Para eliminar de la tabla ESCLADORS CLIENTS
        escaladorDao.EliminarEscalador_vies(E.getId_escalador());
        escaladorDao.eliminarDni(E.getDni());
    }
    // elimina un sector a traves de un id
    /***
     * @param id Int
     * **/
    public void eliminarEscalador(Integer id) throws Exception {

        if (id < 0) {
            throw new Exception("ID inválido");
        }

        escaladorDao.eliminar(id);
    }

    // modifica un sector y lo busca a traves de su id en el caso que no existe te dice que no ha podido modificarlo
    public void modificarEscalador(Escalador e)
            throws Exception {

        if (e == null) {

            throw new Exception(
                    "Error introduir dades "
            );
        }

        if (e.getId_escalador() <= 0) {

            throw new Exception(
                    "ID inválid "
            );
        }

        if (e.getNom() == null
                || e.getNom().isEmpty()) {

            throw new Exception(
                    "Falta el nom"
            );
        }

        if (e.getDni() == null
                || e.getDni().isEmpty()
                || !e.getDni().matches(
                "^[0-9]{8}[A-Z]$")) {

            throw new Exception(
                    "El DNI es INCORRECTE"
            );
        }

        if (e.getEdat() < 0) {

            throw new Exception(
                    "Error edad"
            );
        }

        if (e.getEstil() < 1
                || e.getEstil() > 3) {

            throw new Exception(
                    "Debe indicar una estilo válida"
            );
        }

        escaladorDao.modificar(e);
    }
/**
 * @param dni String
 * @return List<EscaladorNivellDTO> Una lista
 * **/
    public List<EscaladorNivellDTO> buscarPorNivell(String dni) throws Exception{
        if (!dni.matches("^[0-9]{8}[A-Z]$") || dni.length() > 9) {
            throw new Exception("DNI INCORRECTE O LONGITUD SENSE POSAR BE");
        }
        return escaladorDao.buscarPorNivel(dni);
    }


}



