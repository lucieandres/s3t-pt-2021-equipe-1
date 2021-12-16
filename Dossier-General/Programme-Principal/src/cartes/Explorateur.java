package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influence</i> Explorateur dont la valeur est 13 (treize) et qui ont une capacité spéciale qui s'active dès que la carte est retournée.</br>
 * <b>CP</b> : Lorsque la carte est retournée, l'Explorateur se déplace immédiatement sur la colonne de droite (ou la premiere colonne de gauche si il est dans la derniere colonne de droite).
 * Il y est alors posé face cachée en bas de la colonne. Cette action se produit a chaque fois que l'Explorateur est dévoilé.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Explorateur extends CarteSpeciale{
	
	private int indexColonneVisee;
	
	/**
	 * Ce constructeur produit une carte <i>Influence</i> spéciale Explorateur de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @since 1.0
	 */
	public Explorateur(Color couleur) {
		super(couleur, "Explorateur", 13);
	}

	
	/**
	 * Active la capacité spéciale de la carte Explorateur.
	 * 
	 * @since 1.0
	 */
	@Override
	public void activer(Data data) throws Exception {
		int indexColonneActuelle = data.getPlateau().getIndexColonneCarte(this);
		int nbColonne = data.getPlateau().getColonnes().length; 	
		indexColonneVisee = (indexColonneActuelle+1)%nbColonne; indexColonneVisee = (indexColonneVisee+DetecteurTempete(data, indexColonneVisee, nbColonne))%nbColonne;
		CarteInfluence carteCachee = data.getPlateau().getColonne(indexColonneActuelle).getCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getIndexCarteInfluence(this)+1);
		
		data.getPlateau().getColonne(indexColonneActuelle).enleverCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getIndexCarteInfluence(this)+1);
		data.getPlateau().getColonne(indexColonneActuelle).enleverCarteInfluence(data.getPlateau().getColonne(indexColonneActuelle).getIndexCarteInfluence(this));
		data.getPlateau().getColonne(indexColonneActuelle).ajouterCarteInfluence(carteCachee);
		data.getPlateau().getColonne(indexColonneVisee).ajouterCarteInfluence(this);
		this.setEstVisible(false);
	}
	
	/**
	 * Detecte si il y a une carte <i>Influence</i> Tempete dans les colonnes suivantes.
	 * 
	 * @param data Les données de la partie.
	 * 
	 * @param colonneSus La colonne dans laquelle on verifie si il y a une carte <i>Influence</i> Tempete.
	 * 
	 * @param nbColonne Le nombre de colonne sur le plateau.
	 * 
	 * @return le nombre de colonne successive possedant une carte <i>Influence</i> Tempete.
	 * 
	 * @since 1.0
	 */
	public Integer DetecteurTempete(Data data, int colonneSus, int nbColonne){
		if (data.getPlateau().getColonne(colonneSus).getCarteInfluence(0)==null) {
			return 0;
		} 
		else {
			for (CarteInfluence carte : data.getPlateau().getColonne(colonneSus).getCartesInfluences()) {
				if(carte == null) {
					return 0;
				}
				if (carte.getNom().equals("Tempête")) {
					return DetecteurTempete(data, (colonneSus+1)%nbColonne, nbColonne)+1;
				} 
			}
			return 0; //Valeur non accessible mais necessaire
		}
	}
	
	/**
	 * retourne la colonne visée.
	 *
	 * @since 1.0
	 */
	public int getIndexColonneVisee() {
		return this.indexColonneVisee;
	}
	
	
	/**
	 * définit la colonne visée.
	 *@param nc le numéro de la colonne visée.
	 * @since 1.0
	 */
	public void setIndexColonneVisee (int nc) {
		this.indexColonneVisee = nc;
	}
}
