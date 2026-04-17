package BD_CONEXIONS;
import java.sql.*;
public class MYSQL {

    // Parámetros de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/escalada?serverTimezone=Europe/Madrid";
    private static final String USER = "root";
    private static final String PASS = "pepe@123";

    public  static Connection openCon() {


        try {
            Connection con = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Connexió establerta");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * Desconnectar d'una BD.
     * @param con Connexió amb la BD
     */
    private static void closeCon(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
