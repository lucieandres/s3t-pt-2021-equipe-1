package cartes;

import org.json.*;

import json.JsonInterface;
import json.JsonTraitement;

public class Carte extends JsonTraitement implements JsonInterface{
	
	public static void createJsonForPartie() throws Exception {

        JSONObject obj = new JSONObject();
        
        //nom de la carte
        JSONArray nom = new JSONArray();
        obj.put("nom", nom); 
    
        
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
