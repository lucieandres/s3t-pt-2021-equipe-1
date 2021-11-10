package moteur;
import joueur.*;

import java.util.ArrayList;

import cartes.*;
import elements.*;

public class Data {
    private Joueur master;
	private Joueur[] joueurs;
    private Plateau plateau;
    private String statut;
	private Joueur joueur;
	private int valeur;
    
    //pas de constructeur
    
	//initialise data dans l'interface jeu
	
	
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

	//remplit la reserve du joueur avec les 25 cartes influences de sa couleur
	public void initReserve(){
 		ArrayList<CarteInfluence> reserve = new ArrayList<CarteInfluence>();//
		CarteInfluence carteInfluence = new CarteInfluence(joueur.getCouleur(),statut, valeur) {};//
/*
		Alchimiste alchimiste = new Alchimiste(joueur.getCouleur());
		Assassin assassin = new Assassin(joueur.getCouleur());
		Cardinal cardinal = new Cardinal(joueur.getCouleur());
		Explorateur explorateur = new Explorateur(joueur.getCouleur());
		Juliette juliette = new Juliette(joueur.getCouleur());
		MaitreDArme maitreDArme = new MaitreDArme(joueur.getCouleur());
		Marchant marchant = new Marchant(joueur.getCouleur());
		Reine reine = new Reine(joueur.getCouleur());
		Roi roi = new Roi(joueur.getCouleur());
		Seigneur seigneur = new Seigneur(joueur.getCouleur());
		Tempete tempete = new Tempete(joueur.getCouleur());
		Traitre traitre = new Traitre(joueur.getCouleur());
		Troubadour troubadour = new Troubadour(joueur.getCouleur());
*/
		
		MaitreDArme maitreDArme1 = new MaitreDArme(joueurs[0].getCouleur());
		Cardinal cardinal1 = new Cardinal(joueur.getCouleur());
		Alchimiste alchimiste1 = new Alchimiste(joueur.getCouleur());
		
		
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(alchimiste1);
		joueurs[0].addCarteInfluence(alchimiste1);
/* 		for(int i=0;i<joueurs.length;i++) {
			for(int j=0;j<25;j++){
				reserve.add(carteInfluence);
			}
			Reserve res = new Reserve(reserve);
		}
*/

	}

	//choisit 3 cartes aleatoires dans la reserve du joueur
	public void initMainJoueur(){
/* 		ArrayList<CarteInfluence> cartesInfluences = new ArrayList<CarteInfluence>();
		CartesEnMain cartesEnMain = new CartesEnMain(cartesInfluences);
		int rand = (int) (Math.random()*joueur.getReserve().length);
		for(int i=0;i<3;i++){
			cartesEnMain.prendreCarte(carteInfluence, reserve);
		} */
		
	}
    
}
