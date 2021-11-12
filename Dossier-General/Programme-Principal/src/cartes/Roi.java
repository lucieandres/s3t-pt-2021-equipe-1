package cartes;

import javafx.scene.paint.Color;

/** Cette classe définit les cartes <i>Influence</i> Roi, qui n'ont pas de capacité spéciale et dont la valeur est vingt. 
* 
* @author S3T - G1
* 
* @since 1.0
*/
public class Roi extends CarteInfluence{
	
    /**
     * Ce constructeur produit une carte <i>Influence</i> Roi de la couleur passée en paramètre.
     * 
     * @param couleur Couleur de la carte.
     * 
     * @since 1.0
     */
	public Roi(Color couleur) {
		super(couleur, "Roi",20);
	}
}
