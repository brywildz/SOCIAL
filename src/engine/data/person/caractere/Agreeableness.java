package engine.data.person.caractere;

public class Agreeableness extends PersonalityTrait {
    private int niveau;

    public Agreeableness(int niveau) {
        super(niveau);
    }

    public int getLevel() {
        return niveau;
    }
}
