package joueur;

import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;
import moteur.Resultat;

/**
* @generated
*/
public class Bot extends Joueur {

    /**
    * @generated
    */
    private String difficulte;

    
	public Bot(String difficulte,Couleur couleur, String pseudo) {
		super(couleur, pseudo);
		this.difficulte = difficulte;
	}
	public String getDifficulte() {
		return difficulte;
	}
	/**
	* @generated
	*/
	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}

    /**
    * @generated
    */
    


}
