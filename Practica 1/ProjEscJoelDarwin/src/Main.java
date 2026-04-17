import daos.EscolaDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escola;
import model.entity.controlador.ControladorEscola;
import resources.properties.services.MySqlEscolaDao;

import java.sql.Connection;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //OBJETOS DAOS  SOLO ES DE PRUEBAS
        MySqlEscolaDao edao = new MySqlEscolaDao();

        //Registro
        Escola  e1 = new Escola(100,"San pere pi","por ahi","mas o menos",1);
        //FUNCIONA PERO SE APLICAN EN EL MISMO  REGISTRO (e1 ) Descomentalo y miralo

        //AÑADIR
      //  ControladorEscola.Inserir(e1); // SOLO AÑADI ESTE metodo en el controlador

        //MODIFICAR ESCOLA SAN PERE PI
      //  edao.modificar(new Escola(100,"Bomba","sitio","mas",2));

        //Eliminar
        // edao.eliminar(100); // SOLO PONEMOS LA ID

        // Obtener array
       /* List<Escola> es = edao.obtindreTots();
        for(Escola e : es){
            System.out.println(e.getNom());
        }*/

        //OBTENER FILA CONCRETA

        Escola e2 = edao.obtenir(1);
        System.out.println(e2.getNom());


            // Ahora creamos aqui un provedor para poder decir al java quiero la base de datos Mysql
            ConnectionProvider provider = ConnectionFactory.getProvider("mysql"); // aqui usamos el metodo del factory para decilre es credenciales de mysql

            try (Connection conn = provider.getConnection()) { // y esto es totalmente igual lo que ahora necesitamos siempre decirle eh es esta puerta la que quiero abrir

                if (conn != null) {
                    System.out.println("Conexión correcta");
                }

            }
            catch (Exception e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
            }

    }
}