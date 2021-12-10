package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Trois Mousquetaires dont la valeur est 11 et qui a une capacité qui s'active en fin de manche.
 * Toutes les capacités des cartes de la colonne sont écartées.
 * On ne considère que leur valeur initiale.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class TroisMousquetaires extends CarteARetardement{

	/**
	 * Ce constructeur produit une carte <i>Influence</i> spéciale à retardement Trois Mousquetaires de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @since 1.0
	 */
	public TroisMousquetaires(Color couleur) {
		super(couleur, "Trois Mousquetaires", 11);
	}

	/**
	 * Active la capacité spéciale de la carte Trois Mousquetaires.
	 * 
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
	@Override
	public void Activer(Data data) throws Exception {
		for (CarteInfluence carte : data.getPlateau().getColonne(data.getPlateau().getIndexColonneCarte(this)).getCartesInfluences()) {
			if (carte instanceof CarteSpeciale) {
				((CarteSpeciale) carte).setDesactiver(true);
			}
		}
	}

}
