package devworks.controller;

import java.io.IOException;
import java.util.List;

import devworks.App;
import devworks.model.base.Kategoria;
import devworks.model.base.Produktuak;
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
    private TableView<Produktuak> tableView;

    @FXML
    protected void initialize() {
        textAreaBete(false);

        fillChoiceBox();
    }

    @FXML
    private void fillChoiceBox() {
        choiceBoxBilatu.getItems().clear();

        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            List<Kategoria> kategoriak = App.produktuak.getAllKategoriak();

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
            TableColumn<Produktuak, Integer> columnId = new TableColumn<>("ID");
            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Produktuak, String> columnIzena = new TableColumn<>("Izena");
            columnIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));

            TableColumn<Produktuak, String> columnDeskribapena = new TableColumn<>("Deskribapena");
            columnDeskribapena.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));

            TableColumn<Produktuak, Integer> columnPrezioa = new TableColumn<>("Prezioa");
            columnPrezioa.setCellValueFactory(new PropertyValueFactory<>("prezioa"));

            TableColumn<Produktuak, String> columnKategoria = new TableColumn<>("Kategoria");
            columnKategoria.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Produktuak, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<Produktuak, String> param) {
                            int idKategoria = param.getValue().getIdKategoria();
                            List<Kategoria> kategoriak = App.produktuak.getAllKategoriak();
                            String categoriaNombre = kategoriak.stream()
                                    .filter(kategoria -> kategoria.getId() == idKategoria)
                                    .map(Kategoria::getIzena)
                                    .findFirst()
                                    .orElse("Desconocida");
                            return new SimpleStringProperty(categoriaNombre);
                        }
                    });

            TableColumn<Produktuak, String> columnEgoera = new TableColumn<>("Egoera");
            columnEgoera.setCellValueFactory(new PropertyValueFactory<>("egoera"));

            TableColumn<Produktuak, String> columnSaltzaile = new TableColumn<>("Saltzaile");
            columnSaltzaile.setCellValueFactory(new PropertyValueFactory<>("email"));

            // Add the tableview columns
            tableView.getColumns().addAll(columnId, columnIzena, columnDeskribapena, columnPrezioa, columnKategoria,
                    columnEgoera, columnSaltzaile);

            if (!bilatu) {
                List<Produktuak> produktuak = App.produktuak.getProduktoak(); // Obtener todos los productos
                ObservableList<Produktuak> observableList = FXCollections.observableArrayList(produktuak);
                tableView.setItems(observableList);
            } else {
                Kategoria selectedKategoria = choiceBoxBilatu.getSelectionModel().getSelectedItem();
                if (selectedKategoria != null) {
                    int idKategoria = selectedKategoria.getId();
                    List<Produktuak> produktuakFiltrados = App.produktuak.searchProduktoa(idKategoria);
                    ObservableList<Produktuak> observableList = FXCollections.observableArrayList(produktuakFiltrados);
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
