package elements;

import java.util.ArrayList;
import java.util.Random;

import cartes.CarteObjectif;

public class Plateau {

    private Colonne[] colonnes;

    private ArrayList<CarteObjectif> pioche;
    

	//constructeur
//	public Plateau(int nbjoueur) {
//		colonnes = new Colonne[nbjoueur];
//		for(Colonne col : colonnes) {
//			col = new Colonne(nbjoueur);
//		}
//		pioche = new ArrayList<CarteObjectif>();
//	}

	public Colonne[] getColonnes() {
		return colonnes;
	}

	public void setColonnes(Colonne[] colonnes) {
		this.colonnes = colonnes;
	}

	public ArrayList<CarteObjectif> getPioche() {
		return pioche;
	}

	public void setPioche(ArrayList<CarteObjectif> pioche) {
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
    
    public CarteObjectif getCarteObjectifRandomInPioche() {
    	Random rand = new Random();
    	return pioche.get(rand.nextInt(pioche.size()));
    }
    
    public void setAllColonnes() {
    	for(int col=0; col < colonnes.length; col++) {
    		CarteObjectif carte = this.getCarteObjectifRandomInPioche();
    		colonnes[col].setCarteObjectif(carte);
    		pioche.remove(carte);
    	}
    }

    //constructeur
	public Plateau(int nbjoueur) {
		colonnes = new Colonne[nbjoueur];
		for(Colonne col : colonnes) {
			col = new Colonne(nbjoueur);
		}
		pioche = new ArrayList<>();
		ArrayList<CarteObjectif> obj = new ArrayList<>();
		int indexcarte = 0;
		for(int i = 1; i<=6; i++) {
			obj.add(new CarteObjectif("Alchimie", i));
			obj.add(new CarteObjectif("Combat", i));
			obj.add(new CarteObjectif("Agriculture", i));
			obj.add(new CarteObjectif("Commerce", i));
			obj.add(new CarteObjectif("Religion", i));
			obj.add(new CarteObjectif("Musique", i));
			
			if(i==3) {
				obj.add(new CarteObjectif("Alchimie", i));
				obj.add(new CarteObjectif("Combat", i));
				obj.add(new CarteObjectif("Agriculture", i));
				obj.add(new CarteObjectif("Commerce", i));
				obj.add(new CarteObjectif("Religion", i));
				obj.add(new CarteObjectif("Musique", i));
			}
		}
		if(nbjoueur != 6) {
			while(indexcarte < nbjoueur*6) {
				Integer n = obj.size();
				Random rand = new Random();
				Integer numcarte = rand.nextInt(n);
				pioche.add(obj.get(numcarte));
				obj.remove(numcarte);
				indexcarte++;
			}
		}
		else {
			pioche = obj;
		}
		
    	for(int col=0; col < colonnes.length; col++) {
    		CarteObjectif carte = this.getCarteObjectifRandomInPioche();
    		colonnes[col] = new Colonne(nbjoueur);
    		colonnes[col].setCarteObjectif(carte);
    		pioche.remove(carte);
    	}
	}
    
}
