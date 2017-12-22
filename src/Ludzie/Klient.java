/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ludzie;

import Glowna.Restauracja;
import Restauracja.Zamowienie;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Piotr klasa klienta
 */
public class Klient implements Runnable {

    private String nazwa;
    private String adres_dostawy;
    private Restauracja zmienne;
    private int wspolrzednaX;
    private int wspolrzednaY;
    private Thread thread;

    /**
     * funkcja losuja i zwracaja nazwe klienta
     *
     */
    private String nazwalosuj() {
        Random losuj = new Random();
        String[] klient_nazwa = {"RCKiK", "PUK", "WK", "ISANA", "OAZA", "SAMSUNG", "MIASTO_POZNAN", "NESCAFE", "ORLEN", "SAGUARO"};
        int indeks_nazwy;
        indeks_nazwy = losuj.nextInt(klient_nazwa.length);
        String nazwa = klient_nazwa[indeks_nazwy];

        return nazwa;

    }

    /**
     *
     * funkcja losujaca i zwracajaca adres klienta
     */
    private String zwrocadres() {
        Random losuj = new Random();
        String[] nazwy_tab = {"Kilinskiego", "Naramowicka", "Barska", "Bialostoka", "Bielniki", "Blawatkowa", "Bukowska", "Bulgarska"};
        int indeks_ulicy;
        indeks_ulicy = losuj.nextInt(nazwy_tab.length);
        String nazwa_ulicy = nazwy_tab[indeks_ulicy];
        return nazwa_ulicy;
    }

    /**
     * funkcja sprawdzajaca czy w tablicy polozenia klientow moge dodac klienta
     * pod wspolrzednymi (a,b) rozszerzajac te wspolrzedne o rozmiary grafiki
     * klientow
     *
     * @param a
     * @param b
     * @param tablica
     * @return
     */
    private boolean czywolne(int a, int b, boolean tablica[][]) {
        int p = a + 15;
        int z = b + 15;
        boolean check = true;
        for (int i = a; i < p; i++) {
            for (int j = b; j < z; j++) {
                if (tablica[i][j]) {
                    check = false;
                    break;
                }
            }
        }
        if (!check) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * funkcja wypelniajaca tablice polozenia klientow wg wylosowanych
     * wspolrzednych (x,y) oraz rozmiarow grafiki + 6 pikseli do kazdej
     * wspolrzednej dla zachowania przejrzystosci i braku wizualnego nakladania
     * sie na siebie klientow na mapie
     *
     * @param x
     * @param y
     * @param tablica
     */
    private void wypelnij(int x, int y, boolean tablica[][]) {
        int p = x + 20;
        int z = y + 20;
        for (int i = x; i < p; i++) {
            for (int j = y; j < z; j++) {
                tablica[i][j] = true;
            }
        }
    }

    /**
     * kostruktor klienta , losujacy oraz zwracajacy jego pola
     *
     * @param z
     */
    public Klient(Restauracja z) {
        Random losuj = new Random();
        int numerdomu = losuj.nextInt(100) + 1;
        nazwa = nazwalosuj();
        adres_dostawy = zwrocadres() + " " + Integer.toString(numerdomu);
        zmienne = z;
        boolean zip;
        for (int i = 0; i < 1;) {

            wspolrzednaX = losuj.nextInt(380);

            if (wspolrzednaX > 305) {
                wspolrzednaY = losuj.nextInt(315);
            }
            wspolrzednaY = losuj.nextInt(380);

            zip = czywolne(wspolrzednaX, wspolrzednaY, z.getTablica());

            if (!zip) {

                wypelnij(wspolrzednaX, wspolrzednaY, z.getTablica());

                i++;
            }

        }

    }

    /**
     *
     * zwraca adres dostawy
     * @return 
     */
    public String getAdres_dostawy() {
        return adres_dostawy;
    }

    /**
     * ustawia adres dostawy
     *
     * @param adres_dostawy
     */
    public void setAdres_dostawy(String adres_dostawy) {
        this.adres_dostawy = adres_dostawy;
    }

    /**
     *
     * zwraca wspolrednia X klienta
     * @return 
     */
    public int getWspolrzednaX() {
        return wspolrzednaX;
    }

    /**
     *
     * zwraca wspolrednia Y klienta
     * @return 
     */
    public int getWspolrzednaY() {
        return wspolrzednaY;
    }

    /**
     *
     * zwraca nazwe klienta
     * @return 
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * funkcja rysujaca klienta na mapie , przeslaniana w klasach podrzednych
     *
     * @param mapa
     */
    public void narysujnamapie(AnchorPane mapa) {

    }

    /**
     * funkcja realizujaca watek klienta tworzaca nowe zamowienia samoczynnie po
     * okreslonym czasie
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Zamowienie nowe = new Zamowienie(zmienne);

            zmienne.getZamowienia().add(nowe);
            try {

                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Klient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * zwraca zmienna watek
     * @return 
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * ustawia zmienna watek
     *
     * @param thread
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
