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
		int nbSorciere = 0;
		
		for(CarteInfluence carte : data.getPlateau().getColonne(indexColonne).getCartesInfluences()) {
			if (carte != null && carte instanceof  Sorciere) {
				nbSorciere++;
			}
		} 
		if (nbSorciere == 1) {
			CarteInfluence[] cartes = data.getPlateau().getColonne(indexColonne).getCartesInfluences();
			for(int i = 0 ; i<cartes.length ; i++) {
				if (cartes[i] instanceof CarteDouble) {
					if(cartes[i] != null && ((CarteDouble) cartes[i]).valeurUtilisee(data.getPlateau().getColonne(indexColonne).getCarteObjectif()) <9.5 && cartes[i] != this) {
						int indexCarteVisee = data.getPlateau().getIndexColonneCarte(cartes[i]);
						System.out.println("X Suppression de la Carte : " + cartes[i].getNom() + ", Valeur : " + ((CarteDouble) cartes[i]).valeurUtilisee(data.getPlateau().getColonne(indexColonne).getCarteObjectif()) + ", Joueur: " + data.getIndexJoueurParCouleur(cartes[i].getCouleur()) + ", Visibilité : " + cartes[i].getEstVisible());
						data.eliminerCarteData(indexColonne, cartes[i]);
						data.decalerCartes(indexColonne, indexCarteVisee);
						i--;
					}
					else {
						if (cartes[i] != null) System.out.println("V Non Suppression de la Carte : " + cartes[i].getNom() + ", Valeur : " + cartes[i].getValeur() + ", Joueur: " + data.getIndexJoueurParCouleur(cartes[i].getCouleur()) + ", Visibilité : " + cartes[i].getEstVisible());
					}
				}
				else {
					if(cartes[i] != null && cartes[i].getValeur()<9.5 && cartes[i] != this) { 
						int indexCarteVisee = data.getPlateau().getIndexColonneCarte(cartes[i]);
						System.out.println("X Suppression de la Carte : " + cartes[i].getNom() + ", Valeur : " + cartes[i].getValeur() + ", Joueur: " + data.getIndexJoueurParCouleur(cartes[i].getCouleur()) + ", Visibilité : " + cartes[i].getEstVisible());
						data.eliminerCarteData(indexColonne, cartes[i]);
						data.decalerCartes(indexColonne, indexCarteVisee);
						i--;
					}
					else {
						if (cartes[i] != null) System.out.println("V Non Suppression de la Carte : " + cartes[i].getNom() + ", Valeur : " + cartes[i].getValeur() + ", Joueur: " + data.getIndexJoueurParCouleur(cartes[i].getCouleur()) + ", Visibilité : " + cartes[i].getEstVisible());
					}
				} 
			}
		}
		else { 
			System.out.println("Carte désactivée car plusieurs sorcieres");
		}
	}
	
	
}
