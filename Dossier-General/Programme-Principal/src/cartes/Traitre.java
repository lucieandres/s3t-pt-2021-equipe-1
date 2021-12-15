package cartes;


import javafx.scene.paint.Color;
import moteur.Data;

/**
 * Cette classe définit les cartes <i>Influence</i> Traitre dont la valeur est 10 (dix) et qui ont une capacité spéciale qui s'active dès que la carte est retournée.</br>
 * <b>CP</b> : Lorsque la carte Traitre est retournée, on peut échanger la carte objectif de la colonne avec une autre carte objectif.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Traitre extends CarteSpeciale{
	
	private CarteObjectif OJECTO;
	private int COL;
	private CarteObjectif OBJECTC;
	private boolean ORC;
	
	
	/**
	 * Ce constructeur produit une carte <i>Influence</i> spéciale Traitre de la couleur passée en paramètre.
	 * 
	 * @param couleur Couleur de la carte.
	 * 
	 * @since 1.0
	 */
	public Traitre(Color couleur) {
		super(couleur, "Le Traitre", 10);
	}

	@Override
	public void activer(Data data) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Activation de la capacité spéciale.
	 * 
	 * @param data Les données de la partie.
	 * @param indexColonneVisee les numéro de la colonne visée
	 * 
	 * @since 1.0
	 */
	//@Override
	public void activer(Data data, int indexColonneVisee) throws Exception {
		//CarteObjectif carteAEchanger = new CarteObjectif("Alchimie", 5); //carte random pour le moment
		//TODO Trouver un moyen pour donner au joueur l'option de choisir s'il veut retourner la carte, et s'il dit oui,
		//lui faire renseigner l'index de colonne de la carte Objectif choisie 
		//(qu'on passera ensuite dans la variable indexColonneVisee).
		OJECTO = data.getPlateau().getColonne(data.getPlateau().getIndexColonneCarte(this)).getCarteObjectif();
		COL = indexColonneVisee;
		OBJECTC = data.getPlateau().getColonne(indexColonneVisee).getCarteObjectif();
		
		int indexColonneCarte = data.getPlateau().getIndexColonneCarte(this);
	
		CarteObjectif carteAuxiliaire;
		carteAuxiliaire = data.getPlateau().getColonne(indexColonneCarte).getCarteObjectif();
		//Récupère la carte objectif de la colonne dans laquelle se trouve le traître
		data.getPlateau().getColonne(indexColonneCarte).setCarteObjectif(data.getPlateau().getColonne(indexColonneVisee).getCarteObjectif());
		//Assigner à la colonne du traître, la carte objectif renseignée dans indexColonneVisee
		data.getPlateau().getColonne(indexColonneVisee).setCarteObjectif(carteAuxiliaire);
		//Assigner à la colonneVisee la carte objectif de la colonne du traître
		
		data.estRealisee(indexColonneCarte);
		ORC = data.getPlateau().getColonne(indexColonneCarte).getComplete();
		//Assigne à l'attribut super, les nouvelles informations réseau.
	}
	
	public CarteObjectif getOJECTO() {
		return OJECTO;
	}
	
	public void setOJECTO(CarteObjectif co) {
		this.OJECTO = co;
	}
	
	public int getCOL() {
		return COL;
	}
	
	public void setCOL(int colonne) {
		this.COL = colonne;
	}
	
	public CarteObjectif getOBJECTC() {
		return OBJECTC;
	}
	
	public void setOBJECTC(CarteObjectif co) {
		this.OBJECTC = co;
	}
	
	public boolean getORC() {
		return ORC;
	}
	
	public void setORC (boolean orc) {
		this.ORC = orc;
	}
}
