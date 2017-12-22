/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ludzie;

import Glowna.Restauracja;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * klasa klienta okazjonalnego dziedziczacego po kliencie
 */
public class KlientOkazjonalny extends Klient {

    private String[] kod1 = new String[9];
    private String kod;
    private String[] numertel1 = new String[9];
    private String numertel;
    private int godzinazamowienia;
    private String email;
    private ImageView rysunek;

    /**
     *
     * funkcja losujaca i zwracajaca adres email
     */
    private String email_los() {
        Random losuj = new Random();

        String[] email_nazwa = {"ROBSON", "ANCIUK", "PITERK", "ISANAI", "SAGUAR", "JAGUAR"};
        int indeks_nazwy;
        indeks_nazwy = losuj.nextInt(email_nazwa.length);
        String nazwa = email_nazwa[indeks_nazwy];
        int cyferki = losuj.nextInt(1000) + 100;
        String email = nazwa + "@" + Integer.toString(cyferki);

        return email;
    }

    /**
     *
     * konstruktor klienta okazjonalnego losuje wartosc odpowiednich pol
     * @param zmienne
     */
    public KlientOkazjonalny(Restauracja zmienne) {
        super(zmienne);

        Random losuj = new Random();
        kod1[0] = "P";
        kod1[1] = "O";
        kod1[2] = "Z";
        for (int i = 3; i < kod1.length; i++) {
            kod1[i] = Integer.toString(losuj.nextInt(10));
        }
        String delimiter = "";
        kod = String.join(delimiter, kod1);
        godzinazamowienia = losuj.nextInt(24);
        for (int i = 0; i < numertel1.length; i++) {
            numertel1[i] = Integer.toString(losuj.nextInt(10));
        }
        numertel = String.join(delimiter, numertel1);
        email = email_los();

    }

    /**
     * zwraca godzine zamowienia
     * @return 
     */
    public int getGodzinazamowienia() {
        return godzinazamowienia;
    }

    /**
     * ustawia godzine zamowienia
     * @param godzinazamowienia
     */
    public void setGodzinazamowienia(int godzinazamowienia) {
        this.godzinazamowienia = godzinazamowienia;
    }

    /**
     * zwraca email
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * ustawia email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * przeslania metode rysujaca na mapie rysuje klienta okazonalnego zgodnie z
     * pozycja na mapie i odpowiednia grafika
     *
     * @param mapa
     */
    public void narysujnamapie(AnchorPane mapa) {

        this.rysunek = new ImageView();
        rysunek.setImage(new Image("/Grafika/klient_okazjonalny.png"));
        getRysunek().setLayoutX(super.getWspolrzednaX());
        getRysunek().setLayoutY(super.getWspolrzednaY());
        getRysunek().setUserData(this);
        mapa.getChildren().add(getRysunek());

    }

    /**
     *
     * zwraca kod
     * @return 
     */
    public String getKod() {
        return kod;
    }

    /**
     *
     * zwraca numer telefonu
     * @return 
     */
    public String getNumer_tel() {
        return numertel;
    }

    /**
     *
     * zwraca grafike klienta okazjonalnego
     * @return 
     */
    public ImageView getRysunek() {
        return rysunek;
    }

}
