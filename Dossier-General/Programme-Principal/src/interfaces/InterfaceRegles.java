package interfaces;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import moteur.Systeme;
import joueur.Joueur;

public class InterfaceRegles extends BorderPane implements UI {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
		
	public InterfaceRegles(GestionnaireInterface gi) { // javaFX elements goes into the class constructor
		super();
		GI = gi;
	 	Button button1 = new Button("Menu"); 
		button1.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(0))); // switch Pane visibility
		
		VBox VBTop = new VBox();
		VBTop.setAlignment(Pos.TOP_RIGHT);
		VBTop.getChildren().addAll(button1);
		this.getChildren().add(VBTop);
		
	
	/*	public int nbPage; // nombre de pages
		public int indexPage; // index de la page visualisée
		public UI InterfaceParent; // Interface depuis laquelle les règles on étées accédées
	
		public InterfaceRegles() {
		super();
		
		Rectangle2D screen = Screen.getPrimary().getBounds();
		
		Button exit = new Button(); // ------------------------------------ exit Button --------------------------------------- //
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

	public void quitter() { // retour à l'interface mère
		//TODO
	}
	
	public void changerPage(int p) {
		if (p >=0 && p< nbPage) {
			indexPage = p;
		} */
	}
}
