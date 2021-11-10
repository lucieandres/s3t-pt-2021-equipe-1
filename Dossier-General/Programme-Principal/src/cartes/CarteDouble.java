package cartes;

import elements.Colonne;
import javafx.scene.paint.Color;

public abstract class CarteDouble extends CarteInfluence {

	private double valeurSpeciale; // Valeur sp�ciale utilis�e lorsque que la carte est plac�e dans la colonne d'une carte objectif ayant le meme domaine

	private String domaine;


	// Constructeur basique
	protected CarteDouble(Color couleur, String nom, double valeur, boolean visible, double valeurSpeciale, String domaine) {
		super(couleur, nom, valeur, visible);
		this.valeurSpeciale = valeurSpeciale;
		this.domaine = domaine;
	}

	// Constructeur simplifi�
	protected CarteDouble(Color couleur, String nom, double valeur, double valeurSpeciale, String domaine) {
		super(couleur, nom, valeur, false);
		this.valeurSpeciale = valeurSpeciale;
		this.domaine = domaine;
	}

	// Constructeur super simplifi�
	protected CarteDouble(Color couleur, String nom, String domaine) {
		super(couleur, nom, 8, false);
		this.valeurSpeciale = 12;
		this.domaine = domaine;
	}

	public double getValeurSpeciale() {
		return valeurSpeciale;
	}

	public void setValeurSpeciale(double valeurSpeciale) {
		this.valeurSpeciale = valeurSpeciale;
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
			return this.valeurSpeciale;
		}
		else {
			return this.getValeur();
		}
	}

}
