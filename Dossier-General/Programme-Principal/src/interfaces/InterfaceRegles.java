package interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
    
    public GestionnaireInterface GI;
    private int indexPage = 0;
    
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
		
		// --------------------------------------- fond ----------------------------------------- //
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
		// --------------------------------------- bouton retour ----------------------------------------- //
		Button boutonRetour = new Button("Retour");
		boutonRetour.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		boutonRetour.setFont(Font.font("Comic Sans MS", 20));
		boutonRetour.setPrefWidth(150);
		
		// --------------------------------------- contenu ----------------------------------------- //
		VBox regles = new VBox();
		regles.setAlignment(Pos.CENTER);
		regles.setPrefSize(1400, 1000);
		regles.setPadding(new Insets(0,150,0,150));
	
		Button boutonGauche = new Button("<");
		boutonGauche.setPrefSize(150, 500);
		boutonGauche.setFont(Font.font("Comic Sans MS", 50));
		
		Button boutonDroit = new Button(">");
		boutonDroit.setPrefSize(150, 500);
		boutonDroit.setFont(Font.font("Comic Sans MS", 50));

		
		// --------------------------------------- pour defiler les pages ----------------------------------------- //
			try {
				String contenu = "Le jeu de Cape et d'Epée se joue entre 2 et 6 joueurs. SI vous n'êtes pas assez nombreux, il est possible d'ajouter des Bots.\n"
						+ "La partie peut commencer !\n"
						+ "Pour commencer à jouer, le premier joueur sélectionne une carte Influence dans sa main,\n"
						+ "et la dépose dans une colonne,sous une carte Objectif ou une autre carte Influence.\n"
						+ "Une carte Objectif est réalisée lorsque il y autant ou plus de carte Influence sous cette carte que la valeur de la carte.\n"
						+ "Une manche est finie lorsque les cartes Objectifs de toutes les colonnes sont réalissées.\n"
						+ "Une partie est finie à la fin de la 6e manche.\n";
		        String[] page = contenu.split("---");
		        Label text = new Label();
				text.setWrapText(true);
				text.setText(page[0]);
				text.setFont(Font.font("Comic Sans MS", 30));
				regles.getChildren().add(text);
				boutonDroit.setOnAction((e) -> {
					indexPage = Math.min(indexPage+1, page.length-1);
					text.setText(page[indexPage]);
				});
				boutonGauche.setOnAction((event) -> {
					indexPage = Math.max(indexPage-1, 0);
					text.setText(page[indexPage]);
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	        
		// --------------------------------------- titre ----------------------------------------- //
		Label titre = new Label("Règles");
		titre.setFont(Font.font("Comic Sans MS", 40));
		
		
		// --------------------------------------- disposition ----------------------------------------- //
		VBox VBTopCentre = new VBox();
		VBTopCentre.getChildren().add(titre);
		VBTopCentre.setPadding(new Insets(20,0,0,900));

		VBox HBTopDroite = new VBox(boutonRetour);
		HBTopDroite.setPadding(new Insets(20,0,0,700));
		
		HBox HBTop = new HBox(VBTopCentre, HBTopDroite);
		this.setTop(HBTop);
	
		
		HBox HBCentre = new HBox(boutonGauche, regles, boutonDroit);
		HBCentre.setAlignment(Pos.CENTER);
		HBCentre.setPadding(new Insets(0,140,0,110));
		this.setCenter(HBCentre);
		
		
	}
    
}
