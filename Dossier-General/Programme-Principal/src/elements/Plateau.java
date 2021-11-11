package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
import moteur.Partie;

public class Plateau {

    private Colonne[] colonnes;

    private CarteObjectif[] pioche;
    

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


	public Colonne[] getColonnes() {
		return colonnes;
	}

	public void setColonnes(Colonne[] colonnes) {
		this.colonnes = colonnes;
	}

	public CarteObjectif[] getPioche() {
		return pioche;
	}

	public void setPioche(CarteObjectif[] pioche) {
		this.pioche = pioche;
	}
    //Methodes

    //methode qui retire toutes les cartes
    public void enleverTous() {
    	for(int col=0; col < colonnes.length; col++) {
    	    colonnes[col].setCarteObjectif(null);
            colonnes[col].vider();
        }
    }
    
    public CarteObjectif getOneCarteObjectifRandomInPioche() {
    	Random rand = new Random();
    	CarteObjectif carte;
    	ArrayList<Integer> listIndex = new ArrayList<>();
    	for(int i = 0; i< pioche.length ; i++) {
    		if(pioche[i] != null) {
    			listIndex.add(i);
    		}
    	}
    	int selected = rand.nextInt(listIndex.size());
    	carte = pioche[listIndex.get(selected)];
    	pioche[listIndex.get(selected)] = null;
    	return carte;
    	
    }
    
    public void setAllColonnes() {
    	for(int col=0; col < colonnes.length; col++) {
    		colonnes[col].setCarteObjectif(this.getOneCarteObjectifRandomInPioche());
    	}
    }
    
    public void newManche() {
    	this.enleverTous();
    	this.setAllColonnes();
    }
}
