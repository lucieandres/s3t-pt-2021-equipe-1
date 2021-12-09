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
import javafx.scene.text.FontWeight;

/**
 * Cette classe est une interface qui représente les paramètres.
 * 
 *   
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceRegles extends InterfaceBase {
    
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
		boutonRetour.setPrefWidth(GI.screenBounds.getWidth()*0.08);
		
		// --------------------------------------- contenu ----------------------------------------- //
		VBox regles = new VBox();
		regles.setAlignment(Pos.CENTER);
		regles.setPrefSize(GI.screenBounds.getWidth()*0.73, GI.screenBounds.getHeight()*0.93);
		regles.setPadding(new Insets(0,GI.screenBounds.getWidth()*0.08,0,GI.screenBounds.getWidth()*0.08));
		String contenu = "Le jeu de Cape et d'Epée se joue entre 2 et 6 joueurs. Si vous n'êtes pas assez nombreux, il est possible d'ajouter des Bots.\n\n"
				+ "La partie peut commencer !\n\n"
				+ "Pour commencer à jouer, le premier joueur sélectionne une carte Influence dans sa main, "
				+ "et la dépose dans une colonne,sous une carte Objectif ou une autre carte Influence."
				+ "Une carte Objectif est réalisée lorsque il y autant ou plus de carte Influence sous cette carte que la valeur de la carte. "
				+ "Une manche est finie lorsque les cartes Objectifs de toutes les colonnes sont réalissées. "
				+ "Une partie est finie à la fin de la 6e manche. "
				+ "--- \tBut du jeu \n\n"
				+ "Etendre votre influence dans diiférents dommaines en accumulant des cartes Objectif. "
				+ "Le joueur qui aura obtenu le plus de points à la fin de la partie sera déclaré vainqueur.";
	
		// --------------------------------------- pour defiler les pages ----------------------------------------- //

		
		Button boutonGauche = new Button("<");
		boutonGauche.setPrefSize(GI.screenBounds.getWidth()*0.08, GI.screenBounds.getHeight()*0.46);
		boutonGauche.setFont(Font.font("Comic Sans MS", 50));
		
		Button boutonDroit = new Button(">");
		boutonDroit.setPrefSize(GI.screenBounds.getWidth()*0.08, GI.screenBounds.getHeight()*0.46);
		boutonDroit.setFont(Font.font("Comic Sans MS", 50));

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

	        
		// --------------------------------------- titre ----------------------------------------- //
		Label titre = new Label("Règles");
		titre.setFont(Font.font("Pristina", FontWeight.BOLD,120));
		
		
		// --------------------------------------- disposition ----------------------------------------- //
		VBox VBTopCentre = new VBox();
		VBTopCentre.getChildren().add(titre);
		VBTopCentre.setPadding(new Insets(0,0,0,GI.screenBounds.getWidth()*0.42));

		VBox HBTopDroite = new VBox(boutonRetour);
		HBTopDroite.setPadding(new Insets(GI.screenBounds.getHeight()*0.04,0,0,GI.screenBounds.getWidth()*0.35));
		
		HBox HBTop = new HBox(VBTopCentre, HBTopDroite);
		this.setTop(HBTop);
		
		HBox HBCentre = new HBox(boutonGauche, regles, boutonDroit);
		HBCentre.setAlignment(Pos.CENTER);
		HBCentre.setPadding(new Insets(GI.screenBounds.getHeight()*-0.09,GI.screenBounds.getWidth()*0.07,GI.screenBounds.getHeight()*0.01,GI.screenBounds.getWidth()*0.06));
		this.setCenter(HBCentre);

	}
    
}
