package joueur;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;
import javafx.scene.paint.Color;
import json.JsonInterface;
import json.JsonTraitement;
import moteur.Partie;
import cartes.CarteInfluence;
import cartes.CarteObjectif;

public class Joueur /*extends JsonTraitement implements JsonInterface*/ {

    private CarteInfluence  main[] = new CarteInfluence[3];
    private CarteInfluence  defausse[] = new CarteInfluence[25];
	private CarteInfluence  reserve[] = new CarteInfluence[25];
	private CarteObjectif	objectif[];
    private Color           couleur;
    private String          pseudo;
/*
	public Joueur() {
		this.pseudo = "guest";
        this.main = new CarteInfluence[3];
        this.defausse = new CarteInfluence[25]; 
        this.reserve = new CarteInfluence[25]; //pioche de cartes influences du joueur
	}*/

	public Joueur (Color couleur, String pseudo) {
		this.pseudo = pseudo;
		this.couleur = (Color) couleur;
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

    public Color getCouleur() {
        return this.couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = (Color) couleur;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
	
	public CarteInfluence[] getReserve() {
		return reserve;
	}

	public void setReserve(CarteInfluence[] reserve) {
		this.reserve = reserve;
	}
    public CarteInfluence[] getMain() {
		return main;
	}

	public void setMain(CarteInfluence[] main) {
		this.main = main;
	}

	public CarteInfluence[] getDefausse() {
		return defausse;
	}

	public void setDefausse(CarteInfluence[] defausse) {
		this.defausse = defausse;
	}


    // Methodes


//	public void creerPartie() {
//		Partie partie = new Partie();
//		partie.getJoueurs().add(this);
//        //partie.start();
//	}

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
	public void initMain() {
		int i=0;
		for(CarteInfluence carte : main) {
			if(carte == null) 
				i++;
		}
		if(i<3) {
			//exception
		}
		else {
			for(CarteInfluence carte : main) {
				carte = getOneCarteInfluenceRandomInReserve();
			}
		}
	}
	
    public CarteInfluence getOneCarteInfluenceRandomInReserve() {
    	Random rand = new Random();
    	CarteInfluence carte;
    	ArrayList<Integer> listIndex = new ArrayList<>();
    	for(int i = 0; i< reserve.length ; i++) {
    		if(reserve[i] != null) {
    			listIndex.add(i);
    		}
    	}
    	int selected = rand.nextInt(listIndex.size());
    	carte = reserve[listIndex.get(selected)];
    	return carte;
    }
    
    public void addCarteInfluence(CarteInfluence carteI) {
		for(CarteInfluence carte : main) {
			if(carte == null) {
				carte = carteI;
				break;
			}
		}
    }
	
}
