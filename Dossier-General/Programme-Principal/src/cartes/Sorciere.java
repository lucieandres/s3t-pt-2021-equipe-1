package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influence<i> Sorcière dont la valeur est 1 et qui a une capacité qui s'active en fin de manche.</br>
 * <b>CP</b> : Toutes les cartes de valeur 9 ou moins (sauf Sorcière elle-meme) sont éliminées de la colonne.
 * Cette capacité s'applique aussi pour les cartes placées sous la Cape d'invisibilité.
 * S'il y a plusiseurs cartes Sorcière dans la meme colonne leur capacité est annulée. 
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Sorciere extends CarteARetardement{

	/**
	 * Ce constructeur produit une carte <i>Influence</i> spéciale à retardement Sorcière de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @since 1.0
	 */
	public Sorciere(Color couleur) {
		super(couleur, "Sorcière", 1);
	}

	/**
	 * Active la capacité spéciale de la carte Sorcière.
	 * 
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
	@Override
	public void activer(Data data) throws Exception {
		int indexColonne=data.getPlateau().getIndexColonneCarte(this);
		int indexCarte=data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		int nbSorciere = 0;
		
		for(CarteInfluence carte : data.getPlateau().getColonne(indexColonne).getCartesInfluences()) {
			if (carte != null && carte instanceof  Sorciere) {
				nbSorciere++;
			}
		} 
		if (nbSorciere == 1) {
			for(CarteInfluence carte : data.getPlateau().getColonne(indexColonne).getCartesInfluences()) {
				if (carte instanceof CarteDouble) {
					if(carte != null && ((CarteDouble) carte).valeurUtilisee(data.getPlateau().getColonne(indexColonne).getCarteObjectif())<=9) {
						data.eliminerCarteData(indexColonne, carte);
						this.decalerCartes(data.getPlateau().getColonne(indexColonne), indexCarte);
						System.out.println("X Suppression de la Carte : " + carte.getNom() + ", Valeur : " + ((CarteDouble) carte).valeurUtilisee(data.getPlateau().getColonne(indexColonne).getCarteObjectif()) + ", Joueur: " + data.getIndexJoueurParCouleur(carte.getCouleur()));
					}
					else {
						if (carte != null) System.out.println("V Non Suppression de la Carte : " + carte.getNom() + ", Valeur : " + carte.getValeur() + ", Joueur: " + data.getIndexJoueurParCouleur(carte.getCouleur()));
					}
				}
				else {
					if(carte != null && carte.getValeur()<=9 && carte != this) {  
						data.eliminerCarteData(indexColonne, carte);
						this.decalerCartes(data.getPlateau().getColonne(indexColonne), indexCarte);
						System.out.println("X Suppression de la Carte : " + carte.getNom() + ", Valeur : " + carte.getValeur() + ", Joueur: " + data.getIndexJoueurParCouleur(carte.getCouleur()));
					}
					else {
						if (carte != null) System.out.println("V Non Suppression de la Carte : " + carte.getNom() + ", Valeur : " + carte.getValeur() + ", Joueur: " + data.getIndexJoueurParCouleur(carte.getCouleur()));
					}
				} 
			}
		}
		else { 
			this.setDesactivee(true);
			System.out.println("Carte désactivée car plusieurs sorcieres");
		}
	}
	
	
}
