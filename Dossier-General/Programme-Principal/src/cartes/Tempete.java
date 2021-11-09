package cartes;

import joueur.Couleur;

public class Tempete extends CarteSpeciale{
	
	public Tempete(Couleur couleur) {
		super(couleur, "La Tempete", 9);
	}

	@Override
	public void Activer() {
		this.getColonne().getCarteObjectif().Realiser();
	}
}
