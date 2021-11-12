package reseau;

import java.net.Socket;

public interface ReponseMessageTCP {
	void onMessage(Socket socket, Message message);
}
