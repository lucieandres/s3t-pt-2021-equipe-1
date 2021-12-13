package cartes;

import elements.Colonne;
import javafx.scene.paint.Color;

/**
 * Cette classe définit les cartes <i>Influence</i> qui ont la spécificité d'avoir une capacité spéciale qui s'active a la fin de la manche.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public abstract class CarteARetardement extends CarteSpeciale{

    /**
     * Ce constructeur produit une carte <i>Influence</i> à retardement en lui assignant sa couleur, son nom, sa valeur avec les variables qui ont été spécifiées. </br>
     * False sera assigné à sa visibilité par défaut, ce qui correspond à face cachée.</br>
     * NUL sera assigné à son infoReseau par défault.</br>
     * False sera assigné à son desactivee par défault, ce qui correspond une capacité spéciale utilisable.</br>
     * 
     * @param couleur La couleur de la carte <i>Influence</i>.
     * 
     * @param nom Le nom de la carte <i>Influence</i>.
     * 
     * @param valeur La valeur de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
	protected CarteARetardement(Color couleur, String nom, double valeur) {
		super(couleur, nom, valeur);
	}
	
	/**
	 * Décale récursivement les cartes <i>influence</i> d'une colonne a partir de l'index donné en paramètre.
	 * 
	 * @param colonne La colonne dans laquelle les cartes doivent etre décalées.
	 * 
	 * @param indexCarteVide L'index de la carte a partir de laquelle il faut décaler les autres cartes.
	 * 
	 * @since 1.0
	 */
	public void decalerCartes(Colonne colonne, int indexCarteVide) {
		if (indexCarteVide<=colonne.getCartesInfluences().length-1 && colonne.getCarteInfluence(indexCarteVide+1)!=null) {
			CarteInfluence carteSuivante = colonne.getCarteInfluence(indexCarteVide+1);
			colonne.enleverCarteInfluence(indexCarteVide+1);
			colonne.ajouterCarteInfluence(carteSuivante);
			decalerCartes(colonne, indexCarteVide+1);
		}
	}

}
