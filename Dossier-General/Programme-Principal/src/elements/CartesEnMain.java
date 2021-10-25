package elements;

import java.util.ArrayList;

import cartes.CarteInfluence;
import joueur.Joueur;

/**
* @generated
*/
public class CartesEnMain extends ElementPlateau {
    
    private static ArrayList<CarteInfluence> carteInfluences;
    private static Joueur joueur;
        
    //constructeur
    public CartesEnMain(ArrayList<CarteInfluence> cartesInfluences) {
    	super(carteInfluences,joueur);
    }    

    public void prendreCarte(CarteInfluence carteInfluence) {
        carteInfluences.add(carteInfluence);
    }

    public void poserCarte(CarteInfluence carteInfluence) {
        carteInfluences.remove(carteInfluence);
    }
}
