package gui;

import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.individu.Individu;

import java.awt.*;

public class PaintStrategy {

    public void paint(Carte carte, Graphics graphics) {
        int blockSize = 2;
        Block[][] blocks = carte.getBlocks();
        for(int i=0; i<carte.getLargeur(); i++){
            for(int j=0; j<carte.getHauteur(); j++){
                Block block = blocks[i][j];

                if((i+j)%2==0){
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(block.getColumn() * blockSize , block.getLine() *blockSize, blockSize, blockSize);
                }
            }
        }
    }

    public void paint(Individu individu, Graphics graphics){
        int blockSize = 2;
        Block location = individu.getLocation();
        int x = location.getLine();
        int y = location.getColumn();
        graphics.setColor(Color.GREEN);
        graphics.fillOval((x * blockSize)/4, (y * blockSize)/4, blockSize/2, blockSize/2);
    }
}
