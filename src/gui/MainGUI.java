package gui;

import config.GameConfiguration;
import engine.data.map.Block;
import engine.data.map.Map;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;
import engine.process.builder.GameBuilder;
import engine.process.MobileInterface;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

/**
 * Classe d'affichage de la fenêtre principale, celle-ci contient la carte, les individus, les lieux
 * et le panneau d'interaction avec l'utilisateur
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class MainGUI extends JFrame implements Runnable {
    private Map map = Map.getInstance();
    private ControlPanel controlPanel;
    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
    private MobileInterface manager;
    private GameDisplay dashboard;

    public MainGUI(String title) throws HeadlessException, UnsupportedLookAndFeelException, FileNotFoundException {
        super(title);
        init();
    }

    private void init() throws UnsupportedLookAndFeelException, FileNotFoundException {

        Container contentPane = getContentPane();
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        contentPane.setLayout(new BorderLayout());
        controlPanel = new ControlPanel(contentPane);
        controlPanel.setPreferredSize(new Dimension(0,70));
        add(controlPanel, BorderLayout.NORTH);

        GameBuilder.buildCarte();
        manager = GameBuilder.buildInitMobile(map);
        dashboard = new GameDisplay(manager);

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
            if(GameConfiguration.GAME){
                try {
                    Thread.sleep(GameConfiguration.GAME_SPEED);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    manager.nextSecond();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //controlPanel.showDate();
                dashboard.repaint();
            }
            else{
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private class MouseControls implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            Block clicPosition = dashboard.getBlockPosition(y, x);
            PersonRepository individus = PersonRepository.getInstance();
            System.out.println(clicPosition);
            Person p = individus.isHere(clicPosition);
            if(!(p==null)) {
                controlPanel.showInfoIndividu(p);
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
