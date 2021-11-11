package interfaces;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import joueur.Joueur;
import moteur.Systeme;
import joueur.Joueur;

/**
* @generated
*/
public class InterfaceParametres extends BorderPane implements UI {
    
    private Systeme systeme;
    private Joueur joueur;
    public GestionnaireInterface GI;
    
    public InterfaceParametres(GestionnaireInterface gi) { 
		super();
		GI = gi;
		
		VBox VBTop = new VBox();
		VBox VBLeft = new VBox();
		Button buttonBack = new Button("Retour");

		buttonBack.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		
		VBTop.setAlignment(Pos.TOP_CENTER);
		VBTop.getChildren().add(buttonBack);
		
		
		Button buttonGraphics = new Button("Parametres Graphiques");
		Button buttonMusic = new Button("Parametres Musicaux");
		Button buttonSound = new Button("Parametres Sonores");
		Button buttonTheme = new Button("Theme");
		
		VBLeft.setAlignment(Pos.CENTER_LEFT);
		VBLeft.getChildren().addAll(buttonGraphics, buttonMusic, buttonSound, buttonTheme);
		
		this.setLeft(VBLeft);
		this.setCenter(VBTop);
    }
    
    
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
    public activerSon() {
        //TODO
    }
    /**
    * @generated
    */
    public couperSon() {
        //TODO
    }
    /**
    * @generated
    */
    public activerMusique() {
        //TODO
    }
    /**
    * @generated
    */
    public couperMusique() {
        //TODO
    }
    /**
    * @generated
    */
    public changerLangue() {
        //TODO
    }
    /**
    * @generated
    */
    public changerTheme() {
        //TODO
    }
    
}
