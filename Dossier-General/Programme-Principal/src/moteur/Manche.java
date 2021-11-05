package moteur;

import java.util.ArrayList;

import org.json.JSONObject;

/**
* @generated
*/
public class Manche {
    
    /**
    * @generated
    */
    private ArrayList<Tour> tours;
    
    /**
    * @generated
    */
    private int numero;
    
    /**
    * @generated
    */
    private boolean estFinie;
    
    
    public Manche(ArrayList<Tour> t, int num, boolean f) {
    	tours = t;
    	numero = num;
    	estFinie = f;
    }

    public Manche(int num) {
    	tours = new ArrayList<Tour>();
    	numero = num;
    	estFinie = false;
    }

    public Manche(JSONObject obj) throws Exception {
    	tours = new ArrayList<Tour>(); //tours a modifier avec un constructeur depuis un JSONObject
    	numero = (int) obj.get("numero");
    	estFinie = (boolean) obj.getBoolean("estFinie");
    }
    
    
    
    /**
    * @generated
    */
    public ArrayList<Tour> getTour() {
        return this.tours;
    }
    
    /**
    * @generated
    */
    public void setTour(ArrayList<Tour> tours) {
        this.tours = tours;
    }
    
    /**
    * @generated
    */
    public int getNumero() {
        return this.numero;
    }
    
    /**
    * @generated
    */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    /**
    * @generated
    */
    public boolean getEstFinie() {
        return this.estFinie;
    }
    
    /**
    * @generated
    */
    public void setEstFinie(boolean estFinie) {
        this.estFinie = estFinie;
    }
    
}
