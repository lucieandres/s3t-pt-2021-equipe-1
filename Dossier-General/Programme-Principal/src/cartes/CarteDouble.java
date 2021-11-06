package cartes;

import elements.Colonne;
import joueur.Couleur;

public abstract class CarteDouble extends CarteInfluence {

	private double valeurspeciale; // Valeur sp�ciale utilis�e lorsque que la carte est plac�e dans la colonne d'une carte objectif ayant le meme domaine

	private String domaine;


	// Constructeur basique
	protected CarteDouble(Couleur couleur, String nom, double valeur, boolean visible, double valeurspeciale, String domaine) {
		super(couleur, nom, valeur, visible);
		this.valeurspeciale = valeurspeciale;
		this.domaine = domaine;
	}

	// Constructeur simplifi�
	protected CarteDouble(Couleur couleur, String nom, double valeur, double valeurspeciale, String domaine) {
		super(couleur, nom, valeur, false);
		this.valeurspeciale = valeurspeciale;
		this.domaine = domaine;
	}

	// Constructeur super simplifi�
	protected CarteDouble(Couleur couleur, String nom, String domaine) {
		super(couleur, nom, 8, false);
		this.valeurspeciale = 12;
		this.domaine = domaine;
	}

	public double getValeurSpe() {
		return valeurspeciale;
	}

	public void setvaleurspe(double valeurspeciale) {
		this.valeurspeciale = valeurspeciale;
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
			return this.valeurspeciale;
		}
		else {
			return this.getValeur();
		}
	}

}
