package engine.data.person.personalityTraits;

public class Conscientiousness extends PersonalityTrait {
    private int niveau;

    public Conscientiousness(int niveau) {
        super(niveau);
    }

    public int getLevel() {
        return niveau;
    }
}
