package interfaces;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionnaireInterface extends Application {
	
	public Group root = new Group();
	private Node ecranCourant = null;
	private Scene scene = new Scene(root,650,500);

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		root.getChildren().add(new InterfaceMenu(this));
		root.getChildren().add(new InterfaceParametres(this));
		root.getChildren().add(new InterfaceCreerPartie());
		root.getChildren().add(new InterfaceJeu());
		root.getChildren().add(new InterfaceAttente());
		root.getChildren().add(new InterfaceRegles());
		
		primaryStage.setScene(scene);
		
		for(int i=0; i < root.getChildren().size()-1; i++) {
			root.getChildren().get(i).setVisible(false);
		}
		
		afficherEcran(root.getChildren().get(0));
		primaryStage.show();
		
	}

	public static void lancement(String[] args) {
		GestionnaireInterface.launch(args);
	}
	
	public void afficherEcran(Node n) {
		if (ecranCourant != null)
			ecranCourant.setVisible(false);
		n.setVisible(true);
		ecranCourant = n;
	}
	
}

