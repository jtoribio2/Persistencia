package service;

import dao.interfaces.EscolaDAO;
import model.entity.Escola;

import java.util.List;

// AQUI IMPLEMENTAREMOS  LOS METODOS DE LA INTERFICIE DESDE EL CONTROLADOR LLAMAREMOS ESOS METODOS
// TODOS LOS METODOS ABRIRAN LA CONEXION
public class EscolaService {



    private final EscolaDAO escoladao;

    public EscolaService(EscolaDAO edao) {
        this.escoladao = edao;
    }

    // inserta un sector a la base de datos
    public void crearEscola(Escola e ) {

        if (e == null) {
            throw new RuntimeException("El sector no puede ser null");
        }

        if (e.getNom() == null || e.getNom().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (e.getLloc() == null || e.getLloc().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (e.getAproximacio() == null || e.getAproximacio().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }


        if (e.getPopularitat() > 0 && e.getPopularitat() <= 3) {
            throw new RuntimeException("Debe indicar una escola válida");
        }
        escoladao.inserir(e);
    }

    // devuelve una lista donde aparecen todos los sectores de la bd
    public List<Escola> obtenerTodos() {
        return escoladao.obtindreTots();
    }

    // devuelve un sector a traves de su id
    public Escola  obtenerPorId(int id) {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        return escoladao.obtenir(id);
    }

    // elimina un sector a traves de un id
    public void eliminarSector(int id) {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        escoladao.eliminar(id);
    }

    // modifica un sector y lo busca a traves de su id en el caso que no existe te dice que no ha podido modificarlo
    public void modificarSector(Escola e) {

        if (e == null) {
            throw new RuntimeException("Sector no puede ser null");
        }

        if (e.getId_escola() <= 0) {
            throw new RuntimeException("ID inválido");
        }

        escoladao.modificar(e);
    }


    public boolean isGel(Escola o ){
      //YA HARE LAS EXEPCIONES
        return   escoladao.isGel(o);
    }
}

