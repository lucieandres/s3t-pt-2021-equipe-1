package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import joueur.Joueur;
import joueur.Joueur;

/**
* @generated
*/
public class InterfaceParametres extends BorderPane implements UI {
    
    private Joueur joueur;
    public GestionnaireInterface GI;
    
    public InterfaceParametres(GestionnaireInterface gi) { 
		super();
		GI = gi;
		
		HBox HBTop = new HBox();
		VBox VBRight = new VBox();
		VBox VBLeft = new VBox();
		
		Button buttonBack = new Button("Retour");
		buttonBack.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		buttonBack.setFont(Font.font("Comic Sans MS", 20));
		buttonBack.setPrefWidth(150);
		
		Button buttonGraphics = new Button("Paramètres Graphiques");
		buttonGraphics.setFont(Font.font("Comic Sans MS", 20));
		buttonGraphics.setPrefWidth(300);
		
		Button buttonMusic = new Button("Paramètres Musicaux");
		buttonMusic.setFont(Font.font("Comic Sans MS", 20));
		buttonMusic.setPrefWidth(300);
		
		Button buttonSound = new Button("Paramètres Sonores");
		buttonSound.setFont(Font.font("Comic Sans MS", 20));
		buttonSound.setPrefWidth(300);
		
		Button buttonTheme = new Button("Thème");
		buttonTheme.setFont(Font.font("Comic Sans MS", 20));
		buttonTheme.setPrefWidth(300);
		
		Button buttonLanguage = new Button("Langue");
		buttonLanguage.setFont(Font.font("Comic Sans MS", 20));
		buttonLanguage.setPrefWidth(300);
		
		Label titre = new Label("Paramètres");
		titre.setFont(Font.font("Comic Sans MS", 40));
		
		HBTop.getChildren().add(titre);
		HBTop.setAlignment(Pos.TOP_CENTER);
		HBTop.setMinSize(1920, 10);
		HBTop.setPadding(new Insets(20,0,0,0));
		this.setTop(HBTop);
		
		VBRight.setAlignment(Pos.TOP_RIGHT);
		VBRight.getChildren().add(buttonBack);
		VBRight.setPadding(new Insets(-40,30,0,0));
		this.setRight(VBRight);
		
		VBLeft.getChildren().addAll(buttonGraphics, buttonMusic, buttonSound, buttonTheme, buttonLanguage);
		VBLeft.setAlignment(Pos.TOP_LEFT);
		VBLeft.setMinSize(350, 1000);
		VBLeft.setSpacing(15);
		VBLeft.setPadding(new Insets(250,0,0,40));
		this.setLeft(VBLeft);
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
