package gui;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.carte.Map;
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

    public void paint(Map map, Graphics g) throws IOException {
        int blockSize = GameConfiguration.BLOCK_SIZE;
        //g.setColor(new Color(34, 139, 34));
        g.drawImage(ImageIO.read(new File("src/images/map.png")),0,0,map.getColumnCount() * blockSize, map.getLineCount() * blockSize, null);
        //g.fillRect(0, 0, map.getColumnCount() * blockSize, map.getLineCount() * blockSize);
        Block[][] blocks = map.getBlocks();
        g.setColor(Color.GRAY);
        for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
            for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
                Block block = blocks[lineIndex][columnIndex];
                g.drawRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
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
        //g2.drawImage(ImageIO.read(new File("src/images/house.png")),GameConfiguration.HOUSE_X/GameConfiguration.BLOCK_SIZE,GameConfiguration.HOUSE_Y/GameConfiguration.BLOCK_SIZE,GameConfiguration.HOUSE_WIDTH,GameConfiguration.HOUSE_HEIGHT,null);
    }
}
