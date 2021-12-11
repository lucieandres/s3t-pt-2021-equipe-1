package cartes;

import elements.Colonne;
import javafx.scene.paint.Color;

public abstract class CarteARetardement extends CarteSpeciale{

	protected CarteARetardement(Color couleur, String nom, double valeur) {
		super(couleur, nom, valeur);
	}
	
	/**
	 * Décale récursivement les cartes <i>influence</i> d'une colonne a partir de l'index donné en parametre.
	 * 
	 * @param colonne La colonne dans laquelle les cartes doivent etre décalées.
	 * 
	 * @param indexCarteVide L'index de la carte a partir de laquelle il faut décaler les autres cartes.
	 * 
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
	public void décalerCartes(Colonne colonne, int indexCarteVide) {
		if (indexCarteVide<=colonne.getCartesInfluences().length-1 && colonne.getCarteInfluence(indexCarteVide+1)!=null) {
			CarteInfluence carteSuivante = colonne.getCarteInfluence(indexCarteVide+1);
			colonne.enleverCarteInfluence(indexCarteVide+1);
			colonne.ajouterCarteInfluence(carteSuivante);
			décalerCartes(colonne, indexCarteVide+1);
		}
	}

}
