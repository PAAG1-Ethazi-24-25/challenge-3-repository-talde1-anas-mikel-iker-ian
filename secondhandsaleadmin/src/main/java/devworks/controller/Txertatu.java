package devworks.controller;

import java.io.IOException;
import java.util.List;

import devworks.App;
import devworks.model.base.Kategoria;
import devworks.model.base.Produktuak;
import devworks.model.base.Saltzaileak;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    ChoiceBox<String> egoera = new ChoiceBox();
    ChoiceBox<Saltzaileak> saltzaileacbx = new ChoiceBox<>();

    Label mezua = new Label();


    List<Kategoria> ListKategoriak = App.produktuak.getAllKategoriak();
    List<Saltzaileak> listSaltzaileak = App.produktuak.getAllSaltzaileak();


    // que asco dan los negros
    @FXML
    protected void initialize() {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {

            kategoriacbx.getItems().addAll(ListKategoriak);
            egoera.getItems().addAll("berria","erabilia", "hondatua");
            saltzaileacbx.getItems().addAll(listSaltzaileak);

            vBoxDatuak.getChildren().addAll( new Label("Izena:"), izena,
                                                new Label("email:"), email,
                                                new Label("telefonoa:"), telefonoa,
                                                new Label("helbidea:"), helbidea,
                                                mezua );

            vBoxDatuak2.getChildren().addAll( new Label("herria:"), herria,
                                                new Label("egoera:"), egoera,
                                                new Label("saltzailea:"), saltzaileacbx );



        }else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {

            vBoxDatuak.getChildren().addAll( new Label("Izena:"), izena,
                                                new Label("deskribapena:"), deskribapena,
                                                new Label("prezioa:"), prezioa,
                                                mezua );

            vBoxDatuak2.getChildren().addAll( new Label("kategoria:"), kategoriacbx,
                                                new Label("egoera:"), egoera,
                                                new Label("saltzailea:"), saltzaileacbx );
        }
    }

    @FXML
    public void handleTxertatu() throws IOException {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {


            if (izena == null || deskribapena == null || prezioa == null || kategoriacbx.getValue() == null || egoera.getValue() == null || saltzaileacbx.getValue()== null ) {

                mezua.setText("Balio batzuk sartu gabe dituzu");
            }else{
                
                Kategoria kategoria= kategoriacbx.getValue();
                Saltzaileak saltzailea= saltzaileacbx.getValue();

                Produktuak produktua= new Produktuak(0, izena.getText(), deskribapena.getText(), Integer.parseInt(prezioa.getText()) ,kategoria.getId(), egoera.getValue(), null);

                App.produktuak.produktuaTxertatu(produktua, saltzailea.getId());

            }

            
            
        }else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {

            
        }
    }


    @FXML
    void handleAtzera() throws IOException {
        App.conectionIdentifier = "MenuBotoiak";
        App.setRoot("MenuBotoiak");
    }
}
