package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Dragon dont la valeur est 11 et qui a une capacité qui s'active en fin de manche.
 * Le dragon enlève 2 à la valeur de toutes les cartes dans sa colonne sauf pour les cartes du propriétaire du Dragon.
 * S'il y a plusieurs Dragons dans la meme colonne, chacun enlève 2 à la valeur de toutes les cartes adverses.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 * 
 */

public class Dragon extends CarteARetardement{

	public Dragon(Color couleur) {
		super(couleur, "Dragon", 11);
	}

	@Override
	public void Activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		int indexCarte = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		int indexJoueurProprietaire = data.getIndexProprietaireCarteInfluence(indexColonne, indexCarte);
		if(equals(data.resultatFinManche(indexColonne))) {
			for(int i=0; i<data.getPlateau().getColonnes()[indexColonne].getCartesInfluences().length;i++) {
				double nouvelleValeur = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(data.getPlateau().getColonne(indexColonne).getCarteInfluence(i));
				if(!equals(data.getJoueurs()[indexJoueurProprietaire].getCouleur())) {
					nouvelleValeur-=2;
				}
			}
		}
		
	}

}
