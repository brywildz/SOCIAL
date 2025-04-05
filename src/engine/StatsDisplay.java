package engine;

import engine.data.person.Person;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;

public class StatsDisplay extends JFrame {

    public StatsDisplay(String title, JFreeChart chart) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());

        ChartPanel panel = new ChartPanel(chart);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // Affiche les stats d'un individu
    public static void showPersonStats(Person person) {
        Stats stats = new Stats();
        JFreeChart personality = stats.getPersonalityPie(person);
        JFreeChart state = stats.getPersonStateBar(person);

        JFrame frame = new JFrame("Statistiques de " + person.getNom());
        frame.setLayout(new GridLayout(1, 2));
        frame.setSize(1000, 500);
        frame.add(new ChartPanel(personality));
        frame.add(new ChartPanel(state));
        frame.setVisible(true);
    }

    // Affiche les stats globales de la ville
    public static void showGlobalStats() {
        Stats stats = new Stats();
        JFreeChart personality = stats.getPersonalityDistributionPie();
        JFreeChart state = stats.getAverageStatesBar();
        JFreeChart activities = stats.getActivityDistributionPie();

        JFrame frame = new JFrame("Statistiques de la ville");
        frame.setLayout(new GridLayout(1, 3));
        frame.setSize(1200, 500);
        frame.add(new ChartPanel(personality));
        frame.add(new ChartPanel(state));
        frame.add(new ChartPanel(activities));
        frame.setVisible(true);
    }
}
