package CD;

import java.net.SocketException;
import java.util.ArrayList;

import NF.Core;
import UI.GUI;
import javafx.application.Platform;


public class AppControler {
	
	private GUI gui = null;
	private long id=0;
	private Core c;
	private final String leaveMsg = "--QUIT--";
	private final String tcpConnect = "--TCP--";
	private final String ipGroup ="224.7.7.7";
	private final int portGroup = 7777;
	
	
	public AppControler () {
		id = (long)(1000000 * Math.random());
		c = new Core(id, this);
	}
	
	public void setGUI(GUI g) {
		gui = g;
	}

	
	public String getLeaveMsg() {
		return leaveMsg;
	}
	
	public String getTcpConnect() {
		return tcpConnect;
	}
	
	public void exit() {
		c.exit();
		Platform.exit();
	}

	public ArrayList<String> getUpNetworkInterafes() throws SocketException {
		return c.getUpNetworkInterafes();
	}

	public String getCurrentIP() {
		return c.getCurrentIP();
	}

	public void joinUDPMulticastGroup() {
		c.joinUDPMulticastGroup();
	}

	public void sendUDPMessage(String text) {
		c.sendUDPMessage(text);
	}

	public void leaveUDPMulticastGroup() {
		c.leaveUDPMulticastGroup();
	}

	public void setPreferredNetworkInterafe(String newValue) {
		c.setPreferredNetworkInterafe(newValue);
	}

	public long getId() {
		return id;
	}

	public void displayLog(String msg) {
		Platform.runLater(new Runnable() {
		    @Override public void run() {
		    	gui.displayLog(msg);
		    }
		});	
	}
	
	public void setConnected() {
		Platform.runLater(new Runnable() {
		    @Override public void run() {
		    	gui.setConnected();
		    }
		});	
	}

	public void setDisconnected() {
		Platform.runLater(new Runnable() {
		    @Override public void run() {
		    	gui.setDisconnected();
		    }
		});	
	}

	public boolean isLocalMsgSkip() {
		return gui.isLocalMsgSkip();
	}

	public void displayLog(boolean b, String s) {
		if (b)
			Platform.runLater(new Runnable() {
			    @Override public void run() {
			    	gui.displayLog("[Message local de "+id+"] "+ s);
			    }
			});
		else
			Platform.runLater(new Runnable() {
			    @Override public void run() {
			    	gui.displayLog("[Message Multicast reçu par "+id+"] "+s);
			    }
			});	
	}

	//--TCP--IP:xxx.xxx.xxx.xxx:yyyy
	public String extractIP(String msg) {
		return msg.split(":")[1];
	}

	public String extractPort(String msg) {
		// TODO Auto-generated method stub
		return msg.split(":")[2];
	}

	public String getIPGroup() {
		return ipGroup;
	}

	public int getPortGroup() {
		return portGroup;
	}
}
