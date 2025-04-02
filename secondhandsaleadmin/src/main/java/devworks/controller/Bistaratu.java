package devworks.controller;

import java.io.IOException;
import java.util.List;

import devworks.App;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Bistaratu {
    @FXML
    protected void initialize() {
        // textAreaBete(false);

        fillChoiceBox();
    }

    @FXML
    private void fillChoiceBox() {
        // choiceBoxHerria.getItems().clear();
        // List<String> herriakIzenak = App.herriak.getAllProbintzia();
        // for (String Izenak : herriakIzenak) {
        // choiceBoxHerria.getItems().add(Izenak);
        // }
    }

    @FXML
    private void textAreaBete(boolean bilatu) {
        // List<Herria> herriak;
        // if (bilatu) {
        // herriak = App.herriak.getHerriakProbintzia(choiceBoxHerria.getValue());
        // } else {
        // choiceBoxHerria.setValue(null);
        // herriak = App.herriak.getHerrienZerrenda();
        // }
        //
        // ObservableList<Herria> herrienObservableLista =
        // FXCollections.observableArrayList(herriak);
        //
        // tableColumnHerria.prefWidthProperty().bind(tableViewHerriak.widthProperty().multiply(0.3));
        // tableColumnProbintzia.prefWidthProperty().bind(tableViewHerriak.widthProperty().multiply(0.3));
        // tableColumnHondartzak.prefWidthProperty().bind(tableViewHerriak.widthProperty().multiply(0.4));
        //
        // tableViewHerriak.setItems(herrienObservableLista);
        //
        // tableColumnHerria.setCellValueFactory(new PropertyValueFactory<Herria,
        // String>("HerriIzena")); // getHerriIzena
        // tableColumnProbintzia.setCellValueFactory(new PropertyValueFactory<Herria,
        // String>("Probintzia")); // getProbintzia
        // tableColumnHondartzak.setCellValueFactory(cellData -> {
        // Herria herria = cellData.getValue();
        //
        // // Verificar si el herria tiene hondartzak
        // KostakoHerria kostakoHerria =
        // App.hondartzak.getHondartzakHerriaBatek(herria.getHerriIzena());
        //
        // // Comprobar que no sea null y tenga playas
        // if (kostakoHerria != null && kostakoHerria.getHondartzak() != null
        // && kostakoHerria.getHondartzak().length > 0) {
        // return new SimpleStringProperty(String.join(", ",
        // kostakoHerria.getHondartzak()));
        // }
        //
        // return new SimpleStringProperty("NO");
        // });
        //
        // tableViewHerriak.setEditable(true);
        //
        // tableColumnProbintzia.setCellFactory(TextFieldTableCell.<Herria>forTableColumn());
        // tableColumnProbintzia.setOnEditCommit((TableColumn.CellEditEvent<Herria,
        // String> t) -> {
        // Herria herria = t.getRowValue();
        // String oldValue = herria.getProbintzia();
        // String newValue = t.getNewValue();
        //
        // if (!oldValue.equals(newValue)) {
        // herria.setProbintzia(newValue);
        //
        // int result = App.herriak.updateProbintzia(herria, herria.getHerriIzena());
        //
        // fillChoiceBox();
        //
        // if (result == -1) {
        // System.out.println("Error al actualizar la base de datos");
        // herria.setProbintzia(oldValue);
        // }
        // }
        // });
    }

    @FXML
    private void handleBilatu() {
        // textAreaBete(true);
    }

    @FXML
    private void handleClear() throws IOException {
        // textAreaBete(false);
    }

    @FXML
    private void handleTxertatu() throws IOException {
        App.setRoot("Txertatu");
    }

    @FXML
    private void handleAldatu() throws IOException {
        App.setRoot("Aldatu");
    }

    @FXML
    private void handleEzabatu() throws IOException {
        App.setRoot("Ezabatu");
    }

    @FXML
    private void handleIrten() throws IOException {
        App.setRoot("MenuBotoiak");
    }
}
