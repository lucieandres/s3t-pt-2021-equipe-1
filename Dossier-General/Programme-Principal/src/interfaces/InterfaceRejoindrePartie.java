package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import joueur.Joueur;
import moteur.Data;

/**
 * Cette classe permet de rejoindre une partie.
 * C'est sur cette interface qu'un joueur peut rejoindre en tant qu'invité un salon qui a été au préalable créé par InterfaceCreerPartie.java par un autre joueur.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceRejoindrePartie extends BorderPane implements UI {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	Label titre;
	Button boutonRetour;
	Button boutonJouer;
	TextField pseudo;
	TextField code;
	
	public InterfaceRejoindrePartie(GestionnaireInterface gi) {
		super();
		GI = gi;
		
		//Creation d'une bordure
		Border maBordure = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(10), new Insets(10)));
		
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
//-----------------Créations des composants------------------------------------------------        
       
        //Création du titre
      	Label Titre = new Label("Rejoindre Partie");
      	Titre.setFont(Font.font("Pristina", FontWeight.BOLD,120));
      	Titre.setPadding(new Insets(20, 20, 0, 0));
      	
      	// Bouton retour
     	boutonRetour = new Button("Retour");
     	boutonRetour.setOnAction(e -> { GI.afficherEcran(GI.InterfaceMap.get("menu")); });
     	boutonRetour.setPrefSize(150,50);
     	boutonRetour.setFont(Font.font("Comic sans MS", FontWeight.MEDIUM, 20));
     	
     	
//------------------Créations des conteneurs------------------------------------------------
     	
     	//Création de la HBox pour tout accueillir (Sauf le bouton retour)
		HBox HBhaut = new HBox();
		HBhaut.setAlignment(Pos.TOP_CENTER);
		HBhaut.setPrefSize(1520, 1080);
		HBhaut.setPadding(new Insets(0,0,0,400));
		HBhaut.getChildren().addAll(Titre);
        this.setCenter(HBhaut);
        
		//Création de la Vbox pour accueillir le bouton retour
		VBox VBDroite = new VBox();
		VBDroite.setAlignment(Pos.TOP_RIGHT);
		VBDroite.setPrefSize(400,0);
		VBDroite.setPadding(new Insets(50,50,0,0));
		VBDroite.getChildren().addAll(boutonRetour);
		this.setRight(VBDroite);
		
		
		
	}
}
