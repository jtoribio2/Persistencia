package services;

import daos.SectorDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Sector;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sector_service implements SectorDAO {

    /**
     * @param o Objeto que añadiremos a la bd
     **/
    @Override
    public void inserir(Sector o) {
        final String SQL = "INSERT INTO sectors VALUES (?,?,?,?,?,?,?)"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        ConnectionProvider provider = ConnectionFactory.getProvider("mysql");
        try(Connection conn = provider.getConnection()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL); //  LE MANDA A LA BD COM EL FORMATO DEL SQL NO HACE NADA DE MOMENTO
            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setInt(1, o.getId_sector()); // REPRESENTA CADA "?" aqui indicamos el primer "?"
            ps.setInt(2,o.getId_escoles());
            ps.setString (3,o.getNom());
            ps.setFloat (4,o.getLatitut());
            ps.setFloat (5,o.getLongitut());
            ps.setString (6,o.getAproximacio());
            ps.setInt (7,o.getPopularitat());

            ps.executeUpdate(); // AQUI NO PONEMOS NADA ESTO ES UNA CONFIRMACION DE QUE SE AHGA EL CAMBIO

            System.out.println("SE AÑADIO A LA BD  ");


        }
        catch (SQLException e ){
            System.out.println("Error  no se puedo añadir el registro ");
            e.printStackTrace();
        }
    }

    /**
     * @param o Llar  Objeto que  modificaremos a la bd
     * **/
    @Override
    public void modificar(Sector  o) {
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "UPDATE sectors  SET id_escola=?,nom=?,latitut=?,longitut=?,aproximacio=?,popularitat=? WHERE id_sector = ?"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = getCon()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL);
            //DEBEMO DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?

            // REPRESENTA CADA "?" aqui indicamos el primer "?"
            ps.setInt(1,o.getId_escoles());
            ps.setString (2,o.getNom());
            ps.setFloat (3,o.getLatitut());
            ps.setFloat (4,o.getLongitut());
            ps.setString (5,o.getAproximacio());
            ps.setInt (6,o.getPopularitat());
            ps.setInt(7, o.getId_sector());

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
        final String SQL = "DELETE FROM  sectors  WHERE id_sector = ?"; // EL ? representa un parametro
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
    public List<Sector> obtindreTots() {
        List<Sector> llista = new ArrayList<>();
        String sql = "SELECT * FROM sectors";
        // ConnectionProvider provider = ConnectionFactory.getProvider("mysql");
        try (Connection conn = getCon()) {
            // Resulset  obejto que se encarga leer las filas del resultado de la comanda/consulta de mysql
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            while (rs.next()) { // Recorremos las filas que  la bd  nos ha dado

                int id_sector = rs.getInt("id_sector");
                int id_escola =  rs.getInt("id_escola");
                String  nom = rs.getString("nom");
                float latitut = rs.getFloat("lantitut");
                float longitut = rs.getFloat("longitut");
                String aprox = rs.getString("aproximacio");
                int popularitat = rs.getInt("popularitat");

                // Creamos el objeto con los datos de la fila actual
                Sector e = new Sector(id_sector,id_escola,nom,latitut,longitut,aprox,popularitat);


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
    public Sector   obtenir(Integer id) {

        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "SELECT * FROM llars  WHERE id_escola = ?  "; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = getCon()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1,id);

            try (ResultSet rs = ps.executeQuery()) {
                //MIRAMOS SI HAY UNA FILA
                if (rs.next()) {
                    int id_sector = rs.getInt("id_sector");
                    int id_escola =  rs.getInt("id_escola");
                    String  nom = rs.getString("nom");
                    float latitut = rs.getFloat("lantitut");
                    float longitut = rs.getFloat("longitut");
                    String aprox = rs.getString("aproximacio");
                    int popularitat = rs.getInt("popularitat");

                    // Creamos el objeto con los datos de la fila actual
                    Sector e = new Sector(id_sector,id_escola,nom,latitut,longitut,aprox,popularitat);
                    return e ;
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
