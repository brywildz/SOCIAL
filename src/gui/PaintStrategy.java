package gui;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.individu.Individu;

import java.awt.*;

public class PaintStrategy {

    public void paint(Carte carte, Graphics graphics) {
        int blockSize = GameConfiguration.BLOCK_SIZE;
        Block[][] blocks = carte.getBlocks();
        for (int lineIndex = 0; lineIndex < carte.getLineCount(); lineIndex++) {
            for (int columnIndex = 0; columnIndex < carte.getColumnCount(); columnIndex++) {
                Block block = blocks[lineIndex][columnIndex];

                if ((lineIndex + columnIndex) % 2 == 0){
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(block.getColumn() * blockSize , block.getLine() *blockSize, blockSize, blockSize);
                }
                // Ajouter un contour noir
                graphics.setColor(Color.GRAY); // Couleur du contour
                graphics.drawRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
            }
        }
    }

    public void paint(Individu individu, Graphics graphics){
        int blockSize = GameConfiguration.BLOCK_SIZE;
        Block location = individu.getLocation();
        int x = location.getLine();
        int y = location.getColumn();
        graphics.setColor(Color.GREEN);
        graphics.fillOval((x * blockSize), (y * blockSize), blockSize, blockSize);
    }
}
