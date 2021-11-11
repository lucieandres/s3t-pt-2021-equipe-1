package elements;

import cartes.CarteInfluence;
import cartes.CarteObjectif;

/**
 * Cette classe définit les colonnes du plateau de jeu qui vont chacune contenir une carte <i>Objectif</i> et des
 * cartes <i>Influence</i> qui vont changer au cours de la partie.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Colonne {

    private CarteInfluence[] cartesInfluences;
    private CarteObjectif carteObjectif;
    
    /**
     * Ce constructeur produit une colonne avec un emplacement pour une carte <i>Objectif</i> et plusieurs emplacements
     * pour les cartes <i>Influence</i>, le nombre d'emplacements est défini à partir du nombre de joueurs spécifié.
     * 
     * @param nbjoueur Le nombre de joueurs.
     * 
     * @since 1.0
     */
	public Colonne(int nbjoueurs) {
        this.carteObjectif = null;
        this.cartesInfluences = new CarteInfluence[6+(nbjoueurs)*2];
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

    /**
     * Retourne la carte <i>Objectif</i> de la colonne.
     * 
     * @return La carte <i>Objectif</i> de la colonne.
     * 
     * @since 1.0
     */
    public CarteObjectif getCarteObjectif() {
        return this.carteObjectif;
    }

    /**
     * Modifie la carte <i>Objectif</i> de la colonne.
     * 
     * @param carteObjectif La carte <i>Objectif</i> � assigner � la colonne.
     * 
     * @since 1.0
     */
    public void setCarteObjectif(CarteObjectif carteObjectif) {
        this.carteObjectif = carteObjectif;
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
     * � partir du nombre de joueurs et que la taille maximale d'une colonne est de dix-huit.
     * 
     * @return boolean Vrai si la colonne est pleine, faux si elle ne l'est pas.
     * 
     * @since 1.0
     */
    public boolean estPleine() {
        for (int i = 0; i < cartesInfluences.length; i++)
        {
            if (cartesInfluences[i] == null)
                return (false);
        }
        return (true);
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
}
