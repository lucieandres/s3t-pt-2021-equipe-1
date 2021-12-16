package pp;

import interfaces.SpriteCarteInfluence;
import interfaces.SpriteCarteObjectif;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import moteur.Data;

/**
 * Cette classe est l'interface du plateau.
 * C'est sur cette interface que tout les joueurs autour du jeu pourront voir les actions d'un joueur entrain de jouer en temps réel.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class InterfacePlateau extends InterfaceBase {
	
	/**
     *  Ce constructeur permet de créer tous les éléments de l'interface, c'est-à-dire le texte pour voir qui doit jouer et la grille des cartes du plateau.
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
	
	private double LargeurCote;
	
	 public InterfacePlateau(GestionnaireInterfacePP GI) {
	    	this.dessineInterface(GI);
	    }
	 
	 public void drawPartie(GestionnaireInterfacePP GI) {
	    	
	    	VBox v = new VBox();
	    	v.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,CornerRadii.EMPTY,null)));
	    	v.setAlignment(Pos.TOP_CENTER);
	    	v.setPrefSize(GI.screenBounds.getWidth()-LargeurCote*2, GI.screenBounds.getHeight());
	    	
	    	HBox HC = drawColonne(GI);
	    	Label TexteJoueur = drawTexteJoueur(GI);
	    	
	    	//affichage du coté gauche de l'écran
	    	
	    	AnchorPane coteGauche= new AnchorPane(TexteJoueur); 
	    	AnchorPane.setTopAnchor(TexteJoueur, 20.0);
	    	AnchorPane.setLeftAnchor(TexteJoueur, 20.0);
	    	coteGauche.setPrefSize(LargeurCote, GI.screenBounds.getHeight());
	    	
	    	v.getChildren().add(HC);
	    	v.setPadding(new Insets(50,0,50,0));
	    	this.setCenter(v);   
	    	this.setLeft(coteGauche);
	    }
	 
	 /**
	     * Cette méthode permet de dessiner les colonnes.
	     * 
	     * 
	     * @param data Données actuelles du jeu.
	     * 
	     * @since 1.0
	     */
	    
	    public HBox drawColonne(GestionnaireInterfacePP gI) { 
	    	Data data = gI.getData();
	    	HBox Colonnes = new HBox();
	    	Colonnes.setPrefHeight(800);
	        Colonnes.setSpacing(10);
	        Colonnes.setAlignment(Pos.CENTER);
	        
	        for(int i=0;i<data.getJoueurs().length;i++) {
	        	VBox h = new VBox();
	        	h.setSpacing(10);
	        	h.getChildren().add(new SpriteCarteObjectifPP(data.getPlateau().getColonnes()[i].getCarteObjectif())); // carte objectif
	        	for(int j=0;j < data.getPlateau().getColonnes()[i].getCartesInfluences().length;j++) { // carte influences
	        		h.getChildren().add(new SpriteCarteInfluencePP(data.getPlateau().getColonnes()[i].getCartesInfluences()[j]));
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
	    
	    public Label drawTexteJoueur(GestionnaireInterfacePP GI) {
	    	
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

		@Override
		public void dessineInterface(GestionnaireInterfacePP GI) {
			
	    	//taille des cotés proportionnelle à la taille de l'écran
	    	LargeurCote = GI.screenBounds.getWidth()/7;
	    	
	    	// fond de jeu
	        this.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,CornerRadii.EMPTY,null)));
		}
}
