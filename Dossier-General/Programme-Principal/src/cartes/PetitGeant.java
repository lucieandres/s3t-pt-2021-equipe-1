package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Petit Géant dont la valeur est 2 et qui a une capacité qui s'active en fin de manche.
 * La valeur du Petit Géant augmente de 3 pour chaque autre carte présente dans la colonne.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class PetitGeant extends CarteARetardement{

	public PetitGeant(Color couleur) {
		super(couleur, "Petit Géant", 2);
	}

	@Override
	public void Activer(Data data) throws Exception {
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		int indexCarte = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		double nouvelleValeur = this.getValeur();
		if(equals(data.resultatFinManche(indexColonne))) {
			for(int i=0;i<data.getPlateau().getColonne(indexColonne).getCartesInfluences().length;i++) {
				if(!(equals(data.getPlateau().getColonne(indexColonne).getCarteInfluence(indexCarte)))) {
					nouvelleValeur+= 3;
				}
			}
		}
		
	}

}
