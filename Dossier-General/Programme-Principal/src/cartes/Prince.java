package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Prince dont la valeur est 14 et qui a une capacité qui s'active en fin de manche.
 * Si le Prince est dans la meme colonne que l'Ecuyer (meme couleur que le Prince), le joueur obtient l'objectif.
 * S'il y a plusieurs cartes Prince et Ecuyer dans la meme colonne, le joueur dont la carte Ecuyer ou Prince est la plus proche
 * de la carte <i>Objectif<i> remporte l'objectif. 
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Prince extends CarteARetardement{

	protected Prince(Color couleur) {
		super(couleur, "Prince", 14);
	}

	@Override
	public void Activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
