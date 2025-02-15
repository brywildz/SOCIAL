package engine.data.evenement;
import engine.data.individu.Individu;

public class EventPersonnel extends Evenement {
    private String impactPersonnel;
    private Individu individu;
    private int duree;
    private String nom;
    private String description;


    public EventPersonnel(String nom,String description, int duree, Individu individu) {
        super(nom, description, duree);
        this.impactPersonnel = impactPersonnel;
    }
    public Individu getIndividu() {
        return individu;
    }
    public String afficherEtat(){
        System.out.println("L'évenement en cours :"+nom+",description:"+description+"sont impact cette personne :"+individu+"la durée global de l'event"+duree);
        return "";
    }
    public boolean endEvent(){
        return false;
    }

    @Override
    public void afficherEffet() {

    }

}


