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
    public void crearEscola(Escola e )  throws  Exception{

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


        if (e.getPopularitat() > 0 && e.getPopularitat() <= 3) {
            throw new Exception("Debe indicar una escola válida");
        }
        escoladao.inserir(e);
    }

    // devuelve una lista donde aparecen todos los sectores de la bd
    public List<Escola> obtenerTodos() {
        return escoladao.obtindreTots();
    }

    // devuelve un sector a traves de su id
    public Escola  obtenerPorId (int id) throws  Exception{

        if (id <= 0) {
            throw new Exception("ID inválido");
        }

        return escoladao.obtenir(id);
    }

    // elimina un sector a traves de un id
    public void eliminarSector(int id) throws  Exception{

        if (id <= 0) {
            throw new Exception("ID inválido");
        }

        escoladao.eliminar(id);
    }

    // modifica un sector y lo busca a traves de su id en el caso que no existe te dice que no ha podido modificarlo
    public void modificarSector(Escola e) throws Exception{

        if (e == null) {
            throw new Exception("Sector no puede ser null");
        }

        if (e.getId_escola() <= 0) {
            throw new Exception("ID inválido");
        }

        escoladao.modificar(e);
    }


    public boolean isGel(Escola o ) throws  Exception{

        if (o == null) throw new Exception("Escola no puede ser null");

        return   escoladao.isGel(o);
    }
}

