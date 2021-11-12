package interfaces;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 * Cette classe est un interface qui permet de voir les règles.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */


public class InterfaceRegles extends VBox implements UI {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	public int parentID = 0;
	
	public int nbPage; // nombre de pages
	public int indexPage; // index de la page visualiser
	public UI InterfaceParent; // Interface depuis laquelle les r�gles on �t�es acc�d�es
	
	/**
     *  Ce constructeur permet de creer tous les elements de l'interface, c'est-a-dire les deux boutons pour defiler les pages, 
     *  le bouton retour pour retourner au menu ou en jeu, le texte et des images si necessaire.
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble
     * 
     * @since 1.0
     */
	
	public InterfaceRegles(GestionnaireInterface gi) {
		super();
		GI = gi;
		/*
		Button buttonBack = new Button("Menu"); 
		buttonBack.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(0))); // switch Pane visibility
		VBox VBTop = new VBox();
		VBTop.getChildren().addAll(buttonBack);
		this.getChildren().add(VBTop);
		*/
		
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
		Rectangle2D screen = Screen.getPrimary().getBounds();
		
		Button exit = new Button("Retour"); // ------------------------------------ exit Button --------------------------------------- //
		exit.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		this.setAlignment(Pos.CENTER_RIGHT); //for the exit button
		exit.setMinSize(100, 100);
		
		HBox HB = new HBox();
		HB.setAlignment(Pos.CENTER);
		
		Button BoutonGauche = new Button(); // ----------------------------- Bouton Gauche ------------------------------------ //
		BoutonGauche.setMinSize(screen.getWidth()/8, screen.getHeight()-200);
		BoutonGauche.setOnAction(e -> changerPage(indexPage-1));   
		
		VBox content = new VBox(); // --------------------------------------- contenu ----------------------------------------- //
		
		content.setAlignment(Pos.CENTER);
		content.setMinSize(screen.getWidth()-screen.getWidth()/4, screen.getHeight());
		Label text = new Label();
		text.setWrapText(true);
		text.setText("Le jeu de Cape et d'Epée se joue entre 2 et 6 joueurs. SI vous n'êtes pas assez nombreux, il est possible d'ajouter des Bots.\r\n"
				+ "La partie peut commencer !\r\n"
				+ "Pour commencer à jouer, le premier joueur sélectionne une carte Influence dans sa main, et la dépose dans une colonne, sous une carte Objectif ou une autre carte Influence.\r\n"
				+ "Une carte Objectif est réalisée lorsque il y autant ou plus de carte Influence sous cette carte que la valeur de la carte.\r\n"
				+ "Une manche est finie lorsque les cartes Objectifs de toutes les colonnes sont réalissées.\r\n"
				+ "Une partie est finie à la fin de la 6e manche.");
		content.getChildren().add(text);
		
		Button BoutonDroite = new Button(); // ----------------------------- Bouton Droite ------------------------------------ //
		BoutonDroite.setMinSize(screen.getWidth()/8, screen.getHeight()-200);
		BoutonGauche.setOnAction(e -> changerPage(indexPage+1)); 
		
		
		HB.getChildren().addAll(BoutonGauche, content, BoutonDroite);
		this.getChildren().addAll(exit,HB);
	}


	/**
     * Cette methode permet de changer de page.
     * 
     * 
     * @param p Numero de la page.
     * 
     * @since 1.0
     */
	
	public void changerPage(int p) {
		if (p >=0 && p< nbPage) {
			indexPage = p;
		} 
	}
	
	/**
     * Cette methode est le setter de ParentID.
     * 
     * 
     * @param id Identifiant.
     * 
     * @since 1.0
     */
	
	public void setParentID(int id) {
		parentID = id;
	}
	
//	public void quitter() { // retour � l'interface m�re
//	//TODO
//	}
}
