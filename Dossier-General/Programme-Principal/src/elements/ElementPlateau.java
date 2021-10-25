package elements;

import cartes.CarteInfluence;

import java.util.ArrayList;

import joueur.Joueur;


/**
* @generated
*/
public class ElementPlateau {
    
    /**
    * @generated
    */
    private ArrayList<CarteInfluence> cartesInfluences;
    
    /**
    * @generated
    */
    private Joueur joueur;
    

    //constructeur
    public ElementPlateau(ArrayList<CarteInfluence> carteInfluences, Joueur joueur){
        this.cartesInfluences=carteInfluences;
        this.joueur=joueur;
    }
    
    /**
    * @generated
    */
    public ArrayList<CarteInfluence> getCartesInfluences() {
        return this.cartesInfluences;
    }
    
    /**
    * @generated
    */
    public void setCartesInfluences(ArrayList<CarteInfluence> cartesInfluences) {
        this.cartesInfluences = cartesInfluences;
    }
    
    /**
    * @generated
    */
    public Joueur getJoueur() {
        return this.joueur;
    }
    
    /**
    * @generated
    */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    
    
}
