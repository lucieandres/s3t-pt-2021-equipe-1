package interfaces;

import cartes.CarteInfluence;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import moteur.Data;

/**
 * Cette classe est l'interface du jeu.
 * C'est sur cette interface que toutes actions entre les joueurs et le systeme vont se passer.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceJeu extends InterfaceBase {
    
	/**
     *  Ce constructeur permet de créer tous les éléments de l'interface, c'est-à-dire le bouton pour quitter, le bouton pour voir 
     *  les règles, le bouton pour aller le texte pour voir qui doit jouer, la grille des cartes du plateau et la main du joueur.
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
	
	private double LargeurCote;
    
    public InterfaceJeu(GestionnaireInterface GI) {
    	
    	//taille des cot� proportionnelle � la taille de l'�cran
    	LargeurCote = GI.screenBounds.getWidth()/7;
    	
    	// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,CornerRadii.EMPTY,null)));
    	
        // bouton quitté
        
        Button boutonQuiter = new Button("Quitter");
        boutonQuiter.setFont(Font.font("Comic Sans MS", 20));
        boutonQuiter.setOnAction(e -> Platform.exit());
        
    	// bouton règle 
        
        Button BoutonRegle = new Button("R�gles");  
        BoutonRegle.setFont(Font.font("Comic Sans MS", 20));
        BoutonRegle.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("regles")));
        
        // Bouton option
        
        Button BoutonOption = new Button("Option");
        BoutonOption.setFont(Font.font("Comic Sans MS", 20));
        BoutonOption.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("parametres")));
        
        // met tout le monde dans des boites
        
        HBox HBRegleOption = new HBox(BoutonRegle,BoutonOption);
        HBRegleOption.setSpacing(10);
        
        // cot� droit de l'�cran
        AnchorPane coteDroit= new AnchorPane(boutonQuiter, HBRegleOption); 
        
        //position boutonQuiter
        AnchorPane.setRightAnchor(boutonQuiter,20.0);
        AnchorPane.setTopAnchor(boutonQuiter, 20.0);
        
        //position BoutonRegle et BoutonOption
        AnchorPane.setBottomAnchor(HBRegleOption, 20.0);
        AnchorPane.setRightAnchor(HBRegleOption, 20.0);
        
        // d�limitation de coteDroit
        coteDroit.setPrefSize(LargeurCote, GI.screenBounds.getHeight());
    	this.setRight(coteDroit);
        
        //Cr�ation d'un AnchorPane pour tout recueillir (regle,  ...)
        /*
        AnchorPane anchor= new AnchorPane(); 
        anchor.getChildren().addAll(BouttonRegle,textJoueur,buttonQuit,option);
    	
        // Position bouton r�gle
        BouttonRegle.setPadding(new Insets(50, 100, 50, 100));
        AnchorPane.setLeftAnchor(BouttonRegle, 20.0 );
        AnchorPane.setTopAnchor(BouttonRegle,900.0 );

        // Position text tour joueur
        AnchorPane.setLeftAnchor(textJoueur, 20.0 );
        AnchorPane.setTopAnchor(textJoueur,20.0 ); 

        // Position bouton quitter
        buttonQuit.setPadding(new Insets(25, 50, 25, 50));
        AnchorPane.setLeftAnchor(buttonQuit, 1750.0 );
        AnchorPane.setTopAnchor(buttonQuit,20.0 );

        // Position bouton option
        option.setPadding(new Insets(25, 50, 25, 50));
        AnchorPane.setLeftAnchor(option, 1600.0 );
        AnchorPane.setTopAnchor(option,20.0 );

        this.setBottom(anchor);
        
        */
        
    	
    }
    
	/**
     * Cette méthode permet de dessiner la grille de la partie pour jouer.
     * 
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
    
    public void drawPartie(GestionnaireInterface GI) {
    	
    	VBox v = new VBox();
    	v.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,CornerRadii.EMPTY,null)));
    	v.setAlignment(Pos.TOP_CENTER);
    	v.setPrefSize(GI.screenBounds.getWidth()-LargeurCote*2, GI.screenBounds.getHeight());
    	
    	HBox HC = drawColonne(GI);
    	HBox HM = drawMain(GI.getData());
    	Label TexteJoueur = drawTexteJoueur(GI);
    	
    	//affichage du cot� gauche de l'�cran
    	AnchorPane coteGauche= new AnchorPane(TexteJoueur); 
    	AnchorPane.setTopAnchor(TexteJoueur, 20.0);
    	AnchorPane.setLeftAnchor(TexteJoueur, 20.0);
    	coteGauche.setPrefSize(LargeurCote, GI.screenBounds.getHeight());
    	
    	v.getChildren().add(HC);
    	v.setPadding(new Insets(50,0,50,0));
    	v.getChildren().add(HM);
    	GI.Jeux.setCenter(v);   
    	GI.Jeux.setLeft(coteGauche);
    }
    
    /**
     * Cette méthode permet de dessiner la main du joueur.
     * 
     * 
     * @param data Données actuelles du jeu.
     * 
     * @since 1.0
     */
    
    public HBox drawMain(Data data) { 
        HBox mainJoueur = new HBox();
        mainJoueur.setSpacing(10);
        mainJoueur.setAlignment(Pos.BOTTOM_CENTER);
        
        for(int i = 0; i < data.getMaster().getMain().length ;i++) {
        //for(CarteInfluence x: data.getMaster().getMain()) {
        	SpriteCarteInfluence SPI = new SpriteCarteInfluence(data.getMaster().getMain()[i]);
        	final int j = i;
        	SPI.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> data.getMaster().setCarteSelectionnee(j));
        	//SPI.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(data.getMaster().getMain()[i].getNom()));
        	mainJoueur.getChildren().add(SPI);
        }
    	return mainJoueur;
    }
    
    /**
     * Cette méthode permet de dessiner les colonnes.
     * 
     * 
     * @param data Données actuelles du jeu.
     * 
     * @since 1.0
     */
    
    public HBox drawColonne(GestionnaireInterface GI) { 
    	Data data = GI.getData();
    	HBox Colonnes = new HBox();
    	Colonnes.setPrefHeight(800);
        Colonnes.setSpacing(10);
        Colonnes.setAlignment(Pos.CENTER);
        
        for(int i=0;i<data.getJoueurs().length;i++) {
        	VBox h = new VBox();
        	final int k = i;
        	h.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {try {
				data.jouerCarte(data.getMaster().getCarteSelectionnee(),k);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}});
        	h.addEventFilter(MouseEvent.MOUSE_CLICKED, e  -> {try {
				GI.doitJouer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}});
        	h.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(data.getMaster().getMain()));
        	h.setSpacing(10);
        	h.getChildren().add(new SpriteCarteObjectif(data.getPlateau().getColonnes()[i].getCarteObjectif())); // carte objectif
        	for(int j=0;j < data.getPlateau().getColonnes()[i].getCartesInfluences().length;j++) { // carte influences
        		h.getChildren().add(new SpriteCarteInfluence(data.getPlateau().getColonnes()[i].getCartesInfluences()[j]));
        	}
        Colonnes.getChildren().add(h);
        }
        return Colonnes;
    }
    
    /**
     * Cette methode permet d'afficher quel est le joueur en train de jouer
     * 
     * 
     * @param data Données actuelles du jeu.
     * 
     * @since 1.0
     */
    
    public Label drawTexteJoueur(GestionnaireInterface GI) {
    	
    	String joueur = GI.getData().getJoueurs()[GI.getData().getCurrentJoueur()].getPseudo();
    	String prochainJoueur;
    	
    	if(GI.getData().getCurrentJoueur() == GI.getData().getJoueurs().length-1) {
    		prochainJoueur = GI.getData().getJoueurs()[0].getPseudo();
    	} else {
    		prochainJoueur = GI.getData().getJoueurs()[GI.getData().getCurrentJoueur()+1].getPseudo();
    	}
    	
    	Label textJoueur = new Label("C'est le tour de : "+ joueur +"\nProchain joueur : "+ prochainJoueur );
    	textJoueur.setFont(Font.font("Comic Sans MS", 15));
        //textJoueur.setMaxWidth(150);
        textJoueur.setWrapText(true);
		return textJoueur;
    	
    }
    //                          Operations                                  
    

//    /**
//    * @generated
//    */
//    public void placerCarte() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public void defausserCarte() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public void piocherCarte() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public void renouvelerReserve() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public void quitterJeu() {
//        //TODO
//    }
    
}
