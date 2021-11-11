package elements;

import cartes.CarteInfluence;
import cartes.CarteObjectif;

/**
* @generated
*/
public class Colonne {

    private CarteInfluence[] cartesInfluences;
    private CarteObjectif carteObjectif;

    //constructeur
	public Colonne(int nbjoueurs) {
        this.carteObjectif = null;
        this.cartesInfluences = new CarteInfluence[6+(nbjoueurs)*2];
	}


	public CarteInfluence[] getCartesInfluences() {
        return this.cartesInfluences;
    }

    public void setCartesInfluences(CarteInfluence[] cartesInfluences) {
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
    public boolean estPleine() {
        for (int i = 0; i < cartesInfluences.length; i++)
        {
            if (cartesInfluences[i] == null)
                return (false);
        }
        return (true);
    }

    public void vider() {
        for (int i = 0; i < cartesInfluences.length; i++)
        {
            cartesInfluences[i] = null;
        }
    }
}
