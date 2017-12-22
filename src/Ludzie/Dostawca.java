/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ludzie;

import EnumyDostepne.DostepneKatPrawaJazdy;
import EnumyDostepne.DostepneDniTygodnia;
import Glowna.Restauracja;
import Pojazdy.Pojazd;
import Pojazdy.Samochod;
import Pojazdy.Skuter;
import Restauracja.Zamowienie;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Piotr Klasa dostawcy
 */
public class Dostawca extends Czlowiek implements Runnable {

    private int PESEL;
    private ArrayList<Zamowienie> akutalnierozwozone = new ArrayList();
    private String[] dniPracy1 = new String[4];
    private String dniPracy;
    private int startPracy;
    private int koniecPracy;
    private String katPrawaJazdy;
    private Pojazd pojazd;
    private boolean wroc;
    private Thread thread;
    private ImageView rysunek;

    /**
     * konstuktor dostawcy losuje poszczegolne pola , na podstawie wylosowanej
     * kategori prawo jazdy przydziela rowniez pojazd
     */
    public Dostawca() {
        super();
        wroc = false;
        int indeks_dnia;

        Random losowanie = new Random();
        PESEL = losowanie.nextInt(111111111) + 1000;

        ArrayList<DostepneDniTygodnia> dnitygodnia = new ArrayList();
        dnitygodnia.addAll(Arrays.asList(DostepneDniTygodnia.values()));

        for (int i = 0; i < 4; i++) {
            indeks_dnia = losowanie.nextInt(dnitygodnia.size());
            if (i == 3) {
                dniPracy1[i] = dnitygodnia.get(indeks_dnia).toString();
                dnitygodnia.remove(indeks_dnia);
                break;
            }
            dniPracy1[i] = dnitygodnia.get(indeks_dnia).toString() + ",";
            dnitygodnia.remove(indeks_dnia);

        }
        String delimiter = "";
        dniPracy = String.join(delimiter, dniPracy1);

        startPracy = losowanie.nextInt(12) + 7;
        koniecPracy = losowanie.nextInt(24) + 12;

        ArrayList<DostepneKatPrawaJazdy> kategorie = new ArrayList();
        kategorie.addAll(Arrays.asList(DostepneKatPrawaJazdy.values()));
        int indeks_kategori_prawo_jazdy = losowanie.nextInt(kategorie.size());

        katPrawaJazdy = kategorie.get(indeks_kategori_prawo_jazdy).toString();
        if ("A".equals(katPrawaJazdy)) {
            pojazd = new Skuter();
        } else {
            pojazd = new Samochod();
        }
    }

    /**
     *
     * zwraca pojazd
     * @return 
     */
    public Pojazd getPojazd() {
        return pojazd;
    }

    /**
     * rysuje dostawce na mapie, zgodnie z odpowiednia grafika co do typu
     * posiadanego pojazdu
     *
     * @param mapa
     */
    public void narysujnamapie_auto(AnchorPane mapa) {
        this.rysunek = new ImageView();
        rysunek.setImage(new Image("/Grafika/auto.png"));
        getRysunek().setLayoutX(320);
        getRysunek().setLayoutY(350);
        getRysunek().setUserData(this);
        mapa.getChildren().add(getRysunek());

    }

    /**
     * rysuje dostawce na mapie, zgodnie z odpowiednia grafika co do typu
     * posiadanego pojazdu
     *
     * @param mapa
     */
    public void narysujnamapie_skuter(AnchorPane mapa) {
        this.rysunek = new ImageView();
        rysunek.setImage(new Image("/Grafika/skuter.png"));
        getRysunek().setLayoutX(310);
        getRysunek().setLayoutY(340);
        getRysunek().setUserData(this);
        mapa.getChildren().add(getRysunek());

    }

    /**
     *
     * zwraca grafike dostawcy
     * @return 
     */
    public ImageView getRysunek() {
        return rysunek;
    }

    /**
     * kopiuje aktualnemu dostawcy wszyskie zamowienia z dostepnych w
     * restuaracji nastepnie usuwa je z listy dostepnych w restauracji
     */
    public synchronized void skopiuj_zamowienia() {
        while (Restauracja.getZamowienia().size() > 0) {
            akutalnierozwozone.add(Restauracja.getZamowienia().get(0));
            Restauracja.getZamowienia().remove(Restauracja.getZamowienia().get(0));
        }
    }

    /**
     * funkcja zwracajaca nierozwiezione przez dostawce zamowienia z jego listy
     * ( z powodow braku paliwa badz nakazu powrotu do restauracji ) spowrotem
     * do listy zamowien gotowych do rozwiezienia w restauracji ( po jego
     * powrocie do niej )
     */
    public void zwroc_nierozwiezione() {
        while (akutalnierozwozone.size() > 0) {
            Restauracja.getZamowienia().add(akutalnierozwozone.get(0));
            akutalnierozwozone.remove(akutalnierozwozone.get(0));

        }

    }

