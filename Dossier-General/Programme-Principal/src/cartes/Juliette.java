package cartes;

import javafx.scene.paint.Color;

/** Cette classe définit les cartes <i>Influence</i> Juliette, qui n'ont pas de capacité spéciale et dont la valeur est quatorze. 
* 
* @author S3T - G1
* 
* @since 1.0
*/
public class Juliette extends CarteInfluence {
	
    /**
     * Ce constructeur produit une carte <i>Influence</i> Juliette de la couleur passée en paramètre.
     * 
     * @param couleur Couleur de la carte.
     * 
     * @since 1.0
     */
	public Juliette(Color couleur) {
		super(couleur, "Juliette", 14);
	}

}
