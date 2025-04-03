package devworks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import devworks.App;
import devworks.model.ProduktoAtzipena;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Ezabatu {
    @FXML
    VBox vBoxHerriak;

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
        

        // vBoxHerriak.getChildren().clear();
        //
        // for (String pueblo : App.herriak.getHerriIzenak()) {
        // CheckBox cbx = new CheckBox(pueblo);
        // vBoxHerriak.getChildren().add(cbx);
        // // Para añadir evento click al checkbox
        // cbx.setOnMouseClicked(event -> {
        // if (((CheckBox) event.getSource()).isSelected()) {
        // deleteList.add(pueblo);
        // }
        // });
        // }
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
