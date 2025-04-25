package engine.data.event;

import engine.data.map.Clock;
import engine.data.map.Infrastructure;
import engine.data.map.Time;

public class Hobby {
    private String id;
    private Time start;
    private Time end;
    private boolean isOutside;
    private Infrastructure place;

    public Hobby(String id, boolean isOutside, Time start, Time end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.isOutside = isOutside;
    }

    public Hobby(Hobby hobby) {
        this.id = hobby.getId();
        this.start = hobby.getStart();
        this.end = hobby.getEnd();
        this.isOutside = hobby.isOutside();
    }

    public Hobby() {
    }

    public String getId() {
        return id;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public boolean isOutside() {
        return isOutside;
    }

    public boolean isFinished() {
        Time c = Clock.getInstance().getTime();
        return c.isHigherThan(end);
    }

    public void setOutside(boolean isOutside) {
        this.isOutside = isOutside;
    }

    public void setPlace(Infrastructure place) {
        this.place = place;
    }

    public Infrastructure getPlace() {
        return place;
    }

    public boolean hasStart() {
        Time time = Clock.getInstance().getTime();
        return time.equals(start);
    }
}
