package devworks.model.base;

import java.sql.Date;

public class Salmentak {
    private int id;
    private int idProduktu;
    private int idSaltzaile;
    private int idErosle;
    private Date data;
    private double salmentaPrezioa;
    private String izenaProduktu;

    public Salmentak(int id, int idProduktu, int idSaltzaile, int idErosle, Date data, double salmentaPrezioa,
            String izenaProduktu) {
        this.id = id;
        this.idProduktu = idProduktu;
        this.idSaltzaile = idSaltzaile;
        this.idErosle = idErosle;
        this.data = data;
        this.salmentaPrezioa = salmentaPrezioa;
        this.izenaProduktu = izenaProduktu;
    }

    public int getId() {
        return id;
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

    public int getIdProduktu() {
        return idProduktu;
    }

    public String getIzenaProduktu() {
        return izenaProduktu;
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

    public void setIzenaProduktu(String izenaProduktu) {
        this.izenaProduktu =  izenaProduktu;
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
                ", izenaProduktu=" + izenaProduktu +
                '}';
    }
}
