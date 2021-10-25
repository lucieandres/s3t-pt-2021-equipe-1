package cartes;

import elements.Colonne;

public class CarteSpeciale extends CarteInfluence {
	
	String capacit�Speciale;
	
	Boolean effetImmediat; //Pour savoir si la capacit� speciale est immediate

	
	//Constructeur basique
	public CarteSpeciale(String nom, int valeur, boolean estVisible, Colonne colonne, String capaciteSpeciale, Boolean effetImmediat) {
		super(nom, valeur, estVisible, colonne);
		this.capacit�Speciale = capaciteSpeciale;
		this.effetImmediat = effetImmediat;
	}
	
	//Constructeur simplifi�
	public CarteSpeciale(String nom, int valeur, String capaciteSpeciale, Boolean effetImmediat) {
		super(nom, valeur, false, null);
		this.capacit�Speciale = capaciteSpeciale;
		this.effetImmediat = effetImmediat;
	}
	
	public String getCapacit�Speciale() {
		return capacit�Speciale;
	}

	public void setCapacit�Speciale(String capacit�Speciale) {
		this.capacit�Speciale = capacit�Speciale;
	}

	public Boolean getEffetImmediat() {
		return effetImmediat;
	}

	public void setEffetImmediat(Boolean effetImmediat) {
		this.effetImmediat = effetImmediat;
	}
	
	//Op�rations
	
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
