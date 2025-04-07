package devworks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import devworks.App;
import devworks.model.base.Bezeroak;
import devworks.model.base.Erosleak;
import devworks.model.base.Kategoria;
import devworks.model.base.Langileak;
import devworks.model.base.Produktuak;
import devworks.model.base.Salmentak;
import devworks.model.base.Saltzaileak;
import javafx.beans.property.SimpleDoubleProperty;
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

        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")
                || App.conectionIdentifier.equalsIgnoreCase("Langileak")) {

            List<String> herriak = new ArrayList<>();

            if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
                herriak = App.bezeroak.getAllHerriak();
            } else if (App.conectionIdentifier.equalsIgnoreCase("Langileak")) {
                herriak = App.langileak.getAllHerriak();
            }
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
        } else if (App.conectionIdentifier.equalsIgnoreCase("Salmentak")) {
            List<Saltzaileak> saltzaileak = App.salmentak.getAllSaltzaileak();
            ObservableList<Object> observableSaltzaileak = FXCollections.observableArrayList(saltzaileak);
            choiceBoxBilatu.setItems(observableSaltzaileak);

            choiceBoxBilatu.setConverter(new StringConverter<Object>() {
                @Override
                public String toString(Object obj) {
                    if (obj instanceof Saltzaileak) {
                        return ((Saltzaileak) obj).getEmail();
                    }
                    return "";
                }

                @Override
                public Object fromString(String string) {
                    return null;
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

            // COLUMNEN ZABALERA ----> GUZTIRA = 1
            tableView.widthProperty().addListener((obs, oldVal, newVal) -> {
                double totalWidth = newVal.doubleValue();

                columnId.setPrefWidth(totalWidth * 0.05);
                columnIzena.setPrefWidth(totalWidth * 0.2);
                columnDeskribapena.setPrefWidth(totalWidth * 0.2);
                columnPrezioa.setPrefWidth(totalWidth * 0.1);
                columnKategoria.setPrefWidth(totalWidth * 0.15);
                columnEgoera.setPrefWidth(totalWidth * 0.15);
                columnSaltzaile.setPrefWidth(totalWidth * 0.15);
            });

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

            // COLUMNEN ZABALERA ----> GUZTIRA = 1
            tableView.widthProperty().addListener((obs, oldVal, newVal) -> {
                double totalWidth = newVal.doubleValue();

                columnId.setPrefWidth(totalWidth * 0.05);
                columnIzena.setPrefWidth(totalWidth * 0.15);
                columnAltaData.setPrefWidth(totalWidth * 0.15);
                columnEmail.setPrefWidth(totalWidth * 0.15);
                columnHelbidea.setPrefWidth(totalWidth * 0.2);
                columnHerria.setPrefWidth(totalWidth * 0.1);
                columnPostaKodea.setPrefWidth(totalWidth * 0.1);
                columnTelefonoa.setPrefWidth(totalWidth * 0.1);

            });
        } else if (App.conectionIdentifier.equalsIgnoreCase("Langileak")) {

            TableColumn<Object, Integer> columnId = new TableColumn<>("ID");
            columnId.setCellValueFactory(cellData -> {
                Langileak l = (Langileak) cellData.getValue();
                return new SimpleIntegerProperty(l.getId()).asObject();
            });

            TableColumn<Object, String> columnIzena = new TableColumn<>("Izena");
            columnIzena.setCellValueFactory(cellData -> {
                Langileak l = (Langileak) cellData.getValue();
                return new SimpleStringProperty(l.getIzena());
            });

            TableColumn<Object, String> columnKargua = new TableColumn<>("Kargua");
            columnKargua.setCellValueFactory(cellData -> {
                Langileak l = (Langileak) cellData.getValue();
                return new SimpleStringProperty(l.getKargua());
            });

            TableColumn<Object, String> columnEmail = new TableColumn<>("Email");
            columnEmail.setCellValueFactory(cellData -> {
                Langileak l = (Langileak) cellData.getValue();
                return new SimpleStringProperty(l.getEmail());
            });

            TableColumn<Object, Integer> columnTelefonoa = new TableColumn<>("Telefonoa");
            columnTelefonoa.setCellValueFactory(cellData -> {
                Langileak l = (Langileak) cellData.getValue();
                return new SimpleIntegerProperty(l.getTelefonoa()).asObject();
            });

            TableColumn<Object, String> columnHerria = new TableColumn<>("Herria");
            columnHerria.setCellValueFactory(cellData -> {
                Langileak l = (Langileak) cellData.getValue();
                return new SimpleStringProperty(l.getHerriIzena());
            });

            TableColumn<Object, String> columnPostaKodea = new TableColumn<>("PostaKodea");
            columnPostaKodea.setCellValueFactory(cellData -> {
                Langileak l = (Langileak) cellData.getValue();
                return new SimpleStringProperty(l.getPostaKodea());
            });

            TableColumn<Object, String> columnHelbidea = new TableColumn<>("Helbidea");
            columnHelbidea.setCellValueFactory(cellData -> {
                Langileak l = (Langileak) cellData.getValue();
                return new SimpleStringProperty(l.getHelbidea());
            });

            TableColumn<Object, String> columnAltaData = new TableColumn<>("AltaData");
            columnAltaData.setCellValueFactory(cellData -> {
                Langileak l = (Langileak) cellData.getValue();
                return new SimpleStringProperty(l.getAltaData());
            });

            tableView.getColumns().addAll(
                    columnId, columnIzena, columnKargua, columnEmail,
                    columnTelefonoa, columnHerria, columnPostaKodea, columnHelbidea, columnAltaData);

            if (!bilatu) {
                List<Langileak> langileak = App.langileak.getLangileak();
                ObservableList<Object> observableList = FXCollections.observableArrayList(langileak);
                tableView.setItems(observableList);
            } else {
                String selectedHiria = (String) choiceBoxBilatu.getSelectionModel().getSelectedItem();
                if (selectedHiria != null) {
                    List<Langileak> langileakFiltrados = App.langileak.filterLangileak(selectedHiria);
                    ObservableList<Object> observableList = FXCollections.observableArrayList(langileakFiltrados);
                    tableView.setItems(observableList);
                }
            }
            // COLUMNEN ZABALERA ----> GUZTIRA = 1
            tableView.widthProperty().addListener((obs, oldVal, newVal) -> {
                double totalWidth = newVal.doubleValue();

                columnId.setPrefWidth(totalWidth * 0.05);
                columnIzena.setPrefWidth(totalWidth * 0.08);
                columnKargua.setPrefWidth(totalWidth * 0.1);
                columnAltaData.setPrefWidth(totalWidth * 0.15);
                columnEmail.setPrefWidth(totalWidth * 0.15);
                columnHelbidea.setPrefWidth(totalWidth * 0.2);
                columnHerria.setPrefWidth(totalWidth * 0.07);
                columnPostaKodea.setPrefWidth(totalWidth * 0.1);
                columnTelefonoa.setPrefWidth(totalWidth * 0.1);

            });
        } else if (App.conectionIdentifier.equalsIgnoreCase("Salmentak")) {
            TableColumn<Object, Integer> columnId = new TableColumn<>("ID");
            columnId.setCellValueFactory(cellData -> {
                Salmentak s = (Salmentak) cellData.getValue();
                return new SimpleIntegerProperty(s.getId()).asObject();
            });

            TableColumn<Object, Integer> columnIdProduktu = new TableColumn<>("ID Produktu");
            columnIdProduktu.setCellValueFactory(cellData -> {
                Salmentak s = (Salmentak) cellData.getValue();
                return new SimpleIntegerProperty(s.getIdProduktu()).asObject();
            });

            TableColumn<Object, String> columnIzenaProduktu = new TableColumn<>("Izena Produktu");
            columnIzenaProduktu.setCellValueFactory(cellData -> {
                Salmentak s = (Salmentak) cellData.getValue();
                return new SimpleStringProperty(s.getIzenaProduktu());
            });

            TableColumn<Object, Integer> columnIdSaltzaile = new TableColumn<>("ID Saltzaile");
            columnIdSaltzaile.setCellValueFactory(cellData -> {
                Salmentak s = (Salmentak) cellData.getValue();
                return new SimpleIntegerProperty(s.getIdSaltzaile()).asObject();
            });

            TableColumn<Object, String> columnEmailSaltzaile = new TableColumn<>("Email Saltzaile");
            columnEmailSaltzaile.setCellValueFactory(cellData -> {
                Salmentak s = (Salmentak) cellData.getValue();
                int idSaltzaile = s.getIdSaltzaile();
                String email = App.salmentak.getAllSaltzaileak().stream()
                        .filter(saltzaile -> saltzaile.getId() == idSaltzaile)
                        .map(Saltzaileak::getEmail)
                        .findFirst()
                        .orElse("Desconocido");
                return new SimpleStringProperty(email);
            });

            TableColumn<Object, Integer> columnIdErosle = new TableColumn<>("ID Erosle");
            columnIdErosle.setCellValueFactory(cellData -> {
                Salmentak s = (Salmentak) cellData.getValue();
                return new SimpleIntegerProperty(s.getIdErosle()).asObject();
            });

            TableColumn<Object, String> columnEmailErosleak = new TableColumn<>("Email Erosle");
            columnEmailErosleak.setCellValueFactory(cellData -> {
                Salmentak s = (Salmentak) cellData.getValue();
                int idErosle = s.getIdErosle();

                String email = App.salmentak.getAllErosleak().stream()
                        .filter(erosle -> erosle.getId() == idErosle)
                        .map(Erosleak::getEmail)
                        .findFirst()
                        .orElse("Desconocido");

                return new SimpleStringProperty(email);
            });

            TableColumn<Object, String> columnData = new TableColumn<>("Data");
            columnData.setCellValueFactory(cellData -> {
                Salmentak s = (Salmentak) cellData.getValue();
                return new SimpleStringProperty(s.getData().toString());
            });

            TableColumn<Object, Double> columnSalmentakPrezioa = new TableColumn<>("Salmenta Prezioa");
            columnSalmentakPrezioa.setCellValueFactory(cellData -> {
                Salmentak s = (Salmentak) cellData.getValue();
                return new SimpleDoubleProperty(s.getSalmentaPrezioa()).asObject();
            });

            tableView.getColumns().addAll(columnId, columnIdProduktu, columnIzenaProduktu,
                    columnIdSaltzaile, columnEmailSaltzaile, columnIdErosle, columnEmailErosleak, columnData,
                    columnSalmentakPrezioa);

            if (!bilatu) {
                List<Salmentak> salmentak = App.salmentak.getSalmentak();
                ObservableList<Object> observableList = FXCollections.observableArrayList(salmentak);
                tableView.setItems(observableList);
            } else {
                Saltzaileak selectedSaltzaile = (Saltzaileak) choiceBoxBilatu.getSelectionModel().getSelectedItem();
                if (selectedSaltzaile != null) {
                    int idSaltzaile = selectedSaltzaile.getId();
                    List<Salmentak> salmentakFiltrados = App.salmentak.filterSalmentak(idSaltzaile);
                    ObservableList<Object> observableList = FXCollections.observableArrayList(salmentakFiltrados);
                    tableView.setItems(observableList);
                }
            }

            // COLUMNEN ZABALERA ----> GUZTIRA = 1
            tableView.widthProperty().addListener((obs, oldVal, newVal) -> {
                double totalWidth = newVal.doubleValue();

                columnId.setPrefWidth(totalWidth * 0.06);
                columnIdProduktu.setPrefWidth(totalWidth * 0.1);
                columnIzenaProduktu.setPrefWidth(totalWidth * 0.16);
                columnIdSaltzaile.setPrefWidth(totalWidth * 0.1);
                columnEmailSaltzaile.setPrefWidth(totalWidth * 0.15);
                columnIdErosle.setPrefWidth(totalWidth * 0.1);
                columnEmailErosleak.setPrefWidth(totalWidth * 0.1);
                columnData.setPrefWidth(totalWidth * 0.1);
                columnSalmentakPrezioa.setPrefWidth(totalWidth * 0.13);

            });
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
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")
                || App.conectionIdentifier.equalsIgnoreCase("Langileak")) {
            String selectedHiria = (String) choiceBoxBilatu.getSelectionModel().getSelectedItem();
            if (selectedHiria != null) {
                textAreaBete(true);
            } else {
                textAreaBete(false);
            }
        } else if (App.conectionIdentifier.equalsIgnoreCase("Salmentak")) {
            Saltzaileak selectedSaltzaile = (Saltzaileak) choiceBoxBilatu.getSelectionModel().getSelectedItem();

            if (selectedSaltzaile != null) {
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
