package cartes;

import elements.Colonne;
import joueur.Couleur;

public abstract class CarteDouble extends CarteInfluence {

	private double valeurSp�ciale; // Valeur sp�ciale utilis�e lorsque que la carte est plac�e dans la colonne d'une carte objectif ayant le meme domaine

	private String domaine;

	
	// Constructeur basique
	protected CarteDouble(Couleur couleur, String nom, double valeur, boolean visible, Colonne colonne, double valeurSp�ciale, String domaine) {
		super(couleur, nom, valeur, visible, colonne);
		this.valeurSp�ciale = valeurSp�ciale;
		this.domaine = domaine;
	}

	// Constructeur simplifi�
	protected CarteDouble(Couleur couleur, String nom, double valeur, double valeurSp�ciale, String domaine) {
		super(couleur, nom, valeur, false, null);
		this.valeurSp�ciale = valeurSp�ciale;
		this.domaine = domaine;
	}
	
	// Constructeur super simplifi�
	protected CarteDouble(Couleur couleur, String nom, String domaine) {
		super(couleur, nom, 8, false, null);
		this.valeurSp�ciale = 12;
		this.domaine = domaine;
	}

	public double getValeurSp�() {
		return valeurSp�ciale;
	}

	public void setValeurSp�(double valeurSp�ciale) {
		this.valeurSp�ciale = valeurSp�ciale;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	
	
	//Op�ration
	
	public double ValeurUtilisee(CarteObjectif objectif) {
		if (this.domaine == objectif.getDomaine()) {
			return this.valeurSp�ciale;
		} 
		else {
			return this.getValeur();
		}
	}

}
