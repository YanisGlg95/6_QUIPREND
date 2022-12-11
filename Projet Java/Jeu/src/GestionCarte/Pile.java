package GestionCarte;

import java.util.ArrayList;

public class Pile {

    private ArrayList<Carte> serie;
    private int tailleSerie;

  public Pile(Carte carte){
      poser(carte);
      tailleSerie =1;
  }
  public void poser(Carte carte){
      serie.add(carte);
      tailleSerie++;
  }
  public String affichageSerie(){
      String liste = new String();
      for(int i =0;i<serie.size();i++){
          liste += serie.get(i).getNumero();
      }
return liste;
  }


    public int getTailleSerie() {
        return tailleSerie;
    }

    public void setTailleSerie(int tailleSerie) {
        this.tailleSerie = tailleSerie;
    }

    public ArrayList<Carte> getSerie() {
        return serie;
    }

    public void setSerie(ArrayList<Carte> serie) {
        this.serie = serie;
    }
}
