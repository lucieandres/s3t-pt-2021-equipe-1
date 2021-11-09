package moteur;

import joueur.Joueur;

/**
* @generated
*/
public class Resultat {
    
    /**
    * @generated
    */
    private Joueur joueur;
    
    /**
    * @generated
    */
    private int valeur;
    
    
    public Resultat(Joueur j, int v) {
    	joueur = j;
    	valeur = v;
    }
    
    public Resultat() {
    	joueur = null;
    	valeur = 0;
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
    
    /**
    * @generated
    */
    public int getValeur() {
        return this.valeur;
    }
    
    /**
    * @generated
    */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
    
}
