package devworks.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;

import devworks.App;

public class MenuBotoiak {
    @FXML
    void handleProduktoak() throws IOException {
        App.conectionIdentifier = "Produktuak";
        App.setRoot("Bistaratu");
    }

    @FXML
    void handleLangileak() throws IOException {
        App.conectionIdentifier = "Langileak";
        App.setRoot("Bistaratu");
    }

    @FXML
    void handleErabiltzaileak() throws IOException {
        App.conectionIdentifier = "Erabiltzaileak";
        App.setRoot("Bistaratu");
    }

    @FXML
    void handleSalmentak() throws IOException {
        App.conectionIdentifier = "Salmentak";
        App.setRoot("Bistaratu");
    }

    @FXML
    void handleIrten() {
        Platform.exit();
        System.exit(0); // Asegura que la aplicación se cierre completamente
    }
}