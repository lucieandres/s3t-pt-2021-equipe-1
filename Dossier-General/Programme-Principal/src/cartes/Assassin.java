package cartes;

import moteur.Data;
import javafx.scene.paint.Color;

/**
 * Cette classe définit les cartes <i>Influence</i> Assassin dont la valeur est 9,5 (neuf et demi) et qui ont une capacité spéciale qui s'active dès que la carte est retournée.</br>
 * <b>CP</b> : La carte qui revele l'Assassin est immédiatement retiré et placé dans la défausse de son proprietaire.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Assassin extends CarteSpeciale{
	
	private CarteInfluence carteDetruite;
	
	/**
	 * Ce constructeur produit une carte <i>Influence</i> spéciale Assassin de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @since 1.0
	 */
	public Assassin(Color couleur) {
		super(couleur, "Assassin", 9.5);
	}

	/**
	 * Active la capacité spéciale de la carte Assassin.
	 *
	 * @since 1.0
	 */
	@Override
	//Si erreur au lancement, retourner un nouveau Data
	public void activer(Data data) throws Exception {	
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		int indexCarte = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		int indexJoueurProprietaire = data.getIndexProprietaireCarteInfluence(indexColonne, indexCarte);
		data.getJoueurs()[indexJoueurProprietaire].ajouterDansLaDefausse(data.getPlateau().getColonne(indexColonne).getCarteInfluence(indexCarte + 1));
		data.getPlateau().getColonne(indexColonne).enleverCarteInfluence(indexCarte + 1);
		
		this.setDesactivee(true);
	}
	
	public CarteInfluence getCarteDetruite() {
		return this.carteDetruite;
	}
	
	public void setCarteDetruite(CarteInfluence ci) {
		this.carteDetruite = ci;
	}

}
