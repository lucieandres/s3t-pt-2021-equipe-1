package cartes;


/**
* @generated
*/
public class CarteInfluence {
    
    /**
    * @generated
    */
    private String nom;
    
    /**
    * @generated
    */
    private String capacite_speciale;
    
    /**
    * @generated
    */
    private int valeur;
    
    /**
    * @generated
    */
    private boolean visible;
    
    
    //Constructor
    public CarteInfluence(String nom, String capacite_speciale, int valeur, boolean visible) {
        this.nom = nom;
        this.capacite_speciale = capacite_speciale;
        this.valeur = valeur;
        this.visible = visible;
        
    }
    
    //Constructor
    public CarteInfluence(String nom, String capacite_speciale, int valeur) {
        this(nom,capacite_speciale, valeur, false);
    }
    
    /**
    * @generated
    */
    public String getNom() {
        return this.nom;
    }
    
    /**
    * @generated
    */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
    * @generated
    */
    public String getCapacite_speciale() {
        return this.capacite_speciale;
    }
    
    /**
    * @generated
    */
    public void setCapacite_speciale(String capacite_speciale) {
        this.capacite_speciale = capacite_speciale;
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
    public boolean getVisible() {
        return this.visible;
    }
    
    /**
    * @generated
    */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
}
