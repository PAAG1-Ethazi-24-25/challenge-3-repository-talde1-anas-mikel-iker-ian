package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Langileak;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LangileAtzipena {
    private String server;
    private String db;
    private String taula;

    String user;
    String pass;

    public LangileAtzipena(String server, String db, String taula, String user, String pass) {
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

    public Langileak searchLangilea(int id) { // Bilatu langilea id bidez
        String sql = "SELECT * FROM " + taula + " WHERE id_langile = ?";
        Langileak langilea = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                langilea = new Langileak(rs.getInt("id_langile"), rs.getString("izena"), rs.getString("kargua"),
                        rs.getString("email"), rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return langilea;
    }

    public boolean langileaBaDago(int id) { // Egiaztatu langilea existitzen den
        String sql = "SELECT * FROM " + taula + " WHERE id_langile = ?";
        boolean langileaDago = false;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                langileaDago = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return langileaDago;
    }

    public List<Langileak> filterLangileak(String herria) { // Egiaztatu langileak herriaren arabera
        String sql = "SELECT * FROM " + taula + " WHERE herria = ?";

        List<Langileak> langileak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, herria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                langileak.add(new Langileak(rs.getInt("id_langile"), rs.getString("izena"), rs.getString("kargua"),
                        rs.getString("email"), rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return langileak;
    }

    public List<Langileak> getLangileak() { // Langile guztiak lortu
        String sql = "SELECT * FROM " + taula;
        List<Langileak> langileak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                langileak.add(new Langileak(rs.getInt("id_langile"), rs.getString("izena"), rs.getString("kargua"),
                        rs.getString("email"), rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return langileak;
    }

    public List<String> getAllHerriak() { // Herriak lortu
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

    public boolean isEmailDuplicate(String email) { // Egiaztatu email-a existitzen den
        String sql = "SELECT COUNT(*) FROM langileak WHERE email = ?";
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            // If the result is greater than 0, it means that a record with that email
            // already exists
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // The email is duplicated
            }
        } catch (SQLException e) {
            System.out.println("Errorea email-a egiaztatzean: " + e.getMessage());
        }
        return false; // It is not duplicated
    }

    public boolean isUsernameDuplicate(String username) {
        String sql = "SELECT COUNT(*) FROM langileak WHERE erablitzaile_izena = ?";
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            // If the result is greater than 0, it means that a record with that username
            // already exists
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // The username is duplicated
            }
        } catch (SQLException e) {
            System.out.println("Errorea erabiltzailea egiaztatzean: " + e.getMessage());
        }
        return false; // It is not duplicated
    }

    public int langileaTxertatu(Langileak langilea) { // Txertatu langilea datu-basean
        String sql = "INSERT INTO " + taula
                + " (izena, kargua, telefonoa, email, helbidea, herria, posta_kodea, erablitzaile_izena, pasahitza) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Langileak(int id, String izena, String kargua, String email, int telefonoa,
            // String herria,
            // String postaKodea, String helbidea, String erregistroData)

            pstmt.setString(1, langilea.getIzena());
            pstmt.setString(2, langilea.getKargua());
            pstmt.setInt(3, langilea.getTelefonoa());
            pstmt.setString(4, langilea.getEmail());
            pstmt.setString(5, langilea.getHelbidea());
            pstmt.setString(6, langilea.getHerriIzena());
            pstmt.setString(7, langilea.getPostaKodea());
            pstmt.setString(8, langilea.getErabiltzailea());
            pstmt.setString(9, langilea.getPasahitza());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return 0; // No record was inserted
            }

            return 1; // Successful insertion

        } catch (SQLException e) {
            // Handling errors with specific codes
            if (e.getErrorCode() == 1062) {
                return -1062; // Error code for duplicate key
            } else {
                System.out.println("Errorea langilea txertatzean: " + e.getMessage());
                return -1; // Generic error
            }
        }
    }

    public int handleAldatu(Langileak langilea) { // Eguneratu langilea datu-basean
        String sql = "UPDATE " + taula
                + " SET izena = ?, kargua = ?, telefonoa = ?, email = ?, helbidea = ?, herria = ?, posta_kodea = ?, erablitzaile_izena = ?, pasahitza = ? WHERE id_langile = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, langilea.getIzena());
            pstmt.setString(2, langilea.getKargua());
            pstmt.setInt(3, langilea.getTelefonoa());
            pstmt.setString(4, langilea.getEmail());
            pstmt.setString(5, langilea.getHelbidea());
            pstmt.setString(6, langilea.getHerriIzena());
            pstmt.setString(7, langilea.getPostaKodea());
            pstmt.setString(8, langilea.getErabiltzailea());
            pstmt.setString(9, langilea.getPasahitza());
            pstmt.setInt(10, langilea.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return 0;
            }

            return 1;
        } catch (SQLException e) {
            System.out.println("Errorea produktua eguneratzean: " + e.getMessage());
            return -1;
        }
    }

    public boolean deleteLangilea(String izena) { // Ezabatu langilea izenaren arabera
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
                System.out.println(izena + " langilea ezabatu egin da.");
                return true;
            } else {
                System.out.println(izena + " langilea ez da existitzen.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Errorea: " + e.getMessage());
            return false;
        }
    }
}
