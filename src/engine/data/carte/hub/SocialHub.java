package engine.data.carte.hub;

import engine.data.carte.Infrastructure;

import java.util.HashMap;

/**
 * Classe de donnée stockant les information liée aux Espaces Sociaux (lieux de rencontre)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class SocialHub extends Hub {


    public SocialHub(int x, HashMap<String, Infrastructure> infrastructures, int y) {
        super(x, infrastructures, y);
    }
}
