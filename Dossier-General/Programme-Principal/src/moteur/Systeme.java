package moteur;

import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;

import java.util.ArrayList;

/**
* @generated
*/
public class Systeme {
    
    /**
    * @generated
    */
    private ArrayList<InterfaceParametres> interfaceParametres;
    
    /**
    * @generated
    */
    private ArrayList<InterfaceJeu> interfaceJeu;
    
    /**
    * @generated
    */
    private InterfaceAttente interfaceAttente;
    
    
    public Systeme(ArrayList<InterfaceParametres> ip, ArrayList<InterfaceJeu> ij, InterfaceAttente ia) {
    	interfaceParametres = ip;
    	interfaceJeu = ij;
    	interfaceAttente = ia;
    }
    
    public Systeme() {
    	interfaceParametres = new ArrayList<InterfaceParametres>();
    	interfaceJeu = new ArrayList<InterfaceJeu>();
    	//interfaceAttente = new InterfaceAttente();
    }
    
    /**
    * @generated
    */
    public ArrayList<InterfaceParametres> getInterfaceParametres() {
        return this.interfaceParametres;
    }
    
    /**
    * @generated
    */
    public void setInterfaceParametres(ArrayList<InterfaceParametres> interfaceParametres) {
        this.interfaceParametres = interfaceParametres;
    }
    
    /**
    * @generated
    */
    public ArrayList<InterfaceJeu> getInterfaceJeu() {
        return this.interfaceJeu;
    }
    
    /**
    * @generated
    */
    public void setInterfaceJeu(ArrayList<InterfaceJeu> interfaceJeu) {
        this.interfaceJeu = interfaceJeu;
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

	public Object lancer() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
