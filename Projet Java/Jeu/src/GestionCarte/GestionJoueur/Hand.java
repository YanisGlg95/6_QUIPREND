package GestionCarte.GestionJoueur;

import GestionCarte.Carte;

import java.util.ArrayList;
import java.util.Collections;


public class Hand {

    ArrayList<Carte> main;

    public Hand() {
        main = new ArrayList<>(10);
    }

    public void retirer(Carte c) {
        assert (!main.isEmpty());
        main.remove(indexCarte(c));
    }

    public void piocher(Carte c) {
        main.add(c);
        Collections.sort(this.main, Carte::CompareTo);
    }

    public int nbCartesMain() {
        return main.size();
    }

    public boolean appartientMain(int numCarte, Joueur j) {
        boolean present = false;
        for (Carte carteTmp : j.getMain().main) {
            if (carteTmp.getNum() == numCarte) {
                present = true;
            }
        }
        return present;
    }

    private int indexCarte(Carte c) {
        for (int i = 0; i < 10; i++) {
            if (c.getNum() == main.get(i).getNum()) {
                return i;
            }
        }
        return -1;
    }
}

