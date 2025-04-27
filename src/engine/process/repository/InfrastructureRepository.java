package engine.process.repository;

import engine.data.map.Block;
import engine.data.map.Infrastructure;
import engine.data.person.Person;

import java.util.HashMap;

import static config.GameConfiguration.*;
import static engine.process.builder.GameBuilder.random;

/**
 * Singleton stockant l'entierté des infrastructures de la carte
 */
public class InfrastructureRepository {
    private HashMap<String, Infrastructure> housingHub;
    private HashMap<String, Infrastructure> workHub;
    private HashMap<String, Infrastructure> socialHub;
    private HashMap<String, Infrastructure> lifeHub;
    private final HashMap<String, Infrastructure> infrastructures = new HashMap<>();
    private InfrastructureRepository() {
        infrastructures.put("city", new Infrastructure("city", CITY_X, CITY_Y, CITY_WIDTH, CITY_HEIGHT));
        infrastructures.put("parc", new Infrastructure("parc", PARC_X, PARC_Y, PARC_WIDTH, PARC_HEIGHT));
        infrastructures.put("office1", new Infrastructure("office1", ADMIN1_X, ADMIN1_Y, ADMIN_WIDTH, ADMIN_HEIGHT));
        infrastructures.put("office2", new Infrastructure("office2", ADMIN2_X, ADMIN2_Y, ADMIN_WIDTH, ADMIN_HEIGHT));
        infrastructures.put("bibliothèque", new Infrastructure("bibliothèque", LIBRARY_X, LIBRARY_Y, LIBRARY_WIDTH, LIBRARY_HEIGHT));
        infrastructures.put("école1", new Infrastructure("école1", SCHOOL1_X, SCHOOL1_Y, SCHOOL_WIDTH, SCHOOL_HEIGHT));
        infrastructures.put("école2", new Infrastructure("école2", SCHOOL2_X, SCHOOL2_Y, SCHOOL_WIDTH, SCHOOL_HEIGHT));
        infrastructures.put("maison1", new Infrastructure("maison1", HOUSE1_X, HOUSE1_Y, HOUSE1_WIDTH, HOUSE1_HEIGHT));
        infrastructures.put("maison2", new Infrastructure("maison2", HOUSE2_X, HOUSE2_Y, HOUSE1_WIDTH, HOUSE1_HEIGHT));
        infrastructures.put("maison3", new Infrastructure("maison3", HOUSE3_X, HOUSE3_Y, HOUSE1_WIDTH, HOUSE1_HEIGHT));
        infrastructures.put("apartment1", new Infrastructure("apartment1", APARTMENT1_X, APARTMENT1_Y, APARTMENT_WIDTH, APARTMENT_HEIGHT));
        infrastructures.put("apartment2", new Infrastructure("apartment2", APARTMENT2_X , APARTMENT2_Y, APARTMENT_WIDTH, APARTMENT_HEIGHT));
        infrastructures.put("apartment3", new Infrastructure("apartment3", APARTMENT3_X, APARTMENT3_Y, APARTMENT_WIDTH, APARTMENT_HEIGHT));
        infrastructures.put("hospital", new Infrastructure("hospital", HOSPITAL_X, HOSPITAL_Y, HOSPITAL_WIDTH, HOSPITAL_HEIGHT));
        infrastructures.put("night_club", new Infrastructure("nightclub", NIGHTCLUB_X, NIGHTCLUB_Y, NIGHTCLUB_WIDTH, NIGHTCLUB_HEIGHT));
        infrastructures.put("policeStation", new Infrastructure("policeStation", POLICESTATION_X, POLICESTATION_Y, POLICESTATION_WIDTH, POLICESTATION_HEIGHT));
        infrastructures.put("restaurant", new Infrastructure("restaurant", RESTAURANT_X, RESTAURANT_Y, RESTAURANT_WIDTH, RESTAURANT_HEIGHT));
        infrastructures.put("gym", new Infrastructure("gym", GYM_X, GYM_Y, GYM_WIDTH, GYM_HEIGHT));
        infrastructures.put("museum", new Infrastructure("museum", MUSEE_X, MUSEE_Y, MUSEE_WIDTH, MUSEE_HEIGHT));
        infrastructures.put("cinéma", new Infrastructure("cinéma", CINEMA_X, CINEMA_Y, CINEMA_WIDTH, CINEMA_HEIGHT));
        infrastructures.put("forest", new Infrastructure("forest", FOREST_X, FOREST_Y, FOREST_WIDTH, FOREST_HEIGHT));
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

    public Infrastructure getInfrastructure(Block b){
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
        int randomIndex = random(2);
        if(randomIndex == 0){
            return infrastructures.get("office1");
        }
        else{
            return infrastructures.get("office2");
        }
    }

    public Infrastructure getRandomHouse(){
        int randomIndex = random(9);
        if(randomIndex == 0){
            return infrastructures.get("maison1");
        }
        if(randomIndex == 1){
            return infrastructures.get("maison2");
        }
        if(randomIndex == 2){
            return infrastructures.get("maison3");
        }
        if(randomIndex == 3 || randomIndex == 4){
            return infrastructures.get("apartment1");
        }
        if(randomIndex == 5 || randomIndex == 6){
            return infrastructures.get("apartment2");
        }
        return infrastructures.get("apartment3");
    }

    public void addPerson(Person per, Infrastructure activityPlace) {
        infrastructures.get(activityPlace.getNom()).addPerson(per);
    }

    public Infrastructure randomSchool() {
        int r = random(2);
        if(r == 0){
            return infrastructures.get("école1");
        }
        return infrastructures.get("école2");
    }
}
