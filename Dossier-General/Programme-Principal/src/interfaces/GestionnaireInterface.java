package interfaces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import bot.Bot;
import cartes.CarteInfluence;
import elements.Colonne;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import moteur.Data;
import pp.InterfacePlateau;
import reseau.CommunicationClient;
import reseau.Message;
import reseau.ReponseMessageTCP;
import reseau.ReponseMessageUDP;
import reseau.SocketServeurTCP;
import reseau.TypeDeMessage;


/**
 * Cette classe permet de controler les interfaces, c'est elle qui fera la liaison entre les interfaces et le programme principal.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class GestionnaireInterface extends Application {
	
	public Stage MainStage;
	public Group root = new Group();
	private Node ecranCourant = null;
	private boolean estFinie = false;
	protected static Data data;
	public InterfaceJeu Jeux = null; // must be done to pass data from creerPartie to Jeu
	public InterfaceFin Fin = null;
	public InterfacePlateau Plateau = null;
	
	// Reseau
	private final static String ipGroupe ="224.7.7.7";
	private final static int portGroupe = 7777;
	
	public static ReponseMessageUDP myUDPCallback = new ReponseMessageUDP() {
		@Override
		
		/**
		 * 
		 * Classe implémentée permettant de tester l'échange de messages UDP.
		 * 
		 */
		
		public void onMessage(Message message) {
			System.out.println("TestClient myUDPCallback(" + message + ")");
			if (message.getType() == TypeDeMessage.ACP) {
				// Une partie est créée on la rejoint
				int serverPort = message.getPort();
				String serverName = message.getIp();
//				try {
//					SocketServeurTCP client = rejoindrePartie(serverName, serverPort);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
	};
	CommunicationClient com = new CommunicationClient(myUDPCallback);
	

	private static ReponseMessageTCP myTCPCallback = new ReponseMessageTCP() {
		@Override
		
		/**
		 * 
		 * Classe implémentée permettant de tester l'échange de messages TCP.
		 * 
		 */
		
		public void onMessage(Socket socket, Message message) {
			System.out.println("TestClient myTCPCallback(" + message + ")");
			if (message.getType() == TypeDeMessage.ADP)
				System.out.println("TestClient partie rejointe");
		}
	};
	
	//
	
	public Node UIParentID = null;
	protected Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
	
	public LinkedHashMap<String, Pane> InterfaceMap = new LinkedHashMap<String, Pane>();
	
//	public Properties texte; // text data
	HashMap<String,Properties> texteLangue = new HashMap<String,Properties>(); // list of text files
//	public String PropertiesLocalisation = "./resources/textes/"; // text file location
	/**
	 * Cette méthode permet de lancer l'interface graphique.
	 * Elle va initialiser tous les écrans disponible et afficher le premier écran qui sera le menu principal.
	 * Le jeu sera mis direct en plein écran.
	 * 
	 * @param primaryStage Le composant qui va contenir toutes les scènes
	 * 
	 * @since 1.0
	 */
	public void start(Stage primaryStage) throws Exception {
		
		texteLangue.put("français",readPropertiesFile("/textes/texte_fr.properties"));
		texteLangue.put("english",readPropertiesFile("/textes/texte_eng.properties"));

		//Properties texte = readPropertiesFile("/textes/texte_fr.properties"); // initialise
		//System.out.println(texte.getProperty("texte.titre"));
		//fichierTexte.getProperty(element.getValue())
		
		//System.out.println(texte.getProperty("bouton.regle"));
		
		Jeux = new InterfaceJeu(this);
		Fin = new InterfaceFin(this);
		InterfaceMap.put("menu", new InterfaceMenu(this));
		InterfaceMap.put("parametres", new InterfaceParametres(this));
		InterfaceMap.put("creerPartie", new InterfaceLocalCreerPartie(this));
//		InterfaceMap.put("creerPartieEnLigne", new InterfaceLigneCreerPartie(this));
		InterfaceMap.put("jeu", Jeux );
//		InterfaceMap.put("attente", new InterfaceAttente(this));
		InterfaceMap.put("regles", new InterfaceRegles(this));
		InterfaceMap.put("rejoindre", new InterfaceRejoindrePartie(this));
		InterfaceMap.put("finPartie", Fin);
//		InterfaceMap.put("recherche", new InterfaceRecherche(this));
		
		//add instances of the interfaces in the root
		
		for (Map.Entry<String, Pane> mapElement : InterfaceMap.entrySet()) {
            root.getChildren().add(mapElement.getValue());
        }
		
		primaryStage.setScene(scene);
		primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
		primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 4);
		primaryStage.setMaximized(true);
//		primaryStage.setResizable(false);
		primaryStage.setFullScreen(true);
		//primaryStage.setFullScreenExitHint(""); stop fullscreen message
		primaryStage.setTitle("De cape et d'epee"); //name of the stage
		
		for(int i=0; i < root.getChildren().size(); i++) { //set everyone invisible
			root.getChildren().get(i).setVisible(false);
		}
		
		System.out.println(screenBounds.getWidth());
		System.out.println(screenBounds.getHeight());
		
		afficherEcran(InterfaceMap.get("menu"));// show menu
		
		primaryStage.show();
		MainStage = primaryStage;
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
	 * Cette méthode permet de jouer une partie.
	 * Elle appelle la fonction rafraichir à chaque fois qu'un joueur joue.
	 * Elle peut aussi mettre fin à une manche et à une partie.
	 * 
	 * 
	 * @since 1.0
	 */
	public void doitJouer() throws Exception {
		if(!verifManche(data) && estFinie == false) {
	    	if(data.getJoueurs()[data.getCurrentJoueur()] instanceof Bot) {
	    		data.getJoueurs()[data.getCurrentJoueur()].jouer(data, 0, 0);
	    		rafraichir(this);
	    		doitJouer();
	    	}
	    	rafraichir(this);
    	}
    	else {
    		System.out.println("++++++++++"+data.partieFinie());
    		if(!data.partieFinie()) {
	    		estFinie = true;
	        	data.activerCartesARetardement();
	        	data.retournerCarte();
	        	rafraichir(this);
	        	data.finDeManche();
	        	rafraichir(this);
	    		estFinie = false;
	    		doitJouer();
    		}
    		else {
    			System.out.println("fin de la partie");
	    		estFinie = true;
	        	data.retournerCarte();
	        	rafraichir(this);
	        	data.finDeManche();
	        	rafraichir(this);
    			this.afficherEcran(InterfaceMap.get("finPartie"));
    			Fin.afficherStats(data);
    		}
    	}
	}
	
	/**
	 * Permet de rafraichir l'écran du joueur afin d'avoir le résultat des actions des joueurs pendant la partie.
	 * 
	 * @param data Donnée du jeu qui permettront de savoir où en est le jeu.
	 * @throws InterruptedException 
	 * 
	 * @since 1.0
	 */
	
    public void rafraichir(GestionnaireInterface GI) { // Rafraichissement de l'écran courant
	    	for(int i=0; i<GI.getData().getPlateau().getColonnes().length; i++) {
	    		for(int j=0; j<GI.getData().getPlateau().getColonnes()[i].getCartesInfluences().length; j++) {
	    			
	    			Jeux.drawPartie(GI);
	    			
	    		}
	    	}
    }
    
	/**
	 * Permet de rafraichir l'écran du plateau afin d'avoir le résultat des actions des joueurs pendant la partie.
	 * 
	 * @param data Donnée du jeu qui permettront de savoir où en est le jeu.
	 * @throws InterruptedException 
	 * 
	 * @since 1.0
	 */
    
    // Mettre en place l'implémentation réseau
    
//    public void rafraichirPlateau(GestionnaireInterface GI) { // Rafraichissement de l'écran courant
//    	for(int i=0; i<GI.getData().getPlateau().getColonnes().length; i++) {
//    		for(int j=0; j<GI.getData().getPlateau().getColonnes()[i].getCartesInfluences().length; j++) {
//    			
//    			Plateau.drawPartie(GI);
//    			
//    		}
//    	}
//    }
    
    
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
    	boolean verif = true;
    	for(int i = 0; i<data.getPlateau().getColonnes().length; i++) {
    		if(!data.getPlateau().getColonnes()[i].estPleine() && !data.getPlateau().getColonnes()[i].estFiniEtreRempli()) {
    			verif = false;
    		}
    	}
    	return verif;
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
    
    /**
     * Cette méthode permet de définir les données actuelles du jeu
     * 
     * @param data Données actuelles du jeu
     */
    public void setData(Data data) {
    	GestionnaireInterface.data = data;
    }
    
    /**
     * Cette méthode permet de charger les fichiers textuels .properties
     * 
     * @param data Données actuelles du jeu
     */
    public static Properties readPropertiesFile(String fileName) throws IOException {
	      InputStream fis = null;
	      Properties prop = null;
	      try {	 
	    	  ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    	  //fis = loader.getResourceAsStream(fileName);
	    	  fis = GestionnaireInterface.class.getResourceAsStream(fileName);
	    	  //if (fis == null) fis = loader.getResourceAsStream("resources/" + fileName);
	    	  prop = new Properties();
	    	  prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }

	public static String getIpgroupe() {
		return ipGroupe;
	}

	public static int getPortgroupe() {
		return portGroupe;
	}

	public static ReponseMessageTCP getMyTCPCallback() {
		return myTCPCallback;
	}

	public static void setMyTCPCallback(ReponseMessageTCP myTCPCallback) {
		GestionnaireInterface.myTCPCallback = myTCPCallback;
	}
}

