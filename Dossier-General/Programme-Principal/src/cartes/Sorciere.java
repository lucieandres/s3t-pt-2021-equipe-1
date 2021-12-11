package cartes;

import elements.Colonne;
import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Sorcière dont la valeur est 1 et qui a une capacité qui s'active en fin de manche.
 * Toutes les cartes de valeur 9 ou moins (sauf Sorcière elle-meme) sont éliminée de la colonne.
 * Cette capacité s'applique aussi pour les cartes placées sous la Cape d'invisibilité.
 * S'il y a plusiseurs cartes Sorcière dans la meme colonne leur capacité est annulée. 
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Sorciere extends CarteARetardement{

	public Sorciere(Color couleur) {
		super(couleur, "Sorcière", 1);
	}

	/**
	 * Active la capacité spéciale de la carte Sorcière.
	 * 
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
	@Override
	public void activer(Data data) throws Exception {
		int indexColonne=data.getPlateau().getIndexColonneCarte(this);
		int indexCarte=data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		int indexJoueurProprietaire = data.getIndexProprietaireCarteInfluence(indexColonne, indexCarte);
		for(CarteInfluence carte : data.getPlateau().getColonne(indexColonne).getCartesInfluences()) {
			if((carte.getValeur()<=9)&&(carte!=this)) {
				data.getJoueurs()[indexJoueurProprietaire].ajouterDansLaDefausse(data.getPlateau().getColonne(indexColonne).getCarteInfluence(indexCarte));
				data.getPlateau().getColonne(indexColonne).enleverCarteInfluence(indexCarte);
				
				this.décalerCartes(data.getPlateau().getColonne(indexColonne), indexCarte);
			}
		}
	}
	
	public void décalerCartes(Colonne colonne, int indexCarteVide) {
		if (indexCarteVide<=16) {
			if(colonne.getCarteInfluence(indexCarteVide+1)!=null) {
				CarteInfluence carteSuivante = colonne.getCarteInfluence(indexCarteVide+1);
				colonne.enleverCarteInfluence(indexCarteVide+1);
				colonne.ajouterCarteInfluence(carteSuivante);
				décalerCartes(colonne, indexCarteVide+1);
			}
		}
	}

}
