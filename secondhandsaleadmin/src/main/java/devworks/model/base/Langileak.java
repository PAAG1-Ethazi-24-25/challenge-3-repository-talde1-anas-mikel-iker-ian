package devworks.model.base;

public class Langileak extends Pertsonak {

    private String kargua;

    public Langileak(int id, String izena, String kargua, String email, int telefonoa, String herria,
            String postaKodea, String helbidea, String erregistroData, String erabiltzailea, String pasahitza) {
        super(id, izena, email, telefonoa, herria, postaKodea, helbidea, erregistroData, erabiltzailea, pasahitza);
        this.kargua = kargua;
    }

    public String getKargua() {
        return kargua;
    }

    public void setKargua(String kargua) {
        this.kargua = kargua;
    }

    @Override
    public String toString() {
        return "Langileak{" +
                "id=" + getId() +
                ", izena='" + getIzena() + '\'' +
                ", kargua='" + kargua + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefonoa=" + getTelefonoa() +
                ", herria='" + getHerria() + '\'' +
                ", postaKodea='" + getPostaKodea() + '\'' +
                ", helbidea='" + getHelbidea() + '\'' +
                ", altaData='" + getAltaData() + '\'' +
                ", erabiltzailea='" + getErabiltzailea() + '\'' +
                '}';
    }
}
