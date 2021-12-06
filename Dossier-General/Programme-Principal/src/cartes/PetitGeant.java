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

	protected PetitGeant(Color couleur, String nom, double valeur) {
		super(couleur, nom, valeur);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
