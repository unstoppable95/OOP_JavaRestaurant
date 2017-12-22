/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ludzie;

import EnumyDostepne.DostepneNazwiska;
import EnumyDostepne.DostepneImiona;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Piotr klasa tworzaca czlowieka
 */
public class Czlowiek {

    private String Imie;
    private String Nazwisko;

    /**
     * konstruktor czlowieka losujacy imie i nazwisko z dostepnych w
     * odpowiednich klasach w paczce EnumyDostepne
     */
    public Czlowiek() {

        int indeks_imienia, indeks_nazwiska;

        ArrayList<DostepneNazwiska> nazwiska = new ArrayList();
        nazwiska.addAll(Arrays.asList(DostepneNazwiska.values()));

        ArrayList<DostepneImiona> imiona = new ArrayList();
        imiona.addAll(Arrays.asList(DostepneImiona.values()));

        Random losowanie = new Random();
        indeks_imienia = losowanie.nextInt(imiona.size());
        indeks_nazwiska = losowanie.nextInt(nazwiska.size());

        Imie = imiona.get(indeks_imienia).toString();
        Nazwisko = nazwiska.get(indeks_nazwiska).toString();
    }

    /**
     * @return the Imie
     */
    public String getImie() {
        return Imie;
    }

    /**
     * @param Imie the Imie to set
     */
    public void setImie(String Imie) {
        this.Imie = Imie;
    }

    /**
     * @return the Nazwisko
     */
    public String getNazwisko() {
        return Nazwisko;
    }

    /**
     * @param Nazwisko the Nazwisko to set
     */
    public void setNazwisko(String Nazwisko) {
        this.Nazwisko = Nazwisko;
    }

}
