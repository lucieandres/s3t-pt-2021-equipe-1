package interfaces;

import javafx.scene.layout.Pane;
import joueur.Joueur;
import moteur.Systeme;

/**
* @generated
*/
public class InterfaceAttente extends Pane implements UI {
    
    /**
    * attribut systeme de la classe Systeme, package moteur
    */
    private Systeme systeme;
    
    /**
    * attribut joueur de la classe Joueur, package joueur
    */
    private Joueur joueur;
    
    /**
     * attribut renseignant si la case "pret" est cochee ou non, 
     * si tous les joueurs d'une partie l'ont cochee, la partie peut commencer
     */
     private boolean estPret = false;
    
    

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
    
    /**
     * affiche estPret
     */
    public boolean getEstPret() {
		return estPret;
	}

    /**
     * modifie estPret
     */
	public void setEstPret(boolean estPret) {
		this.estPret = estPret;
	}
	
    

    //                          Operations                                  
    
    /**
    * si la case "pret" est cochee, l'attribut estPret devient true
    */
    public void estPret() {
        estPret = true;
    }
    
}
