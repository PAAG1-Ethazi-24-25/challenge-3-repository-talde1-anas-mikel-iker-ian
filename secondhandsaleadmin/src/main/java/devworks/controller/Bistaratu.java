package devworks.controller;

import java.io.IOException;
import java.util.List;

import devworks.App;
import devworks.model.base.Kategoria;
import devworks.model.base.Produktoak;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Bistaratu {
    ChoiceBox<Kategoria> choiceBoxBilatu;

    @FXML
    private TableView<Produktoak> tableView;

    @FXML
    private TableColumn<Produktoak, Integer> tableColumnId;

    @FXML
    private TableColumn<Produktoak, String> tableColumnIzena;

    @FXML
    private TableColumn<Produktoak, String> tableColumnDeskribapena;

    @FXML
    private TableColumn<Produktoak, Integer> tableColumnPrezioa;

    @FXML
    private TableColumn<Produktoak, Integer> tableColumnKategoria;

    @FXML
    private TableColumn<Produktoak, String> tableColumnEgoera;

    @FXML
    private TableColumn<Produktoak, Integer> tableColumnSaltzaile;

    @FXML
    protected void initialize() {
        // textAreaBete(false);

        fillChoiceBox();
    }

    @FXML
    private void fillChoiceBox() {
        choiceBoxBilatu.getItems().clear();

        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            List<Kategoria> kategoriak = App.produktoak.getAllKategoriak();

            ObservableList<Kategoria> observableKategoriak = FXCollections.observableArrayList(kategoriak);

            choiceBoxBilatu.setItems(observableKategoriak);

            choiceBoxBilatu.setConverter(new StringConverter<Kategoria>() {
                @Override
                public String toString(Kategoria kategoria) {
                    return (kategoria != null) ? kategoria.getIzena() : "";
                }

                @Override
                public Kategoria fromString(String string) {
                    return null;
                }
            });
        }

    }

    @FXML
    private void textAreaBete(boolean bilatu) {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
                // Obtener lista de productos
                List<Produktoak> produktuak = App.produktoak.getProduktoak();

                // Convertir a ObservableList
                ObservableList<Produktoak> observableList = FXCollections.observableArrayList(produktuak);

                // Configurar columnas con los atributos de Produktoak
                tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
                tableColumnIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
                tableColumnDeskribapena.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));
                tableColumnPrezioa.setCellValueFactory(new PropertyValueFactory<>("prezioa"));
                tableColumnKategoria.setCellValueFactory(new PropertyValueFactory<>("id_kategoria"));
                tableColumnEgoera.setCellValueFactory(new PropertyValueFactory<>("egoera"));
                tableColumnSaltzaile.setCellValueFactory(new PropertyValueFactory<>("id_saltzaile"));

                // Agregar los datos al TableView
                tableView.setItems(observableList);
            }
        }
    }

    @FXML
    private void handleBilatu() {
        Kategoria selectedKategoria = choiceBoxBilatu.getSelectionModel().getSelectedItem();
        if (selectedKategoria != null) {
            int idKategoria = selectedKategoria.getId();
            System.out.println("ID seleccionado: " + idKategoria);
        }
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
        App.conectionIdentifier = "MenuBotoiak";
        App.setRoot("MenuBotoiak");
    }
}
