package test;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.individu.Individu;
import engine.process.IndividuRepository;
import gui.MainGUI;

public class TestGame {
    public static void main(String[] args) {
        Individu individu = new Individu("Dylan,", 20, "clochard", null, null, null, new Block(20,20));
        IndividuRepository.getInstance().addIndividu(individu);
        MainGUI gameMainGUI = new MainGUI("Social");

        Thread gameThread = new Thread(gameMainGUI);
        gameThread.start();
    }
}
