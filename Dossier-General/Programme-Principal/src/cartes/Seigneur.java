package cartes;

import javafx.scene.paint.Color;

/**
 * Cette classe définit les cartes <i>Influence</i> Seigneur de domaine "Agriculture" dont la valeur est huit ou douze si elle est du meme domaine que la carte <i>Objectif</i> sous laquelle elle est placée.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Seigneur extends CarteDouble {
	
    /**
     * Ce constructeur produit une carte <i>Influence</i> Seigneur de la couleur passée en paramètre.
     * 
     * @param couleur Couleur de la carte.
     * 
     * @since 1.0
     */
	public Seigneur(Color couleur) {
		super(couleur, "Seigneur", "Agriculture");
	}

}
