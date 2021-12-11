package reseau;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

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


	private final static String ipGroup ="224.7.7.7";
	private final static int portGroup = 7777;
	private CoeurUDP udpCore = null;
	private static Socket socket = null;
	
	/**
	 * 
	 * Constructeur de la classe permettant d'instancier une communication client.
	 * 
	 * @param udpCallback une réponse message UDP.
	 */
	
	public CommunicationClient(ReponseMessageUDP udpCallback) {
		udpCore = new CoeurUDP(ipGroup, portGroup);
		udpCore.joinUDPMulticastGroup(udpCallback); //Crée la socket UDP.
	}
	
	/**
	 * 
	 * Méthode permettant de rejoindre un serveur TCP.
	 * 
	 * @param ipServeur l'IP du serveur TCP que l'on souhaite rejoindre
	 * @param portPartie le port de la partie qui permettra de communiquer avec le serveur TCP.
	 * @param tcpCallback une réponse message TCP.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	public static void rejoindreServeurTCP(String ipServeur, int portPartie, ReponseMessageTCP tcpCallback) throws IOException {
		InetAddress ip = null ;

		// Ouverture de la connexion au serveur
		ip = InetAddress.getByName(ipServeur);
		socket = new Socket(ip, portPartie);
		socket.setTcpNoDelay(true);
		SocketServeurTCP serveur = new SocketServeurTCP(socket, tcpCallback);
		Thread t = new Thread(serveur);
	    t.start(); 
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
		
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
				
				
		Message message = new Message(TypeDeMessage.DCP);
		message.setNomj(nomJoueur);
		message.setTypej(typeJoueur);
		message.setIdp(idPartie);
		writer.println(message.toString());	
	}
}
