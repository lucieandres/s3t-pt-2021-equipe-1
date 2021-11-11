package interfaces;

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
import moteur.Systeme;
import joueur.Joueur;

/**
* @generated
*/
public class InterfaceJeu extends BorderPane implements UI {
    
    /**
    * attribut systeme de la classe Systeme, package moteur
    */
    private Systeme systeme;
    
    /**
    * attribut joueur de la classe Joueur, package joueur
    */
    private Joueur joueur;
    private Plateau plateau;
    
    public InterfaceJeu(GestionnaireInterface GI) {
    	
    	//    boutton rÃ¨gle 
        
        Button BouttonRegle = new Button("Règles");        
        BouttonRegle.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(5)));
        
        //    text joueur qui joue
        
        Label textJoueur = new Label("C'est le joueur x qui joue\nPochain joueur : joueur x");
                
        textJoueur.setMaxWidth(150);
        textJoueur.setWrapText(true);
        
        //    boutton quittÃ©
        
        Button buttonQuit = new Button("Quitter");
        buttonQuit.setOnAction(e -> Platform.exit());
        
        //    Boutton option
        
        Button option = new Button("Option");
        option.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(1)));
        
        //Création d'un AnchorPane pour tout recueillir (regle,  ...)
        
        AnchorPane anchor= new AnchorPane(); 
        anchor.getChildren().addAll(BouttonRegle,textJoueur,buttonQuit,option);
    	
        // Position boutton règle
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
