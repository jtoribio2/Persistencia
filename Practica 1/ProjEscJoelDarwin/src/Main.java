import db.ConnectionFactory;
import db.ConnectionProvider;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

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