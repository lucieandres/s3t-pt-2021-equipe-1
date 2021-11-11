package bot;

import java.util.Random;
import java.util.Arrays;

import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;
import elements.Colonne;
import cartes.*;
import javafx.scene.paint.Color;
import joueur.Joueur;


public class Bot extends Joueur {

	private Color couleur;
	private String pseudo;
	private String difficulte;
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
		if (!Arrays.asList(this.validDiff).contains(difficulte)) { throw new IllegalArgumentException("Type de difficulte n'existe pas"); }
		this.difficulte = difficulte;
	}

	public String getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}


    public void jouer(Colonne[] cols){
		switch (this.difficulte){
			case "facile":
				jouer_facile(cols);
			break;
			//case "moyen":
				// jouer_moyen(cols.length);
			//break;
			//case "difficile":
				// jouer_difficile(cols.length);
			//break;
		}
	}

	public void jouer_facile(Colonne[] cols)
	{
		int cardIndex = getRandomInt(0, 3); //envoie un nombre aleatoire entre 0 et 2 inclue pour choisir une carte dans la main
		int colIndex = getRandomInt(0, cols.length); //envoie un nombre aleatoire entre 0 et le nombre de colonne-1 inclue pour choisir un emplacement
		// int allFull = 0;
		// for(int i = 0; i < cols.length; i++)
		// 	if(cols[i].estPleine())
		// 		allFull++;
		// if (allFull == cols.length) //Toutes les colones sont pleines -> BUG
		// 	//erreur à gérer;
		while (cols[colIndex].estPleine()) //verifie si la colonne est pleine
			colIndex = getRandomInt(0, cols.length);

		posercarte(this.main[cardIndex], colIndex);
		piocher();
	}
	// public void jouer_moyen(Colonne[] cols)
	// {
	// }
	// public void jouer_difficile(Colonne[] cols)
	// {
	// }

	private void piocher()
	{
		int top = get_top_index(this.reserve);
		if (top != 0)
			this.main[2] = this.reserve[top - 1];
		else
		{
			for (int i = 0; i < this.defausse.length; i++)
			{
				this.reserve[i] = this.defausse;
				this.defausse[i] = null;
			}
			piocher();
		}
	}

	static private int get_top_index(CarteInfluence[] pile)
	{
		int i = -1;
		while (pile[++i] != null)
			;
		return (i);
	}

	static private int getRandomInt(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
