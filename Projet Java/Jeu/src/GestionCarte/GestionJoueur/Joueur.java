package GestionCarte.GestionJoueur;

import GestionCarte.Carte;
import GestionCarte.Pioche;

public class Joueur {

    private String nomJoueur;

    private Hand main;
    private int nbTetesTotal;
    private int nbNouvelleTetes;



    public static int nbJoueurs=0;
    public Joueur(Pioche p, String nom) {
        main = new Hand();
        Carte carte;
        for (int i = 0; i < 10; i++){
            carte = p.retirer();
            carte.setJoueur(this);
            main.piocher(p.retirer());
        }
        this.nomJoueur = nom;
        nbJoueurs++;
    }


    public String toString() {
        String afficheJeu = String.format("- Vos cartes : ");
        for(int i=0; i< main.nbCartesMain();i++){
            afficheJeu+= main.main.get(i).getNumero();
                    if(i+2!=main.nbCartesMain()){
                        afficheJeu += ", ";
                    }
        }
        return afficheJeu;
    }


 public String getNomJoueur() {
     return nomJoueur;
 }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public int getNbTetesTotal() {
        return nbTetesTotal;
    }

    public void setNbTetesTotal(int nbTetesTotal) {
        this.nbTetesTotal = nbTetesTotal;
    }

    public int CompareTo(Joueur autreJoueur)
    {
        int resultat;
        if (this.nbTetesTotal > autreJoueur.getNbTetesTotal())
            return 1;
        if (this.nbTetesTotal < autreJoueur.getNbTetesTotal())
            return -1;
        if (this.nbTetesTotal == autreJoueur.getNbTetesTotal())
            return 0;
        return 0;
    }
    public int CompareToCoup(Joueur autreJoueur)
    {
        int resultat;
        if (this.nbNouvelleTetes > autreJoueur.getNbNouvelleTetes())
            return 1;
        if (this.nbNouvelleTetes < autreJoueur.getNbNouvelleTetes())
            return -1;
        if (this.nbNouvelleTetes == autreJoueur.getNbNouvelleTetes())
            return 0;
        return 0;
    }

    public Hand getMain() {
        return main;
    }

    public void setMain(Hand main) {
        this.main = main;
    }
    public int getNbNouvelleTetes() {
        return nbNouvelleTetes;
    }

    public void setNbNouvelleTetes(int nbNouvelleTetes) {
        this.nbNouvelleTetes = nbNouvelleTetes;
    }
}




