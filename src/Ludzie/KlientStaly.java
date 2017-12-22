/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ludzie;

import Glowna.Restauracja;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Piotr klasa klienta stalego dziedziczaca z klienta ostale poczatkowe
 * punkty lojalnosciowe i nadaje znizke stala dla klienta stalego
 */
public class KlientStaly extends Klient {

    private int punkty_lojalnosciowe;
    private ImageView rysunek;
    private int stalaznizka;

    /**
     *
     * @param zmienne
     */
    public KlientStaly(Restauracja zmienne) {
        super(zmienne);
        punkty_lojalnosciowe = 0;
        stalaznizka = 5;

    }

    /**
     *
     * zwraca punkty lojalnosciowe
     * @return 
     */
    public int getPunkty_lojalnosciowe() {
        return punkty_lojalnosciowe;
    }

    /**
     *
     * ustawia punkty lojalnosciowe
     * @param punkty_lojalnosciowe
     */
    public void setPunkty_lojalnosciowe(int punkty_lojalnosciowe) {
        this.punkty_lojalnosciowe = punkty_lojalnosciowe;
    }

    /**
     * przeslania metode rysowania na mapie rysuje klienta zgodnie z jego
     * pozycja i dodaje odpowiednia grafike
     *
     * @param mapa
     */
    public void narysujnamapie(AnchorPane mapa) {

        this.rysunek = new ImageView();
        rysunek.setImage(new Image("/Grafika/klient_staly.png"));
        getRysunek().setLayoutX(super.getWspolrzednaX());
        getRysunek().setLayoutY(super.getWspolrzednaY());
        getRysunek().setUserData(this);
        mapa.getChildren().add(getRysunek());

    }

    /**
     *
     * zwraca grafike dla klienta stalego
     * @return 
     */
    public ImageView getRysunek() {
        return rysunek;
    }

    /**
     * zwraca stalaznizke
     * @return 
     */
    public int getStalaznizka() {
        return stalaznizka;
    }

    /**
     * ustawia stala znizke
     * @param stalaznizka
     */
    public void setStalaznizka(int stalaznizka) {
        this.stalaznizka = stalaznizka;
    }

}
