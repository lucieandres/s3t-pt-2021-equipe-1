package reseau;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javafx.scene.paint.Color;
import cartes.CarteObjectif;
//TRAITER ICI LES COMMUNICATIONS QUI PARTENT DU PP
//GROUPE MULTICAST = MESSAGE UDP SINON MESSAGE TCP
//PASSER SOCKET EN PREMIER PARAMETRE DES MESSAGES TCP

/**
 * 
 * Cette classe permet de gérer l'envoi des messages réseaux dont l'envoyeur est le serveur
 * (le programme principal). Les envois de messages sont différents selon si la communication
 * se fait en UDP ou en TCP. Il est à noter que, dans le cas des méthodes de messages TCP, il y a besoin de passer 
 * une socket en paramètre, car, logiquement, le serveur ne connaîtra pas la socket qui est associé au client avec lequel il souhaite communiquer.
 * 
 * @author S3T-G1
 *
 */


public class CommunicationServeur {

	private final static String ipGroup ="224.7.7.7";
	private final static int portGroup = 7777;
	private CoeurUDP udpCore = null;
	private static ReceptionServeurTCP serveur = null;
	//private HashMap<String,Socket> socketsJoueurs // a gérer au sein des clients et du PP.
	
	/**
	 * 
	 * Constructeur de la classe permettant d'instancier une communication serveur.
	 * 
	 * @param udpCallback une réponse message UDP.
	 */
	
	public CommunicationServeur(ReponseMessageUDP udpCallback) {
		udpCore = new CoeurUDP(ipGroup, portGroup);
		udpCore.joinUDPMulticastGroup(udpCallback); //Crée la socket UDP.
	}
	
	/**
	 * 
	 * Méthode permettant d'initialiser un serveur TCP
	 * 
	 * @param port le port du serveur TCP.
	 * @param tcpCallback une réponse TCP.
	 * @return une réception de serveur TCP.
	 */

	public ReceptionServeurTCP initServeurTCP(int port, ReponseMessageTCP tcpCallback) {
		serveur = new ReceptionServeurTCP(port, tcpCallback);
		Thread t = new Thread(serveur);
	    t.start();
	    return serveur;
	}
	
	/**
	 * 
	 * Méthode permettant d'annoncer la création d'une partie, en utilisant le message ACP.
	 * La communication se fera en UDP.
	 * 
	 * @param idPartie identifiant unique de partie. La lettre P suivie d’un entier entre 0 et 9 999 999 (exemple P258456).
	 * @param ip l’IP pour rejoindre la partie sous la forme xxx.xxx.xxx.xxx (où xxx pourra être sur 1, 2 ou 3 digits en fonction de la valeur).
	 * @param port le port pour rejoindre la partie sous la forme d’un entier entre 1024 et 65535.
	 * @param nomPartie le nom de la partie, sous la forme d’une chaine de caractères pouvant contenir des lettres majuscules et minuscule (accentuées ou non), des nombres et les caractères spéciaux apostrophe «’», espace « » et souligné bas «_».
	 * @param nombreJoueursVoulu Nombre de joueurs souhaités sur la partie.
	 * @param nombreJoueurs  Nombre de joueurs réels maximum sur la partie (ne peut pas être supérieur à NBJ).
	 * @param nombreBots  Nombre de joueurs virtuels (BOT) maximum sur la partie (ne peut pas être supérieur à NBJ).
	 * @param statut le statut de la partie ("ATTENTE").
	 */
	
	//MESSAGE ACP (UDP) 
	public void annoncerCreationPartie(String idPartie, String ip, int port, String nomPartie, int nombreJoueursVoulu, int nombreJoueurs, int nombreBots, String statut ) {
		Message message = new Message(TypeDeMessage.ACP);
	    message.setIdp(idPartie);
	    message.setIp(ip);
	    message.setPort(port);
	    message.setNom(nomPartie);
	    message.setNbj(nombreJoueursVoulu);
	    message.setNbjrm(nombreJoueurs);
	    message.setNbjvm(nombreBots);
	    message.setStatut(statut);
	    udpCore.sendUDPMessage(message.toString());
	}
	
	/**
	 * 
	 * Méthode permettant d'initialiser une manche, en utilisant le message ILM.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param listeCarteObj la liste des cartes « objectif » de la manche séparées par des « , » et décrite selon le codage présenté précédemment. Elles sont fournies dans l’ordre des colonnes du plateau.
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois. 
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ILM (TCP) 
	public void initialiserManche(Socket socket, List<CarteObjectif> listeCarteObj, String idPartie, int numeroManche) throws IOException {
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		
		Message message = new Message(TypeDeMessage.ILM);
		message.setLobjectif(listeCarteObj);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant d'informer l'ensemble des gains d'objectif, en utilisant le message ROM.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param listeCarteObj la liste des cartes « objectif » de la manche séparées par des « , » et décrite selon le codage présenté précédemment. Elles sont fournies dans l’ordre des colonnes du plateau.
	 * @param listeCouleur la liste des couleurs de chaque joueur ayant remporté l’objectif dans le même ordre que la liste précédente. Chaque couleur est identifiée par le code couleur (3 caractères) des cartes « influence » et les couleurs sont séparées par des « , ».
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche  un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ROM (TCP)
	public void informerEnsembleGainsObjectifs(Socket socket, List<CarteObjectif> listeCarteObj, List<Color> listeCouleur,String idPartie, int numeroManche) throws IOException {
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		
		Message message = new Message(TypeDeMessage.ROM);
		message.setLobjectif(listeCarteObj);
		message.setListec(listeCouleur);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant d'indiquer la fin d'une partie, en utilisant le message FDP.
	 * La communication se fera en TCP. 
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param couleur indique la couleur du joueur gagnant t. La couleur est identifiée par le code couleur (3 caractères) des cartes « influence ».
	 * @param listeJoueur la liste des nom joueurs classées selon leur score (le gagnant en premier) et séparés par des « , ».
	 * @param listeScore la liste des score séparés par des « , » dans le même ordre que la liste des joueurs.
	 * @param idPartie
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE FDP (TCP)
	public void finDePartie(Socket socket, Color couleur, List<String> listeJoueur, List<Integer> listeScore,String idPartie) throws IOException {
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		
		Message message = new Message(TypeDeMessage.FDP);
	    message.setCouleur(couleur);
		message.setListej(listeJoueur);
		message.setListes(listeScore);
		message.setIdp(idPartie);
		
		
		writer.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant de terminer la partie, en utilisant le message TLP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE TLP (TCP)
	public void TerminerLaPartie(Socket socket, String idPartie) throws IOException {
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		
		Message message = new Message(TypeDeMessage.TLP);
		message.setIdp(idPartie);
		
		
		writer.println(message.toString());	
		
	}
	
	
}
	

