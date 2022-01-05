package pp;

import java.util.Random;

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
 * Cette classe permet de créer une partie en ligne.
 * C'est sur cette interface qu'on peut choisir le nombre de joueurs, le nombre de joueurs réel, rentrer son pseudo et lancer la partie.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class InterfaceCreerPartie extends InterfaceBase {
	
	public GestionnaireInterfacePP GI = null; // link to the prime instance of GestionnaireInterfacePP is required to go back
	
	Slider slider;
	Label joueur;
	Button boutonRetour;
	Button boutonRejoindrePartieEnLigne;
	Button bJouer;
	TextField nompartie;
	
	/**
     *  Ce constructeur permet de créer tous les éléments de l'interface, c'est-à-dire le titre
     *  les glissières, la zone d'insertion de texte, le bouton retour et le bouton pour lancer la partie.
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
	public InterfaceCreerPartie(GestionnaireInterfacePP gi){
		super();
		GI = gi;
		dessineInterface(GI);
		
		}
	/**
     * Cette méthode permet d'envoyer le nom du joueur et le nombre de joueurs au gestionnaire d'interface pour créer une partie.
     * 
     * 
     * @param pseudo Nom du joueur.
     * 
     * @param nbjoueur Nombre de joueurs voulu pour la partie.
     * 
     * @since 1.0
     */
	
	public void creerPartie(String nompartie, int nbjoueur) {
		// GI.nombreJoueurReel;
		// checker le texte de nom partie pour le filtrer
		// a fix
        Data data = new Data(nbjoueur);
//        GI.nombreJoueurReel = nbjoueursreels;
//        GI.nombreJoueurVirtuel = nbjoueur-nbjoueursreels;
        GI.setData(data);
        GI.Lobby.dessineInterface(GI);
        //Partie partie = new Partie(jinitiateur, GI.getData());
    }
	@Override
	public void dessineInterface(GestionnaireInterfacePP GI) {

		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
		HBox HBJoueur = new HBox();
		HBJoueur.setAlignment(Pos.CENTER);
				
		Label TextJoueur = new Label("Choisissez le nombre de joueur");
		TextJoueur.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM , 30));
		TextJoueur.setPadding(new Insets(130,0,0,0));
		
		
		//Parametrage du slider
        Slider slider = new Slider();
        
        slider.setMin(2);
        slider.setMax(6);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setPadding(new Insets(10,20,10,20));
        slider.setOrientation(Orientation.HORIZONTAL);
        
        // HBJoueur
        // Entrer le pseudo du joueur
        TextField nompartie = new TextField();
        nompartie.setPromptText("Nom de la partie");
        nompartie.setFont(Font.font("Comic Sans MS", 20));
        nompartie.setPrefSize(220, 50);
        
        // Bouton Jouer
        Button boutonJouer = new Button("Jouer !");
        boutonJouer.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
        boutonJouer.setPrefSize(100, 50);
        
        boutonJouer.setOnAction(e -> { 
        	this.creerPartie(nompartie.getText(), (int) slider.getValue());
        	GI.afficherEcran(GI.InterfaceMap.get("lobby"));
        });
        
        HBJoueur.setSpacing(20);
        	
		VBox VBHaut = new VBox();
		VBHaut.setPrefSize(1520, 1080);
		VBHaut.setAlignment(Pos.TOP_CENTER);
		
		Label Titre = new Label("Créer Partie");
		Titre.setFont(Font.font("Pristina", FontWeight.BOLD, 120));
		Titre.setPadding(new Insets(20, 20, 0, 0));
        HBJoueur.getChildren().addAll(nompartie, boutonJouer);
        
        VBHaut.getChildren().addAll(Titre, TextJoueur,  slider, HBJoueur);
        VBHaut.setSpacing(50);
        VBHaut.setPadding(new Insets(0,0,0,400));
        VBHaut.setAlignment(Pos.TOP_CENTER);
		
		// VBDroite qui va contenir les boutons Retour et Rejoindre une partie
		VBox VBDroite = new VBox();
		VBDroite.setPrefSize(400,0);
		VBDroite.setAlignment(Pos.TOP_RIGHT);
		VBDroite.setPadding(new Insets(50,50,0,0));

		
		// Bouton retour
		boutonRetour = new Button();
		boutonRetour.setText("Retour");
		boutonRetour.setFont(Font.font("Comic sans MS", FontWeight.MEDIUM, 20));
		boutonRetour.setPrefSize(150, 50);
		
		VBDroite.getChildren().add(boutonRetour);
		
		// Mettre les VBox (VBHaut contient HBJoueur)
		this.setCenter(VBHaut);
		this.setRight(VBDroite);
		
		
		boutonRetour.setOnAction(e -> {
			GI.afficherEcran(GI.InterfaceMap.get("Menu"));
		});
		
	}
}

