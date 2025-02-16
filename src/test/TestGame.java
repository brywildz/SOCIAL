package test;

import gui.MainGUI;

import javax.swing.*;

/**
 * Classe principale instanciant les classes néccessaires au lancement du jeu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class TestGame {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        MainGUI gameMainGUI = new MainGUI("Social");

        Thread gameThread = new Thread(gameMainGUI);
        gameThread.start();
    }

}
