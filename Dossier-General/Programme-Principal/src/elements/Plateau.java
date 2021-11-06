package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cartes.CarteInfluence;
import moteur.Partie;

/**
* @generated
*/
public class Plateau {

    /**
    * @generated
    */
    private ArrayList<Colonne> colonne;


	//constructeur
	public Plateau(ArrayList<Colonne> colonne) {
		this.colonne=colonne;
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

    //Methodes

    //methode qui retire toutes les cartes
    public void EnleverTous(Partie partie) {
    	for(int col=0; col<colonne.size(); col++) {
	    	for(int i=0; i<colonne.get(col).getCartesInfluences().size(); i++) {
	    		partie.getJoueurs();
	    		colonne.get(col).getCartesInfluences().get(i).getCouleur();
	    	}
    	}
        colonne.setCartesInfluences(new ArrayList<CarteInfluence>());
    }

}
