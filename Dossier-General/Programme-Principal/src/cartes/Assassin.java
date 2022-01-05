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
		int indexAssassin = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		System.out.println("indexAssassin : " + indexAssassin + " indexAssassin + 1 : " + (indexAssassin + 1));
		int indexJoueurProprietaire = -1; 
		if(data.getPlateau().getColonne(indexColonne).getCarteInfluence(indexAssassin + 1) != null) {
			indexJoueurProprietaire = data.getIndexProprietaireCarteInfluence(indexColonne, (indexAssassin + 1));
		}
		if(indexJoueurProprietaire != -1) {
			carteDetruite = data.getPlateau().getColonne(indexColonne).getCarteInfluence((indexAssassin + 1));
//			carteDetruite = data.getPlateau().getColonne(indexColonne).getCarteInfluence(1);
			System.out.println("Carte en index 0 (ref) : " + data.getPlateau().getColonne(indexColonne).getCarteInfluence(0) + "Nom : " + data.getPlateau().getColonne(indexColonne).getCarteInfluence(0).getNom());
			System.out.println("Carte assassin activée, Ref : " + this + ", Joueur : " + data.getIndexJoueurParCouleur(this.getCouleur()) + ", Index : " + indexAssassin + ", Colonne : " + data.getPlateau().getIndexColonneCarte(this)); 
			System.out.println("Carte supprimée : " + carteDetruite.getNom() + ", Ref :  " + carteDetruite + ", Joueur : " + data.getIndexJoueurParCouleur(carteDetruite.getCouleur())+ ", Index : " + data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(carteDetruite)+ ", Colonne : " + data.getPlateau().getIndexColonneCarte(carteDetruite));
			data.eliminerCarteData(indexColonne, carteDetruite); 
		}
	}

	/**
	 * retourne la carte détruite.
	 *
	 * @since 1.0
	 */
	public CarteInfluence getCarteDetruite() {
		return this.carteDetruite;
	}
	
	
	/**
	 * définit la carte détruite.
	 *
	 * @since 1.0
	 */
	public void setCarteDetruite(CarteInfluence ci) {
		this.carteDetruite = ci;
	} 

}
