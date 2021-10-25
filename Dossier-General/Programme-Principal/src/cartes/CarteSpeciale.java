package cartes;

import elements.Colonne;

public abstract class CarteSpeciale extends CarteInfluence {
	
	//Constructeur basique
	protected CarteSpeciale(String nom, int valeur, boolean estVisible, Colonne colonne) {
		super(nom, valeur, estVisible, colonne);
	}
	
	//Constructeur simplifié
	protected CarteSpeciale(String nom, int valeur) {
		super(nom, valeur, false, null);
	}
	
	//Operations
	
	public abstract void Activer();
	 
	public void Tourner() {
		  this.setEstVisible(true);
		  this.Activer();
		  
		  //TODO si la manche est finie, pas de capacité speciale
	}
	
}
