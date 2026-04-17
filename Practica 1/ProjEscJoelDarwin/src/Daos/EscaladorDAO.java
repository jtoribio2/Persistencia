package Daos;
import model.entity.Escalador;
import java.util.List;

public interface EscaladorDAO extends Dao<Escalador,Integer> {
   //METODOS  GENERICOS
    @Override
    void inserir(Escalador o);

    @Override
    void modificar(Escalador o);

    @Override
    void eliminar(Escalador o);

    @Override
    List<Escalador> obtindreTots();

    Escalador obtenir (Integer id);
    //METODOS  PROPIOS

}
