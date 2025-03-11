package engine.data.person.socialState;

import engine.data.map.Infrastructure;

public abstract class SocialState {
    private Infrastructure infrastructure;

    public SocialState(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }
}
