package interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cartes.CarteInfluence;
import javafx.animation.FadeTransition;
import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.input.DragEvent;
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
import joueur.Joueur;
import moteur.Data;

/**
 * Cette classe est l'interface du jeu.
 * C'est sur cette interface que toutes actions entre les joueurs et le systeme vont se passer.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceJeuPlateau extends InterfaceBase {
    
	/**
     *  Ce constructeur permet de créer tous les éléments de l'interface, c'est-à-dire le bouton pour quitter, le bouton pour voir 
     *  les règles, le bouton pour aller le texte pour voir qui doit jouer, la grille des cartes du plateau et la main du joueur.
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
	
	private double LargeurCote;

	
    public InterfaceJeuPlateau(GestionnaireInterface GI) {
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
    	LargeurCote = GI.screenBounds.getWidth()/5;
    	
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
    	Label TexteJoueur = drawTexteJoueur(GI);
    	Label TexteScore = drawScore(GI);
    	
    	//affichage du cot� gauche de l'�cran
    	AnchorPane coteGauche= new AnchorPane(TexteJoueur,TexteScore); 
    	
    	AnchorPane.setTopAnchor(TexteJoueur, 20.0);
    	AnchorPane.setLeftAnchor(TexteJoueur, 20.0);
    	
    	AnchorPane.setTopAnchor(TexteScore,100.0);
    	AnchorPane.setLeftAnchor(TexteScore,20.0);
    	
    	coteGauche.setPrefSize(LargeurCote, GI.screenBounds.getHeight());
    	
    	v.getChildren().add(HC);
    	GI.Jeux.setCenter(null);
    	GI.Jeux.setCenter(v);
    	GI.Jeux.setLeft(coteGauche);
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
        	
        	HCarte.setSpacing(-80);
        	h.setSpacing(10);
        	
        	SpriteCarteObjectif SpriteCO = new SpriteCarteObjectif(data.getPlateau().getColonnes()[i].getCarteObjectif(), null, GI);
        	h.getChildren().add(SpriteCO); // carte objectif
        	
        	for(int j=0;j < data.getPlateau().getColonnes()[i].getCartesInfluences().length;j++) { // carte influences
        		if(data.getPlateau().getColonnes()[i].getCartesInfluences()[j] != null) {
	        		SpriteCarteInfluence SPI = new SpriteCarteInfluence(data.getPlateau().getColonnes()[i].getCartesInfluences()[j], GI);
	        		HCarte.getChildren().add(SPI);
        		}
        	}
        	h.getChildren().add(HCarte);
        Colonnes.getChildren().add(h);
        }
        return Colonnes;
    }
    
    /**
     * Cette methode permet d'afficher quel est le joueur en train de jouer
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
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
    
    /**
     * Cette methode permet d'afficher le score des joueurs de la partie du plateau
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
    
    public Label drawScore(GestionnaireInterface GI) {
    	
    	Label l = new Label();
    	l.setFont(Font.font("Comic Sans MS", 15));
    	
    	Map<String, Integer> score = new HashMap<String, Integer>();
    	for(Joueur j : GI.getData().getJoueurs()) {
    		score.put(j.getPseudo(), j.getScore());
    	}
    	
    	List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(score.entrySet());  
    	
    	Collections.sort(list, new Comparator<Entry<String, Integer>>() {  
	    	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	    		return o2.getValue().compareTo(o1.getValue());
	    	}  
    	});  
    	
    	for(int i = 0; i<score.size(); i++) {
    		l.setText(l.getText()+list.get(i).getKey()+" : "+list.get(i).getValue()+"\n");
    	}
    	
    	return l;
    }
}
