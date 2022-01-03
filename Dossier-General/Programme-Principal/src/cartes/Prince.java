package cartes;

import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influences<i> Prince dont la valeur est 14 et qui a une capacité qui s'active en fin de manche.</br>
 * <b>CP</b> : Si le Prince est dans la meme colonne que l'Ecuyer (meme couleur que le Prince), le joueur obtient l'objectif.
 * S'il y a plusieurs cartes Prince et Ecuyer dans la meme colonne, le joueur dont la carte Ecuyer ou Prince est la plus proche
 * de la carte <i>Objectif<i> remporte l'objectif. 
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class Prince extends CarteARetardement{

	/**
	 * Ce constructeur définit la carte <i>Influence</i> spéciale Prince de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @author S3T - G1
	 * 
	 * @since 1.0
	 */
	public Prince(Color couleur) {
		super(couleur, "Prince", 14);
	}
	
	/**
	 * Active la capacité du Prince
	 * 
	 * @throws Exception 
	 * 
	 * @author S3T - G1
	 * 
	 * @since 1.0
	 */
//	@Override
//	public void activer(Data data) throws Exception {
//		for(int i=0; i<data.getJoueurs().length;i++) {
//			for(CarteInfluence carteInfluence : data.getPlateau().getColonne(data.getPlateau().getIndexColonneCarte(this)).getCartesInfluences()) {
//				if((carteInfluence instanceof Ecuyer)&&(carteInfluence.getCouleur()==this.getCouleur())) {
//					if(distanceObjectif(data.getClass().cast(data.getJoueurs()[i]))<distanceObjectif(data.getClass().cast(data.getJoueurs()[i+1]))) {
//						data.getJoueurs()[i].addCarteObjectif(data.getPlateau().getColonne(data.getPlateau().getIndexColonneCarte(this)).getCarteObjectif());
//					}
//				}
//			}
//		}
//	}
	@Override
	//La boucle de position à placer dans data peut-être
	public void activer(Data data) throws Exception {
		System.out.println();
		int indexColonne = data.getPlateau().getIndexColonneCarte(this);
		int indexCartePlusProche = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this);
		
		for(CarteInfluence carte : data.getPlateau().getColonne(indexColonne).getCartesInfluences()) {
			if(((carte instanceof Prince || carte instanceof Ecuyer) && carte.getCouleur() != this.getCouleur()) && data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(carte) < indexCartePlusProche) {
				indexCartePlusProche = data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(carte);
				break;
			}
		}
		
		if(indexCartePlusProche == data.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(this)) {
			data.getJoueurs()[data.getIndexProprietaireCarteInfluence(indexColonne, indexCartePlusProche)].addCarteObjectif(data.getPlateau().getColonne(data.getPlateau().getIndexColonneCarte(this)).getCarteObjectif());
//			System.out.println(data.getJoueurs()[data.getIndexProprietaireCarteInfluence(indexColonne, indexCartePlusProche)].getPseudo() + " GAGNE LA MANCHE");
//			System.out.println(data.getJoueurs()[data.getIndexProprietaireCarteInfluence(indexColonne, indexCartePlusProche)].getScore() + " SON SCORE");
		}
	}
//	/**
//	 * Calcule la position de la carte Prince ou Ecuyer la plus proche de l'objectif
//	 * 
//	 * @param data les données de la partie
//	 * 
//	 * @return posHaute Position la plus haute d'un Prince ou d'un Ecuyer dans la colonne.
//	 * 
//	 * @author S3T - G1
//	 * 
//	 * @since 1.0
//	 * 
//	 */
//	public int distanceObjectif(Data data) throws Exception {
//		int posHaute=0;
//		int pos =0;
//		for(CarteInfluence carteInfluence : data.getPlateau().getColonne(data.getPlateau().getIndexColonneCarte(this)).getCartesInfluences()) {
//			if((carteInfluence==this)||((carteInfluence instanceof Ecuyer)&&(carteInfluence.getCouleur()==this.getCouleur()))) {
//				posHaute = pos;
//				break;
//			}else {
//				pos++;
//			}
//		}
//		return posHaute;
//	}

}
