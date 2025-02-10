package carte;

public class EspaceVital extends Espace{
    private String typeInfrastrucutre;
    private String utile;

    public EspaceVital(Horaire fermeture, Horaire ouverture, int id, String nomEspace, boolean espaceVide,
                       int frequenceEspace, int capacitMin, int capaciteMax, String typeInfrastrucutre, String utile) {
        super(fermeture, ouverture, id, nomEspace, espaceVide, frequenceEspace, capacitMin, capaciteMax);
        this.typeInfrastrucutre = typeInfrastrucutre;
        this.utile = utile;
    }
}
