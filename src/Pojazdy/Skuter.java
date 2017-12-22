/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojazdy;

import java.util.Random;

/**
 * @author Piotr klasa skuter dziedziczaca z pojazdu
 */
public class Skuter extends Pojazd {

    private String kolor;
    private int predkosc;

    /**
     *
     * funkcja losujaca i zwracajaca kolor skuteru
     */
    private String wylosujkolor() {
        Random losuj = new Random();
        String[] tab_kolor = {"czarny", "niebieski", "zielony", "brazowy", "srebrny", "fioletowy", "bezowy"};
        int indeks_koloru;
        indeks_koloru = losuj.nextInt(tab_kolor.length);
        String kolor = tab_kolor[indeks_koloru];

        return kolor;

    }

    /**
     * konstruktor skuter losuje kolor , ustawia predkosc i pojemnosc baku
     */
    public Skuter() {
        super();
        kolor = wylosujkolor();
        setPojemnoscbaku(25.0);
        predkosc = 40;

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
     * funkcja zwracajaca kolor skuteru
     * @return 
     */
    public String getKolor() {
        return kolor;
    }
}
