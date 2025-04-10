package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Erosleak;
import devworks.model.base.Kategoria;
import devworks.model.base.Produktuak;
import devworks.model.base.Saltzaileak;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProduktoAtzipena {
    private String server;
    private String db;
    private String taula;

    String user;
    String pass;

    public ProduktoAtzipena(String server, String db, String taula, String user, String pass) {
        this.server = server;
        this.user = user;
        this.pass = pass;
        this.db = db;
        this.taula = taula;
    }

    public Connection konektatu() {
        String url = "jdbc:mariadb://" + server + "/" + db;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            // Si la conexión es exitosa, puedes imprimir un mensaje en la consola
            // System.out.println("Conexión exitosa a la base de datos " + db + " en el
            // servidor " + server);
        } catch (SQLException e) {
            // Crear el Alert para mostrar el error
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de Conexión");
            alert.setHeaderText("No se pudo establecer la conexión");

            // Dependiendo del error, puedes dar diferentes detalles
            if (e.getErrorCode() == 1045) {
                alert.setContentText("Error: Usuario o contraseña incorrectos.");
            } else if (e.getErrorCode() == 0) {
                alert.setContentText("Error: No se pudo conectar con el servidor.");
            } else {
                alert.setContentText("Código de error: " + e.getErrorCode() + "\n" + e.getMessage());
            }

            // Mostrar el Alert
            alert.showAndWait();
        }
        return conn;
    }

    public List<Produktuak> filterProduktoa(int idKategoria) {
        String sql = "SELECT produktuak.id_produktu, produktuak.izena, produktuak.deskribapena, produktuak.prezioa, produktuak.id_kategoria, produktuak.id_saltzaile, produktuak.egoera, bezeroak.email, produktuak.salduta FROM "
                + taula + " INNER JOIN bezeroak ON bezeroak.id_bezero = id_saltzaile WHERE id_kategoria = ?";

        List<Produktuak> produktuak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idKategoria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                produktuak.add(new Produktuak(rs.getInt("id_produktu"), rs.getString("izena"),
                        rs.getString("deskribapena"),
                        rs.getInt("prezioa"), rs.getInt("id_kategoria"), rs.getInt("id_saltzaile"),
                        rs.getString("egoera"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return produktuak;
    }

    // Produktuak(int id, String izena, String deskribapena, int prezioa, int
    // idKategoria, int idSaltzaile, String egoera,
    // String email)

    public List<Produktuak> getProduktoak() {
        String sql = "SELECT produktuak.id_produktu, produktuak.izena, produktuak.deskribapena, produktuak.prezioa, produktuak.id_kategoria, produktuak.id_saltzaile, produktuak.egoera, bezeroak.email, produktuak.salduta FROM "
                + taula + " INNER JOIN bezeroak ON bezeroak.id_bezero = produktuak.id_saltzaile";
        List<Produktuak> produktuak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                produktuak.add(new Produktuak(rs.getInt("id_produktu"), rs.getString("izena"),
                        rs.getString("deskribapena"),
                        rs.getInt("prezioa"), rs.getInt("id_kategoria"), rs.getInt("id_saltzaile"),
                        rs.getString("egoera"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return produktuak;
    }

    public List<Kategoria> getAllKategoriak() {
        String sql = "SELECT * FROM kategoriak";
        List<Kategoria> kategoriak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                kategoriak.add(
                        new Kategoria(rs.getInt("id_kategoria"), rs.getString("izena"), rs.getString("deskribapena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return kategoriak;
    }

    public List<Saltzaileak> getAllSaltzaileak() {
        String sql = "SELECT * FROM bezeroak";
        List<Saltzaileak> bezeroak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bezeroak.add(
                        new Saltzaileak(rs.getInt("id_bezero"), rs.getString("email"), rs.getString("izena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroak;
    }

    public List<Erosleak> getAllErosleak() {
        String sql = "SELECT * FROM bezeroak";
        List<Erosleak> erosleakList = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                erosleakList.add(new Erosleak(rs.getInt("id_bezero"), rs.getString("email"), rs.getString("izena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return erosleakList;
    }

    public Erosleak getErosleById(int id) {
        String sql = "SELECT * FROM erosleak WHERE id_erosle = ?";
        Erosleak erosleak = null;
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                erosleak = new Erosleak(rs.getInt("id_erosle"), rs.getString("email"), rs.getString("izena"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return erosleak;
    }

    public boolean produktuBaDago(int id) {
        String sql = "SELECT produktuak.id_produktu, produktuak.izena, produktuak.deskribapena, produktuak.prezioa, produktuak.id_kategoria, produktuak.id_saltzaile, produktuak.egoera, bezeroak.email, produktuak.salduta FROM "
                + taula + " INNER JOIN bezeroak ON bezeroak.id_bezero = produktuak.id_saltzaile WHERE id_produktu = ?";
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a product with the given ID exists
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean saldutaBaDago(int id) {
        String sql = "SELECT salduta FROM " + taula + " WHERE id_produktu = ?";
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("salduta") == 1; // Returns true if the product is sold
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Kategoria getKategoriaById(int id) {
        String sql = "SELECT * FROM kategoriak WHERE id_kategoria = ?";
        Kategoria kategoria = null;
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                kategoria = new Kategoria(rs.getInt("id_kategoria"), rs.getString("izena"),
                        rs.getString("deskribapena"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kategoria;
    }

    public Saltzaileak getSaltzaileById(int id) {
        String sql = "SELECT * FROM bezeroak WHERE id_bezero = ?";
        Saltzaileak saltzailea = null;
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                saltzailea = new Saltzaileak(rs.getInt("id_bezero"), rs.getString("email"), rs.getString("izena"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return saltzailea;
    }

    public Produktuak searchProduktuak(int id) {
        String sql = "SELECT produktuak.id_produktu, produktuak.izena, produktuak.deskribapena, produktuak.prezioa, produktuak.id_kategoria, produktuak.id_saltzaile, produktuak.egoera, bezeroak.email, produktuak.salduta FROM "
                + taula + " INNER JOIN bezeroak ON bezeroak.id_bezero = produktuak.id_saltzaile WHERE id_produktu = ?";
        Produktuak produktua = null;
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                produktua = new Produktuak(rs.getInt("id_produktu"), rs.getString("izena"),
                        rs.getString("deskribapena"),
                        rs.getInt("prezioa"), rs.getInt("id_kategoria"), rs.getInt("id_saltzaile"),
                        rs.getString("egoera"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return produktua;
    }

    public int searchSalmentaErosle(int id) {
        String sql = "SELECT id_erosle FROM salmentak WHERE id_produktu = ?";
        int idErosle = -1;
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idErosle = rs.getInt("id_erosle");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idErosle;
    }

    public int handleAldatu(Produktuak produktua, int salduta, int idErosle) {
        String sqlUpdateProduktua = "UPDATE " + taula
                + " SET izena = ?, deskribapena = ?, prezioa = ?, id_kategoria = ?, id_saltzaile = ?, egoera = ?, salduta = ? WHERE id_produktu = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sqlUpdateProduktua)) {

            pstmt.setString(1, produktua.getIzena());
            pstmt.setString(2, produktua.getDeskribapena());
            pstmt.setInt(3, produktua.getPrezioa());
            pstmt.setInt(4, produktua.getIdKategoria());
            pstmt.setInt(5, produktua.getIdSaltzaile());
            pstmt.setString(6, produktua.getEgoera());
            pstmt.setInt(7, salduta);
            pstmt.setInt(8, produktua.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return 0;
            }

            if (salduta == 1) {
                if (!saldutaBaDago(produktua.getId())) {
                    String sqlInsertSalmenta = "INSERT INTO salmentak (id_produktu, id_saltzaile, id_erosle, data, salmenta_prezioa) VALUES (?, ?, ?, NOW(), ?)";

                    try (PreparedStatement insertStmt = conn.prepareStatement(sqlInsertSalmenta)) {
                        insertStmt.setInt(1, produktua.getId());
                        insertStmt.setInt(2, produktua.getIdSaltzaile());
                        insertStmt.setInt(3, idErosle);
                        insertStmt.setInt(4, produktua.getPrezioa());

                        int inserted = insertStmt.executeUpdate();
                        if (inserted > 0) {
                            return 1;
                        } else {
                            return -2; // No se insertó en salmentak
                        }
                    }
                } else {
                    String sqlUpdateSalmenta = "UPDATE salmentak SET id_erosle = ? WHERE id_produktu = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdateSalmenta)) {
                        updateStmt.setInt(1, idErosle);
                        updateStmt.setInt(2, produktua.getId());
                        updateStmt.executeUpdate();
                        return 1;
                    }
                }
            }

            return 1;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return -1062;
            }
            return -1;
        }
    }

    public boolean deleteProduktuak(String izena) {

        if (taula == null || taula.isEmpty()) {
            System.out.println("Errorea: taula ez da definitu");
            return false;
        }

        String sql = "DELETE FROM " + taula + " WHERE izena = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, izena.trim());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(izena + " produktua ezabatu egin da.");
                return true;
            } else {
                System.out.println(izena + " ez da existitzen.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Errorea: " + e.getMessage());
            return false;
        }
    }

    public boolean produktuaTxertatu(Produktuak produktua) {
        String sql = "INSERT INTO " + taula
                + " (izena, deskribapena, prezioa, id_kategoria, egoera, id_saltzaile, salduta) " +
                "VALUES (?, ?, ?, ?, ?, ?, 0)";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Produktuak(int id, String izena, String deskribapena, int prezioa, int
            // idKategoria, String egoera,
            // String email) {

            pstmt.setString(1, produktua.getIzena());
            pstmt.setString(2, produktua.getDeskribapena());
            pstmt.setInt(3, produktua.getPrezioa());
            pstmt.setInt(4, produktua.getIdKategoria());
            pstmt.setString(5, produktua.getEgoera());
            pstmt.setInt(6, produktua.getIdSaltzaile());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Errorea produktua txertatzean: " + e.getMessage());
            return false;
        }
    }
}
