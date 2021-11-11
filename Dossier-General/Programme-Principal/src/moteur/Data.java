package moteur;
import joueur.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import cartes.*;
import elements.*;
import javafx.scene.paint.Color;

public class Data {
    private Joueur master;
	private Joueur[] joueurs;
    private Plateau plateau;
	private int currentManche = 0;//num�ro de manche actuel
    private int currentTour = 0;//num�ro de tour actuel
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
	public void initReserve(Joueur joueur){
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
		
		MaitreDArme maitreDArme1 = new MaitreDArme(joueur.getCouleur());
		Cardinal cardinal1 = new Cardinal(joueur.getCouleur());
		Alchimiste alchimiste1 = new Alchimiste(joueur.getCouleur());
		for(int i = 0; i<8; i++) {
			joueur.addCarteInfluence(maitreDArme1);
			joueur.addCarteInfluence(cardinal1);
			joueur.addCarteInfluence(alchimiste1);
		}
		joueur.addCarteInfluence(cardinal1);
		
/* 		for(int i=0;i<joueurs.length;i++) {
			for(int j=0;j<25;j++){
				reserve.add(carteInfluence);
			}
			Reserve res = new Reserve(reserve);
		}
*/

	}

	//choisit 3 cartes aleatoires dans la reserve du joueur
	public void initMainJoueur(Joueur joueur){
		CarteInfluence carte = joueur.getOneCarteInfluenceRandomInReserve();
		deplacerCarteInfluence(carte, joueur.getDefausse(), joueur.getMain());
	}
	
	public void initPlateau(int nbjoueur) {
		plateau = new Plateau(nbjoueur);
		plateau.initPioche(nbjoueur);
	}
	
	
    public void deplacerCarteInfluence(CarteInfluence carte, CarteInfluence[] depart, CarteInfluence[] arrivee) {
    	for(CarteInfluence c : depart) {
    		if(c == carte) {
    			c = null;
    			break;
    		}
    	}
    	for(CarteInfluence c : arrivee) {
    		if(c == null) {
    			c = carte;
    			break;
    		}
    	}
    }
    
    public void initPartie(Joueur master, int nbjoueur) {
        LinkedHashMap<Integer, Color> colorInt = new LinkedHashMap<Integer, Color>();
    	colorInt.put(0, Color.AQUAMARINE);
    	colorInt.put(1, Color.ORANGERED);
    	colorInt.put(2, Color.YELLOW);
    	colorInt.put(3, Color.MEDIUMPURPLE);
    	colorInt.put(4, Color.SEAGREEN);
    	colorInt.put(5, Color.SILVER);
        
        this.master = master;
        joueurs = new Joueur[nbjoueur];
        this.addJoueur(master);
        for(int i = 1; i<nbjoueur; i++) {
        	Joueur bot = new Bot("easy", colorInt.get(i), "bot"+i);
        	this.addJoueur(bot);
        }
        for(Joueur j : joueurs) {
        	this.initReserve(j);
        	this.initMainJoueur(j);
        }
        
        this.initPlateau(nbjoueur);
        
    }
    
    public void remplirReserve(Joueur joueur) {
    	if(joueur.reserveNulle()) {
    		for(int i = 0; i < joueur.getReserve().length; i++) {
    			joueur.getReserve()[i] = joueur.getDefausse()[i];
        	}
    	}
    	joueur.setDefausse(null);
    }
}
