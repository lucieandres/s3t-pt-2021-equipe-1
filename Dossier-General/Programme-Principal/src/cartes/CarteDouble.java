package cartes;

import elements.Colonne;

public class CarteDouble extends CarteInfluence {

	private int valeurSpéciale; // Valeur spéciale utilisée lorsque que la carte est placée dans la colonne d'une carte objectif ayant le meme domaine

	private String domaine;

	
	// Constructeur basique
	public CarteDouble(String nom, int valeur, boolean visible, Colonne colonne, int valeurSpéciale, String domaine) {
		super(nom, valeur, visible, colonne);
		this.valeurSpéciale = valeurSpéciale;
		this.domaine = domaine;
	}

	// Constructeur simplifié
	public CarteDouble(String nom, int valeur, int valeurSpéciale, String domaine) {
		super(nom, valeur, false, null);
		this.valeurSpéciale = valeurSpéciale;
		this.domaine = domaine;
	}

	public int getValeurSpé() {
		return valeurSpéciale;
	}

	public void setValeurSpé(int valeurSpéciale) {
		this.valeurSpéciale = valeurSpéciale;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	
	
	//Opération
	
	public int ValeurUtilisee(CarteObjectif objectif) {
		if (this.domaine == objectif.getDomaine()) {
			return this.valeurSpéciale;
		} 
		else {
			return this.getValeur();
		}
	}

}
