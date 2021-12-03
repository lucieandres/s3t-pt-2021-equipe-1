package cartes;

import javafx.scene.paint.Color;

/**
 * Cette classe définit les cartes <i>Influence</i> Troubadour de domaine "Musique" dont la valeur est huit ou douze si elle est du meme domaine que la carte <i>Objectif</i> sous laquelle elle est placée.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Troubadour extends CarteDouble {
	
    /**
     * Ce constructeur produit une carte <i>Influence</i> Troubadour de la couleur passée en paramètre.
     * 
     * @param couleur Couleur de la carte.
     * 
     * @since 1.0
     */
	public Troubadour(Color couleur) {
		super(couleur, "Troubadour", "Musique");
	}
	
	/**
	 * Retourne la vraie valeur de la carte <i>Influence</i> entre sa valeur (8) et sa valeur spéciale (12).
	 * 
	 * @param domaine Le domaine de la carte <i>Objectif</i> de la colonne ou est placée la carte <i>Influence</i>.
	 * 
	 * @since 1.0
	 */
	public double valeurUtilisee(String domaine) {
		if (domaine.equals("Musique")) {
			return 12;
		} else {
			return 8;
		}
	}

}
