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

    //piocher une carte
    public void prendreCarte(CarteInfluence carteInfluence, Reserve reserve) {
        reserve.getCartesInfluences().remove(carteInfluence);
        carteInfluences.add(carteInfluence);
    }

    //methode pour poser une carte dans une colonne
    public void poserCarte(CarteInfluence carteInfluence, Colonne colonne) {
        carteInfluences.remove(carteInfluence);
        colonne.getCartesInfluences().add(carteInfluence);
        if(colonne.getCartesInfluences().size()>=colonne.getCarteObjectif().getValeur()){
            colonne.getCarteObjectif().setEstRealise(true);
        } else {
            colonne.getCarteObjectif().setEstRealise(false);
        }
    }

}
