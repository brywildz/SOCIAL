package gui;

import engine.data.person.Person;
import engine.process.builder.EventBuilder;
import engine.process.builder.PersonBuilder;
import engine.process.repository.NameRepository;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;

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
    private static Person personClicked;

    public ControlPanel(Container base) {
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.base=base;
        this.setPreferredSize(new Dimension(300, 100));
        this.setLayout(new BorderLayout());

        westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(2,2));
        JButton personButton = new JButton("Ajouter un individu");
        personButton.setPreferredSize(new Dimension(200, 50));
        westPanel.add(personButton);
        westPanel.setPreferredSize(new Dimension(570, 600));
        this.add(westPanel, BorderLayout.WEST);

        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setPreferredSize(new Dimension(300, 600));
        eastPanel = new JPanel();
        this.add(eastPanel, BorderLayout.EAST);

        personButton.addActionListener((event)-> {
            try {
                createIndividu();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        JButton showCityStatsButton = new JButton("Statistiques de la ville");
        JButton showSocialButton = new JButton("Réseau social");
        westPanel.add(showCityStatsButton);
        westPanel.add(showSocialButton);
        showPersonStatsButton = new JButton("En savoir plus");
        showPersonStatsButton.setPreferredSize(new Dimension(110, 30));
        showPersonStatsButton.addActionListener(e -> new StatsDisplay(personClicked));

        showSocialButton.addActionListener(e -> showSocial());
        showCityStatsButton.addActionListener(e -> StatsDisplay.showGlobalStats());

        JButton speedUpButton = new JButton("⏩");
        JButton speedNormalButton = new JButton("↺");
        JButton speedDownButton = new JButton("⏪");
        JButton pauseButton = new JButton("▶|❚❚");
        speedNormalButton.addActionListener(e -> speedNormalGame());
        speedUpButton.addActionListener(e -> speedUpGame());
        speedDownButton.addActionListener(e -> speedDownGame());
        pauseButton.addActionListener(e -> GAME=!GAME);

        eastPanel.setLayout(new GridLayout(2,2));
        eastPanel.add(speedDownButton);
        eastPanel.add(speedUpButton);
        eastPanel.add(pauseButton);
        eastPanel.add(speedNormalButton);
    }

    private void showSocial() {
        GAME=false;
        centerPanel.removeAll();
        if(infoIndividu == null){
            infoIndividu = new JLabel();
            centerPanel.add(this.infoIndividu, BorderLayout.CENTER);
        }
        infoIndividu.setText("<html><font color='blue' size=6><b>Merci de choisir une personne pour voir son réseau.<b></font></html>");
        JButton validateButton = new JButton("OK");
        validateButton.addActionListener(e -> {
            callWeb();});
        westPanel.add(validateButton);
        this.revalidate();
        this.repaint();
    }

    private void callWeb() {
        if(personClicked != null && !GAME){
            WEB = true;
            base.revalidate();
            base.repaint();
        }
    }

    public static void showWeb(Graphics g) {
        PaintStrategy paintStrategy = new PaintStrategy();
        System.out.println(personClicked.getName());
        paintStrategy.paintWeb(personClicked, g);
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

    private void showErrorEvent() {
        centerPanel.removeAll();
        if(infoIndividu == null){
            infoIndividu = new JLabel();
            centerPanel.add(this.infoIndividu, BorderLayout.CENTER);
        }
        infoIndividu.setText("<html><font color='red' size=6><b>Merci de choisir une personne puis un événement.<b></font></html>");
    }

    private void newEvent(Object selectedItem) throws InterruptedException {
        if(personClicked == null){
            showErrorEvent();
            return;
        }
        GAME=false;
        EventBuilder eb = new EventBuilder(personClicked, (String) selectedItem);
        String s = eb.buildByGui();
        showRespond(s);
        GAME=true;
    }

    private void showRespond(String s) {
        centerPanel.removeAll();
        if(infoIndividu == null){
            infoIndividu = new JLabel();
            centerPanel.add(this.infoIndividu, BorderLayout.CENTER);
        }
        infoIndividu.setText("<html><font color='green' size=6><b>"+s+"<b></font></html>");
        centerPanel.add(this.infoIndividu, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void createIndividu() throws FileNotFoundException {
        if(NameRepository.getInstance().getNameIndex() > 100){
            showErrorCreation();
            return;
        }
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder.buildPerson();

    }

    private void showErrorCreation() {
        centerPanel.removeAll();
        if(infoIndividu == null){
            infoIndividu = new JLabel();
            centerPanel.add(this.infoIndividu, BorderLayout.CENTER);
        }
        infoIndividu.setText("<html><font color='red' size=6><b>Taille limite atteinte (103)<b></font></html>");
        centerPanel.add(this.infoIndividu, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
