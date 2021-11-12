package reseau.test;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import reseau.Message;
import reseau.TypeDeMessage;
import reseau.ReponseMessageTCP;
import reseau.SocketServeurTCP;
import reseau.CoeurUDP;
import reseau.ReponseMessageUDP;

/**
 * 
 * Classe de test d'échanges réseaux entre un client et un serveur, côté client.
 * 
 * @author S3T-G1
 *
 */


public class TestClient {

	
	private final static String ipGroup ="224.7.7.7";
	private final static int portGroup = 7777;
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
				try {
					SocketServeurTCP client = rejoindrePartie(serverName, serverPort);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};

	public static ReponseMessageTCP myTCPCallback = new ReponseMessageTCP() {
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
	
	/**
	 * 
	 * Classe permettant de tester la connexion a une partie.
	 * 
	 * @param serverName Nom du serveur
	 * @param serverPort Port du serveur
	 * @return client
	 * @throws IOException return exception
	 */
	
	public static SocketServeurTCP rejoindrePartie(String serverName, int serverPort) throws IOException {
		System.out.println("TestClient rejoindrePartie(" + serverName + ", " + serverPort + ")");
		InetAddress ip = null ;
		Socket socket = null;

		// Ouverture de la connection au serveur
		ip = InetAddress.getByName(serverName);
		socket = new Socket(ip, serverPort);
		socket.setTcpNoDelay(true);
		SocketServeurTCP client = new SocketServeurTCP(socket, myTCPCallback);
		Thread t = new Thread(client);
	    t.start(); 
		
		// Envoi d'un message au serveur
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		
		// Envoi message Rejoindre une partie
		Message message = new Message(TypeDeMessage.DCP);
		message.setNomj("J1");
		message.setTypej("BOT");
		message.setIdp("P1");
		writer.println(message.toString());	
		
		return client;
	}
	
	/**
	 * 
	 * Méthode main.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		System.out.println("TestClient start");
		CoeurUDP udpC = new CoeurUDP(ipGroup, portGroup);
		udpC.joinUDPMulticastGroup(myUDPCallback);

		try {
			
			Thread.sleep(3600*1000);
			//client.exit();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("TestClient end");
	}

}