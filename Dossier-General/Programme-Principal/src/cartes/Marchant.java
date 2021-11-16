package cartes;

import javafx.scene.paint.Color;

/**
 * Cette classe définit les cartes <i>Influence</i> Marchant de domaine "Commerce" dont la valeur est huit ou douze si elle est du meme domaine que la carte <i>Objectif</i> sous laquelle elle est placée.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Marchant extends CarteDouble{
	
    /**
     * Ce constructeur produit une carte <i>Influence</i> Marchant de la couleur passée en paramètre.
     * 
     * @param couleur Couleur de la carte.
     * 
     * @since 1.0
     */
	public Marchant(Color couleur) {
		super(couleur, "Marchant", "Commerce");
	}

}
