package engine.data.person;
import engine.data.person.vitality.*;

import java.util.HashMap;

/**
 * Classe de donnée regroupant toutes les instance de Vitality d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class PersonState {
    private HashMap<String, Vitality> etat;


    public PersonState(HashMap<String, Vitality> etat) {

        this.etat = etat;
    }

    public HashMap<String, Vitality> getList() {
        return etat;
    }

    @Override
    public String toString() {
        return "Etat{" +
                "etat=" + etat +
                '}';
    }

    public void setList(HashMap<String, Vitality> etat) {
        this.etat = etat;
    }
}
