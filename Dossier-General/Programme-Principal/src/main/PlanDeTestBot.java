package main;

import java.util.HashMap;
import java.util.LinkedHashMap;

import bot.Bot;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;

public class PlanDeTestBot {
	public static boolean estFinie = false;
	public static Data data;
	public static LinkedHashMap<Integer, Integer> total_score = new LinkedHashMap<Integer, Integer>();
	public static LinkedHashMap<Integer, Integer> victoire = new LinkedHashMap<Integer, Integer>();

	public static void main(String[] args) {
        LinkedHashMap<Integer, Color> colorInt = new LinkedHashMap<Integer, Color>();
    	colorInt.put(0, Color.YELLOW);
    	colorInt.put(1, Color.PURPLE);
    	colorInt.put(2, Color.ANTIQUEWHITE);
    	colorInt.put(3, Color.DARKGREEN);
    	colorInt.put(4, Color.RED);
    	colorInt.put(5, Color.BLUE);


    	Bot[] listeBot = new Bot[2];

		for(int i = 0; i < listeBot.length; i++) {
			total_score.put(i, 0);
			victoire.put(i, 0);
		}

		int nbtest = 100;
		int currenttest = 0;

		while(nbtest > currenttest) {
			listeBot[0] = new Bot("facile", colorInt.get(0), "botm" + currenttest);
			listeBot[1] = new Bot("facile", colorInt.get(5), "botf" + currenttest);
			data = new Data(listeBot);
			for(int i = 0; i < data.getJoueurs().length; i++) {
				System.out.println("after init: " + listeBot[i].getPseudo() + " : " + data.getJoueurs()[i].getScore() + " - " + victoire.get(i));
			}

			try {
				doitJouer();
			} catch (Exception e) {
				e.printStackTrace();
			}
			data.calculScoreJoueurs();
			stat();
			currenttest++;
		}
		for(int i = 0; i < listeBot.length; i++) {
			System.out.println(listeBot[i].getPseudo() + " : " + total_score.get(i) + " - " + victoire.get(i));
		}
	}

	public static void doitJouer() throws Exception {
		if(!verifManche() && !estFinie) {
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
	        	data.calculScoreJoueurs();
	    		estFinie = false;
	    		doitJouer();
    		}
    		else {
	    		estFinie = true;
	        	data.retournerCarte();
	        	data.finDeManche();
	        	data.calculScoreJoueurs();
    		}
    	}
	}

    public static boolean verifManche() {
    	boolean verif = true;
    	for(int i = 0; i < data.getPlateau().getColonnes().length; i++) {
    		if(!data.getPlateau().getColonnes()[i].estPleine() && !data.getPlateau().getColonnes()[i].estFiniEtreRempli()) {
    			verif = false;
    		}
    	}
    	return verif;
    }

    public static void stat() {
    	HashMap<Integer, Integer> manche_score = new HashMap<>();
		for(int i = 0; i < data.getJoueurs().length; i++) {
			manche_score.put(i, data.getJoueurs()[i].getScore());
			total_score.replace(i, total_score.get(i) + data.getJoueurs()[i].getScore());
		}
		int best = getMax(manche_score);
		System.out.println(best + " " + manche_score.get(best));
		victoire.replace(best, victoire.get(best) + 1);
    }

    public static int getMax(HashMap<Integer, Integer> manche_score)
    {
    	int max = 0;
    	int maxIndex = 0;
    	for (Integer i : manche_score.keySet())
    	{
    		if (manche_score.get(i) > max)
    		{
    			max = manche_score.get(i);
    			maxIndex = i;
    		}
    	}
    	return (maxIndex);
    }
}
