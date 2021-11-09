package interfaces;

import java.awt.BorderLayout;

import javafx.application.Platform;
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
		buttonSettings = new Button("Paramètres"); 
		buttonSettings.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(1))); // switch Pane visibility
		
		buttonRules = new Button("Règles");
		buttonRules.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(5))); // switch Pane visibility
		
		buttonPlay = new Button("Jouer");
		buttonPlay.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(2))); // switch Pane visibility
		// ^ doit instancier un joueur avec en parametre la valeur de l'input
		
		buttonQuit = new Button("Quitter");
		buttonQuit.setOnAction(e -> Platform.exit());
		buttonQuit.setAlignment(Pos.BASELINE_LEFT);
		
		titre = new Label("De Cape et D'Epée");
		
		
		HBox HBTop = new HBox();
		HBTop.getChildren().addAll(titre, buttonQuit);
		HBTop.setAlignment(Pos.TOP_CENTER);
		
		
		
		VBox VBLeft = new VBox();
		VBLeft.getChildren().addAll(buttonSettings, buttonRules, buttonPlay);
		VBLeft.setAlignment(Pos.CENTER_LEFT);
		this.setCenter(VBLeft);

		
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
