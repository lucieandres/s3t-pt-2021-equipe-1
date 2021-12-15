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
		dessineInterface(GI);
    }
		
    public void dessineInterface(GestionnaireInterface GI) {
    	// --------------------------------------- fond ----------------------------------------- //
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
		// --------------------------------------- bouton retour ----------------------------------------- //
		Button boutonRetour = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.retour"));
		boutonRetour.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		boutonRetour.setFont(Font.font("Comic Sans MS", 20));
		boutonRetour.setPrefWidth(GI.screenBounds.getWidth()*0.08);
		
		// --------------------------------------- contenu ----------------------------------------- //
		VBox regles = new VBox();
		regles.setAlignment(Pos.CENTER);
		regles.setPrefSize(GI.screenBounds.getWidth()*0.73, GI.screenBounds.getHeight()*0.93);
		regles.setPadding(new Insets(0,GI.screenBounds.getWidth()*0.08,0,GI.screenBounds.getWidth()*0.08));
		String contenu = "\t Dans un royaume en pleine déroute, des clans rivaux se livrent une lutte sans merci pour "
				+ "obtenir honneur et gloire. Vous devrez vous assurez la loyauté de personnages tous "
				+ "aussi utiles que douteux afin d'affirmer votre autorité. Mais quele que soit le déroulement de la "
				+ "partie, ne savourez pas votre victoire trop tôt, car rien n'est gagné avant la dernière carte. "
				+ "--- \t But du jeu \n\n"
				+ "Etendre votre influence dans différents domaines en accumulant des cartes Objectifs. Le "
				+ "joueur qui aura obtenu le plus de points à la fin de la partie sera déclaré vainqueur. "
				+ "--- \t Matériel de jeu \n\n"
				+ "* 150 cartes Influence de 6 couleurs (une couleur par joueur)\n\n"
				+ "* 36 cartes Objetcifs, 6 cartes par domaine (avec les points 1, 2, 3, 4 et 5).\n"
				+ "  Alchimie - Combat - Agriculture - Commerce - Religion - Musique"
				+ "--- \t Mise en place du jeu \n\n"
				+ "Chaque joueur choisit et prend la série de 25 cartes Influence d'une couleur. On "
				+ "mélange toutes les cartes Influences, 3 cartes sont placées en main, les autres dans la réserve. "
				+ "On mélange toutes les cartes Objectifs qui seront utilisées pour la aprties : 6 cartes "
				+ "par joueur. A 2 joueurs, on retire les objectifs de valeur 1. Les cartes Objectif sont "
				+ "ensuite posées en pile, face cachée sur la table pour formée la pioche. "
				+ "--- \t Déroulement d'une partie \n\n"
				+ "Une aprtie se joue en 6 macnhes. UA début de chaque manche, on découvre autant, "
				+ "de cartes Objectif que le nombre de joueurs (par exemple, 4 cartes pour 4 joueurs). "
				+ "On laisse suffisament d'espsace sous les cartes objectifs pour pouvoir y placer les "
				+ "cartes Influence.";
	
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
		Label titre = new Label(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.titreRegle"));
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
