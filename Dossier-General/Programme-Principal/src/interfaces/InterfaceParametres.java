package interfaces;

import joueur.Joueur;
import moteur.Systeme;

/**
* @generated
*/
public class InterfaceParametres implements Interface {
    
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
    public activerSon() {
        //TODO
    }
    /**
    * @generated
    */
    public couperSon() {
        //TODO
    }
    /**
    * @generated
    */
    public activerMusique() {
        //TODO
    }
    /**
    * @generated
    */
    public couperMusique() {
        //TODO
    }
    /**
    * @generated
    */
    public changerLangue() {
        //TODO
    }
    /**
    * @generated
    */
    public changerTheme() {
        //TODO
    }
    
}
