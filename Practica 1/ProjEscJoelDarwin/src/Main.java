import controlador.ControladorEscalador;
import controlador.ControladorEscola;
import controlador.ControladorLlar;
import controlador.ControladorSector;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escalador;
import model.entity.Escola;
import model.entity.Llar;
import model.entity.Sector;
import services.Escola_service;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //OBJETOS DAOS  SOLO ES DE PRUEBAS QUITARLO AL IMPLEMNTAR TODAS LOS CONTROLADORES
        //Escola_service edao = new Escola_service(); // ACCEDER DIRECTAMENTE LOS METODOS DAO

        //Registro
        Escola  e1 = new Escola(100,"San pere pi","por ahi","mas o menos",1);
        Escalador e2 = new Escalador(100,"Pere pi",1,1);
        Llar l = new Llar(1000,1,20);
        Sector s = new Sector(201,1,"Un sector",10.05f,12.05f,"Algo",1);
        //FUNCIONA PERO SE APLICAN EN EL MISMO  REGISTRO (e1 ) Descomentalo y miralo

        //AÑADIR
        try {
          //  ControladorEscola.addEscola(e1); // SOLO AÑADI ESTE metodo en el controlador
            //ControladorEscalador.addEscalador(e2);
           // ControladorLlar.addLlar(l);
            ControladorSector.addSector(s);
        }
        catch (Exception e){
           System.out.println("ERROR");
            e.printStackTrace();
        }
        //MODIFICAR ESCOLA SAN PERE PI
        // BUSCARA EL ID EXISTENTE
      //  edao.modificar(new Escola(100,"Bomba","sitio","mas",2));

        //Eliminar
        // edao.eliminar(100); // SOLO PONEMOS LA ID

        // Obtener array
       /*List<Escola> es = edao.obtindreTots();
        for(Escola e : es){
            System.out.println(e.getNom());
        }*/

        //OBTENER FILA CONCRETA

      //  Escola e2 = edao.obtenir(1);
      //  System.out.println(e2.getNom());


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