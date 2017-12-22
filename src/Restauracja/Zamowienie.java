/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restauracja;

import Glowna.Restauracja;
import Ludzie.Klient;
import Ludzie.KlientStaly;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;

/**
 * klasa zamowienie
 *
 * @author Piotr
 */
public class Zamowienie {

    private double Cena;
    private double Cena_dowozu;
    private Klient nazwaklienta;
    private ArrayList<Posilek> Posilki = new ArrayList();
    private ArrayList<ZestawObiadowy> Zestawy = new ArrayList();

    /**
     * konstrukor zamowienia losuje ilosc posilkow oraz zestaw obiadowych
     * wystepujacych w zamowieniu dodaje ich ceny to ceny zamowienia oraz
     * oblicza koszt dostawy w przypadku klient stalego uwzglednia stala znizke
     * na zamowienia oraz ilosc pkt lojalnosciowych
     *
     * @param zmienna
     */
    public Zamowienie(Restauracja zmienna) {
        Cena = 0.0;
        Cena_dowozu = 0.0;
        Random losuj = new Random();
        int ilosc_posilkow = losuj.nextInt(5) + 1;
        int ilosc_zestawow = losuj.nextInt(5) + 1;
        for (int i = 0; i < ilosc_posilkow; i++) {
            Posilki.add(new Posilek());
            Cena += Posilki.get(i).getCena();
        }
        for (int i = 0; i < ilosc_zestawow; i++) {
            Zestawy.add(new ZestawObiadowy());
            Cena += Zestawy.get(i).getCena();
        }

        int indeks_klienta = losuj.nextInt(Restauracja.getKlienci().size());

        nazwaklienta = Restauracja.getKlienci().get(indeks_klienta);

        if (nazwaklienta instanceof KlientStaly) {

            Cena = (((KlientStaly) nazwaklienta).getStalaznizka()) * Cena;

            if (((KlientStaly) nazwaklienta).getPunkty_lojalnosciowe() >= 100) {
                Cena -= 100;
                ((KlientStaly) nazwaklienta).setPunkty_lojalnosciowe(0);
            } else {
                ((KlientStaly) nazwaklienta).setPunkty_lojalnosciowe(100);

            }
        }

        if (abs(nazwaklienta.getWspolrzednaX() - 340) > 120 || Cena < 500 || abs(nazwaklienta.getWspolrzednaY() - 350) > 120) {
            Cena_dowozu = 100;
        }

        Cena += Cena_dowozu;

    }

    /**
     *
     * zwraca nazwe klienta
     * @return 
     */
    public Klient getNazwaklienta() {
        return nazwaklienta;
    }

    /**
     * zwraca cene zamowienia
     * 
     * @return 
     */
    public double getCena() {
        return Cena;
    }

    /**
     * ustawia cene zamowienia
     * @param Cena
     */
    public void setCena(int Cena) {
        this.Cena = Cena;
    }

    /**
     * zwraca cene dowozu
     * @return 
     */
    public double getCena_dowozu() {
        return Cena_dowozu;
    }

    /**
     * ustawia cene dowozu
     * @param Cena_dowozu
     */
    public void setCena_dowozu(int Cena_dowozu) {
        this.Cena_dowozu = Cena_dowozu;
    }

}
