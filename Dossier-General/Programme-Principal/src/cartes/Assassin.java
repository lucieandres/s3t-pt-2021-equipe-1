package cartes;

public class Assassin extends CarteSpeciale{
	
	public Assassin() {
		super("L'Assassin", 9.5);
	}

	@Override
	public void Activer() {
		this.getColonne().getPlateau().getDefausse().getCartesInfluences().add(getColonne().getCartesInfluences().get(this.getColonne().getCartesInfluences().size()-1));
		this.getColonne().getCartesInfluences().get(this.getColonne().getCartesInfluences().size()-1).setColonne(null);
		this.getColonne().getCartesInfluences().remove(this.getColonne().getCartesInfluences().size()-1);
	}

}
