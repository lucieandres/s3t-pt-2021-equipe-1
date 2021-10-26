package cartes;

import elements.Colonne;

public abstract class CarteInfluence {

    private String nom;

    private double valeur;
    
    private boolean estVisible;
    
    private Colonne colonne;
    
    //Constructeur basique
    protected CarteInfluence(String nom, double valeur, boolean estVisible, Colonne colonne) {
        this.nom = nom;
        this.valeur = valeur;
        this.estVisible = estVisible;
        this.colonne = colonne;
    }
    
    //Constructeur simplifié 
    protected CarteInfluence(String nom, int valeur) {
        this(nom, valeur, false, null);
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getValeur() {
        return this.valeur;
    }
    
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    
    public boolean getEstVisible() {
        return this.estVisible;
    }

    public void setEstVisible(boolean estVisible) {
        this.estVisible = estVisible;
    }
    
	public Colonne getColonne() {
		return colonne;
	}

	public void setColonne(Colonne colonne) {
		this.colonne = colonne;
	}
    
    
    //Opération 
    
    public void Placer(Colonne colonne) {
    	this.colonne = colonne;
    	colonne.getCartesInfluences().add(this);
    	if (this.colonne.getCartesInfluences().size() == this.colonne.getCarteObjectif().getValeur()) {
    		this.colonne.getCarteObjectif().Realiser();
    	}
    	if (this.getColonne().getCartesInfluences().size()>2) {
    		this.getColonne().getCartesInfluences().get(this.getColonne().getCartesInfluences().size()-2).Reveler();
    	}
    	//TODO cas d'erreur si la colonne est déja réalisée 
    }
    
    public void Reveler() {
    	this.estVisible = true;
    }
}
