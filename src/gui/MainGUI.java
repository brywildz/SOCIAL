package gui;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.carte.Map;
import engine.data.individu.Individu;
import engine.process.MobileInterface;
import engine.process.GameBuilder;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * Classe d'affichage de la fenêtre principale, celle-ci contient la carte, les individus, les lieux
 * et le panneau d'interaction avec l'utilisateur
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class MainGUI extends JFrame implements Runnable {
    private Map map;
    private ControlPanel controlPanel;
    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
    private MobileInterface manager;
    private GameDisplay dashboard;

    public MainGUI(String title) throws HeadlessException, UnsupportedLookAndFeelException {
        super(title);
        init();
    }

    private void init() throws UnsupportedLookAndFeelException {

        Container contentPane = getContentPane();
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        contentPane.setLayout(new BorderLayout());
        controlPanel = new ControlPanel(contentPane);
        add(controlPanel, BorderLayout.EAST);

        map = GameBuilder.buildCarte();
        manager = GameBuilder.buildInitMobile(map);
        dashboard = new GameDisplay(map, manager);

        MouseControls mouseControls = new MouseControls();
        dashboard.addMouseListener(mouseControls);

        dashboard.setPreferredSize(preferredSize);
        contentPane.add(dashboard, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setPreferredSize(preferredSize);
        setResizable(false);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(GameConfiguration.GAME_SPEED);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            manager.nextSecond();
            dashboard.repaint();
        }
    }

    private class MouseControls implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            Block clicPosition = dashboard.getBlockPosition(y, x);
            HashMap<Block, Individu> individus = map.getIndividus();
            System.out.println(clicPosition);
            System.out.println(x+" "+y);
            if(individus.containsKey(clicPosition)) {
                controlPanel.showInfoIndividu(individus.get(clicPosition).toString());
            }
            else{
                System.out.println("personne");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
