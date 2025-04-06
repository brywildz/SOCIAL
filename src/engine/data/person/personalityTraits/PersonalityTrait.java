package engine.data.person.personalityTraits;

public abstract class PersonalityTrait {
    private final int level;
    private String nom;

    public PersonalityTrait(int level, String nom) {
        this.level = level;
        this.nom = nom;
    }

    public int getLevel() {
        return level;
    }

    public boolean isHigh(){
        return level > 6;
    }

    public boolean isLow(){
        return level < 4;
    }

    public boolean isMid(){
        return level <= 6 && level >= 4;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
