package main;

import interfaces.GestionnaireInterface;
import interfaces.InterfaceMenu;
import javafx.scene.paint.Color;
import joueur.*;
import moteur.*;

public class Lanceur {

	public static void main(String[] args) {
		//Voir projet de TD d'IHM
		//Ici on devra lancer lancer le processus (Créer une var sys jsp si vous avez créé une classe "Système")
		GestionnaireInterface.lancement(args); // lance le programme depuis le gestionnaire d'interface

//		Joueur joueur = new Joueur(Color.AQUA, "test");
//		Data data = new Data(joueur, 2);
//		for(int i=0; i<25; i++) {
//			System.out.println("-----"+data.getJoueurs()[0].getReserve()[i]);
//		}
//		
//		System.out.println("--"+data.getJoueurs()[0].getMain()[0]);
//		System.out.println("--"+data.getJoueurs()[0].getMain()[1]);
//		System.out.println("--"+data.getJoueurs()[0].getMain()[2]);

	}
}


