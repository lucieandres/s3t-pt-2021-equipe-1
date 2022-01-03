package moteur;
import joueur.*;

import java.util.LinkedHashMap;
import java.util.Random;

import bot.Bot;
import cartes.*;
import elements.*;
import javafx.scene.paint.Color;

/**
 * Cette classe définit toutes les données essentiels au fonctionnement du jeu tels que les joueurs, le plateau, le nombre de manches
 * et de tour.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class Data {
    private Joueur master;
	private Joueur[] joueurs;
    private Plateau plateau;
	private int currentManche = 0;//numéro de manche actuel
    private int currentTour = 0;//numéro de tour actuel
    private int currentJoueur  = 0;//correspond au joueur qui joue
    private int joueurIntermediaire = -1; //correspond au joueur qui doit jouer si une cart spéciale est activée
    
    //pas de constructeur
    
	//initialise data dans l'interface jeu
	
    /**
     * Retourne le maître du jeu qui correspond au joueur qui lance la partie.
     * 
     * @return Le joueur maître du jeu.
     * 
     * @since 1.0
     */
	public Joueur getMaster() {
		return master;
	}
	
	/**
     * Modifie le maître du jeu qui correspond au joueur qui lance la partie.
     * 
     * @param master Le joueur maître du jeu.
     * 
     * @since 1.0
     */
	public void setMaster(Joueur master) {
		this.master = master;
	}
	
	public int getJoueurIntermediaire() {
		return joueurIntermediaire;
	}
	
	public void setJoueurInterfmediaire(int ji) {
		joueurIntermediaire = ji;
	}
	
	/**
     * Retourne les joueurs de la partie.
     * 
     * @return Les joueurs de la partie.
     * 
     * @since 1.0
     */
	public Joueur[] getJoueurs() {
		return joueurs;
	}
	
	/**
     * Modifie les joueurs de la partie avec ceux spécifiés.
     * 
     * @param joueurs Les joueurs de la partie.
     * 
     * @since 1.0
     */
	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}
	
	/**
     * Retourne le plateau de jeu de la partie dans lequel se trouve les éléments relatifs aux cartes jouées.
     * 
     * @return Le plateau de jeu.
     * 
     * @since 1.0
     */
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**
     * Modifie le plateau de jeu de la partie dans lequel se trouve les éléments relatifs aux cartes jouées, avec celui spécifié.
     * 
     * @param plateau Le plateau de jeu.
     * 
     * @since 1.0
     */
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	/**
     * Retourne le numéro de la manche de jeu courante. Le nombre de manches d'une partie s'élève à six.
     * 
     * @return Le numéro de la manche courante.
     * 
     * @since 1.0
     */
    public int getCurrentManche() {
		return currentManche;
	}
    
    /**
     * Modifie la manche de jeu courante. Le nombre de manches d'une partie s'élève à six.
     * 
     * @param currentManche Numéro de la manche courante .
     * 
     * @since 1.0
     */
	public void setCurrentManche(int currentManche) {
		this.currentManche = currentManche;
	}
	
	/**
     * Retourne le numéro du tour courant. Le nombre de tours d'une manche n'est pas fixe.
     * 
     * @return Le numéro du tour courant.
     * 
     * @since 1.0
     */
	public int getCurrentTour() {
		return currentTour;
	}
	
	/**
     * Modifie le tour courant. Le nombre de tours d'une manche n'est pas fixe.
     * 
     * @param currentTour Le numéro du tour courant.
     * 
     * @since 1.0
     */
	public void setCurrentTour(int currentTour) {
		this.currentTour = currentTour;
	}
	
	/**
     * Retourne le numéro du joueur à qui c'est le tour de jouer.
     * 
     * @return Le numéro joueur qui doit jouer.
     * 
     * @since 1.0
     */
	public int getCurrentJoueur() {
		return currentJoueur;
	}
	
	/**
     * Modifie le joueur à qui c'est le tour de joueur.
     * 
     * @param currentJoueur Le numéro du joueur qui doit jouer.
     * 
     * @since 1.0
     */
	public void setCurrentJoueur(int currentJoueur) {
		this.currentJoueur = currentJoueur;
	}
	
	/**
     * Initialise le joueur suivant. Change de tour si tous les joueurs ont joué.
     * 
     * @since 1.0
     */
	public void joueurSuivant() {
		if(this.currentJoueur < this.joueurs.length-1) {
			this.currentJoueur++;
		}
		else {
			this.tourSuivant();
			this.currentJoueur=0;
		}
	}
	
	/**
     * Initialise le tour suivant. Méthode appelée quand chacun des joueurs a joué.
     * 
     * @since 1.0
     */
	public void tourSuivant() {
		this.currentTour++;
	}
    
	/**
     * Ajoute le joueur spécifié dans la partie.
     * 
     * @param joueur Joueur à ajouter dans la partie.
     * 
     * @since 1.0
     */
	public void addJoueur(Joueur joueur) {
    	for(int i = 0; i<this.getJoueurs().length; i++) {
    		if(this.getJoueurs()[i] == null) {
    			joueurs[i]=joueur;
    			break;
    		}
    	}
	}
	
	/**
     * Supprime le joueur spécifié de la partie.
     * 
     * @param joueur Joueur à supprimer de la partie.
     * 
     * @since 1.0
     */
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
	
	/**
     * Déplace une carte <i>Influence</i> de la main du joueur courant dans la colonne.
     * 
     * @param indexMain Index de la carte <i>Influence</i> dans la main du joueur courant.
     * 
     * @param indexColonne Index de la colonne dans laquelle doit être placée la carte choisie dans la main du joueur courant.
     * 
     * @since 1.0
     */
    public void deplacerCarteInfluenceMainVersColonne(int indexMain, int indexColonne) throws Exception{
    	plateau.ajouterColonnes(indexColonne, joueurs[currentJoueur].getMain()[indexMain]);
    	CarteInfluence carte = null;
    	if(plateau.getColonne(indexColonne).nombreCartesInfluences() >= 2) {
    		carte = plateau.getColonne(indexColonne).getCarteInfluence(plateau.getColonne(indexColonne).nombreCartesInfluences() - 2);
    	}
//    	CarteInfluence carte = plateau.getColonne(indexColonne).getCartesInfluences()[plateau.getColonne(indexColonne).nombreCartesInfluences() - 2];
    	if(carte instanceof CarteSpeciale && !(carte instanceof CarteARetardement)) {
    		if(carte instanceof Traitre) {
    			//TODO
//    			joueurs[getIndexJoueurParCouleur(carte.getCouleur())].choisir(); ?
//    			Traitre carteT = (Traitre) carte;
//    			carteT.Activer(this, 0);
    		}
    		else {
    			CarteSpeciale carteS = (CarteSpeciale) carte;
        		carteS.activer(this);
    		}
    	}
    	joueurs[currentJoueur].setMain(indexMain, null);
    }
    
    /**
     * Constructeur qui produit une partie à partir d'un joueur maître qui lance la partie et du nombre joueurs de cette dernière.
     * 
     * @param master Joueur qui lance la partie.
     * 
     * @param nbjoueur Le nombre de joueurs de la partie.
     * 
     * @since 1.0
     */
    public Data(Joueur master, int nbjoueur) {
        LinkedHashMap<Integer, Color> colorInt = new LinkedHashMap<Integer, Color>();
    	colorInt.put(0, Color.YELLOW);
    	colorInt.put(1, Color.PURPLE);
    	colorInt.put(2, Color.ANTIQUEWHITE);
    	colorInt.put(3, Color.DARKGREEN);
    	colorInt.put(4, Color.RED);
    	colorInt.put(5, Color.BLUE);
        
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
        System.out.println(plateau.getPioche().size());
       // this.initPlateau(nbjoueur);
       // this.plateau.setAllColonnes();
    }
	
    public Data(int nbjoueur) {
        LinkedHashMap<Integer, Color> colorInt = new LinkedHashMap<Integer, Color>();
    	colorInt.put(0, Color.YELLOW);
    	colorInt.put(1, Color.PURPLE);
    	colorInt.put(2, Color.ANTIQUEWHITE);
    	colorInt.put(3, Color.DARKGREEN);
    	colorInt.put(4, Color.RED);
    	colorInt.put(5, Color.BLUE);
        
        joueurs = new Joueur[nbjoueur];
        plateau = new Plateau(nbjoueur);
        System.out.println(plateau.getPioche().size());
       // this.initPlateau(nbjoueur);
       // this.plateau.setAllColonnes();
    }
    
    /**
     * Remplit la réserve d'un joueur spécifié en paramètre si elle est vide, elle va pour cela déplacer les cartes de la défausse 
     * du joueur vers la réserve.
     * 
     * @param joueur Joueur d'une partie.
     * 
     * @since 1.0
     */
    public void remplirReserve(Joueur joueur) {
    	if(joueur.reserveNulle()) {
    		for(int i = 0; i < joueur.getReserve().length; i++) {
    			joueur.getReserve()[i] = joueur.getDefausse()[i];
        	}
    	}
    	joueur.setDefausse(null);
    }
    
    /**
     * Retourne le numéro du joueur qui remporte la manche en parcourant la colonne spécifiée par son numéro et en cacluant le nombre
     * de points de chaque joueur. Les points d'un joueur représentent l'addition de chacune des valeurs des cartes <i>Objectif</i> gagnées par
     * ce dernier.
     * 
     * @param numeroColonne Le numéro de la colonne à parcourir pour compter les points.
     * 
     * @return Le numéro du joueur vainqueur.
     * 
     * @since 1.0
     */
    public int resultatFinManche(int numeroColonne){
    	double[] resultats = new double[joueurs.length];
    	int numeroVainqueur = 0;
    	
    	System.out.println("&&&&&& LES POINTS A LA FIN");
    	for(int i=0; i<plateau.getColonnes()[numeroColonne].getCartesInfluences().length; i++ ) {
    		for(int j=0; j<resultats.length; j++){
    			if(plateau.getColonne(numeroColonne).getCarteInfluence(i) != null) {
	    			if(plateau.getColonne(numeroColonne).getCarteInfluence(i).getCouleur() == joueurs[j].getCouleur()) {
	    				if(plateau.getColonne(numeroColonne).getCarteInfluence(i) instanceof CarteDouble) {
		    				try {
								System.out.println("Colonne : " + this.getPlateau().getIndexColonneCarte(plateau.getColonne(numeroColonne).getCarteInfluence(i)) + ", Carte : " + plateau.getColonne(numeroColonne).getCarteInfluence(i).getNom() + ", Valeur : " + ((CarteDouble) plateau.getColonne(numeroColonne).getCarteInfluence(i)).valeurUtilisee(this.getPlateau().getColonne(numeroColonne).getCarteObjectif())
																+ ", Joueur : " + this.getIndexJoueurParCouleur(plateau.getColonne(numeroColonne).getCarteInfluence(i).getCouleur()));
							} catch (Exception e) {}
		    				
		    				resultats[j] += ((CarteDouble) plateau.getColonne(numeroColonne).getCarteInfluence(i)).valeurUtilisee(this.getPlateau().getColonne(numeroColonne).getCarteObjectif());	
	    				}
	    				else {
		    				try {
									System.out.println("Colonne : " + this.getPlateau().getIndexColonneCarte(plateau.getColonne(numeroColonne).getCarteInfluence(i)) + ", Carte : " + plateau.getColonne(numeroColonne).getCarteInfluence(i).getNom() + ", Valeur : " + plateau.getColonne(numeroColonne).getCarteInfluence(i).getValeur()
																	+ ", Joueur : " + this.getIndexJoueurParCouleur(plateau.getColonne(numeroColonne).getCarteInfluence(i).getCouleur()));
								} catch (Exception e) {}
		    				
		    				resultats[j] += plateau.getColonne(numeroColonne).getCarteInfluence(i).getValeur();	
	    				}
	    				break;
	    			}
    			}
    		}
    	}
    	
    	for(int k=0; k<resultats.length; k++) {
    		System.out.println("Joueur : " + k + ", Resultats : " + resultats[k]);
    		if (resultats[k]>resultats[numeroVainqueur])
    			numeroVainqueur = k;
    	}
    	return numeroVainqueur;
    	
    }
    
    /**
     * Vérifie si une colonne est réalisée. C'est le cas quand le nombre de cartes
     * qu'elle contient est supérieur ou égal à sa valeur.
     * 
     * @param numeroColonne Le numéro de la colonne à parcourir pour compter les points.
     * 
     * @since 1.0
     */
    public void estRealisee(int numeroColonne) {
    	int valeur = plateau.getColonnes()[numeroColonne].getCarteObjectif().getValeur();
    	if (valeur <= plateau.getColonnes()[numeroColonne].getCartesInfluences().length)
    		plateau.setColonnesComplete(numeroColonne);
    }
    
    /**
     * Initialise la manche suivante. Cette méthode est utilisée lorsqu'une manche est terminée.
     * 
     * @since 1.0
     */
    public void mancheSuivante() {
		this.currentManche++;
	}
    
    /**
     * Retourne vrai si la partie est terminée, faux si elle ne l'est pas. Une partie est terminée au bout de la sixième
     * manche jouée.
     * 
     * @return Un booléen vrai si la partie est terminée, faux si elle ne l'est pas.
     * 
     * @since 1.0
     */
    public boolean partieFinie() {
    	return plateau.piocheEstVide();
    }
    
    /**
     * Place une carte <i>Influence</i> de la main du joueur courant dans une colonne.
     * 
     * @param indexMain Index de la carte <i>Influence</i> à jouer dans la main du joueur à qui c'est le tour de jouer.
     * 
     * @param indexColonne Index de la colonne dans laquelle la carte <i>Influence</i> va être placée.
     * @throws Exception 
     * 
     * @since 1.0
     */
    public void jouerCarte(int indexMain, int indexColonne) throws Exception {
    	if(indexMain != -1 && !plateau.getColonnes()[indexColonne].estPleine()) {
	    	this.deplacerCarteInfluenceMainVersColonne(indexMain, indexColonne);
	    	int numcarte = joueurs[currentJoueur].getCarteInfluenceAleatoireDansReserve();
	    	joueurs[currentJoueur].setMain(indexMain, joueurs[currentJoueur].getReserve()[numcarte]);
	    	joueurs[currentJoueur].setCarteDansReserve(numcarte, null);
	    	joueurs[currentJoueur].setCarteSelectionnee(-1);
	    	if(plateau.getColonne(indexColonne).estFiniEtreRempli()) {
	    		plateau.setColonnesComplete(indexColonne);
	    	}
	    	joueurSuivant();
	    	
    	}
    }

    //retourne toutes les cartes de la partie à la fin de la manche
    public void retournerCarte() {
		for(int i = 0; i<plateau.getColonnes().length; i++) {
			for(int j = 0 ; j < plateau.getColonnes()[i].getCartesInfluences().length ; j++) {
				if(plateau.getColonnes()[i].getCartesInfluences()[j]!=null) {
					plateau.setCarteInfluencesVisible(i, j);
					
				}
			}
		}
    	
    }
    
    /**
     * Active la capacité spéciale de toutes les cartes à retardement si elles sont visibles et pas désactivée.</br>
     * L'ordre de priorité est : Trois Mousquetaires, Magicien, Sorciere, Prince, Ecuyer et le reste dans l'ordre colonne.
     * 
     * @throws Exception 
     * 
     * @since 1.0
     */
    public void activerCartesARetardement() throws Exception {
    	System.out.println("########## ACTIVATION DES CAPACITES");
    	for (Colonne colonne : this.getPlateau().getColonnes()) {
    		for(CarteInfluence carte : colonne.getCartesInfluences()) {	
    			if(carte instanceof TroisMousquetaires && !((TroisMousquetaires) carte).estDesactivee() && carte.getEstVisible()){
    				System.out.println("Colonne : " + this.getPlateau().getIndexColonneCarte(carte) + ", Carte : " + carte.getNom() + ", Valeur : " + carte.getValeur() + ", Joueur : " + this.getIndexJoueurParCouleur(carte.getCouleur()));
    				((TroisMousquetaires) carte).activer(this);
    				((CarteARetardement) carte).setDesactivee(true);
    			}
    		}
    		for(CarteInfluence carte : colonne.getCartesInfluences()) {	
    			if(carte instanceof Magicien && !((Magicien) carte).estDesactivee() && carte.getEstVisible()) {
    				System.out.println("Colonne : " + this.getPlateau().getIndexColonneCarte(carte) + ", Carte : " + carte.getNom() + ", Valeur : " + carte.getValeur() + ", Joueur : " + this.getIndexJoueurParCouleur(carte.getCouleur()));
    				((Magicien) carte).activer(this);
    				((CarteARetardement) carte).setDesactivee(true);
    			}
    			
    		}
    		for(CarteInfluence carte : colonne.getCartesInfluences()) {	
    			if(carte instanceof Sorciere && !((Sorciere) carte).estDesactivee() && carte.getEstVisible()) {
    				System.out.println("Colonne : " + this.getPlateau().getIndexColonneCarte(carte) + ", Carte : " + carte.getNom() + ", Valeur : " + carte.getValeur() + ", Joueur : " + this.getIndexJoueurParCouleur(carte.getCouleur()));
    				((Sorciere) carte).activer(this);
    				((CarteARetardement) carte).setDesactivee(true);
    			}
    		}
    		for(CarteInfluence carte : colonne.getCartesInfluences()) {	
    			if(carte instanceof Prince && !((Prince) carte).estDesactivee() && carte.getEstVisible()) {
    				System.out.println("Colonne : " + this.getPlateau().getIndexColonneCarte(carte) + ", Carte : " + carte.getNom() + ", Valeur : " + carte.getValeur() + ", Joueur : " + this.getIndexJoueurParCouleur(carte.getCouleur()));
    				((Prince) carte).activer(this);
    				((CarteARetardement) carte).setDesactivee(true);
    			}
    		}
    		for(CarteInfluence carte : colonne.getCartesInfluences()) {
    			if(carte instanceof Ecuyer && !((Ecuyer) carte).estDesactivee() && carte.getEstVisible()) {
    				System.out.println("Colonne : " + this.getPlateau().getIndexColonneCarte(carte) + ", Carte : " + carte.getNom() + ", Valeur : " + carte.getValeur() + ", Joueur : " + this.getIndexJoueurParCouleur(carte.getCouleur()));
    				((Ecuyer) carte).activer(this);
    				((CarteARetardement) carte).setDesactivee(true);
    			}	
    		}
    		for(CarteInfluence carte : colonne.getCartesInfluences()) {	
    			if(carte instanceof CarteARetardement && !((CarteARetardement) carte).estDesactivee() && carte.getEstVisible()) {
    				System.out.println("Colonne : " + this.getPlateau().getIndexColonneCarte(carte) + ", Carte : " + carte.getNom() + ", Valeur : " + carte.getValeur() + ", Joueur : " + this.getIndexJoueurParCouleur(carte.getCouleur()));
    				((CarteARetardement) carte).activer(this);
    				((CarteARetardement) carte).setDesactivee(true);
    			}
    		}
    	}
    }
    
	public void finDeManche() {
		for(int i = 0; i<plateau.getColonnes().length; i++) {
			int indexGagnant = resultatFinManche(i);
			System.out.println("Joueur Gagnant : " + indexGagnant);
			joueurs[indexGagnant].addCarteObjectif(plateau.getColonne(i).getCarteObjectif());
    		plateau.setColonnesIncomplete(i);
			regrouperCartesInfluencesDansReserve(i);
			if(!plateau.piocheEstVide())
				plateau.setNouvelleCarteObjectif(i);
			else
				plateau.setNouvelleCarteObjectifNull(i);
		}
		
		
	}

	public void regrouperCartesInfluencesDansReserve(int indexColonne) {
		System.out.println("===== DEPLACEMENT DANS LA RESERVE");
		for(int i = 0; i<plateau.getColonne(indexColonne).getCartesInfluences().length; i++) {
			CarteInfluence carte = plateau.getColonne(indexColonne).getCarteInfluence(i);
			for(int j = 0; j < joueurs.length; j++) {
				if(carte != null) {
					if(carte.getCouleur() == joueurs[j].getCouleur()) {
						joueurs[j].ajouterDansLaDefausse(carte);
						plateau.enleverCarteInfluence(indexColonne, i);
						System.out.println(carte + " : from " + indexColonne + " to joueurs[" + j + "]");
						break;
					}
				}
			}
		}
		
	}
	
	public int getIndexProprietaireCarteInfluence(int indexColonne, int indexCarte) throws Exception {
		CarteInfluence carte = plateau.getColonne(indexColonne).getCarteInfluence(indexCarte);
		for(int i = 0; i < joueurs.length; i++) {
			if(carte != null) {
				if(carte.getCouleur() == joueurs[i].getCouleur()) {
					return i;
				}
			}
		}
		//throw new Exception("Aucun propriétaire");
		return -1;
	}
	
	public Joueur getJoueursAvecIndex(int index) {
		return joueurs[index];
	}

	public void nouvelleManche() {
		
	}
	
	public int getIndexJoueurParCouleur(Color couleur) {
		for(Joueur j : this.joueurs) {
			if(couleur == j.getCouleur())
				return getIndexJoueur(j);
		}
		return -1;
	}
    
	public int getIndexJoueur(Joueur j) {
		for(int i = 0; i < joueurs.length; i++)
			if(joueurs[i] != null) {
				if(j == joueurs[i])
					return i;
			}
		return -1;
	}
	  public Data(Joueur[] j) {
	        joueurs = j;
	        for(Joueur jo : joueurs) {
	        	jo.initReserve();
	        	jo.initMainJoueur();
	        }
	        plateau = new Plateau(joueurs.length);
	       // this.initPlateau(nbjoueur);
	       // this.plateau.setAllColonnes();
	    }
	public double getTotale(int indexColonne, int indexMain, int indexJoueur) throws Exception {
			Data d=this;
			d.currentJoueur=indexJoueur;
			d.deplacerCarteInfluenceMainVersColonne(indexMain, indexColonne);
			return d.plateau.getColonne(indexColonne).getTotalDuJoueur(joueurs[indexJoueur].getCouleur()) + joueurs[indexJoueur].getMain()[indexMain].getValeur();
		}
		
		public double getTotale(int indexColonne, int indexJoueur) throws Exception {
			Data d=this;
			d.currentJoueur=indexJoueur;
			return d.plateau.getColonne(indexColonne).getTotalDuJoueur(joueurs[indexJoueur].getCouleur());
		}

		
		public int getCarteIndex(int indexJoueur, int force) {
			Random rand = new Random();
			int index = rand.nextInt(3);
			double value = joueurs[indexJoueur].getMain()[index].getValeur();
			
			for(int i = 0; i<joueurs[indexJoueur].getMain().length; i++) {
				if(value < joueurs[indexJoueur].getMain()[i].getValeur() && force == 3) {
					value = (int) joueurs[indexJoueur].getMain()[i].getValeur();
					index = i;
				}
				else if( value > joueurs[indexJoueur].getMain()[i].getValeur() && force == 1) {
					value = (int) joueurs[indexJoueur].getMain()[i].getValeur();
					index = i;
				}
			}
			return index;
		}
	
	public boolean possedeCarteLaPlusBasse(Joueur j1, Joueur j2, int indexColonne) throws Exception {
		int indexCarteLaPlusBasseJ1 = 0;
		int indexCarteLaPlusBasseJ2 = 0;
		for(CarteInfluence carte : getPlateau().getColonne(indexColonne).getCartesInfluences()) {
			if(carte != null) {
				if(carte.getCouleur() == j1.getCouleur())
					indexCarteLaPlusBasseJ1 = this.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(carte);
				else if(carte.getCouleur() == j2.getCouleur())
					indexCarteLaPlusBasseJ2 = this.getPlateau().getColonne(indexColonne).getIndexCarteInfluence(carte);
			}
		}
		return indexCarteLaPlusBasseJ1 - indexCarteLaPlusBasseJ2 >= 0;
	}
	
	
	public void calculScoreJoueurs() {
		for(Joueur joueur : this.getJoueurs()) {
			joueur.calculScore();
//			System.out.println("JOUEUR : " + joueur.getPseudo() + "------     SCORE : " + joueur.getScore());
		}
	}
}


