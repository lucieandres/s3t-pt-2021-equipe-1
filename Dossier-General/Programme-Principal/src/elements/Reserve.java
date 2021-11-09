package elements;

import java.util.ArrayList;
import cartes.CarteInfluence;

/**
* @generated
*/
public class Reserve {
    
	private ArrayList<CarteInfluence> cartesInfluences;
    private Defausse defausse;
        
    //constructeur
    public Reserve(ArrayList<CarteInfluence> cartesInfluences) {
       	this.cartesInfluences = cartesInfluences;
    }    

    public ArrayList<CarteInfluence> getCartesInfluences() {
    	return this.cartesInfluences;
    }
        
    public void setCartesInfluences(ArrayList<CarteInfluence> cartesInfluences) {
    	this.cartesInfluences = cartesInfluences;
    }

    //remplir la reserve
    public void reserveVide(){
    	if(cartesInfluences.size()==0){
    		cartesInfluences.addAll(defausse.getCartesInfluences());
        }
    }
    
}
