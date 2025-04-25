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

    public void addLine(int line) {
        this.line += line;
    }

    public void addColumn(int column) {
        this.column += column;
    }

    @Override
    public String toString() {
        return "Block [column=" + column + ", line=" + line + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Block) {
            return ((Block) obj).getLine() == line && ((Block) obj).getColumn() == column;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }
}
