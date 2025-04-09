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

    private Produktuak produktua;
    private Langileak langilea;
    private Bezeroak bezeroa;

    @FXML
    protected void initialize() {
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
            grid.setVgap(8);
            grid.setHgap(8);

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
            grid.setVgap(9);
            grid.setHgap(9);

            // Izena
            grid.add(new Label("Izena:"), 0, 0);
            txfIzenaLocal = new TextField(langilea.getIzena());
            grid.add(txfIzenaLocal, 1, 0);

            // Email
            grid.add(new Label("Email:"), 0, 1);
            txfDeskribapena = new TextField(langilea.getEmail());
            grid.add(txfDeskribapena, 1, 1);

            // Prezioa
            grid.add(new Label("Telefonoa:"), 0, 2);
            txfPrezioa = new TextField(String.valueOf(langilea.getTelefonoa()));
            grid.add(txfPrezioa, 1, 2);

            // Helbidea
            grid.add(new Label("Helbidea:"), 0, 3);
            txfPrezioa = new TextField(String.valueOf(langilea.getHelbidea()));
            grid.add(txfPrezioa, 1, 3);

            grid.add(kategoriaChoiceBox, 1, 3);

            // Herria
            grid.add(new Label("Herria:"), 0, 4);
            txfPrezioa = new TextField(String.valueOf(langilea.getHelbidea()));
            grid.add(txfPrezioa, 1, 3);

            grid.add(kategoriaChoiceBox, 1, 3);

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
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            // Similar a lo anterior, pero para bezeroak
            // Aquí puedes implementar la lógica para mostrar los datos de un bezeroa y
            // permitir su modificación
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
            // Similar a lo anterior, pero para langileak
            // Aquí puedes implementar la lógica para actualizar los datos de un langilea
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            // Similar a lo anterior, pero para bezeroak
            // Aquí puedes implementar la lógica para actualizar los datos de un bezeroa
        }
    }

    @FXML
    void handleAtzera() throws IOException {
        App.setRoot("Bistaratu");
    }
}
