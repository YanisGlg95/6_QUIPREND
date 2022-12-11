package GestionCarte;
import java.util.*;

public class Pioche {
    Queue<Carte> cartes;

    public Pioche(int debut, int fin){
        cartes = new LinkedList<Carte>();
        remplir(debut,fin);
    }
    public Carte retirer(){
        assert(!cartes.isEmpty());
        assert(!cartes.isEmpty());
        return cartes.remove();
    }
    private void remplir(int debut, int fin) {

        ArrayList<Carte> tab = new ArrayList<Carte>();
        for (int i = debut; i <= fin; i++) {
            tab.add(new Carte(i));
        }
        Collections.shuffle(tab);

        while (!tab.isEmpty()) {
            cartes.add(tab.remove(0));
        }
    }
}
