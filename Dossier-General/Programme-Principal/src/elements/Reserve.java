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
        private Defausse defausse;

    //constructeur
    public Reserve() {
    	super(cartesInfluences, joueur);
    }

    //remplir la reserve
    public void reserveVide(){
        if(cartesInfluences.size()==0){
            cartesInfluences.addAll(defausse.getCartesInfluences());
        }
    }
    
}
