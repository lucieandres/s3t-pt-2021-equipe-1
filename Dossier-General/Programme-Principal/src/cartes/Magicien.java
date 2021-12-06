package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Magicien dont la valeur est 7 et qui a une capacité qui s'active en fin de manche.
 * Toutes les cartes de valeur 10 ou plus sont éliminée de la colonne.
 * Cette capacité s'applique aussi pour les cartes placées sous la Cape d'invisibilité.
 * S'il y a plusiseurs cartes Magicien dans la meme colonne leur capacité est annulée.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Magicien extends CarteARetardement{

	protected Magicien(Color couleur) {
		super(couleur, "Magicien", 7);
	}

	@Override
	public void Activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
