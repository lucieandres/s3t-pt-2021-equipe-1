package cartes;

import moteur.Data;
import javafx.scene.paint.Color;

public class Assassin extends CarteSpeciale{
	
	public Assassin(Color couleur) {
		super(couleur, "Assassin", 9.5);
	}

	@Override
	//Si erreur au lancement, retourner un nouveau Data
	public void activer(Data data) throws Exception {
//		colonne.getPlateau().getDefausse().getCartesInfluences().add(colonne.getCartesInfluences().get(colonne.getCartesInfluences().size()-1));
//		colonne.getCartesInfluences().get(colonne.getCartesInfluences().size()-1).setColonne(null);
//		colonne.getCartesInfluences().remove(colonne.getCartesInfluences().size()-1);
		
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		int indexCarte = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		int indexJoueurProprietaire = data.getIndexProprietaireCarteInfluence(indexColonne, indexCarte);
		data.getJoueurs()[indexJoueurProprietaire].ajouterDansLaDefausse(data.getPlateau().getColonne(indexColonne).getCarteInfluence(indexCarte + 1));
		data.getPlateau().getColonne(indexColonne).enleverCarteInfluence(indexCarte + 1);
	}

}
