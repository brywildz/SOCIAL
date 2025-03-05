package engine.data.map.hub;

import config.GameConfiguration;
import engine.data.map.Block;
import engine.data.map.Infrastructure;
import engine.data.map.InfrastructureRepository;

import java.util.HashMap;

/**
 * Classe de donnée stockant les informations liée aux Espaces Vitaux (lieu de vie)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class LifeHub extends Hub {
    public LifeHub(int line, int column, HashMap<String, Infrastructure> infrastructures) {
        super(line, column, InfrastructureRepository.getInstance().getLifeHub());
    }
}
