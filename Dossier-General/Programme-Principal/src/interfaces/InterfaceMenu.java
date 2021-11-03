package interfaces;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Systeme;

/**
* @generated
*/
public class InterfaceMenu extends VBox implements UI {
    
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	public InterfaceMenu(GestionnaireInterface gi) { // javaFX elements goes into the class constructor
		super();
		GI = gi;
		Button button1 = new Button("Go to bug hell (no way back for the moment)"); 
		button1.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(2))); // switch Pane visibility
		Button button2 = new Button("Go somewhere else"); 
		button2.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(1))); // switch Pane visibility
		this.getChildren().addAll(button1,button2);
		
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
    public rejoindrePartie() {
        //TODO
    }
    /**
    * @generated
    */
    public jouer() {
        //TODO
    }
}
