package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

public abstract class CarteSpeciale extends CarteInfluence {
	
	boolean desactivee;
	
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
     * @param desactivee Un booléen faux si la capacité spéciale peux s'activer, vrai sinon.
     * 
     * @since 1.0
     */
	//Constructeur basique
	protected CarteSpeciale(Color couleur, String nom, double valeur, boolean estVisible, boolean desactivee) {
		super(couleur, nom, valeur, estVisible, "NUL");
		this.desactivee = desactivee;
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
		super(couleur, nom, valeur);
		this.desactivee = false;
	}
	
	/**
     * Retourne l'état de la capacité spéciale de la carte.
     * 
     * @return True si elle est désactivée, false sinon.
     * 
     * @since 1.0
     */
	public boolean estDesactivee() {
		return desactivee;
	}


    /**
     * Modifie l'état de la capacité spéciale de la carte.
     * 
     * @param True si elle est désactivée, false sinon.
     * 
     * @since 1.0
     */
	public void setDesactivee(boolean desactivee) {
		this.desactivee = desactivee;
	}
	
	//Operations
	
    /**
     * Activation de la capacité spéciale.
     * 
     * @param data Les données de la partie.
     * 
     * @throws Exception carte introuvable.
     * 
     * @since 1.0
     */
	//Const
	public abstract void activer(Data data) throws Exception;
	
}
