package reseauOld;

import java.net.Socket;

/**
 * 
 * Cette interface permet de créer la méthode de réponse aux messages TCP.
 * 
 * @author S3T-G1
 *
 */

public interface ReponseMessageTCP {
	void onMessage(Socket socket, Message message);
}
