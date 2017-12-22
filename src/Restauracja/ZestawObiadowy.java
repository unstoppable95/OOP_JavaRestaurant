/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restauracja;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Piotr klas zestaw obiadowy
 */
public class ZestawObiadowy {

    private double Cena;

    private ArrayList<Posilek> Zestawobiadowy = new ArrayList();

    /**
     * konstukor zestawu obiadowego losuje liczbe posilkow wchodzacych w sklad
     * zestawu dodaje je i ich ceny do ceny calosc a nastepnie uwzlednia znizke
     * 20% na cene zestawu
     *
     */
    public ZestawObiadowy() {
        Cena = 0;
        Random losuj = new Random();
        int liczba_posilkow_zestaw = losuj.nextInt(5) + 1;
        for (int i = 0; i < liczba_posilkow_zestaw; i++) {
            Posilek nowy = new Posilek();
            Zestawobiadowy.add(nowy);
            Cena += nowy.getCena();

        }
        Cena = 0.8 * Cena;

    }

    /**
     *
     * zwraca cene zestawu obiadowego
     * @return 
     */
    public double getCena() {
        return Cena;
    }

}
