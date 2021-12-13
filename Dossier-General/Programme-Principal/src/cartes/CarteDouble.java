package cartes;

import javafx.scene.paint.Color;

/**
 * Cette classe définit les cartes <i>Influence</i> doubles qui on la spécificité est de changer de valeur si elles ont le meme domaine que la carte <i>Objectif</i> sous laquelle elles sont posées. 
 * Elles ont des propriétés supplémentaires : une valeur spéciale et un domaine. 
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public abstract class CarteDouble extends CarteInfluence {

	private double valeurSpeciale;

	private String domaine;

    /**
     * Ce constructeur produit une carte <i>Influence</i> double en lui assignant sa couleur, son nom ainsi que son domaine avec les variables qui ont été spécifiées. </br>
     * 8 sera assigné à sa valeur par défaut.</br>
     * False sera assigné à sa visibilité par défaut, ce qui correspond à face cachée.</br>
     * NUL sera assigné à son infoReseau par défault.</br>
     * 12 sera assigné à sa valeur spéciale par défaut.</br>
     * 
     * @param couleur La couleur de la carte <i>Influence</i>.
     * 
     * @param nom Le nom de la carte <i>Influence</i>.
     * 
     * @param domaine Le domaine de la carte parmi les suivants : "Alchimie", "Combat", "Agriculture", "Commerce", "Religion" et "Musique".
     * 
     * @since 1.0
     */
	protected CarteDouble(Color couleur, String nom, String domaine) {
		super(couleur, nom, 8);
		this.valeurSpeciale = 12;
		this.domaine = domaine;
	}

    /**
     * Retourne la valeur spéciale de la carte <i>Influence</i>.
     * 
     * @return La valeur spéciale de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
	public double getValeurSpeciale() {
		return valeurSpeciale;
	}

    /**
     * Modifie la valeur spéciale de la carte <i>Influence</i>.
     * 
     * @param valeurSpeciale La valeur spéciale de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
	public void setValeurSpeciale(double valeurSpeciale) {
		this.valeurSpeciale = valeurSpeciale;
	}

    /**
     * Retourne le domaine de la carte <i>Influence</i>.
     * 
     * @return Le domaine de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
	public String getDomaine() {
		return domaine;
	}

    /**
     * Modifie le domaine de la carte <i>Influence</i>.
     * 
     * @param domaine Le domaine de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

    /**
     * Retourne la vraie valeur de la carte <i>Influence</i> entre sa valeur et sa valeur spéciale.
     * 
     * @param objectif La carte <i>Objectif</i> de la colonne ou est placée la carte <i>Influence</i>.
     * 
     * @return la vraie valeur de la carte.
     * 
     * @since 1.0
     */
	public double valeurUtilisee(CarteObjectif objectif) {
		if (this.domaine == objectif.getDomaine()) {
			return this.valeurSpeciale;
		}
		else {
			return this.getValeur();
		}
	}

}
