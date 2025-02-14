package engine.process;

import engine.data.individu.Individu;

public interface MouvementIndividu {
    public void naturalEvent();

    public Individu getIndividu();

    void set(Individu individu);

}
