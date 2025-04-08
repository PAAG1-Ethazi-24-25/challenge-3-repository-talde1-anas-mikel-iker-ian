package devworks.controller;

import java.io.IOException;

import devworks.App;
import devworks.model.base.Produktuak;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

            // Izena eta Probintzia aldatu
            GridPane grid = new GridPane();
            grid.setVgap(10);

            grid.add(new Label("Izena:"), 0, 0);
            TextField txfIzenaLocal = new TextField(herria.getHerriIzena());
            txfIzenaLocal.setId("txfIzena");
            grid.add(txfIzenaLocal, 1, 0);

            grid.add(new Label("Probintzia:"), 0, 1);
            TextField txfProbintziaLocal = new TextField(herria.getProbintzia());
            txfProbintziaLocal.setId("txfProbintzia");
            grid.add(txfProbintziaLocal, 1, 1);

            HBAldatu.getChildren().add(grid);

            // Botioak atzera eta aldatu
            Button btnAldatu = new Button("ALDATU");
            btnAldatu.setPrefSize(300, 24);
            btnAldatu.setOnAction(event -> {
                try {
                    handleAldatu(txfIzenaLocal, txfProbintziaLocal, bilatu);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

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
    public void handleAldatu(TextField txfIzenaLocal, TextField txfProbintziaLocal, int bilatu) throws IOException {
        // String izena = txfIzenaLocal.getText();
        // String probintzia = txfProbintziaLocal.getText();
        //
        // if (izena != null && !izena.isEmpty() && probintzia != null &&
        // !probintzia.isEmpty()) {
        // try {
        // Herria herria = new Herria(izena, probintzia);
        // int herriaUpdate = App.herriak.update(herria, bilatu);
        //
        // if (herriaUpdate == 1) {
        // // System.out.println("Herria ondo gordeta");
        // lbMezua.setText("Datuak behar bezala eguneratu dira");
        //
        // txfIzenaBilatu.clear();
        // HBIzenaEtaProbintzia.getChildren().clear();
        // HBBotoiak.getChildren().clear();
        // initialize();
        //
        // } else if (herriaUpdate == 0) {
        // lbMezua.setText("Datu hori ez existitzen");
        // } else {
        // lbMezua.setText("Barne-errore bat gertatu da programan");
        // }
        // } catch (Exception e) {
        // lbMezua.setText("Errorea gertatu da datuak txertatzean");
        // }
        // } else {
        // lbMezua.setText("Izena eta probintzia beteta behar du");
        // }
    }

    @FXML
    void handleAtzera() throws IOException {
        App.conectionIdentifier = "MenuBotoiak";
        App.setRoot("MenuBotoiak");
    }
}
