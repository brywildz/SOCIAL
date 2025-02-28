package config;

/**
 *Classe gérant les différentes possibilités de configuration
 */
public class GameConfiguration {
    public static final int WINDOW_WIDTH = 1366;
    public static final int WINDOW_HEIGHT = 900;

    public static final int BLOCK_SIZE = 12;

    public static final int LINE_COUNT = WINDOW_HEIGHT / BLOCK_SIZE;
    public static final int COLUMN_COUNT = WINDOW_WIDTH / BLOCK_SIZE;

    public static final int GAME_SPEED = 1000;

    public static final int WORK_HUB_X = 7;
    public static final int WORK_HUB_Y = 5;
    public static final int WORK_HUB_WIDTH = 37;
    public static final int WORK_HUB_HEIGHT = 23;

    public static final int HOUSING_HUB_X = 66;
    public static final int HOUSING_HUB_Y = 8;
    public static final int HOUSING_HUB_WIDTH = 39;
    public static final int HOUSING_HUB_HEIGHT = 21;
}
