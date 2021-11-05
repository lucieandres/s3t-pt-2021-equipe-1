package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
* @generated
*/
public class Plateau {
    

    /**
    * @generated
    */
    private ArrayList<ElementPlateau> elementPlateau;
    
    /**
    * @generated
    */
    private ArrayList<Colonne> colonne;
    
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
	public Plateau(ArrayList<ElementPlateau> elementPlateau) {
		this.elementPlateau=elementPlateau;
	}
    
    /**
    * @generated
    */
    public ArrayList<ElementPlateau> getElementPlateau() {
        return this.elementPlateau;
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
    public ArrayList<Colonne> getColonne() {
        return this.colonne;
    }
    
    /**
    * @generated
    */
    public void setColonne(ArrayList<Colonne> colonne) {
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
