package interfaces;

import elements.Plateau;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import moteur.Systeme;
import joueur.Joueur;

/**
* @generated
*/
public class InterfaceJeu extends Pane implements UI {
    
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
    	
    	Button BouttonRegle = new Button("regle");
    	BouttonRegle.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(5)));
    	this.getChildren().add(BouttonRegle);
    	
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
