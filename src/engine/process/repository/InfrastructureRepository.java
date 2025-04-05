package engine.process.repository;

import engine.data.map.Block;
import engine.data.map.Infrastructure;
import engine.data.person.Person;

import java.util.HashMap;
import java.util.Random;

import static config.GameConfiguration.*;

/**
 * Singleton stockant l'entierté des infrastructures de la carte
 */
public class InfrastructureRepository {
    private HashMap<String, Infrastructure> housingHub;
    private HashMap<String, Infrastructure> workHub;
    private HashMap<String, Infrastructure> socialHub;
    private HashMap<String, Infrastructure> lifeHub;
    private final HashMap<String, Infrastructure> infrastructures = new HashMap<>();;
    private InfrastructureRepository() {
        infrastructures.put("parc", new Infrastructure("parc",PARC_X,PARC_Y,PARC_WIDTH,PARC_HEIGHT));
        infrastructures.put("musée", new Infrastructure("musée",MUSEUM_X,MUSEUM_Y,MUSEUM_WIDTH,MUSEUM_HEIGHT));
        infrastructures.put("travail1", new Infrastructure("travail1",ADMIN_X,ADMIN_Y,ADMIN_WIDTH,ADMIN_HEIGHT));
        infrastructures.put("travail2", new Infrastructure("travail2",ADMIN_X+4,ADMIN_Y+3,ADMIN_WIDTH,ADMIN_HEIGHT));
        infrastructures.put("cinéma", new Infrastructure("cinéma",CINEMA_X,CINEMA_Y,CINEMA_WIDTH,CINEMA_HEIGHT));
        infrastructures.put("bibliothèque", new Infrastructure("bibliothèque",LIBRARY_X,LIBRARY_Y,LIBRARY_WIDTH,LIBRARY_HEIGHT));
        infrastructures.put("école", new Infrastructure("école",SCHOOL_X,SCHOOL_Y,SCHOOL_WIDTH,SCHOOL_HEIGHT));
        infrastructures.put("maison1", new Infrastructure("maison1",HOUSE1_X,HOUSE1_Y, HOUSE1_WIDTH ,HOUSE1_HEIGHT ));
        infrastructures.put("maison2", new Infrastructure("maison2",HOUSE2_X,HOUSE2_Y,HOUSE1_WIDTH,HOUSE1_HEIGHT));
        infrastructures.put("maison3", new Infrastructure("maison3",HOUSE3_X,HOUSE3_Y,HOUSE1_WIDTH,HOUSE1_HEIGHT));
        //infrastructures.put("maison4", new Infrastructure("maison4",HOUSE4_X,HOUSE4_Y,HOUSE1_WIDTH,HOUSE1_HEIGHT));
        //infrastructures.put("maison5", new Infrastructure("maison5",HOUSE5_X,HOUSE5_Y, HOUSE1_WIDTH ,HOUSE1_HEIGHT ));
        infrastructures.put("apartment1", new Infrastructure("apartment1",APARTMENT_X,APARTMENT_Y, APARTMENT_WIDTH,APARTMENT_HEIGHT));
        infrastructures.put("apartment2", new Infrastructure("apartment2",APARTMENT_X-5,APARTMENT_Y, APARTMENT_WIDTH,APARTMENT_HEIGHT));
        infrastructures.put("apartment3", new Infrastructure("apartment3",APARTMENT_X-10,APARTMENT_Y, APARTMENT_WIDTH,APARTMENT_HEIGHT));
        infrastructures.put("cinema", new Infrastructure("cinema", CINEMA_X,CINEMA_Y, CINEMA_WIDTH, CINEMA_HEIGHT));
    }

    private static InfrastructureRepository instance;

    public static InfrastructureRepository getInstance() {
        if (instance == null) {
            instance = new InfrastructureRepository();
        }
        return instance;
    }

    public Infrastructure get(String id) {
        return infrastructures.get(id);
    }

    public HashMap<String, Infrastructure> getInfrastructures() {
        return infrastructures;
    }

    public Infrastructure get(Block b){
        for (Infrastructure infrastructure : infrastructures.values()) {
            Block[][] zone = infrastructure.getZone();
            for (Block[] blocks : zone) {
                for (Block block : blocks) {
                    if (block.equals(b)) {
                        return infrastructure;
                    }
                }
            }
        }
        return null;
    }

    public Infrastructure randomWorkingPlace(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(2);
        if(randomIndex == 0){
            return infrastructures.get("travail1");
        }
        else{
            return infrastructures.get("travail2");
        }
    }

    public Infrastructure getRandomHouse(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(7);
        if(randomIndex == 0){
            return infrastructures.get("maison1");
        }
        if(randomIndex == 1){
            return infrastructures.get("maison2");
        }
        if(randomIndex == 2){
            return infrastructures.get("maison3");
        }
        if(randomIndex == 5){
            return infrastructures.get("apartment1");
        }
        if(randomIndex == 6){
            return infrastructures.get("apartment2");
        }
        return infrastructures.get("apartment3");
    }

    public void addPerson(Person per, Infrastructure activityPlace) {
        infrastructures.get(activityPlace.getNom()).addPerson(per);
    }
}
