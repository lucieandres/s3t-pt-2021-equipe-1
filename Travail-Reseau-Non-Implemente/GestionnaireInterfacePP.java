package pp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import joueur.Joueur;
import moteur.Data;
import reseau.CommunicationServeur;
import reseau.Message;
import reseau.ReponseMessageTCP;
import reseau.ReponseMessageUDP;
import reseau.TypeDeMessage;


/**
 * Cette classe permet de controler les interfaces, c'est elle qui fera la liaison entre les interfaces et le programme principal.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class GestionnaireInterfacePP extends Application {
	
	public Stage MainStage;
	public Group root = new Group();
	private Node ecranCourant = null;
	private boolean estFinie = false;
	protected static Data data;
	public InterfaceFin Fin = null;
	public InterfacePlateau Plateau = null;
	
	public Node UIParentID = null;
	protected Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	protected static String ipGroupe = "224.7.7.7";
	protected static final int portGroupe = 7777;
	
	protected static int nombreJoueurReel = 0;
	protected static int nombreJoueurReelConnecte = 0;
	protected static int nombreJoueurVirtuel = 0;
	protected static int nombreJoueurVirtuelConnecte = 0;
	protected static String idPartie;
	protected static ArrayList<String> idJoueurs;
	protected static ArrayList<Socket> sj;
	protected static int currentManche = 0;
	
	// Reseau

	public static ReponseMessageUDP myUDPCallback = new ReponseMessageUDP() {
		@Override
		
		/**
		 * 
		 * Classe implémentée permettant de tester l'échange de messages UDP.
		 * 
		 */
		
		public void onMessage(Message message) {
			switch(message.getType()) {
			case ACP:
				if(ipGroupe == message.getIp() && portGroupe == message.getPort()) {
					data = new Data(message.getNbj(), message.getNom(), message.getStatut());
					nombreJoueurReel = message.getNbjrm();
					nombreJoueurVirtuel = message.getNbjvm();
					idPartie = message.getIdp();
					//a finir
				}
				break;
				
			case AMP:
				if(ipGroupe == message.getIp() && portGroupe == message.getPort()) {
					data.setStatut(message.getStatut());
					nombreJoueurReelConnecte = message.getNbjrc();
					nombreJoueurVirtuelConnecte = message.getNbjvc();
					//a finir
				}
				break;
			case RUP:
				// permet de recup une liste de partie
				break;
			}
		}
	};

	
	private static ReponseMessageTCP myTCPCallback = new ReponseMessageTCP() {
		@Override
		public void onMessage(Socket socket, Message message) {
			switch(message.getType()) {
			case DCP:
				if(message.getIdp() == idPartie) {
					ajouterJoueur(socket, message.getNomj(), message.getTypej());
				}
				break;
				
			case JCI:
				if(message.getIdp() == idPartie && idJoueurs.contains(message.getIdj()) && idJoueurs.get(data.getCurrentJoueur()) == message.getIdj()) {
					for(int i = 0; i < data.getJoueurs()[data.getCurrentJoueur()].getMain().length; i++) {
						if(message.getCi() == data.getJoueurs()[data.getCurrentJoueur()].getMain()[i]) {
							jouerCarte(i, message.getCol());					
						}
					}
				}
				break;
				
			case JCC:
				// pour cape d'invisibilité
				break;
				
			case JCT:
				// pour echanger les cartes obj
				break;
			}
			
		}
	};

	public static CommunicationServeur comServ = new CommunicationServeur(myUDPCallback);

	public static ReponseMessageTCP getMyTCPCallback() {
		return myTCPCallback;
	}

	public static void setMyTCPCallback(ReponseMessageTCP myTCPCallback) {
		GestionnaireInterfacePP.myTCPCallback = myTCPCallback;
	}

	
	// fin reseau
	
//	private Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
	private Scene scene = new Scene(root, 1280, 720);
	
	public LinkedHashMap<String, Pane> InterfaceMap = new LinkedHashMap<String, Pane>();
	
//	public Properties texte; // text data
//	HashMap<String,String> texteLangue = new HashMap<String,String>(); // list of text files
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
		
//		texteLangue.put("français","texte_fr.properties");
//		texteLangue.put("english","texte_eng.properties");

