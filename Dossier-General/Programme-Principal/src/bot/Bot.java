package bot;

import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;
import javafx.scene.paint.Color;


public class Bot{

	private Color couleur;
	private String pseudo;
    private String difficulte;
	ArrayList<String> typeDifficulte = new ArrayList<String>();
	typeDifficulte.add("facile","moyen","difficile");

	public Bot(String difficulte, Color couleur, String pseudo) {
		this.couleur=couleur;
		this.pseudo=pseudo;
		if !(typeDifficulte.contains(difficulte)) { throw new IllegalArgumentException("Type de difficulte n'existe pas"); }
		this.difficulte = difficulte;
	}
	/*
	public String getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}
    */
    public void jouer(){
		switch (this.difficulte){
			case "facile":

			case "moyenne":
			case "difficile":
		}


	}
}
