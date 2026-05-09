package dao.impl.mysql;


import dao.interfaces.EscolaDAO;

import db.ConnectionProvider;
import model.dto.EscolaDisponibleDTO;
import model.dto.EscolesRestricDTO;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// AQUI IMPLEMENTAREMOS  LOS METODOS DE LA INTERFICIE DESDE EL CONTROLADOR LLAMAREMOS ESOS METODOS
// TODOS LOS METODOS ABRIRAN LA CONEXION
public class EscolaMySQLDAO implements EscolaDAO {
    private final ConnectionProvider provider;
    public EscolaMySQLDAO(ConnectionProvider provider) {
        this.provider = provider;
    }
    /**
     * @param o Objeto que añadiremos a la bd
     * **/
    @Override
    public void inserir(Escola o) {
        final String SQL = "INSERT INTO escoles (nom,lloc,aproximacio,popularitat) VALUES (?,?,?,?)"; // EL ? representa un parametro
        //CONECTAMOS A LA BD

        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)
        ){  //  LE MANDA A LA BD COM EL FORMATO DEL SQL NO HACE NADA DE MOMENTO
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            // REPRESENTA CADA "?" aqui indicamos el primer "?"

            ps.setString(1,o.getNom());
            ps.setString(2,o.getLloc());
            ps.setString(3,o.getAproximacio());
            ps.setInt(4,o.getPopularitat());
            //CONFIRMAR UPDATE
            ps.executeUpdate();

            System.out.println("SE AÑADIO A LA BD ");


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
        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
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
        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

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
        String SQL = "SELECT * FROM escoles ";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {
            // Resulset  obejto que se encarga leer las filas del resultado de la comanda/consulta de mysql


            while (rs.next()) {
                llista.add(map(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error no se pudo modificar el registro ");
            e.printStackTrace();
        }

        return llista;
    }
    /**
     * @param id Intger introduir un numero
     * **/
    @Override
    public Escola obtenir(Integer id) {

        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "SELECT * FROM escoles  WHERE id_escola = ?  "; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            ps.setInt(1,id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
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
/**@param o Escola  @return boolean  **/
    @Override
    public boolean isGel(Escola o ){
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = """
                SELECT *
                FROM escoles e
                INNER JOIN sectors s ON s.id_escola=e.id_escola
                INNER JOIN vies v ON v.id_sector=s.id_sector
                WHERE v.id_tipus_via = 3 AND e.id_escola = ?
                
                """;
        //CONECTAMOS A LA BD
        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setInt(1, o.getId_escola());
            try (ResultSet rs = ps.executeQuery()) {
              boolean tf = rs.next();
                //DEVOLVERA TRUE O FALSE
                return tf;
            }


        }

        catch (SQLException e){
            System.out.println("Error no se pudo modificar el registro ");
            e.printStackTrace();
        }

        return false;
    }/**@param rs Resulset @return Escola **/
    private Escola map(ResultSet rs) throws SQLException {

        Escola s = new Escola();

        s.setId_escola(rs.getInt("id_escola"));
        s.setNom(rs.getString("nom"));
        s.setLloc(rs.getString("lloc"));
        s.setAproximacio(rs.getString("aproximacio"));
        s.setPopularitat(rs.getInt("popularitat"));

        return s;
    }
    /**@return List Escoles Restrict DTO**/
    @Override
    public List<EscolesRestricDTO> escolesDisponibles() {

        String sql = """
        SELECT DISTINCT e.nom AS nom, v.nom AS via, d.rao
        FROM escoles e
        INNER JOIN sectors s ON s.id_escola = e.id_escola
        INNER JOIN vies v ON v.id_sector = s.id_sector
        INNER JOIN disponibilitats d ON d.id_via = v.id_via
        WHERE CURDATE() BETWEEN d.inici AND d.final
    """;

        List<EscolesRestricDTO> escoles = new ArrayList<>();

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                EscolesRestricDTO e = new EscolesRestricDTO(
                        rs.getString("nom"),
                        rs.getString("via"),
                        rs.getString("rao")
                );

                escoles.add(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo escoles disponibles", e);
        }

        return escoles;
    }/**@param es Escola @return List EscolDisponibleDTO**/
    @Override
    public List<EscolaDisponibleDTO> viesDisponibles(Escola es ) {
        List<EscolaDisponibleDTO> vies = new ArrayList<>();
        final String SQL =
                """
                SELECT  v.*
                FROM vies v
                INNER JOIN  sectors s ON s.id_sector = v.id_sector
                WHERE s.id_escola  = ? AND (
                id_via NOT IN (SELECT d.id_via
                FROM disponibilitats d
                ) OR id_via IN (
                SELECT d1.id_via
                FROM disponibilitats d1
                WHERE NOW() NOT BETWEEN d1.inici AND d1.final)
                );  
                """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
        ) {
            ps.setInt(1, es.getId_escola());

            //ALMACENAR LOS VALORES EN LAS VARIABLES
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                EscolaDisponibleDTO dto = new EscolaDisponibleDTO(es.getNom(),rs.getString("v.nom"));
                    vies.add(dto);
                }
                return vies;
            }


        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo vias", e);
        }
    }
/**@param es Escola @return retorna el id  **/
    @Override
    public int inserirRetornantId(Escola es ){
        String sql = """
    INSERT INTO escoles  
    ( nom, lloc,  aproximacio, popularitat)
    VALUES (?, ?, ?, ?)
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            ps.setString(1, es.getNom());
            ps.setString(2, es.getLloc());
            ps.setString(3, es.getAproximacio());
            ps.setInt(4, es.getPopularitat());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error insertando sector", e);
        }

        throw new RuntimeException("No se pudo obtener el ID");
    }

    @Override
    public List<Escola> buscarPorNombre(String nombre) {

        List<Escola> lista = new ArrayList<>();

        String sql = "SELECT * FROM escoles WHERE nom LIKE ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    lista.add(map(rs));
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error buscando escoles por nombre",
                    e
            );
        }

        return lista;
    }

}



