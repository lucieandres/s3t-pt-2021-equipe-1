package joueur;

import java.util.ArrayList;
import java.util.Random;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
import cartes.Juliette;
import cartes.Reine;
import cartes.Roi;
import javafx.scene.paint.Color;
import moteur.Data;

public class Joueur /*extends JsonTraitement implements JsonInterface*/ {

    private CarteInfluence  main[];
    private CarteInfluence  defausse[];
	private CarteInfluence  reserve[];
	private CarteObjectif	objectif[];
    private Color           couleur;
    private String          pseudo;
    
    private CarteInfluence CarteSelectionnee = null;
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
		main = new CarteInfluence[3];
	    defausse = new CarteInfluence[25];
		reserve = new CarteInfluence[25];
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

	public void setCarteSelectionnee(CarteInfluence carte) {
		CarteSelectionnee = carte;
	}
	
	public void PoseCarte() { // d�place une carte de la main vers une colonne
		if(CarteSelectionnee != null) {
			
		}
	}

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
    	return null;
//    	int selected = rand.nextInt(listIndex.size());
//    	carte = reserve[listIndex.get(selected)];
//    	return carte;
    }
    
    public void addCarteInfluence(CarteInfluence carteI) {
		for(CarteInfluence carte : main) {
			if(carte == null) {
				carte = carteI;
				break;
			}
		}
    }

	//remplit la reserve du joueur avec les 25 cartes influences de sa couleur
	public void initReserve(){
/*
		Alchimiste alchimiste = new Alchimiste(joueur.getCouleur());
		Assassin assassin = new Assassin(joueur.getCouleur());
		Cardinal cardinal = new Cardinal(joueur.getCouleur());
		Explorateur explorateur = new Explorateur(joueur.getCouleur());
		Juliette juliette = new Juliette(joueur.getCouleur());
		MaitreDArme maitreDArme = new MaitreDArme(joueur.getCouleur());
		Marchant marchant = new Marchant(joueur.getCouleur());
		Reine reine = new Reine(joueur.getCouleur());
		Roi roi = new Roi(joueur.getCouleur());
		Seigneur seigneur = new Seigneur(joueur.getCouleur());
		Tempete tempete = new Tempete(joueur.getCouleur());
		Traitre traitre = new Traitre(joueur.getCouleur());
		Troubadour troubadour = new Troubadour(joueur.getCouleur());
*/
		
		Roi king = new Roi(couleur);
		Reine queen = new Reine(couleur);
		Juliette julie = new Juliette(couleur);
		for(int i = 0; i<8; i++) {
			reserve[3*i] = king;
			reserve[3*i+1] = queen;
			reserve[3*i+2] = julie;
		}
		reserve[reserve.length-1] = queen;
		
/* 		for(int i=0;i<joueurs.length;i++) {
			for(int j=0;j<25;j++){
				reserve.add(carteInfluence);
			}
			Reserve res = new Reserve(reserve);
		}
*/
	}
	
	//choisit 3 cartes aleatoires dans la reserve du joueur
	public void initMainJoueur(Data data){
		CarteInfluence carte = this.getOneCarteInfluenceRandomInReserve();
		data.deplacerCarteInfluence(carte, defausse, main);
	}

    public boolean reserveNulle() {
    	for(int i = 0; i < reserve.length; i++) {
    		if(reserve[i] != null)
    			return false;
    	}
    	return true;
    }
}
