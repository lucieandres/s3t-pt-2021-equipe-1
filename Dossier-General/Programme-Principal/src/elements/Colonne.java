package elements;

import cartes.CarteObjectif;

/**
* @generated
*/
public class Colonne extends ElementPlateau {
    
	
    /**
    * @generated
    */
    private CarteObjectif carteObjectif;
    private Plateau plateau;
    
    //constructeur
	public Colonne() {
		//TODO
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
    
    public Plateau getPlateau() {
        return this.plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
    
}
