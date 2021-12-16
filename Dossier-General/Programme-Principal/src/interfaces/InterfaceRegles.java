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
	
    /**
     *  Dessine l'interface.
     * 
     * @param gi le gestionnaire d'interface permettra de dessiner l'interface dans la langue séléctionné.
     * 
     * @since 1.0
     */
    
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
		
		String contenu = "";
		if(GI.langueSelectionne=="francais") {
				contenu = "\t Dans un royaume en pleine déroute, des clans rivaux se livrent une lutte sans merci pour "
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
				+ "On mélange toutes les cartes Objectifs qui seront utilisées pour la parties : 6 cartes "
				+ "par joueur. A 2 joueurs, on retire les objectifs de valeur 1. Les cartes Objectif sont "
				+ "ensuite posées en pile, face cachée sur la table pour formée la pioche. "
				+ "--- \t Déroulement d'une partie \n\n"
				+ "Une partie se joue en 6 macnhes. Au début de chaque manche, on découvre autant, "
				+ "de cartes Objectif que le nombre de joueurs (par exemple, 4 cartes pour 4 joueurs). "
				+ "On laisse suffisament d'espsace sous les cartes objectifs pour pouvoir y placer les "
				+ "cartes Influence. \n"
				+ "> Le joueur dont c'est le tour doit placer 1 carte Influence de sa main face cachée dans "
				+ "une colonne de son choix. Si au-dessus de la carte qu'il place se trouve déjà une "
				+ "carte, il la retourne quelle que soit sa couleur. Si la carte retournée a une capacité spéciale, "
				+ "son effet est appliqué soit immédiatement, soit à la fin de la manche.\n"
				+ "> Ensuite le joueur pioche une carte de sa réserve. Si sa réserve est épuisée, "
				+ "il mélange les cartes de sa défausse et reconstitue une nouvelle réserve face cachée.\n"
				+ "> Puis c'est au tour du joueur suivant.\n"
				+ "> On procède ainsi dans cet ordre jusqu'à la fin de la manche."
				+ "--- \t Fin d'une manche \n\n"
				+ "Une manche prend fin lorsque tous les objectifs sont réalisés.\n"
				+ "Une carte Objectif est réalisée lorsque sa colonne compte au moins autant de carte "
				+ "Influence que sa valeur. \n"
				+ "Tant qu'il reste au moins un objectif non réalisé, les joueurs peuvent encore placer "
				+ "d'autres sous les objectifs réalisés. La manche prend fin dès que tous les objectifs "
				+ "sont réalisés. Toutes les cartes encore cachées sont alors retournées. Leur valeur est "
				+ "considérée dans l'attribution des objectifs, mais pas leur capacité spéciale. \n"
				+ "---Les cartes Objectif sont attribuées en comptabilisant chaque colonne une par une. \n"
				+ "Le joueur qui obtient le plus grad total en additionnant les valeurs de ses cartes Influence "
				+ "reçoit la carte Objectif de la colonne. \n"
				+ "En cas d'égalité, le joueur qui a posé la carte Influence la plus proche de la carte Objectif "
				+ "remporte cette dernière. \n"
				+ "Chaque joueur reprend ensuite ses cartes Influence utilisées et les met en face visible sur sa défausse. \n"
				+ "De nouvelles cartes Objectifs sont posées selon le nombre de joueurs et la manche suivante commence. Elle "
				+ "débute avec le joueur qui est assis à la gauche du joueur qui a terminé la manche précédente."
				+ "--- \t Fin du jeu \n\n"
				+ "Le jeu prend fin après 6 manches, lorsque toutres les cartes Objectif ont été attribuées. "
				+ "Pour chaque joueur, les points de ses cartes Objectifs sont comptés. Il y a 2 possibilités : \n"
				+ "1 - Le joueur totalise les points de toutes ses cartes Objectifs obtenus. \n"
				+ "2 - Si le joueur possède des cartes Objectif dans chacun des domaines, il peut faire un "
				+ "décompte spécial : les points des 6 cartes Objectif, une par domaine, sont doublés. "
				+ "Toutefois, le joueur déduit 1 point de son score par carte supplémentaire qu'il possède. \n"
				+ "Le joueur avec le plus de points remporte la partie. \n"
				+ "En cas d'égalité, le joueur qui a le plus de cartes OBjectif de valeur 5 remporte la partie. "
				+ "Si l'égalité persiste, faire de même avec les cartes de valeur 4, etc."
				+ "--- \t Les cartes Influence \n\n"
				+ "Il y a 3 sortes de cartes Influence : \n"
				+ "> Cartes sans capacité spéciale \n"
				+ "> Cartes avec capacité spéciale immédiate \n"
				+ "> Cartes avec capacité spéciale en fin de manche"
				+ "--- \t Cartes sans capacité \n\n"
				+ "> Le Roi \t\t\t\t\t"
				+ "> La Reine \n"
				+ "> Juliette \t\t\t\t\t"
				+ "> Alchimiste \n"
				+ "> Maître d'armes \t\t\t"
				+ "> Seigneur \n"
				+ "> Marchand \t\t\t\t"
				+ "> Cardinal \n"
				+ "> Troubadour"
				+ "--- \t Cartes avec capacité spéciale immédiate \n\n"
				+ "> L'Explorateur \n"
				+ "> L'Assassin \n"
				+ "> La Tempête \n"
				+ "> Le Traître \n"
				+ "> La Cape d'invisibilité"
				+ "--- \t Cartes avec capacité spéciale en fin de manche \n\n"
				+ "> Les Trois Mousquetaires \t"
				+ "> Le Magicien \n"
				+ "> La sorcière \t\t\t\t"
				+ "> Le Prince et L'Ecuyer \n"
				+ "> L'Ermite \t\t\t\t"
				+ "> Le Peti Géant \n"
				+ "> Le Dragon \t\t\t\t"
				+ "> Roméo \n"
				+ "> Le Mendiant \t\t\t\t"
				+ "> Le Sosie"; 
		}
		else {
			contenu = "";
		}
	
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
