package elements;

import java.util.ArrayList;
import java.util.Arrays;

import cartes.CarteInfluence;

/**
* @generated
*/
public class CartesEnMain {
    
    private ArrayList<CarteInfluence> cartesInfluences;
        
    //constructeur
    public CartesEnMain(ArrayList<CarteInfluence> cartesInfluences) {
    	this.cartesInfluences = cartesInfluences;
    }    

    public ArrayList<CarteInfluence> getCartesInfluences() {
        return this.cartesInfluences;
    }
    
    public void setCartesInfluences(ArrayList<CarteInfluence> cartesInfluences) {
        this.cartesInfluences = cartesInfluences;
    }
    
    //piocher une carte
    public void prendreCarte(CarteInfluence carteInfluence, Reserve reserve) {
        reserve.getCartesInfluences().remove(carteInfluence);
        cartesInfluences.add(carteInfluence);
    }

    //methode pour poser une carte dans une colonne
    public void poserCarte(CarteInfluence carteInfluence, Colonne colonne) {
        cartesInfluences.remove(carteInfluence);
        CarteInfluence[] cartes = colonne.getCartesInfluences();
        int n = colonne.getCartesInfluences().length;
        cartes = Arrays.copyOf(colonne.getCartesInfluences(), n+1);
        cartes[n]=carteInfluence;
        if(colonne.getCartesInfluences().length>=colonne.getCarteObjectif().getValeur()){
            colonne.getCarteObjectif().setEstRealise(true);
        } else {
            colonne.getCarteObjectif().setEstRealise(false);
        }
    }

}
