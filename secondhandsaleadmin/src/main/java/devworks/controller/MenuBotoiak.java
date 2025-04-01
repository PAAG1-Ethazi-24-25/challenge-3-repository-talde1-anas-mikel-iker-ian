package devworks.controller;

import java.io.IOException;
import javafx.fxml.FXML;

import devworks.App;

public class MenuBotoiak {
    @FXML
    void handleProduktoak() throws IOException {
        System.out.println("Produktuak botoia sakatu da");
    }

    @FXML
    void handleLangileak() throws IOException {
        System.out.println("Langileak botoia sakatu da");
    }

    @FXML
    void handleErabiltzaileak() throws IOException {
        System.out.println("Erabiltzaileak botoia sakatu da");
    }

    @FXML
    void handleSalmentak() throws IOException {
        System.out.println("Salmentak botoia sakatu da");
    }
}