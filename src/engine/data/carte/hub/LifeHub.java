package engine.data.carte.hub;

import engine.data.carte.Infrastructure;

import java.util.HashMap;

/**
 * Classe de donnée stockant les informations liée aux Espaces Vitaux (lieu de vie)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class LifeHub extends Hub {

    public LifeHub(int x, HashMap<String, Infrastructure> infrastructures, int y) {
        super(x, infrastructures, y);
    }
}
