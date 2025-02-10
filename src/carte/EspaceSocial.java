package carte;

public class EspaceSocial extends Espace{
    private String typeLieu;
    private String reputationLieu;

    public EspaceSocial(Horaire fermeture, Horaire ouverture, int id, String nomEspace, boolean espaceVide,
                        int frequenceEspace, int capacitMin, int capaciteMax, String typeLieu, String reputationLieu) {
        super(fermeture,  ouverture,  id,  nomEspace,  espaceVide,  frequenceEspace,  capacitMin,  capaciteMax);
        this.typeLieu = typeLieu;
        this.reputationLieu = reputationLieu;
    }
}
