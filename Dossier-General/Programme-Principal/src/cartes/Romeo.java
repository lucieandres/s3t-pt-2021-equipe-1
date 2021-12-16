package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * 
 * Cette classe définit les cartes <i>Influence<i> Roméo dont la valeur est 5 et qui a une capacité qui s'active en fin de manche.
 * Si Juliette (meme couleur que Roméo) se trouve dans la colonne, Roméo a une valeur de 15.  
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 *
 */

public class Romeo extends CarteARetardement{

	public Romeo(Color couleur) {
		super(couleur, "Roméo", 5);
	}

	@Override
	public void activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		for(CarteInfluence carte : data.getPlateau().getColonne(data.getPlateau().getIndexColonneCarte(this)).getCartesInfluences()) {
			if(carte instanceof Juliette && carte.getCouleur() == this.getCouleur()) {
					this.setValeur(this.getValeur() + 10);
			}
		}
	}

}
