package reseau;

import java.io.IOException;

/**
 * 
 * Cette classe permet de gérer les écoutes/réceptions sur un serveur UDP.
 * 
 * @author S3T-G1
 *
 */

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class ReceptionServeurUDP implements Runnable {
	
	private int portGroup;
	private InetAddress group;
	private CoeurUDP udpCore;
	private MulticastSocket socket;
	private ReponseMessageUDP callback = null;
	
	/**
	 * 
	 * Constructeur permettant d'initialiser un ReceptionServeurUDP
	 * 
	 * @param c CoeurUDP correspondant
	 * @param g InetAddress correspondante
	 * @param p Port du serveur
	 * @param callback Message de bon fonctionnement.
	 */

	public ReceptionServeurUDP(CoeurUDP c, InetAddress g, int p, ReponseMessageUDP callback) {
		portGroup = p;
		group = g;
		udpCore = c;
		this.callback = callback;
	}

	/**
	 * 
	 * Méthode d'execution de la classe
	 * 
	 */
	
	
	@Override
	public void run() {
		byte[] buffer=new byte[1024];

		boolean leave=false;
		try {
			socket = new MulticastSocket(portGroup); //socket de reception
			socket.joinGroup(group);

			//udpCore.displayLog(true, "Attente d'un message Multicast ...\n");
		    while(!leave){
		        DatagramPacket packet=new DatagramPacket(buffer, buffer.length);
		        socket.receive(packet);
		        String msg=new String(packet.getData(),packet.getOffset(),packet.getLength(),StandardCharsets.UTF_8);
		        
 		        String[] msgs = msg.split("\\|");
		        if (msgs != null && msgs.length > 0 ) {
		        	
		        	for (int i=0; i<msgs.length;i++) {
		        		try {
		        			//appC.displayLog(false,"Reception du message "+msgs[msgs.length-1]+"\n");
		        			Message message = new Message(msgs[i]);
		        			
		        			//ex: ACP-P205-224.7.7.7-7777-Clem-4-1-2-ATTENTE|
		        			
		        			// Appel du PP avec le message.
		        			if (callback != null) 
		        				callback.onMessage(message);
		        			else
		        				System.out.println("Reception du message "+message+"\n");
		        			
		        			// A Gerer Leave
		        			
		        		} catch (ExceptionMessage e) {
		        			System.out.println("Reception du message en exception "+e+"\n");
		        		}			       		
		        	}
		        } else {
		        	System.out.println("ERREUR : Message incomplet (" + msg + ")");
		        }
		    }
		    socket.leaveGroup(group);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
		    socket.close();
		    udpCore.setDisconnected();
		}
	}
}