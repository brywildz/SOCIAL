package engine.data.carte.hub;

import engine.data.carte.Infrastructure;
import engine.data.carte.InfrastructureRepository;

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
