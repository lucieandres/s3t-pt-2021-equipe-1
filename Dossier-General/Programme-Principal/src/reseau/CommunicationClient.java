package reseau;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

//TRAITER ICI LES COMMUNICATIONS QUI PARTENT DES JOUEURS ET BOTS
public class CommunicationClient {

	private final static String ipGroup ="224.7.7.7";
	private final static int portGroup = 7777;
	private CoeurUDP udpCore = null;
	private static Socket socket = null;
	
	public CommunicationClient(ReponseMessageUDP udpCallback) {
		udpCore = new CoeurUDP(ipGroup, portGroup);
		udpCore.joinUDPMulticastGroup(udpCallback); //Crée la socket UDP.
	}
	
	public static void rejoindreServeurTCP(String ipServeur, int portServeur, ReponseMessageTCP tcpCallback) throws IOException {
		InetAddress ip = null ;

		// Ouverture de la connexion au serveur
		ip = InetAddress.getByName(ipServeur);
		socket = new Socket(ip, portServeur);
		socket.setTcpNoDelay(true);
		SocketServeurTCP serveur = new SocketServeurTCP(socket, tcpCallback);
		Thread t = new Thread(serveur);
	    t.start(); 
	}
	
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
