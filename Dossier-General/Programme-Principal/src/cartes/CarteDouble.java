package cartes;

import elements.Colonne;

public abstract class CarteDouble extends CarteInfluence {

	private double valeurSpéciale; // Valeur spéciale utilisée lorsque que la carte est placée dans la colonne d'une carte objectif ayant le meme domaine

	private String domaine;

	
	// Constructeur basique
	protected CarteDouble(String nom, double valeur, boolean visible, Colonne colonne, double valeurSpéciale, String domaine) {
		super(nom, valeur, visible, colonne);
		this.valeurSpéciale = valeurSpéciale;
		this.domaine = domaine;
	}

	// Constructeur simplifié
	protected CarteDouble(String nom, double valeur, double valeurSpéciale, String domaine) {
		super(nom, valeur, false, null);
		this.valeurSpéciale = valeurSpéciale;
		this.domaine = domaine;
	}
	
	// Constructeur super simplifié
	protected CarteDouble(String nom, String domaine) {
		super(nom, 8, false, null);
		this.valeurSpéciale = 12;
		this.domaine = domaine;
	}

	public double getValeurSpé() {
		return valeurSpéciale;
	}

	public void setValeurSpé(double valeurSpéciale) {
		this.valeurSpéciale = valeurSpéciale;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	
	
	//Opération
	
	public double ValeurUtilisee(CarteObjectif objectif) {
		if (this.domaine == objectif.getDomaine()) {
			return this.valeurSpéciale;
		} 
		else {
			return this.getValeur();
		}
	}

}
