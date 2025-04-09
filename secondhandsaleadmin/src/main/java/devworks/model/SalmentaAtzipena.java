package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Erosleak;
import devworks.model.base.Salmentak;
import devworks.model.base.Saltzaileak;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

    public Salmentak bilatuSalmenta(int id) {
        String sql = "SELECT salmentak.id_salmenta, salmentak.id_produktu, salmentak.id_saltzaile, salmentak.id_erosle, salmentak.data, salmentak.salmenta_prezioa, produktuak.izena FROM "
                + taula
                + " salmentak INNER JOIN produktuak ON salmentak.id_produktu = produktuak.id_produktu WHERE WHERE id_salmenta = ?";
        Salmentak salmenta = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                salmenta = new Salmentak(rs.getInt("id_salmenta"), rs.getInt("id_produktu"),
                        rs.getInt("id_saltzaile"), rs.getInt("id_erosle"), rs.getString("data"),
                        rs.getDouble("salmenta_prezioa"), rs.getString("izena"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return salmenta;
    }

    public boolean salmentaBaDago(int id) {
        String sql = "SELECT * FROM " + taula + " WHERE id_salmenta = ?";
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

    public List<Salmentak> filterSalmentak(int idSaltzaile) {
        String sql = "SELECT salmentak.id_salmenta, salmentak.id_produktu, salmentak.id_saltzaile, salmentak.id_erosle, salmentak.data, salmentak.salmenta_prezioa, produktuak.izena FROM "
                + taula
                + " salmentak INNER JOIN produktuak ON salmentak.id_produktu = produktuak.id_produktu WHERE salmentak.id_saltzaile = ?";

        List<Salmentak> salmentak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSaltzaile);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                salmentak.add(new Salmentak(rs.getInt("id_salmenta"), rs.getInt("id_produktu"),
                        rs.getInt("id_saltzaile"), rs.getInt("id_erosle"), rs.getString("data"),
                        rs.getDouble("salmenta_prezioa"), rs.getString("izena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return salmentak;
    }

    public List<Salmentak> getSalmentak() {
        String sql = "SELECT salmentak.id_salmenta, salmentak.id_produktu, salmentak.id_saltzaile, salmentak.id_erosle, salmentak.data, salmentak.salmenta_prezioa, produktuak.izena FROM "
                + taula + " INNER JOIN produktuak ON salmentak.id_produktu = produktuak.id_produktu";
        List<Salmentak> salmentak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                salmentak.add(new Salmentak(rs.getInt("id_salmenta"), rs.getInt("id_produktu"),
                        rs.getInt("id_saltzaile"), rs.getInt("id_erosle"), rs.getString("data"),
                        rs.getDouble("salmenta_prezioa"), rs.getString("izena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return salmentak;
    }

    public List<Saltzaileak> getAllSaltzaileak() {
        String sql = "SELECT salmentak.id_saltzaile, bezeroak.email, bezeroak.izena FROM " + taula
                + " INNER JOIN bezeroak ON salmentak.id_saltzaile = bezeroak.id_bezero GROUP BY id_saltzaile";
        List<Saltzaileak> saltzaileak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                saltzaileak.add(new Saltzaileak(rs.getInt("id_saltzaile"), rs.getString("email"),
                        rs.getString("izena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return saltzaileak;
    }

    public List<Erosleak> getAllErosleak() {
        String sql = "SELECT salmentak.id_erosle, bezeroak.email, bezeroak.izena FROM " + taula
                + " INNER JOIN bezeroak ON salmentak.id_erosle = bezeroak.id_bezero GROUP BY id_erosle";
        List<Erosleak> erosleak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                erosleak.add(new Erosleak(rs.getInt("id_erosle"), rs.getString("email"), rs.getString("izena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return erosleak;
    }

    public boolean deletesSalmentak(int produktuId) {
        if (taula == null || taula.isEmpty()) {
            System.out.println("Errorea: taula ez da definitu");
            return false;
        }
        String sql = "DELETE FROM " + taula + " WHERE id_salmenta = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, produktuId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(produktuId + " ezabatu egin da.");
                return true;
            } else {
                System.out.println(produktuId + " ez da exititzen.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Errorea: " + e.getMessage());
            return false;
        }
    }

    public boolean salmentaTxertatu(Salmentak salmenta) {
        String sql = "INSERT INTO " + taula
                + " (id_produktu, id_saltzaile, id_erosle, data, salmenta_prezioa) " +
                "VALUES (?, ?, ?, NOW(), ?)";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Produktuak(int id, String izena, String deskribapena, int prezioa, int
            // idKategoria, String egoera,
            // String email) {

            pstmt.setInt(1, salmenta.getIdProduktu());
            pstmt.setInt(2, salmenta.getIdSaltzaile());
            pstmt.setInt(3, salmenta.getIdErosle());
            pstmt.setDouble(4, salmenta.getSalmentaPrezioa());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Errorea produktua txertatzean: " + e.getMessage());
            return false;
        }
    }
}
