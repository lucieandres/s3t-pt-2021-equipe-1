package cartes;

import javafx.scene.paint.Color;

public class Traitre extends CarteSpeciale{
	
	public Traitre(Color couleur) {
		super(couleur, "Le Traitre", 10);
	}


	@Override
	public void Activer() {
		CarteObjectif carteAEchanger = new CarteObjectif("Alchimie", 5); //carte random pour le moment
		//TODO il faut trouver un moyen de choisir la carte
		
		int nbObjectif = this.getColonne().getPlateau().getColonne().size();
		int numeroColonneVisee = 0;
				
		for(int i=0; i<nbObjectif; i++){
			if(this.getColonne().getPlateau().getColonne().get(i).getCarteObjectif() == carteAEchanger){
				numeroColonneVisee = i;
				break;
				}
			//TODO erreur si il trouve pas
		}
		
		this.getColonne().getPlateau().getColonne().get(numeroColonneVisee).setCarteObjectif(this.getColonne().getCarteObjectif());
		if (this.getColonne().getPlateau().getColonne().get(numeroColonneVisee).getCarteObjectif().getValeur()==this.getColonne().getPlateau().getColonne().get(numeroColonneVisee).getCartesInfluences().size()) {
			this.getColonne().getPlateau().getColonne().get(numeroColonneVisee).getCarteObjectif().Realiser();
		}
		
		this.getColonne().setCarteObjectif(carteAEchanger);
		if (this.getColonne().getCarteObjectif().getValeur()==this.getColonne().getCartesInfluences().size()) {
			this.getColonne().getCarteObjectif().Realiser();
		}
		
	}

}
