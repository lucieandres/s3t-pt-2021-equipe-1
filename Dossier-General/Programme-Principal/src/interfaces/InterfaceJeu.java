package interfaces;

import cartes.CarteInfluence;
import elements.Plateau;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import moteur.Data;
import joueur.Joueur;

/**
* @generated
*/
public class InterfaceJeu extends BorderPane implements UI {
    
    /**
    * attribut joueur de la classe Joueur, package joueur
    */
    
    public InterfaceJeu(GestionnaireInterface GI) {
    	
    	//    boutton règle 
        
        Button BouttonRegle = new Button("R�gles");        
        BouttonRegle.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("regles")));
        
        //    text joueur qui joue
        
        Label textJoueur = new Label("C'est le joueur x qui joue\nPochain joueur : joueur x");
                
        textJoueur.setMaxWidth(150);
        textJoueur.setWrapText(true);
        
        //    boutton quitté
        
        Button buttonQuit = new Button("Quitter");
        buttonQuit.setOnAction(e -> Platform.exit());
        
        //    Boutton option
        
        Button option = new Button("Option");
        option.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("parametres")));
        
        //Cr�ation d'un AnchorPane pour tout recueillir (regle,  ...)
        
        AnchorPane anchor= new AnchorPane(); 
        anchor.getChildren().addAll(BouttonRegle,textJoueur,buttonQuit,option);
    	
        // Position boutton r�gle
        BouttonRegle.setPadding(new Insets(50, 100, 50, 100));
        AnchorPane.setLeftAnchor(BouttonRegle, 20.0 );
        AnchorPane.setTopAnchor(BouttonRegle,900.0 );

        // Position text tour joueur
        AnchorPane.setLeftAnchor(textJoueur, 20.0 );
        AnchorPane.setTopAnchor(textJoueur,20.0 ); 

        // Position boutton quitter
        buttonQuit.setPadding(new Insets(25, 50, 25, 50));
        AnchorPane.setLeftAnchor(buttonQuit, 1750.0 );
        AnchorPane.setTopAnchor(buttonQuit,20.0 );

        // Position boutton option
        option.setPadding(new Insets(25, 50, 25, 50));
        AnchorPane.setLeftAnchor(option, 1600.0 );
        AnchorPane.setTopAnchor(option,20.0 );

        this.setBottom(anchor);
        
        // draw main joueur
        
        
    	
    }
    
    public void drawPartie(Data data) {
    	VBox v = new VBox();
    	v.getChildren().add(drawMain(data));
    	v.getChildren().add(drawColonne(data));
    }
    
    public HBox drawMain(Data data) { // dessine la main du joueur
        HBox mainJoueur = new HBox();
        mainJoueur.setSpacing(10);
        
        for(CarteInfluence x: data.getMaster().getMain()) {
        	mainJoueur.getChildren().add(new SpriteCarteInfluence(x));
        }
    
    	return mainJoueur;
    }
    
    public VBox drawColonne(Data data) { // dessine les colonnes
        VBox Colonnes = new VBox();
        Colonnes.setSpacing(10);
        
       for(int i=0;i<data.getJoueurs().length;i++) {
    	   HBox h = new HBox();
    	   h.setSpacing(10);
    	   h.getChildren().add(new SpriteCarteObjectif(data.getPlateau().getColonnes()[i].getCarteObjectif())); // carte objectif
    	   for(int j=0;j < data.getPlateau().getColonnes()[i].getCartesInfluences().length;j++) { // carte influences
    		   h.getChildren().add(new SpriteCarteInfluence(data.getPlateau().getColonnes()[i].getCartesInfluences()[j]));
    	   }
    	   Colonnes.getChildren().add(h);
       }
    
    	return Colonnes;
    }
    

    //                          Operations                                  
    

    /**
    * @generated
    */
    public void placerCarte() {
        //TODO
    }
    /**
    * @generated
    */
    public void defausserCarte() {
        //TODO
    }
    /**
    * @generated
    */
    public void piocherCarte() {
        //TODO
    }
    /**
    * @generated
    */
    public void renouvelerReserve() {
        //TODO
    }
    /**
    * @generated
    */
    public void quitterJeu() {
        //TODO
    }
    
}
