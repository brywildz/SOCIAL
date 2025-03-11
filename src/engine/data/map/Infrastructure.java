package engine.data.map;

/**
 * Classe de donnée stockant les informations liée à une infrastructure
 */

public class Infrastructure {
    private String nom;  // Ajout du nom du lieu
    private Block base;
    private Block[][] zone;

    public Infrastructure(String nom, int x, int y, int width, int height) {
        this.nom = nom;
        base = new Block(x, y);
        zone = new Block[height][width];
        for(int i=0; i < height; i++){
            for(int j = 0; j < width; j++){
                zone[i][j] = new Block(x+i, y+j);
                int e=x+i; int s=y+j;
                System.out.println(e+" / "+s);
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public Block getBase() {
        return base;
    }

    public Block[][] getZone() {
        return zone;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setBase(Block base) {
        this.base = base;
    }

    public void setZone(Block[][] zone) {
        this.zone = zone;
    }
}
