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

    
	public Bot(String difficulte,Couleur couleur, InterfaceJeu interfaceJeu, InterfaceParametres interfaceParametres,
			Resultat resultat, InterfaceAttente interfaceAttente, String pseudo) {
		super(couleur, interfaceJeu, interfaceParametres, resultat, interfaceAttente, pseudo);
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
