package engine.data.carte.hub;

import engine.data.carte.Infrastructure;
import engine.data.carte.InfrastructureRepository;

import java.util.HashMap;

public class HousingHub {
    private HashMap<String, Infrastructure> houses;

    public HousingHub() {
        houses = InfrastructureRepository.getInstance().getHousingHub();
    }

    public void ajouterInfrastructure(String nom, Infrastructure infrastructure) {
        houses.put(nom, infrastructure);
    }
}
