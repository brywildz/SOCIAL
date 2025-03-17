package engine.data.map;

/**
 * Classe de donnée gérant l'horloge du jeu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Clock {
    private final Time h;
    private Clock(){h=new Time(8,0,0, new Date(2025,1,1));}
    private static final Clock instance = new Clock();

    public static Clock getInstance(){return instance;};

    public void newSecond(){
        h.newSecond();
    }

    public Time getHoraire(){
        return this.h;
    }

    public Time getActualTime(){
        return new Time(h.getHour(), h.getMinute(), h.getSecond());
    }

    @Override
    public String toString() {
        return h.toString();
    }

    public static boolean isWeekend(){
        int day = Clock.getInstance().getHoraire().getDate().getDay();
        return day+1 % 7 == 0 || day % 7 == 0;
    }

    public String showDate() {
        int day = getHoraire().getDate().getDay();
        int month = getHoraire().getDate().getMonth();
        int year = getHoraire().getDate().getYear();

        return String.format("%02d/%02d/%04d", day, month, year);
    }

    public String showTime() {
        int hour = getHoraire().getHour();
        int minute = getHoraire().getMinute();
        int second = getHoraire().getSecond();

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

}
