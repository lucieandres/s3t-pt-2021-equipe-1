package moteur;
import joueur.*;

import java.util.ArrayList;

import cartes.*;
import elements.*;

public class Data {
    private Joueur master;
	private Joueur[] joueurs;
    private Plateau plateau;
	private int currentManche = 0;//numéro de manche actuel
    private int currentTour = 0;//numéro de tour actuel
    private int currentJoueur  = 0;//correspond au joueur qui joue
    
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
    public int getCurrentManche() {
		return currentManche;
	}
	public void setCurrentManche(int currentManche) {
		this.currentManche = currentManche;
	}
	public int getCurrentTour() {
		return currentTour;
	}
	public void setCurrentTour(int currentTour) {
		this.currentTour = currentTour;
	}
	public int getCurrentJoueur() {
		return currentJoueur;
	}
	public void setCurrentJoueur(int currentJoueur) {
		this.currentJoueur = currentJoueur;
	}
	
	public void joueurSuivant() {
		if(this.currentJoueur < this.joueurs.length) {
			this.currentJoueur++;
		}
		else {
			this.tourSuivant();
			this.currentJoueur=0;
		}
	}
	public void tourSuivant() {
		this.currentTour++;
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
		Cardinal cardinal1 = new Cardinal(joueurs[0].getCouleur());
		Alchimiste alchimiste1 = new Alchimiste(joueurs[0].getCouleur());
		
		
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(maitreDArme1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(cardinal1);
		joueurs[0].addCarteInfluence(alchimiste1);
		joueurs[0].addCarteInfluence(alchimiste1);
		joueurs[0].addCarteInfluence(alchimiste1);
		joueurs[0].addCarteInfluence(alchimiste1);
		joueurs[0].addCarteInfluence(alchimiste1);
		joueurs[0].addCarteInfluence(alchimiste1);
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
