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
	
		Button boutonGauche = new Button("<- gauche");
		Button boutonDroit = new Button("droite ->");
		boutonGauche.setPrefSize(150, 500);
		boutonDroit.setPrefSize(150, 500);
		
		// --------------------------------------- pour defiler les pages ----------------------------------------- //
			try {
//				File fichier = new File("regles.txt");
//		        BufferedReader br = new BufferedReader(new FileReader(fichier));
//		        String st, contenu = "";
//		        while ((st = br.readLine()) != null)
//		            contenu += st + "\n";
//		        br.close();
				String contenu = "Le jeu de Cape et d'Epée se joue entre 2 et 6 joueurs. SI vous n'êtes pas assez nombreux, il est possible d'ajouter des Bots. "
						+ "--- La partie peut commencer !Pour commencer à jouer, le premier joueur sélectionne une carte Influence dans sa main, "
						+ "--- et la dépose dans une colonne,sous une carte Objectif ou une autre carte Influence. "
						+ "--- Une carte Objectif est réalisée lorsque il y autant ou plus de carte Influence sous cette carte que la valeur de la carte."
						+ "--- Une manche est finie lorsque les cartes Objectifs de toutes les colonnes sont réalissées."
						+ "--- Une partie est finie à la fin de la 6e manche.";
		        String[] page = contenu.split("---");
		        Label text = new Label();
				text.setWrapText(true);
				text.setText(page[0]);
				text.setFont(Font.font("Comic Sans MS", 20));
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
		
//		HBTop.getChildren().add(titre);
//		HBTop.setAlignment(Pos.TOP_CENTER);
//		HBTop.setMinSize(1920, 10);
//		HBTop.setPadding(new Insets(20,0,0,0));
//		this.setTop(HBTop);
//		
//		VBTopDroite.setAlignment(Pos.TOP_RIGHT);
//		VBTopDroite.getChildren().add(boutonRetour);
//		VBTopDroite.setPadding(new Insets(-40,30,0,0));
//		this.setRight(VBTopDroite);
//		
//		VBCentre.getChildren().add(content);
//		VBCentre.setAlignment(Pos.CENTER);
//		VBCentre.setMinSize(350, 1000);
//		VBCentre.setPadding(new Insets(250,0,0,250));
//		this.setCenter(VBCentre);
		
		VBox HBTop = new VBox();
		HBox VBTopDroite = new HBox(titre, boutonRetour);
		HBTop.getChildren().add(VBTopDroite);
		HBox VBCentre = new HBox(boutonGauche, regles, boutonDroit);
		HBTop.getChildren().add(VBCentre);
		HBTop.setMinSize(1920, 1080);
		this.setTop(HBTop);
		
	}
    
}
