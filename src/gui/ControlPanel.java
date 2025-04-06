package gui;

import engine.data.person.Person;
import engine.process.PersonBuilder;
import engine.process.repository.PersonRepository;
import config.GameConfiguration.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static config.GameConfiguration.*;

/**
 * Classe d'affichage gérant le panneau gauche des interactions avec l'utilisateur
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class ControlPanel extends JPanel {
    private Container base;
    private JLabel infoIndividu;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel centerPanel;
    private JButton showPersonStatsButton;
    private Person personClicked;

    public ControlPanel(Container base) {
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.base=base;
        this.setPreferredSize(new Dimension(300, 100));
        this.setLayout(new BorderLayout());

        westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.X_AXIS));
        JButton personButton = new JButton("Ajouter un individu");
        personButton.setPreferredSize(new Dimension(200, 50));
        JButton eventButton = new JButton("Ajouter un événement");
        eventButton.setPreferredSize(new Dimension(200, 50));
        westPanel.add(personButton);
        westPanel.add(eventButton);
        westPanel.setPreferredSize(new Dimension(570, 600));
        this.add(westPanel, BorderLayout.WEST);

        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setPreferredSize(new Dimension(450, 600));
        eastPanel = new JPanel();
        this.add(eastPanel, BorderLayout.EAST);


        eventButton.addActionListener((event)-> chooseEvent(westPanel));
        personButton.addActionListener((event)-> createIndividu());

        JButton showCityStatsButton = new JButton("Statistiques de la ville");
        westPanel.add(showCityStatsButton);
        showPersonStatsButton = new JButton("En savoir plus");
        showPersonStatsButton.setPreferredSize(new Dimension(110, 30));
        showPersonStatsButton.addActionListener(e -> new StatsDisplay(personClicked));

        showCityStatsButton.addActionListener(e -> StatsDisplay.showGlobalStats());

        JButton speedUpButton = new JButton("Accélérer");
        JButton speedNormalButton = new JButton("Rétablir");
        JButton speedDownButton = new JButton("Ralentir");
        speedUpButton.addActionListener(e -> speedUpGame());
        speedNormalButton.addActionListener(e -> speedNormalGame());
        speedDownButton.addActionListener(e -> speedDownGame());

        eastPanel.add(speedDownButton);
        eastPanel.add(speedNormalButton);
        eastPanel.add(speedUpButton);
    }

    public void showInfoIndividu(Person p){
        centerPanel.removeAll();
        if(infoIndividu == null){
            infoIndividu = new JLabel();
            centerPanel.add(this.infoIndividu, BorderLayout.CENTER);
        }
        infoIndividu.setText(p.toStringForPane());
        infoIndividu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        infoIndividu.setBorder(new EmptyBorder(5,0,0,0));
        personClicked = p;
        centerPanel.add(this.infoIndividu);
        centerPanel.add(showPersonStatsButton);

        centerPanel.revalidate();
        centerPanel.repaint();
        
    }

    public void chooseEvent(JPanel box) {
        if(box.getComponent(2) instanceof JComboBox){
            return;
        }
        String[] listEvent = {"Pluie", "Non définit", "Non définit", "Non définit", "Non définit", "Non définit", "Non définit", "Non définit", "Non définit"};
        JComboBox<String> choiceBox = new JComboBox<>(listEvent);
        box.add(choiceBox, 2);
        JButton okButton = new JButton("OK");
        box.add(okButton, 3);

        westPanel.revalidate();
        westPanel.repaint();
    }

    public void createIndividu(){
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder.buildPerson();

    }
}
