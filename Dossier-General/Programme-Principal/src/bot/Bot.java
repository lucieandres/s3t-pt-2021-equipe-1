package bot;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import elements.Colonne;
import cartes.*;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;


public class Bot extends Joueur {

	private Color couleur;
	private String pseudo;
	private String difficulte;
	private String memNom;
	private int memCol;
	private CarteInfluence  main[];
	private CarteInfluence  defausse[];
	private CarteInfluence  reserve[];
	private CarteObjectif	objectif[];
	String validDiff[];

	public Bot(String difficulte, Color couleur, String pseudo) {
		super(couleur, pseudo);

		this.validDiff = new String[] {"facile", "moyen", "difficile"};
		this.couleur=couleur;
		this.pseudo=pseudo;
		main = new CarteInfluence[3];
		defausse = new CarteInfluence[25];
		reserve = new CarteInfluence[25];
		this.memNom = null;
		this.memCol = -1;
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
	public void jouer(Data data, int indexMain, int indexColonne) {
		switch (this.difficulte){
			case "facile":
				indexMain = setAleatoireIndexMain();
				indexColonne = setAleatoireIndexColonne(data);
			try {
				data.jouerCarte(indexMain, indexColonne);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			case "moyen":
				try {
					jouer_moyen(data);
				} catch (Exception e) {
					e.printStackTrace();
				}
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

	public void jouer_moyen(Data data) {
		int i;
		if (this.memNom != null)
		{
			if ((this.memNom == "Magicien" && (i = verif_carte("Sorcière")) != -1) || (this.memNom == "Socrière" && (i = verif_carte("Magicien")) != -1))
			{
				data.jouerCarte(i, this.memCol);
				this.memNom = null;
				return ;
			}
			else if ((this.memNom == "Juliette" && (i = verif_carte("Roméo")) != -1) || (this.memNom == "Roméo" && (i = verif_carte("Juliette")) != -1))
			{
				data.jouerCarte(i, this.memCol);
				this.memNom = null;
				return ;
			}
			// check si moyen decombo avec un carte placée\
			// si oui jouer la carte, mettre à null la mémoire du nom et IMPORTANT: RETURN après avoir fait ça
		}
		else
		{
			if ((i = verif_carte("Magicien")) != -1 && (i = verif_carte("Sorcière")) != -1)
			{
				int bestCol = verif_col_combo(data, "maxE");
				data.jouerCarte(i, bestCol);
				this.memNom = this.main[i].getNom();
				this.memCol = bestCol;
				return ;
			}
			else if ((i = verif_carte("Juliette")) != -1 && (i = verif_carte("Roméo")) != -1)
			{
				int bestCol = verif_col_combo(data, "overMin");
				data.jouerCarte(i, bestCol);
				this.memNom = this.main[i].getNom();
				this.memCol = bestCol;
				return ;
			}
			else if ((i = verif_carte("Ermite")) != -1)
			{
				int cartecolI;
				int nbAlly;
				int nbEnemy;
				if ((cartecolI = search_advcard(data, "Mendiant")) != -1)
				{
					if ((nbAlly = how_many(data, cartecolI, "Ally")) != -1 && (nbEnemy = how_many(data, cartecolI, "Enemy")) != -1)
					{
						if (nbAlly == 0 && nbEnemy > 1)
						{
							data.jouerCarte(i, cartecolI);
							this.memNom = this.main[i].getNom();
							this.memCol = cartecolI;
							return ;
						}
					}
				}
			}
			else if ((i = verif_carte("Ecuyer")) != -1)
			{
				int cartecolI;
				int nbAlly;
				if ((cartecolI = search_advcard(data, "Mendiant")) != -1)
				{
					if ((nbAlly = how_many(data, cartecolI, "Ally")) != -1)
					{
						if (nbAlly == 0)
						{
							data.jouerCarte(i, cartecolI);
							this.memNom = this.main[i].getNom();
							this.memCol = cartecolI;
							return ;
						}
					}
				}
			}
			else
			{
				// aleatoire si pas de combo
				int indexMain = setAleatoireIndexMain();
				int indexColonne = setAleatoireIndexColonne(data);
				data.jouerCarte(indexMain, indexColonne);
				this.memNom = this.main[indexMain].getNom();
				this.memCol = indexColonne;
			}
		}
	}

	public void jouer_difficile(Colonne[] cols) {
	}

	//trouve le nombre d'ally ou d'enemy dans une colone donnée (a verifier)
	private int how_many(Data data, int col_index, String mode)
	{
		Colonne[] cols = data.getPlateau().getColonnes();
		CarteInfluence[] cs;
		int carte_buff = 0;
		cs = cols[col_index].getCartesInfluences();
		if (mode != "Ally" && mode != "Enemy")
			return (-1);
		for (int j = 0; j < cs.length; j++) {
			if (mode == "Ally" && cs[col_index].getCouleur() == this.couleur)
				carte_buff++;
			else if (mode == "Enemy" && cs[col_index].getCouleur() != this.couleur)
				carte_buff++;
		}
		return carte_buff;
	}

	// permet de trouver l'index d'une colone contenant une carte adverse d'un certain nom
	private int search_advcard(Data data, String nom)
	{
		Colonne[] cols = data.getPlateau().getColonnes();
		CarteInfluence[] cs;
		for (int i = 0; i < cols.length; i++) {
			cs = cols[i].getCartesInfluences();
			for (int j = 0; j < cs.length; j++) {
				if (cs[i].getCouleur() != this.couleur && cs[i].getNom() == nom)
					return (i);
			}
		}
		return (-1);
	}

	// retourne certaine donnée en fonction du mode (a verifier)
	private int verif_col_combo(Data data, String mode){
		Colonne[] cols = data.getPlateau().getColonnes();
		Integer[] verif = new Integer[cols.length];
		CarteInfluence[] cs;
		int allycBuff;
		int enemycBuff;
		int dataIndex = 0;
		for (int i = 0; i < cols.length; i++) {
			cs = cols[i].getCartesInfluences();
			allycBuff = 0;
			enemycBuff = 0;
			for (int j = 0; j < cs.length; j++) {
				if(cs[i].getCouleur() != this.couleur)
					enemycBuff++;
				else
					allycBuff++;
			}
			verif[dataIndex++] = enemycBuff;
			verif[dataIndex++] = allycBuff;
		}

		switch (mode) {
			case "maxE":
					int maxE = 0;
					int imaxE = -1;
					for (dataIndex = 1; dataIndex < verif.length; dataIndex += 2) {
						if (verif[dataIndex] > maxE)
						{
							maxE = verif[dataIndex];
							imaxE = dataIndex/2;
						}
					}
					return (imaxE);
				break;
			case "minE":
					int minE = 0;
					int iminE = -1;
					for (dataIndex = 0; dataIndex < verif.length; dataIndex += 2) {
						if (verif[dataIndex] < minE)
						{
							minE = verif[dataIndex];
							iminE = dataIndex/2;
						}
					}
					return (iminE);
				break;
			case "overMin":
					int minCartes = 0;
					int minCartesIndex = -1;
					for (int i = 0; i < cols.length; i++) {
						int clength = cols[i].getCartesInfluences().length;
						if (clength < minCartes)
						{
							minCartes = clength;
							minCartesIndex = i;
						}
					}
					return (minCartesIndex);
				break;
		}
		return 0;
	}

	private int verif_carte(String nom)
	{
		for (int i = 0 ; i < 3; i++)
			if (this.main[i].getNom() == nom)
				return (i);
		return (-1);
	}

	static private int getRandomInt(int max) {
		return (int) (new Random().nextInt(max));
	}

//----------------------------------------------------------------
//
//	public double[] pointTotalMax(Data data) {
//		double[] bestIndex=null;
//		bestIndex[0]=0;
//		for(int i = 0 ; i< data.getPlateau().getColonnes().length ; i++) {
//			for(int j=0; j<main.length; j++ ) {
//				//attendant methode pointTotal(nbColonne, indexmain, int indexjoueur)
//				double pointTotal = data.getTotal(i, j, data.getCurrentJoueur());
//				if (pointTotal > bestIndex[0] && pasBon(i, j, pointTotal, data)) { //index+colonne...
//					bestIndex[0]=pointTotal;
//
//				}
//			}
//		}
//		return bestIndex;
//	}
//	//faudra le renommer //se sert à éviter les move pas intelligent rule 5,6
//	public Boolean pasBon(int indexColonne, int indexMain,double pointTotal, Data data) {
//		for(int i=0; i<data.getJoueurs().length;i++) {
//			if(!(data.getCurrentJoueur()==i)) { //only consider the other players
//				double pointTotal2 = data.getTotal(indexColonne, indexMain , i); //point on the colonne of that player
//				CarteInfluence cartesSurColonne[]=data.getPlateau().getColonne(indexColonne).getCartesInfluences();
//				if(pointTotal2>pointTotal && !(cartesSurColonne[cartesSurColonne.length-1]==null)) { //si le nb de point de l'autre joueur est plus élevé there is no more room to play afterward ( the card before last move is already filled)
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//	//RULES:
//	 //1peut caculer le point des autres carte des autres dans la colonne va perdre
//	//2ACTUALLY just need to get the defausse =)))
//	//3have to consider next player can be attacking if he has Magicien,... ->what is the percentage??
//	//4% of having an assasin hidden,
//
//	//5have to consider the point of opponent on the same colone too, if it's last carte on colone but still losing->NOT play or move to the next colone
//	//6or if the opponent have upcoming turn and in his defausse can have a carte that can beat us-> NOT PLAYING by that way
//	//7a la fin pouvoir faire FuzzyLogic avec pointTotal, pointAttaque,....
//	public double EtreAttaque(Data data) {
//		double etreAttaque=0;
//		for(int i=0; i<data.getJoueurs().length;i++) {
//			if(!(data.getCurrentJoueur()==i)) {
//				//the card that are NOT in the defausse of the player can beat us in the colonne
//				CarteInfluence cartesDefausse[]=data.getJoueursAvecIndex(i).getDefausse();
//				String typeCartesDefausse[] = null;
//				for(int j=0; j<cartesDefausse.length; j++) {
//					typeCartesDefausse[j]=cartesDefausse[j].getNom();
//				}//How to get the cartes that are not present in the defausse to make a list??
//
//			}
//	}
//		return etreAttaque;
//	}
//
//	public double pointAttaquer(Data data) {
//
//		return 0;
//	}
}
