package devworks.model.base;

public class Bezeroak extends Pertsonak {

    public Bezeroak(int id, String izena, String email, int telefonoa, String herria, String postaKodea,
            String helbidea, String erregistroData, String erabiltzailea, String pasahitza) {
        super(id, izena, email, telefonoa, herria, postaKodea, helbidea, erregistroData, erabiltzailea, pasahitza);
    }

    @Override
    public String toString() {
        return "Bezeroak{" +
                "id=" + getId() +
                ", izena='" + getIzena() + '\'' +
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