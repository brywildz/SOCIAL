package engine.data.event;

import engine.data.map.Time;

public class Action {
    private String id;
    private Time start;
    private Time end;
    private final boolean isOutside;

    public Action(String id, boolean isOutside, Time start, Time end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.isOutside = isOutside;
    }

    public Action(Action action) {
        this.id = action.getId();
        this.start = action.getStart();
        this.end = action.getEnd();
        this.isOutside = action.isOutside();
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


}
