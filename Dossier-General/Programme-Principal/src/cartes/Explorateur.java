package cartes;

public class Explorateur extends CarteSpeciale{
	
	public Explorateur() {
		super("L'Explorateur", 13);
	}

	@Override
	public void Activer() {
		int numeroColonne = 0;
		int nbObjectif = this.getColonne().getPlateau().getColonne().size(); 
		
		for(int i=0; i<nbObjectif; i++){
			if(this.getColonne().getPlateau().getColonne().get(i) == this.getColonne()){
				numeroColonne = i;
				break;
				}
			//TODO erreur si il trouve pas
		}
		
		if(numeroColonne == nbObjectif-1) {
			this.Placer(this.getColonne().getPlateau().getColonne().get(0));
			this.getColonne().getCartesInfluences().remove(this);
			this.setColonne(this.getColonne().getPlateau().getColonne().get(0));
		}
		else {
			this.Placer(this.getColonne().getPlateau().getColonne().get(numeroColonne+1));
			this.getColonne().getCartesInfluences().remove(this);
			this.setColonne(this.getColonne().getPlateau().getColonne().get(numeroColonne+1));
		}
	}
	
}
