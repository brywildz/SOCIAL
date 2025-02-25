package gui;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.individu.Individu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe d'affichage gérant la strategie d'affichage (comment les composants  vont être dessiné sur l'écran)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

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

    public void paintHouse(Graphics2D g2) throws IOException {
        int blockSize = GameConfiguration.BLOCK_SIZE;
        g2.drawImage(ImageIO.read(new File("src/images/house.png")),GameConfiguration.HOUSE_X/GameConfiguration.BLOCK_SIZE,GameConfiguration.HOUSE_Y/GameConfiguration.BLOCK_SIZE,GameConfiguration.HOUSE_WIDTH,GameConfiguration.HOUSE_HEIGHT,null);
    }
}
