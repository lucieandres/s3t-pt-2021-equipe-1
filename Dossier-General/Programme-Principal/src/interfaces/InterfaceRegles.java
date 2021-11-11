package interfaces;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import joueur.Joueur;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.scene.layout.Pane;
//import moteur.Systeme;
import joueur.Joueur;

public class InterfaceRegles extends VBox implements UI {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	public int parentID = 0;
	
	public int nbPage; // nombre de pages
	public int indexPage; // index de la page visualis�e
	public UI InterfaceParent; // Interface depuis laquelle les r�gles on �t�es acc�d�es
	
	public InterfaceRegles(GestionnaireInterface gi) {
		super();
		GI = gi;
		/*
		Button buttonBack = new Button("Menu"); 
		buttonBack.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(0))); // switch Pane visibility
		VBox VBTop = new VBox();
		VBTop.getChildren().addAll(buttonBack);
		this.getChildren().add(VBTop);*/
		
		Rectangle2D screen = Screen.getPrimary().getBounds();
		
		Button exit = new Button("Retour");// ------------------------------------ exit Button --------------------------------------- //
		exit.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		this.setAlignment(Pos.CENTER_RIGHT); //for the exit button
		exit.setMinSize(100, 100);
		
		HBox HB = new HBox();
		HB.setAlignment(Pos.CENTER);
		
		Button BoutonGauche = new Button(); // ----------------------------- Bouton Gauche ------------------------------------ //
		BoutonGauche.setMinSize(screen.getWidth()/8, screen.getHeight()-200);
		BoutonGauche.setOnAction(e -> changerPage(indexPage-1));   
		
		VBox content = new VBox(); // --------------------------------------- contenu ----------------------------------------- //
		content.setMinSize(screen.getWidth()-screen.getWidth()/4, screen.getHeight());
		
		Button BoutonDroite = new Button(); // ----------------------------- Bouton Droite ------------------------------------ //
		BoutonDroite.setMinSize(screen.getWidth()/8, screen.getHeight()-200);
		BoutonGauche.setOnAction(e -> changerPage(indexPage+1)); 
		
		
		HB.getChildren().addAll(BoutonGauche, content, BoutonDroite);
		this.getChildren().addAll(exit,HB);
	}

	public void quitter() { // retour � l'interface m�re
		//TODO
	}
	
	public void changerPage(int p) {
		if (p >=0 && p< nbPage) {
			indexPage = p;
		} 
	}
	
	public void setParentID(int id) {
		parentID = id;
	}
}
