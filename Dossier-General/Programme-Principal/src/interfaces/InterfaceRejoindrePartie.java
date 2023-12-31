package interfaces;

import java.util.regex.Pattern;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Cette classe permet de rejoindre une partie.
 * C'est sur cette interface qu'un joueur peut rejoindre en tant qu'invité un salon qui a été au préalable créé par InterfaceCreerPartie.java par un autre joueur.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceRejoindrePartie extends InterfaceBase {
	
	public GestionnaireInterface GI = null; //Un lien vers l'instance principale de GestionnaireInterface est nécessaire pour revenir en arrière.
	
	Label titre;
	Label TexteJoueur;
	Label TexteCode;
	Button boutonRetour;
	Button boutonJouer;
	TextField pseudo;
	TextField code;
	VBox VBHaut;
	
	/**
     *  Ce constructeur permet de rejoindre une partie en ligne.
     *  Pour rejoindre un e parite, on doit saisir notre pseudo et le code de la partie
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
	public InterfaceRejoindrePartie(GestionnaireInterface gi) {
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
		
//-----------------Créations des composants------------------------------------------------        
       
        //Création du titre
      	Label Titre = new Label(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.rejoindrePartie"));
      	Titre.setFont(Font.font("Pristina", FontWeight.BOLD,120));
      	Titre.setPadding(new Insets(GI.screenBounds.getHeight()*0.02, GI.screenBounds.getWidth()*0.01, 0, 0));
      	
      	//Création des labels de textes
      	
      	Label TexteJoueur = new Label(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.entreePseudo"));
      	TexteJoueur.setFont(Font.font("centaur", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
      	TexteJoueur.setPadding(new Insets(0,GI.screenBounds.getWidth()*0.01,0,0));
      	
      	Label TexteCode = new Label(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.codeSalon"));
      	TexteCode.setFont(Font.font("centaur", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
      	TexteCode.setPadding(new Insets(0,GI.screenBounds.getWidth()*0.01,0,0));
      	
      	//Création des champs de saisie
      	
      	TextField pseudo = new TextField();
      	pseudo.setPromptText(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.pseudo"));
      	pseudo.setFont(Font.font("Comic Sans MS", 20));
      	
      	TextField code = new TextField();
      	code.setPromptText(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.code"));
      	code.setFont(Font.font("Comic Sans MS", 20));
      	
      	// Bouton retour
     	boutonRetour = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.retour"));
     	boutonRetour.setOnAction(e -> { GI.afficherEcran(GI.InterfaceMap.get("menu")); });
     	boutonRetour.setPrefSize(GI.screenBounds.getWidth()*0.08,GI.screenBounds.getHeight()*0.05);
     	boutonRetour.setFont(Font.font("Comic sans MS", FontWeight.MEDIUM, 20));
     	
     	// Bouton jouer
     	Button boutonJouer = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.jouer"));
        boutonJouer.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
        boutonJouer.setPrefSize(GI.screenBounds.getWidth()*0.08,GI.screenBounds.getHeight()*0.05);
        
        boutonJouer.setOnAction(e -> rejoindrePartie(pseudo.getText(), code.getText()));
     	
     	
//------------------Créations des conteneurs------------------------------------------------
     	
     	//Création d'une Hbox qui va accueillir un textField (Pseudo) et un label (indication)
        HBox HBName = new HBox();
        HBName.setAlignment(Pos.CENTER);
        HBName.setMaxSize(GI.screenBounds.getWidth()*0.8, GI.screenBounds.getHeight());
        HBName.setPadding(new Insets(GI.screenBounds.getHeight()*0.19,0,0,0));
        HBName.getChildren().addAll(TexteJoueur,pseudo);
        
     	//Création d'une Hbox qui va accueillir un textField (Pseudo) et un label (indication)
        HBox HBCode = new HBox();
        HBCode.setAlignment(Pos.CENTER);
        HBCode.setMaxSize(GI.screenBounds.getWidth()*0.8, GI.screenBounds.getHeight());
        HBCode.setPadding(new Insets(GI.screenBounds.getHeight()*0.07,0,GI.screenBounds.getHeight()*0.07,0));
        HBCode.getChildren().addAll(TexteCode,code);
        
        //Création de la VBox pour tout accueillir (Sauf le bouton retour)
		VBHaut = new VBox();
		VBHaut.setAlignment(Pos.TOP_CENTER);
		VBHaut.setPrefSize(GI.screenBounds.getWidth()*0.8, GI.screenBounds.getHeight());
		VBHaut.setPadding(new Insets(0,0,0,GI.screenBounds.getWidth()*0.2));
		VBHaut.getChildren().addAll(Titre,HBName,HBCode,boutonJouer);
        this.setCenter(VBHaut);
        
		//Création de la Vbox pour accueillir le bouton retour
		VBox VBDroite = new VBox();
		VBDroite.setAlignment(Pos.TOP_RIGHT);
		VBDroite.setPrefSize(GI.screenBounds.getWidth()*0.2,0);
		VBDroite.setPadding(new Insets(GI.screenBounds.getHeight()*0.05,GI.screenBounds.getWidth()*0.03,0,0));
		VBDroite.getChildren().addAll(boutonRetour);
		this.setRight(VBDroite);
			
	
	}

	private void rejoindrePartie(String pseudo, String code) {
		// TODO
		if(code.isBlank() || code.length()>8 || !Pattern.matches("P[0-9]{7}",code)) {
			Label erreur = new Label("Erreur");
			erreur.setTextFill(Color.RED);
			erreur.setFont(Font.font("Comic Sans MS", 20));
			VBHaut.getChildren().add(erreur);
		}
		else {
			GI.afficherEcran(GI.InterfaceMap.get("chargement"));
		}

	}
	
}
