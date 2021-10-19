package main;
 
import interfaces.InterfaceMenu;
import moteur.Systeme;

public class Lanceur{ 
  
	public static void main(String[] args) {
		//Voir projet de TD d'IHM
		//Ici on devra lancer lancer le processus (Créer une var sys jsp si vous avez créé une classe "Système")
		Systeme jeu = new Systeme();
		InterfaceMenu.lancement(args, jeu.lancer());
	}
}
	

