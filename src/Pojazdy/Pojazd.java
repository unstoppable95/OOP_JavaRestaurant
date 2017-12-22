/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojazdy;

import java.util.Random;

/**
 *
 * @author Piotr klasa pojazd
 */
public class Pojazd {

    private int predkosc;
    private String[] numer_rejestracyjny = new String[6];
    private double pojemnoscbaku;

    /**
     * kostruktor pojazdu losuje odpowiednie pola
     */
    public Pojazd() {
        Random losowanie = new Random();

        predkosc = losowanie.nextInt(70) + 20;
        numer_rejestracyjny[0] = "P";
        numer_rejestracyjny[1] = "O";
        for (int i = 2; i <= 5; i++) {
            numer_rejestracyjny[i] = Integer.toString(losowanie.nextInt(10));
        }
    }

    /**
     * @return the predkosc
     */
    public int getPredkosc() {
        return predkosc;
    }

    /**
     * @return the pojemnoscbaku
     */
    public double getPojemnoscbaku() {
        return pojemnoscbaku;
    }

    /**
     * @param pojemnoscbaku the pojemnoscbaku to set
     */
    public void setPojemnoscbaku(double pojemnoscbaku) {
        this.pojemnoscbaku = pojemnoscbaku;
    }

}
