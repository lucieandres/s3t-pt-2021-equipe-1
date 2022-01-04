package interfaces;

import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
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
    	dessineInterface(GI);
    }
    
    /**
     *  Dessine l'interface.
     * 
     * @param gi le gestionnaire d'interface permettra de dessiner l'interface dans la langue séléctionné.
     * 
     * @since 1.0
     */
    
	public void dessineInterface(GestionnaireInterface GI) {
		//taille des cotés proportionnelle à la taille de l'écran
    	LargeurCote = GI.screenBounds.getWidth()/7;
    	
    	// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,CornerRadii.EMPTY,null)));
    	
        // bouton quitté
        
        Button boutonQuitter = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.quitter"));
        boutonQuitter.setFont(Font.font("Comic Sans MS", 20));
        boutonQuitter.setOnAction(e -> Platform.exit());
        
    	// bouton règle 
        
        Button BoutonRegle = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.regle"));  
        BoutonRegle.setFont(Font.font("Comic Sans MS", 20));
        BoutonRegle.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("regles")));
        
        // Bouton option
        
        Button BoutonOption = new Button(GI.texteLangue.get(GI.langueSelectionne).getProperty("bouton.parametres"));
        BoutonOption.setFont(Font.font("Comic Sans MS", 20));
        BoutonOption.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("parametres")));
        
        // met tout le monde dans des boites
        
        HBox HBRegleOption = new HBox(BoutonRegle,BoutonOption);
        HBRegleOption.setSpacing(10);
        
        // coté droit de l'écran
        AnchorPane coteDroit= new AnchorPane(boutonQuitter, HBRegleOption); 
        
        //position boutonQuiter
        AnchorPane.setRightAnchor(boutonQuitter,20.0);
        AnchorPane.setTopAnchor(boutonQuitter, 20.0);
        
        //position BoutonRegle et BoutonOption
        AnchorPane.setBottomAnchor(HBRegleOption, 20.0);
        AnchorPane.setRightAnchor(HBRegleOption, 20.0);
        
        // délimitation de coteDroit
        coteDroit.setPrefSize(LargeurCote, GI.screenBounds.getHeight());
    	this.setRight(coteDroit);
           	
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
    	HBox HM = drawMain(GI);
    	Label TexteJoueur = drawTexteJoueur(GI);
    	
    	//affichage du cot� gauche de l'�cran
    	AnchorPane coteGauche= new AnchorPane(TexteJoueur); 
    	AnchorPane.setTopAnchor(TexteJoueur, 20.0);
    	AnchorPane.setLeftAnchor(TexteJoueur, 20.0);
    	coteGauche.setPrefSize(LargeurCote, GI.screenBounds.getHeight());
    	
    	v.getChildren().add(HC);
    	v.setPadding(new Insets(50,0,50,0));
    	v.getChildren().add(HM);
    	GI.Jeux.setCenter(null);
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
    
    public HBox drawMain(GestionnaireInterface GI) { 
    	Data data = GI.getData();
        HBox mainJoueur = new HBox();
        mainJoueur.setSpacing(10);
        mainJoueur.setAlignment(Pos.BOTTOM_CENTER);
        
        for(int i = 0; i < data.getMaster().getMain().length ;i++) {
        //for(CarteInfluence x: data.getMaster().getMain()) {
        	SpriteCarteInfluence SPI = new SpriteCarteInfluence(data.getMaster().getMain()[i],GI);
        	final int j = i;
        	//SPI.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> data.getMaster().setCarteSelectionnee(j));
        	
        	SPI.setOnMouseDragged(new EventHandler<MouseEvent>() {
            	@Override public void handle(MouseEvent mouseEvent) {
            		data.getMaster().setCarteSelectionnee(j);
            		
            		double easing = 0.25;
            		double targetX = mouseEvent.getX() + SPI.getTranslateX() - SPI.getBoundsInParent().getWidth()/2;
            		double dx = targetX - SPI.translateX;
            		SPI.translateX += dx * easing;
            		
            		double targetY = mouseEvent.getY() + SPI.getTranslateY() - SPI.getBoundsInParent().getHeight()/2;
            		double dy = targetY - SPI.translateY;
            		SPI.translateY += dy * easing;
            		
            		SPI.setTranslateX(SPI.translateX);
            		SPI.setTranslateY(SPI.translateY);
            		
            		//SPI.setTranslateX(mouseEvent.getX() + SPI.getTranslateX() - SPI.getBoundsInParent().getWidth()/2);
            		//SPI.setTranslateY(mouseEvent.getY() + SPI.getTranslateY() - SPI.getBoundsInParent().getHeight()/2);
      		  		}
            	});
        	
        	SPI.setOnMouseReleased(new EventHandler<MouseEvent>() {
            	@Override public void handle(MouseEvent mouseEvent) {
            		
            		TranslateTransition translate = new TranslateTransition();  
            		translate.setDuration(Duration.millis(200)); 
            		translate.setCycleCount(1);
            		translate.setInterpolator(Interpolator.EASE_BOTH);
            		
            		translate.setFromX(SPI.translateX);
            		translate.setFromY(SPI.translateY);
            		
            		translate.setToX(0);
            		translate.setToY(0);
            		
            		translate.setNode(SPI);
            		translate.play();
            		
            		translate.statusProperty().addListener(new ChangeListener<Status>() {
        		        @Override
        		        public void changed(ObservableValue<? extends Status> observableValue, Status oldValue, Status newValue) {
        		              if(newValue==Status.STOPPED){
        		            	  SPI.translateX = 0;
        		            	  SPI.translateY = 0;
        		            	  data.getMaster().setCarteSelectionnee(-1); /* /!\ à surveiller /!\ */
        		              }            
        		        }
        		    });
      		  		}
            	});
        	
        	//SPI.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(data.getMaster().getMain()[i].getNom()));
        	mainJoueur.getChildren().add(SPI);
        }
    	return mainJoueur;
    }
    
    /**
     * Cette méthode permet de dessiner les colonnes.
     * 
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
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
        	VBox HCarte = new VBox();
        	
        	//h.setBackground(new Background(new BackgroundFill(new Color(0,0,0,1), null, null)));
        	
        	final int k = i;
        	
        	h.setOnMouseEntered(new EventHandler<MouseEvent>() {
            	@Override public void handle(MouseEvent mouseEvent) {
            		try {
						data.jouerCarte(data.getMaster().getCarteSelectionnee(),k);
						GI.doitJouer();
						System.out.println(data.getMaster().getMain());
					} catch (Exception e) {
						e.printStackTrace();
					}
      		  	}
            });
        	
        	/*
        	h.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {try {
				data.jouerCarte(data.getMaster().getCarteSelectionnee(),k);
			} catch (Exception e1) {
				e1.printStackTrace();
			}});
        	h.addEventFilter(MouseEvent.MOUSE_CLICKED, e  -> {try {
				GI.doitJouer();
			} catch (Exception e1) {
				e1.printStackTrace();
			}});
        	h.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(data.getMaster().getMain()));
        	*/
        	HCarte.setSpacing(-80);
        	h.setSpacing(10);
        	h.getChildren().add(new SpriteCarteObjectif(data.getPlateau().getColonnes()[i].getCarteObjectif(), GI)); // carte objectif
        	for(int j=0;j < data.getPlateau().getColonnes()[i].getCartesInfluences().length;j++) { // carte influences
        		if(data.getPlateau().getColonnes()[i].getCartesInfluences()[j] != null)
        			HCarte.getChildren().add(new SpriteCarteInfluence(data.getPlateau().getColonnes()[i].getCartesInfluences()[j], GI));
        	}
        	h.getChildren().add(HCarte);
        Colonnes.getChildren().add(h);
        }
        return Colonnes;
    }
    
    /**
     * Cette methode permet d'afficher quel est le joueur en train de jouer
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
    	
    	Label textJoueur = new Label(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.tour") + joueur +"\n"+ GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.prochainTour") + prochainJoueur );
    	textJoueur.setFont(Font.font("Comic Sans MS", 15));
        //textJoueur.setMaxWidth(150);
        textJoueur.setWrapText(true);
		return textJoueur;
    	
    }
}
