package moteur;



import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import elements.Plateau;
import javafx.scene.paint.Color;
import joueur.Joueur;
import elements.*;
import json.JsonInterface;
import json.JsonTraitement;

/**
* @generated
*/
public class Partie /*extends JsonTraitement implements JsonInterface*/ {

    private String code;
    private int manche;
    private int tour;

    public Partie(Joueur j, Data data) {
        data.setMaster(j);
    	this.code = code;
    }

    /*
    public Partie(JSONObject obj) throws Exception {

    	joueurs = new ArrayList<Joueur>();
    	JSONArray getjoueur = (JSONArray) obj.get("joueurs");
    	for(int i=0; i<getjoueur.length(); i++) {
    		Joueur j = new Joueur(getjoueur.getJSONObject(i));
    		joueurs.add(j);
    	}
    	manches = new ArrayList<Manche>();
    	JSONArray getmanche = (JSONArray) obj.get("manche");
    	for(int i=0; i<getmanche.length(); i++) {
    		Manche m = new Manche(getmanche.getJSONObject(i));
    		manches.add(m);
    	}
    	this.code = obj.getString("partie");
    }
    */


    /**
    * @generated
    */
    public String getCode() {
        return this.code;
    }

    /**
    * @generated
    */
    public void setCode(String code) {
        this.code = code;
    }

    public Joueur getJoueurByCouleur(Data data, Color couleur) {
    	for(int i=0; i<data.getJoueurs().length; i++) {
    		if(data.getJoueurs()[i].getCouleur()==couleur) {
    			return data.getJoueurs()[i];
    		}
    	}
		return null;
    }

    public static void createPartie(Data data, Joueur j) {
        data.setJoueurs(new Joueur[4]);
        data.getJoueurs()[0] = j;
        data.setPlateau(new Plateau(4));
    }

    
    /*
	public static void createJsonForPartie() throws Exception {

	      JSONObject obj = new JSONObject();
	 //random id partie
	      String id = String.valueOf((int)(Math.random()*Math.pow(10, 10)));
	      obj.put("partie", id);
	 //add player initiateur de la partie
		  JSONArray joueurs = new JSONArray();
		  joueurs.put("");
		  obj.put("joueurs", joueurs);
	 //init des manches
	      obj.put("manche", 0);
	 //init des tours
	      obj.put("tour", 0);
	 //init des cartes sur le plateau
		  JSONArray plateau = new JSONArray();
		  plateau.put("");
	      obj.put("plateau", plateau);

	 //creation du json
	      createJsonFromObject("../json/partie/"+id+".json", obj);

	}

	@Override
	public String toJson() {
		String jsonjoueur ="";
		for(int i=0; i<joueurs.size(); i++) {
			jsonjoueur = joueurs + ","+ joueurs.get(i).toJson();
		}
		return "{\"partie\":"+code+", \"plateau\":[], \"joueurs\":"+jsonjoueur+"}";

	}
	*/

}
