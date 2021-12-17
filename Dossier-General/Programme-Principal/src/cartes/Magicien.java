package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influence<i> Magicien dont la valeur est 7 et qui a une capacité qui s'active en fin de manche.
 * Toutes les cartes de valeur 10 ou plus sont éliminées de la colonne.
 * Cette capacité s'applique aussi pour les cartes placées sous la Cape d'invisibilité.
 * S'il y a plusiseurs cartes Magicien dans la meme colonne leur capacité est annulée.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Magicien extends CarteARetardement{

	/**
	 * Ce constructeur produit une carte <i>Influence</i> spéciale à retardement Magicien de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @since 1.0
	 */
	public Magicien(Color couleur) {
		super(couleur, "Magicien", 7);
	}

	/**
	 * Active la capacité spéciale de la carte Magicien.
	 * 
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
	@Override
	public void activer(Data data) throws Exception {
		int indexColonne=data.getPlateau().getIndexColonneCarte(this);
		int indexCarte=data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		int nbMagicien = 0;
		
		for(CarteInfluence carte : data.getPlateau().getColonne(indexColonne).getCartesInfluences()) {
			if (carte != null && carte instanceof Magicien) {
				nbMagicien++;
			}
		}
		
		if (nbMagicien == 1) {
			for(CarteInfluence carte : data.getPlateau().getColonne(indexColonne).getCartesInfluences()) {
				if(carte != null && carte.getValeur()>=10) {
					data.getJoueursAvecIndex(data.getIndexJoueurParCouleur(carte.getCouleur())).ajouterDansLaDefausse(carte);
					data.getPlateau().getColonne(indexColonne).enleverCarteInfluence(indexCarte);
						
					this.decalerCartes(data.getPlateau().getColonne(indexColonne), indexCarte);
				}
			}
		}	
	}
}
