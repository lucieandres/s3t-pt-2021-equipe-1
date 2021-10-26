package interfaces;

import javafx.scene.layout.Pane;
import joueur.Joueur;
import moteur.Systeme;

public class InterfaceRegles extends Pane implements UI {
	
	public int nbPage; // nombre de pages
	public int indexPage; // index de la page visualisée
	public UI InterfaceParent; // Interface depuis laquelle les règles on étées accédées
	
	
	public void quitter() { // retour à l'interface mère
		//TODO
	}
	
	public void changerPage(int p) {
		if (p >=0 && p< nbPage) {
			indexPage = p;
		}
	}
}
