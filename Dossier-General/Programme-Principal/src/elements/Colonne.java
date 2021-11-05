package elements;

import java.util.ArrayList;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
import moteur.Partie;

/**
* @generated
*/
public class Colonne {
	
    private ArrayList<CarteInfluence> cartesInfluences;
    private CarteObjectif carteObjectif;
    
    //constructeur
	public Colonne(CarteObjectif carteObjectif) {
        this.carteObjectif=carteObjectif;
        ArrayList<CarteInfluence> cartesInfluences = new ArrayList<CarteInfluence>();
	}
    
	
	public ArrayList<CarteInfluence> getCartesInfluences() {
        return this.cartesInfluences;
    }
    
    public void setCartesInfluences(ArrayList<CarteInfluence> cartesInfluences) {
        this.cartesInfluences = cartesInfluences;
    }
    
    /**
    * @generated
    */
    public CarteObjectif getCarteObjectif() {
        return this.carteObjectif;
    }
    
    /**
    * @generated
    */
    public void setCarteObjectif(CarteObjectif carteObjectif) {
        this.carteObjectif = carteObjectif;
    }

    //methode qui retourne une carte
    public void tournerCarte(CarteInfluence carteInfluence){
    	carteInfluence.setEstVisible(true);
    }
        
    //methode renvoyant true tant que la colonne n'est pas pleine
    //renvoie false si pleine (est pleine si �gale � 6+(nb joueurs)*2)
    public Boolean estPleine(Partie partie) {
    	if (cartesInfluences.size() < (6+(partie.nombreJoueurs())*2) ) {
        	return true;
        }
        else
        	return false;
    }
}
