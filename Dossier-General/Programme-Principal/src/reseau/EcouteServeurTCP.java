package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class EcouteServeurTCP implements Runnable {
	
	private int port = -1;
	private ServerSocket socketServer = null;
	private boolean isConnected = false;
	private ReponseMessageTCP callback = null;
	private SocketServeurTCP socketThread = null;
	
	public EcouteServeurTCP (int portGroup, ReponseMessageTCP callback) {
		this.port = portGroup;
		this.callback = callback;
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
		displayLog("TCPServer.run() start");
		launchServer(port);
		displayLog("TCPServer.run() end");
	}
	
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
