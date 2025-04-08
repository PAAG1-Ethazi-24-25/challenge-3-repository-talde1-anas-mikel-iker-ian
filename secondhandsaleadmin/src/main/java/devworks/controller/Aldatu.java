package devworks.controller;

import java.io.IOException;

import devworks.App;
import devworks.model.base.Kategoria;
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
    private TextField txfIdErosle;
    private Label lblIdErosle;

    private Produktuak produktua;

    @FXML
    protected void initialize() {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            lbBilatu.setText("Produktuak ID bilatu:");
        } else if (App.conectionIdentifier.equalsIgnoreCase("Langileak")) {
            lbBilatu.setText("Langileak ID bilatu:");
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {
            lbBilatu.setText("Bezeroak ID bilatu:");
        } else if (App.conectionIdentifier.equalsIgnoreCase("Salmentak")) {
            lbBilatu.setText("Salmentak ID bilatu:");
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
        }
    }

    @FXML
    void imprimatuAldatu(int bilatu) throws IOException {
        HBAldatu.getChildren().clear();
        HBBotoiak.getChildren().clear();

        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            produktua = App.produktuak.searchProduktuak(bilatu);

            GridPane grid = new GridPane();
            grid.setVgap(10);
            grid.setHgap(10);

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

            // ID Erosle (oculto por defecto)
            lblIdErosle = new Label("ID Erosle:");
            txfIdErosle = new TextField();
            grid.add(lblIdErosle, 0, 7);
            grid.add(txfIdErosle, 1, 7);
            lblIdErosle.setVisible(false);
            txfIdErosle.setVisible(false);

            // Check if the product is already sold
            boolean isSold = App.produktuak.saldutaBaDago(produktua.getId());

            // If the product is sold, set the checkbox and show the buyer's ID
            if (isSold) {
                cbSalduta.setSelected(true);
                int idErosle = App.produktuak.searchSalmentaErosle(produktua.getId());
                txfIdErosle.setText(String.valueOf(idErosle));
                lblIdErosle.setVisible(true);
                txfIdErosle.setVisible(true);
            }

            // Show the checkbox for "salduta" status
            cbSalduta.setOnAction(e -> {
                boolean selected = cbSalduta.isSelected();
                lblIdErosle.setVisible(selected);
                txfIdErosle.setVisible(selected);
            });

            HBAldatu.getChildren().add(grid);

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
    }

    public void handleAldatu() {
        String izena = txfIzenaLocal.getText();
        String deskribapena = txfDeskribapena.getText();
        String prezioa = txfPrezioa.getText();
        String egoera = egoeraChoiceBox.getValue();
        Kategoria kategoria = kategoriaChoiceBox.getValue();
        Saltzaileak saltzaile = saltzaileChoiceBox.getValue();
        boolean salduta = cbSalduta.isSelected();
        String idErosle = txfIdErosle.getText();

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
                Produktuak produktuaActualizado = new Produktuak(produktua.getId(), izena, deskribapena, (int) precio,
                        kategoria.getId(), saltzaile.getId(), egoera, null);

                // Si el producto está marcado como vendido, asignar el ID del comprador
                if (salduta) {
                    if (idErosle != null && !idErosle.isEmpty()) {
                        try {
                            int erosleId = Integer.parseInt(idErosle);
                            int result = App.produktuak.handleAldatu(produktuaActualizado, 1, erosleId);
                            if (result > 0) {
                                lbMezua.setText("Produktua eguneratu da.");
                            } else {
                                lbMezua.setText("Errorea salmenta eguneratzerakoan.");
                            }
                        } catch (NumberFormatException e) {
                            lbMezua.setText("ID Erosle baliozkoa izan behar da.");
                        }
                    } else {
                        lbMezua.setText("Erosle ID beharrezkoa da salduta dagoen produktuarentzat.");
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
    }

    @FXML
    void handleAtzera() throws IOException {
        App.setRoot("Bistaratu");
    }
}
