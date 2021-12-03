package cartes;

import elements.Plateau;
import javafx.scene.paint.Color;

public abstract class CarteSpeciale extends CarteInfluence {
	
	//Constructeur basique
	protected CarteSpeciale(Color couleur, String nom, double valeur, boolean estVisible) {
		super(couleur, nom, valeur, estVisible);
	}
	
	//Constructeur simplifi�
	protected CarteSpeciale(Color couleur, String nom, double valeur) {
		super(couleur, nom, valeur, false);
	}
	
	//Operations
	
	public abstract void Activer(Plateau plateau) throws Exception;
	 
	// A revoir
//	public void Reveler() {
//		this.setEstVisible(true);
//		if(!this.getColonne().getCarteObjectif().getEstRealise()) {
//			  this.Activer();
//		}
//	}
	
}
