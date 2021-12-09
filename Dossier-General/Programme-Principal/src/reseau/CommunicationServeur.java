package reseau;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import cartes.*;

//TRAITER ICI LES COMMUNICATIONS QUI PARTENT DU PP
//GROUPE MULTICAST = MESSAGE UDP SINON MESSAGE TCP
//PASSER SOCKET EN PREMIER PARAMETRE DES MESSAGES TCP

public class CommunicationServeur {

	private final static String ipGroup ="224.7.7.7";
	private final static int portGroup = 7777;
	private CoeurUDP udpCore = null;
	private static ReceptionServeurTCP serveur = null;
	//private HashMap<String,Socket> socketsJoueurs // a gérer au sein des clients et du PP.
	
	public CommunicationServeur(ReponseMessageUDP udpCallback) {
		udpCore = new CoeurUDP(ipGroup, portGroup);
		udpCore.joinUDPMulticastGroup(udpCallback); //Crée la socket UDP.
	}

	public ReceptionServeurTCP initServeurTCP(int port, ReponseMessageTCP tcpCallback) {
		serveur = new ReceptionServeurTCP(port, tcpCallback);
		Thread t = new Thread(serveur);
	    t.start();
	    return serveur;
	}
	
	
	//MESSAGE ACP (UDP) 
	public void annoncerCreationPartie(String idPartie, String ip, int port, String nomPartie, int nombreJoueursVoulu, int nombreJoueurs, int nombreBots, String statut ) {
		Message message = new Message(TypeDeMessage.ACP);
	    message.setIdp(idPartie);
	    message.setIp(ip);
	    message.setPort(port);
	    message.setNom(nomPartie);
	    message.setNbj(nombreJoueursVoulu);
	    message.setNbjrm(nombreJoueurs);
	    message.setNbjvm(nombreBots);
	    message.setStatut(statut);
	    udpCore.sendUDPMessage(message.toString());
	}
	
	//MESSAGE ILM (TCP) 
	public void initialiserManche(Socket socket, List<CarteObjectif> listeCarteObj, String idPartie, int numeroManche) throws IOException {
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		
		Message message = new Message(TypeDeMessage.ILM);
		message.setLobjectif(listeCarteObj);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());	
		
	}
}
