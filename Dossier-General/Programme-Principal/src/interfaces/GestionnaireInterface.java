package interfaces;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import cartes.CarteInfluence;
import elements.Colonne;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import moteur.Data;

public class GestionnaireInterface extends Application {
	
	public Group root = new Group();
	private Node ecranCourant = null;
	
	public Node UIParentID = null;
	
	private Scene scene = new Scene(root,1920,1080);
	
	public LinkedHashMap<String, Pane> InterfaceMap = new LinkedHashMap<String, Pane>();
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		InterfaceMap.put("menu", new InterfaceMenu(this));
		InterfaceMap.put("parametres", new InterfaceParametres(this));
		InterfaceMap.put("creerPartie", new InterfaceCreerPartie(this));
		InterfaceMap.put("jeu", new InterfaceJeu(this));
		InterfaceMap.put("attente", new InterfaceAttente(this));
		InterfaceMap.put("regles", new InterfaceRegles(this));
		
		//add instances of the interfaces in the root
		
		for (Map.Entry<String, Pane> mapElement : InterfaceMap.entrySet()) {
            root.getChildren().add(mapElement.getValue());
        }
		
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(true);
		//primaryStage.setFullScreenExitHint(""); stop fullscreen message
		primaryStage.setTitle("De cape et d'epee"); //name of the stage
		
		for(int i=0; i < root.getChildren().size(); i++) { //set everyone invisible
			root.getChildren().get(i).setVisible(false);
		}
		
		afficherEcran(root.getChildren().get(0));// show menu
		primaryStage.show();
		
	}

	public static void lancement(String[] args) {
		GestionnaireInterface.launch(args);
	}
	
	public void afficherEcran(Node n) { // function used to switch Node visibility
		
		UIParentID = ecranCourant;
		
		if (ecranCourant != null)
			ecranCourant.setVisible(false);
		n.setVisible(true);
		ecranCourant = n;
	}
	
    public void refresh(Data data) { // Rafraichissement de l'Ã©cran courant
    	for(int i=0; i<data.getPlateau().getColonnes().length; i++) {
    		for(int j=0; j<data.getPlateau().getColonne()[i].getCartesInfluences().length; j++) {
    			if(data.getPlateau().getColonne()[i].getCartesInfluences()[j] != null) {
    				//affichage de la carte dans la colonne i à la place j
    				//il faut donc pouvoir différencier chaque case du tableau d'affichage 
    			}
    		}
    	}
    }
    
    public void update(Data data) {
    	 
    }
     
    public boolean arbitre(Data data, CarteInfluence carte, int index) {
    	Colonne col = data.getPlateau().getColonnes()[index];
    	if(col.estPleine()) {
    		// Impossible de placer la carte -> statut erreur
    		return false;
    	}
    	else {
    		//verif si la carte n'est pas déjà joué
    		//n'est pas utilisé pour l'instant car on utilise plusieurs fois la meme carte (pour cette version)
//    		for(int i=0; i<col.getCartesInfluences().length; i++) {
//    			if(col.getCartesInfluences()[i] == carte) {
//    				return false;
//    			}
//    		}
    	}
    	return true;
    }
    
}

