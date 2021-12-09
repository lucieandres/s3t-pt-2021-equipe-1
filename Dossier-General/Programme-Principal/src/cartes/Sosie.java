package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Sosie qui n'a pas de valeur initiale et dont la capacité s'active en fin de manche.
 * Le Sosie prend la valeur de la carte qui se trouve en-dessous de lui. Il imite la valeur mais pas la capacité spéciale.
 * Si la carte en-dessous du Sosie est éliminée, il prend la valeur de la carte suivante.
 * Si il n'y a pas de carte en-dessous, le Sosie n'a pas de valeur.
 * Si un autre Sosie se trouve en-dessous du Sosie, la carte supérieure prend la valeur du Sosie inférieur si celui-ci a une valeur.
 *   
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 *
 */

public class Sosie extends CarteARetardement{

	public Sosie(Color couleur) {
		super(couleur, "Sosie", 0);
	}

	@Override
	public void Activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
