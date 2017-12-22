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
 * @author Piotr klasa klienta firmowego dziedziczaca z klienta
 */
public class KlientFirmowy extends Klient {

    private String adres_korespondencji;
    private String[] nr_konta1 = new String[10];
    private String[] REGON1 = new String[5];
    private String REGON;
    private String nr_konta;
    private ImageView rysunek;

    /**
     *
     * zwraca numer konta
     * @return 
     */
    public String getNr_konta() {
        return nr_konta;
    }

    /**
     *
     * zwraca REGON
     * @return 
     */
    public String getREGON() {
        return REGON;
    }

    /**
     *
     * funkcja losujaca i zwracajaca adres korespondencyjny
     */
    private String adreslosuj() {
        Random losuj = new Random();
        String[] adres_kores = {"Poznan", "Wrzesnia", "Warszawa", "Szczecin", "Olsztyn", "Bialystok", "Lublin", "Praga", "Bratyslawa", "Gdansk"};
        int indeks_adresu;
        indeks_adresu = losuj.nextInt(adres_kores.length);
        String nazwakores1 = adres_kores[indeks_adresu];
        String numer = Integer.toString(losuj.nextInt(80) + 1);
        String nazwakores = nazwakores1 + " " + numer;

        return nazwakores;

    }

    /**
     * konstruktor klienta firmowego losuje wszystkie pola
     *
     * @param zmienne
     */
    public KlientFirmowy(Restauracja zmienne) {
        super(zmienne);
        Random losuj = new Random();
        String delimiter = "";
        adres_korespondencji = adreslosuj();
        for (int i = 0; i < nr_konta1.length; i++) {
            nr_konta1[i] = Integer.toString(losuj.nextInt(10));
        }
        nr_konta = String.join(delimiter, nr_konta1);

        for (int i = 0; i < REGON1.length; i++) {
            REGON1[i] = Integer.toString(losuj.nextInt(10));
        }
        REGON = String.join(delimiter, REGON1);

    }

    /**
     * zwraca adres korespodencji
     * @return 
     */
    public String getAdres_korespondencji() {
        return adres_korespondencji;
    }

    /**
     * ustawia adres korespondencji
     * @param adres_korespondencji
     */
    public void setAdres_korespondencji(String adres_korespondencji) {
        this.adres_korespondencji = adres_korespondencji;
    }

    /**
     *
     * przeslonieta metoda rysowania na mapie dla klienta firmowego dodaje
     * odpowiednia grafike do wspolrzednych klienta na mapie
     */
    public void narysujnamapie(AnchorPane mapa) {

        this.rysunek = new ImageView();
        rysunek.setImage(new Image("/Grafika/klient_firmowy.png"));
        getRysunek().setLayoutX(super.getWspolrzednaX());
        getRysunek().setLayoutY(super.getWspolrzednaY());
        getRysunek().setUserData(this);
        mapa.getChildren().add(getRysunek());

    }

    /**
     *
     * funkcja zwracajaca rysunek klienta firmowego
     * @return 
     */
    public ImageView getRysunek() {
        return rysunek;
    }

}
