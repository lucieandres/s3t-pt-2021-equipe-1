package cartes;


public class CarteObjectif {

    private String domaine;

    private int valeur;
    
    
    //Constructeur basique
    public CarteObjectif(String domaine, int valeur) {
        this.domaine = domaine;
        this.valeur = valeur;
    }
    
    public String getDomaine() {
        return this.domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public int getValeur() {
        return this.valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
}
