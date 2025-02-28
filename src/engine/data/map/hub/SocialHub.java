package engine.data.map.hub;

import engine.data.map.Infrastructure;
import engine.data.map.InfrastructureRepository;

import java.util.HashMap;

/**
 * Classe de donnée stockant les information liée aux Espaces Sociaux (lieux de rencontre)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class SocialHub extends Hub {


    public SocialHub(int line, int column, HashMap<String, Infrastructure> infrastructures) {
        super(line, column, InfrastructureRepository.getInstance().getSocialHub());
    }
}
