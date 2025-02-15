package gui;

import engine.data.evenement.EventMeteo;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    Container base;
    JButton button;

    public ControlPanel(Container base) {
    this.base=base;
    this.setPreferredSize(new Dimension(300, 0));
    button = new JButton("Pluie");
    add(button);
    button.addActionListener((event)-> new EventMeteo("pluie","il pleut",50,50));
    }


}
