/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_piotrpawlaczyk;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import Ludzie.Klient;
import Ludzie.KlientFirmowy;
import Ludzie.KlientOkazjonalny;
import Ludzie.KlientStaly;
import javafx.scene.control.TextArea;
import Glowna.ZarzadzanieKlientami;
import Glowna.ZarzadzanieDostawcami;
import Glowna.Restauracja;
import Ludzie.Dostawca;
import Restauracja.Zamowienie;

/**
 *
 * @author Piotr klasa kontrolujaca obsluge przyciskow
 */
public class FXMLDocumentController implements Initializable {

    private Restauracja zmienne;
    @FXML
    Button add_klient;
    @FXML
    Button rm_dostawca;
    @FXML
    private TextArea Wyswietlacz;
    @FXML
    AnchorPane mapa;
    @FXML
    private Button AddDostawca;
    @FXML
    private Button add_zamowienie;
    @FXML
    private Button Wroc;
    @FXML
    private Button rm_klient;

    /**
     * funkcja dodajaca klienta losujaca jego typ, nastepnie dodaje odpowidni
     * typ klienta do listy klientow, rysuje do na mapie oraz tworzy jego watek
     *
     * @param event
     */
    @FXML
    private void add_klient_f(ActionEvent event) {
        Random losowanie = new Random();
        int jaki_klient = losowanie.nextInt(3);

        if (jaki_klient == 0) {
            KlientFirmowy nowy = new KlientFirmowy(zmienne);

            Restauracja.getKlienci().add(nowy);
            nowy.narysujnamapie(mapa);
            nowy.getRysunek().setOnMouseClicked(new ZarzadzanieKlientami(Wyswietlacz, zmienne));
            Thread thread = new Thread(nowy);
            thread.start();
            nowy.setThread(thread);
        } else if (jaki_klient == 1) {
            KlientOkazjonalny nowy = new KlientOkazjonalny(zmienne);

            Restauracja.getKlienci().add(nowy);
            nowy.narysujnamapie(mapa);
            nowy.getRysunek().setOnMouseClicked(new ZarzadzanieKlientami(Wyswietlacz, zmienne));
            Thread thread = new Thread(nowy);
            thread.start();
            nowy.setThread(thread);

        } else if (jaki_klient == 2) {

            KlientStaly nowy = new KlientStaly(zmienne);

            Restauracja.getKlienci().add(nowy);
            nowy.narysujnamapie(mapa);
            nowy.getRysunek().setOnMouseClicked(new ZarzadzanieKlientami(Wyswietlacz, zmienne));
            Thread thread = new Thread(nowy);
            thread.start();
            nowy.setThread(thread);

        }

    }

    /**
     * funkcja usuwajaca ostatnio kliknietego klienta usuwajaca jego zamowienia
     * , watek oraz grafike na mapie
     *
     * @param event
     */
    @FXML
    private void rm_klient_f(ActionEvent event) {
        this.Wyswietlacz.clear();
        Klient nowy = zmienne.getOstatniklient();

        for (int i = 0; i < zmienne.getZamowienia().size(); i++) {
            if (zmienne.getZamowienia().get(i).getNazwaklienta() == nowy) {
                zmienne.getZamowienia().remove(i);
                i--;

            }

        }

        if (nowy instanceof KlientFirmowy) {

            ((KlientFirmowy) nowy).getRysunek().setOnMouseClicked(null);
            this.mapa.getChildren().remove(((KlientFirmowy) nowy).getRysunek());
            nowy.getThread().interrupt();
            zmienne.getKlienci().remove(nowy);
            this.Wyswietlacz.clear();

        }
        if (nowy instanceof KlientOkazjonalny) {

            ((KlientOkazjonalny) nowy).getRysunek().setOnMouseClicked(null);
            this.mapa.getChildren().remove(((KlientOkazjonalny) nowy).getRysunek());
            nowy.getThread().interrupt();
            zmienne.getKlienci().remove(nowy);
            this.Wyswietlacz.clear();
        }

        if (nowy instanceof KlientStaly) {

            ((KlientStaly) nowy).getRysunek().setOnMouseClicked(null);
            this.mapa.getChildren().remove(((KlientStaly) nowy).getRysunek());
            nowy.getThread().interrupt();
            zmienne.getKlienci().remove(nowy);
            this.Wyswietlacz.clear();
        }

    }

    /**
     * funkcja dodajaca dostawce, rysujaca go na mapie zgodnie z aktualnym
     * polozeniem i odpowiednia grafika oraz tworzaca jego watek
     *
     * @param event
     */
    @FXML
    private void add_dostawca_f(ActionEvent event) {

        Dostawca nowy = new Dostawca();
        Restauracja.getDostawcy().add(nowy);
        if (nowy.getKatPrawaJazdy().equals("B")) {
            nowy.narysujnamapie_auto(mapa);
            nowy.getRysunek().setOnMouseClicked(new ZarzadzanieDostawcami(Wyswietlacz, zmienne));
            Thread thread = new Thread(nowy);
            thread.start();
            nowy.setThread(thread);
        } else {
            nowy.narysujnamapie_skuter(mapa);
            nowy.getRysunek().setOnMouseClicked(new ZarzadzanieDostawcami(Wyswietlacz, zmienne));
            Thread thread = new Thread(nowy);
            thread.start();
            nowy.setThread(thread);

        }
    }

    /**
     * funkcja nakazujaca wrocic kliknietemu klientowi na "parking" przed
     * restauracja
     *
     * @param event
     */
    @FXML
    private void wroc_f(ActionEvent event) {

        Dostawca nowy = zmienne.getOstatnidostawca();

        nowy.setWroc(true);

    }

    /**
     * funkcja dodajaca zamowienie do listy zamowien w restauracji
     *
     * @param event
     */
    @FXML
    private void add_zamowienie_f(ActionEvent event) {
        this.Wyswietlacz.clear();
        Zamowienie nowe = new Zamowienie(zmienne);
        zmienne.getZamowienia().add(nowe);
        // this.Wyswietlacz.appendText("Koszt zamowienie : " + nowe.getCena());
        // this.Wyswietlacz.appendText("Koszt dowozu : " + nowe.getCena_dowozu());

    }

    /**
     * funkcja usuwajaca dostawce z listy dostawcow , zwracajaca nierozwiezione
     * przez niego zamowienia do glownej listy zamowien w restauracji, usuwajaca
     * jego grafike i watek
     *
     * @param event
     */
    @FXML
    private void rm_dostawca_f(ActionEvent event) {
        this.Wyswietlacz.clear();
        Dostawca nowy = zmienne.getOstatnidostawca();
        nowy.zwroc_nierozwiezione();
        nowy.getRysunek().setOnMouseClicked(null);
        this.mapa.getChildren().remove(nowy.getRysunek());
        nowy.getThread().interrupt();
        zmienne.getDostawcy().remove(nowy);
        this.Wyswietlacz.clear();

    }

    /**
     * Funkcja inicijalizuja restaurace i zmieniajaca kolor mapy
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zmienne = new Restauracja();
        mapa.setStyle("-fx-background-color: #00FF99");
       
    }


}
