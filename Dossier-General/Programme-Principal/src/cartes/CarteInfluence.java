package cartes;

import elements.Colonne;
import javafx.scene.paint.Color;

public abstract class CarteInfluence {

    private String nom;

    private Color couleur;

    private double valeur;

    private boolean estVisible;

    //Constructeur basique
    protected CarteInfluence(Color couleur, String nom, double valeur, boolean estVisible) {
        this.nom = nom;
        this.valeur = valeur;
        this.estVisible = estVisible;
        this.couleur = couleur;
    }

    //Constructeur simplifié
    protected CarteInfluence(Color couleur, String nom, int valeur) {
        this(couleur, nom, valeur, false);
    }

    public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
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
