package interfaces;

import java.awt.BorderLayout;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import moteur.Systeme;
import joueur.Joueur;

/**
* @generated
*/
public class InterfaceMenu extends BorderPane implements UI {
    
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	Button buttonSettings;
	Button buttonRules;
	Button buttonPlay;
	Button buttonQuit;
	Label titre;
	
	public InterfaceMenu(GestionnaireInterface gi) { // javaFX elements goes into the class constructor
		super();
		GI = gi;
		
		// input pour pseudo
		buttonSettings = new Button("Parametres"); 
		buttonSettings.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(1))); // switch Pane visibility
		buttonSettings.setPrefWidth(150);
		buttonSettings.setFont(Font.font("Comic Sans MS", 20));
		
		buttonRules = new Button("Regles");
		buttonRules.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(5))); // switch Pane visibility
		buttonRules.setPrefWidth(150);
		buttonRules.setFont(Font.font("Comic Sans MS", 20));
		
		buttonPlay = new Button("Jouer");
		buttonPlay.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(2))); // switch Pane visibility
		buttonPlay.setPrefWidth(150);
		buttonPlay.setFont(Font.font("Comic Sans MS", 20));
		// ^ doit instancier un joueur avec en parametre la valeur de l'input
		
		buttonQuit = new Button("Quitter");
		buttonQuit.setOnAction(e -> Platform.exit());
		buttonQuit.setPrefWidth(150);
		buttonQuit.setFont(Font.font("Comic Sans MS", 20));
		
		titre = new Label("De Cape et D'Epée");
		titre.setFont(Font.font("Comic Sans MS", 30));
		
		this.setRight(buttonQuit);
		
		HBox HBTop = new HBox();
		HBTop.getChildren().add(titre);
		HBTop.setAlignment(Pos.TOP_CENTER);
		HBTop.setMinSize(1920,10);
		this.setTop(HBTop);
		HBTop.setPadding(new Insets(20,0,0,0));
		
		VBox VBRight = new VBox();
		VBRight.getChildren().add(buttonQuit);
		VBRight.setAlignment(Pos.TOP_RIGHT);
		this.setRight(VBRight);
		VBRight.setPadding(new Insets(-40,30,0,0));
		
		VBox VBLeft = new VBox();
		VBLeft.getChildren().addAll(buttonSettings, buttonRules, buttonPlay);
		VBLeft.setAlignment(Pos.TOP_LEFT);
		VBLeft.setMinSize(350, 780);
		this.setLeft(VBLeft);
		VBLeft.setSpacing(25);
		VBLeft.setPadding(new Insets(250,0,0,80));

		
	}
	
	
    /**
    * attribut systeme de la classe Systeme, package moteur
    */
    private Systeme systeme;
    
    /**
    * attribut joueur de la classe Joueur, package joueur
    */
    private Joueur joueur;
    
    
    
    /**
    * affiche systeme
    */
    public Systeme getSysteme() {
        return this.systeme;
    }
    
    /**
    * modifie systeme
    */
    public void setSysteme(Systeme systeme) {
        this.systeme = systeme;
    }
    
    /**
    * affiche joueur
    */
    public Joueur getJoueur() {
        return this.joueur;
    }
    
    /**
    * modifie joueur
    */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public void rejoindrePartie() {
        //TODO
    	//Methode de modif du JSON dans joueur (position provisoire)
    }
    /**
    * @generated
    */
    public void jouer() {
        //TODO
    }
}
