package joueur;

import java.util.Set;

import cartes.CarteInfluence;
import json.JsonInterface;

import java.util.ArrayList;
import java.util.HashSet;

/**
* @generated
*/
public class Couleur implements JsonInterface {
    
    /**
    * @generated
    */
    private Joueur joueur;
    
    /**
    * @generated
    */
    private ArrayList<CarteInfluence> carteInfluence;
    
    /**
    * @generated
    */
    private  String nom;
    
    
    
    public Couleur(Joueur joueur, ArrayList<CarteInfluence> carteInfluence, String nom) {
		super();
		this.joueur = joueur;
		this.carteInfluence = carteInfluence;
		this.nom = nom;
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
    public ArrayList<CarteInfluence> getCarteInfluence() {
        return this.carteInfluence;
    }
    
    /**
    * @generated
    */
    public void setCarteInfluence(ArrayList<CarteInfluence> carteInfluence) {
        this.carteInfluence = carteInfluence;
    }
    
    /**
    * @generated
    */
    public String getNom() {
        return this.nom;
    }
    
    /**
    * @generated
    */
    public void setNom(String nom) {
        this.nom = nom;
    }

	@Override
	public String toJson() {
		return null;//A modif
	}
    
	
    
}
