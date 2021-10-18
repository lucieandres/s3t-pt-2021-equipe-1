package cartes;


/**
* @generated
*/
public class CarteObjectif {
    
    /**
    * @generated
    */
    private String domaine;
    
    /**
    * @generated
    */
    private int valeur;
    
    /**
    * @generated
    */
    private boolean estRealise;
    
    
    //Constructor
    public CarteObjectif(String domaine, int valeur) {
        this(domaine, valeur, false);
    }
    
    //Constructor
    public CarteObjectif(String domaine, int valeur, boolean estRealise) {
        this.domaine = domaine;
        this.valeur = valeur;
        this.estRealise = estRealise; 
    }
    
    /**
    * @generated
    */
    public String getDomaine() {
        return this.domaine;
    }
    
    /**
    * @generated
    */
    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
    
    /**
    * @generated
    */
    public int getValeur() {
        return this.valeur;
    }
    
    /**
    * @generated
    */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
    /**
    * @generated
    */
    public boolean getEstRealise() {
        return this.estRealise;
    }
    
    /**
    * @generated
    */
    public void setEstRealise(boolean estRealise) {
        this.estRealise = estRealise;
    }
    
}
