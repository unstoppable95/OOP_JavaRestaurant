/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Glowna;

import Ludzie.Dostawca;
import Pojazdy.Samochod;
import Pojazdy.Skuter;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Piotr klasa odpowiadajaca za wyswietlania w oknie tekstowym
 * informacji o kliknietym dostawcy
 */
public class ZarzadzanieDostawcami implements EventHandler<MouseEvent> {

    private TextArea Wyswietlacz;
    private Restauracja restauracja;

    /**
     *
     * @param X
     * @param restauracja1
     */
    public ZarzadzanieDostawcami(TextArea X, Restauracja restauracja1) {
        this.Wyswietlacz = X;
        this.restauracja = restauracja1;
    }

    /**
     * funkcja wyswietlajaca na wyswietlaczu informacje o dostawcy pobierajaca
     * poszczegolne pola, zapamietuje ostatnio kliknietego klienta
     *
     * @param event
     */
    @Override
    public void handle(MouseEvent event) {
        Wyswietlacz.clear();

        if (((Node) event.getSource()).getUserData() instanceof Dostawca) {
            Dostawca t = (Dostawca) ((Node) event.getSource()).getUserData();
            this.Wyswietlacz.appendText("Imie : " + t.getImie());
            this.Wyswietlacz.appendText("\nNazwisko : " + t.getNazwisko());
            this.Wyswietlacz.appendText("\nPESEL : " + t.getPESEL());
            this.Wyswietlacz.appendText("\nKategorie prawo jazdy : " + t.getKatPrawaJazdy());
            this.Wyswietlacz.appendText("\nDni pracy : " + t.getDniPracy());
            this.Wyswietlacz.appendText("\nGodziny rozpoczecia pracy : " + t.getStartPracy());
            this.Wyswietlacz.appendText("\nGodziny zakonczenia pracy : " + t.getKoniecPracy());

            if (t.getKatPrawaJazdy().equals("A")) {

                this.Wyswietlacz.appendText("\nPozostala ilosc paliwa : " + (Math.round(t.getPojazd().getPojemnoscbaku() * 100.0) / 100.0));
                this.Wyswietlacz.appendText("\nKolor skuteru : " + ((Skuter) t.getPojazd()).getKolor());
            } else {
                this.Wyswietlacz.appendText("\nPozostala ilosc paliwa : " + (Math.round(t.getPojazd().getPojemnoscbaku() * 100.0) / 100.0));
                this.Wyswietlacz.appendText("\nMarka samochodu : " + ((Samochod) t.getPojazd()).getMarka());
            }
            restauracja.setOstatnidostawca(t);

        }

    }
}
