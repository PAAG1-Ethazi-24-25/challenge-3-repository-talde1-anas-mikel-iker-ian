package devworks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import devworks.App;
import devworks.model.base.Kategoria;
import devworks.model.base.Produktuak;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Txertatu {
    @FXML
    VBox vBoxHerriarenDatuak;

    @FXML
    TextField txfIzena;

    @FXML
    TextField txfProbintzia;

    @FXML
    CheckBox chkKostakoa;

    @FXML
    Label lbMezua;

    protected TextArea hond1 = new TextArea();
    protected TextArea hond2 = new TextArea();
    protected TextArea hond3 = new TextArea();
    private boolean checkboxSelected = false;

    @FXML
    protected void initialize() {
        
    }

    @FXML
    public void handleTxertatu() throws IOException {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            List<Kategoria> produktuak = App.produktuak.getAllKategoriak();
            
        }
    }

    @FXML
    void handleKostakoa() throws IOException {
        // chkKostakoa.setOnMouseClicked(event -> {
        // if (((CheckBox) event.getSource()).isSelected()) {
        // vBoxHerriarenDatuak.getChildren().add(hond1);
        // vBoxHerriarenDatuak.getChildren().add(hond2);
        // vBoxHerriarenDatuak.getChildren().add(hond3);
        // checkboxSelected = true;
        // } else {
        // vBoxHerriarenDatuak.getChildren().remove(hond1);
        // vBoxHerriarenDatuak.getChildren().remove(hond2);
        // vBoxHerriarenDatuak.getChildren().remove(hond3);
        // checkboxSelected = false;
        // }
        // });
    }

    @FXML
    void handleAtzera() throws IOException {
        App.conectionIdentifier = "MenuBotoiak";
        App.setRoot("MenuBotoiak");
    }
}
