package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import bot.Bot;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;
import interfaces.GestionnaireInterface;

public class PlanDeTestBot {
	public static boolean estFinie = false;
	public static Data data;
	public static LinkedHashMap<Joueur, Integer> score = new LinkedHashMap<Joueur, Integer>();
	public static LinkedHashMap<Joueur, Integer> victoire = new LinkedHashMap<Joueur, Integer>();
	
	public static void main(String[] args) {
        LinkedHashMap<Integer, Color> colorInt = new LinkedHashMap<Integer, Color>();
    	colorInt.put(0, Color.YELLOW);
    	colorInt.put(1, Color.PURPLE);
    	colorInt.put(2, Color.ANTIQUEWHITE);
    	colorInt.put(3, Color.DARKGREEN);
    	colorInt.put(4, Color.RED);
    	colorInt.put(5, Color.BLUE);
    	
		Joueur[] listeBot = new Joueur[2];
		
			listeBot[0] = new Bot("facile", colorInt.get(0), "botf" + 0);
			listeBot[1] = new Bot("moyen", colorInt.get(5), "botm" + 1);
		
		for(Joueur j : listeBot) {
			score.put(j, 0);
			victoire.put(j, 0);
		}
		
		int nbtest = 100;
		int currenttest = 0;
		
		while(nbtest > currenttest) {
			data = new Data(listeBot);
			try {
				doitJouer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stat();
			currenttest++;
		}
		for(int i = 0; i<listeBot.length; i++) {
			System.out.println(listeBot[i].getPseudo() + " : " + score.get(listeBot[i]) + " - " + victoire.get(listeBot[i]));
		}
	}
	
	public static void doitJouer() throws Exception {
		if(!verifManche() && estFinie == false) {
	    	if(data.getJoueurs()[data.getCurrentJoueur()] instanceof Bot) {
	    		data.getJoueurs()[data.getCurrentJoueur()].jouer(data, 0, 0);
	    		doitJouer();
	    	}
    	}
    	else {
    		if(!data.partieFinie()) {
	    		estFinie = true;
	        	data.activerCartesARetardement();
	        	data.retournerCarte();
	        	data.finDeManche();
	    		estFinie = false;
	    		doitJouer();
    		}
    		else {
	    		estFinie = true;
	        	data.retournerCarte();
	        	data.finDeManche();
    		}
    	}
	}
    
    public static boolean verifManche() {
    	boolean verif = true;
    	for(int i = 0; i<data.getPlateau().getColonnes().length; i++) {
    		if(!data.getPlateau().getColonnes()[i].estPleine() && !data.getPlateau().getColonnes()[i].estFiniEtreRempli()) {
    			verif = false;
    		}
    	}
    	return verif;
    }
    
    public static void stat() {
    	HashMap<Integer, Joueur> scores = new HashMap<>();
		for(Joueur j : data.getJoueurs()) {
			scores.put(j.getScore(), j);
			score.replace(j, score.get(j) + j.getScore());
		}
		TreeMap<Integer, Joueur> triScores = new TreeMap<>(scores);
		ArrayList<Joueur> triJoueur = new ArrayList<>(triScores.values());
		victoire.replace(triJoueur.get(triScores.size()-1), (victoire.get(triJoueur.get(triScores.size()-1))+1));
    }
}
