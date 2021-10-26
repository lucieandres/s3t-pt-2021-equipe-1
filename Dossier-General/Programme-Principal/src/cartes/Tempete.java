package cartes;

public class Tempete extends CarteSpeciale{
	
	public Tempete() {
		super("La Tempete", 9);
	}

	@Override
	public void Activer() {
		this.getColonne().getCarteObjectif().setEstRealise(true);
	}
}
