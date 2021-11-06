package joueur;

import java.io.File;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;
import json.JsonInterface;
import json.JsonTraitement;
import moteur.Partie;
import moteur.Resultat;
import cartes.CarteInfluence;

public class Joueur /*extends JsonTraitement implements JsonInterface*/ {

    private CarteInfluence  main[];
    private CarteInfluence  defausse[];
    private CarteInfluence  pioche[];
    private Couleur         couleur;
    private String          pseudo;

	public Joueur() {
		this.pseudo = "guest";
        this.main = new CarteInfluence[3];
        this.defausse = new CarteInfluence[25];
        this.pioche = new CarteInfluence[25];
	}

	/*
    public Joueur(JSONObject obj) throws Exception {
    	this.couleur = (Couleur) obj.get("couleur");
    	this.pseudo = (String) obj.getString("pseudo");
    	this.resultat = null; //A modif avec un constructeur depuis JSON vers Object
    	this.interfaceJeu = null; //A modif avec un constructeur depuis JSON vers Object
    	this.interfaceParametres = null; //A modif avec un constructeur depuis JSON vers Object
    	this.interfaceAttente = null; //A modif avec un constructeur depuis JSON vers Object
    }
    */

    public Couleur getCouleur() {
        return this.couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    // Methodes


	public void creerPartie() {
		Partie partie = new Partie();
		partie.getJoueurs().add(this);
        //partie.start();
	}

	/*
    public boolean joinPartie(int idpartie) {
    	File repertoire = new File("../json/partie");
    	String liste[] = repertoire.list();

    	if (liste != null) {
    		for (int i = 0; i < liste.length; i++) {
    			System.out.println(liste[i]);
    	    }
    	}
    	else {
    		System.err.println("Nom de repertoire invalide");
    	}
    	return false;
    }


	@Override
	public String toJson() {
		return "{\"pseudo\":"+pseudo+",\"couleur\":"+couleur.toString()+"}";//A modif manque les interfaces
	}
	*/
}
