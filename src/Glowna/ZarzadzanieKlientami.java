/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Glowna;

import Ludzie.KlientFirmowy;
import Ludzie.KlientOkazjonalny;
import Ludzie.KlientStaly;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Piotr klasa odpowiadajaca za wyswietlanie informacji o kliknietym
 * kliencie
 *
 */
public class ZarzadzanieKlientami implements EventHandler<MouseEvent> {

    private TextArea Wyswietlacz;
    private Restauracja restauracja;

    /**
     *
     * @param X
     * @param restauracja1
     */
    public ZarzadzanieKlientami(TextArea X, Restauracja restauracja1) {
        this.Wyswietlacz = X;
        this.restauracja = restauracja1;
    }

    /**
     * funkcja wyswietlajaca informacje o wybranym kliencie rozroznia typ
     * klienta, i na jego postawie pobiera odpowiednie pola po wyswietleniu
     * danego klienta ustawia go jako ostatnio wybranego
     *
     * @param event
     */
    @Override
    public void handle(MouseEvent event) {
        Wyswietlacz.clear();
        if (((Node) event.getSource()).getUserData() instanceof KlientOkazjonalny) {
            KlientOkazjonalny t = (KlientOkazjonalny) ((Node) event.getSource()).getUserData();
            this.Wyswietlacz.appendText("Nazwa : " + t.getNazwa());
            this.Wyswietlacz.appendText("\nAdres dostawy : " + t.getAdres_dostawy());
            this.Wyswietlacz.appendText("\nEmail : " + t.getEmail());
            this.Wyswietlacz.appendText("\nTelefon : " + t.getNumer_tel());
            this.Wyswietlacz.appendText("\nKod : " + t.getKod());
            this.Wyswietlacz.appendText("\nGodzina zamowienia : " + t.getGodzinazamowienia());
            restauracja.setOstatniklient(t);

        }

        if (((Node) event.getSource()).getUserData() instanceof KlientFirmowy) {
            KlientFirmowy t = (KlientFirmowy) ((Node) event.getSource()).getUserData();
            this.Wyswietlacz.appendText("Nazwa : " + t.getNazwa());
            this.Wyswietlacz.appendText("\nAdres dostawy : " + t.getAdres_dostawy());
            this.Wyswietlacz.appendText("\nAdres korespondencji : " + t.getAdres_korespondencji());
            this.Wyswietlacz.appendText("\nNR KONTA: " + t.getNr_konta());
            this.Wyswietlacz.appendText("\nREGON : " + t.getREGON());
            restauracja.setOstatniklient(t);

        }
        if (((Node) event.getSource()).getUserData() instanceof KlientStaly) {
            KlientStaly t = (KlientStaly) ((Node) event.getSource()).getUserData();
            this.Wyswietlacz.appendText("Nazwa : " + t.getNazwa());
            this.Wyswietlacz.appendText("\nAdres dostawy : " + t.getAdres_dostawy());
            this.Wyswietlacz.appendText("\nPunkty lojalnosciowe : " + t.getPunkty_lojalnosciowe());
            restauracja.setOstatniklient(t);

        }
    }

}
