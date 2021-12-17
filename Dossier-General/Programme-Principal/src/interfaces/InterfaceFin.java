package interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import joueur.Joueur;
import moteur.Data;

/**
 * Cette classe permet de créer une partie.
 * C'est sur cette interface qu'on peut choisir le nombre de joueurs, rentrer son pseudo et lancer la partie.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class InterfaceFin extends InterfaceBase {
	
	Label titre;
	Label pseudo;
	Button boutonMenu;
	Button boutonRejouerLocal;
	Button boutonRejouerLigne;
	
	HBox HBHaut;
	HBox HBMilieu;
	HBox HBBas;
	
	VBox VBDroite;
	VBox VBGauche;

	VBox VBJoueur[];	
	HBox HBClassement[];
	HBox HBPseudo[];
	HBox HBCouleur[];
	HBox HBNbCarte[];
	HBox HBScore[];
	
	
	/**
     *  Ce constructeur permet de créer tous les éléments de l'interface, c'est-à-dire le titre
     *  la glissière, la zone d'insertion de texte, le bouton retour et le bouton pour lancer la partie.
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
	public InterfaceFin(GestionnaireInterface gi){
		super();
		dessineInterface(gi);
	}
	
	/**
     *  Dessine l'interface.
     * 
     * @param gi le gestionnaire d'interface permettra de dessiner l'interface dans la langue séléctionné.
     * 
     * @since 1.0
     */
	
	public void dessineInterface(GestionnaireInterface gi) {
		this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
		titre = new Label("Score");
		titre.setFont(Font.font("Pristina", FontWeight.BOLD,80));
		
		boutonMenu = new Button(gi.texteLangue.get(gi.langueSelectionne).getProperty("bouton.menu"));
		boutonMenu.setOnAction(e -> gi.afficherEcran(gi.InterfaceMap.get("menu")));
		boutonMenu.setPrefWidth(gi.screenBounds.getWidth()*0.08);
		boutonMenu.setFont(Font.font("Comic Sans MS", 20));
		
		AnchorPane elementTop= new AnchorPane(titre, boutonMenu);
		
		elementTop.setPrefSize(gi.screenBounds.getWidth(), (gi.screenBounds.getHeight()/9)*1);
		
		AnchorPane.setLeftAnchor(titre,gi.screenBounds.getWidth()/2 - titre.getBoundsInParent().getWidth());
        AnchorPane.setTopAnchor(titre, 20.0);
		
		AnchorPane.setRightAnchor(boutonMenu,20.0);
        AnchorPane.setTopAnchor(boutonMenu, 20.0);
        
		this.setTop(elementTop);
		
		boutonRejouerLocal = new Button(gi.texteLangue.get(gi.langueSelectionne).getProperty("bouton.rejouerLocal"));
		boutonRejouerLocal.setOnAction(e -> gi.afficherEcran(gi.InterfaceMap.get("creerPartie")));
		//boutonRejouerLocal.setPrefWidth(gi.screenBounds.getWidth()*0.08);
		boutonRejouerLocal.setFont(Font.font("Comic Sans MS", 20));
		
/*		boutonRejouerLigne = new Button("Rejouer en Ligne");
		boutonRejouerLigne.setOnAction(e -> gi.afficherEcran(gi.InterfaceMap.get("creerPartieEnLigne")));
		boutonRejouerLigne.setFont(Font.font("Comic Sans MS", 20));
	*/	
		VBox coteDroit  = new VBox(boutonRejouerLocal/*, boutonRejouerLigne*/);
		coteDroit.setPrefSize(gi.screenBounds.getWidth()/7, gi.screenBounds.getHeight() - (gi.screenBounds.getHeight()/9));
		coteDroit.setAlignment(Pos.CENTER);
		coteDroit.setSpacing(10);
		
		Pane coteGauche = new Pane();
		coteGauche.setPrefSize(gi.screenBounds.getWidth()/7, gi.screenBounds.getHeight() - (gi.screenBounds.getHeight()/9));
		
		
		this.setRight(coteDroit);
		this.setLeft(coteGauche);
	}
	/**
     * Cette fonction permet d'afficher les statistiques d'un joueur
     * 
     * @param J le joueur dont on souhaite voir les statistiques
     * 
     * @since 1.0
     */
	
	public VBox afficherResultatJoueur(Joueur J, int Max) {
		
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		int VolumeCoeff = 7;
		int taillePolice = 24;
		
		VBox StatsJoueur = new VBox();
		StatsJoueur.setAlignment(Pos.CENTER);
		StatsJoueur.setPrefSize(160, (screenBounds.getHeight()/9)*6);
		
		// necessaire poue l'équilibrage de l'interface
		Pane espaceVolume = new Pane(); 
		espaceVolume.setPrefSize(USE_COMPUTED_SIZE,Max*VolumeCoeff - J.getScore()*VolumeCoeff);
		
		//score of the player
		Text textScore = new Text(J.getScore()+""); 
		textScore.setFont(new Font("Comic Sans MS", taillePolice));
		
		// visual representation of the score of the player
		Pane scoreVolume = new Pane(); 
		scoreVolume.setBackground(new Background(new BackgroundFill(J.getCouleur(),CornerRadii.EMPTY,null)));
		scoreVolume.setPrefSize(USE_COMPUTED_SIZE, J.getScore()*VolumeCoeff);
		
		//name of the player
		Text textName = new Text(J.getPseudo()+""); 
		textName.setFont(new Font("Comic Sans MS", taillePolice));
		
		//number of card of the player
		Text textcarteNB = new Text(J.getObjectif().size()+""); //name of the player
		textcarteNB.setFont(new Font("Comic Sans MS", taillePolice));
		
		StatsJoueur.getChildren().addAll(espaceVolume,textScore,scoreVolume,textName,textcarteNB);
		return StatsJoueur;
	}
	
	/**
     * Cette procédure permet d'afficher les statistiques des joueurs en fin de partie
     * 
     * @param data les données d'une partie
     * 
     * @since 1.0
     */
	
	public void afficherStats(Data data) {
		
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		
		HashMap<Integer, Joueur> scores = new HashMap<>();
		for(Joueur j : data.getJoueurs())
			scores.put(j.getScore(), j);
		TreeMap<Integer, Joueur> triScores = new TreeMap<>(scores);
		ArrayList<Joueur> triJoueur = new ArrayList<>(triScores.values());
		System.out.println(triScores);
		
		HBMilieu = new HBox();
		HBMilieu.setAlignment(Pos.CENTER);
		HBMilieu.setSpacing(20);
		HBMilieu.setMinWidth(screenBounds.getWidth() - (screenBounds.getWidth()/7)*2);
		HBMilieu.setMinHeight(screenBounds.getHeight()-screenBounds.getHeight()/9);
		//HBMilieu.setPrefSize(screenBounds.getWidth(), screenBounds.getHeight()-screenBounds.getHeight()*9);
		
		int Max = triJoueur.get(triJoueur.size()-1).getScore();
		
		//ajoute la colonne de chaque joueur 
		/*
		for(Joueur j : triJoueur) {
			HBMilieu.getChildren().add(afficherResultatJoueur(j,Max));
		}*/
		for(int j = triJoueur.size()-1;j>=0;j--)
			HBMilieu.getChildren().add(afficherResultatJoueur(triJoueur.get(j),Max));

		this.setCenter(HBMilieu);
	}
}
