package gui;

import engine.data.person.vitality.Health;
import engine.data.person.vitality.Hunger;
import engine.data.person.vitality.Mood;
import engine.data.person.vitality.Sleep;
import engine.process.Stats;
import engine.data.person.Person;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PieSectionEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class StatsDisplay extends JFrame {
    JLabel chartComment = new JLabel();
    JPanel commentPane = new JPanel(new BorderLayout());
    Person person;
    JPanel friendsPanel;
    JScrollPane relationInfo;

    public StatsDisplay(String title, JFreeChart chart) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());

        ChartPanel panel = new ChartPanel(chart);
        this.add(panel, BorderLayout.CENTER);

        JPanel friendsPanel = new JPanel();

        this.setVisible(true);
    }

    public StatsDisplay(Person person) {
        super("Bilan psychologique de "+person.getName());
        this.setResizable(false);
        this.person = person;
        commentPane.add(chartComment);
        this.setSize(1040, 750);
        this.setLayout(new BorderLayout());
        Stats stats = new Stats();
        JFreeChart personality = stats.getPersonalityPie(person);
        JFreeChart state = stats.getPersonStateBar(person);
        JFreeChart relation = stats.getRelationPie(person);

        ChartPanel personalityChart = new ChartPanel(personality);
        ChartPanel relationChart = new ChartPanel(relation);
        ChartPanel stateChart = new ChartPanel(state);
        personalityChart.setPreferredSize(new Dimension(500,350));
        relationChart.setPreferredSize(new Dimension(500,350));
        stateChart.setPreferredSize(new Dimension(500,350));

        JPanel humanStats = new JPanel();
        humanStats.add(personalityChart);
        humanStats.add(stateChart);
        friendsPanel = new JPanel();
        friendsPanel.setLayout(new BorderLayout());
        friendsPanel.add(relationChart, BorderLayout.EAST);
        friendsPanel.add(commentPane, BorderLayout.WEST);
        
        personalityChart.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
                setPersonalityLabel(event);
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {}
        });
        relationChart.addChartMouseListener(new ChartMouseListener() {

            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
                setRelationLabel(event);
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {

            }
        });
        stateChart.addChartMouseListener(new ChartMouseListener() {

            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
                setStateLabel(event);
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent event) {

            }
        });

        this.add(humanStats, BorderLayout.NORTH);
        this.add(friendsPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    // <editor-fold> desc="PersonalityLabel"
    private void setPersonalityLabel(ChartMouseEvent event) {
        commentPane.removeAll();
        ChartEntity entity = event.getEntity();
        if(entity instanceof PieSectionEntity){
            PieSectionEntity bar = (PieSectionEntity) entity;
            Comparable key = bar.getSectionKey();
            switch(key.toString()){
                case "Ouverture" : setOpennessLabel();
                case "Extraversion" : setExtraversionLabel();
                case "Nervosité" : setNeuroticismLabel();
                case "Conscienciosité" : setConscientiousnessLabel();
                case "Agréabilité" : setAgreeablenessLabel();
            }
        }
    }

    private void setAgreeablenessLabel() {
        String text = "<html><h1>Agréabilité (Big Five)</h1>" +
                "<p>L’agréabilité reflète la gentillesse, l’empathie et la coopération.</p>" +
                "<p>Une personne très agréable est chaleureuse, attentionnée et prête à aider.</p>" +
                "<p>Une personne peu agréable est plus critique, directe et parfois conflictuelle.</p></html>";
        chartComment.setText(text);
        chartComment.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commentPane.add(chartComment);
        this.revalidate();
        this.repaint();
    }

    private void setConscientiousnessLabel() {
        String text = "<html><h1>Conscienciosité (Big Five)</h1>" +
                "<p>La conscienciosité traduit l’organisation, la rigueur et la fiabilité.</p>" +
                "<p>Une personne très consciencieuse est disciplinée, méthodique et responsable.</p>" +
                "<p>Une personne peu consciencieuse est plus impulsive, désorganisée ou distraite.</html>";
        chartComment.setText(text);
        chartComment.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commentPane.add(chartComment);
        this.revalidate();
        this.repaint();
    }

    private void setNeuroticismLabel() {
        String text = "<html><h1>Extraversion (Big Five)</h1>" +
                "<p>L’extraversion mesure le niveau d’énergie sociale et d’expression.</p>" +
                "<p>Une personne très extravertie est sociable, expressive et aime être entourée.</p>" +
                "<p>Une personne introvertie est plus réservée, calme et préfère la solitude.</html>";
        chartComment.setText(text);
        chartComment.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commentPane.add(chartComment);
        this.revalidate();
        this.repaint();
    }

    private void setExtraversionLabel() {
        String text = "<html><h1>Neuroticisme (Big Five)</h1>" +
                "<p>Le neuroticisme reflète la stabilité émotionnelle et la sensibilité au stress.</p>" +
                "<p>Une personne très névrosée est plus anxieuse, émotive ou facilement irritable.</p>" +
                "<p>Une personne peu névrosée est calme, stable et gère bien les tensions.</p></html>";
        chartComment.setText(text);
        chartComment.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commentPane.add(chartComment);
        this.revalidate();
        this.repaint();
    }

    private void setOpennessLabel() {
        String text = "<html><h1>Ouverture à l’expérience (Big Five)</h1>" +
                "<p>Reflète la curiosité, l’imagination et l’intérêt pour la nouveauté.</p>" +
                "<p>Une personne très ouverte est créative, aime explorer et essayer de nouvelles choses.</p>" +
                "<p>Une personne peu ouverte préfère la routine, les idées concrètes et les environnements<br>familiers.</p></html>";
        chartComment.setText(text);
        chartComment.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commentPane.add(chartComment);
        this.revalidate();
        this.repaint();
    }

    //</editor-fold>

    // <editor-fold> desc="StateLabel"
    private void setStateLabel(ChartMouseEvent event) {
        commentPane.removeAll();

        ChartEntity entity = event.getEntity();
        if(entity instanceof CategoryItemEntity){
            CategoryItemEntity bar = (CategoryItemEntity) entity;
            Comparable key = bar.getColumnKey();
            switch (key.toString()){
                case "Santé" : setHealthLabel();
                case "Faim" : setHungerLabel();
                case "Humeur" : setMoodLabel();
                case "Sommeil" : setSleepLabel();
            }
        }
        this.revalidate();
        this.repaint();
    }

    private void setSleepLabel() {
        Sleep sleep = person.getPersonState().getSleep();
        String text = "<html><h1>Sommeil (Etat)</h1>";
        if(sleep.isHigh()){
            text += "<p>"+person.getName()+" est très fatigué/e il/elle devrait se reposer.</p></html>";
        }
        else if(sleep.isLow()){
            text += "<p>"+person.getName()+" est en forme aujourd'hui il/elle a dû bien dormir.</p></html>";
        }
        else{
            text += "<p>"+person.getName()+" a un sommeil correct ni trop fatigué ni trop en forme</p></html>";
        }
        chartComment.setText(text);
        chartComment.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        chartComment.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commentPane.add(chartComment);
    }

    private void setMoodLabel() {
        Mood mood = person.getPersonState().getMood();
        String text = "<html><h1>Humeur (Etat)</h1>";
        if(mood.isHigh()){
            text += "<p>"+person.getName()+" est de très bonne humeur. Sûrement une bonne nouvelle.</p></html>";
        }
        else if(mood.isLow()){
            text += "<p>"+person.getName()+" est de mauvaise humeur aujourd'hui. Que lui est-il arrivé/e ?</p></html>";
        }
        else{
            text += "<p>"+person.getName()+" a une humeur correct, rien à signaler.</p></html>";
        }
        chartComment.setText(text);
        chartComment.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        chartComment.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commentPane.add(chartComment);
    }

    private void setHungerLabel() {
        Hunger hunger = person.getPersonState().getHunger();
        String text = "<html><h1>Faim (Etat)</h1>";
        if(hunger.isHigh()){
            text += "<p>"+person.getName()+" a très faim, il/elle a besoin de manger.</p></html>";
        }
        else if(hunger.isLow()){
            text += "<p>"+person.getName()+" n'a pas très faim pour l'instant.</p></html>";
        }
        else{
            text += "<p>"+person.getName()+" a beaucoup trop mangé, il/elle ne peut rien avaler.</p></html>";
        }
        chartComment.setText(text);
        chartComment.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        chartComment.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commentPane.add(chartComment);

    }

    private void setHealthLabel() {
        Health health = person.getPersonState().getHealth();
        String text = "<html><h1>Santé (Etat)</h1>";
        if(health.isHigh()){
            text += "<p>"+person.getName()+" est malade en ce moment, il/elle doit aller chez le médecin.</p></html>";
        }
        else if(health.isLow()){
            text += "<p>"+person.getName()+" n'est pas malade.</p></html>";
        }
        else{
            text += "<p>"+person.getName()+" est en très bonne santé.</p></html>";
        }
        chartComment.setText(text);
        chartComment.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        chartComment.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commentPane.add(chartComment);
    }
    // </editor-fold>


    private void setRelationLabel(ChartMouseEvent event) {
        commentPane.removeAll();
        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.add(chartComment);
        String type="";
        JTextArea relationText = new JTextArea();
        relationText.setEditable(false);
        ChartEntity entity = event.getEntity();
        if(entity instanceof PieSectionEntity){
            PieSectionEntity bar = ( PieSectionEntity) entity;
            Comparable key = bar.getSectionKey();
            switch (key.toString()){
                case "Familiales" : {
                    type = "familiales";
                    setFamilyLabel(table);
                }
                case "Professionnelles" : {
                    type = "professionnelles";
                    setProLabel(table);
                }
                case "Amicales" : {
                    type = "amicales";
                    setFriendsLabel(table);
                }
            }
            chartComment.setText("<html><h1>Relations "+type+" de "+person.getName()+"</h1></html>");
        }
        this.revalidate();
        this.repaint();
    }

    private void setFriendsLabel(JPanel table) {
        ArrayList<Person> friends = person.getRelation().getAmicale();
        String[] colonnes = {"Nom", "Âge", "Trait Majoritaire"};
        DefaultTableModel tableModel = new DefaultTableModel(colonnes, 0);
        Iterator<Person> iterator = friends.iterator();
        while(iterator.hasNext()){
            Person friend = iterator.next();
            tableModel.addRow(new Object[]{friend.getName(), friend.getAge(), friend.getPersonality().getMaxPerso()});
        }
        JTable tableFriends = new JTable(tableModel);
        relationInfo = new JScrollPane(tableFriends);
        relationInfo.setPreferredSize(new Dimension(250, 200));
        table.add(relationInfo);
        commentPane.add(table);
    }

    private void setProLabel(JPanel table) {
        ArrayList<Person> pro = person.getRelation().getPro();
        String[] colonnes = {"Nom", "Âge", "Trait Majoritaire"};
        DefaultTableModel tableModel = new DefaultTableModel(colonnes, 0);
        Iterator<Person> iterator = pro.iterator();
        while(iterator.hasNext()){
            Person friend = iterator.next();
            tableModel.addRow(new Object[]{friend.getName(), friend.getAge(), friend.getPersonality().getMaxPerso()});
        }
        JTable tableFriends = new JTable(tableModel);
        relationInfo = new JScrollPane(tableFriends);
        relationInfo.setPreferredSize(new Dimension(250, 200));
        table.add(relationInfo);
        commentPane.add(table);
    }

    private void setFamilyLabel(JPanel table) {
        ArrayList<Person> family = person.getRelation().getFamiliale();
        String[] colonnes = {"Nom", "Âge", "Trait Majoritaire"};
        DefaultTableModel tableModel = new DefaultTableModel(colonnes, 0);
        Iterator<Person> iterator = family.iterator();
        while(iterator.hasNext()){
            Person friend = iterator.next();
            tableModel.addRow(new Object[]{friend.getName(), friend.getAge(), friend.getPersonality().getMaxPerso()});
        }
        JTable tableFriends = new JTable(tableModel);
        relationInfo = new JScrollPane(tableFriends);
        relationInfo.setPreferredSize(new Dimension(250, 200));
        table.add(relationInfo);
        commentPane.add(table);
    }

    // Affiche les stats d'un individu
    public static void showPersonStats(Person person) {
        Stats stats = new Stats();
        JFreeChart personality = stats.getPersonalityPie(person);
        JFreeChart state = stats.getPersonStateBar(person);
        JFreeChart relation = stats.getRelationPie(person);
        JFrame frame = new JFrame("Statistiques de " + person.getName());
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
