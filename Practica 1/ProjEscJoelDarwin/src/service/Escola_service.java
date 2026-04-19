package service;

import dao.interfaces.EscolaDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escola;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// AQUI IMPLEMENTAREMOS  LOS METODOS DE LA INTERFICIE DESDE EL CONTROLADOR LLAMAREMOS ESOS METODOS
// TODOS LOS METODOS ABRIRAN LA CONEXION
public class Escola_service implements EscolaDAO {
    /**
     * @param o Objeto que añadiremos a la bd
     * **/
    @Override
    public void inserir(Escola o) {
        final String SQL = "INSERT INTO escoles VALUES (?,?,?,?,?)"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        ConnectionProvider provider = ConnectionFactory.getProvider("mysql");
        try(Connection conn = provider.getConnection()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL); //  LE MANDA A LA BD COM EL FORMATO DEL SQL NO HACE NADA DE MOMENTO
            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setInt(1, o.getId_escola()); // REPRESENTA CADA "?" aqui indicamos el primer "?"
            ps.setString(2,o.getNom());
            ps.setString(3,o.getLloc());
            ps.setString(4,o.getAproximacio());
            ps.setInt(5,o.getPopularitat());
            ps.executeUpdate(); // AQUI NO PONEMOS NADA ESTO ES UNA CONFIRMACION DE QUE SE AHGA EL CAMBIO

            System.out.println("SE AÑADIO A LA BD  ");


        }
        catch (SQLException e ){
            System.out.println("Error  no se puedo añadir el registro ");
            e.printStackTrace();
        }
    }

    /**
     * @param o Objeto quemodificaremos a la bd
     * **/
    @Override
    public void modificar(Escola o) {
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "UPDATE escoles  SET nom=?,lloc=?,aproximacio=?,popularitat=? WHERE id_escola = ?"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = getCon()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL);
            //DEBEMO DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setString(1,o.getNom());
            ps.setString(2,o.getLloc());
            ps.setString(3,o.getAproximacio());
            ps.setInt(4,o.getPopularitat());
            ps.setInt(5, o.getId_escola());
            ps.executeUpdate();
        }

            catch (SQLException e){
                System.out.println("Error  no se pudo modificar el registro ");
                e.printStackTrace();
            }

            System.out.println("SE MODIFICO  A LA BD  ");
    }
    /**
     * @param o Objeto que elimnaremos  a la bd
     * **/
    @Override
    public void eliminar(Integer o) {
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "DELETE FROM  escoles  WHERE id_escola = ?"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = getCon()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL);
            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setInt(1, o);
            ps.executeUpdate();

            System.out.println("FILA ELIMINADA  A LA BD  ");
        }

        catch (SQLException e){
            System.out.println("Error no se pudo modificar el registro ");
            e.printStackTrace();
        }


    }
    /**
     * @return devuelve un List
     * **/
    @Override
    public List<Escola> obtindreTots() {
        List<Escola> llista = new ArrayList<>();
        String sql = "SELECT * FROM escoles ";
       // ConnectionProvider provider = ConnectionFactory.getProvider("mysql");
        try (Connection conn = getCon()) {
            // Resulset  obejto que se encarga leer las filas del resultado de la comanda/consulta de mysql
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            while (rs.next()) { // Recorremos las filas que  la bd  nos ha dado
                int id =  rs.getInt("id_escola");
                String nom =  rs.getString("nom");
                String lloc =  rs.getString("lloc");
                String aproximacio =   rs.getString("aproximacio");
                int popularitat =   rs.getInt("popularitat");

                // Creamos el objeto con los datos de la fila actual
                Escola e = new Escola(id,nom,lloc,aproximacio,popularitat);


                // Lo metemos en nuestra lista
                llista.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error no se pudo modificar el registro ");
            e.printStackTrace();
        }

        return llista;
    }
/**
 * @param id Intger introducimos un numero
 * **/
    @Override
    public Escola obtenir(Integer id) {

                //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
                final String SQL = "SELECT * FROM escoles  WHERE id_escola = ?  "; // EL ? representa un parametro
                //CONECTAMOS A LA BD
                try(Connection conn = getCon()){
                /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
                PreparedStatement ps = conn.prepareStatement(SQL);
                ps.setInt(1,id);

                try (ResultSet rs = ps.executeQuery()) {
                  //MIRAMOS SI HYA UNA FILA
                    if (rs.next()) {
                        int ids =  rs.getInt("id_escola");
                        String nom =  rs.getString("nom");
                        String lloc =  rs.getString("lloc");
                        String aproximacio =   rs.getString("aproximacio");
                        int popularitat =   rs.getInt("popularitat");

                        // Creamos el objeto con los datos de la fila actual
                        Escola e = new Escola(ids,nom,lloc,aproximacio,popularitat);
                        return  e;
                    }
                }

                System.out.println("FILA ELIMINADA  A LA BD  ");
            }

            catch (SQLException e){
                System.out.println("Error no se pudo modificar el registro ");
                e.printStackTrace();
            }
            return null;


}
    private Connection getCon() throws SQLException {
        return ConnectionFactory.getProvider("mysql").getConnection();
    }

}

