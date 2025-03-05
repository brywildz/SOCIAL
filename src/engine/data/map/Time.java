package engine.data.map;

/**
 * Classe de donnée stockant les informations liée à l'horaire , instanciable dans les où l'on manipule des heures
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Time {
    private int hour;
    private int minute;
    private int second;
    private Date date;

    public Time(int hour, int second, int minute, Date date) {
        this.hour = hour;
        this.second = second;
        this.minute = minute;
        this.date = date;
    }

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void newSecond(){
        if(second ==59){
            second =0;
            newMinute();
        }
        else{
            second++;
        }
    }

    private void newMinute() {
        if(minute==59){
            minute=0;
            newHour();
        }
        else{
            minute++;
        }
    }

    private void newHour() {
        if(hour ==24){
            hour =0;
            newDay();
        }
        else{
            hour++;
        }
    }

    private void newDay() {
        date.newDay();
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj){
        Time h = (Time) obj;
        return this.hour == h.hour && this.minute == h.minute && this.second == h.second;
    }

    public boolean isHigherThan(Time other){
        return this.hour >= other.getHour() && this.minute >= other.getMinute() && this.second >= other.getSecond();
    }

    @Override
    public String toString() {
        return "Date : "+date.toString()+"\nHeure : "+String.format("%d:%d:%d", hour, minute, second);
    }
}
