package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Petit Géant dont la valeur est 2 et qui a une capacité qui s'active en fin de manche.</br>
 * <b>CP</b> : La valeur du Petit Géant augmente de 3 pour chaque autre carte présente dans la colonne.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class PetitGeant extends CarteARetardement{

	/**
	 * Ce constructeur définit la carte <i>Influence</i> spéciale Petit Géant de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @author S3T - G1
	 * 
	 * @since 1.0
	 */
	public PetitGeant(Color couleur) {
		super(couleur, "Petit Géant", 2);
	}

	
	/**
	 * Active la capacité du Petit Géant
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
