package Daos;


import model.entity.Escalador;
import model.entity.Escola;
import java.util.List;
public interface EscolaDAO extends Dao<Escola,Integer> {

    @Override
    void inserir(Escola o);

    @Override
    void modificar(Escola o);

    @Override
    void eliminar(Escola o);

    @Override
    List<Escola> obtindreTots();

    Escola  obtenir (Integer id);
    //METODOS  PROPIOS

}
