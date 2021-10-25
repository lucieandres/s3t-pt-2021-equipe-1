package cartes;

import elements.Colonne;

public class CarteSpeciale extends CarteInfluence {
	
	String capacitéSpeciale;
	
	Boolean effetImmediat; //Pour savoir si la capacité speciale est immediate

	
	//Constructeur basique
	public CarteSpeciale(String nom, int valeur, boolean estVisible, Colonne colonne, String capaciteSpeciale, Boolean effetImmediat) {
		super(nom, valeur, estVisible, colonne);
		this.capacitéSpeciale = capaciteSpeciale;
		this.effetImmediat = effetImmediat;
	}
	
	//Constructeur simplifié
	public CarteSpeciale(String nom, int valeur, String capaciteSpeciale, Boolean effetImmediat) {
		super(nom, valeur, false, null);
		this.capacitéSpeciale = capaciteSpeciale;
		this.effetImmediat = effetImmediat;
	}
	
	public String getCapacitéSpeciale() {
		return capacitéSpeciale;
	}

	public void setCapacitéSpeciale(String capacitéSpeciale) {
		this.capacitéSpeciale = capacitéSpeciale;
	}

	public Boolean getEffetImmediat() {
		return effetImmediat;
	}

	public void setEffetImmediat(Boolean effetImmediat) {
		this.effetImmediat = effetImmediat;
	}
	
	//Opérations
	
	public void CapaciteSpeciale() {
		if (this.getEstVisible()) {
			if ("Explorateur".equals(this.getNom())) {
				this.Placer(this.getColonne().getPlateau().getColonne().get(getValeur()));
			}
			if ("Assasin".equals(this.getNom())) {
				this.getColonne().getPlateau().getDefausse().getCartesInfluences().add(getColonne().getCartesInfluences().get(this.getColonne().getCartesInfluences().size()));
				this.getColonne().getCartesInfluences().remove(this.getColonne().getCartesInfluences().size()) ;
			}
			if ("Tempete".equals(this.getNom())) {
				this.getColonne().getCarteObjectif().setEstRealise(true);
			}
			if ("Traitre".equals(this.getNom())) {
				//TODO
			}
			if ("CapeInvisible".equals(this.getNom())) {
				//TODO
			}
		}
	}
	
	public void Tourner() {
		  this.setEstVisible(true);
		  if (this instanceof CarteSpeciale) {
			  this.CapaciteSpeciale();
		  }
	}
		  
	   

}
