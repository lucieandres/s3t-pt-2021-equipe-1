package reseauOld;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import cartes.CarteInfluence;

//TRAITER ICI LES COMMUNICATIONS QUI PARTENT DES JOUEURS ET BOTS

/**
 * 
 * Cette classe permet de gérer l'envoi des messages réseaux dont l'envoyeur est le client
 * (soit un joueur réel, soit un joueur virtuel). Les envois de messages sont différents selon si la communication
 * se fait en UDP ou en TCP. Il est à noter que, dans le cas des méthodes de messages TCP, il n'y a pas besoin de passer 
 * une socket en paramètre, car, logiquement, le client connaîtra la socket qui lui est associé.
 * 
 * @author S3T-G1
 *
 */

public class CommunicationClient {


	private final static String ipGroupeUDP ="224.7.7.7";
	private final static int portGroupeUDP = 7777;
	private CoeurUDP coeurUDP = null;
	private static Socket socket = null;
	
	/**
	 * 
	 * Constructeur de la classe permettant d'instancier une communication client.
	 * 
	 * @param reponseUDP une réponse message UDP, correspondant à une instance d'un client qui implémente l'interface ReponseMessageUDP.
	 */
	
	public CommunicationClient(ReponseMessageUDP reponseUDP) {
		coeurUDP = new CoeurUDP(ipGroupeUDP, portGroupeUDP);
		coeurUDP.joinUDPMulticastGroup(reponseUDP); //Crée la socket UDP.
	}
	
	/**
	 * 
	 * Méthode permettant de rejoindre un serveur TCP.
	 * 
	 * @param ipServeur l'IP du serveur TCP que l'on souhaite rejoindre
	 * @param portPartie le port de la partie qui permettra de communiquer avec le serveur TCP.
	 * @param reponseTCP une réponse message TCP, correspondant à une instance d'un client qui implémente l'interface ReponseMessageTCP.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	public static void rejoindreServeurTCP(String ipServeur, int portPartie, ReponseMessageTCP reponseTCP) throws IOException {
		InetAddress ip = null ;

		// Ouverture de la connexion au serveur
		ip = InetAddress.getByName(ipServeur);
		socket = new Socket(ip, portPartie);
		socket.setTcpNoDelay(true);
		SocketServeurTCP serveur = new SocketServeurTCP(socket, reponseTCP);
		Thread t = new Thread(serveur);
	    t.start(); 
	}
	
	
	
	/**
	 * 
	 * Méthode permettant de rechercher une partie, en utilisant le message RUP.
	 * La communication se fera en UDP.
	 * 
	 * @param typeDePartie le type de partie recherchée
	 * @param tailleDeLaPartie nombre maximum de joueurs dans la partie recherchée
	 */
	
	//MESSAGE RUP (UDP) 
	public void rechercherUnePartie(String typeDePartie, int tailleDeLaPartie  ) {
		Message message = new Message(TypeDeMessage.RUP);
	    message.setTypep(typeDePartie);
	    message.setTaillep(tailleDeLaPartie);
	    coeurUDP.sendUDPMessage(message.toString());
	}

	
	/**
	 * 
	 * Méthode permettant de rejoindre une partie, en utilisant le message DCP.
	 * La communication se fera en TCP.
	 * 
	 * @param nomJoueur le nom du joueur.
	 * @param typeJoueur le type du joueur soit « JR » soit « BOT ».
	 * @param idPartie l’identifiant unique de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE DCP (TCP)
	public void rejoindrePartie(String nomJoueur, String typeJoueur, String idPartie) throws IOException {
		
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
				
				
		Message message = new Message(TypeDeMessage.DCP);
		message.setNomj(nomJoueur);
		message.setTypej(typeJoueur);
		message.setIdp(idPartie);
		ecriture.println(message.toString());	
	}
	
	
	
	/**
	 * 
	 * Méthode permettant de jouer une carte influence, en utilisant le message JCI.
	 * La communication se fera en UDP.
	 * @param CarteInfluence Carte choisie par le joueur
	 * @param Colonne colonne ou est jouée la carte
	 * @param idPartie identifiant de la partie
	 * @param NumeroManche identifiant de la manche courante
	 * @param idJoueur identifiant du joueur
	 */
	
	//MESSAGE JCI (UDP) 
	public void JouerCarteInfluence(CarteInfluence CarteInfluence, int Colonne, String idPartie, int NumeroManche, String idJoueur  ) {
		Message message = new Message(TypeDeMessage.JCI);
	    message.setCi(CarteInfluence);
	    message.setCo(Colonne);
	    message.setIdp(idPartie);
	    message.setNm(NumeroManche);
	    message.setIdj(idJoueur);
	
	    coeurUDP.sendUDPMessage(message.toString());
	}
	
	
	
	
	
	/**
	 * 
	 * Méthode permettant au joueur courant de choisir la carte qu’il souhaite jouer sous la carte cape d’invisibilité, en utilisant le message JCC.
	 * La communication se fera en TCP.
	 * 
	 * @param carteChoisie la carte choisi par le joueur ou « NUL » s’il ne choisit aucune carte. La carte doit obligatoirement être une carte de la main du joueur.
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante.
	 * @param idJoueur un identifiant unique caractérisant le joueur.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	

	
	//MESSAGE JCC (TCP)
	public void jouerCarteInfluenceCachee(CarteInfluence carteChoisie, String idPartie, int numeroManche, String idJoueur) throws IOException {
		
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
				
				
		Message message = new Message(TypeDeMessage.JCC);
		message.setCi(carteChoisie);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		message.setIdj(idJoueur);
		ecriture.println(message.toString());	
	}

	/**
	 * 
	 * Méthode permettant au joueur courant de choisir la colonne avec laquelle il invere la carte objectif, en utilisant le message RMC.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param numeroColonne le numéro (entre 1 et 6) de la colonne « objectif » de la carte retournée. 
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois.
	 * @param idJoueur un identifiant unique caractérisant le joueur.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	
	
	
	//MESSAGE JCT (TCP)
	public void indiquerColonneInverseCarteObjectif(int numeroColonne, String idPartie, int numeroManche, String idJoeur) throws IOException {
		
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.JCT);
		message.setCo(numeroColonne);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		message.setIdj(idJoeur);

		ecriture.println(message.toString());	
		
	}

}
