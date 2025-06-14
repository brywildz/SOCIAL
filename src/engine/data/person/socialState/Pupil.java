package engine.data.person.socialState;

import engine.data.map.Infrastructure;
import engine.data.map.Time;

public class Pupil extends SocialState{
    private Time startTime;
    private Time endTime;

    public Pupil(Infrastructure infrastructure, Time startTime, Time endTime) {
        super(infrastructure);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Etudiant";
    }
}
