package reseau;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class TCPSocketThread implements Runnable {
	
	private Socket socket;
	private boolean isConnected = false;
	private TCPMessageCallback callback = null;

	public TCPSocketThread(Socket s, TCPMessageCallback callback) {
		socket = s;
		this.callback = callback;
		isConnected = true;
	}

	// Fonctions d'interface avec le GUI
	public void displayLog(String msg) {
		System.out.println(msg);
	}	
	public void setConnected() {
		isConnected = true;
	}
	public void setDisconnected() {
		isConnected = false;
	}
	
	@Override
	public void run() {
		displayLog("TCPSocket.run() start");
		launchClient();
		displayLog("TCPSocket.run() end");
	}
	
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
		        		} catch (MessageException e) {
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
	
	public void exit() {
		setDisconnected();
	}
}