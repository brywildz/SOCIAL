package gui;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.individu.Individu;
import engine.process.MobileInterface;
import engine.process.GameBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class MainGUI extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    private Carte carte;

    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
    private MobileInterface manager;
    private GameDisplay dashboard;

    public MainGUI(String title) throws HeadlessException {
        super(title);
        init();
    }

    private void init() {

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        ControlPanel cp = new ControlPanel(contentPane);
        add(cp, BorderLayout.EAST);
        //KeyControls keyControls = new KeyControls();
        JTextField textField = new JTextField();
        //textField.addKeyListener(keyControls);
        contentPane.add(textField, BorderLayout.SOUTH);

        carte = GameBuilder.buildCarte();
        manager = GameBuilder.buildInitMobile(carte);
        dashboard = new GameDisplay(carte, manager);

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

            Block individuPosition = dashboard.getIndividuPosition(x, y);
            HashMap<Block, Individu> individus = carte.getIndividus();
            if(individus.containsKey(individuPosition)) {
                System.out.println(individus.get(individuPosition).toString());
            }
            else{
                System.out.println("rien");
            }
            //manager.putBomb(bombPosition);
            System.out.println(x/GameConfiguration.BLOCK_SIZE + " " + y/GameConfiguration.BLOCK_SIZE);
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
