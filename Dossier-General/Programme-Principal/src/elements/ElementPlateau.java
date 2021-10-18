package elements;

import java.util.Set;

import cartes.CarteInfluence;
import joueur.Joueur;

import java.util.HashSet;

/**
* @generated
*/
public class ElementPlateau {
    

    /**
    * @generated
    */
    private Set<CarteInfluence> carteInfluence;
    
    /**
    * @generated
    */
    private Joueur joueur;
    
	//Constructeur
	public ElementPlateau() {
		//TODO
	}
    
    /**
    * @generated
    */
    public CarteInfluence getCarteInfluence() {
        return (CarteInfluence) this.carteInfluence;
    }
    
    /**
    * @generated
    */
    public CarteInfluence setCarteInfluence(CarteInfluence carteInfluence) {
        this.carteInfluence = (Set<CarteInfluence>) carteInfluence;
		return carteInfluence;
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
    public Joueur setJoueur(Joueur joueur) {
        return this.joueur = joueur;
    }
    
    
}
