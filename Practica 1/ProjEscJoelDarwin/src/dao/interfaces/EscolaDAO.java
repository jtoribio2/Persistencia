package dao.interfaces;


import model.dto.EscolaDisponibleDTO;
import model.dto.EscolesRestricDTO;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;

import java.util.List;
public interface EscolaDAO extends Dao<Escola,Integer> {

    @Override
    public void inserir(Escola o);

    @Override
    void modificar(Escola o);

    @Override
    void eliminar(Integer o);

    @Override
    List<Escola> obtindreTots();

    Escola obtenir(Integer id);
    //METODOS  PROPIOS

    boolean isGel(Escola o);

    List<EscolesRestricDTO> escolesDisponibles();

    List<EscolaDisponibleDTO> viesDisponibles(Escola es);

    int inserirRetornantId(Escola id);

    List<Escola> buscarPorNombre(String nombre);
}
