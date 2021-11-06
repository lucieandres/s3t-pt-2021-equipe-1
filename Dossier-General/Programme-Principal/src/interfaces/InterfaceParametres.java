package interfaces;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import joueur.Joueur;
import moteur.Systeme;

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
		
		Button buttonBack = new Button("Menu");
		buttonBack.setOnAction(e -> GI.root.getChildren().get(0));
				
		Button buttonGraphics = new Button("Paramètres Graphiques");
		Button buttonMusic = new Button("Paramètres Musicaux");
		Button buttonSound = new Button("Paramètres Sonores");
		Button buttonTheme = new Button("Thème");
		VBox VBTop = new VBox();
		HBox HBLeft = new HBox();
		
		VBTop.setAlignment(Pos.TOP_CENTER);
		VBTop.getChildren().addAll(buttonBack);
		
		
		HBLeft.setAlignment(Pos.CENTER_LEFT);
		HBLeft.getChildren().addAll(buttonGraphics, buttonMusic, buttonSound, buttonTheme);
		
		
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
