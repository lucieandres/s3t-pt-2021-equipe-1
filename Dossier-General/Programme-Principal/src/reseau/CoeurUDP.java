package reseau;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * 
 * Cette classe permet de répertorier les méthodes qui serviront au processus UDP.
 * 
 * @author S3T-G1
 *
 */


public class CoeurUDP {
	
	private String ipGroup = null;
	private int portGroup = -1;
	private InetAddress group;
	private MulticastSocket socketServer = null;
	private boolean isConnected = false;
	/**
	 * 
	 * Constructeur permettant d'initialiser un CoeurUDP
	 * 
	 * @param ipGroup L'IP broadcast qui va référencer le serveur UDP.
	 * @param portGroup Le port qui référence le serveur.
	 * 
	 */
	
	public CoeurUDP (String ipGroup, int portGroup) {
		this.ipGroup = ipGroup;
		this.portGroup = portGroup;
	}

	
	/**
	 * 
	 * Méthode permettant d'indiquer qu'un serveur est connecté.
	 * 
	 */
	
	public void setConnected() {
		isConnected = true;
	}
	
	/**
	 * 
	 * Méthode permettant d'indiquer qu'un serveur est connecté.
	 * 
	 */

	public void setDisconnected() {
		isConnected = false;
	}
	
	
	/**
	 * 
	 * Méthode permettant de rejoindre un groupe UDP Multicast
	 * 
	 */
	
	public  void joinUDPMulticastGroup() {
		joinUDPMulticastGroup(ipGroup, portGroup, null);
	}
	
	/**
	 * 
	 * Méthode permettant de rejoindre un groupe UDP Multicast
	 * 
	 * @param callback la réponse UDP qui permettra d'indiquer que la connexion s'est bien effectué.
	 */
	
	public  void joinUDPMulticastGroup(ReponseMessageUDP callback) {
		joinUDPMulticastGroup(ipGroup, portGroup, callback);
	}
	
	/**
	 * 
	 * Méthode permettant de rejoindre un groupe UDP Multicast
	 * 
	 * @param ip L'IP du serveur UDP.
	 * @param p Le port qui référence le serveur.
	 * @param callback la réponse UDP qui permettra d'indiquer que la connexion s'est bien effectué.
	 */
	
	
	public  void joinUDPMulticastGroup(String ip, int p, ReponseMessageUDP callback) {
		if (isConnected) {
			setDisconnected();
			socketServer.close();
		}
		try {
			socketServer = new MulticastSocket (portGroup); // socket de sortie (emission de message)
			group = InetAddress.getByName(ip);
			setConnected();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		Thread t = new Thread(new ReceptionServeurUDP(this, group, p, callback));
	    t.start(); 
	}

	/**
	 * 
	 * Méthode permettant d'envoyer un message UDP.
	 * 
	 * @param message Le message UDP a envoyé.
	 */
	
	
	public void sendUDPMessage(String message) {
		byte[] msg=null;
		DatagramPacket packet=null;
		//msg = (""+id+" -> "+message).getBytes(StandardCharsets.UTF_8);
		msg = message.getBytes(StandardCharsets.UTF_8);
		packet = new DatagramPacket(msg, msg.length, group, portGroup);
		try {
			socketServer.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * Méthode permettant d'obtenir l'IP courante.
	 * 
	 * @return l'IP courante
	 */
	
	public String getCurrentIP() {
		String ip = "";
		try {
		ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
		e.printStackTrace();
		}
		return ip;
		}

	

	
	/**
	 * 
	 * Méthode permettant de quitter le groupe UDP Multicasst
	 * 
	 */
	
	public void exit() {
		if (isConnected) {
			setDisconnected();
			socketServer.close();
		}
	}
}