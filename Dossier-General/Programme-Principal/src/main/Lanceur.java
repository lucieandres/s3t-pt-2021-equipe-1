package main;
 
import interfaces.InterfaceMenu;
import moteur.Systeme;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Lanceur extends Application{ 
  
	public static void main(String[] args) {
		//Voir projet de TD d'IHM
		//Ici on devra lancer lancer le processus (Créer une var sys jsp si vous avez créé une classe "Système")
		Systeme jeu = new Systeme();
		launch(args);
		//InterfaceMenu.lancement(args, jeu.lancer());
	}

	 @Override
	   public void start(Stage primaryStage) {
	       primaryStage.setTitle("Hello World!");
	       Button btn = new Button();
	       btn.setText("Say 'Hello World'");
	       btn.setOnAction(new EventHandler<ActionEvent>() {

	           @Override
	           public void handle(ActionEvent event) {
	               System.out.println("Hello World!");
	           }
	       });
	       
	       StackPane root = new StackPane();
	       root.getChildren().add(btn);
	       primaryStage.setScene(new Scene(root, 300, 250));
	       primaryStage.show();
	   }

}
	

