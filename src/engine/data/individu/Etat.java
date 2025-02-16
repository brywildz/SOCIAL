package engine.data.individu;
import engine.data.individu.bienetre.*;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe de donnée regroupant toutes les instance de BienEtre d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Etat {
    private HashMap<String, BienEtre> etat;


    public Etat(HashMap<String, BienEtre> etat) {

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
