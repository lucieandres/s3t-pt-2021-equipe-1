package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Ecuyer dont la valeur est 2 et qui a une capacité qui s'active en fin de manche.
 * Si l'Ecuyer est dans la meme colonne que le Prince (meme couleur que l'Ecuyer), le joueur obtient l'objectif.
 * S'il y a plusieurs cartes Prince et Ecuyer dans la meme colonne, le joueur dont la carte Ecuyer ou Prince est la plus proche
 * de la carte <i>Objectif<i> remporte l'objectif.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Ecuyer extends CarteARetardement{

	public Ecuyer(Color couleur) {
		super(couleur, "Ecuyer", 2);
	}

	@Override
	public void Activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
