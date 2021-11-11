package interfaces;

import java.util.LinkedHashMap;
import java.util.Map;

import cartes.CarteInfluence;
import elements.Colonne;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import moteur.Data;


/**
 * Cette classe permet de controler les interfaces, c'est elle qui fera la liaison entre les interfaces et le programme principal.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class GestionnaireInterface extends Application {
	
	public Group root = new Group();
	private Node ecranCourant = null;
	
	protected static Data data;
	public InterfaceJeu Jeux = null; // must be done to pass data from creerPartie to Jeu
	
	public Node UIParentID = null;
	private Scene scene = new Scene(root,1920,1080);
	
	public LinkedHashMap<String, Pane> InterfaceMap = new LinkedHashMap<String, Pane>();
	

	/**
	 * Cette méthode permet de lancer l'interface graphique.
	 * Elle va initialiser tout les écrans disponible et afficher le premier écran qui sera le menu principal.
	 * Le jeu sera mis direct en plein écran.
	 * 
	 * @param primaryStage Le composant qui va contenir toutes les scènes
	 * 
	 * @since 1.0
	 */
	public void start(Stage primaryStage) throws Exception {
		
		Jeux = new InterfaceJeu(this);
		InterfaceMap.put("menu", new InterfaceMenu(this));
		InterfaceMap.put("parametres", new InterfaceParametres(this));
		InterfaceMap.put("creerPartie", new InterfaceCreerPartie(this));
		InterfaceMap.put("jeu", Jeux );
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
		
		afficherEcran(InterfaceMap.get("menu"));// show menu
		primaryStage.show();
		
	}
	
	/**
	 * Cette méthode lance le jeu ainsi que l'interface.
	 * 
	 * @param args ?.
	 * 
	 * @since 1.0
	 */
	
	public static void lancement(String[] args) {
		GestionnaireInterface.launch(args);
	}
	
	/**
	 * Cette méthode permet d'afficher un écran.
	 * On l'utilisera surtout pour changer d'écran lorsque l'utilisateur veut naviguer dans l'interface.
	 * Exemple : Menu -> Option
	 * 
	 * @param n L'écran qu'on veut afficher.
	 * 
	 * @since 1.0
	 */
	
	public void afficherEcran(Node n) { // function used to switch Node visibility
		
		UIParentID = ecranCourant;
		
		if (ecranCourant != null)
			ecranCourant.setVisible(false);
		n.setVisible(true);
		ecranCourant = n;
	}
	
	/**
	 * Permet de rafraichir l'écran afin d'avoir le résultat des actions des joueurs pendant la partie.
	 * 
	 * @param data Donnée du jeu qui permettront de savoir où en est le jeu.
	 * 
	 * @since 1.0
	 */
	
    public void refresh(Data data) { // Rafraichissement de l'écran courant
    	for(int i=0; i<data.getPlateau().getColonnes().length; i++) {
    		for(int j=0; j<data.getPlateau().getColonnes()[i].getCartesInfluences().length; j++) {
    			if(data.getPlateau().getColonnes()[i].getCartesInfluences()[j] != null) {
    				
    				Jeux.drawPartie(data);
    			}
    		}
    	}
    }
    
    /**
     * Cette méthode vérifie qu'une carte peut être jouée.
     * 
     * @param data Données de la partie actuelle.
     * 
     * @param carte Carte testé pour être joué.
     * 
     * @param index le numéro de colonne où la carte va être joué.
     * 
     * @return true si on peut la jouer, false si on peut pas la jouer.
     * 
     * @since 1.0
     */
    
    public boolean arbitre(Data data, CarteInfluence carte, int index) {
    	Colonne col = data.getPlateau().getColonnes()[index];
    	if(col.estPleine()) {
    		// Impossible de placer la carte -> statut erreur
    		return false;
    	}
    	else {
    		//verif si c'est bien a ce joueur de jouer
    		if(carte.getCouleur() != data.getJoueurs()[data.getCurrentTour()].getCouleur()) {
    			return false;
    		}
    		//verif si la carte n'est pas deja joue
    		//n'est pas utilis� pour l'instant car on utilise plusieurs fois la meme carte (pour cette version)
//    		for(int i=0; i<col.getCartesInfluences().length; i++) {
//    			if(col.getCartesInfluences()[i] == carte) {
//    				return false;
//    			}
//    		}
    	}
    	return true;
    }
    
    /**
     * Cette méthode vérifie si la manche est terminée.
     * 
     * @param data Données de la partie actuelle.
     * 
     * @return true si la manche est finie, false si la manche n'est pas encore fini.
     * 
     * @since 1.0
     */
    
    public boolean verifManche(Data data) {
    	Colonne[] cols = data.getPlateau().getColonnes();
    	for(Colonne col : cols) {
    		if(!col.estPleine()) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Cette méthode permet de retourner les données actuelles du jeu
     * 
     * @return les données du jeu
     * 
     * @since 1.0
     */
    public Data getData() {
    	return data;
    }

    public void setData(Data data) {
    	this.data = data;
    }
}

