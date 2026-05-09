package dao.impl.mysql;

import dao.interfaces.ViaDAO;
import db.ConnectionProvider;
import model.dto.ViaPerDifDTO;
import model.dto.ViesAptRecentDTO;
import model.entity.*;
import model.dto.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViaMySQLDAO implements ViaDAO {

    private final ConnectionProvider provider;

    public ViaMySQLDAO(ConnectionProvider provider) {
        this.provider = provider;
    }
/**@param v Via **/
    // INSERT
    @Override
    public void inserir(Via v) {

        String sql = """
            INSERT INTO vies 
            (id_sector, id_tipus_via, nom, llargada, dificultat, orientacio, ancoratge, troca)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getId_sector());
            ps.setInt(2, v.getId_tipus_via());
            ps.setString(3, v.getNom());
            ps.setInt(4, v.getLlargada());
            ps.setString(5, v.getDificultat());
            ps.setString(6, v.getOrientacio());
            ps.setString(7, v.getAncoratge());
            ps.setString(8, v.getTroca());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error insertando via", e);
        }
    }
/**@param v Via **/
    // UPDATE
    @Override
    public void modificar(Via v) {

        String sql = """
            UPDATE vies SET
            id_sector=?, id_tipus_via=?, nom=?, llargada=?,
            dificultat=?, orientacio=?, ancoratge=?, troca=?
            WHERE id_via=?
        """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getId_sector());
            ps.setInt(2, v.getId_tipus_via());
            ps.setString(3, v.getNom());
            ps.setInt(4, v.getLlargada());
            ps.setString(5, v.getDificultat());
            ps.setString(6, v.getOrientacio());
            ps.setString(7, v.getAncoratge());
            ps.setString(8, v.getTroca());
            ps.setInt(9, v.getId_via());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró la vía");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error modificando via", e);
        }
    }
/**@param id Integer **/
    //  DELETE
    @Override
    public void eliminar(Integer id) {

        String sql = "DELETE FROM vies WHERE id_via=?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando via", e);
        }
    }
/**@return list via **/
    //  FIND ALL
    @Override
    public List<Via> obtindreTots() {

        List<Via> lista = new ArrayList<>();

        String sql = "SELECT * FROM vies";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo vias", e);
        }

        return lista;
    }
/**@param id Via **/
    //  buscar por id
    @Override
    public Via obtenir(Integer id) {

        String sql = "SELECT * FROM vies WHERE id_via=?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo via", e);
        }

        return null;
    }
/**@param nombre String @return List via **/
    // Buscar por nombre
    @Override
    public List<Via> buscarPorNombre(String nombre) {

        List<Via> lista = new ArrayList<>();

        String sql = "SELECT * FROM vies WHERE nom LIKE ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando vias", e);
        }

        return lista;
    }
/**@param rs Resulset @return Via v **/
    // MAP
    private Via map(ResultSet rs) throws SQLException {

        return new Via(
                rs.getInt("id_via"),
                rs.getInt("id_sector"),
                rs.getInt("id_tipus_via"),
                rs.getString("nom"),
                rs.getInt("llargada"),
                rs.getString("dificultat"),
                rs.getString("orientacio"),
                rs.getString("ancoratge"),
                rs.getString("troca")
        );
    }


/**@param via Via @return **/
    @Override
    public Sector buscarSector(Via via) {

        if (via == null || via.getId_via() <= 0) {
            throw new RuntimeException("Via inválida");
        }

        String sql = """
        SELECT s.*
        FROM vies v
        JOIN sectors s ON v.id_sector = s.id_sector
        WHERE v.id_via = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, via.getId_via());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    return new Sector(
                            rs.getInt("id_sector"),
                            rs.getInt("id_escola"),
                            rs.getString("nom"),
                            rs.getFloat("latitut"),
                            rs.getFloat("longitut"),
                            rs.getString("aproximacio"),
                            rs.getInt("popularitat")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sector", e);
        }

        return null;
    }
