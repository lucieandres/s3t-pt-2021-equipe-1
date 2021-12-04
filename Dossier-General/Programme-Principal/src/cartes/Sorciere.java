package cartes;

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

	protected Sorciere(Color couleur, String nom, double valeur) {
		super(couleur, nom, valeur);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
