package gui;

import engine.data.individu.Individu;
import engine.process.EventManager;
import engine.data.individu.IndividuRepository;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * Classe d'affichage gérant le panneaux gauche des interaction avec l'utilisateur
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class ControlPanel extends JPanel {
    Container base;
    JButton Indbutton = new JButton("Ajouter un individu");
    JButton Eventbutton = new JButton ("Ajouter un événnement");
    JTextPane infoIndividu;

    public ControlPanel(Container base) {
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.base=base;
        this.setPreferredSize(new Dimension(300, 0));
        this.setLayout(new BorderLayout());
        JPanel box = new JPanel();
        box.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        Indbutton.setPreferredSize(new Dimension(200, 50));
        Eventbutton.setPreferredSize(new Dimension(200, 50));
        box.add(Indbutton);
        box.add(Eventbutton);
        box.setPreferredSize(new Dimension(600, 600));
        this.add(box, BorderLayout.CENTER);
        Eventbutton.addActionListener((event)-> chooseEvent(box));
    }

    public void showInfoIndividu(String info){
        if(infoIndividu == null){
            infoIndividu = new JTextPane();
            infoIndividu.setEditable(false);
            this.add(this.infoIndividu, BorderLayout.SOUTH);
            Border roundedBorder = BorderFactory.createLineBorder(Color.GRAY, 1, true);
            Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
            infoIndividu.setBorder(BorderFactory.createCompoundBorder(roundedBorder, padding));
        }
        infoIndividu.setText(info);
        this.revalidate();
        this.repaint();
        
    }

    public void chooseEvent(JPanel box) {
        String[] listEvent = {"Pluie", "Non définit", "Non définit", "Non définit", "Non définit", "Non définit", "Non définit", "Non définit", "Non définit"};
        JComboBox<String> choiceBox = new JComboBox<>(listEvent);
        box.add(choiceBox);
        JButton okButton = new JButton("OK");
        box.add(okButton);
        okButton.addActionListener( e-> {
            try{
                String critereActuel = (String) choiceBox.getSelectedItem();
                if(critereActuel.equals("Pluie")){
                    EventManager em = new EventManager("pluie");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        this.revalidate();
        this.repaint();
    }

    public void createIndividu(){
            String name = JOptionPane.showInputDialog(this, "Entrez le nom de l'individu :");
            if (name != null && !name.isEmpty()) {
                String ageStr = JOptionPane.showInputDialog(this, "Entrez l'âge de l'individu :");
                try {
                    int age = Integer.parseInt(ageStr);
                    Individu individu = new Individu(name, age, "Statut par défaut", null, null, null, 0, 0,0 ,0 ,0);
                    IndividuRepository.getInstance().addIndividu(individu);
                    JOptionPane.showMessageDialog(this, "Individu '" + name + "' ajouté !");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Erreur : Veuillez entrer un nombre valide pour l'âge.");
                }
            }
    }
}
