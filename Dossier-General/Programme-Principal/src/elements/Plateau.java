package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cartes.CarteInfluence;
import moteur.Partie;

public class Plateau {

    private Colonne[] colonnes;

	//constructeur
	public Plateau(int nbcolonne) {
		this.colonnes = new Colonne[nbcolonne];
	}

    public Colonne[] getColonne() {
        return this.colonnes;
    }

    public void setColonne(Colonne[] colonne) {
        this.colonnes = colonne;
    }

    //Methodes

    //methode qui retire toutes les cartes
    public void enleverTous() {
    	for(int col=0; col < colonnes.length; col++) {
    	    colonnes[col].setCarteObjectif(null);
            colonnes[col].vider();
        }

    }

}
