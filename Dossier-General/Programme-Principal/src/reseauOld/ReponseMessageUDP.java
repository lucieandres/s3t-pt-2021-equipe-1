package reseauOld;


/**
 * 
 * Cette interface permet de créer la méthode de réponse aux messages UDP.
 * 
 * @author S3T-G1
 *
 */

public interface ReponseMessageUDP {
	void onMessage(Message message);
}
