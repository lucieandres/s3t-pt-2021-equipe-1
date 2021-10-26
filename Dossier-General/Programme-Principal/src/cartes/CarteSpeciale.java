package cartes;

import elements.Colonne;

public abstract class CarteSpeciale extends CarteInfluence {
	
	//Constructeur basique
	protected CarteSpeciale(String nom, double valeur, boolean estVisible, Colonne colonne) {
		super(nom, valeur, estVisible, colonne);
	}
	
	//Constructeur simplifié
	protected CarteSpeciale(String nom, double valeur) {
		super(nom, valeur, false, null);
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
