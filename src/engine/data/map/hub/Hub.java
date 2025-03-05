package engine.data.map.hub;

import engine.data.map.Block;
import engine.data.map.Infrastructure;

import java.util.HashMap;

/**
 * Classe abstraite de donnée stockant les information liée aux Espaces du jeu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public abstract class Hub {
    protected Block blocks[][];
    private int line;
    private int column;
    protected HashMap<String, Infrastructure> infrastructures;

    public Hub(int line, int column, HashMap<String, Infrastructure> infrastructures) {
        this.line = line;
        this.column = column;
        this.infrastructures = new HashMap<>();
    }
    public HashMap<String, Infrastructure> getInfrastructures() {
        return infrastructures;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
