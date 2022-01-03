package reseauOld;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * 
 * Cette classe permet de gérer les écoutes/réceptions sur un serveur TCP.
 * 
 * @author S3T-G1
 *
 */


public class ReceptionServeurTCP implements Runnable {
	
	private int port = -1;
	private ServerSocket socketServer = null;
	private boolean isConnected = false;
	private ReponseMessageTCP callback = null;
	private SocketServeurTCP socketThread = null;
	
	
	/**
	 * 
	 * Constructeur permettant d'initialiser un ReceptionServeurTCP
	 * 
	 * @param portGroup Port du serveur.
	 * @param callback Message de bonne reception.
	 */
	
	public ReceptionServeurTCP (int portGroup, ReponseMessageTCP callback) {
		this.port = portGroup;
		this.callback = callback;
	}
	
	
	/**
	 * 
	 * Méthode permettant d'écrire des messages sur la sortie standard. 
	 * Reviens à faire un simple System.out.println.
	 * 
	 * @param msg Le message à écrire sur la sortie standard.
	 */
	
	public void displayLog(String msg) {
		System.out.println(msg);
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
	 * Méthode permettant d'indiquer qu'un serveur est déconnecté.
	 * 
	 */
	
	
	public void setDisconnected() {
		isConnected = false;
	}
	
	
	/**
	 * 
	 * Méthode de lancement de la classe.
	 * 
	 */
	
	@Override
	public void run() {
		displayLog("TCPServer.run() start");
		launchServer(port);
		displayLog("TCPServer.run() end");
	}
	
	
	/**
	 * 
	 * Méthode de lancement du serveur
	 * 
	 * @param p Port du serveur.
	 */
	
	public  void launchServer(int p) {
		if (isConnected) {
			setDisconnected();
			try {
				socketServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			socketServer = new ServerSocket(port);
			setConnected();
			
			while (isConnected) {
				Socket connectionSocket = socketServer.accept();
				connectionSocket.setTcpNoDelay(true);
				
				//TODO Garder la liste de tous les threads cr��s pour sortie propre
				socketThread = new SocketServeurTCP(connectionSocket, callback);
				Thread t = new Thread(socketThread);
			    t.start(); 
			}
		} catch (SocketException e) {
			// Interrupted function call: accept failed
			// Ignore car du a appel exit 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	/**
	 * 
	 * Méthode permettant de quitter le serveur TCP
	 * 
	 */
	
	public void exit() {
		//TODO exit de tous les threads TCPSocketThread
		if (socketThread != null)
			socketThread.exit();
		
		setDisconnected();
		if (socketServer != null) {
			try {
				socketServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
