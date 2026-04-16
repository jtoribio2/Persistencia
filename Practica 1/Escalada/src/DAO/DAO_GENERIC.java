package DAO;
import java.util.*;
public interface DAO_GENERIC<T,K>{
// T : Indiquem la classe K: Representa la clau primaria
    void inserir (T o );
    void modificar (T o);
    void eliminar (T o);
    List<T> obtindreTots();
    T obtenir (K id);

}
