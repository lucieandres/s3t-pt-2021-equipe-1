package elements;

import java.util.ArrayList;
import java.util.Random;

import cartes.CarteObjectif;

/**
 * Cette classe permet de regrouper tous les �l�ments essentiels au fonctionnement d'une partie tels que les colonnes contenant les cartes jou�es
 * et la pioche de cartes <i>Objectif</i> qui va �tre utilis�e � chaque manche.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Plateau {

    private Colonne[] colonnes;

    private ArrayList<CarteObjectif> pioche;
    
    /**
     * Ce constructeur produit un plateau de jeu avec un nombre de colonnes sp�cifi� par le nombre de joueurs.
     * 
     * @param nbjoueur Le nombre de joueurs.
     */
	public Plateau(int nbjoueur) {
		this.colonnes = new Colonne[nbjoueur];
		for(Colonne col : colonnes) {
			col = new Colonne(nbjoueur);
		}
	}
	
	/**
     * Retourne les colonnes du plateau de jeu.
     * 
     * @return Les colonnes du plateau.
     * 
     * @since 1.0
     */
	public Colonne[] getColonnes() {
		return colonnes;
	}
	
	/**
     * Modifie les colonnes du plateau de jeu.
     * 
     * @param colonnes Les colonnes du plateau de jeu.
     * 
     * @since 1.0
     */
	public void setColonnes(Colonne[] colonnes) {
		this.colonnes = colonnes;
	}
	
	/**
     * Retourne la pioche de cartes <i>Objectif</i>.
     * 
     * @return La pioche de cartes <i>Objectif</i>.
     * 
     * @since 1.0
     */
	public ArrayList<CarteObjectif> getPioche() {
		return pioche;
	}
	
	/**
     * Modifie la pioche de cartes <i>Objectif</i>.
     * 
     * @param pioche La pioche de cartes <i>Objectif</i>.
     * 
     * @since 1.0
     */
	public void setPioche(ArrayList<CarteObjectif> pioche) {
		this.pioche = pioche;
	}
	
	/**
     * Retire toutes les cartes <i>Objectif</i> de chaque colonne.
     * 
     * @since 1.0
     */
    public void enleverTous() {
    	for(int col=0; col < colonnes.length; col++) {
    	    colonnes[col].setCarteObjectif(null);
            colonnes[col].vider();
        }
    }
    
    /**
     * Retourne une carte <i>Objectif</i> al�atoire de la pioche.
     * 
     * @return Une carte <i>Objectif</i> al�atoire.
     * 
     * @since 1.0
     */
    public CarteObjectif getCarteObjectifRandomInPioche() {
    	Random rand = new Random();
    	return pioche.get(rand.nextInt(pioche.size()));
    }
    
    /**
     * Ajoute une carte <i>Objectif</i> al�atoire dans chaque colonne.
     * 
     * @since 1.0
     */
    public void setAllColonnes() {
    	for(int col=0; col < colonnes.length; col++) {
    		CarteObjectif carte = this.getCarteObjectifRandomInPioche();
    		colonnes[col].setCarteObjectif(carte);
    		pioche.remove(carte);
    	}
    }
    /**
     * Initialise la pioche de cartes <i>Objectif</i>. Le nombre de joueurs multipli� par six repr�sente le montant des cartes qui composent
     * la pioche. Il existe trente-six cartes, taille maximale de la pioche.
     * 
     * @param nbjoueur Le nombre de joueurs.
     * 
     * @since 1.0
     */
	public void initPioche(int nbjoueur) {
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
				Random rand = new Random();
				int numcarte = rand.nextInt(obj.size());
				pioche.add(obj.get(numcarte));
				obj.remove(numcarte);
			}
		}
		else {
			pioche = obj;
		}
	}
    
}
