package devworks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import devworks.App;
import devworks.model.ProduktoAtzipena;
import devworks.model.base.Bezeroak;
import devworks.model.base.Langileak;
import devworks.model.base.Produktuak;
import devworks.model.base.Salmentak;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Ezabatu {
    @FXML
    VBox vBoxProduktuak;

    @FXML
    Label lbMezua;

    int aukeratuak = 0;

    List<String> deleteList = new ArrayList<String>();

    /** Herri bakoitzeko checkbox bat sortuko da. */
    private ProduktoAtzipena produktuak;
    private ProduktoAtzipena bezeroak;
    private ProduktoAtzipena langileak;
    private ProduktoAtzipena salmentak;

    @FXML
    protected void initialize() {

        produktuak = new ProduktoAtzipena("localhost", "db_bigarreneskukomerkatua", "produktuak", "root", "");
        bezeroak = new ProduktoAtzipena("localhost", "db_bigarreneskukomerkatua", "bezeroak", "root", "");
        langileak = new ProduktoAtzipena("localhost", "db_bigarreneskukomerkatua", "langileak", "root", "");
        salmentak = new ProduktoAtzipena("localhost", "db_bigarreneskukomerkatua", "salmentak", "root", "");

        vBoxProduktuak.getChildren().clear();

        if (App.conectionIdentifier == "Bezeroak") {
            for (Bezeroak bezero : App.bezeroak.getBezeroak()) {
                CheckBox cbx = new CheckBox(bezero.getIzena());
                vBoxProduktuak.getChildren().add(cbx);
                // Para añadir evento click al checkbox
                cbx.setOnMouseClicked(event -> {
                    if (((CheckBox) event.getSource()).isSelected()) {
                        deleteList.add(bezero.getIzena());
                    }
                });
            }
        } else if (App.conectionIdentifier == "Langileak") {
            for (Langileak langile : App.langileak.getLangileak()) {
                CheckBox cbx = new CheckBox(langile.getIzena());
                vBoxProduktuak.getChildren().add(cbx);
                // Para añadir evento click al checkbox
                cbx.setOnMouseClicked(event -> {
                    if (((CheckBox) event.getSource()).isSelected()) {
                        deleteList.add(langile.getIzena());
                    }
                });
            }
        } else if (App.conectionIdentifier == "Produktuak") {
            for (Produktuak produktu : App.produktuak.getProduktoak()) {
                CheckBox cbx = new CheckBox(produktu.getIzena());
                vBoxProduktuak.getChildren().add(cbx);
                // Para añadir evento click al checkbox
                cbx.setOnMouseClicked(event -> {
                    if (((CheckBox)event.getSource()).isSelected()) {
                        deleteList.add(produktu.getIzena());
                    }
                });
            }
        } else if (App.conectionIdentifier == "Salmentak") {
            for (Salmentak salmentak : App.salmentak.getSalmentak()) {
                CheckBox cbx = new CheckBox( salmentak.getId() + " " +salmentak.getIzenaProduktu());
                vBoxProduktuak.getChildren().add(cbx);

                cbx.setOnMouseClicked (event -> {
                    if (((CheckBox) event.getSource()).isSelected()) {
                        deleteList.add(salmentak.getId() + " " + salmentak.getIzenaProduktu());
                    }
                });
            }
        }

    }

    /**
     * Hautatutako herriak ezabatuko dira zerrendatik.
     * Zure esku egiaztapenak egitea.
     */
    @FXML
    void handleEzabatu() throws IOException {
        // for (String delete : deleteList) {
        // if (App.herriak.herriaBaDago(delete)) {
        // App.herriak.herriaEzabatu(delete);
        // initialize();
        // }
        // }
    }

    @FXML
    void handleAtzera() throws IOException {
        App.conectionIdentifier = "MenuBotoiak";
        App.setRoot("MenuBotoiak");
    }
}
