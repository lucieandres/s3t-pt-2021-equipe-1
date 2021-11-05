package interfaces;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionnaireInterface extends Application {
	
	public Group root = new Group();
	private Node ecranCourant = null;
	
	private Scene scene = new Scene(root,1920,1080);

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//add instances of the interfaces in the root
		root.getChildren().add(new InterfaceMenu(this));
		root.getChildren().add(new InterfaceParametres(this));
		root.getChildren().add(new InterfaceCreerPartie());
		root.getChildren().add(new InterfaceJeu());
		root.getChildren().add(new InterfaceAttente());
		root.getChildren().add(new InterfaceRegles());
		
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(true);
		//primaryStage.setFullScreenExitHint(""); stop fullscreen message
		primaryStage.setTitle("De cape et d'épée"); //name of the stage
		
		for(int i=0; i < root.getChildren().size(); i++) { //set everyone invisible
			root.getChildren().get(i).setVisible(false);
		}
		
		afficherEcran(root.getChildren().get(5));// show menu
		primaryStage.show();
		
	}

	public static void lancement(String[] args) {
		GestionnaireInterface.launch(args);
	}
	
	public void afficherEcran(Node n) { // function used to switch Node visibility
		if (ecranCourant != null)
			ecranCourant.setVisible(false);
		n.setVisible(true);
		ecranCourant = n;
	}
	
}

