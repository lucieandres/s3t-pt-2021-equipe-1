package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Mendiant dont la valeur est 4 et a une capacité qui s'active en fin de manche.
 * Lorsque le Mendiant est joué, le joueur qui totalise le plus faible score dans la colonne remporte l'objectif.
 * S'il y a égalité, le joueur qui a la carte dont la position est la plus basse dans la colonne remporte l'objectif.
 * Si un joueur n'a pas de carte et/ou un Sosie sans valeur dans cette colonne, il ne participe pas à l'attribution de l'objectif.
 * Si un joueur n'a que la carte Cape d'invisibilité dans la colonne, il remporte l'objectif. 
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 *
 */

public class Mendiant extends CarteARetardement{

	public Mendiant(Color couleur) {
		super(couleur, "Mendiant", 4);
	}

	@Override
	public void activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
