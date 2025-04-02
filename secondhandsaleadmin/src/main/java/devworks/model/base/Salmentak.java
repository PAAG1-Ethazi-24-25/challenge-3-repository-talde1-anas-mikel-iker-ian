package devworks.model.base;

import java.sql.Date;

public class Salmentak {
    private int id;
    private int idProduktu;
    private int idBezero;
    private Date data;
    private double salmentaPrezioa;

    public Salmentak(int id, int idProduktu, int idBezero, Date data, double salmentaPrezioa) {
        this.id = id;
        this.idProduktu = idProduktu;
        this.idBezero = idBezero;
        this.data = data;
        this.salmentaPrezioa = salmentaPrezioa;
    }

    public int getId() {
        return id;
    }

    public int getIdProduktu() {
        return idProduktu;
    }

    public int getIdBezero() {
        return idBezero;
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

    public void setIdBezero(int idBezero) {
        this.idBezero = idBezero;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setSalmentaPrezioa(double salmentaPrezioa) {
        this.salmentaPrezioa = salmentaPrezioa;
    }

    @Override
    public String toString() {
        return "Salmentak {id=" + id + ", idProduktu=" + idProduktu + ", idBezero=" + idBezero + ", data=" + data
                + ", salmentaPrezioa=" + salmentaPrezioa + "}";
    }
}
