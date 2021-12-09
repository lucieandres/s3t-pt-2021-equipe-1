package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influence</i> spéciales Explorateur dont la valeur est treize.
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
	 * @throws Exception 
	 * 
	 * @since 1.0
	 */
	@Override
	public void Activer(Data data) throws Exception {
		int indexColonneActuelle = data.getPlateau().getIndexColonneCarte(this);
		int nbColonne = data.getPlateau().getColonnes().length; 	
		int indexColonneVisee =  (indexColonneActuelle+1)%nbColonne;

		
		indexColonneVisee=(indexColonneVisee+DetecteurTempete(data, indexColonneVisee, nbColonne))%nbColonne;
			
		CarteInfluence carteCachée = data.getPlateau().getColonne(indexColonneActuelle).getCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getIndexCarteInfluence(this)+1);
		
		data.getPlateau().getColonne(indexColonneActuelle).enleverCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getIndexCarteInfluence(this)+1);
		data.getPlateau().getColonne(indexColonneActuelle).enleverCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getIndexCarteInfluence(this));
		data.getPlateau().getColonne(indexColonneActuelle).ajouterCarteInfluence(carteCachée);
		data.getPlateau().getColonne(indexColonneVisee).ajouterCarteInfluence(this);
		
	}
	
	public Integer DetecteurTempete(Data data, int colonneSus, int nbColonne){
		if (data.getPlateau().getColonne(colonneSus).getCarteInfluence(0)==null) {
			return 0;
		} else {
			for (CarteInfluence carte : data.getPlateau().getColonne(colonneSus).getCartesInfluences()) {
				if(carte == null) {
					return 0;
				}
				if (carte.getNom().equals("La Tempete")) {
					return DetecteurTempete(data, (colonneSus+1)%nbColonne, nbColonne)+1;
				} 
			}
			return 0;
		}
	}
}
