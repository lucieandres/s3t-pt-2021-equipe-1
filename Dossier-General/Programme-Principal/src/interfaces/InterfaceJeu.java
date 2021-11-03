package interfaces;

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
    public placerCarte() {
        //TODO
    }
    /**
    * @generated
    */
    public defausserCarte() {
        //TODO
    }
    /**
    * @generated
    */
    public piocherCarte() {
        //TODO
    }
    /**
    * @generated
    */
    public renouvelerReserve() {
        //TODO
    }
    /**
    * @generated
    */
    public quitterJeu() {
        //TODO
    }
    
}
