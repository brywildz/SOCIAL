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

    public Time getTime(){
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
        String day = Clock.getInstance().getTime().getDate().getDayName();
        return day.equals("Samedi") || day.equals("Dimanche");
    }

    public String showDate() {
        int day = getTime().getDate().getDay();
        int month = getTime().getDate().getMonth();
        int year = getTime().getDate().getYear();

        return String.format("%02d/%02d/%04d", day, month, year);
    }

    public String showTime() {
        int hour = getTime().getHour();
        int minute = getTime().getMinute();
        int second = getTime().getSecond();

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public String showDayName(){
        return getTime().getDate().getDayName();
    }

}
