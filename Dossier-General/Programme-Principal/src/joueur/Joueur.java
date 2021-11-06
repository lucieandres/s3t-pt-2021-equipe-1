package joueur;

import java.io.File;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import elements.CartesEnMain;
import elements.Defausse;
import elements.Reserve;
import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;
import json.JsonInterface;
import json.JsonTraitement;
import moteur.Partie;
import moteur.Resultat;

public class Joueur /*extends JsonTraitement implements JsonInterface*/ {

    private Couleur couleur;
    private InterfaceJeu interfaceJeu;
    private InterfaceParametres interfaceParametres;
    private Resultat resultat;
    private InterfaceAttente interfaceAttente;
    private String pseudo;
    private CartesEnMain cartesEnMain;
    private Defausse defausse;
    private Reserve reserve;

    public CartesEnMain getCartesEnMain() {
		return cartesEnMain;
	}

	public void setCartesEnMain(CartesEnMain cartesEnMain) {
		this.cartesEnMain = cartesEnMain;
	}

	public Defausse getDefausse() {
		return defausse;
	}

	public void setDefausse(Defausse defausse) {
		this.defausse = defausse;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

	public Joueur(Couleur couleur, InterfaceJeu interfaceJeu, InterfaceParametres interfaceParametres,
			Resultat resultat, InterfaceAttente interfaceAttente, String pseudo) {
		super();
		this.couleur = couleur;
		this.interfaceJeu = interfaceJeu;
		this.interfaceParametres = interfaceParametres;
		this.resultat = resultat;
		this.interfaceAttente = interfaceAttente;
        // pas besoin d'interface dans le joueur
        // c'est la boule principale qui gére l'affichage
		this.pseudo = pseudo;
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

    public InterfaceJeu getInterfaceJeu() {
        return this.interfaceJeu;
    }

    public void setInterfaceJeu(InterfaceJeu interfaceJeu) {
        this.interfaceJeu = interfaceJeu;
    }

    public InterfaceParametres getInterfaceParametres() {
        return this.interfaceParametres;
    }

    public void setInterfaceParametres(InterfaceParametres interfaceParametres) {
        this.interfaceParametres = interfaceParametres;
    }

    public Resultat getResultat() {
        return this.resultat;
    }

    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    public InterfaceAttente getInterfaceAttente() {
        return this.interfaceAttente;
    }

    public void setInterfaceAttente(InterfaceAttente interfaceAttente) {
        this.interfaceAttente = interfaceAttente;
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
