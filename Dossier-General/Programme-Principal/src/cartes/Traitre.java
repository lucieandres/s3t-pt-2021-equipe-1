package cartes;

import javafx.scene.paint.Color;
import moteur.Data;

public class Traitre extends CarteSpeciale{
	
	public Traitre(Color couleur) {
		super(couleur, "Le Traitre", 10);
	}


	@Override
	public void Activer(Data data) throws Exception {
		//CarteObjectif carteAEchanger = new CarteObjectif("Alchimie", 5); //carte random pour le moment
		//TODO il faut trouver un moyen de choisir la carte
		
		int indexColonneCarte = data.getPlateau().getIndexColonneCarte(this);
		int indexColonneVisee = 0; 
		//Trouver un moyen pour donner au joueur l'option de choisir s'il veut retourner la carte, et s'il dit oui,
		//lui faire renseigner l'index de colonne de la carte Objectif choisie.
	
		CarteObjectif carteAuxiliaire;
		carteAuxiliaire = data.getPlateau().getColonne(indexColonneCarte).getCarteObjectif();
		data.getPlateau().getColonne(indexColonneCarte).setCarteObjectif(data.getPlateau().getColonne(indexColonneVisee).getCarteObjectif());
		data.getPlateau().getColonne(indexColonneVisee).setCarteObjectif(carteAuxiliaire);
		
		//changer la colonne
		
		
//		int nbObjectif = this.getColonne().getPlateau().getColonne().size();
//		//int numeroColonneVisee = 0;
//				
//		for(int i=0; i<nbObjectif; i++){
//			if(this.getColonne().getPlateau().getColonne().get(i).getCarteObjectif() == carteAEchanger){
//				numeroColonneVisee = i;
//				break;
//				}
//			//TODO erreur si il trouve pas
//		}
//		
//		this.getColonne().getPlateau().getColonne().get(numeroColonneVisee).setCarteObjectif(this.getColonne().getCarteObjectif());
//		if (this.getColonne().getPlateau().getColonne().get(numeroColonneVisee).getCarteObjectif().getValeur()==this.getColonne().getPlateau().getColonne().get(numeroColonneVisee).getCartesInfluences().size()) {
//			this.getColonne().getPlateau().getColonne().get(numeroColonneVisee).getCarteObjectif().Realiser();
//		}
//		
//		this.getColonne().setCarteObjectif(carteAEchanger);
//		if (this.getColonne().getCarteObjectif().getValeur()==this.getColonne().getCartesInfluences().size()) {
//			this.getColonne().getCarteObjectif().Realiser();
//		}
//		
	}

}
