package engine.data.person.vitality;

import engine.data.map.Time;

/**
 * Classe de donnée stockant les différente information liée au sommeil d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Sleep extends Vitality {
    private Boolean isSleeping;
    Time SleepTime = new Time(0,0,0);
    Time WakeUpTime = new Time(0,0,0);

    public Sleep(int niveau, Boolean isSleeping) {
        super(niveau);
        this.isSleeping = isSleeping;
    }

    public Boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(Boolean sleeping) {
        isSleeping = sleeping;
    }

    public Boolean getSleeping() {
        return isSleeping;
    }

    public Time getSleepTime() {
        return SleepTime;
    }

    public Time getWakeUpTime() {
        return WakeUpTime;
    }

    public void setSleepTime(Time sleepTime) {
        SleepTime = sleepTime;
    }

    public void setWakeUpTime(Time wakeUpTime) {
        WakeUpTime = wakeUpTime;
    }
}
