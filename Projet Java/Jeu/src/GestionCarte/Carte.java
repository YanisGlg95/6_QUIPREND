package GestionCarte;

import GestionCarte.GestionJoueur.Joueur;

public class Carte{
    public static final int MAX_NUMERO = 104;
    public static final int MIN_NUMERO = 1;
    private int numero;

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    private Joueur joueur;


    private int teteBoeufs;

public Carte(int num){
    assert(Valide(num));
    numero = num;
    if (num % 5 == 0 && num %10 !=0) {
        teteBoeufs=2;
    }
    else if(num % 11 == 0 && num!=55) {
        teteBoeufs=5;
    }
    else if(num %10 ==0) {
        teteBoeufs=3;
    }
    else if(num== 55) {
        teteBoeufs=7;
    }
    else {
        teteBoeufs=1;
    }
}

    public String getNumero() {
    if(this.teteBoeufs != 1){
        return String.valueOf(numero) + " (" +teteBoeufs +")";
    }else
        return String.valueOf(numero);
    }

    public int getNum(){
    return this.numero;
    }

    public static boolean Valide(int numero){
    return numero >= MIN_NUMERO && numero <= MAX_NUMERO;
    }

    public int CompareTo(Carte autreCarte)
    {
        int resultat;
        if (this.numero > autreCarte.numero)
            return 1;
        if (this.numero < autreCarte.numero)
             return -1;
        if (this.numero == autreCarte.numero)
            return 0;
        return 0;
    }
    public int getTeteBoeufs() {
        return teteBoeufs;
    }

    public void setTeteBoeufs(int teteBoeufs) {
        this.teteBoeufs = teteBoeufs;
    }
}
