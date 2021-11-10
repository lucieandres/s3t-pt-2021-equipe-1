package moteur;
import joueur.*;
import cartes.*;
import elements.*;

public class Data {
    private Joueur master;
	private Joueur[] joueurs;
    private Plateau plateau;
    private String statut;
    
    //pas de constructeur
    
	public Joueur getMaster() {
		return master;
	}
	public void setMaster(Joueur master) {
		this.master = master;
	}
	public Joueur[] getJoueurs() {
		return joueurs;
	}
	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}
	public Plateau getPlateau() {
		return plateau;
	}
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
    
	public void addJoueur(Joueur joueur) {
    	for(int i = 0; i<this.getJoueurs().length; i++) {
    		if(this.getJoueurs()[i] == null) {
    			joueurs[i]=joueur;
    			break;
    		}
    	}
	}
	
	public void removeJoueur(Joueur joueur) {
    	for(int i = 0; i<this.getJoueurs().length; i++) {
    		if(this.getJoueurs()[i] == null) {
    			joueurs[i]=null;
    			break;
    		}
    	}
	}

	public void initReserve(){
/* 		CarteInfluence carteInfluence = new CarteInfluence(null,CarteInfluence.class.getName(), 0) {
			
		};
		switch(joueurs.length){
			case 2 :
				if()
		} */
	}

	public void initMainJoueur(){
		//TODO
	}
    
}
