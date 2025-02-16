package gui;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.individu.Individu;
import engine.process.MobileInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Classe d'affichage gérant le dessin des different composant selon la classe paintStrategy
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class GameDisplay extends JPanel {
    private Carte carte;
    private MobileInterface manager;
    private PaintStrategy paintStrategy =new PaintStrategy();

    public GameDisplay(Carte carte, MobileInterface manager) {
        this.carte = carte;
        this.manager = manager;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintStrategy.paint(carte, g);
        HashMap<Block, Individu> individu = manager.getIndividus();
        Iterator<Individu> it = individu.values().iterator();
        while(it.hasNext()){
            Individu ind = it.next();
            paintStrategy.paint(ind, g);
            try {
                paintStrategy.paintHouse((Graphics2D)g);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Block getBlockPosition(int x, int y){
        int line= x/GameConfiguration.BLOCK_SIZE;
        int column = y/GameConfiguration.BLOCK_SIZE;
        return carte.getBlock(line, column);
    }
}
