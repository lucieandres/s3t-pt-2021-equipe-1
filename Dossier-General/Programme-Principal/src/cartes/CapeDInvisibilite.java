package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Cape d'invisibilité dont la valeur est 0 et qui a une capacité qui s'active dès qu'elle est retournée.
 * Lorsque la Cape d'invisibilité est retournée, son propriétaire peut cacher en-dessous une des cartes qu'il a dans sa main 
 * à l'abri du regard des autres joueurs et piocher une nouvelle carte <i>Influence<i>.
 * La carte placée sous la Cape d'invisibilité est révélée en fin de manche. 
 * Si elle a une capacité spéciale, elle est activée normalement (sauf Explorateur, Assassin, Tempete ou Traitre).
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class CapeDInvisibilite extends CarteSpeciale{

	public CapeDInvisibilite(Color couleur) {
		super(couleur, "Cape d’invisibilité", 0);
	}

	@Override
	public void activer(Data data) throws Exception { //non fini !!
		
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		int indexCarte = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		int indexJoueurProprietaire = data.getIndexProprietaireCarteInfluence(indexColonne, indexCarte);
		int indexPioche = data.getJoueurs()[indexJoueurProprietaire].getCarteInfluenceAleatoireDansReserve();
//		CarteInfluence carte = data.getPlateau().getColonnes()[indexColonne].getCartesInfluences()[0];
		if(data.getPlateau().getColonne(indexColonne).getCarteInfluence(indexCarte).getEstVisible()) {
			if(equals(data.getJoueurs()[indexJoueurProprietaire].getMain()[0])) {
				//data.getPlateau().ajouterColonnes(indexColonne, data.getJoueurs()[indexJoueurProprietaire].getMain()[0]);
				data.getJoueurs()[indexJoueurProprietaire].ajouterCarteInfluence(data.getJoueurs()[indexJoueurProprietaire].getReserve()[indexPioche]);
			}
			else if(equals(data.getJoueurs()[indexJoueurProprietaire].getMain()[1])) {
				//data.getPlateau().ajouterColonnes(indexColonne, data.getJoueurs()[indexJoueurProprietaire].getMain()[1]);	
				data.getJoueurs()[indexJoueurProprietaire].ajouterCarteInfluence(data.getJoueurs()[indexJoueurProprietaire].getReserve()[indexPioche]);

			}
			else if(equals(data.getJoueurs()[indexJoueurProprietaire].getMain()[2])){
				//data.getPlateau().ajouterColonnes(indexColonne, data.getJoueurs()[indexJoueurProprietaire].getMain()[0]);
				data.getJoueurs()[indexJoueurProprietaire].ajouterCarteInfluence(data.getJoueurs()[indexJoueurProprietaire].getReserve()[indexPioche]);
			}
//			else if((carte instanceof CarteSpeciale)&&(!(carte instanceof CarteARetardement))){
//				carte.setDesactiver();	
//			}
		
		}
	}
}


