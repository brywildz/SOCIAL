package gui;

import config.GameConfiguration;
import engine.data.map.Block;
import engine.data.map.Map;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;
import engine.data.person.vitality.Mood;
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
    private Map map;
    private MobileInterface manager;
    private PaintStrategy paintStrategy =new PaintStrategy();

    public GameDisplay(Map map, MobileInterface manager) {
        this.map = map;
        this.manager = manager;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        try {
            paintStrategy.paint(map, g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //paintStrategy.paintBuilding((Graphics2D)g);
            paintStrategy.paintCity((Graphics2D)g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        paintStrategy.paintHour((Graphics2D)g);
        this.revalidate();
        this.repaint();
        HashMap<String, Person> individu = PersonRepository.getInstance().getIndividus();
        Iterator<Person> it = individu.values().iterator();
        while(it.hasNext()){
            Color c = Color.YELLOW;
            Person ind = it.next();
            Mood m = ind.getPersonState().getMood();
            if(ind.isSleeping()){
                paintStrategy.paint(ind, g, Color.GRAY);
            }
            else if(m.getNiveau()>6){
                paintStrategy.paint(ind, g, Color.GREEN);
            }
            else if(m.getNiveau()<4){
                paintStrategy.paint(ind, g, Color.RED);
            }
            else{
                paintStrategy.paint(ind, g, Color.YELLOW);
            }

        }

    }

    public Block getBlockPosition(int y, int x){
        int line= y/GameConfiguration.BLOCK_SIZE;
        int column = x/GameConfiguration.BLOCK_SIZE;
        return map.getBlock(line, column);
    }
}
