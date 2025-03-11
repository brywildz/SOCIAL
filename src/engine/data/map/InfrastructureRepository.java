package engine.data.map;

import java.util.HashMap;

import static config.GameConfiguration.*;
import static config.GameConfiguration.BUILDING_HEIGHT;

/**
 * Singleton stockant l'entierté des infrastructures de la carte
 */
public class InfrastructureRepository {
    private HashMap<String, Infrastructure> housingHub;
    private HashMap<String, Infrastructure> workHub;
    private HashMap<String, Infrastructure> socialHub;
    private HashMap<String, Infrastructure> lifeHub;
    private InfrastructureRepository() {
        housingHub = new HashMap<>();
        workHub = new HashMap<>();
        socialHub = new HashMap<>();
        lifeHub = new HashMap<>();

        housingHub.put("Maison1", new Infrastructure("Maison1", APARTMENT_X, APARTMENT_Y, APARTMENT_WIDTH, APARTMENT_HEIGHT));
        /*housingHub.put("Maison2", new Infrastructure("Maison2",0,0, , ));
        housingHub.put("Maison3", new Infrastructure("Maison3",0,0, , ));*/

        workHub.put("Bureau", new Infrastructure("building", BUILDING_X, BUILDING_Y, BUILDING_WIDTH, BUILDING_HEIGHT));
        /*workHub.put("Magasin", new Infrastructure("Magasin",0,0, , ));
        workHub.put("Banque", new Infrastructure("Banque",0,0, , ));

        socialHub.put("event_hall", new Infrastructure("event_hall",0,0, , ));
        socialHub.put("Cinéma", new Infrastructure("Cinéma",0,0, , ));
        socialHub.put("Parc", new Infrastructure("Parc",0,0, , ));
        socialHub.put("Bibliothèque", new Infrastructure("Bibliothèque",0,0, , ));

        lifeHub.put("Hôpital", new Infrastructure("Hôpital",0,0, , ));*/

    }
    private static InfrastructureRepository instance;

    public static InfrastructureRepository getInstance() {
        if (instance == null) {
            instance = new InfrastructureRepository();
        }
        return instance;
    }

    public HashMap<String, Infrastructure> getHousingHub() {
        return housingHub;
    }

    public HashMap<String, Infrastructure> getWorkHub() {
        return workHub;
    }

    public HashMap<String, Infrastructure> getSocialHub() {
        return socialHub;
    }

    public HashMap<String, Infrastructure> getLifeHub() {
        return lifeHub;
    }

    public Infrastructure get(String id) {
        if (housingHub.containsKey(id)) {
            return housingHub.get(id);
        }
        if (workHub.containsKey(id)) {
            return workHub.get(id);
        }
        if (socialHub.containsKey(id)) {
            return socialHub.get(id);
        }
        if (lifeHub.containsKey(id)) {
            return lifeHub.get(id);
        }
        return null;
    }
}
