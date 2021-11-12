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

//CLASSE DE TEST D'ECHANGES RESEAUX ENTRE UN CLIENT ET UN SERVEUR, POUR EFFECTUER DES TESTS.

public class TestServeur {

	private static int tcpPort = 9001;

	private final static String ipGroup ="224.7.7.7";
	private final static int portGroup = 7777;
	private static ReceptionServeurTCP server = null;
	
	public static ReponseMessageUDP myUDPCallback = new ReponseMessageUDP() {
		@Override
		public void onMessage(Message message) {
			System.out.println("TestServer myUDPCallback(" + message + ")");
		}
	};

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
	    creerPartie.setStatus("ATTENTE");
	    udpC.sendUDPMessage(creerPartie.toString());		

	    // Creation du serveur TCP de la partie
		server = new ReceptionServeurTCP(tcpPort, myTCPCallback);
		Thread t = new Thread(server);
	    t.start(); 
	}
	
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
	    udpC.leaveUDPMulticastGroup();
	    udpC.setDisconnected();
		System.out.println("TestServer end");
	}
}
