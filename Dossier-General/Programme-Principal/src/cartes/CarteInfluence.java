package cartes;

import elements.Colonne;
import joueur.Couleur;

public abstract class CarteInfluence {

    private String nom;

    private Couleur couleur;

    private double valeur;

    private boolean estVisible;

    //Constructeur basique
    protected CarteInfluence(Couleur couleur, String nom, double valeur, boolean estVisible) {
        this.nom = nom;
        this.valeur = valeur;
        this.estVisible = estVisible;
        this.couleur = couleur;
    }

    //Constructeur simplifié
    protected CarteInfluence(Couleur couleur, String nom, int valeur) {
        this(couleur, nom, valeur, false);
    }

    public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
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

    public void Reveler() {
    	this.estVisible = true;
    }
}
