package gui;

import static config.GameConfiguration.*;

import engine.data.map.*;
import engine.data.person.Person;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

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
        g.drawImage(ImageIO.read(new File("src/images/map1.png")),0,0,map.getColumnCount() * blockSize, map.getLineCount() * blockSize, null);
        //g.fillRect(0, 0, map.getColumnCount() * blockSize, map.getLineCount() * blockSize);
        Block[][] blocks = map.getBlocks();
        g.setColor(Color.GRAY);
        for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
            for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
                Block block = blocks[lineIndex][columnIndex];
                //g.drawRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
            }
        }
    }

    public void paintHour(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(80 * BLOCK_SIZE, 0 * BLOCK_SIZE, 150, 50);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 25));
        graphics.setColor(Color.BLACK);
        Clock clock = Clock.getInstance();
        graphics.drawString(clock.showDate(), 80*BLOCK_SIZE, 2*BLOCK_SIZE);
        graphics.drawString(clock.showTime(), 80*BLOCK_SIZE, 4*BLOCK_SIZE);
    }

    public void paint(Person person, Graphics graphics, Color color) {
        int blockSize = BLOCK_SIZE;
        Block location = person.getLocation();
        int x = location.getColumn();
        int y = location.getLine();
        graphics.setColor(color);
        graphics.fillOval((x * blockSize), (y * blockSize), blockSize, blockSize);
        graphics.setColor(Color.BLACK); // Définir la couleur du contour en noir
        graphics.drawOval(x * blockSize, y * blockSize, blockSize, blockSize);
    }

    public void paintCity(Graphics2D g2) throws IOException {
        int blockSize = BLOCK_SIZE;
        g2.drawImage(ImageIO.read(new File("src/images/apartments1.png")), APARTMENT_X *BLOCK_SIZE, APARTMENT_Y *BLOCK_SIZE,
                APARTMENT_WIDTH *BLOCK_SIZE, APARTMENT_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/apartments1.png")), (APARTMENT_X-5) *BLOCK_SIZE, APARTMENT_Y *BLOCK_SIZE,
                APARTMENT_WIDTH *BLOCK_SIZE, APARTMENT_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/apartments1.png")), (APARTMENT_X-10) *BLOCK_SIZE, APARTMENT_Y *BLOCK_SIZE,
                APARTMENT_WIDTH *BLOCK_SIZE, APARTMENT_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/administrativeBuilding.png")), ADMIN_X *BLOCK_SIZE, ADMIN_Y *BLOCK_SIZE,
                ADMIN_WIDTH *BLOCK_SIZE, ADMIN_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/administrativeBuilding.png")), (ADMIN_X+4) *BLOCK_SIZE, (ADMIN_Y+3) *BLOCK_SIZE,
                ADMIN_WIDTH *BLOCK_SIZE, ADMIN_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/bank.png")), BANK_X *BLOCK_SIZE, BANK_Y *BLOCK_SIZE,
                BANK_WIDTH *BLOCK_SIZE, BANK_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/house1.png")), HOUSE1_X *BLOCK_SIZE, HOUSE1_Y *BLOCK_SIZE,
                HOUSE1_WIDTH *BLOCK_SIZE, HOUSE1_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/house2.png")), HOUSE2_X *BLOCK_SIZE, HOUSE2_Y *BLOCK_SIZE,
                HOUSE1_WIDTH *BLOCK_SIZE, HOUSE1_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/house3.png")), HOUSE3_X *BLOCK_SIZE, HOUSE3_Y *BLOCK_SIZE,
                HOUSE1_WIDTH *BLOCK_SIZE, HOUSE1_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/library.png")), LIBRARY_X *BLOCK_SIZE, LIBRARY_Y *BLOCK_SIZE,
                LIBRARY_WIDTH *BLOCK_SIZE, LIBRARY_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/school.png")), SCHOOL_X *BLOCK_SIZE, SCHOOL_Y *BLOCK_SIZE,
                SCHOOL_WIDTH *BLOCK_SIZE, SCHOOL_HEIGHT *BLOCK_SIZE, null);

        /*g2.drawImage(ImageIO.read(new File("src/images/house1.png")), MUSEUM_X *BLOCK_SIZE, MUSEUM_Y *BLOCK_SIZE,
                MUSEUM_WIDTH *BLOCK_SIZE, MUSEUM_HEIGHT *BLOCK_SIZE, null);

        g2.drawImage(ImageIO.read(new File("src/images/house1.png")), CINEMA_X *BLOCK_SIZE, CINEMA_Y *BLOCK_SIZE,
                CINEMA_WIDTH *BLOCK_SIZE, CINEMA_HEIGHT *BLOCK_SIZE, null);*/

    }
}
