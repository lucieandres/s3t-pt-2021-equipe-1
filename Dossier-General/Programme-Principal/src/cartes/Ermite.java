package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Ermite dont la valeur est 12 et qui a une capacité qui s'active en fin de manche.
 * La valeur de l'Ermite diminue de 1 pour chaque autre carte présente dans la colonne.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Ermite extends CarteARetardement{

	public Ermite(Color couleur) {
		super(couleur, "Ermite", 12);
	}

	@Override
	public void Activer(Data data) throws Exception {
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		int indexCarte = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		double nouvelleValeur = this.getValeur();
		if(equals(data.resultatFinManche(indexColonne))) {
			for(int i=0;i<data.getPlateau().getColonne(indexColonne).getCartesInfluences().length;i++) {
				if(!(equals(data.getPlateau().getColonne(indexCarte).getCarteInfluence(indexCarte)))) {
					nouvelleValeur--;
				}
			}
		}
	}
}
