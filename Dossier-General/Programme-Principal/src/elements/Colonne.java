package elements;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
import cartes.Tempete;
import javafx.scene.paint.Color;

/** Cette classe définit les colonnes du plateau de jeu qui vont chacune contenir une carte <i>Objectif</i> et des
* cartes <i>Influence</i> qui vont changer au cours de la partie.
* 
* @author S3T - G1
* 
* @since 1.0
*/
public class Colonne {

    private CarteInfluence[] cartesInfluences;
    private CarteObjectif carteObjectif;
    private Boolean complete;

    /**
     * Ce constructeur produit une colonne avec un emplacement pour une carte <i>Objectif</i> et plusieurs emplacements
     * pour les cartes <i>Influence</i>, le nombre d'emplacements est défini à partir du nombre de joueurs spécifié.
     * 
     * @param nbjoueurs Le nombre de joueurs.
     * 
     * @since 1.0
     */
	public Colonne(int nbjoueurs) {
        this.carteObjectif = null;
        this.cartesInfluences = new CarteInfluence[10];
        this.complete = false;
	}

	/**
     * Retourne les cartes <i>Influence</i> de la colonne.
     * 
     * @return Les cartes <i>Influence</i> de la colonne.
     * 
     * @since 1.0
     */
	public CarteInfluence[] getCartesInfluences() {
        return this.cartesInfluences;
    }
	
	/**
     * Modifie les cartes <i>Influence</i> de la colonne avec celles spécifiées.
     * 
     * @param cartesInfluences Les cartes <i>Influence</i> à assigner à la colonne.
     * 
     * @since 1.0
     */
    public void setCartesInfluences(CarteInfluence[] cartesInfluences) {
        this.cartesInfluences = cartesInfluences;
    }

    /** Retourne la carte <i>Objectif</i> de la colonne.
    * 
    * @return La carte <i>Objectif</i> de la colonne.
    * 
    * @since 1.0
    */
    public CarteObjectif getCarteObjectif() {
        return this.carteObjectif;
    }

    /** Modifie la carte <i>Objectif</i> de la colonne.
    * 
    * @param carteObjectif La carte <i>Objectif</i> à assigner à la colonne.
    * 
    * @since 1.0
    */
    public void setCarteObjectif(CarteObjectif carteObjectif) {
        this.carteObjectif = carteObjectif;
    }
    
    /** Retourne true tant que la colonne n'est pas terminée, false si terminée.
    * 
    * @return boolean Vrai si la colonne est terminée, faux si elle ne l'est pas.
    * 
    * @since 1.0
    */
	public Boolean getComplete() {
		return complete;
	}

    /** Modifie l'état de la colonne.
    * 
    * @param complete Vrai si la colonne est terminée, faux si elle ne l'est pas.
    * 
    * @since 1.0
    */
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
    
    /**
     * Ajoute une carte <i>Influence</i> dans la colonne et place celle d'avant en face visible s'il en existe une.
     * 
     * @param carte Carte à ajouter dans la colonne.
     * 
     * @since 1.0
     */
    public void ajouterCarteInfluence(CarteInfluence carte) {
    	for(int i = 0; i<cartesInfluences.length; i++) {
    		if(cartesInfluences[i] == null) {
    			carte.setEstVisible(false);
    			cartesInfluences[i] = carte;
    			break;
    		}
    		else {
    			cartesInfluences[i].setEstVisible(true);
    		}
    	}
    }
    
    /**
     * Place la carte <i>Influence</i> spécifiée en face visible.
     * 
     * @param carteInfluence La carte <i>Influence</i> à placer en face visible.
     * 
     * @since 1.0
     */
    public void tournerCarte(CarteInfluence carteInfluence){
    	carteInfluence.setEstVisible(true);
    }

    /**
     * Retourne true tant que la colonne n'est pas pleine, false si pleine. Il est important de noter que la taille est calculée
     * à partir du nombre de joueurs et que la taille maximale d'une colonne est de dix-huit.
     * 
     * @return boolean Vrai si la colonne est pleine, faux si elle ne l'est pas.
     * 
     * @since 1.0
     */
    public boolean estPleine() {
        for (int i = 0; i < cartesInfluences.length; i++) {
        	if(cartesInfluences[i] instanceof Tempete && cartesInfluences[i].getEstVisible())
        		return true;
            if (cartesInfluences[i] == null)
                return false;
        }
        return true;
    }
    
    public boolean estFiniEtreRempli() {
        for (int i = 0; i < carteObjectif.getValeur(); i++) {
            if (cartesInfluences[i] == null)
                return false;
        }
        return true;
    }
    
    /**
     * Vide la colonne de toutes ses cartes <i>Influence</i>.
     * 
     * @since 1.0
     */
    public void vider() {
        for (int i = 0; i < cartesInfluences.length; i++)
        {
            cartesInfluences[i] = null;
        }
    }
    /**
     * Rend une carte <i>influence</i> visible.
     * @param indexCarte la carte à rendre visible.
     */
    public void setCarteInfluenceVisible(int indexCarte) {
    	cartesInfluences[indexCarte].setEstVisible(true);
    }
    
    /**
     * retourne une carte <i>influence</i> de la colonne.
     * @param index index de la carte <i>influence</i> choisie.
     * @return
     */
	public CarteInfluence getCarteInfluence(int index) {
		return cartesInfluences[index];
	}
	
	/**
	 * enlève une carte <i>influence</i> de la colonne.
	 * @param index index de la carte <i>influence</i> choisie.
	 */
	public void enleverCarteInfluence(int index) {
		cartesInfluences[index]=null;
	}
	
	// A valider
    /**
     * Cherche l'index de la carte passée en parametre.
     * 
     * @param carte Carte Influence à chercher.
     * 
     * @since 1.0
     */
	public int getIndexCarteInfluence(CarteInfluence carte) throws Exception{
		int i = 0;
		for(CarteInfluence cartesInfluence : this.cartesInfluences) {
			if(carte == cartesInfluence){
				return i;
			}
			i++;
		}
		throw new Exception("La carte n'a pas été trouvée");
	}

	/**
	 * retoune le nombre de cartes <i>influence<i>.
	 * @return
	 */
	public int nombreCartesInfluences() {
		if(this.estPleine())
			return getCartesInfluences().length;
		for(int i = 0; i < getCartesInfluences().length; i++) {
			if(getCartesInfluences()[i] == null)
				return i;
		}
		return 0;
	}
	
	/**
	 * retoune le score d'un joueur sur la colonne.
	 * @return
	 */
	public double getTotalDuJoueur(Color couleur) {
		double res = 0;
		for(CarteInfluence c : cartesInfluences) {
			if(c != null) {
				if(c.getCouleur().equals(couleur)) {
					res += c.getValeur();
				}
			}
		}
		return res;
	}
}
