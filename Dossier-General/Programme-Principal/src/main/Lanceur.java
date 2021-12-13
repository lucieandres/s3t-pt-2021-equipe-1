package main;

import interfaces.GestionnaireInterface;
import pp.GestionnaireInterfacePP;

public class Lanceur {

	public static void main(String[] args) {
		GestionnaireInterface.lancement(args); // lance le programme depuis le gestionnaire d'interface	
		//GestionnaireInterfacePP.lancement(args); // lance le programme depuis le gestionnaire d'interface	
	}
}


