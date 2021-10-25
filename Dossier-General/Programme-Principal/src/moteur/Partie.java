package moteur;



import joueur.Joueur;

import java.util.ArrayList;

/**
* @generated
*/
public class Partie {
    
    /**
    * @generated
    */
    private ArrayList<Joueur> joueurs;
    
    /**
    * @generated
    */
    private ArrayList<Manche> manches;
    
    /**
    * @generated
    */
    private String code;
    
    public Partie(ArrayList<Joueur> j, ArrayList<Manche> m, String code) {
    	joueurs = j;
    	manches = m;
    	this.code = code;
    }
    
    public Partie() {
    	joueurs = new ArrayList<Joueur>();
    	manches = new ArrayList<Manche>();
    	this.code = null;
    }
    
    /**
    * @generated
    */
    public ArrayList<Joueur> getJoueur() {
        return this.joueurs;
    }
    
    /**
    * @generated
    */
    public void setJoueur(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
    
    /**
    * @generated
    */
    public ArrayList<Manche> getManche() {
        return this.manches;
    }
    
    /**
    * @generated
    */
    public void setManche(ArrayList<Manche> manches) {
        this.manches = manches;
    }
    
    /**
    * @generated
    */
    public String getCode() {
        return this.code;
    }
    
    /**
    * @generated
    */
    public void setCode(String code) {
        this.code = code;
    }
    
    
}
