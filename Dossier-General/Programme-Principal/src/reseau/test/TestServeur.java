package reseau.test;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import reseau.Message;
import reseau.ExceptionMessage;
import reseau.TypeDeMessage;
import reseau.ReponseMessageTCP;
import reseau.ReceptionServeurTCP;
import reseau.CoeurUDP;
import reseau.ReponseMessageUDP;

/**
 * 
 * Classe de test d'échanges réseaux entre un client et un serveur, côté serveur.
 * 
 * @author S3T-G1
 *
 */


public class TestServeur {

	private static int tcpPort = 9001;

	private final static String ipGroup ="224.7.7.7";
	private final static int portGroup = 7777;
	private static ReceptionServeurTCP server = null;
	
	public static ReponseMessageUDP myUDPCallback = new ReponseMessageUDP() {
		@Override
		
		/**
		 * 
		 * Classe implémentée permettant de tester l'échange de messages UDP.
		 * 
		 */
		
		public void onMessage(Message message) {
			System.out.println("TestServer myUDPCallback(" + message + ")");
		}
	};

	/**
	 * 
	 * Méthode permettant de tester les réponses au message TCP.
	 * 
	 * 
	 */
	
	public static ReponseMessageTCP myTCPCallback = new ReponseMessageTCP() {
		@Override
		public void onMessage(Socket socket, Message message) {
			System.out.println("TestServer myTCPCallback(" + message + ")");

			if (message.getType() == TypeDeMessage.DCP) {
				// Demande rejoindre partie, on accepte
				Message reponse = new Message(TypeDeMessage.ADP);
				reponse.setIdp(message.getIdp());
				reponse.setIdj("J1");
				OutputStream output;
				try {
					output = socket.getOutputStream();
					PrintWriter writer = new PrintWriter(output, true);
					writer.println(reponse.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	
	/**
	 * 
	 *Méthode permettant de créer une partie 
	 *
	 * @param udpC CoeurUDP correspondant
	 * @throws ExceptionMessage Message d'exception
	 */
	
	public static void creerPartie(CoeurUDP udpC) throws ExceptionMessage {
		// Envoi du message UDP
	    Message creerPartie = new Message(TypeDeMessage.ACP);
	    creerPartie.setIdp("P205");
	    creerPartie.setIp("127.0.0.1");
	    creerPartie.setPort(tcpPort);
	    creerPartie.setNom("Clem");
	    creerPartie.setNbj(4);
	    creerPartie.setNbjrm(1);
	    creerPartie.setNbjvm(2);
	    creerPartie.setStatut("ATTENTE");
	    udpC.sendUDPMessage(creerPartie.toString());		

	    // Creation du serveur TCP de la partie
		server = new ReceptionServeurTCP(tcpPort, myTCPCallback);
		Thread t = new Thread(server);
	    t.start(); 
	}
	
	/**
	 * Méthode main.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		System.out.println("TestServer start");
		CoeurUDP udpC = new CoeurUDP(ipGroup, portGroup);
		udpC.joinUDPMulticastGroup(myUDPCallback);

		// On creee une partie apres 1s
	    try {
			Thread.sleep(1*1000);
		    creerPartie(udpC);
	    } catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExceptionMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    server.exit();
	    udpC.exit();
	    udpC.setDisconnected();
		System.out.println("TestServer end");
	}
}
