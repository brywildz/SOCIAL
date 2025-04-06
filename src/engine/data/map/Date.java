package engine.data.map;

public class Date {
    private int year;
    private int month;
    private int day;
    private String dayName;
    private int indexWeek;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.indexWeek = 3;
        dayName = daysNameList[indexWeek];
    }

    public void newDay(){
        indexWeek++;
        if(indexWeek>=7){
            indexWeek=0;
        }
        dayName = daysNameList[indexWeek];
        if(day==30){
            day=1;
            newMonth();
        }
        else{
            day++;
        }
    }

    private void newMonth() {
        if(month==12){
            month=1;
            newYear();
        }
        else{
            month++;
        }
    }

    private void newYear() {
        year++;
    }

    public String toString(){
        return String.format("%d/%d/%d", year, month, day);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDayName() {
        return dayName;
    }

    public static String[] daysNameList ={"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
}
