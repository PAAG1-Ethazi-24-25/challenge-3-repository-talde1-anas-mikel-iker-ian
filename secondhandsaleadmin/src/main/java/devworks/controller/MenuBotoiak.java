package devworks.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;

import devworks.App;

public class MenuBotoiak {
    @FXML
    void handleProduktoak() throws IOException {
        App.pagina = "Produktuak";
        App.setRoot("Bistaratu");
    }

    @FXML
    void handleLangileak() throws IOException {
        App.pagina = "Langileak";
        App.setRoot("Bistaratu");
    }

    @FXML
    void handleErabiltzaileak() throws IOException {
        App.pagina = "Erabiltzaileak";
        App.setRoot("Bistaratu");
    }

    @FXML
    void handleSalmentak() throws IOException {
        App.pagina = "Salmentak";
        App.setRoot("Bistaratu");
    }

    @FXML
    void handleIrten() {
        Platform.exit();
        System.exit(0); // Asegura que la aplicación se cierre completamente
    }
}