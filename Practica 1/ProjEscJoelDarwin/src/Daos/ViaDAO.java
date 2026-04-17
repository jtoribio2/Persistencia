package Daos;
import model.entity.Via;
import java.util.List;

public interface ViaDAO extends Dao<Via,Integer>{
    // METODOS GENERICOS
    @Override
    void inserir(Via o);

    @Override
    void modificar(Via o);

    @Override
    void eliminar(Via o);

    @Override
    List<Via> obtindreTots();
    Via obtenir (Integer id);
    //METODOS  PROPIOS

}
