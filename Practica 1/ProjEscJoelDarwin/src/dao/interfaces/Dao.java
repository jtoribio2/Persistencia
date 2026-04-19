package dao.interfaces;

import java.util.List;
// DAO GENERICO DONDE AQUI PONEMOS TODOS LOS METODOS QUE TIENE EN COMUN  ENTRE LOS DIFERENTES DAOS DE LAS CLASSES
public interface Dao <C,K> { // DAO<...>  es como un parametro de un funcion nos sirve para reutilizar los metodos para diferentes classes(C) y PKs(K) los tipos de datos
// NO HACE FATLA PONER PUBLIC ENTEORIA
    void inserir (C o );
    void modificar (C o);
    void eliminar (K o);
    List<C> obtindreTots();
    C obtenir (K id);
}
