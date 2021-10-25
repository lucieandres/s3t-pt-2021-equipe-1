package cartes;


public class CarteObjectif {

    private String domaine;

    private int valeur;

    private boolean estRealise;
    
    
    //Constructeur basique
    public CarteObjectif(String domaine, int valeur, boolean estRealise) {
        this.domaine = domaine;
        this.valeur = valeur;
        this.estRealise = estRealise; 
    }
    
    //Constructeur simplifié 
    public CarteObjectif(String domaine, int valeur) {
        this(domaine, valeur, false);
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
    
    public boolean getEstRealise() {
        return this.estRealise;
    }

    public void setEstRealise(boolean estRealise) {
        this.estRealise = estRealise;
    }
    
    
    //Operation
    
    public void Realiser() {
    	this.estRealise = true;
    }
    
}
