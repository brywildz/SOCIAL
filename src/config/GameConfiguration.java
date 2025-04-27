package config;

/**
 *Classe gérant les différentes possibilités de configuration
 */
public class GameConfiguration {

    public static final int FIRST_DAY = 5;

    public static final int SECOND_START = 0;
    public static final int MINUTE_START = 0;
    public static final int HOUR_START = 7;

    public static final int YEAR_START = 2025;
    public static final int MONTH_START = 1;
    public static final int DAY_START = 1;

    public static boolean GAME = true;

    public static final int WINDOW_WIDTH = 1500;
    public static final int WINDOW_HEIGHT = 750;

    public static final int BLOCK_SIZE = 10;

    public static final int LINE_COUNT = WINDOW_HEIGHT / BLOCK_SIZE;
    public static final int COLUMN_COUNT = WINDOW_WIDTH / BLOCK_SIZE;

    public static int GAME_SPEED = 1000;

    public static int CITY_X = 2, CITY_Y = 2;
    public static int CITY_WIDTH = 149, CITY_HEIGHT = 35;

    public static final int APARTMENT1_X = 14, APARTMENT1_Y = 36;
    public static final int APARTMENT2_X = 61, APARTMENT2_Y = 15;
    public static final int APARTMENT3_X = 85, APARTMENT3_Y = 1;
    public static final int APARTMENT_WIDTH = 7, APARTMENT_HEIGHT = 8;

    public static final int HOUSE1_X = 30, HOUSE1_Y = 1;
    public static final int HOUSE2_X = 38, HOUSE2_Y = 1;
    public static final int HOUSE3_X = 45, HOUSE3_Y = 1;
    public static final int HOUSE1_WIDTH = 7, HOUSE1_HEIGHT = 7;

    public static final int ADMIN_WIDTH = 8, ADMIN_HEIGHT = 8;
    public static final int ADMIN1_X = 4, ADMIN1_Y = 3;
    public static final int ADMIN2_X = 64, ADMIN2_Y = 2;

    public static final int BANK_WIDTH = 7, BANK_HEIGHT = 7;
    public static final int BANK_X = 14, BANK_Y = 5;

    public static final int LIBRARY_WIDTH = 10, LIBRARY_HEIGHT = 5;
    public static final int LIBRARY_X = 31, LIBRARY_Y = 34;

    public static final int SCHOOL_WIDTH = 8, SCHOOL_HEIGHT = 8;
    public static final int SCHOOL1_X = 36, SCHOOL1_Y = 21;
    public static final int SCHOOL2_X = 134, SCHOOL2_Y = 27;

    public static final int PARC_WIDTH = 43, PARC_HEIGHT = 6;
    public static final int PARC_X = 97, PARC_Y = 11;

    public static final int HOSPITAL_WIDTH = 7, HOSPITAL_HEIGHT = 7;
    public static final int HOSPITAL_X = 17, HOSPITAL_Y = 17;

    public static final int NIGHTCLUB_WIDTH = 10, NIGHTCLUB_HEIGHT = 10;
    public static final int NIGHTCLUB_X = 136, NIGHTCLUB_Y = 47;

    public static final int POLICESTATION_WIDTH = 4, POLICESTATION_HEIGHT = 3;
    public static final int POLICESTATION_X = 70, POLICESTATION_Y = 15;

    public static final int RESTAURANT_WIDTH = 8, RESTAURANT_HEIGHT = 6;
    public static final int RESTAURANT_X = 55, RESTAURANT_Y = 30;

    public static final int FOREST_WIDTH = 149, FOREST_HEIGHT = 7;
    public static final int FOREST_X = 0, FOREST_Y = 67;

    public static final int ASSOCIATION_WIDTH = 8, ASSOCIATION_HEIGHT = 8;
    public static final int ASSOCIATION_X = 84, ASSOCIATION_Y = 33;

    public static final int CLOTHING_WIDTH = 3, CLOTHING_HEIGHT = 3;
    public static final int CLOTHING_X = 58, CLOTHING_Y = 30;

    public static final int FURNITURE_WIDTH = 3, FURNITURE_HEIGHT = 3;
    public static final int FURNITURE_X = 61, FURNITURE_Y = 30;

    public static final int TOWER_WIDTH = 3, TOWER_HEIGHT = 3;
    public static final int TOWER_X = 45, TOWER_Y = 15;

    public static final int AIRPORT_WIDTH = 4, AIRPORT_HEIGHT = 4;
    public static final int AIRPORT_X = 80, AIRPORT_Y = 10;

    public static final int FIRESTATION_WIDTH = 3, FIRESTATION_HEIGHT = 3;
    public static final int FIRESTATION_X = 40, FIRESTATION_Y = 20;

    public static final int GOVERNMENT_WIDTH = 4, GOVERNMENT_HEIGHT = 4;
    public static final int GOVERNMENT_X = 25, GOVERNMENT_Y = 15;

    public static final int GYM_WIDTH = 8, GYM_HEIGHT = 8;
    public static final int GYM_X = 78, GYM_Y = 13;

    public static final int MUSEE_WIDTH = 8, MUSEE_HEIGHT = 8;
    public static final int MUSEE_X = 2, MUSEE_Y = 28;

    public static final int CINEMA_WIDTH = 9, CINEMA_HEIGHT = 9;
    public static final int CINEMA_X = 34, CINEMA_Y = 10;

    public static void speedUpGame(){

        GAME_SPEED = 65;
    }

    public static void speedNormalGame(){
        GAME_SPEED = 1000;
    }

    public static void speedDownGame(){
        GAME_SPEED = 2000;
    }

}
