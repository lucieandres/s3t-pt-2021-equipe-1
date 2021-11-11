package bot;

import java.util.ArrayList;

import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;
import javafx.scene.paint.Color;
import joueur.Joueur;


public class Bot extends Joueur {

	private Color couleur;
	private String pseudo;
    private String difficulte;
	ArrayList<String> typeDifficulte = new ArrayList<String>();

	public Bot(String difficulte, Color couleur, String pseudo) {
		super(couleur, pseudo);
		typeDifficulte.add("facile");
		typeDifficulte.add("moyen");
		typeDifficulte.add("difficile");
		this.couleur=couleur;
		this.pseudo=pseudo;
		if (!typeDifficulte.contains(difficulte)) { throw new IllegalArgumentException("Type de difficulte n'existe pas"); }
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
