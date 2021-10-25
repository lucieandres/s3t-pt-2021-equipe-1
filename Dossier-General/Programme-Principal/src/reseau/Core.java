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

import CD.AppControler;



public class Core {
	

	private InetAddress group;
	private MulticastSocket socketServer = null;
	private long id=0;
	private boolean isConnected = false;
	private NetworkInterface prefNetInterface = null;
	private AppControler appC = null;
	
	public Core (long i, AppControler ac) {
		id = i;
		appC = ac;
	}
	
	
	public  void joinUDPMulticastGroup() {
		joinUDPMulticastGroup(appC.getIPGroup(), appC.getPortGroup());
	}
	

	public  void joinUDPMulticastGroup(String ip, int p) {
		if (socketServer != null) {
			socketServer.close();
		}
		try {
			socketServer = new MulticastSocket (appC.getPortGroup()); // socket de sortie (emission de message)
			if (prefNetInterface!=null)
				socketServer.setNetworkInterface(prefNetInterface);
			group = InetAddress.getByName(ip);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		Thread t = new Thread(new ServerUDPThread(this, appC, group, p, id));
	    t.start(); 
	}

	public void sendUDPMessage(String message) {
		byte[] msg=null;
		DatagramPacket packet=null;
		msg = (""+id+" -> "+message).getBytes(StandardCharsets.UTF_8);
		packet = new DatagramPacket(msg, msg.length, group, appC.getPortGroup());
		try {
			socketServer.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exit() {
		if (isConnected) {
			sendUDPMessage(appC.getLeaveMsg());
			socketServer.close();
		}
	}

	public void setConnected(boolean b) {
		isConnected = b;	
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
			appC.displayLog(true, "La multidiffusion : "+prefNetInterface.supportsMulticast()+"\n");
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
		sendUDPMessage(appC.getLeaveMsg());
		socketServer.close();
	}
}
