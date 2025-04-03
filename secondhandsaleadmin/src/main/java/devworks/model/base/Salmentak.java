package devworks.model.base;

import java.sql.Date;

public class Salmentak {
    private int id;
    private int idProduktu;
    private int idSaltzaile;
    private int idErosle;
    private Date data;
    private double salmentaPrezioa;

    public Salmentak(int id, int idProduktu, int idSaltzaile, int idErosle, Date data, double salmentaPrezioa) {
        this.id = id;
        this.idProduktu = idProduktu;
        this.idSaltzaile = idSaltzaile;
        this.idErosle = idErosle;
        this.data = data;
        this.salmentaPrezioa = salmentaPrezioa;
    }

    public int getId() {
        return id;
    }

    public int getIdProduktu() {
        return idProduktu;
    }

    public int getIdSaltzaile() {
        return idSaltzaile;
    }

    public int getIdErosle() {
        return idErosle;
    }

    public Date getData() {
        return data;
    }

    public double getSalmentaPrezioa() {
        return salmentaPrezioa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdProduktu(int idProduktu) {
        this.idProduktu = idProduktu;
    }

    public void setIdSaltzaile(int idSaltzaile) {
        this.idSaltzaile = idSaltzaile;
    }

    public void setIdErosle(int idErosle) {
        this.idErosle = idErosle;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setSalmentaPrezioa(double salmentaPrezioa) {
        this.salmentaPrezioa = salmentaPrezioa;
    }

    @Override
    public String toString() {
        return "Salmentak{" +
                "id=" + id +
                ", idProduktu=" + idProduktu +
                ", idSaltzaile=" + idSaltzaile +
                ", idErosle=" + idErosle +
                ", data=" + data +
                ", salmentaPrezioa=" + salmentaPrezioa +
                '}';
    }
}
