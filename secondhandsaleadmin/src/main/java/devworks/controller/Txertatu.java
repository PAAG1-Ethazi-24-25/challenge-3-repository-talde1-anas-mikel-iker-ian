package devworks.controller;

import java.io.IOException;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;

import devworks.App;
import devworks.model.base.Kategoria;
import devworks.model.base.Langileak;
import devworks.model.base.Produktuak;
import devworks.model.base.Bezeroak;
import devworks.model.base.Saltzaileak;
import devworks.model.base.enumeradoreak.KarguakEnum;
import devworks.model.base.Salmentak;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Txertatu {

    @FXML
    VBox vBoxDatuak;

    @FXML
    VBox vBoxDatuak2;

    TextField izena = new TextField();
    TextField email = new TextField();
    TextField telefonoa = new TextField();
    TextField helbidea = new TextField();
    TextField herria = new TextField();
    TextField pk = new TextField();
    TextField erabiltzailea = new TextField();
    TextField pasahitza = new TextField();
    TextField deskribapena = new TextField();
    TextField prezioa = new TextField();
    ChoiceBox<Kategoria> kategoriacbx = new ChoiceBox<>();
    ChoiceBox<String> egoera = new ChoiceBox<>();
    ChoiceBox<KarguakEnum> kargua = new ChoiceBox<>();
    ChoiceBox<Saltzaileak> saltzaileacbx = new ChoiceBox<>();
    ChoiceBox<Produktuak> produktuakcbx = new ChoiceBox<>();

    Label mezua = new Label();

    List<Kategoria> ListKategoriak = App.produktuak.getAllKategoriak();
    List<Saltzaileak> listSaltzaileak = App.produktuak.getAllSaltzaileak();
    List<Produktuak> listProduktuak = App.produktuak.getProduktoak();

    @FXML
    protected void initialize() {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {

            kategoriacbx.getItems().addAll(ListKategoriak);
            egoera.getItems().addAll("berria", "erabilia", "hondatua");
            saltzaileacbx.getItems().addAll(listSaltzaileak);

            vBoxDatuak.getChildren().addAll(new Label("Izena:"), izena,
                    new Label("deskribapena:"), deskribapena,
                    new Label("prezioa:"), prezioa,
                    mezua);

            vBoxDatuak2.getChildren().addAll(new Label("kategoria:"), kategoriacbx,
                    new Label("egoera:"), egoera,
                    new Label("saltzailea:"), saltzaileacbx);

        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {

            vBoxDatuak.getChildren().addAll(new Label("Izena:"), izena,
                    new Label("email:"), email,
                    new Label("telefonoa:"), telefonoa,
                    new Label("helbidea:"), helbidea,
                    mezua);

            vBoxDatuak2.getChildren().addAll(new Label("herria:"), herria,
                    new Label("posta kodea:"), pk,
                    new Label("erabiltzaile izena:"), erabiltzailea,
                    new Label("pasahitza:"), pasahitza);

        } else if (App.conectionIdentifier.equalsIgnoreCase("Salmentak")) {

            produktuakcbx.getItems().addAll(listProduktuak);
            saltzaileacbx.getItems().addAll(listSaltzaileak);

            vBoxDatuak.getChildren().addAll(new Label("Produktua:"), produktuakcbx, new Label("eroslea:"),
                    saltzaileacbx,
                    mezua);

            vBoxDatuak2.getChildren().addAll(new Label("Salmenta prezioa:"), prezioa);

        } else if (App.conectionIdentifier.equalsIgnoreCase("Langileak")) {

            kargua.getItems().addAll(KarguakEnum.values());

            vBoxDatuak.getChildren().addAll(new Label("Izena:"), izena,
                    new Label("Kargua:"), kargua,
                    new Label("email:"), email,
                    new Label("telefonoa:"), telefonoa,
                    new Label("helbidea:"), helbidea,
                    mezua);

            vBoxDatuak2.getChildren().addAll(new Label("herria:"), herria,
                    new Label("posta kodea:"), pk,
                    new Label("erabiltzaile izena:"), erabiltzailea,
                    new Label("pasahitza:"), pasahitza);

        }
    }

    @FXML
    public void handleTxertatu() throws IOException {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            if (izena == null || deskribapena == null || prezioa == null || kategoriacbx.getValue() == null
                    || egoera.getValue() == null || saltzaileacbx.getValue() == null) {
                mezua.setText("Balio batzuk sartu gabe dituzu");
            } else {

                // Verificar si alguno de los campos contiene posibles inyecciones de código
                String[] inputs = {
                        izena.getText(),
                        deskribapena.getText(),
                        prezioa.getText(),
                        kategoriacbx.getValue().toString(),
                        saltzaileacbx.getValue().toString(),
                        egoera.getValue()
                };

                boolean containsInjection = false;
                String[] patterns = { "<script>", "</script>", "javascript:", "onerror", "onload", "eval(", "alert(" };

                for (String input : inputs) {
                    if (input != null) {
                        // Verificar si el campo contiene alguno de los patrones sospechosos
                        for (String pattern : patterns) {
                            if (input.toLowerCase().contains(pattern)) {
                                containsInjection = true;
                                break;
                            }
                        }
                    }
                    if (containsInjection)
                        break;
                }

                if (containsInjection) {
                    mezua.setText("Ez dira onartzen etiketak edo script-ak");
                } else {
                    Kategoria kategoria = kategoriacbx.getValue();
                    Saltzaileak saltzailea = saltzaileacbx.getValue();

                    // Crear el producto
                    Produktuak produktua = new Produktuak(0, izena.getText(), deskribapena.getText(),
                            Integer.parseInt(prezioa.getText()), kategoria.getId(), saltzailea.getId(),
                            egoera.getValue(),
                            null);

                    App.produktuak.produktuaTxertatu(produktua);

                    mezua.setText("Erregistroa ondo txertatu da");

                    clearFields(vBoxDatuak);
                    clearFields(vBoxDatuak2);
                }
            }
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            if (izena == null || email == null || telefonoa.getText().isEmpty() || helbidea == null || herria == null
                    || pk == null || erabiltzailea == null || pasahitza == null) {

                mezua.setText("Balio batzuk sartu gabe dituzu");
            } else {
                // Verificar si alguno de los campos contiene posibles inyecciones de código
                String[] inputs = {
                        izena.getText(),
                        email.getText(),
                        telefonoa.getText(),
                        helbidea.getText(),
                        herria.getText(),
                        pk.getText(),
                        erabiltzailea.getText(),
                        pasahitza.getText()
                };

                boolean containsInjection = false;
                String[] patterns = { "<script>", "</script>", "javascript:", "onerror", "onload", "eval(", "alert(" };

                for (String input : inputs) {
                    if (input != null) {
                        // Verificar si el campo contiene alguno de los patrones sospechosos
                        for (String pattern : patterns) {
                            if (input.toLowerCase().contains(pattern)) {
                                containsInjection = true;
                                break;
                            }
                        }
                    }
                    if (containsInjection)
                        break;
                }

                if (containsInjection) {
                    mezua.setText("Ez dira onartzen etiketak edo script-ak");
                } else {
                    // Verificar si el correo electrónico o el nombre de usuario ya están en la base
                    // de datos
                    boolean isEmailDuplicate = App.bezeroak.isEmailDuplicate(email.getText());
                    boolean isUsernameDuplicate = App.bezeroak.isUsernameDuplicate(erabiltzailea.getText());

                    if (isEmailDuplicate) {
                        mezua.setText("Email hau dagoeneko erregistratuta dago");
                    } else if (isUsernameDuplicate) {
                        mezua.setText("Erabiltzaile izen hau dagoeneko erregistratuta dago");
                    } else {
                        // Crear el cliente
                        Bezeroak bezeroa = new Bezeroak(0, izena.getText(), email.getText(),
                                Integer.parseInt(telefonoa.getText()), herria.getText(), pk.getText(),
                                helbidea.getText(),
                                "01/01/01", erabiltzailea.getText(), pasahitza.getText());

                        App.bezeroak.bezeroaTxertatu(bezeroa);

                        mezua.setText("Erregistroa ondo txertatu da");

                        clearFields(vBoxDatuak);
                        clearFields(vBoxDatuak2);
                    }
                }
            }
        } else if (App.conectionIdentifier.equalsIgnoreCase("Salmentak")) {
            if (produktuakcbx.getValue() == null || saltzaileacbx.getValue() == null || prezioa == null) {

                mezua.setText("Balio batzuk sartu gabe dituzu");
            } else {

                // Verificar si alguno de los campos contiene posibles inyecciones de código
                String[] inputs = {
                        produktuakcbx.getValue().toString(),
                        saltzaileacbx.getValue().toString(),
                        prezioa.getText()
                };

                boolean containsInjection = false;
                String[] patterns = { "<script>", "</script>", "javascript:", "onerror", "onload", "eval(", "alert(" };

                for (String input : inputs) {
                    if (input != null) {
                        // Verificar si el campo contiene alguno de los patrones sospechosos
                        for (String pattern : patterns) {
                            if (input.toLowerCase().contains(pattern)) {
                                containsInjection = true;
                                break;
                            }
                        }
                    }
                    if (containsInjection)
                        break;
                }

                if (containsInjection) {
                    mezua.setText("Ez dira onartzen etiketak edo script-ak");
                } else {
                    Produktuak produktua = produktuakcbx.getValue();
                    Saltzaileak saltzailea = saltzaileacbx.getValue();

                    // Crear la venta
                    Salmentak salmenta = new Salmentak(0, produktua.getId(), produktua.getIdSaltzaile(),
                            saltzailea.getId(), "01/01/2000", Double.parseDouble(prezioa.getText()),
                            produktua.getIzena());

                    App.salmentak.salmentaTxertatu(salmenta);

                    mezua.setText("Erregistroa ondo txertatu da");

                    clearFields(vBoxDatuak);
                    clearFields(vBoxDatuak2);
                }
            }
        } else if (App.conectionIdentifier.equalsIgnoreCase("Langileak")) {
            if (izena == null || kargua == null || email == null || telefonoa.getText().isEmpty() || helbidea == null
                    || herria == null || pk == null || erabiltzailea == null || pasahitza == null) {

                mezua.setText("Balio batzuk sartu gabe dituzu");
            } else {

                // Verificar si alguno de los campos contiene posibles inyecciones de código
                String[] inputs = {
                        izena.getText(),
                        kargua.getValue().getIzena(),
                        email.getText(),
                        telefonoa.getText(),
                        helbidea.getText(),
                        herria.getText(),
                        pk.getText(),
                        erabiltzailea.getText(),
                        pasahitza.getText()
                };

                boolean containsInjection = false;
                String[] patterns = { "<script>", "</script>", "javascript:", "onerror", "onload", "eval(", "alert(" };

                for (String input : inputs) {
                    if (input != null) {
                        // Verificar si el campo contiene alguno de los patrones sospechosos
                        for (String pattern : patterns) {
                            if (input.toLowerCase().contains(pattern)) {
                                containsInjection = true;
                                break;
                            }
                        }
                    }
                    if (containsInjection)
                        break;
                }

                if (containsInjection) {
                    mezua.setText("Ez dira onartzen etiketak edo script-ak");
                } else {

                    // Verificar si el email o el nombre de usuario están duplicados
                    if (App.langileak.isEmailDuplicate(email.getText())) {
                        mezua.setText("Email hau dagoeneko erabiltzen da.");
                    } else if (App.langileak.isUsernameDuplicate(erabiltzailea.getText())) {
                        mezua.setText("Erabiltzailea dagoeneko existitzen da.");
                    } else {
                        // Crear el trabajador
                        Langileak langilea = new Langileak(0, izena.getText(), kargua.getValue().getIzena(),
                                email.getText(),
                                Integer.parseInt(telefonoa.getText()), herria.getText(), pk.getText(),
                                helbidea.getText(),
                                "01/01/01", erabiltzailea.getText(), pasahitza.getText());

                        App.langileak.langileaTxertatu(langilea);

                        mezua.setText("Erregistroa ondo txertatu da");

                        clearFields(vBoxDatuak);
                        clearFields(vBoxDatuak2);
                    }
                }
            }
        }
    }

    public void clearFields(Pane pane) {
        for (Node node : pane.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).clear();
            } else if (node instanceof ChoiceBox) {
                ((ChoiceBox<?>) node).getSelectionModel().clearSelection();
            } else if (node instanceof Pane) {
                clearFields((Pane) node); // recursivo para contenedores anidados
            }
        }

        for (Node node : pane.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).clear();
            } else if (node instanceof Pane) {
                clearFields((Pane) node);
            }
        }
    }

    @FXML
    void handleAtzera() throws IOException {
        App.setRoot("Bistaratu");
    }
}
