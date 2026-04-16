import db.DBConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {

            System.out.println("Conexión correcta");

        } catch (Exception e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
    }
}