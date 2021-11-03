package interfaces;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import moteur.Systeme;
import joueur.Joueur;

public class InterfaceRegles extends Pane implements UI {
	
	public InterfaceRegles() {
		super();
	}

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
