package engine.data.map;

/**
 * Classe de donnée stockant les informations liée à une infrastructure
 */

public class Infrastructure {
    private String nom;  // Ajout du nom du lieu
    private int x;
    private int y;

    public Infrastructure(String nom, int x, int y) {
        this.nom = nom;
        this.x = x;
        this.y = y;
    }

    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
