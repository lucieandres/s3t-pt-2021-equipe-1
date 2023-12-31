package elements;

import java.util.ArrayList;
import java.util.Random;

import cartes.CarteInfluence;
import cartes.CarteObjectif;

/**
 * Cette classe permet de regrouper tous les éléments essentiels au fonctionnement d'une partie tels que les colonnes contenant les cartes jouées
 * et la pioche de cartes <i>Objectif</i> qui va être utilisée à chaque manche.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
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
     * Ajoute une carte <i>Influence</i> spécifiée dans une colonne grâce à l'index aussi renseigné en paramètre.
     * 
     * @param index index de la colonne spécifiée
     * @param carte carte <i>influence<i> spécifiée
     * 
     * @since 1.0
     */
	public void ajouterColonnes(int index, CarteInfluence carte) {
		colonnes[index].ajouterCarteInfluence(carte);
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
     * Retourne une carte <i>Objectif</i> aléatoire de la pioche.
     * 
     * @return Une carte <i>Objectif</i> aléatoire.
     * 
     * @since 1.0
     */
    public CarteObjectif getCarteObjectifRandomInPioche() {
    	Random rand = new Random();
    	CarteObjectif carte = pioche.get(rand.nextInt(pioche.size()));
    	pioche.remove(carte);
    	return carte;
    }
    
    /**
     * Ajoute une carte <i>Objectif</i> aléatoire dans chaque colonne.
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
     * Ce constructeur produit un plateau et initialise le nombre de colonnes en fonction du nombre de joueurs spécifié en paramètre.
     * Il initialise aussi la pioche de cartes <i>Objectif</i>. Le nombre de joueurs multiplié par six représente
     * le montant des cartes qui composent la pioche. Il existe trente-six cartes, taille maximale de la pioche.
     * 
     * @param nbjoueur Le nombre de joueurs.
     * 
     * @since 1.0
     */
	public Plateau(int nbjoueur) {
		colonnes = new Colonne[nbjoueur];
		for(Colonne col : colonnes) {
			col = new Colonne(nbjoueur);
		}
		pioche = new ArrayList<>();
		ArrayList<CarteObjectif> obj = new ArrayList<>();
		int indexcarte = 0;
		for(int i = 1; i<=5; i++) {
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
			while(indexcarte < nbjoueur*6+nbjoueur) {
				Integer n = obj.size();
				Random rand = new Random();
				Integer numcarte = rand.nextInt(n);
				System.out.println(numcarte + " < "+ n);
				pioche.add(obj.get(numcarte));
				obj.remove(obj.get(numcarte));
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
    
    /**
     * rend visible la carte <i>influence<i> désignée
     * 
     * @param indexColonne l'index de la colonne de la carte désignée
     * @param indexCarte l'index dela carte désignée
     * 
     * @since 1.0
     */
    public void setCarteInfluencesVisible(int indexColonne, int indexCarte) {
    	colonnes[indexColonne].setCarteInfluenceVisible(indexCarte);
    }
    
    /**
     * retourne une colonne.
     * @param index index de la colonne à retourner
     * @return
     */
    public Colonne getColonne(int index) {
    	return colonnes[index];
    }
    
    /**
     * enlève une carte du plateau.
     * @param colonne la colonne de la carte à enlever.
     * @param carte index de la carte à enlever.
     */
    public void enleverCarteInfluence(int colonne, int carte) {
    	colonnes[colonne].enleverCarteInfluence(carte);
    }

/**
 * met une nouvelle carte <i>objectif<i> dans une colonne
 * @param i numéro de la colonne où mettre la nouvelle carte.
 */
	public void setNouvelleCarteObjectif(int i) {
		colonnes[i].setCarteObjectif(getCarteObjectifRandomInPioche());
	}

	/**
	 * met une nouvelle carte <i>objectif<i> nulle dans une colonne
	 * @param i numéro de la colonne où mettre la nouvelle carte nulle.
	 */
	public void setNouvelleCarteObjectifNull(int i) {
		colonnes[i].setCarteObjectif(null);
	}

	/**
	 * Méthode qui dit si la pioche est vide ou non.
	 * @return
	 */
	public boolean piocheEstVide() {
		System.out.println(pioche.isEmpty());
		return pioche.isEmpty();
	}
	
	
	// A valider
    /**
     * Cherche l'index de la colonne qui contient la carte passée en parametre.
     * 
     * @param carte Carte Influence à chercher.
     * 
     * @since 1.0
     */
	public int getIndexColonneCarte(CarteInfluence carte) throws Exception{
		for(int i = 0; i < colonnes.length; i++) {
			for(CarteInfluence cartesInfluence : colonnes[i].getCartesInfluences()) {
				if(carte == cartesInfluence)
					return i;
			}
		}
		throw new Exception("La carte n'a pas été trouvée");
	}

	
	/**
	 * rend une colonne complète.
	 * @param numeroColonne colonne à rendre complete
	 */
	public void setColonnesComplete(int numeroColonne) {
		colonnes[numeroColonne].setComplete(true);
	}

	/**
	 * rend une colonne incomplète.
	 * @param numeroColonne colonne à rendre incomplete
	 */
	public void setColonnesIncomplete(int numeroColonne) {
		colonnes[numeroColonne].setComplete(false);
	}

	/**
	 * élimine une carte du plateau.
	 * @param indexColonne la colonne de la carte à éliminer.
	 * @param carte la carte à éliminer.
	 * @return
	 * @throws Exception
	 */
	public Colonne eliminerCartePlateau(int indexColonne, CarteInfluence carte) throws Exception {
		Colonne newColonne = this.getColonne(indexColonne);
		newColonne.enleverCarteInfluence(newColonne.getIndexCarteInfluence(carte));
		return newColonne;
	}
	
}