    /**
     * funkcja odpowiadajaca za rozwozenie przez dostwace posilkow na poczatku
     * dostawce pobiera liste zamowien z restauracji jezeli "prywatna" lista
     * dostawcy jest wieksza od 0 i nie chce on wrocic do restauracji to rozwozi
     * on zamowienia poruszajac sie w kierunku poziomym badz pionowym zaleznie
     * od polozenia docelowego, w przypadku braku paliwa badz nakazu powrotu
     * dostawca wraca do restauracji zwracajac liste nierozwiezionych przez
     * siebie zamowien
     */
    public void dostarcz() {
        skopiuj_zamowienia();

        while (akutalnierozwozone.size() > 0 && this.isWroc() == false) {

            Zamowienie pierwsze = akutalnierozwozone.get(0);

            while ((pierwsze.getNazwaklienta().getWspolrzednaX() < this.getRysunek().getLayoutX()) && this.isWroc() == false) {
                if (this.getPojazd().getPojemnoscbaku() > 0) {
                    this.getRysunek().setLayoutX(this.getRysunek().getLayoutX() - 1);
                    this.getPojazd().setPojemnoscbaku(this.getPojazd().getPojemnoscbaku() - 0.001);

                    try {
                        sleep(this.getPojazd().getPredkosc());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    this.wroc_parking();
                    this.zwroc_nierozwiezione();

                }
            }
            while ((pierwsze.getNazwaklienta().getWspolrzednaY() < this.getRysunek().getLayoutY()) && this.isWroc() == false) {
                if (this.getPojazd().getPojemnoscbaku() > 0) {
                    this.getRysunek().setLayoutY(this.getRysunek().getLayoutY() - 1);
                    this.getPojazd().setPojemnoscbaku(this.getPojazd().getPojemnoscbaku() - 0.001);
                    try {
                        sleep(this.getPojazd().getPredkosc());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    this.wroc_parking();
                    this.zwroc_nierozwiezione();

                }

            }

            while (pierwsze.getNazwaklienta().getWspolrzednaX() > this.getRysunek().getLayoutX() && this.isWroc() == false) {
                if (this.getPojazd().getPojemnoscbaku() > 0) {
                    this.getRysunek().setLayoutX(this.getRysunek().getLayoutX() + 1);
                    this.getPojazd().setPojemnoscbaku(this.getPojazd().getPojemnoscbaku() - 0.001);
                    try {
                        sleep(this.getPojazd().getPredkosc());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    this.wroc_parking();
                    this.zwroc_nierozwiezione();
                }

            }
            while (pierwsze.getNazwaklienta().getWspolrzednaY() > this.getRysunek().getLayoutY() && this.isWroc() == false) {
                if (this.getPojazd().getPojemnoscbaku() > 0) {
                    this.getRysunek().setLayoutY(this.getRysunek().getLayoutY() + 1);
                    this.getPojazd().setPojemnoscbaku(this.getPojazd().getPojemnoscbaku() - 0.001);
                    try {
                        sleep(this.getPojazd().getPredkosc());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    this.wroc_parking();
                    this.zwroc_nierozwiezione();
                }

            }

            akutalnierozwozone.remove(pierwsze);

        }

        this.wroc_parking();
        this.zwroc_nierozwiezione();
        this.wroc = false;
    }

    /**
     * funkcja odpowiadajaca za powrot na "parking" przed restauracja dostawcy
     * jako wspolrzedne docelowe ustawione ma wspolrzedne " parkingu ", w
     * przypadku powrotu tankuje rowniez pojazd odpowiednia iloscia paliwa dla
     * kazdego typu
     */
    public void wroc_parking() {
        while (320 < this.getRysunek().getLayoutX()) {

            this.getRysunek().setLayoutX(this.getRysunek().getLayoutX() - 1);

            try {
                sleep(this.getPojazd().getPredkosc());
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        while (330 < this.getRysunek().getLayoutY()) {

            this.getRysunek().setLayoutY(this.getRysunek().getLayoutY() - 1);

            try {
                sleep(this.getPojazd().getPredkosc());
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        while (320 > this.getRysunek().getLayoutX()) {

            this.getRysunek().setLayoutX(this.getRysunek().getLayoutX() + 1);

            try {
                sleep(this.getPojazd().getPredkosc());
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        while (330 > this.getRysunek().getLayoutY()) {

            this.getRysunek().setLayoutY(this.getRysunek().getLayoutY() + 1);

            try {
                sleep(this.getPojazd().getPredkosc());
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (this.getKatPrawaJazdy().equals("A")) {
            this.getPojazd().setPojemnoscbaku(25.0);
        } else {
            this.getPojazd().setPojemnoscbaku(50.0);
        }
    }

    /**
     *
     * zwraca zmienna wroc odpowiadajaca za nakaz powrotu do restauracji
     * @return 
     */
    public boolean isWroc() {
        return wroc;
    }

    /**
     *
     * zwraca dni pracy dostawcy
     * @return 
     */
    public String getDniPracy() {
        return dniPracy;
    }

    /**
     *
     * zwraca godzine startu pracy dostawcy
     * @return 
     */
    public int getStartPracy() {
        return startPracy;
    }

    /**
     *
     * zwraca godzine konca pracy dostawcy
     * @return 
     */
    public int getKoniecPracy() {
        return koniecPracy;
    }

    /**
     * zwraca numer PESEL dostawcy
     * @return 
     */
    public int getPESEL() {
        return PESEL;
    }

    /**
     * ustawia PESEL dostawcy
     * @param PESEL
     */
    public void setPESEL(int PESEL) {
        this.PESEL = PESEL;
    }

    /**
     * zwraca katPrawaJazdy
     * @return 
     */
    public String getKatPrawaJazdy() {
        return katPrawaJazdy;
    }

    /**
     * ustawia kategorie prawojazdy
     *
     * @param katPrawaJazdy
     */
    public void setKatPrawaJazdy(String katPrawaJazdy) {
        this.katPrawaJazdy = katPrawaJazdy;
    }

    /**
     * funkcja odpowiedzialna za realizacje watku dostawcy
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            dostarcz();
        }

    }

    /**
     * ustawia zmienna odpowiadajaca za nakaz powrotu do restauracji
     *
     * @param wroc
     */
    public void setWroc(boolean wroc) {
        this.wroc = wroc;
    }

    /**
     * zwraca watek dostawcy
     *
     * @return thread
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * ustawia watek dostawcy
     *
     * @param thread
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

}
