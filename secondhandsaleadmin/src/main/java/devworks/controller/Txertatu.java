package devworks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import devworks.App;
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
        // String izena = txfIzena.getText();
        // String probintzia = txfProbintzia.getText();
        //
        // List<String> playas = new ArrayList<>();
        // if (!hond1.getText().isEmpty()) {
        // playas.add(hond1.getText());
        // }
        // if (!hond2.getText().isEmpty()) {
        // playas.add(hond2.getText());
        // }
        // if (!hond3.getText().isEmpty()) {
        // playas.add(hond3.getText());
        // }
        //
        // String[] kostakoString = playas.toArray(new String[0]);
        //
        // int herriaInsert = 0;
        //
        // //if (izena != null && !izena.isEmpty() && probintzia != null &&
        // !probintzia.isEmpty()) {
        // // try {
        // // if (checkboxSelected) {
        // // KostakoHerria herria = new KostakoHerria(izena, probintzia,
        // kostakoString);
        // // herriaInsert = App.herriak.insertKostakoHerria(herria);
        // // } else {
        // // Herria herria = new Herria(izena, probintzia);
        // // herriaInsert = App.herriak.insert(herria);
        // // }
        //
        // if (herriaInsert == 1) {
        // // System.out.println("Herria ondo gordeta");
        // } else if (herriaInsert == -1062) {
        // lbMezua.setText("Datu hori badago");
        // } else {
        // lbMezua.setText("Barne-errore bat gertatu da programan");
        // }
        // } catch (Exception e) {
        // lbMezua.setText("Errorea gertatu da datuak txertatzean");
        // }
        // } else {
        // lbMezua.setText("Izena eta probintzia beteta behar du");
        // }
        //
        // txfIzena.clear();
        // txfProbintzia.clear();
        // if (checkboxSelected) {
        // hond1.clear();
        // hond2.clear();
        // hond3.clear();
        // }
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
        App.setRoot("MenuBotoiak");
    }
}
