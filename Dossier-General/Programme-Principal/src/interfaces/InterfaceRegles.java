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
		
		if(GI.langueSelectionne== "francais")
			contenu = "\t Dans un royaume en pleine déroute, des clans rivaux se livrent une lutte sans merci pour "
				+ "obtenir honneur et gloire. Vous devrez vous assurez la loyauté de personnages tous "
				+ "aussi utiles que douteux afin d'affirmer votre autorité. Mais quelle que soit le déroulement de la "
				+ "partie, ne savourez pas votre victoire trop tôt, car rien n'est gagné avant la dernière carte. "
				+ "--- \t But du jeu \n\n"
				+ "Etendre votre influence dans différents domaines en accumulant des cartes Objectifs. Le "
				+ "joueur qui aura obtenu le plus de points à la fin de la partie sera déclaré vainqueur. "
				+ "--- \t Matériel de jeu \n\n"
				+ "* 150 cartes Influence de 6 couleurs (une couleur par joueur)\n\n"
				+ "* 36 cartes Objetcifs, 6 cartes par domaine (avec les points 1, 2, 3, 4 et 5)\n"
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
		else
			contenu = "\t In a kingdom in disarray, rival clans fight mercilessly "
					+ "for honour and glory. You will have to secure the loyalty of characters as useful as they are dubious "
					+ "in order to assert your authority. But whatever the outcome of the game, don't "
					+ "savour your victory too soon, because nothing is won until the last card."
					+ "--- \t Aim of the game \n\n"
					+ "Expand your influence in different areas by accumulating Objective cards."
					+ "The player with the most points at the end of the game is declared the winner."
					+ "--- \t Game Material \n\n"
					+ "* 150 Influence cards of 6 colours (one colour per player)\n\n"
					+ "* 36 Objective cards, 6 cards per area (with points 1, 2, 3, 4 and 5)\n"
					+ "  Alchemy - Combat - Agriculture - Trade - Religion - Music"
					+ "--- \t Set up the game \n\n"
					+ "Each player chooses and takes the set of 25 Influence cards of one colour. Shuffle all the Influence cards,"
					+ " 3 cards are placed in your hand, the others in the reserve. Shuffle all the Objective cards that will be "
					+ "used in the game : 6 cards per player. If there are two players, the Objective cards with a value of 1 are removed. "
					+ "The Objective cards are then placed in a pile, face down on the table to form the deck."
					+ "--- \t How a game is played \n\n"
					+ "A game is played in 6 rounds. At the beginning of each round, as many Objective "
					+ "cards are uncovered as there are players (for example, 4 cards for 4 players). "
					+ "Leave enough space under the objective cards to place the influence cards. \n"
					+ "> The player whose turn it is must place 1 Influence card from his hand face down in a "
					+ "column of his choice. If a card is already on top of the card he places, he turns it over "
					+ "regardless of its colour. If the turned over card has a special ability, its effect is applied"
					+ " either immediately or at the end of the round.\n"
					+ "> The player then draws a card from his reserve. If his reserve is exhausted, he shuffles the "
					+ "cards from his discard pile and reconstitutes a new reserve face down.\n"
					+ "> Then it is the next player's turn.\n"
					+ "> This order is followed until the end of the round."
					+ "--- \t End of a round \n\n"
					+ "A round ends when all the objectives are completed.\n"
					+ "An objective card is completed when its column contains at least as many influence cards as its value. \n"
					+ "As long as there is at least one unfulfilled objective left, players can still place others under the completed objectives. "
					+ "The round ends as soon as all objectives are completed. All cards that are still hidden are then turned over. Their value "
					+ "is considered in the allocation of objectives, but not their special ability.\r\n"
					+ "--- Objective cards are awarded by counting each column one by one.\r\n"
					+ "The player with the highest total of Influence cards receives the Objective card for that column.\r\n"
					+ "In the event of a tie, the player who placed the closest Influence card to the Objective card wins.\r\n"
					+ "Each player then takes their used Influence cards and places them face up on their discard pile.\r\n"
					+ "New Objective cards are laid down according to the number of players and the next round begins. "
					+ "The next round begins with the player sitting to the left of the player who finished the previous round."
					+ "--- \t End of the game \n\n"
					+ "The game ends after 6 rounds, when all objective cards have been dealt. "
					+ "Each player's Objective cards are scored. There are 2 possibilities : \n"
					+ "1 - The player totals the points of all his Objective cards obtained. \n"
					+ "2 - If the player has Objective cards in each of the areas, he can make a "
					+ "special count: the points of the 6 Objective cards, one per area, are doubled. "
					+ "However, the player deducts 1 point from his score for each additional card he has. \n"
					+ "The player with the most points wins the game. \n"
					+ "In the event of a tie, the player with the most Objective cards of value 5 wins the game. "
					+ "If there is still a tie, do the same with the cards of value 4, etc."
					+ "--- \t Influence cards \n\n"
					+ "There are 3 types of Influence cards : \n"
					+ "> Cards with no special ability \n"
					+ "> Cards with an immediate special ability \n"
					+ "> Cards with a special ability at the end of the round"
					+ "--- \t Cards without capacity \n\n"
					+ "> The King \t\t\t\t"
					+ "> The Queen \n"
					+ "> Juliet \t\t\t\t\t"
					+ "> Alchemist \n"
					+ "> Master of Arms \t\t\t"
					+ "> Lord \n"
					+ "> Merchant \t\t\t\t"
					+ "> Cardinal \n"
					+ "> Troubadour"
					+ "--- \t Cards with immediate special ability \n\n"
					+ "> The Explorer \n"
					+ "> The Assassin \n"
					+ "> The Storm \n"
					+ "> The Traitor \n"
					+ "> Invisibility Cloak"
					+ "--- \t Cards with special abilities at the end of the round \n\n"
					+ "> The Three Musketeers \t\t"
					+ "> The Magician \n"
					+ "> The Witch \t\t\t\t"
					+ "> The Prince and the Squire \n"
					+ "> The Hermit \t\t\t\t"
					+ "> The Little Giant \n"
					+ "> The Dragon \t\t\t\t"
					+ "> Romeo \n"
					+ "> The Beggar \t\t\t\t"
					+ "> The Lookalike";
		
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
