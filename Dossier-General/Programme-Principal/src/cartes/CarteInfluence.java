package cartes;

import elements.Colonne;
import javafx.scene.paint.Color;

/**
 * Cette classe définit une carte <i>Influence</i> ainsi que toutes ses propriétés ; son nom, sa couleur, sa valeur et sa visibilité. Il existe cent cinquante de ces cartes. 
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public abstract class CarteInfluence {

    private String nom;

    private Color couleur;

    private double valeur;

    private boolean estVisible;

    /**
     * Ce constructeur produit une carte <i>Influence</i> en lui assignant sa couleur, son nom, sa valeur et sa visibilité avec les variables qui ont été spécifiées.
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
    protected CarteInfluence(Color couleur, String nom, double valeur, boolean estVisible) {
        this.nom = nom;
        this.valeur = valeur;
        this.estVisible = estVisible;
        this.couleur = couleur;
    }

    /**
     * Ce constructeur produit une carte <i>Influence</i> en lui assignant sa couleur, son nom ainsi que sa valeur avec les variables qui ont été spécifiées.
     * False sera assigné à sa visibilité par défaut, ce qui correspond à face cachée.
     * 
     * @param couleur La couleur de la carte <i>Influence</i>.
     * 
     * @param nom Le nom de la carte <i>Influence</i>.
     * 
     * @param valeur La valeur de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
    //Constructeur simplifié
    protected CarteInfluence(Color couleur, String nom, int valeur) {
        this(couleur, nom, valeur, true);
    }

    /**
     * Retourne la couleur de la carte <i>Influence</i>.
     * 
     * @return La couleur de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
    public Color getCouleur() {
    	return couleur;
    }
    /**
     * Modifie la couleur de la carte <i>Influence</i>.
     * 
     * @param couleur La couleur de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
     * Retourne le nom de la carte <i>Influence</i>.
     * 
     * @return La nom de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
	public String getNom() {
        return this.nom;
    }

	/**
     * Modifie le nom de la carte <i>Influence</i>.
     * 
     * @param nom Le nom de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la valeur de la carte <i>Influence</i>.
     * 
     * @return La valeur de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
    public double getValeur() {
        return this.valeur;
    }

    /**
     * Modifie la valeur de la carte <i>Influence</i>.
     * 
     * @param valeur Le nom de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    /**
     * Retourne vrai si la carte <i>Influence</i> est face visible, faux si elle ne l'est pas.
     * 
     * @return La valeur de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
    public boolean getEstVisible() {
        return this.estVisible;
    }

    /**
     * Modifie la visibilité de la carte <i>Influence</i>.
     * 
     * @param estVisible La visiblité de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
    public void setEstVisible(boolean estVisible) {
        this.estVisible = estVisible;
    }

    /**
     * Place la carte <i>Influence</i> en face visible.
     * 
     * @since 1.0
     */
    public void Reveler() {
    	this.estVisible = true;
    }
}
