package devworks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import devworks.App;
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

    @FXML
    Label lbIzenburua;

    int aukeratuak = 0;

    List<String> deleteList = new ArrayList<String>();

    @FXML
    protected void initialize() {
        vBoxProduktuak.getChildren().clear();

        if (App.conectionIdentifier.equals("Bezeroak")) {
            lbIzenburua.setText(("Ezabatu nahi dituzun Bezeroak"));
            for (Bezeroak bezero : App.bezeroak.getBezeroak()) {
                CheckBox cbx = new CheckBox(bezero.getIzena());
                vBoxProduktuak.getChildren().add(cbx);

                cbx.setOnMouseClicked(event -> {
                    if (cbx.isSelected()) {
                        deleteList.add(bezero.getIzena());
                    }
                });
            }
        } else if (App.conectionIdentifier.equals("Langileak")) {
            lbIzenburua.setText(("Ezabatu nahi dituzun Langileak"));
            for (Langileak langile : App.langileak.getLangileak()) {
                CheckBox cbx = new CheckBox(langile.getIzena());
                vBoxProduktuak.getChildren().add(cbx);

                cbx.setOnMouseClicked(event -> {
                    if (cbx.isSelected()) {
                        deleteList.add(langile.getIzena());
                    }
                });
            }
        } else if (App.conectionIdentifier.equals("Produktuak")) {
            lbIzenburua.setText(("Ezabatu nahi dituzun Produktuak"));
            for (Produktuak produktu : App.produktuak.getProduktoak()) {
                CheckBox cbx = new CheckBox(produktu.getIzena());
                vBoxProduktuak.getChildren().add(cbx);

                cbx.setOnMouseClicked(event -> {
                    if (cbx.isSelected()) {
                        deleteList.add(produktu.getIzena());
                    }
                });
            }
        } else if (App.conectionIdentifier.equals("Salmentak")) {
            lbIzenburua.setText(("Ezabatu nahi dituzun Salmentak"));
            for (Salmentak salmentak : App.salmentak.getSalmentak()) {
                CheckBox cbx = new CheckBox(salmentak.getId() + " " + salmentak.getIzenaProduktu());
                vBoxProduktuak.getChildren().add(cbx);

                cbx.setOnMouseClicked(event -> {
                    if (cbx.isSelected()) {
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

        if (deleteList.isEmpty()) {
            lbMezua.setText("Ez duzu ezer hautatu");
            return;
        }

        switch (App.conectionIdentifier) {
            case "Bezeroak":
                for (String izena : deleteList) {
                    App.bezeroak.deleteBezeroa(izena);
                }
                break;
            case "Langileak":
                for (String izena : deleteList) {
                    App.langileak.deleteLangilea(izena);
                }
                break;
            case "Salmentak":
                for (String izena : deleteList) {
                    App.salmentak.deletesSalmentak(izena);
                }
                break;
            case "Produktuak":
                for (String izena : deleteList) {
                    App.produktuak.deleteProduktuak(izena);
                }
                break;
        }

        lbMezua.setText("Ezabatuta!!");

        deleteList.clear();
        initialize();

        // for (String delete : deleteList) {
        // if (App.herriak.herriaBaDago(delete)) {
        // App.herriak.herriaEzabatu(delete);
        // initialize();
        // }
        // }
    }

    @FXML
    void handleAtzera() throws IOException {
        App.setRoot("Bistaratu");
    }
}
