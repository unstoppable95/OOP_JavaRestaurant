/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Glowna;

import Ludzie.Dostawca;
import Ludzie.Klient;
import Restauracja.Zamowienie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Piotr Klasa przechowujaca informacje o klientach,dostawcach ,
 * zamowieniach oraz przechowujaca ostatnio kliknietego dostawce i klienta
 */
public class Restauracja {

    private static boolean[][] tablica = new boolean[400][400];

    private static List<Zamowienie> Zamowienia;

    private static List<Dostawca> Dostawcy;

    private static List<Klient> Klienci;

    private Dostawca Ostatnidostawca;

    private Klient Ostatniklient;

    /**
     *
     * zwraca ostatnio kliknietego dostawce
     * @return 
     */
    public Dostawca getOstatnidostawca() {
        return Ostatnidostawca;
    }

    /**
     *
     * zwraca ostatniego kliknietego klienta
     * @return 
     */
    public Klient getOstatniklient() {
        return Ostatniklient;
    }

    /**
     *
     * @param Ostatniklient ustawia ostatnio kliknietego klienta
     */
    public void setOstatniklient(Klient Ostatniklient) {
        this.Ostatniklient = Ostatniklient;
    }

    /**
     * ustawia liste zamowien
     *
     * @param Zamowienia
     */
    public static void setZamowienia(List<Zamowienie> Zamowienia) {
        Restauracja.Zamowienia = Zamowienia;
    }

    /**
     * ustawia liste dostawcow
     *
     * @param Dostawcy
     */
    public static void setDostawcy(List<Dostawca> Dostawcy) {
        Restauracja.Dostawcy = Dostawcy;
    }

    /**
     * ustawia liste klientow
     *
     * @param Klienci
     */
    public static void setKlienci(List<Klient> Klienci) {
        Restauracja.Klienci = Klienci;
    }

    /**
     *
     * zwraca liste zamowien
     * @return 
     */
    public static synchronized List<Zamowienie> getZamowienia() {
        return Zamowienia;
    }

    /**
     * zwraca liste dostawcow
     *
     * @return
     */
    public static  synchronized List<Dostawca> getDostawcy() {
        return Dostawcy;
    }

    /**
     * zwraca liste klientow
     *
     * @return
     */
    public static synchronized List<Klient> getKlienci() {
        return Klienci;
    }

    /**
     * tworzy nowa arrayliste zamowien
     */
    public static void add_Zamowienia() {
        setZamowienia(new ArrayList<>());

    }

    /**
     * tworzy nowa arrayliste dostawcow
     */
    public static void add_Dostawcy() {
        setDostawcy(new ArrayList<>());

    }

    /**
     * tworzy nwoa arrayliste klientow
     */
    public static void add_Klienci() {
        setKlienci(new ArrayList<>());

    }
    /**
     * wypelnia pomocnicza tablice (rozmiarow anchorpane) poloz klientow na false
     * nastepnie pozycje mapy ustawia na true ( zeby klient sie tam nie pojawil )
     */
    public static void Wypelnijtablice() {
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                tablica[i][j] = false;
            }
        }
        for (int i = 320; i < 400; i++) {
            for (int j = 330; j < 400; j++) {
                tablica[i][j] = true;
            }
        }
    }
    /**
     * ustawia tablice pomocnicza polozen klientow
     * @param tablica 
     */
    public static void setTablica(boolean[][] tablica) {
        Restauracja.tablica = tablica;
    }
    /**
     * 
     * zwraca tablicze pomocnicza klientow
     * 
     * @return 
     */
    public boolean[][] getTablica() {
        return tablica;
    }

    /**
     * ustawia ostatniegodostawce 
     * @param Ostatnidostawca 
     */
    public void setOstatnidostawca(Dostawca Ostatnidostawca) {
        this.Ostatnidostawca = Ostatnidostawca;
    }

}
