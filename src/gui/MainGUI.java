package gui;

import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.process.MouvementIndividu;
import engine.process.GameBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainGUI extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    private Carte carte;

    private final static Dimension preferredSize = new Dimension(800, 800);
    private MouvementIndividu manager;
    private GameDisplay dashboard;

    public MainGUI(String title) throws HeadlessException {
        super(title);
        init();
    }

    private void init() {

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

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

    }

    private class MouseControls implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            Block bombPosition = dashboard.getBombPosition(x, y);
            manager.putBomb(bombPosition);
            System.out.println(x + " " + y);
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
