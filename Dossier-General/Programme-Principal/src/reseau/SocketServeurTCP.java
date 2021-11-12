package reseau;

import java.io.IOException;

/**
 * 
 * 
 * Cette classe permet de gérer les socket qui seront crées par le serveur TCP pour communiquer
 * avec les clients.
 * 
 */

import java.io.InputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class SocketServeurTCP implements Runnable {
	
	private Socket socket;
	private boolean isConnected = false;
	private ReponseMessageTCP callback = null;

	/**
	 * Constructeur permettant d'initialiser un SocketServeurTCP
	 * 
	 * @param s Socket associée
	 * @param callback Message de bon fonctionnement
	 */
	
	
	public SocketServeurTCP(Socket s, ReponseMessageTCP callback) {
		socket = s;
		this.callback = callback;
		isConnected = true;
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
		displayLog("TCPSocket.run() start");
		launchClient();
		displayLog("TCPSocket.run() end");
	}
	
	/**
	 * 
	 * Méthode de lancement du serveur
	 * 
	 * 
	 */
	
	
	public void launchClient() {

        Scanner scanner = null;
        try {
	        InputStream inputToServer = socket.getInputStream();
	        scanner = new Scanner(inputToServer, "UTF-8");
			
			//displayLog("Attente d'un message tcp ...\n");
		    while(isConnected){
		        String msg=scanner.nextLine();
		        
 		        String[] msgs = msg.split("\\|");
		        if (msgs != null && msgs.length > 0 ) {
		        	
		        	for (int i=0; i<msgs.length;i++) {
		        		try {
		        			Message message = new Message(msgs[i]);
		        			//displayLog("Reception du message "+message+"\n");
		        			
		        			// Appel du PP avec le message.
		        			callback.onMessage(socket, message);
		        		} catch (ExceptionMessage e) {
		        			displayLog("Reception du message en exception "+e+"\n");
		        		}
		        	}
		        } else {
		        	displayLog("ERREUR : Message incomplet (" + msg + ")");
		        }
		    }
		} catch (NoSuchElementException e) {
			// On ignore, cnx fermee
		} catch (IOException e) {
			displayLog(e.getMessage());
		}finally {
		    try {
		    	if (scanner != null) scanner.close();
				socket.close();
			} catch (IOException e) {
				displayLog(e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * Méthode permettant à la socket courante de se déconnecter.
	 * 
	 */
	
	public void exit() {
		setDisconnected();
	}
}