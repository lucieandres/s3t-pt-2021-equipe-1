package elements;

import java.util.Set;

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
    private CartesEnMain cartesEnMain;
    
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
    public void setElementPlateau(ElementPlateau elementPlateau) {
        this.elementPlateau.add(elementPlateau);
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
    public void setColonne(Colonne colonne) {
        this.colonne = colonne;
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
    public CartesEnMain getCartesEnMain() {
        return this.cartesEnMain;
    }
    
    /**
    * @generated
    */
    public void setCartesEnMain(CartesEnMain cartesEnMain) {
        this.cartesEnMain = cartesEnMain;
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
    public void setDefausse(Defausse defausse) {
        this.defausse = defausse;
    }
    
    
}
