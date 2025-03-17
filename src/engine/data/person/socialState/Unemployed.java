package engine.data.person.socialState;

import engine.data.map.Infrastructure;

public class Unemployed extends SocialState{


    public Unemployed(Infrastructure infrastructure) {
        super(infrastructure);
    }

    @Override
    public String toString() {
        return "Chomeur";
    }
}
