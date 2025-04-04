package devworks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import devworks.model.ProduktoAtzipena;
import devworks.model.BezeroAtzipena;
import devworks.model.LangileAtzipena;
import devworks.model.SalmentaAtzipena;

/**
 * JavaFX App
 */
public class App extends Application {
    public static ProduktoAtzipena produktuak = new ProduktoAtzipena("localhost", "db_bigarreneskukomerkatua",
            "produktuak", "root", "");
    public static BezeroAtzipena bezeroak = new BezeroAtzipena("localhost", "db_bigarreneskukomerkatua", "bezeroak",
            "root", "");
    public static LangileAtzipena langileak = new LangileAtzipena("localhost", "db_bigarreneskukomerkatua", "langileak",
            "root", "");
    public static SalmentaAtzipena salmentak = new SalmentaAtzipena("localhost", "db_bigarreneskukomerkatua",
            "salmentak", "root", "");

    public static String conectionIdentifier = "MenuBotoiak"; // To know on what page we are

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MenuBotoiak"));
        scene.getStylesheets().add(getClass().getResource("css/ModenaAldatua.css").toExternalForm());
        stage.setTitle("Second Hand Sale Admin");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/devworks/img/icon.png")));
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Sakatu ESC pantaila osoa moduan ateratzeko");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}