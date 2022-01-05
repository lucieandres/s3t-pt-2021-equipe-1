package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Dragon dont la valeur est 11 et qui a une capacité qui s'active en fin de manche.</br>
 * <b>CP</b> : Le dragon enlève 2 à la valeur de toutes les cartes dans sa colonne sauf pour les cartes du propriétaire du Dragon.
 * S'il y a plusieurs Dragons dans la meme colonne, chacun enlève 2 à la valeur de toutes les cartes adverses.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 * 
 */

public class Dragon extends CarteARetardement{

	/**
	 * Ce constructeur définit la carte <i>Influence</i> spéciale Dragon de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @author S3T - G1
	 * 
	 * @since 1.0
	 */
	public Dragon(Color couleur) {
		super(couleur, "Dragon", 11);
	}

	/**
	 * Active la capacité du Dragon
	 * 
	 * @throws Exception 
	 * 
	 * @author S3T - G1
	 * 
	 * @since 1.0
	 */
	@Override
	public void activer(Data data) throws Exception {
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		for(int i=0; i<data.getPlateau().getColonnes()[indexColonne].getCartesInfluences().length;i++) {
			if(data.getPlateau().getColonnes()[indexColonne].getCarteInfluence(i)!= null && this.getCouleur() != data.getPlateau().getColonnes()[indexColonne].getCarteInfluence(i).getCouleur()) {
				data.getPlateau().getColonnes()[indexColonne].getCarteInfluence(i).setValeur(data.getPlateau().getColonnes()[indexColonne].getCarteInfluence(i).getValeur()-2);
			}
		}
	}

}
