package interfaces;

import joueur.Joueur;
import moteur.Systeme;

public class InterfaceRegles implements Interface {
	
	public int nbPage; // nombre de pages
	public int indexPage; // index de la page visualisée
	public Interface InterfaceParent; // Interface depuis laquelle les règles on étées accédées
	
	
	public void quitter() { // retour à l'interface mère
		//TODO
	}
	
	public void changerPage(int p) {
		if (p >=0 && p< nbPage) {
			indexPage = p;
		}
	}
}
