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

	protected TroisMousquetaires(Color couleur, String nom, double valeur) {
		super(couleur, "Trois Mousquetaires", 11);
	}

	@Override
	public void Activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
