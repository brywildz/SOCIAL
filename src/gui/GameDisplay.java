package gui;

import config.GameConfiguration;
import engine.data.map.Block;
import engine.data.map.Map;
import engine.data.person.Person;
import engine.process.MobileInterface;
import engine.process.repository.PersonRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private Map map = Map.getInstance();
    private MobileInterface manager;
    private PaintStrategy paintStrategy = new PaintStrategy();
    private BufferedImage cityImage;

    public GameDisplay(MobileInterface manager) {
        this.manager = manager;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(cityImage, 0, 0, null);
        paintStrategy.paintDate((Graphics2D)g);
        HashMap<String, Person> individu = PersonRepository.getInstance().getPersons();
        Iterator<Person> it = individu.values().iterator();
        while(it.hasNext()){
            Person person = it.next();
            paintStrategy.paint(person, g, paintStrategy.getColorFor(person));

        }
        if(GameConfiguration.WEB){
            ControlPanel.showWeb(g);
            GameConfiguration.WEB = false;
        }

    }

    public void paintComponent(Graphics g, Person person) {

    }

    public Block getBlockPosition(int y, int x){
        int line= y/GameConfiguration.BLOCK_SIZE;
        int column = x/GameConfiguration.BLOCK_SIZE;
        return map.getBlock(line, column);
    }

    public void generateCityImage(){
        int width = 1500;
        int height = 750;
        cityImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = cityImage.createGraphics();

        try{
            paintStrategy.paint(map,g2);
            paintStrategy.paintCity((Graphics2D)g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.dispose();
    }

    public void addNotify(){
        super.addNotify();
        generateCityImage();
        repaint();
    }
}
