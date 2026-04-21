package dao.interfaces;
import model.entity.Escalador;
import java.util.List;

public interface EscaladorDAO extends Dao<Escalador,String> {
   //METODOS  GENERICOS
    @Override
    void inserir(Escalador o);

    @Override
    void modificar(Escalador o);

    @Override
    void eliminar(String dni);

    @Override
    List<Escalador> obtindreTots();

    Escalador obtenir (String dni);
    //METODOS  PROPIOS

}