/**@param via Via  @return Escola **/
    @Override
    public Escola buscarEscola(Via via) {

        if (via == null) {
            throw new RuntimeException("Via inválida");
        }

        String sql = """
        SELECT e.*
        FROM vies v
        JOIN sectors s ON v.id_sector = s.id_sector
        JOIN escoles e ON s.id_escola = e.id_escola
        WHERE v.id_via = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, via.getId_via());

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
            throw new RuntimeException("Error obteniendo escola", e);
        }

        return null;
    }

/**@param dades String @return Lit via per dif DTO **/
    @Override
    public List<ViaPerDifDTO> viesPerDificultat(String dades)  {

        List<ViaPerDifDTO> llista = new ArrayList<>();

        String SQL = """
        SELECT  
            v.nom AS nom,
            v.dificultat AS dif,
            s.nom AS sector,
            e.nom AS escola
        FROM vies v
        INNER JOIN sectors s ON s.id_sector = v.id_sector
        INNER JOIN escoles e ON e.id_escola = s.id_escola
        WHERE v.dificultat BETWEEN ? AND ?
        ORDER BY v.dificultat;
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            String[] rangs = dades.trim().split("\\s+");

            if (rangs.length != 2) {
                throw new RuntimeException("Formato incorrecto. Ej: '5a 6b'");
            }

            ps.setString(1, rangs[0]);
            ps.setString(2, rangs[1]);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    ViaPerDifDTO vies = new ViaPerDifDTO(
                            rs.getString("nom"),
                            rs.getString("dif"),
                            rs.getString("sector"),
                            rs.getString("escola")
                    );
                    llista.add(vies);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo vias por dificultad", e);
        }

        return llista;
    }/**@return ViesperEstatApteDto**/
    @Override
    public  List<ViesPerEstatApteDTO> viesPerEstatApte(){
        List<ViesPerEstatApteDTO> aptes = new ArrayList<>();
        String sql = """
                SELECT  *
                FROM vies
                WHERE id_via NOT IN (SELECT d.id_via
                FROM disponibilitats d
                ) OR id_via IN (SELECT d1.id_via
                FROM disponibilitats d1
                WHERE NOW() NOT BETWEEN d1.inici AND d1.final)
                ORDER BY nom ASC;
                """;
        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ViesPerEstatApteDTO v = new ViesPerEstatApteDTO(
                            rs.getString("nom"),
                                    rs.getInt("llargada"),
                                    rs.getString("dificultat"),
                                    rs.getString("orientacio"),
                                    rs.getString("ancoratge"),
                                    rs.getString("troca")
                    );
                    aptes.add(v);
                }
            }
            return aptes;
        }
        catch (SQLException e ){
            System.out.println("ERROR");
            return null;
        }


    }/**@return Vies per estat tancat dt o**/
    @Override
    public List<ViesPerEstatTancatDTO> viesPerEstatTancat(){
        List<ViesPerEstatTancatDTO> tancats = new ArrayList<>();
        String sql = """
      SELECT v.*, d.rao, d.inici,d.final
      FROM vies v
      INNER  JOIN disponibilitats d ON v.id_via = d.id_via
      WHERE NOW()  BETWEEN d.inici AND d.final
      ORDER BY v.nom  ASC;
   """;
        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                ViesPerEstatTancatDTO dto = new ViesPerEstatTancatDTO(
                        rs.getString("v.nom"),
                        rs.getString("d.rao"),
                        rs.getString("d.inici"),
                        rs.getString("d.final")
                );
                    tancats.add(dto);
                }
                return tancats;
            }

        }
        catch (SQLException e ){
            System.out.println("ERROR");
            return null;
        }
    }

/**@param escola int @return retirna les vies mes llargues de la escola  **/
    @Override
    public List<ViesLlarguesDTO> mostrarViesLlargues(int escola){
        List<ViesLlarguesDTO> llargues = new ArrayList<>();
        String SQL = """
               SELECT v.*
               FROM vies v
               INNER JOIN sectors s ON s.id_sector = v.id_sector
               WHERE s.id_escola = ?
               GROUP BY v.id_via
               HAVING v.llargada = MAX(v.llargada);
               """;


        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
        ){
            ps.setInt(1, escola);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ViesLlarguesDTO v  = new ViesLlarguesDTO(rs.getString("v.nom"),rs.getInt("v.llargada"));
                    llargues.add(v);
                }
            }
            return llargues;
        }
        catch (SQLException e ){
            System.out.println("ERROR");
            return null;
        }
    }

    @Override
    public List<ViesAptRecentDTO> viasAptesRecent(int dia) {

        List<ViesAptRecentDTO> viesRecent = new ArrayList<>();

        String SQL = """
        SELECT 
            v.nom AS nom, 
            DATEDIFF(NOW(), d.final) AS dies
        FROM vies v
        INNER JOIN disponibilitats d ON d.id_via = v.id_via
        WHERE DATEDIFF(NOW(), d.final) <= ?
          AND d.final <= CURDATE()
        ORDER BY dies ASC
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, dia);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    ViesAptRecentDTO via = new ViesAptRecentDTO(
                            rs.getString("nom"),
                            rs.getInt("dies")
                    );

                    viesRecent.add(via);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo vias aptas recientes", e);
        }

        return viesRecent;
    }

    @Override
    public void EliminarViasPorSector(int id_sector) {

        String sql = "DELETE FROM vies WHERE id_sector = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_sector);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            throw new RuntimeException("Error eliminando vias del sector: " + id_sector, e);
        }

    }

    @Override
    public  void ElimnarViaDisponibilitat(int via  ){
        String SQL = "DELETE FROM disponibilitats WHERE id_via = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, via);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            throw new RuntimeException("Error eliminando via Dispoonibilitat", e);
        }
    }
    @Override
    public List<Via> buscarViesPorSector(int sector ){
        List<Via> l = new ArrayList<>();

        String sql = "SELECT * FROM vies WHERE id_sector = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sector );

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    l.add(map(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sectores por escola", e);
        }

        return  l;


    }
    @Override
    public void ElimnarViaEscaladors(int via ){
        String SQL = "DELETE FROM escaladors_vies WHERE id_via = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, via);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            throw new RuntimeException("Error eliminando via Dispoonibilitat", e);
        }
    }
    @Override
    public void EliminarViaLlars(int via ){
        String SQL = "DELETE FROM llars  WHERE id_via = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, via);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            throw new RuntimeException("Error eliminando via Dispoonibilitat", e);
        }
    }

}