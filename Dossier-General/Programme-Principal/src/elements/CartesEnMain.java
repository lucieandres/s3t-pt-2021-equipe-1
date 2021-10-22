package elements;

import java.util.ArrayList;

import cartes.CarteInfluence;

/**
* @generated
*/
public class CartesEnMain extends ElementPlateau {
    
    private ArrayList<CarteInfluence> cartesInfluences = new ArrayList<>();
        
    //constructeur
    public CartesEnMain(ArrayList<CarteInfluence> cartesInfluences) {
    	this.cartesInfluences = cartesInfluences;
    }    
    
    public ArrayList<CarteInfluence> getCartesInfluences(){
        return this.cartesInfluences;
    }

    public void setCarteInfluences(ArrayList<CarteInfluence> cartesInfluences){
        this.cartesInfluences = cartesInfluences;
    }

    public void prendreCarte(CarteInfluence carteInfluence){
        cartesInfluences.add(carteInfluence);
    }

    public void poserCarte(CarteInfluence carteInfluence){
        cartesInfluences.remove(carteInfluence);
    }
}
