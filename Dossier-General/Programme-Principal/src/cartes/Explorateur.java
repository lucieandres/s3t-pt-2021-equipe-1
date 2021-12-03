package cartes;

import elements.Colonne;
import elements.Plateau;
import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influence</i> spéciale Explorateur dont la valeur est treize.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Explorateur extends CarteSpeciale{
	
	/**
	 * Ce constructeur produit une carte <i>Influence</i> spéciale Explorateur de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @since 1.0
	 */
	public Explorateur(Color couleur) {
		super(couleur, "L'Explorateur", 13);
	}

	
	/**
	 * Active la capacité spéciale de la carte Explorateur.
	 * 
	 * @param plateau plateau du jeu.
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
//	@Override
	public void Activer(Data data) throws Exception {
		int indexColonneActuelle = data.getPlateau().getIndexColonneCarte(this);
		int indexColonneVisee = indexColonneActuelle+1;
		int nbColonne = data.getPlateau().getColonnes().length; 
		
		for (int i = 0; i <= data.getPlateau().getColonnes().length; i++) {
			for(int j=0; j <= data.getPlateau().getColonne(indexColonneVisee).getCartesInfluences().length; j++) {
				if(data.getPlateau().getColonne(indexColonneVisee).getCarteInfluence(i).getNom()=="Tempete") {
					indexColonneVisee +=1;
				}
				else {
					break;
				}
			}

		indexColonneVisee=indexColonneVisee%nbColonne;
			
		data.getPlateau().getColonne(indexColonneVisee).ajouterCarteInfluence(this);
		data.getPlateau().getColonne(indexColonneActuelle).enleverCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getCartesInfluences().length-1);
		data.getPlateau().getColonne(indexColonneVisee).ajouterCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getCartesInfluences().length));
		data.getPlateau().getColonne(indexColonneActuelle).enleverCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getCartesInfluences().length);
		
		}
	}
	
}
