package engine.data.person.personalityTraits;

public abstract class PersonalityTrait {
    private final int level;

    public PersonalityTrait(int level) {
        this.level = level;
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
}
