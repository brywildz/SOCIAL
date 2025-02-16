package engine.data.carte;

import engine.data.individu.Individu;
import engine.data.individu.IndividuRepository;

import java.util.HashMap;

/**
 * Classe de donnée stockant les information liée à la carte du jeu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Carte {
    private int lineCount;
    private int columnCount;
    private double echelle;
    private HashMap<Integer, String> espaces;
    private Block[][] blocks;
    private HashMap<Block, Individu> individus;

    public Carte(int largeur, int hauteur){
        this.columnCount = hauteur;
        this.lineCount = largeur;
        this.individus = IndividuRepository.getInstance().getIndividus();
        blocks = new Block[largeur][hauteur];

        for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                blocks[lineIndex][columnIndex] = new Block(lineIndex, columnIndex);
            }
        }
    }

    private void init(int lineCount, int columnCount) {
        this.lineCount = lineCount;
        this.columnCount = columnCount;

        blocks = new Block[lineCount][columnCount];


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

    public int getLineCount() {
        return lineCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
