package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Salmentak;

public class SalmentaAtzipena {
    private String server;
    private String db;
    private String taula;

    String user;
    String pass;

    public SalmentaAtzipena(String server, String db, String taula, String user, String pass) {
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

    public List<Salmentak> filterSalmentak(int idProduktu) {
        String sql = "SELECT salmentak.id_salmenta, salmentak.id_produktu, salmentak.id_saltzaile, salmentak.id_erosle, salmentak.data, salmentak.salmenta_prezioa, produktuak.izena FROM "
                + taula
                + " salmentak INNER JOIN produktuak ON salmentak.id_produktu = produktuak.id_produktu WHERE id_produktu = ?";

        List<Salmentak> salmentak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProduktu);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                salmentak.add(new Salmentak(rs.getInt("id_salmenta"), rs.getInt("id_produktu"),
                        rs.getInt("id_saltzaile"), rs.getInt("id_erosle"), rs.getDate("data"),
                        rs.getDouble("salmenta_prezioa"), rs.getString("izena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return salmentak;
    }

    public List<Salmentak> getSalmentak() {
        String sql = "SELECT salmentak.id_salmenta, salmentak.id_produktu, salmentak.id_saltzaile, salmentak.id_erosle, salmentak.data, salmentak.salmenta_prezioa, produktuak.izena AS izena FROM "
                + taula + " INNER JOIN produktuak ON salmentak.id_produktu = produktuak.id_produktu";
        List<Salmentak> salmentak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                salmentak.add(new Salmentak(rs.getInt("id_salmenta"), rs.getInt("id_produktu"),
                        rs.getInt("id_saltzaile"), rs.getInt("id_erosle"), rs.getDate("data"),
                        rs.getDouble("salmenta_prezioa"), rs.getString("izena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return salmentak;
    }

    public boolean deletesSalmentak(String produktuIzena) {

        if (taula == null || taula.isEmpty()) {
            System.out.println("Errorea: taula ez da definitu");
            return false;
        }
        /*
         * 
         * 
         * 
         * EL NOMBRE DE LA COLUMNA, NOSE SI ESTA BIEN "produktuIzena"
         * 
         * 
         * 
         */
        String sql = "DELETE FROM " + taula + " WHERE produktuIzena = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produktuIzena);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(produktuIzena + " ezabatu egin da.");
                return true;
            } else {
                System.out.println(produktuIzena + " ez da exititzen.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Errorea: " + e.getMessage());
            return false;
        }

    }
}
