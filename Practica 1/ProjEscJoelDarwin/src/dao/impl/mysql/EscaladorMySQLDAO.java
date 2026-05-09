package dao.impl.mysql;
import dao.interfaces.*;
import db.ConnectionProvider;
import model.dto.EscaladorNivellDTO;
import model.entity.Escalador;
import model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscaladorMySQLDAO implements EscaladorDAO  {

    private final ConnectionProvider provider;

    public EscaladorMySQLDAO(ConnectionProvider provider) {
        this.provider = provider;
    }
    /**@param e Escalador **/
    @Override
    public void inserir(Escalador e) {

        String sql =
        """
        INSERT INTO escaladors
        (dni,nom, edat, estil)
        VALUES (?,?, ?, ?)
        """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNom());
            ps.setInt(3, e.getEdat());
            ps.setInt(4, e.getEstil());

            ps.executeUpdate();

        } catch (SQLException x) {
            throw new RuntimeException("Error insertando sector", x);
        }
    }


/**@param e Escalador **/
    @Override
    public void modificar(Escalador e ) {

        String sql = """
        UPDATE escaladors
        SET   dni=?, nom = ? , edat = ? , estil= ? 
        WHERE id_escalador = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNom());
            ps.setInt(3, e.getEdat());
            ps.setInt(4, e.getEstil());
            ps.setInt(5, e.getId_escalador());


            int filas = ps.executeUpdate(); // si devuelve 0 filas quiere decir que no lo ha encontrado


            if (filas == 0) {
                throw new RuntimeException("No se encontró el Escalador  con id " + e.getId_escalador());
            }

        } catch (SQLException x) {
            throw new RuntimeException("Error al modificar Esclador ", x);
        }
    }
/**@param id Integer**/
    @Override
    public void eliminar(Integer id) {

        String sql = "DELETE FROM escaladors  WHERE id_escalador= ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando sector", e);
        }
    }
/**@return List Escalador **/
    @Override
    public List<Escalador> obtindreTots() {

        List<Escalador> lista = new ArrayList<>();

        String sql = "SELECT * FROM escaladors";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sectores", e);
        }

        return lista;
    }
/**@param id Integer @return Escalador**/
    // implementacion de obtenir bector le pasas un id y te devuelve el objeto sector que le has pedido si no lo encuentra devuelve null
    @Override
    public Escalador obtenir(Integer id)  {

        String sql = "SELECT * FROM escaladors WHERE id_escalador = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sector por id", e);
        }

        return null;
    }
/**@param dni String **/
    @Override
    public void eliminarDni(String dni){

        String sql = "DELETE FROM escaladors  WHERE dni = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,dni);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando sector", e);
        }
    }
/**@param dni String @return Escalador objecte**/
    @Override
    public Escalador obtenirPerDni(String dni){
        String sql = "SELECT * FROM escaladors WHERE dni = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sector por id", e);
        }

        return null;
    }/**@param rs Resulset @return Escalador **/
    // metodo para cojer una fila de nuestra tabla y transformarlo en la clase sector
    private Escalador map(ResultSet rs) throws SQLException {

        Escalador e  = new Escalador();
        e.setId_escalador(rs.getInt("id_escalador"));
        e.setDni(rs.getString("dni"));
        e.setNom(rs.getString("nom"));
        e.setEdat(rs.getInt("edat"));
        e.setEstil(rs.getInt("estil"));

        return e;
    }
/**@param dni String @return List Escalador NIVEL DTO **/
    @Override
    public List<EscaladorNivellDTO> buscarPorNivel(String dni) {

        List<EscaladorNivellDTO> lista = new ArrayList<>();

        String sql = """
        SELECT 
            e.nom,
            e.edat,
            MAX(v.dificultat) AS nivell_maxim

        FROM escaladors e

        INNER JOIN escaladors_vies ev
            ON ev.id_escalador = e.id_escalador

        INNER JOIN vies v
            ON v.id_via = ev.id_via
        
        WHERE e.dni != ?

        GROUP BY e.id_escalador

        HAVING MAX(v.dificultat) >= (

            SELECT MAX(v2.dificultat)

            FROM escaladors esc

            INNER JOIN escaladors_vies ev2
                ON ev2.id_escalador = esc.id_escalador

            INNER JOIN vies v2
                ON v2.id_via = ev2.id_via

            WHERE esc.dni = ? 
        )
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.setString(2, dni);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    EscaladorNivellDTO dto = new EscaladorNivellDTO(
                            rs.getString("nom"),
                            rs.getInt("edat"),
                            rs.getString("nivell_maxim")
                    );

                    lista.add(dto);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando escaladores por nivel", e);
        }

        return lista;
    }
@Override
public void EliminarEscalador_vies(int Escalador){

    String sql = "DELETE FROM escaladors_vies  WHERE id_escalador= ?";

    try (Connection conn = provider.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, Escalador);
        ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error eliminando Escalador", e);
    }
    }

}
