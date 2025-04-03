package devworks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import devworks.App;
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

    @FXML
    protected void initialize() {
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
