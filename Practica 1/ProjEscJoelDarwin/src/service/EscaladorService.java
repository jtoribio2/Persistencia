package service;



import dao.interfaces.EscaladorDAO;
import model.entity.Escalador;

import java.util.List;

// AQUI IMPLEMENTAREMOS  LOS METODOS DE LA INTERFICIE DESDE EL CONTROLADOR LLAMAREMOS ESOS METODOS
// TODOS LOS METODOS ABRIRAN LA CONEXION

public class EscaladorService  {
    private final EscaladorDAO escaladorDao;


    public EscaladorService(EscaladorDAO edao) {
        this.escaladorDao = edao;
    }
    // inserta un sector a la base de datos
    public void crearEscalador(Escalador e ) {

        if (e == null) {
            throw new RuntimeException("El sector no puede ser null");
        }

        if (e.getNom() == null || e.getNom().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (e.getDni() == null || e.getDni().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }


        if (e.getEstil() > 0 && e.getEstil() <= 3) {
            throw new RuntimeException("Debe indicar una escola válida");
        }
        escaladorDao.inserir(e);
    }

    // devuelve una lista donde aparecen todos los sectores de la bd
    public List<Escalador> obtenerTodos() {
        return escaladorDao.obtindreTots();
    }

    // devuelve un sector a traves de su id
    public Escalador  obtenerPorId(Integer id ) {

        if (id < 0) {
            throw new RuntimeException("ID inválido");
        }

        return escaladorDao.obtenir(id);
    }

    public Escalador obtenerPorDni(String dni ) {

        if (!dni.matches("^[0-9]{8}[A-Z]$") ) {
            throw new RuntimeException("DNI INCORRECTE");
        }
        return escaladorDao.obtenirPerDni(dni);
    }


    public void eliminarPerDni(String dni){
        if (!dni.matches("^[0-9]{8}][A-Z]$") ) {
            throw new RuntimeException("DNI INCORRECTE");
        }

        escaladorDao.eliminarDni(dni);
    }
    // elimina un sector a traves de un id
    public void eliminarEscalador(Integer id) {

        if (id < 0) {
            throw new RuntimeException("ID inválido");
        }

        escaladorDao.eliminar(id);
    }

    // modifica un sector y lo busca a traves de su id en el caso que no existe te dice que no ha podido modificarlo
    public void modificarEscalador(Escalador  e) {

        if (e == null) {
            throw new RuntimeException("Escalador  no puede ser null");
        }

        if (e.getId_escalador() <= 0) {
            throw new RuntimeException("ID inválido");
        }

        escaladorDao.modificar(e);
    }


}



