package devworks.controller;

import java.io.IOException;
import java.util.List;

import devworks.App;
import devworks.model.base.Kategoria;
import devworks.model.base.Produktuak;
import devworks.model.base.Bezeroak;
import devworks.model.base.Saltzaileak;
import devworks.model.base.Salmentak;
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
    ChoiceBox<String> egoera = new ChoiceBox<>();
    ChoiceBox<Saltzaileak> saltzaileacbx = new ChoiceBox<>();
    ChoiceBox<Produktuak> produktuakcbx = new ChoiceBox<>();

    Label mezua = new Label();

    
    List<Kategoria> ListKategoriak = App.produktuak.getAllKategoriak();
    List<Saltzaileak> listSaltzaileak = App.produktuak.getAllSaltzaileak();
    List<Produktuak> listProduktuak = App.produktuak.getProduktoak();


    // que asco dan los negros
    @FXML
    protected void initialize() {
        if (App.conectionIdentifier.equalsIgnoreCase("Produktuak")) {

            kategoriacbx.getItems().addAll(ListKategoriak);
            egoera.getItems().addAll("berria","erabilia", "hondatua");
            saltzaileacbx.getItems().addAll(listSaltzaileak);


            vBoxDatuak.getChildren().addAll( new Label("Izena:"), izena,
                                                new Label("deskribapena:"), deskribapena,
                                                new Label("prezioa:"), prezioa,
                                                mezua );

            vBoxDatuak2.getChildren().addAll( new Label("kategoria:"), kategoriacbx,
                                                new Label("egoera:"), egoera,
                                                new Label("saltzailea:"), saltzaileacbx );



        }else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {

            vBoxDatuak.getChildren().addAll( new Label("Izena:"), izena,
                                                new Label("email:"), email,
                                                new Label("telefonoa:"), telefonoa,
                                                new Label("helbidea:"), helbidea,
                                                mezua );

            vBoxDatuak2.getChildren().addAll( new Label("herria:"), herria,
                                                new Label("posta kodea:"), pk,
                                                new Label("erabiltzaile izena:"), erabiltzailea,
                                                new Label("pasahitza:"), pasahitza );
        }else if (App.conectionIdentifier.equalsIgnoreCase("Salmentak")){
            produktuakcbx.getItems().addAll(listProduktuak);

            vBoxDatuak.getChildren().addAll( new Label("Produktua:"), produktuakcbx, new Label("eroslea:"), saltzaileacbx,
                                                mezua );

            vBoxDatuak2.getChildren().addAll( new Label("herria:"), prezioa );

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

            //     Produktuak(int id, String izena, String deskribapena, int prezioa, int idKategoria, int idSaltzaile, String egoera,
            // String email)

                Produktuak produktua= new Produktuak(0, izena.getText(), deskribapena.getText(), Integer.parseInt(prezioa.getText()) ,kategoria.getId(), saltzailea.getId() ,egoera.getValue(), null);

                App.produktuak.produktuaTxertatu(produktua);

            }
            
        } else if (App.conectionIdentifier.equalsIgnoreCase("Bezeroak")) {

            if (izena == null || email == null || telefonoa == null || helbidea == null || herria == null || pk == null || erabiltzailea == null || pasahitza == null ) {

                mezua.setText("Balio batzuk sartu gabe dituzu");
            }else{
                
                // Bezeroak(int id, String izena, String email, int telefonoa, String herria, String postaKodea,
            // String helbidea, String erregistroData, String erabiltzalea, String pasahitza )

                Bezeroak bezeroa = new Bezeroak(0, izena.getText(), email.getText(), Integer.parseInt(telefonoa.getText()), herria.getText(), pk.getText(), helbidea.getText(), "01/01/01", erabiltzailea.getText(), pasahitza.getText()  );

                App.bezeroak.bezeroaTxertatu(bezeroa);

            }
            
        } else if (App.conectionIdentifier.equalsIgnoreCase("Salmentak")){

            if (produktuakcbx.getValue() == null ||  saltzaileacbx.getValue() == null || prezioa == null ) {

                mezua.setText("Balio batzuk sartu gabe dituzu");
            }else{
                
            Produktuak produktua= produktuakcbx.getValue();
            Saltzaileak saltzailea= saltzaileacbx.getValue();

            // Salmentak(int id, int idProduktu, int idSaltzaile, int idErosle, Date data, double salmentaPrezioa,
            // String izenaProduktu)

            Salmentak salmenta = new Salmentak(0, produktua.getId(), produktua.getIdSaltzaile(), 
                                                saltzailea.getId(), "01/01/2000" , Double.parseDouble(prezioa.getText()), produktua.getIzena() );
            
            App.salmentak.salmentaTxertatu(salmenta);
            


            }

        }
    }


    @FXML
    void handleAtzera() throws IOException {
        App.conectionIdentifier = "MenuBotoiak";
        App.setRoot("MenuBotoiak");
    }
}
