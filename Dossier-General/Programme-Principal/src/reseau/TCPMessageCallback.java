package reseau;

import java.net.Socket;

public interface TCPMessageCallback {
	void onMessage(Socket socket, Message message);
}
