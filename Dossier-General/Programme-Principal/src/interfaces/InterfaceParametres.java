package interfaces;

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
 * Cette classe est une interface qui représente les paramètres.
 * 
 *   
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceParametres extends InterfaceBase {
    
//    private Joueur joueur;
    public GestionnaireInterface GI;
    
    Button boutonRetour;
    Button boutonGraphique;
    Button boutonMusique;
    Button boutonSon;
    Button boutonTheme;
    
    Label titre;
    
    HBox HBTop;
    VBox VBRight;
    VBox VBLeft;
    
    Button boutonLangage;
    HBox HBLangue;
    Button boutonFR;
    Button boutonEN;
	/**
     *  Ce constructeur permet de creer tous les élements de l'interface, c'est-a-dire le titre "Paramètres", le bouton "retour", 
     *  le bouton "Paramètre Graphiques", le bouton "Paramètre Musicaux", le bouton "Paramètres Sonores", le bouton "Theme", le bouton "Langue".
     *  
     * 
     * @param gi le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble
     * 
     * @since 1.0
     */
    public InterfaceParametres(GestionnaireInterface gi) { 
		super();
		GI = gi;
		
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
        HBTop = new HBox();
		VBRight = new VBox();
		VBLeft = new VBox();
		
		boutonRetour = new Button("Retour");
		boutonRetour.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		boutonRetour.setFont(Font.font("Comic Sans MS", 20));
		boutonRetour.setPrefWidth(GI.screenBounds.getWidth()*0.08);
		
		boutonGraphique = new Button("Paramètres Graphiques"); // -----------------------------------------------------------------------------------------------------------------
		boutonGraphique.setFont(Font.font("Comic Sans MS", 20));
		boutonGraphique.setPrefWidth(GI.screenBounds.getWidth()*0.156);
		boutonGraphique.setOnAction(e -> graphique());
	
		
		boutonMusique = new Button("Paramètres Musicaux"); // ---------------------------------------------------------------------------------------------------------------------
		boutonMusique.setFont(Font.font("Comic Sans MS", 20));
		boutonMusique.setPrefWidth(GI.screenBounds.getWidth()*0.156);
		
		boutonSon = new Button("Paramètres Sonores"); // --------------------------------------------------------------------------------------------------------------------------
		boutonSon.setFont(Font.font("Comic Sans MS", 20));
		boutonSon.setPrefWidth(GI.screenBounds.getWidth()*0.156);
		
		boutonTheme = new Button("Thème"); // -------------------------------------------------------------------------------------------------------------------------------------
		boutonTheme.setFont(Font.font("Comic Sans MS", 20));
		boutonTheme.setPrefWidth(GI.screenBounds.getWidth()*0.156);
		
		boutonLangage = new Button("Langue"); // ----------------------------------------------------------------------------------------------------------------------------------
		boutonLangage.setFont(Font.font("Comic Sans MS", 20));
		boutonLangage.setPrefWidth(GI.screenBounds.getWidth()*0.156);
		boutonLangage.setOnAction(e -> langue());
		
		titre = new Label("Paramètres"); // ---------------------------------------------------------------------------------------------------------------------------------------
		titre.setFont(Font.font("Comic Sans MS", 40));
		
		//------------------Conteneur titre---------------------------------------------------------
		VBox VBTopCentre = new VBox();
		VBTopCentre.getChildren().add(titre);
		VBTopCentre.setPadding(new Insets(GI.screenBounds.getHeight()*0.018,GI.screenBounds.getWidth()*0.1,0,GI.screenBounds.getWidth()*0.28));

		//------------------Conteneur bouton retour------------------------------------------------
		VBox VBTopDroite = new VBox(boutonRetour);
		VBTopDroite.setPadding(new Insets(GI.screenBounds.getHeight()*0.078,GI.screenBounds.getWidth()*0.02,0,GI.screenBounds.getWidth()*0.48));
		
		//------------------Conteneur bouton quitter------------------------------------------------
		HBox HBTop = new HBox(VBTopCentre, VBTopDroite);
		this.setTop(HBTop);
		
		VBRight.setAlignment(Pos.TOP_RIGHT);
		VBRight.getChildren().add(boutonRetour);
		VBRight.setPadding(new Insets(-40,30,0,0));
		this.setRight(VBRight);
		
		VBLeft.getChildren().addAll(boutonGraphique, boutonMusique, boutonSon, boutonTheme, boutonLangage);
		VBLeft.setAlignment(Pos.TOP_LEFT);
		VBLeft.setMinSize(350, 1000);
		VBLeft.setSpacing(15);
		VBLeft.setPadding(new Insets(250,0,0,40));
		this.setLeft(VBLeft);
	}
    
    public void graphique() {
		VBox ParametresGraphiques = new VBox();
		ParametresGraphiques.setAlignment(Pos.CENTER_LEFT);
		
		Button boutonFenetre = new Button("mode fenêtré");
		boutonFenetre.setFont(Font.font("Comic Sans MS", 20));
		boutonFenetre.setOnAction(e -> GI.MainStage.setFullScreen(!GI.MainStage.isFullScreen()));
		
		ParametresGraphiques.getChildren().add(boutonFenetre);
		this.setCenter(ParametresGraphiques);
    }
    
    public void langue() {
		HBLangue = new HBox();
		HBLangue.setAlignment(Pos.CENTER_LEFT);
		
		boutonFR = new Button("Francais");
		boutonFR.setFont(Font.font("Comic Sans MS", 20));
		boutonFR.setPrefWidth(150);
		
		boutonEN = new Button("English");
		boutonEN.setFont(Font.font("Comic Sans MS", 20));
		boutonEN.setPrefWidth(150);
		
		HBLangue.getChildren().addAll(boutonFR, boutonEN);
		this.setCenter(HBLangue);
    }
    
    public void musique() {
    	
    }
    
    
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
    
    

//    //                          Operations                                  
//    
//    /**
//    * @generated
//    */
//    public activerSon() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public couperSon() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public activerMusique() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public couperMusique() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public changerLangue() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public changerTheme() {
//        //TODO
//    }
    
}
