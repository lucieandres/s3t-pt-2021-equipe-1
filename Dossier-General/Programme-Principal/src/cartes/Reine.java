package cartes;

import javafx.scene.paint.Color;

/** Cette classe définit les cartes <i>Influence</i> Reine, qui n'ont pas de capacité spéciale et dont la valeur est seize. 
* 
* @author S3T - G1
* 
* @since 1.0
*/
public class Reine extends CarteInfluence{
	
    /**
     * Ce constructeur produit une carte <i>Influence</i> Reine de la couleur passée en paramètre.
     * 
     * @param couleur Couleur de la carte.
     * 
     * @since 1.0
     */
	public Reine(Color couleur) {
		super(couleur, "Reine",16);
	}

}
