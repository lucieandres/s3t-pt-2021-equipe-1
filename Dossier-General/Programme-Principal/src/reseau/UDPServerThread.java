package reseau;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class UDPServerThread implements Runnable {
	
	private int portGroup;
	private InetAddress group;
	private UDPCore udpCore;
	private MulticastSocket socket;
	private UDPMessageCallback callback = null;

	public UDPServerThread(UDPCore c, InetAddress g, int p, UDPMessageCallback callback) {
		portGroup = p;
		group = g;
		udpCore = c;
		this.callback = callback;
	}

	@Override
	public void run() {
		byte[] buffer=new byte[1024];

		boolean leave=false;
		try {
			socket = new MulticastSocket(portGroup); //socket de reception
			if (udpCore.getPrefNetInterface()!=null)
				socket.setNetworkInterface(udpCore.getPrefNetInterface());
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
		        			
		        		} catch (MessageException e) {
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