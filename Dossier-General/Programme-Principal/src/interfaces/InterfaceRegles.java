package interfaces;

import joueur.Joueur;
import moteur.Systeme;

public class InterfaceRegles implements Interface {
	
	public int nbPage; // nombre de pages
	public int indexPage; // index de la page visualis�e
	public Interface InterfaceParent; // Interface depuis laquelle les r�gles on �t�es acc�d�es
	
	
	public void quitter() { // retour � l'interface m�re
		//TODO
	}
	
	public void changerPage(int p) {
		if (p >=0 && p< nbPage) {
			indexPage = p;
		}
	}
}
