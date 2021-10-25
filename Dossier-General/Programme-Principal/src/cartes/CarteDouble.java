package cartes;

import elements.Colonne;

public abstract class CarteDouble extends CarteInfluence {

	private int valeurSp�ciale; // Valeur sp�ciale utilis�e lorsque que la carte est plac�e dans la colonne d'une carte objectif ayant le meme domaine

	private String domaine;

	
	// Constructeur basique
	protected CarteDouble(String nom, int valeur, boolean visible, Colonne colonne, int valeurSp�ciale, String domaine) {
		super(nom, valeur, visible, colonne);
		this.valeurSp�ciale = valeurSp�ciale;
		this.domaine = domaine;
	}

	// Constructeur simplifi�
	protected CarteDouble(String nom, int valeur, int valeurSp�ciale, String domaine) {
		super(nom, valeur, false, null);
		this.valeurSp�ciale = valeurSp�ciale;
		this.domaine = domaine;
	}
	
	// Constructeur super simplifi�
	protected CarteDouble(String nom, String domaine) {
		super(nom, 8, false, null);
		this.valeurSp�ciale = 12;
		this.domaine = domaine;
	}

	public int getValeurSp�() {
		return valeurSp�ciale;
	}

	public void setValeurSp�(int valeurSp�ciale) {
		this.valeurSp�ciale = valeurSp�ciale;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	
	
	//Op�ration
	
	public int ValeurUtilisee(CarteObjectif objectif) {
		if (this.domaine == objectif.getDomaine()) {
			return this.valeurSp�ciale;
		} 
		else {
			return this.getValeur();
		}
	}

}
