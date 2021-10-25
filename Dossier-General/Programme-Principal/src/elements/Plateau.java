package elements;

import java.util.List;
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
    private List<Colonne> colonne;
    
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
    public List<Colonne> getColonne() {
        return this.colonne;
    }
    
    /**
    * @generated
    */
    public void setColonne(List<Colonne> colonne) {
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
