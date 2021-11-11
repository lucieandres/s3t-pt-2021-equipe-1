package reseau;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;

import UI.UDPControler;



public class UDPCore {
	
	private String ipGroup = null;
	private int portGroup = -1;
	private InetAddress group;
	private MulticastSocket socketServer = null;
	private boolean isConnected = false;
	private NetworkInterface prefNetInterface = null;
	
	// used only for displayLog, setConnected and setDisconnected
	private UDPControler udpC = null;
	
	public UDPCore (UDPControler uc, String ipGroup, int portGroup) {
		udpC = uc;
		this.ipGroup = ipGroup;
		this.portGroup = portGroup;
	}
	public UDPCore (String ipGroup, int portGroup) {
		this.ipGroup = ipGroup;
		this.portGroup = portGroup;
	}
	
	// Fonctions d'interface avec le GUI
	public void displayLog(boolean b, String s) {
		if (udpC == null)
			System.out.println(s);
		else
			udpC.displayLog(b, s);
	}

	public void displayLog(String msg) {
		if (udpC == null)
			System.out.println(msg);
		else
		udpC.displayLog(msg);
	}
	
	public void setConnected() {
		isConnected = true;
		if (udpC != null) udpC.setConnected();
	}

	public void setDisconnected() {
		isConnected = false;
		if (udpC != null) udpC.setDisconnected();
	}
	
	public  void joinUDPMulticastGroup() {
		joinUDPMulticastGroup(ipGroup, portGroup, null);
	}
	
	public  void joinUDPMulticastGroup(UDPMessageCallback callback) {
		joinUDPMulticastGroup(ipGroup, portGroup, callback);
	}
	
	public  void joinUDPMulticastGroup(String ip, int p, UDPMessageCallback callback) {
		if (isConnected) {
			setDisconnected();
			socketServer.close();
		}
		try {
			socketServer = new MulticastSocket (portGroup); // socket de sortie (emission de message)
			if (prefNetInterface!=null)
				socketServer.setNetworkInterface(prefNetInterface);
			group = InetAddress.getByName(ip);
			setConnected();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		Thread t = new Thread(new UDPServerThread(this, group, p, callback));
	    t.start(); 
	}

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

	public void exit() {
		if (isConnected) {
			setDisconnected();
			socketServer.close();
		}
	}

	public ArrayList<String> getUpNetworkInterafes() throws SocketException {
		ArrayList<String> interfaceNames = new ArrayList<String>();
		Enumeration<NetworkInterface> enumNetI=null;
		try {
			enumNetI = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		while (enumNetI!=null && enumNetI.hasMoreElements()) {
		    NetworkInterface networkInterface = enumNetI.nextElement();
		    if (networkInterface.isUp())
		    	interfaceNames.add(networkInterface.getDisplayName());
		}
		return interfaceNames;
	}
	
	public void setPreferredNetworkInterafe(String name) {	
		Enumeration<NetworkInterface> enumNetI=null;
		try {
			enumNetI = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		while (enumNetI!=null && enumNetI.hasMoreElements()) {
		    NetworkInterface networkInterface = enumNetI.nextElement();
		    if (networkInterface.getDisplayName().equals(name)) {
		    	prefNetInterface = networkInterface;
		    }	
		}
		try {
			displayLog(true, "La multidiffusion : "+prefNetInterface.supportsMulticast()+"\n");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public String getCurrentIP() {
		if (prefNetInterface==null)
			try {
				return InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		return prefNetInterface.getInetAddresses().nextElement().getHostAddress();
	}
	
	public NetworkInterface getPrefNetInterface() {
		return prefNetInterface;
	}

	public void leaveUDPMulticastGroup() {
		socketServer.close();
		setDisconnected();
	}
}
