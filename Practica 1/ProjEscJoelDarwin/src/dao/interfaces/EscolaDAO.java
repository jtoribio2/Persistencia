package dao.interfaces;


import model.entity.Escola;
import java.util.List;
public interface EscolaDAO extends Dao<Escola,Integer> {

    @Override
   public  void inserir(Escola o);

    @Override
    void modificar(Escola o);

    @Override
    void eliminar(Integer o);

    @Override
    List<Escola> obtindreTots();

    Escola  obtenir (Integer id);
    //METODOS  PROPIOS

}
