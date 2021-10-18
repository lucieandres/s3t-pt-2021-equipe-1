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
    private int difficulte;

    
	public Bot(int difficulte,Couleur couleur, InterfaceJeu interfaceJeu, InterfaceParametres interfaceParametres,
			Resultat resultat, InterfaceAttente interfaceAttente, String pseudo) {
		super(couleur, interfaceJeu, interfaceParametres, resultat, interfaceAttente, pseudo);
		this.difficulte = difficulte;
	}
	public int getDifficulte() {
		return difficulte;
	}
	/**
	* @generated
	*/
	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

    /**
    * @generated
    */
    


}
