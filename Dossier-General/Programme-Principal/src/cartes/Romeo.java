package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * 
 * Cette classe définit les cartes <i>Influences<i> Roméo dont la valeur est 5 et qui a une capacité qui s'active en fin de manche.
 * Si Juliette (meme couleur que Roméo) se trouve dans la colonne, Roméo a une valeur de 15.  
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 *
 */

public class Romeo extends CarteARetardement{

	protected Romeo(Color couleur, String nom, double valeur) {
		super(couleur, "Romeo", 5);
	}

	@Override
	public void Activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
