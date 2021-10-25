package elements;

import cartes.CarteObjectif;

/**
* @generated
*/
public class Colonne extends ElementPlateau {
    
	
    private static Object cartesInfluences;
    private static Object joueur;
    /**
    * @generated
    */
    private CarteObjectif carteObjectif;
    private Plateau plateau;
    
    //constructeur
	public Colonne(CarteObjectif carteObjectif, Plateau plateau) {
		super(cartesInfluences,joueur);
        this.carteObjectif=carteObjectif;
        this.plateau=plateau;
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
