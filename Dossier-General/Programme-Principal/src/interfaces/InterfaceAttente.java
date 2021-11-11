package interfaces;

import javafx.scene.layout.Pane;
import joueur.Joueur;

/**
 * Cette classe est une interface qui represente le menu principal.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceAttente extends Pane implements UI {
    
    
    /**
    * attribut joueur de la classe Joueur, package joueur
    */
    private Joueur joueur;
    
    /**
     * attribut renseignant si la case "pret" est cochee ou non, 
     * si tous les joueurs d'une partie l'ont cochee, la partie peut commencer
     */
     private boolean estPret = false;
    
    
     public InterfaceAttente(GestionnaireInterface gi) {}
    
//    /**
//    * affiche joueur
//    */
//    public Joueur getJoueur() {
//        return this.joueur;
//    }
//    
//    /**
//    * modifie joueur
//    */
//    public void setJoueur(Joueur joueur) {
//        this.joueur = joueur;
//    }
//    
//    /**
//     * affiche estPret
//     */
//    public boolean getEstPret() {
//		return estPret;
//	}
//
//    /**
//     * modifie estPret
//     */
//	public void setEstPret(boolean estPret) {
//		this.estPret = estPret;
//	}
//	
//    
//
//    //                          Operations                                  
//    
//    /**
//    * si la case "pret" est cochee, l'attribut estPret devient true
//    */
//    public void estPret() {
//        estPret = true;
//    }
    
}
