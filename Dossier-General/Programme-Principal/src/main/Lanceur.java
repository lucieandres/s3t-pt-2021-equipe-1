package main;

import interfaces.GestionnaireInterface;
import pp.GestionnaireInterfacePP;
/**
 * Le lanceur du programme.
 * @author S3T - G1
 *
 */
public class Lanceur {
/**
 * La classe main qui lance le programme depuis le gestionnaire d'interface.
 * @param args les arguments lors du lancement.
 */
	public static void main(String[] args) {
		GestionnaireInterface.lancement(args); // lance le programme depuis le gestionnaire d'interface	
		//GestionnaireInterfacePP.lancement(args); // lance le programme depuis le gestionnaire d'interface	
	}
}


