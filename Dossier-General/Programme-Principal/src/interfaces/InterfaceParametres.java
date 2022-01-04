package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
		this.dessineInterface(GI);
    }	
	
    /**
     *  Dessine l'interface.
     * 
     * @param gi le gestionnaire d'interface permettra de dessiner l'interface dans la langue séléctionné.
     * 
     * @since 1.0
     */
    
	public void dessineInterface(GestionnaireInterface gi) {

		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
        HBTop = new HBox();
		VBRight = new VBox();
		VBLeft = new VBox();
		
		boutonRetour = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.retour"));
		boutonRetour.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		boutonRetour.setFont(Font.font("Comic Sans MS", 20));
		boutonRetour.setPrefWidth(GI.screenBounds.getWidth()*0.08);
		
		boutonGraphique = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.graphique")); // -----------------------------------------------------------------------------------------------------------------
		boutonGraphique.setFont(Font.font("Comic Sans MS", 20));
		boutonGraphique.setPrefWidth(GI.screenBounds.getWidth()*0.156);
		boutonGraphique.setOnAction(e -> graphique());
		
		boutonSon = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.son")); // --------------------------------------------------------------------------------------------------------------------------
		boutonSon.setFont(Font.font("Comic Sans MS", 20));
		boutonSon.setPrefWidth(GI.screenBounds.getWidth()*0.156);
		
		boutonTheme = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.theme")); // -------------------------------------------------------------------------------------------------------------------------------------
		boutonTheme.setFont(Font.font("Comic Sans MS", 20));
		boutonTheme.setPrefWidth(GI.screenBounds.getWidth()*0.156);
		
		boutonLangage = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.langue")); // ----------------------------------------------------------------------------------------------------------------------------------
		boutonLangage.setFont(Font.font("Comic Sans MS", 20));
		boutonLangage.setPrefWidth(GI.screenBounds.getWidth()*0.156);
		boutonLangage.setOnAction(e -> langue());
		
		titre = new Label(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.parametre")); // ---------------------------------------------------------------------------------------------------------------------------------------
		titre.setStyle("-fx-font: normal bold 10em 'Pristina' ");
		
		//------------------Conteneur titre---------------------------------------------------------
		VBox VBTopCentre = new VBox();
		VBTopCentre.getChildren().add(titre);
		VBTopCentre.setPadding(new Insets(-GI.screenBounds.getHeight()*0.018,0,0,GI.screenBounds.getWidth()*0.28));

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
		
		VBLeft.getChildren().addAll(boutonGraphique, boutonSon, boutonTheme, boutonLangage);
		VBLeft.setAlignment(Pos.TOP_LEFT);
		VBLeft.setMinSize(350, 1000);
		VBLeft.setSpacing(15);
		VBLeft.setPadding(new Insets(250,0,0,40));
		this.setLeft(VBLeft);
	}
    
	/**
     * Affiche le bloc des paramètres graphiques.
     * 
     * @since 1.0
     */
    
    public void graphique() {
		VBox ParametresGraphiques = new VBox();
		ParametresGraphiques.setAlignment(Pos.CENTER_LEFT);
		
		Button boutonFenetre = new Button("mode fenêtré");
		boutonFenetre.setFont(Font.font("Comic Sans MS", 20));
		boutonFenetre.setOnAction(e -> GI.MainStage.setFullScreen(!GI.MainStage.isFullScreen()));
		
		ParametresGraphiques.getChildren().add(boutonFenetre);
		this.setCenter(ParametresGraphiques);
    }
    
    /**
     * Affiche le bloc des paramètres linguistiques.
     * 
     * @since 1.0
     */
    
    public void langue() {
		HBLangue = new HBox();
		HBLangue.setAlignment(Pos.CENTER_LEFT);
		
		boutonFR = new Button("Français");
		boutonFR.setFont(Font.font("Comic Sans MS", 20));
		boutonFR.setOnAction(e -> francais());
		boutonFR.setPrefWidth(150);
		
		boutonEN = new Button("English");
		boutonEN.setFont(Font.font("Comic Sans MS", 20));
		boutonEN.setOnAction(e -> english());
		boutonEN.setPrefWidth(150);
		
		HBLangue.getChildren().addAll(boutonFR, boutonEN);
		this.setCenter(HBLangue);
    }
    
    /**
     * Méthode qui définit la langue du jeu en français.
     * 
     * @since 1.0
     */    
    
    public void francais() {
    	if(this.GI.langueSelectionne!="francais") {
    		this.GI.langueSelectionne = "francais";
    		for(InterfaceBase i : GI.InterfaceBaseList)
    			i.dessineInterface(GI);
    	}
    }
    
    /**
     * Méthode qui définit la langue du jeu en anglais.
     * 
     * @since 1.0
     */
    
    public void english() {
    	if(this.GI.langueSelectionne!="english") {
    		this.GI.langueSelectionne = "english";
    		for(InterfaceBase i : GI.InterfaceBaseList)
    			i.dessineInterface(GI);
    	}
    }
    /**
     * Affiche le bloc des paramètres sonores.
     * 
     * @since 1.0
     */
    
    public void son() {
    	/*
    	  VBox VBMusique = new VBox();
    	  VBMusique.setAlignement(Pos.CENTER_LEFT);
    	  
    	  Label labelMusique = new Label("Musique");
    	  Slider volumeMusique = new Slider();
    	  Label labelEffet = new Label("Effets sonores");
    	  Slider volumeEffet = new Slider();
    	  
    	  VBMusique.getChildren().addAll(labelMusique, volumeMusique, labelEffet, volumeEffet);
    	  this.setCenter(VBMusique);*/
    }
    
    /**
     * Affiche le bloc du choix des thèmes de cartes.
     * 
     * @since 1.0
     */
    
    public void theme() {
    	/*
    	  HBox HBTheme = new HBox();
    	  HBTheme.setAlignment(Pos.CENTER_LEFT);
    	  
    	  Button boutonThemeModif = new Button("Theme Modifié");
    	  boutonThemeModif.setFont(Font.font("Comic Sans MS", 20);
    	  boutonThemeModif.setPrefWidth(150);
    	  
    	  Button boutonThemeBase = new Button("Theme de base");
    	  boutonThemeBase.setFont(Font.font("Comic Sans MS", 20);
    	  boutonThemeBase.setPrefWidth(150);
    	  
    	  HBTheme.getChildren().addAll(boutonThmeBase, boutonThemeModif);
    	  this.setCenter(HBTheme);*/
    }
    

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
    
//    /**
//    * @generated
//    */
//    public changerTheme() {
//        //TODO
//    }
}

