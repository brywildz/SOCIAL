package gui;

import static config.GameConfiguration.*;
import engine.data.map.Block;
import engine.data.map.Map;
import engine.data.person.Person;

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
        int blockSize = BLOCK_SIZE;
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

    public void paint(Person person, Graphics graphics){
        int blockSize = BLOCK_SIZE;
        Block location = person.getLocation();
        int x = location.getLine();
        int y = location.getColumn();
        graphics.setColor(Color.GREEN);
        graphics.fillOval((x * blockSize), (y * blockSize), blockSize, blockSize);
    }

    public void paintHouse(Graphics2D g2) throws IOException {
        int blockSize = BLOCK_SIZE;
        //g2.drawImage(ImageIO.read(new File("src/images/house.png")),HOUSE_X/BLOCK_SIZE,HOUSE_Y/BLOCK_SIZE,HOUSE_WIDTH,HOUSE_HEIGHT,null);
    }

    public void paintBuilding(Graphics2D g2) throws IOException {
        int blockSize = BLOCK_SIZE;
        int buildingHeight = BUILDING_HEIGHT*blockSize;
        int buildingWidth = BUILDING_WIDTH*blockSize;
        int buildingX = BUILDING_X*blockSize;
        int buildingY = BUILDING_Y*blockSize;
        g2.drawImage(ImageIO.read(new File("src/images/building.png")), buildingX,buildingY,buildingWidth,buildingHeight,null);
        g2.drawImage(ImageIO.read(new File("src/images/building.png")), (BUILDING_X+5)*BLOCK_SIZE,buildingY,buildingWidth,buildingHeight,null);
        g2.drawImage(ImageIO.read(new File("src/images/apartment.png")), APARTMENT_X *BLOCK_SIZE, APARTMENT_Y *BLOCK_SIZE,
                APARTMENT_WIDTH *BLOCK_SIZE, APARTMENT_HEIGHT *BLOCK_SIZE, null);
        g2.drawImage(ImageIO.read(new File("src/images/apartment.png")), (APARTMENT_X +1) *BLOCK_SIZE, (APARTMENT_Y) *BLOCK_SIZE,
                APARTMENT_WIDTH *BLOCK_SIZE, APARTMENT_HEIGHT *BLOCK_SIZE, null);
        g2.drawImage(ImageIO.read(new File("src/images/apartment.png")), (APARTMENT_X) *BLOCK_SIZE, (APARTMENT_Y+4) *BLOCK_SIZE,
                APARTMENT_WIDTH *BLOCK_SIZE, APARTMENT_HEIGHT *BLOCK_SIZE, null);
        g2.drawImage(ImageIO.read(new File("src/images/apartment.png")), (APARTMENT_X+5) *BLOCK_SIZE, (APARTMENT_Y+4) *BLOCK_SIZE,
                APARTMENT_WIDTH *BLOCK_SIZE, APARTMENT_HEIGHT *BLOCK_SIZE, null);
    }
}
