package main;

import interfaces.GestionnaireInterface;
import interfaces.InterfaceMenu;
import joueur.*;
import moteur.*;

public class Lanceur {

	public static void main(String[] args) {
		//Voir projet de TD d'IHM
		//Ici on devra lancer lancer le processus (Créer une var sys jsp si vous avez créé une classe "Système")
		Joueur you = new Joueur();
		Data data = new Data();
		data.setJoueurs(null);
		data.setPlateau(null);
		Systeme jeu = new Systeme();
		GestionnaireInterface.lancement(args); // lance le programme depuis le gestionnaire d'interface

		Partie partie = new Partie(you, data);
		partie.createPartie(data, you);

	}

/*
	Scene scene1, scene2;

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("My First JavaFX GUI");

		//Scene 1
		Label label1= new Label("This is the first scene");
		Button button1= new Button("Go to scene 2");
		button1.setOnAction(e -> primaryStage.setScene(scene2));
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, button1);
		scene1= new Scene(layout1, 300, 250);

		//Scene 2
		Label label2= new Label("This is the second scene");
		Button button2= new Button("Go to scene 1");
		button2.setOnAction(e -> primaryStage.setScene(scene1));
		VBox layout2= new VBox(20);
		layout2.getChildren().addAll(label2, button2);
		scene2= new Scene(layout2,300,250);


		primaryStage.setScene(scene1);
		primaryStage.show();
	}
	    */
}


