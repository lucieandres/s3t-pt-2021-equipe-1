package cartes;

import elements.Plateau;
import javafx.scene.paint.Color;

public class Assassin extends CarteSpeciale{
	
	public Assassin(Color couleur) {
		super(couleur, "L'Assassin", 9.5);
	}

	//@Override
	public void Activer(Plateau plateau) throws Exception {
//		colonne.getPlateau().getDefausse().getCartesInfluences().add(colonne.getCartesInfluences().get(colonne.getCartesInfluences().size()-1));
//		colonne.getCartesInfluences().get(colonne.getCartesInfluences().size()-1).setColonne(null);
//		colonne.getCartesInfluences().remove(colonne.getCartesInfluences().size()-1);
		
		int indexColonne = plateau.getIndexColonneCarte(this);
		int indexCarte = plateau.getColonne(indexColonne).getIndexCarteInfluence(this);
	}

}
