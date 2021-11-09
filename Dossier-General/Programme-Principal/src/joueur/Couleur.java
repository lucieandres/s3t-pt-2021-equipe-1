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
    private ArrayList<CarteInfluence> carteInfluence;
    
    /**
    * @generated
    */
    private  String nom;
    
    
    
    public Couleur(ArrayList<CarteInfluence> carteInfluence, String nom) {
		this.carteInfluence = carteInfluence;
		this.nom = nom;
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
