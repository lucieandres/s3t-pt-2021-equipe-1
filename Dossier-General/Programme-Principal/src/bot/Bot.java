package bot;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import elements.Colonne;
import cartes.*;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;

public class Bot extends Joueur {

	private String difficulte;
	private String memNom;
	private Color couleur;
	private int memCol;
	private CarteInfluence  main[];
	private Neural_network nn;
	private String validDiff[];

	public Bot(String difficulte, Color couleur, String pseudo) {
		super(couleur, pseudo);
		this.couleur = couleur;
		this.validDiff = new String[] {"facile", "moyen", "difficile"};
		this.memNom = null;
		this.memCol = -1;
		if (!Arrays.asList(this.validDiff).contains(difficulte)) { throw new IllegalArgumentException("Type de difficulte n'existe pas"); }
		this.difficulte = difficulte;
		if (this.difficulte == "moyen" || this.difficulte == "difficile")
		{
			int[] hidden = {3, 2};
			this.nn = new Neural_network(4, 2, hidden);
			nn.reset();
		}
	}

	public String getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(String difficulte) {
		if (!Arrays.asList(this.validDiff).contains(difficulte)) { throw new IllegalArgumentException("Type de difficulte n'existe pas"); }
		this.difficulte = difficulte;
	}

	@Override
	public void jouer(Data data, int a, int b) {
		this.main = data.getJoueursAvecIndex(data.getCurrentJoueur()).getMain();
		switch (this.difficulte){
			case "facile":
				jouer_facile(data);
			break;
			case "moyen":
				jouer_moyen(data);
			break;
			case "difficile":
				jouer_difficile(data);
			break;
		}
	}

