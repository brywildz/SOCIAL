package gui;

import engine.data.carte.Carte;
import engine.data.individu.Individu;
import engine.process.MouvementIndividu;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JPanel {
    private Carte carte;
    private MouvementIndividu manager;
    private PaintStrategy paintStrategy =new PaintStrategy();

    public GameDisplay(Carte carte, MouvementIndividu manager) {
        this.carte = carte;
        this.manager = manager;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintStrategy.paint(carte, g);
        Individu individu = manager.getIndividu();
    }
}
