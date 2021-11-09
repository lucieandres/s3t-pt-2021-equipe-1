package reseau;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;



public class ServerUDPThread implements Runnable {
	
	private int portGroup;
	private InetAddress group;
	private long id=0;
	private AppControler appC;
	private Core core;
	private MulticastSocket socket;

	public ServerUDPThread(Core c, AppControler ac, InetAddress g, int p,long i) {
		portGroup = p;
		group = g;
		id = i;
		appC = ac;
		core = c;
	}

	@Override
	public void run() {
		byte[] buffer=new byte[1024];

		try {
			socket = new MulticastSocket(portGroup); //socket de reception
			if (core.getPrefNetInterface()!=null)
				socket.setNetworkInterface(core.getPrefNetInterface());
			socket.joinGroup(group);

			core.setConnected(true);
			appC.setConnected();
			
			appC.displayLog(true, "Attente d'un message Multicast ...\n");
		    while(true){
		        DatagramPacket packet=new DatagramPacket(buffer, buffer.length);
		        socket.receive(packet);
		        String msg=new String(packet.getData(),packet.getOffset(),packet.getLength(),StandardCharsets.UTF_8);
		        
		        //filtrage ou pas des messages locaux
		        if (!appC.isLocalMsgSkip() && msg.startsWith(""+id+" ->")) {
		        	appC.displayLog(true,"Reception du message de "+msg+"\n");
		        }else if (!msg.startsWith(""+id+" ->")) 
		        	appC.displayLog(false,"Reception du message de "+msg+"\n");
		        
		        //Filtrage des commandes QUIT ou TCP
		        if(msg.contains(""+id+" -> "+appC.getLeaveMsg())) {
		        	appC.displayLog(true, "Message de fin d'écoute détecté\n");
		            break;
		        }else if (msg.contains(appC.getTcpConnect())) {
		        	String ipTCP = appC.extractIP(msg);
		        	String portTCP = appC.extractPort(msg);
		        	appC.displayLog("   --->  IP = "+ipTCP+" : Port = "+portTCP);
		        }
		    }
		    socket.leaveGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		    socket.close();
		    core.setConnected(false);
		    appC.setDisconnected();
		}
	}
}
