package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influence</i> qui ont la spécificité d'avoir une capacité spéciale.
 * Elles ont une propriété supplémentaire : un boolean desactivee. 
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public abstract class CarteSpeciale extends CarteInfluence {
	
	boolean desactivee;
	
    /**
     * Ce constructeur produit une carte <i>Influence</i> spéciale en lui assignant sa couleur, son nom ainsi que sa valeur avec les variables qui ont été spécifiées. </br>
     * False sera assigné à sa visibilité par défaut, ce qui correspond à face cachée.</br>
     * NUL sera assigné à son infoReseau par défault.</br>
     * False sera assigné à son desactivee par défault, ce qui correspond une capacité spéciale utilisable.</br>
     * 
     * @param couleur La couleur de la carte <i>Influence</i>.
     * 
     * @param nom Le nom de la carte <i>Influence</i>.
     * 
     * @param valeur  La valeur de la carte <i>Influence</i>.
     * 
     * @since 1.0
     */
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
     * @param desactivee True si elle est désactivée, false sinon.
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
