package interfaces;

import javafx.application.Platform;
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
 * Cette classe est une interface qui represente le menu principal.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceMenu extends BorderPane implements UI {
    
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	HBox HBTop;
	VBox VBRight;
	VBox VBLeft;
	
	Button buttonSettings;
	Button buttonRules;
	Button buttonPlay;
	Button buttonQuit;
	Label titre;
	
	/**
     *  Ce constructeur permet de creer tous les elements de l'interface, c'est-a-dire le titre du jeu,
     *  le bouton qui va rediriger vers les parametres, le bouton qui va rediriger vers les regles, le bouton qui va rediriger vers la creation d'une partie et pour finir le bouton "quitter" qui va permettre de sortir du jeu.
     * 
     * @param gi le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble
     * 
     * @since 1.0
     */
	public InterfaceMenu(GestionnaireInterface gi) { // javaFX elements goes into the class constructor
		super();
		GI = gi;
		
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
		buttonSettings = new Button("Paramètres"); 
		buttonSettings.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("parametres"))); // switch Pane visibility
		buttonSettings.setPrefWidth(150);
		buttonSettings.setFont(Font.font("Comic Sans MS", 20));
		
		buttonRules = new Button("Règles");
		buttonRules.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("regles"))); // switch Pane visibility
		buttonRules.setPrefWidth(150);
		buttonRules.setFont(Font.font("Comic Sans MS", 20));
		
		buttonPlay = new Button("Jouer");
		buttonPlay.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("creerPartie"))); // switch Pane visibility
		buttonPlay.setPrefWidth(150);
		buttonPlay.setFont(Font.font("Comic Sans MS", 20));
		
		buttonQuit = new Button("Quitter");
		buttonQuit.setOnAction(e -> Platform.exit());
		buttonQuit.setPrefWidth(150);
		buttonQuit.setFont(Font.font("Comic Sans MS", 20));
		
		titre = new Label("De Cape et D'Epée");
		titre.setFont(Font.font("Comic Sans MS", 40));
		
		this.setRight(buttonQuit);
		
		HBTop = new HBox();
		HBTop.getChildren().add(titre);
		HBTop.setAlignment(Pos.TOP_CENTER);
		HBTop.setMinSize(1920,10);
		HBTop.setPadding(new Insets(20,0,0,0));
		this.setTop(HBTop);
		
		
		VBRight = new VBox();
		VBRight.getChildren().add(buttonQuit);
		VBRight.setAlignment(Pos.TOP_RIGHT);
		this.setRight(VBRight);
		VBRight.setPadding(new Insets(-40,30,0,0));
		
		VBLeft = new VBox();
		VBLeft.getChildren().addAll(buttonSettings, buttonRules, buttonPlay);
		VBLeft.setAlignment(Pos.TOP_LEFT);
		VBLeft.setMinSize(350, 1000);
		this.setLeft(VBLeft);
		VBLeft.setSpacing(25);
		VBLeft.setPadding(new Insets(250,0,0,80));

	}
	
	
    
//    /**
//    * attribut joueur de la classe Joueur, package joueur
//    */
//    private Joueur joueur;
//    
//    
//
//    
//    /**
//    * affiche joueur
//    */
//    public Joueur getJoueur() {
//        return this.joueur;
//    }
//    
//    /**
//    * modifie joueur
//    */
//    public void setJoueur(Joueur joueur) {
//        this.joueur = joueur;
//    }
//    
//    
//
//    //                          Operations                                  
//    
//    /**
//    * @generated
//    */
//    public void rejoindrePartie() {
//        //TODO
//    	//Methode de modif du JSON dans joueur (position provisoire)
//    }
//    /**
//    * @generated
//    */
//    public void jouer() {
//        //TODO
//    }
}
