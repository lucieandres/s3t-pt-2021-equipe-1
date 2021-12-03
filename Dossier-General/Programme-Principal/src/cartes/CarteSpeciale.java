package cartes;

import elements.Plateau;
import javafx.scene.paint.Color;

public abstract class CarteSpeciale extends CarteInfluence {
	
    /**
     * Ce constructeur produit une carte <i>Influence</i> spéciale en lui assignant sa couleur, son nom, sa valeur, sa visibilité avec les variables qui ont été spécifiées.
     * 
     * @param couleur La couleur de la carte <i>Influence</i>.
     * 
     * @param nom Le nom de la carte <i>Influence</i>.
     * 
     * @param valeur La valeur de la carte <i>Influence</i>.
     * 
     * @param estVisible Un booléen vrai si la carte <i>Influence</i> est face visible, faux si elle est face cachée.
     * 
     * @since 1.0
     */
	//Constructeur basique
	protected CarteSpeciale(Color couleur, String nom, double valeur, boolean estVisible) {
		super(couleur, nom, valeur, estVisible);
	}
	
    /**
     * Ce constructeur produit une carte <i>Influence</i> spéciale en lui assignant sa couleur, son nom ainsi que sa valeur avec les variables qui ont été spécifiées.
     * False sera assigné à sa visibilité par défaut, ce qui correspond à face cachée.
     * 
     * @param couleur La couleur de la carte <i>Influence</i>.
     * 
     * @param nom Le nom de la carte <i>Influence</i>.
     * 
     * @param valeur  La valeur de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
	//Constructeur simplifié
	protected CarteSpeciale(Color couleur, String nom, double valeur) {
		super(couleur, nom, valeur, false);
	}
	
	//Operations
	
    /**
     * Activation de la capacité spéciale.
     * 
     * @param data Les données de la partie.
     * 
     * @since 1.0
     */
	//Const
	public abstract void Activer(Data data) throws Exception;
	
}
