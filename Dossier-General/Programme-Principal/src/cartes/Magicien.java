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
		int nbMagicien = 0;
		
		for(CarteInfluence carte : data.getPlateau().getColonne(indexColonne).getCartesInfluences()) {
			if (carte != null && carte instanceof Magicien) {
				nbMagicien++;
			}
		}
		
		if (nbMagicien == 1) {
			CarteInfluence[] cartes = data.getPlateau().getColonne(indexColonne).getCartesInfluences();
			for(int i = 0 ; i<cartes.length ; i++) {
				if (cartes[i] instanceof CarteDouble) {
					if(cartes[i] != null && ((CarteDouble) cartes[i]).valeurUtilisee(data.getPlateau().getColonne(indexColonne).getCarteObjectif())>9.5) {
						int indexCarteVisee = data.getPlateau().getIndexColonneCarte(cartes[i]);
						System.out.println("X Suppression de la Carte : " + cartes[i].getNom() + ", Joueur: " + data.getIndexJoueurParCouleur(cartes[i].getCouleur()) + ", Visibilité : " + cartes[i].getEstVisible());
						data.eliminerCarteData(indexColonne, cartes[i]);
						data.decalerCartes(indexColonne, indexCarteVisee);
						i--;
					}
					else {
						if (cartes[i] != null) System.out.println("V Non Suppression de la Carte : " + cartes[i].getNom() + ", Valeur : " + cartes[i].getValeur() + ", Joueur: " + data.getIndexJoueurParCouleur(cartes[i].getCouleur()) + ", Visibilité : " + cartes[i].getEstVisible());
					}
				}
				else {
					if(cartes[i] != null && cartes[i].getValeur()>9.5) {
						int indexCarteVisee = data.getPlateau().getIndexColonneCarte(cartes[i]);
						System.out.println("X Suppression de la Carte : " + cartes[i].getNom() + ", Joueur: " + data.getIndexJoueurParCouleur(cartes[i].getCouleur()) + ", Visibilité : " + cartes[i].getEstVisible());
						data.eliminerCarteData(indexColonne, cartes[i]);
						data.decalerCartes(indexColonne, indexCarteVisee );
						i--;
					}	
					else {
						if (cartes[i] != null) System.out.println("V Non Suppression de la Carte : " + cartes[i].getNom() + ", Valeur : " + cartes[i].getValeur() + ", Joueur: " + data.getIndexJoueurParCouleur(cartes[i].getCouleur()) + ", Visibilité : " + cartes[i].getEstVisible());
					}
				}
			}
			
		}
		else {
			System.out.println("Carte désactivée car plusieurs magiciens");
		}
	}
}
