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

    //remplir la reserve si elle a été vidée
    public void remplirReserve(){
    	if(cartesInfluences.size()==0){
    		defausse.shuffleDefausse();
    		for(CarteInfluence carteInfluence : defausse.getDefausse()) {
        		carteInfluence.setEstVisible(false);
        		this.cartesInfluences.add(carteInfluence);
        	}
    		cartesInfluences.addAll(defausse.getCartesInfluences());
    		defausse.viderDefausse();
        }
    }
    
}
