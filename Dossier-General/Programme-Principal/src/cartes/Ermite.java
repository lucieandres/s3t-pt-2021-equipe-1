package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences</i> Ermite dont la valeur est 12 et qui a une capacité qui s'active en fin de manche.</br>
 * <b>CP</b> : La valeur de l'Ermite diminue de 1 pour chaque autre carte présente dans la colonne.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Ermite extends CarteARetardement {

	/**
	 * Ce constructeur définit la carte <i>Influence</i> spéciale Ermite de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @author S3T - G1
	 * 
	 * @since 1.0
	 */
	public Ermite(Color couleur) {
		super(couleur, "Ermite", 12);
	}

	/**
	 * Active la capacité de l'Ermite
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
		for (CarteInfluence carteI : data.getPlateau().getColonne(indexColonne).getCartesInfluences()) {
			if (carteI != this && carteI != null) {
				this.setValeur(this.getValeur()-1);
			}
		}
	}
}
