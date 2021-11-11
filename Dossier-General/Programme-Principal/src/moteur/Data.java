package moteur;
import joueur.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import bot.Bot;
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
	
//	public void initPlateau(int nbjoueur) {
//		plateau = new Plateau(nbjoueur);
//		plateau.initPioche(nbjoueur);
//	}
	
	
    public void deplacerCarteInfluenceMainVersColonne(int indexMain, int indexColonne) {
    	plateau.ajouterColonnes(indexColonne, joueurs[currentJoueur].getMain()[indexMain]);
    	joueurs[currentJoueur].setMain(indexMain, null);
    }
    
    //constructeur
    public Data(Joueur master, int nbjoueur) {
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
        	Joueur bot = new Bot("facile", colorInt.get(i), "bot"+i);
        	this.addJoueur(bot);
        }
        for(Joueur j : joueurs) {
        	j.initReserve();
        	j.initMainJoueur();
        }
        
        plateau = new Plateau(nbjoueur);
       // this.initPlateau(nbjoueur);
       // this.plateau.setAllColonnes();
        
    }
	
    
    public void remplirReserve(Joueur joueur) {
    	if(joueur.reserveNulle()) {
    		for(int i = 0; i < joueur.getReserve().length; i++) {
    			joueur.getReserve()[i] = joueur.getDefausse()[i];
        	}
    	}
    	joueur.setDefausse(null);
    }
    
    
    public int resultatFinManche(int numeroColonne) {
    	double[] resultats = new double[joueurs.length];
    	int numeroVainqueur = 0;
    	
    	for(int i=0; i<plateau.getColonnes()[numeroColonne].getCartesInfluences().length; i++ ) {
    		for(int j=0; j<resultats.length; j++){
    			if(plateau.getColonnes()[numeroColonne].getCartesInfluences()[i].getCouleur() == joueurs[j].getCouleur())
    			resultats[j] += plateau.getColonnes()[numeroColonne].getCartesInfluences()[i].getValeur();
    		}
    	}
    	
    	for(int k=0; k<resultats.length; k++) {
    		if (resultats[k]>resultats[numeroVainqueur])
    			numeroVainqueur = k;
    	}
    	
    	return numeroVainqueur;
    	
    }
    
    public boolean estRealisee(int numeroColonne) {
    	CarteObjectif carte = plateau.getColonnes()[numeroColonne].getCarteObjectif();
    	int valeur = carte.getValeur();
    	if (valeur <= plateau.getColonnes()[numeroColonne].getCartesInfluences().length)
    		return true;
    	else
    		return false;
    	
    }
    
    public void mancheSuivante() {
		this.currentManche++;
	}
    
    public boolean mancheFinie() {
    	int estFinie=0;
    	
    	for(int i=0; i<plateau.getColonnes().length; i++) {
    		if (estRealisee(i))
    			estFinie++;
    	}
    	
    	if(estFinie==plateau.getColonnes().length) {
    		this.mancheSuivante();
    		return true;
    	}
    	else
    		return false;
    }
    
    public boolean partieFinie() {
    	if (this.getCurrentManche()>6)
    		return true;
    	else
    		return false;
    	
    }
    
    public void jouerCarte(int indexMain, int indexColonne) {
    	this.deplacerCarteInfluenceMainVersColonne(indexMain, indexColonne);
    	int numcarte = joueurs[currentJoueur].getOneCarteInfluenceRandomInReserve();
    	joueurs[currentJoueur].setMain(indexMain, joueurs[currentJoueur].getReserve()[numcarte]);
    	joueurs[currentJoueur].setCarteDansReserve(numcarte, null);
    	joueurSuivant();
    }
    
}


