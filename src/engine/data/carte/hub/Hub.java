package engine.data.carte.hub;

import engine.data.carte.Infrastructure;

import java.util.HashMap;

/**
 * Classe abstraite de donnée stockant les information liée aux Espaces du jeu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public abstract class Hub {
    private int x;
    private int y;
    protected HashMap<String, Infrastructure> infrastructures;

    public Hub(int x, HashMap<String, Infrastructure> infrastructures, int y) {
        this.x = x;
        this.y = y;
        this.infrastructures = new HashMap<>();
    }
    public HashMap<String, Infrastructure> getInfrastructures() {
        return infrastructures;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
