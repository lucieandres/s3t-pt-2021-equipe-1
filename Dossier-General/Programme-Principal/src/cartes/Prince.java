package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Prince dont la valeur est 14 et qui a une capacité qui s'active en fin de manche.
 * Si le Prince est dans la meme colonne que l'Ecuyer (meme couleur que le Prince), le joueur obtient l'objectif.
 * S'il y a plusieurs cartes Prince et Ecuyer dans la meme colonne, le joueur dont la carte Ecuyer ou Prince est la plus proche
 * de la carte <i>Objectif<i> remporte l'objectif. 
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Prince extends CarteARetardement{

	public Prince(Color couleur) {
		super(couleur, "Prince", 14);
	}

	@Override
	public void activer(Data data) throws Exception {
		

//		
//
//			else if((data.getPlateau().getColonnes()[indexColonne].getCarteInfluence(i) instanceof Ecuyer)||(data.getPlateau().getColonnes()[indexColonne].getCarteInfluence(i) instanceof Prince)) {
//				
//			}
//			
//		}
		
	}
	
	public int combinaison(Data data, Color couleur) throws Exception{
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		int indexCarte = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		int indexJoueurProprietaire = data.getIndexProprietaireCarteInfluence(indexColonne, indexCarte);
		for (int i=0;i<data.getPlateau().getColonne(indexColonne).getCartesInfluences().length;i++) {
			if(data.getJoueurs()[indexJoueurProprietaire].getCouleur()==data.getPlateau().getColonne(indexColonne).getCarteInfluence(i).getCouleur()) {
				if(data.getPlateau().getColonne(indexColonne).getCarteInfluence(i) instanceof Ecuyer) {
					data.getJoueurs()[indexJoueurProprietaire].addCarteObjectif(data.getPlateau().getColonnes()[indexColonne].getCarteObjectif());
					return i;
				}	
			}
		}
		return 0;
	}

}
