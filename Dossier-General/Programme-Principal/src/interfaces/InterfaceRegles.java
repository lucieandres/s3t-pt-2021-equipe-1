package interfaces;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import joueur.Joueur;
import moteur.Systeme;

public class InterfaceRegles extends BorderPane implements UI {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
		
	public InterfaceRegles(GestionnaireInterface gi) { // javaFX elements goes into the class constructor
		super();
		GI = gi;
	 	Button button1 = new Button("Ta grand mere"); 
		button1.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(0))); // switch Pane visibility
		
		VBox VBTop = new VBox();
		VBTop.setAlignment(Pos.TOP_RIGHT);
		VBTop.getChildren().addAll(button1);
		this.getChildren().add(VBTop);
		
	}
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
