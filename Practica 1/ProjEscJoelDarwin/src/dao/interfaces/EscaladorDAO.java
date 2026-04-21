package dao.interfaces;
import model.entity.Escalador;
import java.util.List;

public interface EscaladorDAO extends Dao<Escalador,Integer> {
   //METODOS  GENERICOS
    @Override
    void inserir(Escalador o);

    @Override
    void modificar(Escalador o);

    @Override
    void eliminar(Integer id );

    @Override
    List<Escalador> obtindreTots();


    @Override
    Escalador obtenir (Integer id);

    //METODOS  PROPIOS
    void eliminarDni(String dni);
    Escalador obtenirPerDni (String dni);
}
