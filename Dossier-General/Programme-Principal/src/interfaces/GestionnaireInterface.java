package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import bot.Bot;
import cartes.CarteInfluence;
import elements.Colonne;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import moteur.Data;
import pp.InterfacePlateau;
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
public class GestionnaireInterface extends Application {
	
	public Stage MainStage;
	public Group root = new Group();
	private Node ecranCourant = null;
	private boolean estFinie = false;
	protected static Data data;
	public InterfaceJeu Jeux = null; // must be done to pass data from creerPartie to Jeu
	public InterfaceFin Fin = null;
	public InterfacePlateau Plateau = null;
	public MediaPlayer musique;
	public GestionnaireInterface self = this;
	
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
	//CommunicationClient com = new CommunicationClient(myUDPCallback);
	

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
	
	public LinkedHashMap<String, Pane> InterfaceMap = new LinkedHashMap<String, Pane>(); // store interface as Pane
	public ArrayList<InterfaceBase> InterfaceBaseList = new ArrayList<InterfaceBase>(); // store interfaces as InterfaceBase
	
//	public Properties texte; // text data
	HashMap<String,Properties> texteLangue = new HashMap<String,Properties>(); // list of text files
	String langueSelectionne;
	
	HashMap<String,Image> Cartes = new HashMap<String,Image>();
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
		
		Cartes = ChargeCartes();
		//System.out.println(Cartes.get("agricole_1").toString());
		
		texteLangue.put("francais",readPropertiesFile("/textes/texte_fr.properties"));
		texteLangue.put("english",readPropertiesFile("/textes/texte_eng.properties"));
		langueSelectionne = "francais";

		//Properties texte = readPropertiesFile("/textes/texte_fr.properties"); // initialise
		//System.out.println(texte.getProperty("texte.titre"));
		//fichierTexte.getProperty(element.getValue())
		//System.out.println(texte.getProperty("bouton.regle"));
		
		
		InterfaceBase IMenu = new InterfaceMenu(this);
		InterfaceBase IParametres =  new InterfaceParametres(this);
		InterfaceBase ILocalCreerPartie = new InterfaceLocalCreerPartie(this);
		InterfaceBase IRegles = new InterfaceRegles(this);
		InterfaceBase IRejoindrePartie = new InterfaceRejoindrePartie(this);
		InterfaceBase IChargement = new InterfaceChargement(this);
		Jeux = new InterfaceJeu(this);
		Fin = new InterfaceFin(this);
		
		InterfaceBaseList.add(IMenu);
		InterfaceBaseList.add(IParametres);
		InterfaceBaseList.add(ILocalCreerPartie);
		InterfaceBaseList.add(IRegles);
		InterfaceBaseList.add(IChargement);
		InterfaceBaseList.add(IRejoindrePartie);
		InterfaceBaseList.add(Jeux);
		InterfaceBaseList.add(Fin);
		
		InterfaceMap.put("menu", IMenu);
		InterfaceMap.put("parametres", IParametres);
		InterfaceMap.put("creerPartie", ILocalCreerPartie);
		InterfaceMap.put("jeu", Jeux );
		InterfaceMap.put("regles", IRegles);
		InterfaceMap.put("chargement", IChargement);
		InterfaceMap.put("rejoindre", IRejoindrePartie);
		InterfaceMap.put("finPartie", Fin);
		
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

		Media sound = new Media(new File("Dossier-General/Programme-Principal/src/interfaces/resources/Musique/Menu.mp3").toURI().toString());
		musique = new MediaPlayer(sound);
		musique.setCycleCount(MediaPlayer.INDEFINITE);
		musique.setVolume(1);
		musique.play();
		
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
	 * Cette méthode permet de lancer un son
	 * On l'utilisera surtout lorsque l'utilisateur chnagera d'écran
	 * 
	 * @since 1.0
	 */
	
