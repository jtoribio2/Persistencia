package dao.impl.mysql;

import dao.interfaces.SectorDAO;
import db.ConnectionProvider;
import model.dto.SectorViaDispDTO;
import model.entity.Escola;
import model.entity.Sector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorMySQLDAO implements SectorDAO {

    private final ConnectionProvider provider;

    public SectorMySQLDAO(ConnectionProvider provider) {
        this.provider = provider;
    }
    /**@param s Sector **/
    // inserta un Objeto sector a la base de datos
    @Override
    public void inserir(Sector s) {

        String sql = """
    INSERT INTO sectors 
    (id_escola, nom, latitut, longitut, aproximacio, popularitat)
    VALUES (?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, s.getId_escoles());
            ps.setString(2, s.getNom());
            ps.setFloat(3, s.getLatitut());
            ps.setFloat(4, s.getLongitut());
            ps.setString(5, s.getAproximacio());
            ps.setInt(6, s.getPopularitat());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error insertando sector", e);
        }
    }
    /**@param s Sector **/
    // le pasamos un sector con los campos modificado lo encuentra atraves de su id y lo devuelve a la base de datos cambiado, si no lo encuientra informa de que no se ha modificado
    @Override
    public void modificar(Sector s) {

        String sql = """
        UPDATE sectors 
        SET id_escola = ?, 
            nom = ?, 
            latitut = ?, 
            longitut = ?, 
            aproximacio = ?, 
            popularitat = ?
        WHERE id_sector = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, s.getId_escoles());
            ps.setString(2, s.getNom());
            ps.setFloat(3, s.getLatitut());
            ps.setFloat(4, s.getLongitut());
            ps.setString(5, s.getAproximacio());
            ps.setInt(6, s.getPopularitat());
            ps.setInt(7, s.getId_sector());

            int filas = ps.executeUpdate(); // si devuelve 0 filas quiere decir que no lo ha encontrado


            if (filas == 0) {
                throw new RuntimeException("No se encontró el sector con id " + s.getId_sector());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar sector", e);
        }
    }
/**@param id Integer**/
    // Elimina de la bd un vetor a traves de su id
    @Override
    public void eliminar(Integer id) {

        String sql = "DELETE FROM sectors WHERE id_sector = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando sector", e);
        }
    }

    /** decuelve una lista con todos los objetos que hay en la tabla sector**/
    @Override
    public List<Sector> obtindreTots() {

        List<Sector> lista = new ArrayList<>();

        String sql = "SELECT * FROM sectors";

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
    /**@param id Integer**/
    // implementacion de obtenir bector le pasas un id y te devuelve el objeto sector que le has pedido si no lo encuentra devuelve null
    @Override
    public Sector obtenir(Integer id) {

        String sql = "SELECT * FROM sectors WHERE id_sector = ?";

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
    /**@param nombre String **/
    @Override
    public List<Sector> buscarPorNombre(String nombre) {

        List<Sector> lista = new ArrayList<>();

        String sql = "SELECT * FROM sectors WHERE nom LIKE ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando por nombre", e);
        }

        return lista;
    }
    /**@param rs Resulset  @return Sector **/
    // metodo para cojer una fila de nuestra tabla y transformarlo en la clase sector
    private Sector map(ResultSet rs) throws SQLException {

        Sector s = new Sector();

        s.setId_sector(rs.getInt("id_sector"));
        s.setId_escoles(rs.getInt("id_escola"));
        s.setNom(rs.getString("nom"));

        return s;
    }
    /**@param idSector int **/
    @Override
    public Escola buscarEscola(int idSector) {

        String sql = """
        SELECT e.*
        FROM sectors s
        JOIN escoles e ON s.id_escola = e.id_escola
        WHERE s.id_sector = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSector);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    return new Escola(
                            rs.getInt("id_escola"),
                            rs.getString("nom"),
                            rs.getString("lloc"),
                            rs.getString("aproximacio"),
                            rs.getInt("popularitat")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo escola del sector", e);
        }

        return null;
    }
/**@param idEscola INT **/
    @Override
    public List<Sector> buscarPorEscola(int idEscola) {

        List<Sector> lista = new ArrayList<>();

        String sql = "SELECT * FROM sectors WHERE id_escola = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEscola);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Sector s = new Sector(
                            rs.getInt("id_sector"),
                            rs.getInt("id_escola"),
                            rs.getString("nom"),
                            rs.getFloat("latitut"),
                            rs.getFloat("longitut"),
                            rs.getString("aproximacio"),
                            rs.getInt("popularitat")
                    );

                    lista.add(s);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sectores por escola", e);
        }

        return lista;
    }
/**@param quantitatVies int **/
    //BUSCAR SECTORES QUE TENGAN MAS DE X VIAS DISPONIBLES
//REUTILIZE UNA CONSULTA QUE BUSCA LAS VIAS DISPONIBLES TENIENDO EN CUENTAS LAS RESTRICCIONES YA PASSADAS/CADUCADAS
    @Override
    public List<SectorViaDispDTO>  sectorViesDisponibles (int quantitatVies){
        List<SectorViaDispDTO> aptes = new ArrayList<>();
        String sql = """
           SELECT  s.nom AS nom , COUNT(*)  AS count
           FROM sectors s
           INNER JOIN (
           SELECT *
           FROM vies
           WHERE id_via NOT IN (
               SELECT d.id_via
               FROM disponibilitats d
           )
           OR id_via IN (
               SELECT d1.id_via
               FROM disponibilitats d1
               WHERE NOW() > d1.final
           )
           ) vies_disponibles ON   vies_disponibles.id_sector = s.id_sector
           GROUP BY    vies_disponibles.id_sector
           HAVING count >= ?;
           """;


        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, quantitatVies);


            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {


                    SectorViaDispDTO sector =   new SectorViaDispDTO(
                            rs.getString("nom"),
                            rs.getInt("count")
                    );


                    aptes.add(sector);
                }
                return  aptes;
            }


        } catch (SQLException e) {


            throw new RuntimeException("Error obteniendo escola del sector", e);
        }
    }
/**@param s Sector **/
    @Override
    public int inserirRetornantId(Sector s) {

        String sql = """
    INSERT INTO sectors 
    (id_escola, nom, latitut, longitut, aproximacio, popularitat)
    VALUES (?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            ps.setInt(1, s.getId_escoles());
            ps.setString(2, s.getNom());
            ps.setFloat(3, s.getLatitut());
            ps.setFloat(4, s.getLongitut());
            ps.setString(5, s.getAproximacio());
            ps.setInt(6, s.getPopularitat());

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

}