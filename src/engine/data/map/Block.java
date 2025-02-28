package engine.data.map;

import java.util.Objects;

/**
 * Classe de donnée stockant les informations liée aux blocks
 *
 * @author Dylan Manseri
 * @version 0.1
 */

public class Block {
    private int line;
    private int column;

    public Block(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Block [line=" + line + ", column=" + column + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Block) {
            if(((Block) obj).getLine() == line && ((Block) obj).getColumn() == column) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }
}
