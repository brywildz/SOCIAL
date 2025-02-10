package carte;

import java.util.HashMap;

public class Carte {
    private int largeur;
    private int hauteur;
    private double echelle;
    private HashMap<Integer, String> espaces;

    public Carte(int largeur, int hauteur, double echelle, HashMap<Integer, String> espaces) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.echelle = echelle;
        this.espaces = espaces;
    }
}
