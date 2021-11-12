package reseau;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;




public class CoeurUDP {
	
	private String ipGroup = null;
	private int portGroup = -1;
	private InetAddress group;
	private MulticastSocket socketServer = null;
	private boolean isConnected = false;
	private NetworkInterface prefNetInterface = null;
	
	public CoeurUDP (String ipGroup, int portGroup) {
		this.ipGroup = ipGroup;
		this.portGroup = portGroup;
	}

	public void setConnected() {
		isConnected = true;
	}

	public void setDisconnected() {
		isConnected = false;
	}
	
	
	public  void joinUDPMulticastGroup() {
		joinUDPMulticastGroup(ipGroup, portGroup, null);
	}
	
	
	public  void joinUDPMulticastGroup(ReponseMessageUDP callback) {
		joinUDPMulticastGroup(ipGroup, portGroup, callback);
	}
	
	public  void joinUDPMulticastGroup(String ip, int p, ReponseMessageUDP callback) {
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
	
	public void exit() {
		if (isConnected) {
			setDisconnected();
			socketServer.close();
		}
	}
}