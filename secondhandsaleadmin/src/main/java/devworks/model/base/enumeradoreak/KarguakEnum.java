package devworks.model.base.enumeradoreak;

public enum KarguakEnum {
    ADMINISTRATZAILEA("administratzailea"),
    SALTZAILEA("saltzailea"),
    KUDEATZAILEA("kudeatzailea"),
    GARRAIOLARIA("garraiolaria");

    private final String izena;

    KarguakEnum(String izena) {
        this.izena = izena;
    }

    public String getIzena() {
        return izena;
    }

    @Override
    public String toString() {
        return izena;
    }

    public static KarguakEnum fromIzena(String izena) {
        for (KarguakEnum k : values()) {
            if (k.izena.equalsIgnoreCase(izena)) {
                return k;
            }
        }
        return null;
    }
}
