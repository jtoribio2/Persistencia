package dao.interfaces;
import model.entity.Tipus_via;
import java.util.List;

public interface Tipus_viaDAO extends Dao<Tipus_via,Integer>{
    // METODOS GENERICOS
    @Override
    void inserir(Tipus_via o);

    @Override
    void modificar(Tipus_via o);

    @Override
    void eliminar(Integer o);

    @Override
    List<Tipus_via> obtindreTots();

    Tipus_via obtenir (Integer id);

    //METODOS  PROPIOS

}
