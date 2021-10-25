package cartes;

import java.util.ArrayList;

import elements.Colonne;

public class CarteInfluence {

    private String nom;

    private int valeur;
    
    private boolean estVisible;
    
    private Colonne colonne;
    
    //Constructeur basique
    public CarteInfluence(String nom, int valeur, boolean estVisible, Colonne colonne) {
        this.nom = nom;
        this.valeur = valeur;
        this.estVisible = estVisible;
        this.colonne = colonne;
    }
    
    //Constructeur simplifié 
    public CarteInfluence(String nom, int valeur) {
        this(nom, valeur, false, null);
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getValeur() {
        return this.valeur;
    }
    
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
    boolean getEstVisible() {
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
    	
    	//Cas d'erreur a ajouter si la colonne est déja réalisée 
    }
    
    public void Tourner() {
    	this.estVisible = true;
    }
}
