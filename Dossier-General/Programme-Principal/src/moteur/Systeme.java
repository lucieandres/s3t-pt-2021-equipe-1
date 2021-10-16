package moteur;

import java.util.Set;

import interfaces.InterfaceAttente;
import interfaces.InterfaceJeu;
import interfaces.InterfaceParametres;

import java.util.HashSet;

/**
* @generated
*/
public class Systeme {
    
    /**
    * @generated
    */
    private Set<InterfaceParametres> interfaceParametres;
    
    /**
    * @generated
    */
    private Set<InterfaceJeu> interfaceJeu;
    
    /**
    * @generated
    */
    private InterfaceAttente interfaceAttente;
    
    
    
    /**
    * @generated
    */
    public InterfaceParametres getInterfaceParametres() {
        return this.interfaceParametres;
    }
    
    /**
    * @generated
    */
    public InterfaceParametres setInterfaceParametres(InterfaceParametres interfaceParametres) {
        this.interfaceParametres = interfaceParametres;
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
    public InterfaceJeu setInterfaceJeu(InterfaceJeu interfaceJeu) {
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
    public InterfaceAttente setInterfaceAttente(InterfaceAttente interfaceAttente) {
        this.interfaceAttente = interfaceAttente;
    }
    
    
}
