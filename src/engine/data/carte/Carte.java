package engine.data.carte;

import engine.data.individu.Individu;

import java.util.HashMap;

public class Carte {
    private int largeur;
    private int hauteur;
    private double echelle;
    private HashMap<Integer, String> espaces;
    private Block[][] blocks;
    private HashMap<Block, Individu> individus;

    public Carte(int largeur, int hauteur){
        this.hauteur = hauteur;
        this.largeur = largeur;
        blocks = new Block[largeur][hauteur];

        for(int i=0; i<largeur; i++){
            for(int j=0; j<hauteur; j++){
                blocks[largeur][hauteur] = new Block(largeur, hauteur);
            }
        }
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public double getEchelle() {
        return echelle;
    }

    public void setEchelle(double echelle) {
        this.echelle = echelle;
    }

    public HashMap<Integer, String> getEspaces() {
        return espaces;
    }

    public void setEspaces(HashMap<Integer, String> espaces) {
        this.espaces = espaces;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public HashMap<Block, Individu> getIndividus() {
        return individus;
    }

    public void setIndividus(HashMap<Block, Individu> individus) {
        this.individus = individus;
    }

    public Block getBlock(int x, int y){
        return blocks[x][y];
    }
}
