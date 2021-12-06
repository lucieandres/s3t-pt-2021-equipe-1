package reseau;

public class ImplémentationMessage {

	//Pour chaque message, créer :
		//PARTIE ENVOYEUR : créer le message et récupère les données.
		//PARTIE RECEPTEUR :
	
	//ACP CÔTÉ ENVOYEUR : PP (UDP)
    Message creerPartie = new Message(TypeDeMessage.ACP);
    creerPartie.setIdp("P205"); // ALEATOIRE
    creerPartie.setIp("224.7.7.7"); // VOIR PROTOCOLE RESEAU
    creerPartie.setPort(7777); // VOIR PROTOCOLE RESEAU
    creerPartie.setNom("Clem"); // ALEATOIRE
    creerPartie.setNbj(4); // RECUPERER AVEC INTERFACE GRAPHIQUE 
    creerPartie.setNbjrm(1); // RECUPERER AVEC INTERFACE GRAPHIQUE 
    creerPartie.setNbjvm(2); // RECUPERER AVEC INTERFACE GRAPHIQUE 
    creerPartie.setStatut("ATTENTE"); // SERA TOUJOURS EN ATTENTE AU DEBUT?
    udpC.sendUDPMessage(creerPartie.toString()); //	A REMPLACER
    
    //
	
    
    
}
