package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cartes.CarteInfluence;
import moteur.Partie;

public class Plateau {

    private Colonne[] colonne;

	//constructeur
	public Plateau(int nbcolonne) {
		this.colonne = new Colonne[4];
	}

    public Colonne[] getColonne() {
        return this.colonne;
    }

    public void setColonne(Colonne[] colonne) {
        this.colonne = colonne;
    }

    //Methodes

    //methode qui retire toutes les cartes
    public void EnleverTous() {
    	for(int col=0; col < colonne.length; col++) {
    	    colonne[col].setCarteObjectif(null);
            colonne[col].vider();
        }

    }

}
