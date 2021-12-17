package bot;

import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import elements.Colonne;
import cartes.*;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;
/**
 * 
 * @author Utilisateur
 *
 */
public class Bot extends Joueur {
	private String difficulte;
	private final static int valMin = 8;
	
	String validDiff[];

	public Bot(String difficulte, Color couleur, String pseudo) {
		super(couleur, pseudo);

		this.validDiff = new String[] {"facile", "moyen", "difficile"};
		if (!Arrays.asList(this.validDiff).contains(difficulte)) { throw new IllegalArgumentException("Type de difficulte n'existe pas"); }
		this.difficulte = difficulte;
	}

	public String getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}
	
	@Override
    public void jouer(Data data, int indexMain, int indexColonne) throws Exception {
		switch (this.difficulte){
			case "facile":
				indexMain = setAleatoireIndexMain();
				indexColonne = setAleatoireIndexColonne(data);
			try {
				data.jouerCarte(indexMain, indexColonne);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			case "moyen":
				jouer_moyen(data);
			break;
			//case "difficile":
				// jouer_difficile(cols.length);
			//break;
		}
	}

	public int setAleatoireIndexMain() {
		return getRandomInt(3);
	}
	
	public int setAleatoireIndexColonne(Data data) {
    	Random rand = new Random();
    	ArrayList<Integer> listIndex = new ArrayList<>();
    	for(int i = 0 ; i< data.getPlateau().getColonnes().length ; i++) {
    		if(!data.getPlateau().getColonnes()[i].estPleine()) {
    			listIndex.add(i);
    		}
    	}
    	if(listIndex.size() == 1) {
    		return(listIndex.get(0));
    	}
    	return listIndex.get(rand.nextInt(listIndex.size()));
	}
	
	// bot moyen
	
	public void jouer_moyen(Data data) throws Exception {
		ArrayList<TreeMap<Integer, Color>> listColonneScores = new ArrayList<TreeMap<Integer, Color>>();
		
		ArrayList<Integer> listScoreSup = new ArrayList<Integer>();
		ArrayList<Integer> listScoreInf = new ArrayList<Integer>();
		
		for(int ic = 0; ic < data.getPlateau().getColonnes().length; ic++) {
			System.out.println("colonne : " + ic);
			TreeMap<Integer, Color> listJoueur = new TreeMap<Integer, Color>(Collections.reverseOrder());

			for(int ij = 0; ij < data.getJoueurs().length; ij++) {
				System.out.print(data.getJoueurs()[ij].getCouleur() + " : ");
				if(!data.getPlateau().getColonnes()[ic].estPleine()) {
					System.out.println(data.getTotale(ic, ij));
					listJoueur.put((int) data.getTotale(ic, ij), data.getJoueurs()[ij].getCouleur());
				}
				else {
					System.out.println(1000);
					listJoueur.put(1000, data.getJoueurs()[ij].getCouleur());
				}
			}
			listColonneScores.add(listJoueur);
			
			ArrayList<Integer> sc = new ArrayList<Integer>(listColonneScores.get(ic).keySet());
			
			if(sc.size()>2) {
				if(listColonneScores.get(ic).containsValue(getCouleur())) {
					listScoreSup.add(sc.get(0) - sc.get(1));
					listScoreInf.add(0);
				}
				else {
					listScoreSup.add(0);
					ArrayList<Color> co = new ArrayList<Color>();
					co.addAll(listColonneScores.get(ic).values());
					
					if(co.get(1) == getCouleur()) {
						listScoreInf.add(sc.get(0) - sc.get(1));
					}
					else {
						listScoreInf.add(0);
					}
				}
			}
			else {
				listScoreInf.add(0);
				listScoreSup.add(0);
			}
		}
		
			int valInf = 100;
			int indInf = 0;
			int valSup = 100;
			int indSup = 0;
			
			for(int cl = 0; cl<listScoreSup.size(); cl++) {
				if(listScoreSup.get(cl) != null) {
					if(listScoreSup.get(cl)<valSup) {
						valSup = listScoreSup.get(cl);
						indSup = cl;
					}
				}
				if(listScoreInf.get(cl) != null) {
					if(listScoreInf.get(cl)<valInf) {
						valInf = listScoreInf.get(cl);
						indInf = cl;
					}
				}
			}
			
			int indexMain = 0;
			
			int indexColonneMinScore = 0;
			
			if(valInf == 0 && valSup == 0) {
				System.out.println(listColonneScores.size());
				for(int i = 1; i<listColonneScores.size(); i++ ) {
					if(listColonneScores.get(indexColonneMinScore).firstKey() > listColonneScores.get(i).firstKey()) {
						indexColonneMinScore = i;
					}
				}
				indexMain = data.getCarteIndex(data.getCurrentJoueur(), 3);
				System.out.println("Main: "+indexMain+" ; Colonne : "+indexColonneMinScore );
				data.jouerCarte(indexMain, indexColonneMinScore);
			}
			else if(valSup<valInf) {
				if(valSup<valMin) {
				 indexMain = data.getCarteIndex(data.getCurrentJoueur(), 3);
				}
				else {
					 indexMain = data.getCarteIndex(data.getCurrentJoueur(), 1);
				}
				data.jouerCarte(indexMain, indSup);
			}
			else if(valSup>valInf) {
				if(valInf<valMin) {
					 indexMain = data.getCarteIndex(data.getCurrentJoueur(), 3);
				}
				else {
					 indexMain = data.getCarteIndex(data.getCurrentJoueur(), 1);
				}
				data.jouerCarte(indexMain, indInf);
			}
			else {
				data.jouerCarte(setAleatoireIndexMain(), setAleatoireIndexColonne(data));
			}

			
	}
	
	
	//intelligent, mais pas parfait: not yet considering analyse of cards that are NOT reveilled yet 
	//either the cartesObjectif qu'il a gagne....

//	public void jouer_moyen(Data data) throws Exception {
//		double pointEtreAttaque = 0;
//		Integer [] differenceMax= differenceMax(data);
//		int bestIndex= differenceMax[0];
//		System.out.println(differenceMax[1]);
//		int indexColonne= differenceMax[1];
//		System.out.println(differenceMax[2]);
//		int indexMain=differenceMax[2];
//		if( !(indexMain == -1 || indexColonne == -1) ) {
//			pointEtreAttaque = etreAttaque(data, indexColonne, bestIndex);
//		}
//		ArrayList<Integer> pointAttaque= pointAttaquer(data);
//		if(bestIndex-pointEtreAttaque>pointAttaque.get(0)) {
//			data.jouerCarte(indexMain, indexColonne);
//		}
//		else {
//			data.jouerCarte(pointAttaque.get(2), pointAttaque.get(1));
//							//indexMain           //indexColonne
//		}
//		
//	}
//	
	
	// faut calculer le diference avec le joueur second?
//	public Integer[] differenceMax(Data data) throws Exception {
//		Integer[] bestIndex= new Integer[3];
//		int differenceAvecJoueurs=0;
//		bestIndex[0]=0;
//		bestIndex[1]=-1;
//		bestIndex[2]=-1;
//		for(int i = 0 ; i< data.getPlateau().getColonnes().length ; i++) {	
//			if(!data.getPlateau().getColonne(i).estPleine()) {
//				for(int j=0; j<main.length; j++ ) {
//					double pointTotal = data.getTotale(i, j, data.getCurrentJoueur());
//					if(differenceAutreJoueurs(data, j, i, (int)pointTotal)>differenceAvecJoueurs) {
//						if (!mauvaisIdee(i, j, pointTotal, data)) { //index+colonne...
//					    	bestIndex[0]=((Double) pointTotal).intValue();
//					    	bestIndex[1]=i; //indexColonne 
//					    	bestIndex[2]=j; //indexMain
//						}
//					}
//				   
//				}
//			}
//			
//		}
//		return bestIndex;
//	}
	
	
//	public int differenceAutreJoueurs(Data data, int indexMain, int indexColonne, int pointTotalBot) throws Exception 	{ //la difference entre notre maxValue avec le joueur qui a le plus grand valeur sur le colonne parmi les autres joueurs
//		int res=0;
//		Data d= data;
//		double pointMaxJoueur=0;
//		for(int i=0; i<d.getJoueurs().length;i++) { 
//			if(!(d.getCurrentJoueur()==i)) { //not our bot
//				d.setCurrentJoueur(i);
//				double pointJoueur = d.getTotale(indexColonne, indexMain , i);
//				if(pointJoueur>pointMaxJoueur) {
//					pointMaxJoueur=pointJoueur;
//				}
//			}
//		}
//		res=pointTotalBot-(int)pointMaxJoueur;
//		return res;
//	}
//	public Boolean mauvaisIdee(int indexColonne, int indexMain,double pointTotal, Data data) throws Exception {
//		for(int i=0; i<data.getJoueurs().length;i++) { 
//			if(!(data.getCurrentJoueur()==i)) { //only consider the other players
//				double pointTotal2 = data.getTotale(indexColonne, indexMain , i); //point on the colonne of that player
//				CarteInfluence cartesSurColonne[]=data.getPlateau().getColonne(indexColonne).getCartesInfluences(); 
//				if(pointTotal2>pointTotal && !(cartesSurColonne[cartesSurColonne.length-1]==null)) { //si le nb de point de l'autre joueur est plus élevé there is no more room to play afterward ( the card before last move is already filled)
//					return true;
//				}	
//				else { //si le joueurSuivant peux ajouter une carte pour gagner notre PointTotale et il n'y aura plus de place à jouer apres
//						//pourcentage qu'il va jouer la carte indique sur le colonne->base sur son point sur le colonne (et l'autre colonne)
//					int indexJoueurSuivant = data.getCurrentJoueur();
//					if(data.getCurrentJoueur()<data.getJoueurs().length-1) {
//						indexJoueurSuivant++;
//					}
//					else {
//						indexJoueurSuivant=0;
//					}
//					List<CarteInfluence>cartesMain = getCartesPasDansDefausse(data, indexJoueurSuivant);
//					if(cartesMain.size()==3 && !(cartesSurColonne[cartesSurColonne.length-2]==null)) {
//						for(int j=0; j<cartesMain.size(); j++) {
//							if(data.getTotale(indexColonne, j , indexJoueurSuivant)>pointTotal) {
//								return true;
//							}
//						}
//					}
//				}
//			}
//			
//		}
//		return false;
//	}
//	
////not yet calcule le % qque le joueur va choisir vraiment la carte
//	public double etreAttaque(Data data, int indexColonne, int bestIndex) throws Exception {	
//		double pointEtreAttaque=0;
//		double pointTotal2=0;
//		CarteInfluence[] cartesSurColonne = data.getPlateau().getColonne(indexColonne).getCartesInfluences(); 
//		
//		if(!(cartesSurColonne[cartesSurColonne.length-1]==null)) { //si la colonne sera plein apres notre partie et les autres joueurs n'auront aucun chance de nous attaquer
//			return 0;
//		}
//		else {
//			double point=0;
//			Data d= data; //create d so that we wont harm the real data
//			for(int i=0; i<d.getJoueurs().length;i++) { 
//				if(!(d.getCurrentJoueur()==i)) {
//					List<CarteInfluence>cartesMain = getCartesPasDansDefausse(data, i);
//					for(int j=0; j<cartesMain.size(); j++) {
//						d.setCurrentJoueur(i);
//						d.deplacerCarteInfluenceMainVersColonne(j, indexColonne);
//						pointTotal2 = d.getTotale(indexColonne, j , i);
//						point=bestIndex-pointTotal2*(3/cartesMain.size()); //si on a 3 carte dans le main player(cartesMain.size=3)
//							//is the % of getting the indexMain out of 3 in n value of cartesMain //*3/3 = 1 ->point=bestIndex-pointTotal
//							// formule: 2C(n-1) / 3Cn
//						if(point>pointEtreAttaque) {
//							pointEtreAttaque=point;
//						}
//					} 
//				}
//			}
//		}
//		
//		return pointEtreAttaque;
//	}
//	
//	//either choose "really" colonne to attack
//	public ArrayList<Integer> pointAttaquer(Data data) throws Exception { //attaquer joueur qui a le valeur le plus haut ou second si le bot est le plus haut
//		//faut : if we are the second then kinda privege this option? 
//		//pointAttaque depending on how many row in colonne is left to ajout point ?
//		//consider also reserve of bot
//		ArrayList<Integer> attaqueMax = new ArrayList<Integer>();
//		attaqueMax.add(0);
//		attaqueMax.add(-1);
//		attaqueMax.add(-1);
//		ArrayList<Integer> attaque = new ArrayList<Integer>();
//		int joueurAyantPlusDePoint=data.getCurrentJoueur(); 
//		for(int i = 0 ; i< data.getPlateau().getColonnes().length ; i++) {	
//			if(!data.getPlateau().getColonne(i).estPleine()) {
//				double pointTotalBot=data.getPlateau().getColonne(i).getTotalDuJoueur(data.getJoueurs()[data.getCurrentJoueur()].getCouleur()); //point of bot on that colonne
//				
//				double pointPlusEleve=0;
//				for(int k=0; k<data.getJoueurs().length;k++) {
//					Double[] pointTotale= new Double[data.getJoueurs().length-1];	
//					if(!(data.getCurrentJoueur()==k)) {
//						pointTotale[k]=data.getPlateau().getColonne(i).getTotalDuJoueur(data.getJoueurs()[k].getCouleur()); //point Total of others players on that colonne
//						if(pointTotale[k]>pointPlusEleve) {
//							joueurAyantPlusDePoint=k; //joueurAyantPlusDePoint de tous les autres joueur(ne compte pas le bot)
//							pointPlusEleve=pointTotale[k];
//						}
//					}	
//				}
//				Data d= data;
//				for(int j=0; j<main.length; j++) {
//					if(!mauvaisIdee(i,j,d.getTotale(i,j,d.getCurrentJoueur()),d)) {
//						double pointAttaque=0;
//						d.deplacerCarteInfluenceMainVersColonne(j, i);
//						pointAttaque=pointPlusEleve- d.getPlateau().getColonne(i).getTotalDuJoueur(d.getJoueurs()[joueurAyantPlusDePoint].getCouleur());
//						if(attaque.get(i)<pointAttaque||attaque.get(i)==null) {
//							attaque.set(i, (int)pointAttaque); //pointAttaque
//							attaque.set(i+1, i);//indexColonne
//							attaque.set(i+2, j);//indexMain
//						}
//					}
//					
//				}
//			}
//			if(attaqueMax.get(0)<attaque.get(i)) {
//				attaqueMax.set(0, attaque.get(i));
//				attaqueMax.set(1, attaque.get(i+1)); //colonne
//				attaqueMax.set(2, attaque.get(i+2)); //main
//			}
//		}
//			return attaqueMax;
//	}
	
//	public double risqueDeCarteCache(Data data) { //calcul de risque des cartes pas encore releve sur les colonnes
//		//1 get player who played that cart
//		//2 analyse the list of carts it can be out of the mainPossible
//	}
	
	
//	public Boolean mauvaisIdee2(int indexColonne, int indexMain,double pointTotalBot, Data data) throws Exception {
//		if(mauvaisIdee(indexColonne,indexMain,pointTotalBot,data)) {
//			return true;
//		}
//		else {
//			//other conditions
//		}
//		//if we have first or second place then -> good idee to attack? , after attacking, do we have the chance to win? //well if its very beginning then no need to attack?
//		//after attacking we still have no way to win?
//		return false;
//	}
				
	public void jouer_difficile(Colonne[] cols) {
	}
	
	
	static private int getRandomInt(int max) {
		return (int) (new Random().nextInt(max));
	}
	public ArrayList<String> get25CartesInfluences() {
		String[] strs = new String[]{ "Alchimiste", "Assassin", "CapeDInvisibilite", "Cardinal","Dragon","Ecuyer","Ermite","Explorateur","Juliette","Magicien","MaitreDArme","Marchand","Mendiant","PetitGeant","Prince","Reine","Roi","Romeo","Seigneur", "Sorciere", "Sosie","Tempete","Traite","TroisMousquetaires","Troubadour" };
		ArrayList<String> cartesInfluences = new ArrayList<String>();
		for (String s : strs) {
		    cartesInfluences.add(s);
		}
		return cartesInfluences;
	}
	public List<CarteInfluence> getCartesPasDansDefausse(Data data, int indexJoueur) {
		CarteInfluence [] cartesDefausse=data.getJoueursAvecIndex(indexJoueur).getDefausse();
		List<CarteInfluence> res = Arrays.asList(cartesDefausse);  
		ArrayList<String> cartesInfluences = get25CartesInfluences();
		for(int i=0; i<cartesDefausse.length; i++) {
			for(int j=0; j<cartesInfluences.size();j++) {
				if(res.get(i).getClass().getName()==cartesInfluences.get(j)) {
					res.remove(i);
				}
			}
			
		}
		return res;
	}
	
}