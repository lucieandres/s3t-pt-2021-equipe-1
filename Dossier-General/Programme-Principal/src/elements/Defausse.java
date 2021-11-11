package elements;

import java.util.ArrayList;

import cartes.CarteInfluence;

/**
* @generated
*/
public class Defausse extends ElementPlateau {
    
	ArrayList<CarteInfluence> cartesInfluences;
	
        //constructeur
    public Defausse() {
    	
    }
    
    public void addDefausseCartesInfluences(CarteInfluence carteInfluence) {
    	this.cartesInfluences.add(carteInfluence);
    }
    
    public void sortDefausse() { //si la reserve du joueur est epuisee, il melange sa defausse et la reutilise tant que reserve face cachee
    	
    }
    
    
}
