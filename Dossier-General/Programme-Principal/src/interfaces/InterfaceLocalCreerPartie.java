package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

public class InterfaceLocalCreerPartie extends InterfaceBase {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	Slider slider;
	Label joueur;
	Button boutonRetour;
	Button boutonRejoindre;
	Button bJouer;
	TextField pseudo;
	ChoiceBox<String> difficulte;
	
	/**
     *  Ce constructeur permet de créer tous les éléments de l'interface, c'est-à-dire le titre
     *  la glissière, la zone d'insertion de texte, le bouton retour et le bouton pour lancer la partie.
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
	public InterfaceLocalCreerPartie(GestionnaireInterface gi){
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
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
		HBox HBJoueur = new HBox();
		HBJoueur.setAlignment(Pos.CENTER);
				
		Label TextJoueur = new Label(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.nbJoueur"));
		TextJoueur.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM , 30));
		TextJoueur.setPadding(new Insets(150,0,0,0));
		
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
        
        //Choix de la difficulté
        
        difficulte = new ChoiceBox<>();
        difficulte.getItems().add(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.choixBot.facile"));
        difficulte.getItems().add(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.choixBot.normal"));
        difficulte.getItems().add(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.choixBot.difficile"));
        
        Label txtDiff = new Label(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.choixBot"));
        txtDiff.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
        
        HBox HBDiff = new HBox();
        HBDiff.setAlignment(Pos.CENTER);
        HBDiff.getChildren().addAll(txtDiff, difficulte);
        
        // HBJoueur
        // Entrer le pseudo du joueur
        TextField pseudo = new TextField();
        pseudo.setPromptText(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.pseudo"));
        pseudo.setFont(Font.font("Comic Sans MS", 20));
        pseudo.setPrefSize(220, 50);
        
        // Bouton Jouer
        Button boutonJouer = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.jouer"));
        boutonJouer.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
        boutonJouer.setPrefSize(100, 50);
        
        boutonJouer.setOnAction(e -> { 
        	this.creerPartie(pseudo.getText(), (int) slider.getValue());
        	GI.afficherEcran(GI.InterfaceMap.get("jeu"));
        });
        
        HBJoueur.setSpacing(20);
        	
		VBox VBHaut = new VBox();
		VBHaut.setPrefSize(1520, 1080);
		VBHaut.setAlignment(Pos.TOP_CENTER);
		
		Label Titre = new Label(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.creerPartie"));
		Titre.setFont(Font.font("Pristina", FontWeight.BOLD, 120));
		Titre.setPadding(new Insets(20, 20, 0, 0));
        HBJoueur.getChildren().addAll(pseudo, boutonJouer);
        
        VBHaut.getChildren().addAll(Titre, TextJoueur, slider, HBDiff, HBJoueur);
        VBHaut.setSpacing(50);
        VBHaut.setPadding(new Insets(0,0,0,400));
        VBHaut.setAlignment(Pos.TOP_CENTER);
		
		// VBDroite qui va contenir les boutons Retour et Jouer en Ligne	
		VBox VBDroite = new VBox();
		VBDroite.setPrefSize(400,0);
		VBDroite.setAlignment(Pos.TOP_RIGHT);
		VBDroite.setPadding(new Insets(50,50,0,0));

		
		// Bouton retour
		boutonRetour = new Button();
		boutonRetour.setText(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.retour"));
		boutonRetour.setFont(Font.font("Comic sans MS", FontWeight.MEDIUM, 20));
		boutonRetour.setPrefSize(150, 50);
		
		VBDroite.getChildren().add(boutonRetour);
		
		// Bouton Jouer en ligne
		boutonRejoindre = new Button();
		boutonRejoindre.setText(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.partieLigne"));
		boutonRejoindre.setFont(Font.font("Comic sans MS", FontWeight.MEDIUM, 18));
		boutonRejoindre.setPrefSize(150, 50);
		VBDroite.setSpacing(50);
		
		VBDroite.getChildren().add(boutonRejoindre);
		
		// Mettre les VBox (VBHaut contient HBJoueur)
		this.setCenter(VBHaut);
		this.setRight(VBDroite);
		
		
		boutonRetour.setOnAction(e -> {
			GI.afficherEcran(GI.InterfaceMap.get("menu"));
		});
		
	
		boutonRejoindre.setOnAction(e -> {
			GI.afficherEcran(GI.InterfaceMap.get("rejoindre"));
		});
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
	
	public void creerPartie(String pseudo, int nbjoueur) {
		if(pseudo.isBlank() || pseudo.length()>12) {
			pseudo = "Joueur1";
		}
        Joueur jinitiateur = new Joueur(Color.YELLOW, pseudo);
        Data data = new Data(jinitiateur, nbjoueur);
        GI.setData(data);
        GI.Jeux.drawPartie(GI);
    }
}