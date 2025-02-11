package engine.data.individu;

import java.util.ArrayList;

public class Relation {
    private ArrayList<Individu> pro;
    private ArrayList<Individu> familiale;
    private ArrayList<Individu> amicale;

    public Relation(ArrayList<Individu> amicale, ArrayList<Individu> familiale, ArrayList<Individu> pro) {
        this.amicale = amicale;
        this.familiale = familiale;
        this.pro = pro;
    }
}
