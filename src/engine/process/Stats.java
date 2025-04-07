package engine.process;

import java.util.HashMap;

import engine.data.person.Person;
import engine.data.person.PersonState;
import engine.data.person.personalityTraits.PersonalityTrait;
import engine.process.repository.PersonRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import engine.data.person.personalityTraits.Agreeableness;
import engine.data.person.personalityTraits.Conscientiousness;
import engine.data.person.personalityTraits.Extraversion;
import engine.data.person.personalityTraits.Neuroticism;
import engine.data.person.personalityTraits.Openness;


public class Stats {
    private Person person;
    private PersonRepository personRepo = PersonRepository.getInstance();
    private HashMap<Character, Integer> traitCounts = new HashMap<>();
    private HashMap<String, Integer> activityCounts = new HashMap<>();


    //Camembert pour accéder au info de de la personnalites de tout le monde (5 trait)
    public JFreeChart getPersonalityDistributionPie() {
        traitCounts.put('a', 0); // Agreeableness
        traitCounts.put('c', 0); // Conscientiousness
        traitCounts.put('e', 0); // Extraversion
        traitCounts.put('n', 0); // Neuroticism
        traitCounts.put('o', 0); // Openness

        for (Person p : personRepo.getPersons().values()) {
            PersonalityTrait maxTrait = p.getPersonality().getMaxPerso();
            if (maxTrait instanceof Agreeableness) traitCounts.put('a', traitCounts.get('a') + 1);
            else if (maxTrait instanceof Conscientiousness) traitCounts.put('c', traitCounts.get('c') + 1);
            else if (maxTrait instanceof Extraversion) traitCounts.put('e', traitCounts.get('e') + 1);
            else if (maxTrait instanceof Neuroticism) traitCounts.put('n', traitCounts.get('n') + 1);
            else if (maxTrait instanceof Openness) traitCounts.put('o', traitCounts.get('o') + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Agreeableness", traitCounts.get('a'));
        dataset.setValue("Conscientiousness", traitCounts.get('c'));
        dataset.setValue("Extraversion", traitCounts.get('e'));
        dataset.setValue("Neuroticism", traitCounts.get('n'));
        dataset.setValue("Openness", traitCounts.get('o'));

        return ChartFactory.createPieChart("Répartition des personnalités", dataset, true, true, false);
    }
    //Pour accéder à la personnaliter d'un seul individus en particulier
    public JFreeChart getPersonalityPie(Person person) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Agréabilité", person.getAgreeableness().getLevel());
        dataset.setValue("Conscienciosité", person.getConscientiousness().getLevel());
        dataset.setValue("Extraversion", person.getExtraversion().getLevel());
        dataset.setValue("Nervosité", person.getNeuroticism().getLevel());
        dataset.setValue("Ouverture", person.getOpenness().getLevel());

        return ChartFactory.createPieChart("Personnalite de " + person.getName(), dataset, true, true, false);
    }


    // représente des etats de sante/ humeur etc "PersonState"
    public JFreeChart getAverageStatesBar() {

        int totalHealth = 0;
        int totalMood = 0;
        int totalSleep = 0;
        int totalHunger = 0;
        int count = 0;


        for (Person p : personRepo.getPersons().values()) {
            PersonState state = p.getPersonState();
            totalHealth += state.getHealth().getNiveau();
            totalMood += state.getMood().getNiveau();
            totalSleep += state.getSleep().getNiveau();
            totalHunger += state.getHunger().getNiveau();
            count++;
        }

        // cas ou on aurait à diviser par 0 exemple : avec un seul pnj quoi
        if (count == 0) count = 1;

        // Moyennes
        double avgHealth = totalHealth / (double) count;
        double avgMood = totalMood / (double) count;
        double avgSleep = totalSleep / (double) count;
        double avgHunger = totalHunger / (double) count;

        // Création du graphique
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(avgHealth, "States", "Santé");
        dataset.setValue(avgMood, "States", "Humeur");
        dataset.setValue(avgSleep, "States", "Sommeil");
        dataset.setValue(avgHunger, "States", "Faim");


        return ChartFactory.createBarChart(
                "Moyenne des états vitaux",
                "Type d'état",
                "Valeur moyenne",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );
    }
    // représente des etats de sante/ humeur etc "PersonState" d'un seul individu
    public JFreeChart getPersonStateBar(Person person) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int health = person.getPersonState().getHealth().getNiveau();
        int mood = person.getPersonState().getMood().getNiveau();
        int sleep = person.getPersonState().getSleep().getNiveau();
        int hunger = person.getPersonState().getHunger().getNiveau();

        dataset.setValue(health, "State", "Santé");
        dataset.setValue(mood, "State", "Humeur");
        dataset.setValue(sleep, "State", "Sommeil");
        dataset.setValue(hunger, "State", "Faim");

        return ChartFactory.createBarChart(
                "État de " + person.getName(),
                "Catégories",
                "Niveau",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
    }
    // méthodes pour voir les activitées des individus
    public JFreeChart getActivityDistributionPie() {

        for (Person p : personRepo.getPersons().values()) {
            if (p.getHobby() != null) {
                String action = p.getHobby().getId();
                activityCounts.put(action, activityCounts.getOrDefault(action, 0) + 1);
            }
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String activity : activityCounts.keySet()) {
            dataset.setValue(activity, activityCounts.get(activity));
        }

        return ChartFactory.createPieChart("Répartition des Activités", dataset, true, true, false);
    }

    public JFreeChart getRelationPie(Person person) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Amicales", person.getRelation().getAmicale().size());
        dataset.setValue("Familiales", person.getRelation().getFamiliale().size());
        dataset.setValue("Professionnelles", person.getRelation().getPro().size());

        return ChartFactory.createPieChart("Graphique des relations", dataset, true, true, false);
    }
}


