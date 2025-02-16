package config;

/**
 *Classe gérant les différente possibilité de configuration
 */
public class GameConfiguration {
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 800;

    public static final int BLOCK_SIZE = 25;

    public static final int LINE_COUNT = WINDOW_HEIGHT / BLOCK_SIZE;
    public static final int COLUMN_COUNT = WINDOW_WIDTH / BLOCK_SIZE;

    public static final int GAME_SPEED = 1000;

    public static final int HOUSE_X = 200;
    public static final int HOUSE_Y = 100;
    public static final int HOUSE_WIDTH = 300;
    public static final int HOUSE_HEIGHT = 300;
}
