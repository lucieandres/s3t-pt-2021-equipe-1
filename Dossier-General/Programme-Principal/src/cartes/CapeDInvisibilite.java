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
	public void Activer(Data data) throws Exception {
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		int indexCarte = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		int indexJoueurProprietaire = data.getIndexProprietaireCarteInfluence(indexColonne, indexCarte);
//		if(data.getPlateau().getColonne(indexColonne).getCarteInfluence(indexCarte).getEstVisible()) {
//			if(equals(data.getJoueurs()[indexJoueurProprietaire].setCarteSelectionnee(0))) {
//				
//				data.getJoueurs()[indexJoueurProprietaire].ajouterCarteInfluence(data.getJoueurs()[indexJoueurProprietaire].getCarteInfluenceAleatoireDansReserve());
//			}
//			else if(equals(data.getJoueurs()[indexJoueurProprietaire].setCarteSelectionnee(1))) {
//				
//				data.getJoueurs()[indexJoueurProprietaire].ajouterCarteInfluence(data.getJoueurs()[indexJoueurProprietaire].getCarteInfluenceAleatoireDansReserve());
//			}
//			else if(equals(data.getJoueurs()[indexJoueurProprietaire].setCarteSelectionnee(1))){
//				data.getJoueurs()[indexJoueurProprietaire].ajouterCarteInfluence(data.getJoueurs()[indexJoueurProprietaire].getCarteInfluenceAleatoireDansReserve());
//			}
//			
//		}
		
	}

}
