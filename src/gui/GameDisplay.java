package gui;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.individu.Individu;
import engine.process.MobileInterface;

import javax.swing.*;
import java.awt.*;
import java.lang.module.Configuration;
import java.util.HashMap;
import java.util.Iterator;

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
        }
    }

    public Block getIndividuPosition(int x, int y){
        int line= y/GameConfiguration.BLOCK_SIZE;
        int column = x/GameConfiguration.BLOCK_SIZE;
        return carte.getBlock(line, column);
    }
}
