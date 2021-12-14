package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influence</i> Tempete dont la valeur est 9 (neuf) et qui ont une capacité spéciale qui s'active dès que la carte est retournée.</br>
 * <b>CP</b> : Lorsque la carte Tempete est retournée, la colonne est réalisée.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Tempete extends CarteSpeciale{
	
	/**
	 * Ce constructeur produit une carte <i>Influence</i> spéciale Tempete de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @since 1.0
	 */
	public Tempete(Color couleur) {
		super(couleur, "Tempête", 9);
	}

	/**
	 * Active la capacité spéciale de la carte Tempete.
	 * 
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
	@Override
	public void activer(Data data) throws Exception {
		data.getPlateau().getColonne(data.getPlateau().getIndexColonneCarte(this)).setComplete(true);
	}
}