    public void bruitInterface() {
    	Media son = new Media(new File("Dossier-General/Programme-Principal/src/interfaces/resources/Musique/Bruitage_lance.wav").toURI().toString());
		MediaPlayer bruit = new MediaPlayer(son);
		bruit.play();
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
	    		class TaskDelay extends TimerTask { // Timer pour voir la fin de la manche
					public void run() {
						
						Platform.runLater(() -> {
							try {
								data.getJoueurs()[data.getCurrentJoueur()].jouer(data, 0, 0);
					    		rafraichir(self);
					    		doitJouer();
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
					}
	    		}
	    		Timer T = new Timer();
	    		TimerTask tache = new TaskDelay();
	    		T.schedule(tache, 250); // ------------------------------------------------------------------ delai bot
	    		/*
	    		data.getJoueurs()[data.getCurrentJoueur()].jouer(data, 0, 0);
	    		rafraichir(this);
	    		doitJouer();*/
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
	        	data.calculScoreJoueurs();
	        	System.out.println(" ");
	        	
	        	/*
	        	data.finDeManche();
	        	data.calculScoreJoueurs();
	    		System.out.println(" ");
	        	rafraichir(this);
	    		estFinie = false;
	    		doitJouer();*/
	    		
	    		class TaskDelay extends TimerTask { // Timer pour voir la fin de la manche
					public void run() {
						
						Platform.runLater(() -> {
							try {
					        	rafraichir(self);
					    		estFinie = false;
					    		doitJouer();
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
					}
	    		}
	    		Timer T = new Timer();
	    		TimerTask tache = new TaskDelay();
	    		T.schedule(tache, 3000); // ------------------------------------------------------------------ delai fin de manche
    		}
    		else {
    			System.out.println("fin de la partie");
	    		estFinie = true;
	        	data.retournerCarte();
	        	rafraichir(this);
	        	data.finDeManche();
	        	//rafraichir(this);
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
     * @param filename l'emplacement de la ressource en fonction du classpath
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
    
    /**
     * Cette méthode permet de charger les fichiers .png
     * 
     * @param filename l'emplacement de la ressource en fonction du classpath
     */
    
    public static Image readPngFile(String fileName) {
    	  double coeff = 1;
	      InputStream fis = null;
	      Image img = null;
	      fis = GestionnaireInterface.class.getResourceAsStream(fileName);
	      img = new Image(fis);
	      img = new Image(GestionnaireInterface.class.getResourceAsStream(fileName),img.getWidth()/coeff,img.getHeight()/coeff,false,false);

	      try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	      return img;
	   }
    
    /**
     * Cette méthode permet de charger toutes les images du programme
     * 
     * 
     */
    
    public HashMap<String,Image> ChargeCartes() {
    	HashMap<String,Image> result = new HashMap<String,Image>();
    	
    	for(int i = 1;i<=5;i++) { // charge cartes objectif
    		result.put("Agriculture_"+i,readPngFile("/sprites/classique/carteObjectif/agricole_"+i+".png"));
    		result.put("Combat_"+i,readPngFile("/sprites/classique/carteObjectif/militaire_"+i+".png"));
    		result.put("Religion_"+i,readPngFile("/sprites/classique/carteObjectif/religion_"+i+".png"));
    		result.put("Musique_"+i,readPngFile("/sprites/classique/carteObjectif/culture_"+i+".png"));
    		result.put("Commerce_"+i,readPngFile("/sprites/classique/carteObjectif/economie_"+i+".png"));
    		result.put("Alchimie_"+i,readPngFile("/sprites/classique/carteObjectif/science_"+i+".png"));
    	}
    	
    	/*
    	for(int i = 1;i<=6;i++) { //charge le dos des cartes
    		result.put("back_"+i,readPngFile("/sprites/classique/carteInfluence/back_"+i+".png"));
    	}*/
    	//result.put("back_1",readPngFile("/sprites/classique/carteInfluence/back_1.png"));
    	result.put("back_0xffff00ff",readPngFile("/sprites/classique/carteInfluence/back_1.png"));
    	result.put("back_0x800080ff",readPngFile("/sprites/classique/carteInfluence/back_2.png"));
    	result.put("back_0xfaebd7ff",readPngFile("/sprites/classique/carteInfluence/back_3.png"));
    	result.put("back_0x006400ff",readPngFile("/sprites/classique/carteInfluence/back_4.png"));
    	result.put("back_0xff0000ff",readPngFile("/sprites/classique/carteInfluence/back_5.png"));
    	result.put("back_0x0000ffff",readPngFile("/sprites/classique/carteInfluence/back_6.png"));
    	
    	
    	
    	//charge les cartes influence
    	result.put("Alchimiste",readPngFile("/sprites/classique/carteInfluence/alchimiste.png"));
    	result.put("Assassin",readPngFile("/sprites/classique/carteInfluence/assasin.png"));
    	result.put("Cape d’invisibilité",readPngFile("/sprites/classique/carteInfluence/cape_d'invisibilite.png"));
    	result.put("Cardinal",readPngFile("/sprites/classique/carteInfluence/cardinal.png"));
    	result.put("Dragon",readPngFile("/sprites/classique/carteInfluence/dragon.png"));
    	result.put("Ecuyer",readPngFile("/sprites/classique/carteInfluence/ecuyer.png"));
    	result.put("Ermite",readPngFile("/sprites/classique/carteInfluence/ermite.png"));
    	result.put("Explorateur",readPngFile("/sprites/classique/carteInfluence/explorateur.png"));
    	result.put("Juliette",readPngFile("/sprites/classique/carteInfluence/juliette.png"));
    	result.put("Magicien",readPngFile("/sprites/classique/carteInfluence/magicien.png"));
    	result.put("Maître d’armes",readPngFile("/sprites/classique/carteInfluence/maitre_d'armes.png"));
    	result.put("Marchand",readPngFile("/sprites/classique/carteInfluence/marchand.png"));
    	result.put("Mendiant",readPngFile("/sprites/classique/carteInfluence/mendiant.png"));
    	result.put("Petit Géant",readPngFile("/sprites/classique/carteInfluence/petit_geant.png"));
    	result.put("Prince",readPngFile("/sprites/classique/carteInfluence/prince.png"));
    	result.put("Reine",readPngFile("/sprites/classique/carteInfluence/reine.png"));
    	result.put("Roi",readPngFile("/sprites/classique/carteInfluence/roi.png"));
    	result.put("Roméo",readPngFile("/sprites/classique/carteInfluence/romeo.png"));
    	result.put("Seigneur",readPngFile("/sprites/classique/carteInfluence/seigneur.png"));
    	result.put("Sorcière",readPngFile("/sprites/classique/carteInfluence/sorciere.png"));
    	result.put("Sosie",readPngFile("/sprites/classique/carteInfluence/sosie.png"));
    	result.put("Tempête",readPngFile("/sprites/classique/carteInfluence/tempete.png"));
    	result.put("Traitre",readPngFile("/sprites/classique/carteInfluence/traitre.png"));
    	result.put("Trois Mousquetaires",readPngFile("/sprites/classique/carteInfluence/trois_mousquetaires.png"));
    	result.put("Troubadour",readPngFile("/sprites/classique/carteInfluence/troubadour.png"));
    	
    	return result;
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

