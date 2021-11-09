package cartes;

import elements.Colonne;
import joueur.Couleur;

public abstract class CarteSpeciale extends CarteInfluence {
	
	//Constructeur basique
	protected CarteSpeciale(Couleur couleur, String nom, double valeur, boolean estVisible, Colonne colonne) {
		super(couleur, nom, valeur, estVisible, colonne);
	}
	
	//Constructeur simplifié
	protected CarteSpeciale(Couleur couleur, String nom, double valeur) {
		super(couleur, nom, valeur, false, null);
	}
	
	//Operations
	
	public abstract void Activer();
	 
	public void Reveler() {
		this.setEstVisible(true);
		if(!this.getColonne().getCarteObjectif().getEstRealise()) {
			  this.Activer();
		}
	}
	
}
