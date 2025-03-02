package engine.data.person;
import engine.data.person.bienetre.*;

import java.util.HashMap;

/**
 * Classe de donnée regroupant toutes les instance de BienEtre d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class PersonState {
    private HashMap<String, BienEtre> etat;


    public PersonState(HashMap<String, BienEtre> etat) {

        this.etat = etat;
    }

    public HashMap<String, BienEtre> getList() {
        return etat;
    }

    @Override
    public String toString() {
        return "Etat{" +
                "etat=" + etat +
                '}';
    }

    public void setList(HashMap<String, BienEtre> etat) {
        this.etat = etat;
    }
}
