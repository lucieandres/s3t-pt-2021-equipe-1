package interfaces;

import javafx.scene.layout.Pane;
import joueur.Joueur;
import moteur.Systeme;

public class InterfaceRegles extends Pane implements UI {
	
	public int nbPage; // nombre de pages
	public int indexPage; // index de la page visualis�e
	public UI InterfaceParent; // Interface depuis laquelle les r�gles on �t�es acc�d�es
	
	
	public void quitter() { // retour � l'interface m�re
		//TODO
	}
	
	public void changerPage(int p) {
		if (p >=0 && p< nbPage) {
			indexPage = p;
		}
	}
}
