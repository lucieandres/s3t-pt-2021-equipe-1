package pp;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	ChoiceBox<String> difficulte;
	
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
		this.dessineInterface(gi);
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
	
	public void creerPartie(int nbjoueur) {
        Data data = new Data(nbjoueur);
        GI.setData(data);
        GI.afficherEcran(GI.InterfaceMap.get("Attente"));
        GI.com.initServeurTCP(7777, GestionnaireInterfacePP.getMyTCPCallback());
        GI.Plateau.drawPartie(GI);
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
		
		Label TextJoueurReel = new Label("Choisissez le nombre de joueur réel");
		TextJoueurReel.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM , 30));
		TextJoueurReel.setPadding(new Insets(60,0,0,0));
		
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
        
      //Parametrage du slider
        Slider slider1 = new Slider();
        
        slider1.setMin(2);
        slider1.setMax(6);
        slider1.setBlockIncrement(1);
        slider1.setMajorTickUnit(1);
        slider1.setMinorTickCount(0);
        slider1.setShowTickLabels(true);
        slider1.setSnapToTicks(true);
        slider1.setPadding(new Insets(10,20,10,20));
        slider1.setOrientation(Orientation.HORIZONTAL);
        
        // CheckBox Difficulté
        difficulte = new ChoiceBox<>();
        difficulte.getItems().add("Facile");
        difficulte.getItems().add("Moyen");
        difficulte.getItems().add("Difficile");
        
        Label txtDiff = new Label("Veuillez saisir la difficulté des bots : ");
        txtDiff.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
        
        HBox HBDiff = new HBox();
        HBDiff.setAlignment(Pos.CENTER);
        HBDiff.getChildren().addAll(txtDiff, difficulte);
        
        // Bouton Jouer
        Button boutonJouer = new Button("Créer le salon !");
        boutonJouer.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
        boutonJouer.setPrefSize(300, 50);
        
        boutonJouer.setOnAction(e -> { 
        	this.creerPartie((int) slider.getValue());
        	//difficulte.getValue(); -> Pour récup la difficulté pour un bot
        });
        
        HBJoueur.setSpacing(20);
        	
		VBox VBHaut = new VBox();
		VBHaut.setPrefSize(1520, 1080);
		VBHaut.setAlignment(Pos.TOP_CENTER);
		
		Label Titre = new Label("Créer Partie");
		Titre.setFont(Font.font("Pristina", FontWeight.BOLD, 120));
		Titre.setPadding(new Insets(20, 20, 0, 0));
        HBJoueur.getChildren().addAll(boutonJouer);
        
        VBHaut.getChildren().addAll(Titre, TextJoueur,  slider, TextJoueurReel, slider1, HBDiff, HBJoueur);
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