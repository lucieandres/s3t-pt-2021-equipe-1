package network.test;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import network.Message;
import network.TCPMessageCallback;
import network.MessageException;
import network.MessageType;
import network.TCPServerThread;
import network.UDPCore;
import network.UDPMessageCallback;

public class TestServer {

	private static int tcpPort = 9001;

	private final static String ipGroup ="224.7.7.7";
	private final static int portGroup = 7777;
	private static TCPServerThread server = null;
	
	public static UDPMessageCallback myUDPCallback = new UDPMessageCallback() {
		@Override
		public void onMessage(Message message) {
			System.out.println("TestServer myUDPCallback(" + message + ")");
		}
	};

	public static TCPMessageCallback myTCPCallback = new TCPMessageCallback() {
		@Override
		public void onMessage(Socket socket, Message message) {
			System.out.println("TestServer myTCPCallback(" + message + ")");

			if (message.getType() == MessageType.DCP) {
				// Demande rejoindre partie, on accepte
				Message reponse = new Message(MessageType.ADP);
				reponse.setIdp(message.getIdp());
				reponse.setIdj(message.getNomj());
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
	
	public static void creerPartie(UDPCore udpC) throws MessageException {
		// Envoi du message UDP
	    Message creerPartie = new Message(MessageType.ACP);
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
		server = new TCPServerThread(tcpPort, myTCPCallback);
		Thread t = new Thread(server);
	    t.start(); 
	}
	
	public static void main(String[] args) {
		System.out.println("TestServer start");
		UDPCore udpC = new UDPCore(ipGroup, portGroup);
		udpC.joinUDPMulticastGroup(myUDPCallback);

		// On creee une partie apres 1s
	    try {
			Thread.sleep(1*1000);
		    creerPartie(udpC);
	    } catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MessageException e) {
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
