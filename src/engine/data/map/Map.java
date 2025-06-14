package engine.data.map;

import config.GameConfiguration;
import engine.data.event.WeatherEvent;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;

import java.util.HashMap;

/**
 * Classe de donnée stockant les informations liée à la carte du jeu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Map {
    private int lineCount;
    private int columnCount;
    private HashMap<Integer, String> espaces;
    private Block[][] blocks;
    private HashMap<String, Person> individus;
    private WeatherEvent weather;
    private static Map instance;

    public Map(int lineCount, int columnCount){
        this.columnCount = columnCount;
        this.lineCount = lineCount;
        this.individus = PersonRepository.getInstance().getPersons();
        blocks = new Block[this.lineCount][this.columnCount];

        for (int lineIndex = 0; lineIndex < this.lineCount; lineIndex++) {
            for (int columnIndex = 0; columnIndex < this.columnCount; columnIndex++) {
                blocks[lineIndex][columnIndex] = new Block(lineIndex, columnIndex);
            }
        }
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
        }
        return instance;
    }

    private void init(int lineCount, int columnCount) {
        this.lineCount = lineCount;
        this.columnCount = columnCount;

        blocks = new Block[lineCount][columnCount];


    }


    public HashMap<Integer, String> getEspaces() {
        return espaces;
    }

    public void setEspaces(HashMap<Integer, String> espaces) {
        this.espaces = espaces;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public HashMap<String, Person> getIndividus() {
        return individus;
    }

    public void setIndividus(HashMap<String, Person> individus) {
        this.individus = individus;
    }

    public Block getBlock(int y, int x){
        return blocks[y][x];
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public WeatherEvent getWeather() {
        return weather;
    }

    public void setWeather(WeatherEvent weather) {
        this.weather = weather;
    }
}
