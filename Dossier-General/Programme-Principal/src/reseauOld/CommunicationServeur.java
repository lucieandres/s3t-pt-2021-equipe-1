package reseauOld;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javafx.scene.paint.Color;
import joueur.Joueur;
import cartes.CarteInfluence;
import cartes.CarteObjectif;
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

	private final static String ipGroupeUDP ="224.7.7.7";
	private final static int portGroupeUDP = 7777;
	private CoeurUDP coeurUDP = null;
	private static ReceptionServeurTCP serveur = null;
	//private HashMap<String,Socket> socketsJoueurs // a gérer au sein des clients et du PP.
	
	/**
	 * 
	 * Constructeur de la classe permettant d'instancier une communication serveur.
	 * 
	 * @param reponseUDP une réponse message UDP, correspondant à une instance du serveur qui implémente l'interface ReponseMessageUDP.
	 */
	
	public CommunicationServeur(ReponseMessageUDP reponseUDP) {
		coeurUDP = new CoeurUDP(ipGroupeUDP, portGroupeUDP);
		coeurUDP.joinUDPMulticastGroup(reponseUDP); //Crée la socket UDP.
	}
	
	/**
	 * 
	 * Méthode permettant d'initialiser un serveur TCP
	 * 
	 * @param port le port du serveur TCP.
	 * @param reponseTCP une réponse TCP, correspondant à une instance du serveur qui implémente l'interface ReponseMessageUDP.
	 * @return une réception de serveur TCP
	 */

	public ReceptionServeurTCP initServeurTCP(int port, ReponseMessageTCP reponseTCP) {
		serveur = new ReceptionServeurTCP(port, reponseTCP);
		Thread t = new Thread(serveur);
	    t.start();
	    return serveur;
	}
	
	/**
	 * 
	 * Méthode permettant d'annoncer la création d'une partie, en utilisant le message ACP.
	 * La communication se fera en UDP.
	 * 
	 * @param idPartie identifiant unique de partie. La lettre P suivie d’un entier entre 0 et 9 999 999 (exemple P258456).
	 * @param ip l’IP pour rejoindre la partie sous la forme xxx.xxx.xxx.xxx (où xxx pourra être sur 1, 2 ou 3 digits en fonction de la valeur).
	 * @param port le port pour rejoindre la partie sous la forme d’un entier entre 1024 et 65535.
	 * @param nomPartie le nom de la partie, sous la forme d’une chaine de caractères pouvant contenir des lettres majuscules et minuscule (accentuées ou non), des nombres et les caractères spéciaux apostrophe «’», espace « » et souligné bas «_».
	 * @param nombreJoueursVoulu Nombre de joueurs souhaités sur la partie.
	 * @param nombreJoueurs  Nombre de joueurs réels maximum sur la partie (ne peut pas être supérieur à NBJ).
	 * @param nombreBots  Nombre de joueurs virtuels (BOT) maximum sur la partie (ne peut pas être supérieur à NBJ).
	 * @param statut le statut de la partie ("ATTENTE").
	 */
	
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
	    coeurUDP.sendUDPMessage(message.toString());
	}
	
	
	/**
	 * 
	 * Méthode permettant d'annoncer la mise à jour d'une partie, en utilisant le message AMP.
	 * La communication se fera en UDP.
	 * 
	 * @param idPartie identifiant unique de partie. La lettre P suivie d’un entier entre 0 et 9 999 999 (exemple P258456).
	 * @param ip l’IP pour rejoindre la partie sous la forme xxx.xxx.xxx.xxx (où xxx pourra être sur 1, 2 ou 3 digits en fonction de la valeur).
	 * @param port le port pour rejoindre la partie sous la forme d’un entier entre 1024 et 65535.
	 * @param nomPartie le nom de la partie, sous la forme d’une chaine de caractères pouvant contenir des lettres majuscules et minuscule (accentuées ou non), des nombres et les caractères spéciaux apostrophe «’», espace « » et souligné bas «_».
	 * @param nombreJoueursVoulu Nombre de joueurs souhaités sur la partie.
	 * @param nombreJoueurs  Nombre de joueurs réels maximum sur la partie (ne peut pas être supérieur à NBJ).
	 * @param nombreBots  Nombre de joueurs virtuels (BOT) maximum sur la partie (ne peut pas être supérieur à NBJ).
	 * @param nombreJoueursConnectes Nombre de joueurs réels connectés à la partie (ne peut pas être supérieur à NBJRM).
	 * @param nombreBotsConnectes  Nombre de joueurs virtuels (BOT) connectés à la partie (ne peut pas être supérieur à NBJVM)
	 * @param statut le statut de la partie ("ATTENTE").
	 */
	
	//MESSAGE AMP (UDP) 
	public void annoncerMiseAJourPartie(String idPartie, String ip, int port, String nomPartie, int nombreJoueursVoulu, int nombreJoueurs, int nombreBots,int nombreJoueursConnectes, int nombreBotsConnectes,  String statut ) {
		Message message = new Message(TypeDeMessage.AMP);
	    message.setIdp(idPartie);
	    message.setIp(ip);
	    message.setPort(port);
	    message.setNom(nomPartie);
	    message.setNbj(nombreJoueursVoulu);
	    message.setNbjrm(nombreJoueurs);
	    message.setNbjvm(nombreBots);
	    message.setNbjrc(nombreJoueursConnectes);
	    message.setNbjvc(nombreBotsConnectes);
	    message.setStatut(statut);
	    coeurUDP.sendUDPMessage(message.toString());
	}

	
	/**
	 * 
	 * Méthode permettant d'accepter quelqu'un dans la partie, en utilisant le message ADP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @param idJoueur l’identifiant du joueur.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ADP (TCP)
	public void accepterDansLaPartie(Socket socket, String idPartie, String idJoueur) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ADP);
		message.setIdp(idPartie);
		message.setIdj(idJoueur);
		
		ecriture.println(message.toString());	
		
	}
	
	
	/**
	 * 
	 * Méthode permettant de refuser quelqu'un dans la partie, en utilisant le message RDP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE RDP (TCP)
	public void refuserDansLaPartie(Socket socket, String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.RDP);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant d'annoncer la déconnexion de quelqu'un dans la partie, en utilisant le message ADJ.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ADJ (TCP)
	public void afficherDeconnexionJoueur(Socket socket, String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ADJ);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant d'initialiser une partie, en utilisant le message ILP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param listeJoueurs la liste des joueurs sous formes de chaîne de caractère. Chaque nom est séparé par une ",".
	 * @param listeCouleurs la liste des couleurs de chaque joueurs, dans le même ordre que la liste précédente.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ILP (TCP)
	public void initialiserPartie(Socket socket, List<String> listeJoueurs, List<Color> listeCouleurs, String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ILP);
		message.setListej(listeJoueurs);
		message.setListec(listeCouleurs);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant a chaque joueur de recevoir indépendamment 3 cartes influences, en utilisant le message RTC.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param listeCartes la liste des trois cartes influences reçues par le joueur, et séparées par une ",".
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE RTC (TCP)
	public void recevoirTroisPremièreCartes(Socket socket, List<CarteInfluence> listeCartes, String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.RTC);
		message.setLcarte(listeCartes);
		message.setIdp(idPartie);
		
		ecriture.println(message.toString());	
		
	}
	

	
	
	
	
	/**
	 * 
	 * Méthode permettant d'initialiser une manche, en utilisant le message ILM.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param listeCarteObj la liste des cartes « objectif » de la manche séparées par des « , » et décrite selon le codage présenté précédemment. Elles sont fournies dans l’ordre des colonnes du plateau.
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois. 
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ILM (TCP) 
	public void initialiserManche(Socket socket, List<CarteObjectif> listeCarteObj, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ILM);
		message.setLobjectif(listeCarteObj);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant d'initialiser un tour, en utilisant le message IDT.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param Couleur indique la couleur du joueur courant. La couleur est identifiée par le code couleur (3 caractères) des cartes « influence ».
	 * @param idPartie l’identifiant de la partie.
	 * @param numeroManche : un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois. 
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE IDT (TCP)
	public void initialiserTour(Socket socket, Color couleur, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.IDT);
		message.setCouleur(couleur);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		ecriture.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant d'informer l'ensemble des joueurs de la carte jouée, en utilisant le message ICJ.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param Couleur indique la couleur du joueur courant. La couleur est identifiée par le code couleur (3 caractères) des cartes « influence ».
	 * @param idPartie l’identifiant de la partie.
	 * @param numeroManche : un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois. 
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ICJ (TCP)
	public void informerJoueursCarteJouee(Socket socket, Color couleur, int colonne, CarteInfluence carteRetournee, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ICJ);
		message.setCouleur(couleur);
		message.setCouleur(couleur);
		message.setCr(carteRetournee);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		ecriture.println(message.toString());	
		
	}
	
	
	
	/**
	 * 
	 * Méthode permettant au joueur de choisir la carte qu’il souhaite mettre sous la cape d’invisibilité, en utilisant le message CCI.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param numeroColonne le numéro (entre 1 et 6) de la colonne « objectif » où se trouve la cape d’invisibilité. 
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois. 
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE CCI (TCP)
	public void demanderCarteCapeInvisibilite(Socket socket, int numeroColonne, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.CCI);
		message.setCo(numeroColonne);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());
		
	}
	
	/**
	 * 
	 * Méthode permettant de remplir la main du joueur s’il a mis une carte sous la cape d’invisibilité, en utilisant le message RMC.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param numeroCarte La nouvelle carte ajouté à la main du joueur.
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois. 
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE RMC (TCP)
	public void remplirMainJoueurCapeInvisibilite(Socket socket, CarteInfluence numeroCarte, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.RMC);
		message.setNc(numeroCarte);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());
		
	}
	
	/**
	 * 
	 * Méthode permettant de demander au joueur la carte objectifs qu’il souhaite échanger avec la carte de sa colonne, en utilisant le message RCT.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param carteObjectif la colonne choisie (qui est forcément différente de celle de la carte traite).
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ECT (TCP)
	public void demanderJoueurColonneObjectif(Socket socket, CarteObjectif carteObjectif, int numeroColonne, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ECT);
		message.setObjectif(carteObjectif);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());
		
	}
	
	/**
	 * 
	 * Méthode permettant d'informer l’ensemble des joueurs des effets de la carte retournée, en utilisant le message ICR.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param numeroColonne le numéro (entre 1 et 6) de la colonne « objectif » où se trouve la cape d’invisibilité.
	 * @param carteRetourne la carte qui vient d’être retournée. 
	 * @param capaciteSpeciale La capacité spéciale immédiate de la carte.
	 * @param objectifRealise « VRAI » si après l’effet de la carte retournée l’objectif de la colonne est réalisé, « FAUX » si ce n’est pas le cas.
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ICR (TCP)
	public void informerEnsembleJoueursEffetsCarteRetournee(Socket socket, int numeroColonne, CarteInfluence carteRetourne, String objectifRealise, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ICR);
		message.setCo(numeroColonne);
		message.setCr(carteRetourne);
		message.setOr(objectifRealise);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());
		
	}
	
	/**
	 * 
	 * Méthode permettant de remplir la main du joueur qui a joué durant ce tour, en utilisant le message RMJ.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param nouvelleCarte La nouvelle carte ajouté à la main du joueur.
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE RMJ (TCP)
	public void remplirMainJoueur(Socket socket, CarteInfluence nouvelleCarte, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ECT);
		message.setNc(nouvelleCarte);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());
		
	}
	
	/**
	 * 
	 * Méthode permettant si la réserve du joueur courant est vide de la remplir avec les cartes de sa défausse, en utilisant le message RRJ.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param Couleur indique la couleur du joueur courant. La couleur est identifiée par le code couleur (3 caractères) des cartes « influence ».
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE RRJ (TCP)
	public void informerJoueursMiseAJourReserve(Socket socket, Color couleur, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ECT);
		message.setCouleur(couleur);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());
		
	}
	
	/**
	 * 
	 * Méthode permettant d'informer l’ensemble des joueurs de la fin de la manche, en utilisant le message FDM.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param nouvelleCarte La nouvelle carte ajouté à la main du joueur.
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante. La première manche commence à 1 et le numéro est incrémenté de 1 à chaque fois.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE FDM (TCP)
	public void informerJoueursFinManche(Socket socket, CarteInfluence nouvelleCarte, String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ECT);
		message.setNc(nouvelleCarte);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());
		
	}
	
	/**
	 * 
	 * Méthode permettant d'informer l'ensemble des gains d'objectif, en utilisant le message ROM.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param listeCarteObj la liste des cartes « objectif » de la manche séparées par des « , » et décrite selon le codage présenté précédemment. Elles sont fournies dans l’ordre des colonnes du plateau.
	 * @param listeCouleur la liste des couleurs de chaque joueur ayant remporté l’objectif dans le même ordre que la liste précédente. Chaque couleur est identifiée par le code couleur (3 caractères) des cartes « influence » et les couleurs sont séparées par des « , ».
	 * @param idPartie l’identifiant de la partie fournie à l’initialisation de la partie.
	 * @param numeroManche  un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ROM (TCP)
	public void informerEnsembleGainsObjectifs(Socket socket, List<CarteObjectif> listeCarteObj, List<Color> listeCouleur,String idPartie, int numeroManche) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ROM);
		message.setLobjectif(listeCarteObj);
		message.setListec(listeCouleur);
		message.setIdp(idPartie);
		message.setNm(numeroManche);
		
		writer.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant d'indiquer la fin d'une partie, en utilisant le message FDP.
	 * La communication se fera en TCP. 
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param couleur indique la couleur du joueur gagnant t. La couleur est identifiée par le code couleur (3 caractères) des cartes « influence ».
	 * @param listeJoueur la liste des nom joueurs classées selon leur score (le gagnant en premier) et séparés par des « , ».
	 * @param listeScore la liste des score séparés par des « , » dans le même ordre que la liste des joueurs.
	 * @param idPartie
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE FDP (TCP)
	public void finDePartie(Socket socket, Color couleur, List<String> listeJoueur, List<Integer> listeScore,String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.FDP);
	    message.setCouleur(couleur);
		message.setListej(listeJoueur);
		message.setListes(listeScore);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant de terminer la partie, en utilisant le message TLP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE TLP (TCP)
	public void terminerLaPartie(Socket socket, String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.TLP);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	/**
	 * 
	 * Méthode permettant de relancer une nouvelle partie, en utilisant le message RNP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @param idNouvellePartie l'identifiant de la nouvelle partie
	 * @throws IOException exception d'entrée/sortie.
	 */
	//MESSAGE RNP (TCP)
	public void relancerNouvellePartie(Socket socket,String idPartie, String idNouvellePartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.RNP);
		message.setIdp(idPartie);
		message.setIdnp(idNouvellePartie);
		
		
		ecriture.println(message.toString());	
	}
	
	/**
	 * 
	 * Méthode permettant d'informer les joueurs de la restauration d'une partie, en utilisant le message RLP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE RLP (TCP)
	public void relancerLaPartie(Socket socket, String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.RLP);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	/**
	 * 
	 *  Méthode permettant d'informer le joueur courant du début de la restauration d'une partie, en utilisant le message DRP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param NombreMessages le nombre de messages dans l'étape de restauration pour le joueur courant.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE DRP (TCP)
	public void debutRestaurationPartie(Socket socket, int NombreMessages,String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.DRP);
		message.setNbm(NombreMessages);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	
	/**
	 * 
	 *  Méthode permettant de transmettre les messages un par un à chaque joueur, en utilisant le message TME. le dernier message enregistré n'est pas transmis.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param NumeroMessage le numéro du message dans l'étape de restauration pour le joueur courant
	 * @param Message le message tel que défini dans le protocole.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE TME (TCP)
	public void transmissionMessageEnregistrer(Socket socket, int NumeroMessage,String Message) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.TME);
		message.setNm(NumeroMessage);
		message.setMessage(Message);
		
		
		ecriture.println(message.toString());	
		
	}
	
	
	/**
	 * 
	 * Méthode permettant d'informer de la fin de la transmission des messages, en utilisant le message FTM.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE FTM (TCP)
	public void finTransmissionMessages(Socket socket, String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.FTM);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	
	/**
	 * 
	 * Méthode permettant de mettre en pause la partie, en utilisant le message CCP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE CCP (TCP)
	public void couperCoursPartie(Socket socket, String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.CCP);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	
	/**
	 * 
	 * Méthode permettant de reprendre la partie, en utilisant le message ARP.
	 * La communication se fera en TCP.
	 * 
	 * @param socket la socket qui fera la correspondance entre serveur et client.
	 * @param idPartie l’identifiant de la partie.
	 * @throws IOException exception d'entrée/sortie.
	 */
	
	//MESSAGE ARP (TCP)
	public void actionReprendrePartie(Socket socket, String idPartie) throws IOException {
		OutputStream sortie = socket.getOutputStream();
		PrintWriter ecriture = new PrintWriter(sortie, true);
		
		Message message = new Message(TypeDeMessage.ARP);
		message.setIdp(idPartie);
		
		
		ecriture.println(message.toString());	
		
	}
	
	
}
	

