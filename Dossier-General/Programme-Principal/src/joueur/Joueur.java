package joueur;

import java.io.File;
import java.util.Date;

import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;
import moteur.Resultat;

/**
* @generated
*/
public class Joueur {

    /**
    * @generated
    */
    private Couleur couleur;

    /**
    * @generated
    */
    private InterfaceJeu interfaceJeu;

    /**
    * @generated
    */
    private InterfaceParametres interfaceParametres;

    /**
    * @generated
    */
    private Resultat resultat;

    /**
    * @generated
    */
    private InterfaceAttente interfaceAttente;

    /**
    * @generated
    */
    private  String pseudo;

    /**
    * @generated
    */

    
    public Joueur(Couleur couleur, InterfaceJeu interfaceJeu, InterfaceParametres interfaceParametres,
			Resultat resultat, InterfaceAttente interfaceAttente, String pseudo) {
		super();
		this.couleur = couleur;
		this.interfaceJeu = interfaceJeu;
		this.interfaceParametres = interfaceParametres;
		this.resultat = resultat;
		this.interfaceAttente = interfaceAttente;
		this.pseudo = pseudo;
	}

	/**
    * @generated
    */
    public Couleur getCouleur() {
        return this.couleur;
    }

    /**
    * @generated
    */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
    * @generated
    */
    public InterfaceJeu getInterfaceJeu() {
        return this.interfaceJeu;
    }

    /**
    * @generated
    */
    public void setInterfaceJeu(InterfaceJeu interfaceJeu) {
        this.interfaceJeu = interfaceJeu;
    }

    /**
    * @generated
    */
    public InterfaceParametres getInterfaceParametres() {
        return this.interfaceParametres;
    }

    /**
    * @generated
    */
    public void setInterfaceParametres(InterfaceParametres interfaceParametres) {
        this.interfaceParametres = interfaceParametres;
    }

    /**
    * @generated
    */
    public Resultat getResultat() {
        return this.resultat;
    }

    /**
    * @generated
    */
    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    /**
    * @generated
    */
    public InterfaceAttente getInterfaceAttente() {
        return this.interfaceAttente;
    }

    /**
    * @generated
    */
    public void setInterfaceAttente(InterfaceAttente interfaceAttente) {
        this.interfaceAttente = interfaceAttente;
    }

    /**
    * @generated
    */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
    * @generated
    */
    public void setPseudo( String pseudo) {
        this.pseudo = pseudo;
    }
    
    // Methodes
    
    public boolean joinPartie(int idpartie) {
    	File repertoire = new File("../json/partie");
    	String liste[] = repertoire.list();    
    	
    	if (liste != null) {         
    		for (int i = 0; i < liste.length; i++) {
    			System.out.println(liste[i]);
    	    }
    	} else {
    		System.err.println("Nom de repertoire invalide");
    	}
    	return false;
    }
}
