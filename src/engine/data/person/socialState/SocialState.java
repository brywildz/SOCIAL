package engine.data.person.socialState;

import engine.data.map.Infrastructure;

public abstract class SocialState {
    private Infrastructure infrastructure;
    private boolean isTodayOff = false;

    public SocialState(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public void setIsTodayOff(boolean todayOff) {
        this.isTodayOff = todayOff;
    }

    public boolean getIsTodayOff() {
        return isTodayOff;
    }
}