	public void jouer_facile(Data data)
	{
		int indexMain = setAleatoireIndexMain();
		int indexColonne = setAleatoireIndexColonne(data);
		try {
			data.jouerCarte(indexMain, indexColonne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jouer_moyen(Data data) {
		int i;
		if (this.memNom != null)
		{
			if ((this.memNom == "Magicien" && (i = verif_carte("Sorcière")) != -1) || (this.memNom == "Socrière" && (i = verif_carte("Magicien")) != -1))
			{
				if (verif_pl(data, this.memCol))
					return;
				try {
					data.jouerCarte(i, this.memCol);
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.memNom = null;
				return ;
			}
			else if ((this.memNom == "Juliette" && (i = verif_carte("Roméo")) != -1) || (this.memNom == "Roméo" && (i = verif_carte("Juliette")) != -1))
			{
				if (verif_pl(data, this.memCol))
					return;
				try {
					data.jouerCarte(i, this.memCol);
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.memNom = null;
				return ;
			}
		}
			if ((i = verif_carte("Magicien")) != -1 && (i = verif_carte("Sorcière")) != -1)
			{
				try {
					int bestCol = verif_col_combo(data, "maxE");
					this.memNom = this.main[i].getNom();
					this.memCol = bestCol;
					data.jouerCarte(i, bestCol);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return ;
			}
			else if ((i = verif_carte("Juliette")) != -1 && (i = verif_carte("Roméo")) != -1)
			{
				try {
					int bestCol = verif_col_combo(data, "overMin");
					this.memNom = this.main[i].getNom();
					this.memCol = bestCol;
					data.jouerCarte(i, bestCol);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
						if (nbAlly <= 1 && nbEnemy > 1)
						{
							try {
								this.memNom = this.main[i].getNom();
								this.memCol = cartecolI;
								data.jouerCarte(i, cartecolI);
							} catch (Exception e) {
								e.printStackTrace();
							}

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
							try {
								this.memNom = this.main[i].getNom();
								this.memCol = cartecolI;
								data.jouerCarte(i, cartecolI);
							} catch (Exception e) {
								e.printStackTrace();
							}
							return ;
						}
					}
				}
			}
			jouer_nn(data);
	}

	public void jouer_difficile(Data data) {
		int i;
		if (this.memNom != null)
		{
			if ((this.memNom == "Magicien" && (i = verif_carte("Petit Géant")) != -1) || (this.memNom == "Petit Géant" && (i = verif_carte("Magicien")) != -1))
			{
				if (verif_pl(data, this.memCol))
					return ;
				try {
					data.jouerCarte(i, this.memCol);
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.memNom = null;
				return ;
			}
			else if ((this.memNom == "Magicien" && (i = verif_carte("Sorcière")) != -1) || (this.memNom == "Socrière" && (i = verif_carte("Magicien")) != -1))
			{
				if (verif_pl(data, this.memCol))
					return;
				try {
					data.jouerCarte(i, this.memCol);
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.memNom = null;
				return ;
			}
			else if ((this.memNom == "Juliette" && (i = verif_carte("Roméo")) != -1) || (this.memNom == "Roméo" && (i = verif_carte("Juliette")) != -1))
			{
				if (verif_pl(data, this.memCol))
					return;
				try {
					data.jouerCarte(i, this.memCol);
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.memNom = null;
				return ;
			}
			else if ((this.memNom == "Reine" || this.memNom == "Roi") && (i = verif_carte("Trois Mousquetaires")) != -1)
			{
				if (verif_pl(data, this.memCol))
					return;
				try {
					data.jouerCarte(i, this.memCol);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ;
			}
		}
			if ((i = verif_carte("Magicien")) != -1 && (i = verif_carte("Sorcière")) != -1)
			{
				try {
					int bestCol = verif_col_combo(data, "maxE");
					this.memNom = this.main[i].getNom();
					this.memCol = bestCol;
					data.jouerCarte(i, bestCol);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return ;
			}
			else if ((i = verif_carte("Petit Géant")) != -1 && (i = verif_carte("Magicien")) != -1)
			{
				try {
					int bestCol = verif_col_combo(data, "maxE");
					this.memNom = this.main[i].getNom();
					this.memCol = bestCol;
					data.jouerCarte(i, bestCol);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ;
			}
			else if ((i = verif_carte("Juliette")) != -1 && (i = verif_carte("Roméo")) != -1)
			{
				try {
					int bestCol = verif_col_combo(data, "overMin");
					this.memNom = this.main[i].getNom();
					this.memCol = bestCol;
					data.jouerCarte(i, bestCol);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
						if (nbAlly <= 1 && nbEnemy > 1)
						{
							try {
								this.memNom = this.main[i].getNom();
								this.memCol = cartecolI;
								data.jouerCarte(i, cartecolI);
							} catch (Exception e) {
								e.printStackTrace();
							}

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
							try {
								this.memNom = this.main[i].getNom();
								this.memCol = cartecolI;
								data.jouerCarte(i, cartecolI);
							} catch (Exception e) {
								e.printStackTrace();
							}
							return ;
						}
					}
				}
			}
			else if ((i = verif_carte("Assassin")) != -1)
			{
				int cartecolI;
				if ((cartecolI = search_advcard(data, "Roi")) != -1)
				{
					try {
						this.memNom = this.main[i].getNom();
						this.memCol = cartecolI;
						data.jouerCarte(i, cartecolI);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ;
				}
				else if ((cartecolI = search_advcard(data, "Reine")) != -1)
				{
					try {
						this.memNom = this.main[i].getNom();
						this.memCol = cartecolI;
						data.jouerCarte(i, cartecolI);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ;
				}
			}
			jouer_nn(data);
	}

	private void jouer_nn(Data data) {
		if(this.main[0] == null || this.main[1] == null || this.main[2] == null)
			System.out.println((float)this.main[0].getValeur() + " " + (float)this.main[1].getValeur() + " " + (float)this.main[2].getValeur());
		float[] input = {(float)this.main[0].getValeur(), (float)this.main[1].getValeur(), (float)this.main[2].getValeur(), this.memCol};
		nn.calculate(input);
		float carteOut = nn.value[nn.value.length-1].matrice[0][0];
		float colOut = nn.value[nn.value.length-1].matrice[0][1];
		if (carteOut > 0 && carteOut <= 0.33)
			carteOut = 0;
		else if (carteOut > 0.33 && carteOut <= 0.66)
			carteOut = 1;
		else if (carteOut > 0.66 && carteOut <= 1)
			carteOut = 2;
		colOut = remap(colOut, 0, 1, 0, data.getPlateau().getColonnes().length-1);
		if (data.getPlateau().getColonne(Math.round(colOut)).estPleine())
			colOut = setAleatoireIndexColonne(data);
		this.memNom = this.main[(int)carteOut].getNom();
		this.memCol = Math.round(colOut);
		try {
			data.jouerCarte((int)carteOut, Math.round(colOut));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int how_many(Data data, int col_index, String mode)
	{
		Colonne[] cols = data.getPlateau().getColonnes();
		CarteInfluence[] cs;
		int carte_buff = 0;
		cs = cols[col_index].getCartesInfluences();
		if (mode != "Ally" && mode != "Enemy")
			return (-1);
		for (int j = 0; j < cs.length; j++) {
			if (cs[j] != null)
			{
				if (mode == "Ally" && cs[j].getCouleur() == this.couleur)
					carte_buff++;
				else if (mode == "Enemy" && cs[j].getCouleur() != this.couleur)
					carte_buff++;
			}
		}
		return carte_buff;
	}


	private int search_advcard(Data data, String nom)
	{
		Colonne[] cols = data.getPlateau().getColonnes();
		CarteInfluence[] cs;
		for (int i = 0; i < cols.length; i++) {
			cs = cols[i].getCartesInfluences();
			for (int j = 0; j < cs.length; j++) {
				if (cs[j] != null) {
					if (cs[j].getCouleur() != this.couleur && cs[j].getNom() == nom && !cols[i].estPleine())
						return (i);
				}
			}
		}
		return (-1);
	}


	private int verif_col_combo(Data data, String mode){
		Colonne[] cols = data.getPlateau().getColonnes();
		Integer[] verif = new Integer[cols.length * 2];
		CarteInfluence[] cs;
		int allycBuff;
		int enemycBuff;
		int dataIndex = 0;
		for (int i = 0; i < cols.length; i++) {
			cs = cols[i].getCartesInfluences();
			allycBuff = 0;
			enemycBuff = 0;
			for (int j = 0; j < cs.length; j++) {
				if(cs[j] != null)
				{
					if(cs[j].getCouleur() != this.couleur)
						enemycBuff++;
					else
						allycBuff++;
				}
				if (cols[i].estPleine())
				{
					enemycBuff = -1;
					allycBuff = -1;
					break ;
				}
			}
			verif[dataIndex++] = enemycBuff;
			verif[dataIndex++] = allycBuff;
		}

		int reqVal = 0;
		switch (mode) {
			case "maxE":
					int maxE = 0;
					int imaxE = Bot.getRandomInt(cols.length);
					for (dataIndex = 0; dataIndex < verif.length; dataIndex += 2) {
						if (verif[dataIndex] > maxE && verif[dataIndex] != -1)
						{
							maxE = verif[dataIndex];
							imaxE = dataIndex/2;
						}
					}
					reqVal = imaxE;
				break;
			case "minE":
					int minE = 0;
					int iminE = -1;
					for (dataIndex = 0; dataIndex < verif.length; dataIndex += 2) {
						if (verif[dataIndex] < minE && verif[dataIndex] != -1)
						{
							minE = verif[dataIndex];
							iminE = dataIndex/2;
						}
					}
					reqVal = iminE;
				break;
			case "overMin":
					int minCartes = 10;
					int minCartesIndex = 0;
					for (int i = 0; i < cols.length; i++) {
						if (cols[i].estPleine())
							continue ;
						cs = cols[i].getCartesInfluences();
						int j = 0;
						while (j < 10 && cs[j] != null)
							j++;
						if (j < minCartes)
						{
							minCartes = j;
							minCartesIndex = i;
						}
					}
					reqVal = minCartesIndex;
				break;
		}
		return reqVal;
	}


	private int verif_carte(String nom){
		for (int i = 0 ; i < this.main.length; i++)
			if (this.main[i] != null && this.main[i].getNom() == nom)
				return (i);
		return (-1);
	}

	static private int getRandomInt(int max) {
		return (int) (new Random().nextInt(max));
	}

	private int setAleatoireIndexMain() {
		return Bot.getRandomInt(3);
	}


	private int setAleatoireIndexColonne(Data data) {
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

	private float remap(float val, float a_u, float a_d, float b_u,float b_d) {
		return (b_u+ ((val - a_u)*(b_d - b_u))/(a_d - a_u));
	}

	private boolean verif_pl(Data data, int index)
	{
		if (data.getPlateau().getColonne(index).estPleine())
		{
			jouer_nn(data);
			return true;
		}
		return false;
	}
}
