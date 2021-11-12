package bot;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

import elements.Colonne;
import cartes.*;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;


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
	
	@Override
    public void jouer(Data data, int indexMain, int indexColonne) {
		switch (this.difficulte){
			case "facile":
				indexMain = setAleatoireIndexMain();
				indexColonne = setAleatoireIndexColonne(data);
				data.jouerCarte(indexMain, indexColonne);
			break;
			//case "moyen":
				// jouer_moyen(cols.length);
			//break;
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
	public void jouer_moyen(Colonne[] cols) {
	}
	public void jouer_difficile(Colonne[] cols) {
	}

	static private int getRandomInt(int max) {
		return (int) (new Random().nextInt(max));
	}
}
