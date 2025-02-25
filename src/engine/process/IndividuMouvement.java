package engine.process;

import engine.data.carte.Horloge;
import engine.data.individu.Individu;

public class IndividuMouvement {
    Individu individu;

    public IndividuMouvement (Individu individu) {
        this.individu = individu;
    }

    public void move(){
        //si l'action précedente est terminé une nouvelle doit être initié avec cette classe
        if(individu.getCurrentEvent().getDuree().equals(Horloge.getInstance().getHoraire()));
        //mettre une methode getHoraire dans event et crée un equals
    }
}