//		texte = readPropertiesFile(PropertiesLocalisation+texteLangue.get("français")); // initialise le jeu en français
		
		Fin = new InterfaceFin(this);
		Plateau = new InterfacePlateau(this);
		
		InterfaceMap.put("Menu", new InterfaceMenu(this));
		InterfaceMap.put("Plateau", Plateau);
		InterfaceMap.put("finPartie", Fin);
		InterfaceMap.put("creationPartie", new InterfaceCreerPartie(this));
		
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
		
		afficherEcran(InterfaceMap.get("Menu"));// show menu
		
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
		GestionnaireInterfacePP.launch(args);
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
	
	public void doitJouer() throws Exception {
		if(data.getJoueurIntermediaire() > -1) {
			data.setJoueurInterfmediaire(-1);
		}
		if(!verifManche(data) && estFinie == false) {
	    	if(data.getJoueurs()[data.getCurrentJoueur()] instanceof Bot) {
	    		data.getJoueurs()[data.getCurrentJoueur()].jouer(data, 0, 0);
	    		rafraichir(this);
	    		doitJouer();
	    	}
	    	rafraichir(this);
    	}
    	else {
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
	    		estFinie = true;
	        	data.retournerCarte();
	        	rafraichir(this);
	        	data.finDeManche();
	        	rafraichir(this);
	        	Fin.afficherStats(data);
    			this.afficherEcran(InterfaceMap.get("finPartie"));
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
	
    public void rafraichir(GestionnaireInterfacePP GI) { // Rafraichissement de l'écran courant
	    	for(int i=0; i<GI.getData().getPlateau().getColonnes().length; i++) {
	    		for(int j=0; j<GI.getData().getPlateau().getColonnes()[i].getCartesInfluences().length; j++) {
	    			
	    			Plateau.drawPartie(GI);
	    			
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
    
    public void rafraichirPlateau(GestionnaireInterfacePP GI) { // Rafraichissement de l'écran courant
    	for(int i=0; i<GI.getData().getPlateau().getColonnes().length; i++) {
    		for(int j=0; j<GI.getData().getPlateau().getColonnes()[i].getCartesInfluences().length; j++) {
    			Plateau.drawPartie(GI);
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
    	GestionnaireInterfacePP.data = data;
    }
    
	public static void jouerCarte(int indexMain, int indexColonne) {
		try {
			data.jouerCarte(indexMain, indexColonne);
			for(int i = 0; i<sj.size(); i++) {
				comServ.informerJoueursCarteJouee(sj.get(i), data.getJoueurs()[data.getCurrentJoueur()].getCouleur(),
													indexColonne, data.getJoueurs()[data.getCurrentJoueur()].getMain()[indexMain], idPartie, currentManche);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initPartie() {
		data.setReserveJoueur();
	}
	


	public static void ajouterJoueur(Socket socket, String nomj, String type) {
		switch(type) {
		case "JR":
			if(nombreJoueurReel > nombreJoueurReelConnecte) {
				sj.add(socket);
				idJoueurs.add(nomj);
				Color c = data.getCouleurRestante();
				if(c == null) {
					try {
						comServ.refuserDansLaPartie(socket, idPartie);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					data.addJoueur(new Joueur(c, nomj));
					try {
						comServ.accepterDansLaPartie(socket, idPartie, nomj);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					comServ.annoncerMiseAJourPartie(idPartie, ipGroupe, portGroupe, idPartie, nombreJoueurVirtuel+nombreJoueurVirtuel,
													nombreJoueurReel, nombreJoueurVirtuel, nombreJoueurReelConnecte, nombreJoueurVirtuelConnecte, "ATTENTE");
					
				}
			}
			break;
		case "BOT":
			if(nombreJoueurVirtuel > nombreJoueurVirtuelConnecte) {
				sj.add(socket);
				idJoueurs.add(nomj);
				Color c = data.getCouleurRestante();
				if(c == null) {
					try {
						comServ.refuserDansLaPartie(socket, idPartie);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					data.addJoueur(new Bot("facile",c, nomj));
					try {
						comServ.accepterDansLaPartie(socket, idPartie, nomj);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					comServ.annoncerMiseAJourPartie(idPartie, ipGroupe, portGroupe, idPartie, nombreJoueurVirtuel+nombreJoueurVirtuel,
													nombreJoueurReel, nombreJoueurVirtuel, nombreJoueurReelConnecte, nombreJoueurVirtuelConnecte, "ATTENTE");
					
				}
			}
		}
	}
	
	public static void lancerPartie() {
		data.initPartie();
	}
    /**
     * Cette méthode permet de charger les fichiers textuels .properties
     * 
     * @param data Données actuelles du jeu
     */
//    public static Properties readPropertiesFile(String fileName) throws IOException {
//	      FileInputStream fis = null;
//	      Properties prop = null;
//	      try {
//	         fis = new FileInputStream(fileName);
//	         prop = new Properties();
//	         prop.load(fis);
//	      } catch(FileNotFoundException fnfe) {
//	         fnfe.printStackTrace();
//	      } catch(IOException ioe) {
//	         ioe.printStackTrace();
//	      } finally {
//	         fis.close();
//	      }
//	      return prop;
//	   }
}

