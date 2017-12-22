/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restauracja;

import EnumyDostepne.DostepnePosilki;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * klasa posilek losujaca informacje o posilki
 *
 * @author Piotr
 */
public class Posilek {

    private String nazwa;
    private String lista_skladnikow;
    private int cena;
    private String kategoria_posilkow;
    private String rozmiar_porcji;

    /**
     *
     * funkcja losujaca i zwracaja rozmiar porcji danego posilku
     * @return 
     */
    public String rozmiarporcji() {
        Random losuj = new Random();
        String[] rozmiary = {"mala", "srednia", "duza", "podwojna"};
        int indeks_rozmiaru;
        indeks_rozmiaru = losuj.nextInt(rozmiary.length);
        String rozmiarporcji = rozmiary[indeks_rozmiaru];
        return rozmiarporcji;

    }

    /**
     * konstrukor posilku wybiera posilek z dostepnych posilkow z klasy enum,
     * nastepnie dodaje do odpowidniej kategori wylosowany posilek oraz generuje
     * cene posilku
     */
    public Posilek() {
        Random losuj = new Random();

        ArrayList<DostepnePosilki> wyborposilkow = new ArrayList();
        wyborposilkow.addAll(Arrays.asList(DostepnePosilki.values()));
        int indeks_posilku = losuj.nextInt(wyborposilkow.size());

        nazwa = wyborposilkow.get(indeks_posilku).toString();

        if (indeks_posilku <= 2) {
            kategoria_posilkow = "Pizze";
        } else if (indeks_posilku <= 5) {
            kategoria_posilkow = "Zupy";
        } else if (indeks_posilku <= 8) {
            kategoria_posilkow = "Zestaw obiadowy";
        } else {
            kategoria_posilkow = "Desery";
        }

        rozmiar_porcji = rozmiarporcji();

        if (indeks_posilku <= 2) {
            lista_skladnikow = "maka,woda,drozdze,ser,szynka,sos pomidorowy";
        } else if (indeks_posilku <= 5) {
            lista_skladnikow = "woda,warzywa,przyprawy,mieso";
        } else if (indeks_posilku <= 8) {
            lista_skladnikow = "mieso,przyprawy,ziemniaki lub frytki ";
        } else {
            lista_skladnikow = "lody, bita smietana, owoce , cukier , sos";
        }

        cena = losuj.nextInt(200) + 1;

    }

    /**
     * zwraca nazwe posilku
     * @return 
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * ustawia nazwe posilku
     *
     * @param nazwa
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * zwraca cene posilku
     * @return 
     */
    public int getCena() {
        return cena;
    }

    /**
     * ustawia cene posilku
     *
     * @param cena
     */
    public void setCena(int cena) {
        this.cena = cena;
    }

    /**
     * zwraca kategorie posilku
     * @return 
     */
    public String getKategoria_posilkow() {
        return kategoria_posilkow;
    }

    /**
     * ustawia kategorie posilkow
     * @param kategoria_posilkow
     */
    public void setKategoria_posilkow(String kategoria_posilkow) {
        this.kategoria_posilkow = kategoria_posilkow;
    }

}
