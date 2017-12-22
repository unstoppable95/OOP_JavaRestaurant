/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojazdy;

import java.util.Random;

/**
 *
 * @author Piotr klasa samochod dziedzicza z klasy pojazd
 */
public class Samochod extends Pojazd {

    private String marka;
    private int predkosc;

    /**
     * funkcja losujaca i zwracajaca marke samochodu
     */
    private String markalosuj() {
        Random losuj = new Random();
        String[] marka_tab = {"renault", "mercedes", "ferrari", "opel", "BMW", "Fiat", "Masseratti", "Porsche"};
        int indeks_auta;
        indeks_auta = losuj.nextInt(marka_tab.length);
        String marka = marka_tab[indeks_auta];

        return marka;

    }

    /**
     * konstrukor samochodu ustawia predkosc oraz ustawia pojemnosc baku
     */
    public Samochod() {
        super();
        marka = markalosuj();
        Random losuj = new Random();
        predkosc = 20;
        setPojemnoscbaku(50.0);

    }

    /**
     *
     * funkcja zwracajaca predkosc
     * @return 
     */
    public int getPredkosc() {
        return predkosc;
    }

    /**
     *
     * @return 
     * @funkcja zwracajaca marke samochodu
     */
    public String getMarka() {
        return marka;
    }

}
