package devworks.controller;

import java.io.IOException;

import devworks.App;
import devworks.model.base.Produktuak;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {
            Produktuak produktua = App.produktuak.searchProduktuak(bilatu);
    
            HBAldatu.getChildren().clear();
            HBBotoiak.getChildren().clear();
    
            GridPane grid = new GridPane();
            grid.setVgap(10);
            grid.setHgap(10);
    
            // Izena
            grid.add(new Label("Izena:"), 0, 0);
            TextField txfIzenaLocal = new TextField(produktua.getIzena());
            grid.add(txfIzenaLocal, 1, 0);
    
            // Deskribapena
            grid.add(new Label("Deskribapena:"), 0, 1);
            TextField txfDeskribapena = new TextField(produktua.getDeskribapena());
            grid.add(txfDeskribapena, 1, 1);
    
            // Prezioa
            grid.add(new Label("Prezioa:"), 0, 2);
            TextField txfPrezioa = new TextField(String.valueOf(produktua.getPrezioa()));
            grid.add(txfPrezioa, 1, 2);
    
            // Id Kategoria
            grid.add(new Label("ID Kategoria:"), 0, 3);
            TextField txfKategoria = new TextField(String.valueOf(produktua.getIdKategoria()));
            grid.add(txfKategoria, 1, 3);
    
            // Egoera
            grid.add(new Label("Egoera:"), 0, 4);
            TextField txfEgoera = new TextField(produktua.getEgoera());
            grid.add(txfEgoera, 1, 4);
    
            // Id Saltzaile
            grid.add(new Label("ID Saltzaile:"), 0, 5);
            TextField txfSaltzaile = new TextField(String.valueOf(produktua.getIdSaltzaile()));
            grid.add(txfSaltzaile, 1, 5);
    
            // Checkbox salduta
            CheckBox cbSalduta = new CheckBox("Produktua salduta?");
            grid.add(cbSalduta, 0, 6);
    
            // ID Erosle (oculto por defecto)
            Label lblIdErosle = new Label("ID Erosle:");
            TextField txfIdErosle = new TextField();
            grid.add(lblIdErosle, 0, 7);
            grid.add(txfIdErosle, 1, 7);
            lblIdErosle.setVisible(false);
            txfIdErosle.setVisible(false);
    
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
                    // Crear Produktuak objektua actualizado
                    produktua.setIzena(txfIzenaLocal.getText());
                    produktua.setDeskribapena(txfDeskribapena.getText());
                    produktua.setPrezioa(Integer.parseInt(txfPrezioa.getText()));
                    produktua.setIdKategoria(Integer.parseInt(txfKategoria.getText()));
                    produktua.setEgoera(txfEgoera.getText());
                    produktua.setIdSaltzaile(Integer.parseInt(txfSaltzaile.getText()));
    
                    int salduta = cbSalduta.isSelected() ? 1 : 0;
                    int idErosle = salduta == 1 ? Integer.parseInt(txfIdErosle.getText()) : 0;
    
                    handleAldatu(produktua, salduta, idErosle);
                } catch (Exception ex) {
                    lbMezua.setText("Errorea datuak bidaltzean.");
                    ex.printStackTrace();
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
    
    

    @FXML
    public void handleAldatu(Produktuak produktua, int salduta, int idErosle) throws IOException {
        if (produktua.getIzena() != null && !produktua.getIzena().isEmpty()
                && produktua.getDeskribapena() != null && !produktua.getDeskribapena().isEmpty()
                && produktua.getEgoera() != null && !produktua.getEgoera().isEmpty()) {
    
            try {
                int emaitza = App.produktuak.handleAldatu(produktua, salduta, idErosle);
    
                if (emaitza == 1) {
                    lbMezua.setText("Datuak behar bezala eguneratu dira.");
                    txfBilatu.clear();
                    HBAldatu.getChildren().clear();
                    HBBotoiak.getChildren().clear();
                    initialize();
    
                } else if (emaitza == 0) {
                    lbMezua.setText("Produktua ez da existitzen edo ez da aldatu.");
                } else if (emaitza == -2) {
                    lbMezua.setText("Produktua eguneratu da, baina salmenta txertatzea huts egin du.");
                } else if (emaitza == -1062) {
                    lbMezua.setText("Produktu berdina jada existitzen da (datu errepikatua).");
                } else {
                    lbMezua.setText("Barne-errore bat gertatu da datuak eguneratzean.");
                }
    
            } catch (Exception e) {
                lbMezua.setText("Errorea gertatu da datuak txertatzean.");
                e.printStackTrace();
            }
    
        } else {
            lbMezua.setText("Izena, deskribapena eta egoera bete behar dira.");
        }
    }
    

    @FXML
    void handleAtzera() throws IOException {
        App.conectionIdentifier = "MenuBotoiak";
        App.setRoot("MenuBotoiak");
    }
}
