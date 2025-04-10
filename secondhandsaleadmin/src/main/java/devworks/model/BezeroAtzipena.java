package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import devworks.model.base.Bezeroak;

public class BezeroAtzipena {
    private String server;
    private String db;
    private String taula;

    String user;
    String pass;

    public BezeroAtzipena(String server, String db, String taula, String user, String pass) {
        this.server = server;
        this.user = user;
        this.pass = pass;
        this.db = db;
        this.taula = taula;
    }

    public Connection konektatu() { // Konektatu datu-basearekin
        String url = "jdbc:mariadb://" + server + "/" + db;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            // System.out.println("Conexión exitosa a la base de datos " + db + " en el
            // servidor " + server);
        } catch (SQLException e) {
            // Create the Alert to display the error
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errorea konexioan");
            alert.setHeaderText("Ezin izan da konexioa ezarri");

            if (e.getErrorCode() == 1045) {
                alert.setContentText("Errorea: erabiltzaile edo pasahitz okerrak.");
            } else if (e.getErrorCode() == 0) {
                alert.setContentText("Errorea: ezin izan da zerbitzariarekin konektatu.");
            } else {
                alert.setContentText("Código de error: " + e.getErrorCode() + "\n" + e.getMessage());
            }

            alert.showAndWait();
        }
        return conn;
    }

    public Bezeroak bilatuBezeroa(int id) { // Bilatu bezeroa id bidez
        String sql = "SELECT * FROM " + taula + " WHERE id_bezero = ?";
        Bezeroak bezeroa = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bezeroa = new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroa;
    }

    public boolean bezeroBaDago(int id) { // Bezeroa dagoen egiaztatu id bidez
        String sql = "SELECT * FROM " + taula + " WHERE id_bezero = ?";
        boolean exists = false;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return exists;
    }

    public Bezeroak searchBezeroak(int id) { // Bilatu bezeroa id bidez
        String sql = "SELECT * FROM " + taula + " WHERE id_bezero = ?";
        Bezeroak bezeroa = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bezeroa = new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroa;
    }

    public List<Bezeroak> filterBezeroak(String herria) { // Bilatu bezeroak herriaren bidez
        String sql = "SELECT * FROM " + taula + " WHERE herria = ?";

        List<Bezeroak> bezeroak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, herria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bezeroak.add(new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroak;
    }

    public List<Bezeroak> getBezeroak() { // Datu-baseko bezero guztiak lortu
        String sql = "SELECT * FROM " + taula;
        List<Bezeroak> bezeroak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bezeroak.add(new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroak;
    }

    public List<String> getAllHerriak() { // Datu-baseko herriak lortu
        String sql = "SELECT herria FROM " + taula + " GROUP BY herria";
        List<String> herriak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                herriak.add(rs.getString("herria"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return herriak;
    }

    public boolean isEmailDuplicate(String email) { // Egiaztatu email-a
        String sql = "SELECT COUNT(*) FROM bezeroak WHERE email = ?";
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Email duplicated
            }
        } catch (SQLException e) {
            System.out.println("Errorea email-a egiaztatzean: " + e.getMessage());
        }
        return false; // No duplicated
    }

    public boolean isUsernameDuplicate(String username) { // Egiaztatu erabiltzaile izena
        String sql = "SELECT COUNT(*) FROM bezeroak WHERE erablitzaile_izena = ?";
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // username duplicated
            }
        } catch (SQLException e) {
            System.out.println("Errorea erabiltzailea egiaztatzean: " + e.getMessage());
        }
        return false; // No duplicated
    }

    public int bezeroaTxertatu(Bezeroak bezeroa) {
        String sql = "INSERT INTO " + taula
                + " (izena, email, telefonoa, helbidea, herria, posta_kodea, erablitzaile_izena, pasahitza) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bezeroak(int id, String izena, String email, int telefonoa, String herria,
            // String postaKodea,
            // String helbidea, String erregistroData, String erabiltzalea, String
            // pasahitza)

            pstmt.setString(1, bezeroa.getIzena());
            pstmt.setString(2, bezeroa.getEmail());
            pstmt.setInt(3, bezeroa.getTelefonoa());
            pstmt.setString(4, bezeroa.getHelbidea());
            pstmt.setString(5, bezeroa.getHerria());
            pstmt.setString(6, bezeroa.getPostaKodea());
            pstmt.setString(7, bezeroa.getErabiltzalea());
            pstmt.setString(8, bezeroa.getPasahitza());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return 0; // No record was inserted
            }

            return 1; // Successful insertion

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return -1062; // Error code for duplicate key
            } else {
                System.out.println("Errorea bezeroa txertatzean: " + e.getMessage());
                return -1; // Generic error
            }
        }
    }

    public int handleAldatu(Bezeroak bezeroa) { // Aldatu bezeroa
        String sql = "UPDATE " + taula
                + " SET izena = ?, email = ?, telefonoa = ?, helbidea = ?, herria = ?, posta_kodea = ?, erablitzaile_izena = ?, pasahitza = ? "
                + "WHERE id_bezero = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bezeroa.getIzena());
            pstmt.setString(2, bezeroa.getEmail());
            pstmt.setInt(3, bezeroa.getTelefonoa());
            pstmt.setString(4, bezeroa.getHelbidea());
            pstmt.setString(5, bezeroa.getHerria());
            pstmt.setString(6, bezeroa.getPostaKodea());
            pstmt.setString(7, bezeroa.getErabiltzalea());
            pstmt.setString(8, bezeroa.getPasahitza());
            pstmt.setInt(9, bezeroa.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return 0; // No record was updated
            }

            return 1; // Successful update
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return -1062; // Error code for duplicate key
            }
            return -1; // Generic error
        }
    }

    public boolean deleteBezeroa(String izena) { // Ezabatu bezeroa izenaren bidez

        if (taula == null || taula.isEmpty()) { // To verify that the table is not empty before starting
            System.out.println("Errorea: taula ez da definitu");
            return false;
        }

        String sql = "DELETE FROM " + taula + " WHERE izena = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, izena.trim()); // Just in case the user enters the name with blank spaces
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(izena + " bezeroa ezabatu egin da.");
                return true;
            } else {
                System.out.println(izena + " bezeroa ez da existitzen.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Errorea ezabatzerakoan: " + e.getMessage());
            return false;
        }
    }
}