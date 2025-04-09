package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Langileak;

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

    public Connection konektatu() {
        String url = "jdbc:mariadb://" + server + "/" + db;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            // System.out.println(server + " zerbidoreko " + db + " datu-basera konektatu
            // zara.");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1045)
                System.out.println("Erabiltzaile edo pasahitz okerrak");
            else if (e.getErrorCode() == 0)
                System.out.println("Ezin zerbitzariarekin konektatu");
            else
                System.out.println(e.getErrorCode() + "-" + e.getMessage());
        }
        return conn;
    }

    public Langileak searchLangilea(int id) {
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

    public boolean langileaBaDago(int id) {
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

    public List<Langileak> filterLangileak(String herria) {
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

    public List<Langileak> getLangileak() {
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

    public List<String> getAllHerriak() {
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

    public boolean deleteLangilea(String izena) {

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

    public boolean langileaTxertatu(Langileak langilea) {
        String sql = "INSERT INTO " + taula
                + " (izena, kargua, telefonoa, email, helbidea, herria, posta_kodea, erablitzaile_izena, pasahitza) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Errorea produktua txertatzean: " + e.getMessage());
            return false;
        }
    }

}
