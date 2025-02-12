package gui;

import engine.data.carte.Carte;
import engine.process.MouvementIndividu;

import javax.swing.*;
import java.awt.*;

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

        carte = GameBuilder.buildMap();
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

    }
}
