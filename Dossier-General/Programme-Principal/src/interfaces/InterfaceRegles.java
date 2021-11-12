package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Cette classe est une interface qui représente les paramètres.
 * 
 *   
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceRegles extends BorderPane implements UI {
    
//    private Joueur joueur;
    public GestionnaireInterface GI;
    
	/**
     *  Ce constructeur permet de creer tous les élements de l'interface, c'est-a-dire le titre "Paramètres", le bouton "retour", 
     *  le bouton "Paramètre Graphiques", le bouton "Paramètre Musicaux", le bouton "Paramètres Sonores", le bouton "Theme", le bouton "Langue".
     *  
     * 
     * @param gi le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble
     * 
     * @since 1.0
     */
    public InterfaceRegles(GestionnaireInterface gi) { 
		super();
		GI = gi;
		
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
		HBox HBTop = new HBox();
		VBox VBRight = new VBox();
		VBox VBLeft = new VBox();
		
		Button buttonBack = new Button("Retour");
		buttonBack.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		buttonBack.setFont(Font.font("Comic Sans MS", 20));
		buttonBack.setPrefWidth(150);
		
		VBox content = new VBox(); // --------------------------------------- contenu ----------------------------------------- //
		content.setAlignment(Pos.CENTER);
		Label text = new Label();
		text.setWrapText(true);
		text.setText("Le jeu de Cape et d'Epée se joue entre 2 et 6 joueurs. SI vous n'êtes pas assez nombreux, il est possible d'ajouter des Bots.\r\n\n"
				+ "La partie peut commencer !\r\n\n"
				+ "Pour commencer à jouer, le premier joueur sélectionne une carte Influence dans sa main, et la dépose dans une colonne,\r\n\nsous une carte Objectif ou une autre carte Influence.\r\n"
				+ "Une carte Objectif est réalisée lorsque il y autant ou plus de carte Influence sous cette carte que la valeur de la carte.\r\n\n"
				+ "Une manche est finie lorsque les cartes Objectifs de toutes les colonnes sont réalissées.\r\n\n"
				+ "Une partie est finie à la fin de la 6e manche.");
		text.setFont(Font.font("Comic Sans MS", 20));
		content.getChildren().add(text);
		
		Label titre = new Label("Règles");
		titre.setFont(Font.font("Comic Sans MS", 40));
		
		HBTop.getChildren().add(titre);
		HBTop.setAlignment(Pos.TOP_CENTER);
		HBTop.setMinSize(1920, 10);
		HBTop.setPadding(new Insets(20,0,0,0));
		this.setTop(HBTop);
		
		VBRight.setAlignment(Pos.TOP_RIGHT);
		VBRight.getChildren().add(buttonBack);
		VBRight.setPadding(new Insets(-40,30,0,0));
		this.setRight(VBRight);
		
		VBLeft.getChildren().add(content);
		VBLeft.setAlignment(Pos.TOP_LEFT);
		VBLeft.setMinSize(350, 1000);
		VBLeft.setPadding(new Insets(250,0,0,250));
		this.setLeft(VBLeft);
	}
    
}
