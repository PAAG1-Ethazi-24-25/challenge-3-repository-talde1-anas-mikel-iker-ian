package devworks.controller;

import java.io.IOException;
import java.util.List;

import devworks.App;
import devworks.model.base.Bezeroak;
import devworks.model.base.Kategoria;
import devworks.model.base.Produktuak;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Bistaratu {
    @FXML
    private ChoiceBox<Object> choiceBoxBilatu;

    @FXML
    private TableView<Object> tableView;

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
            ObservableList<Object> observableKategoriak = FXCollections.observableArrayList(kategoriak);
            choiceBoxBilatu.setItems(observableKategoriak);

            choiceBoxBilatu.setConverter(new StringConverter<Object>() {
                @Override
                public String toString(Object obj) {
                    if (obj instanceof Kategoria) {
                        return ((Kategoria) obj).getIzena();
                    }
                    return "";
                }

                @Override
                public Object fromString(String string) {
                    return null;
                }
            });

        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            List<String> herriak = App.bezeroak.getAllHerriak();
            ObservableList<Object> observableHerriak = FXCollections.observableArrayList(herriak);
            choiceBoxBilatu.setItems(observableHerriak);

            choiceBoxBilatu.setConverter(new StringConverter<Object>() {
                @Override
                public String toString(Object obj) {
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    return "";
                }

                @Override
                public Object fromString(String string) {
                    return string;
                }
            });
        }

    }

    @SuppressWarnings("unchecked")
    @FXML
    private void textAreaBete(boolean bilatu) {
        tableView.getColumns().clear();

        tableView.getColumns().clear(); // Limpia las columnas antes de cargar nuevas

        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {

            TableColumn<Object, Integer> columnId = new TableColumn<>("ID");
            columnId.setCellValueFactory(cellData -> {
                Produktuak p = (Produktuak) cellData.getValue();
                return new SimpleIntegerProperty(p.getId()).asObject();
            });

            TableColumn<Object, String> columnIzena = new TableColumn<>("Izena");
            columnIzena.setCellValueFactory(cellData -> {
                Produktuak p = (Produktuak) cellData.getValue();
                return new SimpleStringProperty(p.getIzena());
            });

            TableColumn<Object, String> columnDeskribapena = new TableColumn<>("Deskribapena");
            columnDeskribapena.setCellValueFactory(cellData -> {
                Produktuak p = (Produktuak) cellData.getValue();
                return new SimpleStringProperty(p.getDeskribapena());
            });

            TableColumn<Object, Integer> columnPrezioa = new TableColumn<>("Prezioa");
            columnPrezioa.setCellValueFactory(cellData -> {
                Produktuak p = (Produktuak) cellData.getValue();
                return new SimpleIntegerProperty(p.getPrezioa()).asObject();
            });

            TableColumn<Object, String> columnKategoria = new TableColumn<>("Kategoria");
            columnKategoria.setCellValueFactory(cellData -> {
                Produktuak p = (Produktuak) cellData.getValue();
                int idKategoria = p.getIdKategoria();
                String izena = App.produktuak.getAllKategoriak().stream()
                        .filter(k -> k.getId() == idKategoria)
                        .map(Kategoria::getIzena)
                        .findFirst()
                        .orElse("Desconocida");
                return new SimpleStringProperty(izena);
            });

            TableColumn<Object, String> columnEgoera = new TableColumn<>("Egoera");
            columnEgoera.setCellValueFactory(cellData -> {
                Produktuak p = (Produktuak) cellData.getValue();
                return new SimpleStringProperty(p.getEgoera());
            });

            TableColumn<Object, String> columnSaltzaile = new TableColumn<>("Saltzaile");
            columnSaltzaile.setCellValueFactory(cellData -> {
                Produktuak p = (Produktuak) cellData.getValue();
                return new SimpleStringProperty(p.getEmail());
            });

            tableView.getColumns().addAll(
                    columnId, columnIzena, columnDeskribapena,
                    columnPrezioa, columnKategoria, columnEgoera, columnSaltzaile);

            if (!bilatu) {
                List<Produktuak> produktuak = App.produktuak.getProduktoak();
                ObservableList<Object> observableList = FXCollections.observableArrayList(produktuak);
                tableView.setItems(observableList);
            } else {
                Kategoria selectedKategoria = (Kategoria) choiceBoxBilatu.getSelectionModel().getSelectedItem();
                if (selectedKategoria != null) {
                    int idKategoria = selectedKategoria.getId();
                    List<Produktuak> produktuakFiltrados = App.produktuak.filterProduktoa(idKategoria);
                    ObservableList<Object> observableList = FXCollections.observableArrayList(produktuakFiltrados);
                    tableView.setItems(observableList);
                }
            }

        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {

            TableColumn<Object, Integer> columnId = new TableColumn<>("ID");
            columnId.setCellValueFactory(cellData -> {
                Bezeroak b = (Bezeroak) cellData.getValue();
                return new SimpleIntegerProperty(b.getId()).asObject();
            });

            TableColumn<Object, String> columnIzena = new TableColumn<>("Izena");
            columnIzena.setCellValueFactory(cellData -> {
                Bezeroak b = (Bezeroak) cellData.getValue();
                return new SimpleStringProperty(b.getIzena());
            });

            TableColumn<Object, String> columnEmail = new TableColumn<>("Email");
            columnEmail.setCellValueFactory(cellData -> {
                Bezeroak b = (Bezeroak) cellData.getValue();
                return new SimpleStringProperty(b.getEmail());
            });

            TableColumn<Object, Integer> columnTelefonoa = new TableColumn<>("Telefonoa");
            columnTelefonoa.setCellValueFactory(cellData -> {
                Bezeroak b = (Bezeroak) cellData.getValue();
                return new SimpleIntegerProperty(b.getTelefonoa()).asObject();
            });

            TableColumn<Object, String> columnHerria = new TableColumn<>("Herria");
            columnHerria.setCellValueFactory(cellData -> {
                Bezeroak b = (Bezeroak) cellData.getValue();
                return new SimpleStringProperty(b.getHerriIzena());
            });

            TableColumn<Object, String> columnPostaKodea = new TableColumn<>("PostaKodea");
            columnPostaKodea.setCellValueFactory(cellData -> {
                Bezeroak b = (Bezeroak) cellData.getValue();
                return new SimpleStringProperty(b.getPostaKodea());
            });

            TableColumn<Object, String> columnHelbidea = new TableColumn<>("Helbidea");
            columnHelbidea.setCellValueFactory(cellData -> {
                Bezeroak b = (Bezeroak) cellData.getValue();
                return new SimpleStringProperty(b.getHelbidea());
            });

            TableColumn<Object, String> columnAltaData = new TableColumn<>("AltaData");
            columnAltaData.setCellValueFactory(cellData -> {
                Bezeroak b = (Bezeroak) cellData.getValue();
                return new SimpleStringProperty(b.getAltaData());
            });

            tableView.getColumns().addAll(
                    columnId, columnIzena, columnEmail, columnTelefonoa,
                    columnHerria, columnPostaKodea, columnHelbidea, columnAltaData);

            if (!bilatu) {
                List<Bezeroak> bezeroak = App.bezeroak.getBezeroak();
                ObservableList<Object> observableList = FXCollections.observableArrayList(bezeroak);
                tableView.setItems(observableList);
            } else {
                String selectedHiria = (String) choiceBoxBilatu.getSelectionModel().getSelectedItem();
                if (selectedHiria != null) {
                    List<Bezeroak> bezeroakFiltrados = App.bezeroak.filterBezeroak(selectedHiria);
                    ObservableList<Object> observableList = FXCollections.observableArrayList(bezeroakFiltrados);
                    tableView.setItems(observableList);
                }
            }
        }

    }

    @FXML
    private void handleBilatu() {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            Kategoria selectedKategoria = (Kategoria) choiceBoxBilatu.getSelectionModel().getSelectedItem();

            if (selectedKategoria == null) {
                textAreaBete(false);
            } else {
                textAreaBete(true);
            }
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            String selectedHiria = (String) choiceBoxBilatu.getSelectionModel().getSelectedItem();
            if (selectedHiria != null) {
                textAreaBete(true);
            } else {
                textAreaBete(false);
            }
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
