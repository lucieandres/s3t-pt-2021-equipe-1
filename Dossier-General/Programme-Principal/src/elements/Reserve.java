package elements;

import java.util.ArrayList;

import cartes.CarteInfluence;
import joueur.Joueur;

/**
* @generated
*/
public class Reserve extends ElementPlateau {
    
        private static ArrayList<CarteInfluence> cartesInfluences;
        private static Joueur joueur;

    //constructeur
    public Reserve() {
    	super(cartesInfluences, joueur);
    }
    
}
