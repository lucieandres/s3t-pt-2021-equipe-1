package reseau;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javafx.scene.paint.Color;
import cartes.CarteObjectif;
import joueur.Joueur;
//TRAITER ICI LES COMMUNICATIONS QUI PARTENT DU PP
//GROUPE MULTICAST = MESSAGE UDP SINON MESSAGE TCP
//PASSER SOCKET EN PREMIER PARAMETRE DES MESSAGES TCP

/**
 * 
 * Cette classe permet de gérer l'envoi des messages réseaux dont l'envoyeur est le serveur
 * (le programme principal). Les envois de messages sont différents selon si la communication
 * se fait en UDP ou en TCP. Il est à noter que, dans le cas des méthodes de messages TCP, il y a besoin de passer 
 * une socket en paramètre, car, logiquement, le serveur ne connaîtra pas la socket qui est associé au client avec lequel il souhaite communiquer.
 * 
 * @author S3T-G1
 *
 */


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
	
	//MESSAGE ROM (TCP)
	public void informerEnsembleGainsObjectifs(Socket socket, List<CarteObjectif> listeCarteObj, List<Color> listeCouleur,String idPartie, int numeroManche) throws IOException {
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		
		Message message = new Message(TypeDeMessage.ROM);
		message.setLobjectif(listeCarteObj);
		message.setListec(listeCouleur);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());	
		
	}
	
	//MESSAGE FDP (TCP)
	public void finDePartie(Socket socket, Color couleur, List<Joueur> listeJoueur, List<Integer> listeScore,String idPartie) throws IOException {
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		
		Message message = new Message(TypeDeMessage.FDP);
	    message.setCouleur(couleur);
		message.setListej(listeJoueur);
		message.setListes(listeScore);
		message.setIdp(idPartie);
		
		
		writer.println(message.toString());	
		
	}
	
	//MESSAGE TLP (TCP)
	public void TerminerLaPartie(Socket socket, String idPartie) throws IOException {
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		
		Message message = new Message(TypeDeMessage.TLP);
		message.setIdp(idPartie);
		
		
		writer.println(message.toString());	
		
	}
	
	
}
	

