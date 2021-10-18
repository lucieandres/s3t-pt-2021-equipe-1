package elements;

import java.util.Set;
import java.util.HashSet;

/**
* @generated
*/
public class Plateau {
    

    /**
    * @generated
    */
    private Set<ElementPlateau> elementPlateau;
    
    /**
    * @generated
    */
    private Colonne colonne;
    
    /**
    * @generated
    */
    private Reserve reserve;
    
    /**
    * @generated
    */
    private CartesEnMain cartes_en_main;
    
    /**
    * @generated
    */
    private Defausse defausse;
    
	//constructeur
	public Plateau() {
		//TODO
	}
    
    /**
    * @generated
    */
    public ElementPlateau getElementPlateau() {
        return (ElementPlateau) this.elementPlateau;
    }
    
    /**
    * @generated
    */
    public ElementPlateau setElementPlateau(ElementPlateau elementPlateau) {
        this.elementPlateau = (Set<ElementPlateau>) elementPlateau;
		return elementPlateau;
    }
    
    /**
    * @generated
    */
    public Colonne getColonne() {
        return this.colonne;
    }
    
    /**
    * @generated
    */
    public Colonne setColonne(Colonne colonne) {
        return this.colonne = colonne;
    }
    
    /**
    * @generated
    */
    public Reserve getReserve() {
        return this.reserve;
    }
    
    /**
    * @generated
    */
    public Reserve setReserve(Reserve reserve) {
        return this.reserve = reserve;
    }
    
    /**
    * @generated
    */
    public CartesEnMain getCartes_en_main() {
        return this.cartes_en_main;
    }
    
    /**
    * @generated
    */
    public CartesEnMain setCartes_en_main(CartesEnMain cartes_en_main) {
        return this.cartes_en_main = cartes_en_main;
    }
    
    /**
    * @generated
    */
    public Defausse getDefausse() {
        return this.defausse;
    }
    
    /**
    * @generated
    */
    public Defausse setDefausse(Defausse defausse) {
        return this.defausse = defausse;
    }
    
    
}
