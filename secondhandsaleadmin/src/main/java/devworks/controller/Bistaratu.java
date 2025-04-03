package devworks.controller;

import java.io.IOException;
import java.util.List;

import devworks.App;
import devworks.model.base.Kategoria;
import devworks.model.base.Produktoak;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;

public class Bistaratu {
    @FXML
    private ChoiceBox<Kategoria> choiceBoxBilatu;

    @FXML
    private TableView<Produktoak> tableView;

    @FXML
    protected void initialize() {
        textAreaBete(false);

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

    @SuppressWarnings("unchecked")
    @FXML
    private void textAreaBete(boolean bilatu) {
        tableView.getColumns().clear();

        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            TableColumn<Produktoak, Integer> columnId = new TableColumn<>("ID");
            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Produktoak, String> columnIzena = new TableColumn<>("Izena");
            columnIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));

            TableColumn<Produktoak, String> columnDeskribapena = new TableColumn<>("Deskribapena");
            columnDeskribapena.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));

            TableColumn<Produktoak, Integer> columnPrezioa = new TableColumn<>("Prezioa");
            columnPrezioa.setCellValueFactory(new PropertyValueFactory<>("prezioa"));

            TableColumn<Produktoak, String> columnKategoria = new TableColumn<>("Kategoria");
            columnKategoria.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Produktoak, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<Produktoak, String> param) {
                            int idKategoria = param.getValue().getIdKategoria();
                            List<Kategoria> kategoriak = App.produktoak.getAllKategoriak();
                            String categoriaNombre = kategoriak.stream()
                                    .filter(kategoria -> kategoria.getId() == idKategoria)
                                    .map(Kategoria::getIzena)
                                    .findFirst()
                                    .orElse("Desconocida");
                            return new SimpleStringProperty(categoriaNombre);
                        }
                    });

            TableColumn<Produktoak, String> columnEgoera = new TableColumn<>("Egoera");
            columnEgoera.setCellValueFactory(new PropertyValueFactory<>("egoera"));

            TableColumn<Produktoak, String> columnSaltzaile = new TableColumn<>("Saltzaile");
            columnSaltzaile.setCellValueFactory(new PropertyValueFactory<>("email"));

            // Add the tableview columns
            tableView.getColumns().addAll(columnId, columnIzena, columnDeskribapena, columnPrezioa, columnKategoria,
                    columnEgoera, columnSaltzaile);

            if (!bilatu) {
                List<Produktoak> produktuak = App.produktoak.getProduktoak(); // Obtener todos los productos
                ObservableList<Produktoak> observableList = FXCollections.observableArrayList(produktuak);
                tableView.setItems(observableList);
            } else {
                Kategoria selectedKategoria = choiceBoxBilatu.getSelectionModel().getSelectedItem();
                if (selectedKategoria != null) {
                    int idKategoria = selectedKategoria.getId();
                    List<Produktoak> produktuakFiltrados = App.produktoak.searchProduktoa(idKategoria);
                    ObservableList<Produktoak> observableList = FXCollections.observableArrayList(produktuakFiltrados);
                    tableView.setItems(observableList);
                }
            }
        }
    }

    @FXML
    private void handleBilatu() {
        Kategoria selectedKategoria = choiceBoxBilatu.getSelectionModel().getSelectedItem();

        if (selectedKategoria == null) {
            textAreaBete(false);
        } else {
            textAreaBete(true);
        }
    }

    @FXML
    private void handleClear() throws IOException {
        choiceBoxBilatu.getSelectionModel().clearSelection();
        textAreaBete(false);
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
