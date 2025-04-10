package devworks.controller;

import java.io.IOException;
import java.util.List;

import devworks.App;
import devworks.model.base.Bezeroak;
import devworks.model.base.Erosleak;
import devworks.model.base.Kategoria;
import devworks.model.base.Langileak;
import devworks.model.base.Produktuak;
import devworks.model.base.Saltzaileak;
import devworks.model.base.enumeradoreak.KarguakEnum;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Aldatu {
    @FXML
    VBox vBoxHerriarenDatuak;

    @FXML
    HBox HBAldatu;

    @FXML
    HBox HBBotoiak;

    @FXML
    TextField txfBilatu;

    @FXML
    Label lbMezua;

    @FXML
    Label lbBilatu;

    private TextField txfIzenaLocal;
    private TextField txfDeskribapena;
    private TextField txfPrezioa;
    private ChoiceBox<Kategoria> kategoriaChoiceBox;
    private ChoiceBox<String> egoeraChoiceBox;
    private ChoiceBox<Saltzaileak> saltzaileChoiceBox;
    private CheckBox cbSalduta;
    private ChoiceBox<Erosleak> erosleChoiceBox;
    private Label lblIdErosle;

    private TextField txfEmail;
    private TextField txfTelefonoa;
    private TextField txfHelbidea;
    private TextField txfHerria;
    private TextField txfPostaKodea;
    private TextField txfErabiltzaileIzena;
    private TextField txfPasahitza;
    private ChoiceBox<KarguakEnum> cbKargua;

    private Produktuak produktua;
    private Langileak langilea;
    private Bezeroak bezeroa;

    @FXML
    protected void initialize() {
        HBAldatu.getChildren().clear();
        HBBotoiak.getChildren().clear();

        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            lbBilatu.setText("Produktuak ID bilatu:");
        } else if (App.conectionIdentifier.equalsIgnoreCase("Langileak")) {
            lbBilatu.setText("Langileak ID bilatu:");
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            lbBilatu.setText("Bezeroak ID bilatu:");
        }

        Button btnAtzera = new Button("ATZERA");
        btnAtzera.setPrefSize(300, 24);
        btnAtzera.setOnAction(event -> {
            try {
                handleAtzera();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        HBBotoiak.getChildren().addAll(btnAtzera);
    }

    @FXML
    void handleBilatu() throws IOException {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            int bilatu = 0;
            try {
                bilatu = Integer.parseInt(txfBilatu.getText());
            } catch (Exception e) {
                lbMezua.setText("ID zenbaki bat izan behar da");
                return;
            }
            if (App.produktuak.produktuBaDago(bilatu)) {
                try {
                    imprimatuAldatu(bilatu);
                    lbMezua.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                    lbMezua.setText("Errorea datuak inprimatzerakoan!");
                }
            } else {
                lbMezua.setText("Produktu hau ez da existitzen");
            }
        } else if (App.conectionIdentifier.equalsIgnoreCase("Langileak")) {
            int bilatu = 0;
            try {
                bilatu = Integer.parseInt(txfBilatu.getText());
            } catch (Exception e) {
                lbMezua.setText("ID zenbaki bat izan behar da");
                return;
            }
            if (App.langileak.langileaBaDago(bilatu)) {
                try {
                    imprimatuAldatu(bilatu);
                    lbMezua.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                    lbMezua.setText("Errorea datuak inprimatzerakoan!");
                }
            } else {
                lbMezua.setText("Langile hau ez da existitzen");
            }
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            int bilatu = 0;
            try {
                bilatu = Integer.parseInt(txfBilatu.getText());
            } catch (Exception e) {
                lbMezua.setText("ID zenbaki bat izan behar da");
                return;
            }
            if (App.bezeroak.bezeroBaDago(bilatu)) {
                try {
                    imprimatuAldatu(bilatu);
                    lbMezua.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                    lbMezua.setText("Errorea datuak inprimatzerakoan!");
                }
            } else {
                lbMezua.setText("Bezero hau ez da existitzen");
            }
        }
    }

    @FXML
    void imprimatuAldatu(int bilatu) throws IOException {
        HBAldatu.getChildren().clear();
        HBBotoiak.getChildren().clear();

        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            produktua = App.produktuak.searchProduktuak(bilatu);

            GridPane grid = new GridPane();
            grid.setVgap(2);
            grid.setHgap(2);

            // Izena
            grid.add(new Label("Izena:"), 0, 0);
            txfIzenaLocal = new TextField(produktua.getIzena());
            grid.add(txfIzenaLocal, 1, 0);

            // Deskribapena
            grid.add(new Label("Deskribapena:"), 0, 1);
            txfDeskribapena = new TextField(produktua.getDeskribapena());
            grid.add(txfDeskribapena, 1, 1);

            // Prezioa
            grid.add(new Label("Prezioa:"), 0, 2);
            txfPrezioa = new TextField(String.valueOf(produktua.getPrezioa()));
            grid.add(txfPrezioa, 1, 2);

            // Kategoria (ChoiceBox)
            grid.add(new Label("Kategoria:"), 0, 3);
            kategoriaChoiceBox = new ChoiceBox<>();
            kategoriaChoiceBox.getItems().addAll(App.produktuak.getAllKategoriak());

            // Obtener el ID de la categoría seleccionada en el producto
            int kategoriaId = produktua.getIdKategoria();

            // Buscar la categoría correspondiente por su ID
            Kategoria selectedKategoria = null;
            for (Kategoria kategoria : kategoriaChoiceBox.getItems()) {
                if (kategoria.getId() == kategoriaId) {
                    selectedKategoria = kategoria;
                    break;
                }
            }

            // Establecer el valor preseleccionado en el ChoiceBox
            if (selectedKategoria != null) {
                kategoriaChoiceBox.setValue(selectedKategoria);
            }

            grid.add(kategoriaChoiceBox, 1, 3);

            // Egoera (ChoiceBox)
            grid.add(new Label("Egoera:"), 0, 4);
            egoeraChoiceBox = new ChoiceBox<>();
            egoeraChoiceBox.getItems().addAll("berria", "erabilia", "hondatua");
            egoeraChoiceBox.setValue(produktua.getEgoera());
            grid.add(egoeraChoiceBox, 1, 4);

            // Saltzailea (ChoiceBox)
            grid.add(new Label("Saltzailea:"), 0, 5);
            saltzaileChoiceBox = new ChoiceBox<>();
            saltzaileChoiceBox.getItems().addAll(App.produktuak.getAllSaltzaileak());

            // Obtener el ID del saltzaile asociado al producto
            int saltzaileId = produktua.getIdSaltzaile();

            // Buscar el saltzaile correspondiente por su ID
            Saltzaileak selectedSaltzaile = null;
            for (Saltzaileak saltzaile : saltzaileChoiceBox.getItems()) {
                if (saltzaile.getId() == saltzaileId) {
                    selectedSaltzaile = saltzaile;
                    break;
                }
            }

            // Establecer el valor preseleccionado en el ChoiceBox
            if (selectedSaltzaile != null) {
                saltzaileChoiceBox.setValue(selectedSaltzaile);
            }

            grid.add(saltzaileChoiceBox, 1, 5);

            // Checkbox salduta
            cbSalduta = new CheckBox("Produktua salduta?");
            grid.add(cbSalduta, 0, 6);

            // Eroslea (ChoiceBox) - oculto por defecto
            lblIdErosle = new Label("Eroslea:");
            erosleChoiceBox = new ChoiceBox<>();
            erosleChoiceBox.setVisible(false);
            lblIdErosle.setVisible(false);

            // Cargar erosleak
            List<Erosleak> erosleakList = App.produktuak.getAllErosleak();
            erosleChoiceBox.getItems().addAll(erosleakList);

            // Preseleccionar eroslea si ya está vendido
            boolean isSold = App.produktuak.saldutaBaDago(produktua.getId());
            if (isSold) {
                cbSalduta.setSelected(true);
                int idErosle = App.produktuak.searchSalmentaErosle(produktua.getId());

                for (Erosleak erosle : erosleakList) {
                    if (erosle.getId() == idErosle) {
                        erosleChoiceBox.setValue(erosle);
                        break;
                    }
                }

                lblIdErosle.setVisible(true);
                erosleChoiceBox.setVisible(true);
            }

            cbSalduta.setOnAction(e -> {
                boolean selected = cbSalduta.isSelected();
                lblIdErosle.setVisible(selected);
                erosleChoiceBox.setVisible(selected);
            });

            grid.add(lblIdErosle, 0, 7);
            grid.add(erosleChoiceBox, 1, 7);

            HBAldatu.getChildren().add(grid);
        } else if (App.conectionIdentifier.equalsIgnoreCase("Langileak")) {
            langilea = App.langileak.searchLangilea(bilatu);

            GridPane grid = new GridPane();
            grid.setVgap(2);
            grid.setHgap(2);

            // Izena
            grid.add(new Label("Izena:"), 0, 0);
            txfIzenaLocal = new TextField(langilea.getIzena());
            grid.add(txfIzenaLocal, 1, 0);

            // Kargua (choicebox)
            grid.add(new Label("Kargua:"), 0, 1);

            // Crear el ChoiceBox con valores del enum
            cbKargua = new ChoiceBox<>();
            cbKargua.getItems().addAll(KarguakEnum.values());

            // Obtener el valor actual desde el langilea
            KarguakEnum karguaEnum = KarguakEnum.fromIzena(langilea.getKargua());
            if (karguaEnum != null) {
                cbKargua.setValue(karguaEnum);
            } else {
                cbKargua.setValue(KarguakEnum.SALTZAILEA); // valor por defecto
            }

            grid.add(cbKargua, 1, 1);
            // Email
            grid.add(new Label("Email:"), 0, 2);
            txfEmail = new TextField(langilea.getEmail());
            grid.add(txfEmail, 1, 2);

            // Telefonoa
            grid.add(new Label("Telefonoa:"), 0, 3);
            txfTelefonoa = new TextField(String.valueOf(langilea.getTelefonoa()));
            grid.add(txfTelefonoa, 1, 3);

            // Helbidea
            grid.add(new Label("Helbidea:"), 0, 4);
            txfHelbidea = new TextField(String.valueOf(langilea.getHelbidea()));
            grid.add(txfHelbidea, 1, 4);

            // Herria
            grid.add(new Label("Herria:"), 0, 5);
            txfHerria = new TextField(String.valueOf(langilea.getHerriIzena()));
            grid.add(txfHerria, 1, 5);

            // Posta kodea
            grid.add(new Label("Posta kodea:"), 0, 6);
            txfPostaKodea = new TextField(String.valueOf(langilea.getPostaKodea()));
            grid.add(txfPostaKodea, 1, 6);

            // Erabiltzaile izena
            grid.add(new Label("Erabiltzaile izena:"), 0, 7);
            txfErabiltzaileIzena = new TextField(String.valueOf(langilea.getErabiltzailea()));
            grid.add(txfErabiltzaileIzena, 1, 7);

            // Pasahitza
            grid.add(new Label("Pasahitza:"), 0, 8);
            txfPasahitza = new TextField(String.valueOf(langilea.getPasahitza()));
            grid.add(txfPasahitza, 1, 8);

            HBAldatu.getChildren().add(grid);
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            bezeroa = App.bezeroak.searchBezeroak(bilatu);

            GridPane grid = new GridPane();
            grid.setVgap(2);
            grid.setHgap(2);

            // Izena
            grid.add(new Label("Izena:"), 0, 0);
            txfIzenaLocal = new TextField(bezeroa.getIzena());
            grid.add(txfIzenaLocal, 1, 0);

            // Email
            grid.add(new Label("Email:"), 0, 1);
            txfEmail = new TextField(bezeroa.getEmail());
            grid.add(txfEmail, 1, 1);

            // Telefonoa
            grid.add(new Label("Telefonoa:"), 0, 2);
            txfTelefonoa = new TextField(String.valueOf(bezeroa.getTelefonoa()));
            grid.add(txfTelefonoa, 1, 2);

            // Helbidea
            grid.add(new Label("Helbidea:"), 0, 3);
            txfHelbidea = new TextField(String.valueOf(bezeroa.getHelbidea()));
            grid.add(txfHelbidea, 1, 3);

            // Herria
            grid.add(new Label("Herria:"), 0, 4);
            txfHerria = new TextField(String.valueOf(bezeroa.getHelbidea()));
            grid.add(txfHerria, 1, 4);

            // Posta kodea
            grid.add(new Label("Posta kodea:"), 0, 5);
            txfPostaKodea = new TextField(String.valueOf(bezeroa.getPostaKodea()));
            grid.add(txfPostaKodea, 1, 5);

            // Erabiltzaile izena
            grid.add(new Label("Erabiltzaile izena:"), 0, 6);
            txfErabiltzaileIzena = new TextField(String.valueOf(bezeroa.getErabiltzalea()));
            grid.add(txfErabiltzaileIzena, 1, 6);

            // Pasahitza
            grid.add(new Label("Pasahitza:"), 0, 7);
            txfPasahitza = new TextField(String.valueOf(bezeroa.getPasahitza()));
            grid.add(txfPasahitza, 1, 7);

            HBAldatu.getChildren().add(grid);
        }

        // Botón ALDATU
        Button btnAldatu = new Button("ALDATU");
        btnAldatu.setPrefSize(300, 24);
        btnAldatu.setOnAction(event -> {
            try {
                handleAldatu(); // Llamamos directamente al método handleAldatu
            } catch (Exception e) {
                lbMezua.setText("Errorea datuak bidaltzean.");
                e.printStackTrace();
            }
        });

        // Botón ATZERA
        Button btnAtzera = new Button("ATZERA");
        btnAtzera.setPrefSize(300, 24);
        btnAtzera.setOnAction(event -> {
            try {
                handleAtzera();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        HBBotoiak.getChildren().addAll(btnAldatu, btnAtzera);
    }

    public void handleAldatu() {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            String izena = txfIzenaLocal.getText();
            String deskribapena = txfDeskribapena.getText();
            String prezioa = txfPrezioa.getText();
            String egoera = egoeraChoiceBox.getValue();
            Kategoria kategoria = kategoriaChoiceBox.getValue();
            Saltzaileak saltzaile = saltzaileChoiceBox.getValue();
            boolean salduta = cbSalduta.isSelected();
            Erosleak erosle = erosleChoiceBox.getValue();
            Integer idErosle = (erosle != null) ? erosle.getId() : null;

            // Asegúrate de realizar las comprobaciones necesarias
            if (izena != null && !izena.isEmpty() && deskribapena != null && !deskribapena.isEmpty() && egoera != null
                    && kategoria != null && saltzaile != null) {

                // Verificar si alguno de los campos contiene posibles inyecciones de código
                String[] inputs = {
                        izena,
                        deskribapena,
                        prezioa,
                        egoera,
                        kategoria.getIzena(),
                        saltzaile.getIzena()
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
                    lbMezua.setText("Ez dira onartzen etiketak edo script-ak");
                    return;
                }

                try {
                    // Verificar que el precio sea un número válido
                    double precio;
                    try {
                        precio = Double.parseDouble(prezioa);
                    } catch (NumberFormatException e) {
                        lbMezua.setText("Prezioa zenbaki baliozkoa izan behar da.");
                        return;
                    }

                    // Obtener el producto de la interfaz de usuario
                    Produktuak produktuaActualizado = new Produktuak(produktua.getId(), izena, deskribapena,
                            (int) precio,
                            kategoria.getId(), saltzaile.getId(), egoera, null);

                    // Si el producto está marcado como vendido, asignar el ID del comprador
                    if (salduta) {
                        if (idErosle != null) {
                            int result = App.produktuak.handleAldatu(produktuaActualizado, 1, idErosle);
                            if (result > 0) {
                                initialize();
                                lbMezua.setText("Produktua eguneratu da.");
                            } else {
                                lbMezua.setText("Errorea salmenta eguneratzerakoan.");
                            }
                        } else {
                            lbMezua.setText("Erosle bat aukeratu behar da saldutako produktuarentzat.");
                        }
                    } else {
                        // Si el producto no está vendido, solo actualizamos el producto
                        int result = App.produktuak.handleAldatu(produktuaActualizado, 0, 0);
                        if (result > 0) {
                            initialize();
                            lbMezua.setText("Produktua eguneratu da.");
                        } else {
                            lbMezua.setText("Errorea produktua eguneratzerakoan.");
                        }
                    }

                } catch (Exception e) {
                    lbMezua.setText("Errorea datuak bidaltzean.");
                    e.printStackTrace();
                }
            } else {
                lbMezua.setText("Izena, deskribapena, egoera eta saltzailea bete behar dira.");
            }
        } else if (App.conectionIdentifier.equalsIgnoreCase("Langileak")) {
            String izena = txfIzenaLocal.getText();
            String kargua = cbKargua.getValue().getIzena();
            String email = txfEmail.getText();
            String telefonoa = txfTelefonoa.getText();
            String helbidea = txfHelbidea.getText();
            String herria = txfHerria.getText();
            String postaKodea = txfPostaKodea.getText();
            String erabiltzaileIzena = txfErabiltzaileIzena.getText();
            String pasahitza = txfPasahitza.getText();

            // Asegúrate de realizar las comprobaciones necesarias
            if (izena != null && !izena.isEmpty() && email != null && !email.isEmpty() && telefonoa != null
                    && !telefonoa.isEmpty() && helbidea != null && !helbidea.isEmpty() && herria != null
                    && !herria.isEmpty() && postaKodea != null && !postaKodea.isEmpty() && erabiltzaileIzena != null
                    && !erabiltzaileIzena.isEmpty() && pasahitza != null && !pasahitza.isEmpty()) {

                // Verificar si alguno de los campos contiene posibles inyecciones de código
                String[] inputs = {
                        izena,
                        email,
                        telefonoa,
                        helbidea,
                        herria,
                        postaKodea,
                        erabiltzaileIzena,
                        pasahitza
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
                    lbMezua.setText("Ez dira onartzen etiketak edo script-ak");
                    return;
                }

                try {
                    // Verificar que el teléfono sea un número válido
                    int telefono;
                    try {
                        telefono = Integer.parseInt(telefonoa);
                    } catch (NumberFormatException e) {
                        lbMezua.setText("Telefono zenbaki baliozkoa izan behar da.");
                        return;
                    }

                    // Crear el objeto Langileak con los datos actualizados
                    Langileak langileaActualizado = new Langileak(langilea.getId(), izena, kargua, email, telefono,
                            herria, postaKodea, helbidea, langilea.getAltaData(), erabiltzaileIzena, pasahitza);

                    System.out.println(langileaActualizado.toString());

                    // Actualizar el empleado en la base de datos
                    int result = App.langileak.handleAldatu(langileaActualizado);
                    if (result > 0) {
                        initialize();
                        lbMezua.setText("Langilea eguneratu da.");
                    } else if (result == -1062) {
                        lbMezua.setText("Erabiltzaile izena jada existitzen da.");
                    } else {
                        lbMezua.setText("Errorea langilea eguneratzerakoan.");
                    }
                } catch (Exception e) {
                    lbMezua.setText("Errorea datuak bidaltzean.");
                    e.printStackTrace();
                }
            } else {
                lbMezua.setText(
                        "Izena, kargua, email, telefonoa, helbidea, herria, posta kodea, alta data, erabiltzaile izena eta pasahitza bete behar dira.");
            }
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            String izena = txfIzenaLocal.getText();
            String email = txfEmail.getText();
            String telefonoa = txfTelefonoa.getText();
            String helbidea = txfHelbidea.getText();
            String herria = txfHerria.getText();
            String postaKodea = txfPostaKodea.getText();
            String erabiltzaileIzena = txfErabiltzaileIzena.getText();
            String pasahitza = txfPasahitza.getText();

            // Asegúrate de realizar las comprobaciones necesarias
            if (izena != null && !izena.isEmpty() && email != null && !email.isEmpty() && telefonoa != null
                    && !telefonoa.isEmpty() && helbidea != null && !helbidea.isEmpty() && herria != null
                    && !herria.isEmpty() && postaKodea != null && !postaKodea.isEmpty() && erabiltzaileIzena != null
                    && !erabiltzaileIzena.isEmpty() && pasahitza != null && !pasahitza.isEmpty()) {

                // Verificar si alguno de los campos contiene posibles inyecciones de código
                String[] inputs = {
                        izena,
                        email,
                        telefonoa,
                        helbidea,
                        herria,
                        postaKodea,
                        erabiltzaileIzena,
                        pasahitza
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
                    lbMezua.setText("Ez dira onartzen etiketak edo script-ak");
                    return;
                }

                try {
                    // Verificar que el teléfono sea un número válido
                    int telefono;
                    try {
                        telefono = Integer.parseInt(telefonoa);
                    } catch (NumberFormatException e) {
                        lbMezua.setText("Telefono zenbaki baliozkoa izan behar da.");
                        return;
                    }

                    // Crear el objeto Bezeroak con los datos actualizados
                    Bezeroak bezeroaActualizado = new Bezeroak(bezeroa.getId(), izena, email, telefono, herria,
                            postaKodea, helbidea, bezeroa.getAltaData(), erabiltzaileIzena, pasahitza);

                    // Actualizar el cliente en la base de datos
                    int result = App.bezeroak.handleAldatu(bezeroaActualizado);
                    if (result > 0) {
                        initialize();
                        lbMezua.setText("Bezeroa eguneratu da.");
                    } else if (result == -1062) {
                        lbMezua.setText("Erabiltzaile izena jada existitzen da.");
                    } else {
                        lbMezua.setText("Errorea bezeroa eguneratzerakoan.");
                    }
                } catch (Exception e) {
                    lbMezua.setText("Errorea datuak bidaltzean.");
                    e.printStackTrace();
                }
            } else {
                lbMezua.setText(
                        "Izena, email, telefonoa, helbidea, herria, posta kodea, alta data, erabiltzaile izena eta pasahitza bete behar dira.");
            }
        }
    }

    @FXML
    void handleAtzera() throws IOException {
        App.setRoot("Bistaratu");
    }
}
