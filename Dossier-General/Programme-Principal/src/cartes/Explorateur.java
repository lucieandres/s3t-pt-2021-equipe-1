package cartes;

import javafx.scene.paint.Color;

public class Explorateur extends CarteSpeciale{
	
	public Explorateur(Color couleur) {
		super(couleur, "L'Explorateur", 13);
	}

	@Override
	public void Activer() {
		int numeroColonne = 0;
		int numeroColonneVisee;
		int nbObjectif = this.getColonne().getPlateau().getColonne().size(); 
		
		Tempete tempete = new Tempete();
		
		for(int i=0; i<nbObjectif; i++){
			if(this.getColonne().getPlateau().getColonne().get(i) == this.getColonne()){
				numeroColonne = i;
				break;
				}
			//TODO erreur si il trouve pas
		}
		
		if(numeroColonne == nbObjectif-1) {
			numeroColonneVisee = 0;
		}
		else {
			numeroColonneVisee = numeroColonne+1;
		}
		
		while(!this.getColonne().getPlateau().getColonne().get(numeroColonneVisee).getCartesInfluences().contains(tempete)) {
			numeroColonneVisee+=1;
		}
		
		this.Placer(this.getColonne().getPlateau().getColonne().get(numeroColonneVisee));
		this.getColonne().getCartesInfluences().remove(this);
	}
	
}
