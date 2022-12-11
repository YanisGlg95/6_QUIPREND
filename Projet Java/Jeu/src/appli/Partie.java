package appli;

import GestionCarte.Carte;
import GestionCarte.GestionJoueur.Joueur;
import GestionCarte.Pile;
import GestionCarte.Pioche;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Partie {
    private Pile[] series;
    private Pioche pioche;
    private ArrayList<Joueur> tabJoueurs;

    public Partie(){
        pioche = new Pioche(Carte.MIN_NUMERO, Carte.MAX_NUMERO);

        initJoueurs();
        if(tabJoueurs.size() <2){
            System.out.println("Nombre de Joueurs insuffisant");
            return;
        }
        ArrayList<Carte> c = new ArrayList<>(4);
        for(int i=0; i<4; i++){
            c.add(pioche.retirer());
        }
        Collections.sort(c, Carte::CompareTo);
        series[1] = new Pile(c.get(0));
        series[2] = new Pile(c.get(1));
        series[3] = new Pile(c.get(2));
        series[4] = new Pile(c.get(3));
        System.out.print("Les "+tabJoueurs.size()+" joueurs sont ");
        for(int i=0; i< tabJoueurs.size();i++){
            System.out.print(" " + tabJoueurs.get(i).getNomJoueur());
            if(i+2== tabJoueurs.size()){
                System.out.println("et");;
            }else if(i+1 != tabJoueurs.size()){
                System.out.println(",");
            }
        }
        System.out.println("Merci de jouer à 6 qui prend !");
        lancerJeu();
        finJeu();
    }


    private void initJoueurs(){
        int nbJoueurs=0;
        try{
            File file = new File("config.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine() && nbJoueurs <10){
                tabJoueurs.add(new Joueur(pioche, sc.nextLine()));
                nbJoueurs ++;
            }
            sc.close();
        }catch(Exception e){
            e.getStackTrace();
        }
    }
public void affichageSerie() {
    System.out.println("- Série n° 1 : " + series[1].affichageSerie());
    System.out.println("- Série n° 2 : " + series[2].affichageSerie());
    System.out.println("- Série n° 3 : " + series[3].affichageSerie());
    System.out.println("- Série n° 4 : " + series[4].affichageSerie());
}
    public int Coup(Joueur j){

    System.out.println("A "+ j.getNomJoueur() + " de jouer.");
        //java.util.Console.pause();
    affichageSerie();
    j.toString();
j.setNbNouvelleTetes(0);
    Scanner sc = new Scanner(System.in);
    String s;
    System.out.print("Saisissez votre choix : ");

    s = sc.nextLine();

    while(!j.getMain().appartientMain(Integer.parseInt(s), j)){
        System.out.println("Vous n'avez pas cette carte, saisissez votre choix : ");
        s = sc.nextLine();
    }
    j.getMain().retirer(new Carte(Integer.parseInt(s)));
    return Integer.parseInt(s);

}

     public void choixSerie(Carte c){

        if(c.getNum()<series[3].getSerie().get(series.length-1).getNum()&&c.getNum()<series[2].getSerie().get(series.length-1).getNum()&&c.getNum()<series[1].getSerie().get(series.length-1).getNum()&&c.getNum()<series[0].getSerie().get(series.length-1).getNum()){
            règle4(c);

        }else {
            int serieFinale=0;
            int numMini = 104;
            for (int i = 0; i < series.length; i++) {
                int num = (c.getNum() - series[i].getSerie().get(series.length - 1).getNum());
                if (num < numMini && num > 0) {
                    numMini = num;
                    serieFinale = i;
                }
            }
            dépotSurSérie(serieFinale, c);
        }


    }
    public void dépotSurSérie(Integer numSerie, Carte c){
        if(series[numSerie].getSerie().size() == 5){
            ramasse(numSerie, c);;
        }else{
        series[numSerie].getSerie().add(c);
}
    }
    public void ramasse(int numSerie, Carte c){
        int nbTeteBoeuf=0;
        for(Carte cTmp : series[numSerie].getSerie()){
            {
                nbTeteBoeuf += cTmp.getTeteBoeufs();
            }
        }
        c.getJoueur().setNbTetesTotal(c.getJoueur().getNbTetesTotal()+ nbTeteBoeuf);
        c.getJoueur().setNbNouvelleTetes(nbTeteBoeuf);

        series[numSerie].getSerie().clear();
        series[numSerie].getSerie().add(c);
    }

    public void règle4(Carte c){
        Scanner sc = new Scanner(System.in);
        String s;
        System.out.println("Pour poser la carte "+ c.getNum()+", "+c.getJoueur().getNomJoueur()+" doit choisir la série qu’il va ramasser");
        affichageSerie();
        System.out.print("Saisissez votre choix : ");

        s = sc.nextLine();

        while(!(Integer.parseInt(s)>=1 &&Integer.parseInt(s)<=4)){
            System.out.println("Ce n'est pas une série valide, saisissez votre choix : ");
            s = sc.nextLine();
        }
        ramasse(Integer.parseInt(s),c);

    }

    public void lancerJeu(){

ArrayList<Carte> tabCoups = new ArrayList<Carte>(tabJoueurs.size());
        for(int i=0;i<10;i++){
            for(Joueur j : tabJoueurs){
                tabCoups.add(new Carte(Coup(j)));
                //écran effacé
                //java.util.Console.clearScreen();
            }
            dépotCarte(tabCoups);
            tabCoups.clear();
        }
    }

public void dépotCarte(ArrayList<Carte> tabCoups){
        int carteMin;
        int min;
        String message;
    message=  "Les cartes";
    for(int k =0;k<tabCoups.size();k++){
        Carte c = tabCoups.get(k);
        message+= c.getNum() + " ("+ c.getJoueur().getNomJoueur() +")";
        if(k+2 != tabCoups.size()){
            message+=", ";
        }
    }
    System.out.println(message +" vont être posées.");
        for(int i=0;i<tabJoueurs.size();i++){
            carteMin = tabCoups.get(0).getNum();
            min=0;
            for(int j=1;j<tabCoups.size();j++){
                if(tabCoups.get(j).getNum()<carteMin){
                    min=j;
                    carteMin = tabCoups.get(j).getNum();
                }
            }
            choixSerie(tabCoups.get(min));
            tabCoups.remove(min);
        }
    System.out.println(message + " ont été posées.");
        affichageSerie();
}
    public void finJeu(){
        System.out.println("** Score final");
        Collections.sort(this.tabJoueurs, Joueur::CompareTo);
        for(Joueur j : tabJoueurs){
            System.out.println(j.getNomJoueur() + " a ramassé " + j.getNbTetesTotal());
            if(j.getNbTetesTotal()>1){
                System.out.println(" têtes de boeufs");
            }else{
                System.out.println(" tête de boeufs");
            }
        }
    }

    public void messageRamassage(){
ArrayList<Joueur> tabInit= tabJoueurs;
        Collections.sort(this.tabJoueurs, Joueur::CompareToCoup);
        boolean tetes = false;
        for(Joueur j: tabJoueurs){
             if(j.getNbNouvelleTetes()>0){
                 tetes = true;
                 System.out.print(j.getNomJoueur()+ " a ramassé " +j.getNbNouvelleTetes());
                 if(j.getNbNouvelleTetes()>1){
                     System.out.println(" têtes de boeufs");
                 }else{
                     System.out.println(" tête de boeufs");
                 }
             }
        }
        if(!tetes){
            System.out.println("Aucun joueur ne ramasse de têtes de boeufs");
        }
        tabJoueurs = tabInit;

    }

}
