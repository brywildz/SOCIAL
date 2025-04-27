package gui;

import engine.data.event.Event;
import engine.data.map.*;
import engine.data.person.Person;
import engine.data.person.vitality.Mood;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static config.GameConfiguration.*;

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

    public void paintDate(Graphics graphics){
        int positionX = 135;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(positionX * BLOCK_SIZE, 0 * BLOCK_SIZE, 150, 65);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 25));
        graphics.setColor(Color.BLACK);
        Clock clock = Clock.getInstance();
        graphics.drawString(clock.showDayName(), positionX*BLOCK_SIZE, 2*BLOCK_SIZE);
        graphics.drawString(clock.showDate(), positionX*BLOCK_SIZE, 4*BLOCK_SIZE);
        graphics.drawString(clock.showTime(), positionX*BLOCK_SIZE, 6*BLOCK_SIZE);
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
        g2.drawImage(ImageIO.read(new File("src/images/apartments1.png")), APARTMENT1_X * blockSize, APARTMENT1_Y * blockSize,
                APARTMENT_WIDTH * blockSize, APARTMENT_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/apartments4.png")), APARTMENT2_X * blockSize, APARTMENT2_Y * blockSize,
                APARTMENT_WIDTH * blockSize, APARTMENT_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/apartments6.png")), APARTMENT3_X * blockSize, APARTMENT3_Y * blockSize,
                APARTMENT_WIDTH * blockSize, APARTMENT_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/administrativeBuilding.png")), ADMIN1_X * blockSize, ADMIN1_Y * blockSize,
                ADMIN_WIDTH * blockSize, ADMIN_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/administrativeBuilding.png")), ADMIN2_X * blockSize, ADMIN2_Y * blockSize,
                ADMIN_WIDTH * blockSize, ADMIN_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/bank.png")), BANK_X * blockSize, BANK_Y * blockSize,
                BANK_WIDTH * blockSize, BANK_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/house1.png")), HOUSE1_X * blockSize, HOUSE1_Y * blockSize,
                HOUSE1_WIDTH * blockSize, HOUSE1_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/house2.png")), HOUSE2_X * blockSize, HOUSE2_Y * blockSize,
                HOUSE1_WIDTH * blockSize, HOUSE1_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/house3.png")), HOUSE3_X * blockSize, HOUSE3_Y * blockSize,
                HOUSE1_WIDTH * blockSize, HOUSE1_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/library.png")), LIBRARY_X * blockSize, LIBRARY_Y * blockSize,
                LIBRARY_WIDTH * blockSize, LIBRARY_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/school.png")), SCHOOL1_X * blockSize, SCHOOL1_Y * blockSize,
                SCHOOL_WIDTH * blockSize, SCHOOL_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/school1.png")), SCHOOL2_X * blockSize, SCHOOL2_Y * blockSize,
                SCHOOL_WIDTH * blockSize, SCHOOL_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/nightClub.png")), NIGHTCLUB_X * blockSize, NIGHTCLUB_Y * blockSize,
                NIGHTCLUB_WIDTH * blockSize, NIGHTCLUB_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/hospital.png")), HOSPITAL_X * blockSize, HOSPITAL_Y * blockSize,
                HOSPITAL_WIDTH * blockSize, HOSPITAL_HEIGHT * blockSize, null);

        /*g2.drawImage(ImageIO.read(new File("src/images/policeStation.png")), POLICESTATION_X * blockSize, POLICESTATION_Y * blockSize,
                POLICESTATION_WIDTH * blockSize, POLICESTATION_HEIGHT * blockSize, null);*/

        g2.drawImage(ImageIO.read(new File("src/images/restaurant.png")), RESTAURANT_X * blockSize, RESTAURANT_Y * blockSize,
                RESTAURANT_WIDTH * blockSize, RESTAURANT_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/salle.png")), GYM_X * blockSize, GYM_Y * blockSize,
                GYM_WIDTH * blockSize, GYM_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/museum.png")), MUSEE_X * blockSize, MUSEE_Y * blockSize,
                MUSEE_WIDTH * blockSize, MUSEE_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/cinema.png")), CINEMA_X * blockSize, CINEMA_Y * blockSize,
                CINEMA_WIDTH * blockSize, CINEMA_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/association.png")), ASSOCIATION_X * blockSize, ASSOCIATION_Y * blockSize,
                ASSOCIATION_WIDTH * blockSize, ASSOCIATION_HEIGHT * blockSize, null);

        /*g2.drawImage(ImageIO.read(new File("src/images/clothing.png")), CLOTHING_X * blockSize, CLOTHING_Y * blockSize,
                CLOTHING_WIDTH * blockSize, CLOTHING_HEIGHT * blockSize, null);*/

        /*g2.drawImage(ImageIO.read(new File("src/images/furniture.png")), FURNITURE_X * blockSize, FURNITURE_Y * blockSize,
                FURNITURE_WIDTH * blockSize, FURNITURE_HEIGHT * blockSize, null);*/

        /*g2.drawImage(ImageIO.read(new File("src/images/tower.png")), TOWER_X * blockSize, TOWER_Y * blockSize,
                TOWER_WIDTH * blockSize, TOWER_HEIGHT * blockSize, null);*/

        /*g2.drawImage(ImageIO.read(new File("src/images/airport.png")), AIRPORT_X * blockSize, AIRPORT_Y * blockSize,
                AIRPORT_WIDTH * blockSize, AIRPORT_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/fireStation.png")), FIRESTATION_X * blockSize, FIRESTATION_Y * blockSize,
                FIRESTATION_WIDTH * blockSize, FIRESTATION_HEIGHT * blockSize, null);

        g2.drawImage(ImageIO.read(new File("src/images/government.png")), GOVERNMENT_X * blockSize, GOVERNMENT_Y * blockSize,
                GOVERNMENT_WIDTH * blockSize, GOVERNMENT_HEIGHT * blockSize, null);*/
    }

    public Color getColorFor(Person person){
        Mood m = person.getPersonState().getMood();
        Event event = person.getEvent();
        if(person.isSleeping()){
            return Color.GRAY;
        }
        else if(person.isSick()){
            return new Color(120, 180, 120);
        }
        else if(person.getEvent()!=null){
            switch (event.getId()) {
                case "meet":
                    return Color.PINK;
                case "success":
                    return Color.BLUE;
                case "walk":
                    return Color.cyan;
                case "family_dinner":
                    return Color.LIGHT_GRAY;
                case "party":
                    return new Color(128, 0, 120);
                case "work_dinner": //work_dinner
                    return Color.DARK_GRAY;
            }
        }
        else if(m.getNiveau()>6){
            return new Color(0, 200, 0);
        }
        else if(m.getNiveau()<4){
            return Color.RED;
        }
        else{
            return Color.YELLOW;
        }
        return Color.YELLOW;
    }
}
