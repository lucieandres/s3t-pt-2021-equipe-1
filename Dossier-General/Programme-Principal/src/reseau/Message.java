package reseau;

/**
 * 
 * Cette classe répertorie les différents messages réseaux utilisés lors du protocole. A partir de cette classe, un client
 * et un serveur sont capables de décoder chaque message réseau, ainsi que de récuperer les variables correspondantes.
 * 
 * @author S3T-G1
 *
 */

public class Message {

	private TypeDeMessage type;
	private String idp;
	private String ip;
	private int port;
	private String nom;
	private int nbj;
	private int nbjrm;
	private int nbjvm;
	private String statut;
	private int nbjrc;
	private int nbjvc;
	private String typep;
	private int taillep;
	private String nomj;
	private String typej;
	private String idj;
	private String listej;
	private String listec;
	private String lcarte;
	private String lobjectif;
	private int nm;
	private String couleur;
	private String ci;
	private int co;
	private String cr;
	private String nc;
	private String objectif;
	private String cs;
	//Message OJECTO
	private String or;
	private String listes;
	private String idnp;
	private int nbm;
	private int nme;
	private String message;
	
	/**
	 * 
	 * Constructeur permettant de décoder chaque message en fonction du type du message, et de récupérer chaque paramètres du message.
	 * 
	 * @param msg Le message réseau que l'on souhaite traité.
	 * @throws ExceptionMessage exception qui se lance en cas de mauvaise syntaxe du message réseau.
	 */
	
	
	//LA METHODE CI DESSOUS EST ENCORE INCOMPLETE, IL RESTE NOTAMMENT
	//BEAUCOUP D'EXCEPTIONS ET DE CAS PARTICULIERS A TRAITER.
	public Message(String msg) throws ExceptionMessage {
		if (!msg.substring(3,4).equals("-"))
			throw new ExceptionMessage(msg + " mal formate");
					
		String mtype = msg.substring(0,3);
		msg = msg.substring(4);
		String[] vars = msg.split("-");
		switch(mtype) {
		
			//LES MESSAGES ECHANGES POUR LA CREATION D'UNE PARTIE :
			
			case "ACP":
				// decode le message ACP
				
				if (vars == null || vars.length!=8)
					throw new ExceptionMessage(msg + " ACP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ACP;
				idp = new String(vars[0]);
				ip = new String(vars[1]);
			    port = Integer.parseInt(vars[2]);
				nom = new String(vars[3]);
				// exception si non int
				nbj = Integer.parseInt(vars[4]);
				nbjrm = Integer.parseInt(vars[5]);
				nbjvm = Integer.parseInt(vars[6]);
				statut = new String(vars[7]);
				
				break;
				
			case "AMP":	
				//decode le message AMP
				if (vars == null || vars.length!=10)
					throw new ExceptionMessage(msg + " AMP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.AMP;
				idp = new String(vars[0]);
				ip = new String(vars[1]);
			    port = Integer.parseInt(vars[2]);
				nom = new String(vars[3]);
				nbj = Integer.parseInt(vars[4]);
				nbjrm = Integer.parseInt(vars[5]);
				nbjvm = Integer.parseInt(vars[6]);
				nbjrc = Integer.parseInt(vars[7]);
				nbjvc = Integer.parseInt(vars[8]);
				statut = new String(vars[9]);
				
				break;
				
			case "RUP":	
				//decode le message RUP
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " RUP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RUP;
				typep = new String(vars[0]);
				taillep = Integer.parseInt(vars[1]);
			    
				break;
				
			case "DCP":
				//decode le message DCP
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " DCP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.DCP;
				nomj = new String(vars[0]);
				typej = new String(vars[1]);
				idp = new String(vars[2]);
			
				break;
				
			case "ADP":
				//decode le message ADP
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " ADP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ADP;
				idp = new String(vars[0]);
				idj = new String(vars[1]);
				
				break;
				
			case "RDP":
				//decode le message RDP
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " RDP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RDP;
				idp = new String(vars[0]);
				
				break;
				
			case "ADJ":
				//decode le message ADJ
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " ADJ : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ADJ;
				idp = new String(vars[0]);
				
				break;
			
			//LES MESSAGES ECHANGES POUR L'INITIALISATION D'UNE PARTIE CREER ET COMPLETE :
				
			case "ILP":
				//decode le message ILP
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " ILP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ILP;
				listej = new String(vars[0]);
				listec = new String(vars[1]);
				idp = new String(vars[2]);
				
				break;
			
			case "RTC":
				//decode le message RTC
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " RTC : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RTC;
				lcarte = new String(vars[0]);
				idp = new String(vars[1]);
				
				break;
			
			//LES MESSAGES ECHANGES DURANT UNE PARTIE :	
				
			case "ILM":
				//decode le message ILM
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " ILM : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ILM;
				lobjectif = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "IDT":
				//decode le message IDT
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " IDT : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.IDT;
				couleur = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
			
			case "JCI":
				//decode le message JCI
				if (vars == null || vars.length!=5)
					throw new ExceptionMessage(msg + " JCI : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.JCI;
				ci = new String(vars[0]);
				co = Integer.parseInt(vars[1]);
				idp = new String(vars[2]);
				nm = Integer.parseInt(vars[3]);
				idj = new String(vars[4]);
				
				break;
				
			case "ICJ":
				//decode le message ICJ
				if (vars == null || vars.length!=5)
					throw new ExceptionMessage(msg + " ICJ : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ICJ;
				couleur = new String(vars[0]);
				co = Integer.parseInt(vars[1]);
				cr = new String(vars[2]);
				idp = new String(vars[3]);
				nm = Integer.parseInt(vars[4]);
				
				break;
				
			case "CCI":
				//decode le message CCI
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " CCI : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.CCI;
				co = Integer.parseInt(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "JCC":
				//decode le message JCC
				if (vars == null || vars.length!=4)
					throw new ExceptionMessage(msg + "JCC : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.JCC;
				ci = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				idj = new String(vars[3]);
				
				break;
				
			case "RMC":
				//decode le message RMC
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + "RMC : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RMC;
				nc = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
						
				break;
				
			case "ECT":
				//decode le message ECT
				if (vars == null || vars.length!=4)
					throw new ExceptionMessage(msg + "ECT : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ECT;
				objectif = new String(vars[0]);
				co = Integer.parseInt(vars[1]);
				idp = new String(vars[2]);
				nm = Integer.parseInt(vars[3]);
						
				break;
				
			case "JCT":
				//decode le message JCT
				if (vars == null || vars.length!=4)
					throw new ExceptionMessage(msg + "JCT : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.JCT;
				co = Integer.parseInt(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				idj = new String(vars[3]);
						
				break;

			case "ICR":
				//decode le message ICR
				if (vars == null || vars.length!=6)
					throw new ExceptionMessage(msg + "ICR : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ICR;
				co = Integer.parseInt(vars[0]);
				cr = new String(vars[1]);
				cs = new String(vars[2]); // --- devra etre traiter specifiquement pour plusieurs cas ---
				or = new String(vars[3]); // VRAI ou FAUX
				if (or == "VRAI") {}
				else if (or == "FAUX") {}
				else {} // a completer
				idp = new String(vars[4]);
				nm = Integer.parseInt(vars[5]);		
				
				break;
				
			//Message OJECTO
				
			case "RMJ":
				//decode le message RMJ
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + "RMJ : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RMJ;
				nc = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);

				break;
				
			case "RRJ":
				//decode le message RRJ
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + "RRJ : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RRJ;
				couleur = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "FDM":
				//decode le message FDM
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + "FDM : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.FDM;
				nc = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "ROM":
				//decode le message ROM
				if (vars == null || vars.length!=4)
					throw new ExceptionMessage(msg + "ROM : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ROM;
				lobjectif = new String(vars[0]);
				listec = new String(vars[1]);
				idp = new String(vars[2]);
				nm = Integer.parseInt(vars[3]);
				
				break;
				
			case "FDP":
				//decode le message FDP
				if (vars == null || vars.length!=4)
					throw new ExceptionMessage(msg + "FDP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.FDP;
				couleur = new String(vars[0]);
				listej = new String(vars[1]);
				listes = new String(vars[2]);
				idp = new String(vars[3]);
				//nt? voir protocole reseau.
				
				break;
			
			//LES MESSAGES ECHANGES APRES LA FIN D'UNE PARTIE:	
				
			case "TLP":
				//decode le message TLP
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + "TLP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.TLP;
				idp = new String(vars[0]);
				
				break;
				
				
			case "RNP":
				//decode le message RNP
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " RNP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RNP;
				idp = new String(vars[0]);
				idnp = new String(vars[1]);

				break;
			
			//LES MESSAGES POUR LA RESTAURATION D'UNE PARTIE PREALABLEMENT SAUVEGARDEE:	
			
			case "RLP":
				//decode le message RLP
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " RLP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RLP;
				idp = new String(vars[0]);

				break;
				
			case "DRP":
				//decode le message DRP
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " DRP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.DRP;
				nbm = Integer.parseInt(vars[0]);
				idp = new String(vars[1]);

				break;	
			
			case "TME":
				//decode le message TME
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " TME : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.TME;
				nme = Integer.parseInt(vars[0]);
				message = new String(vars[1]);

				break;
				
			case "FTM":
				//decode le message FTM
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " FTM : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.FTM;
				idp = new String(vars[0]);

				break;
				
			//METTRE EN PAUSE UNE PARTIE:
				
			case "CCP":
				//decode le message CCP
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " CCP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.CCP;
				idp = new String(vars[0]);

				break;
				
			case "ARP":
				//decode le message ARP
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " ARP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ARP;
				idp = new String(vars[0]);

				break;
			
			default:			
				throw new ExceptionMessage(mtype + " type de message inconnu.");
		}
	}


	/**
	 * 
	 * Constructeur de Message, a partir d'un type de message.
	 * 
	 * @param type Type de message (Exemple : ACP)
	 */

	public Message(TypeDeMessage type) {
		this.type = type;
	}

	/**
	 * 
	 * Méthode qui permet de renvoyer une chaîne de caractère avec le bon message et les bons paramètres en fonction du type de message.
	 * Redéfinition d'une méthode déjà existante.
	 * 
	 */
	

	@Override
	public String toString() {
		switch (type) {
			
		
			case ACP:
				return "ACP-" + idp + "-" + ip + "-" + port + "-" + nom + "-"
					+ nbj + "-" + nbjrm + "-" + nbjvm + "-" + statut+ "|";
			
			case AMP:
				return "AMP-" + idp + "-" + ip + "-" + port + "-" + nom + "-"
					+ nbj + "-" + nbjrm + "-" + nbjvm + "-" + nbjrc + "-" + nbjvc + "-" + statut+ "|";
				
			case RUP:
				return "RUP-" + typep + "-" + taillep+ "|";
			
			case DCP:
				return "DCP-" + nomj + "-" + typej + "-" + idp+ "|";
			
			case ADP:
				return "ADP-" + idp + "-" + idj+ "|";
				
			case RDP:
				return "RDP-" + idp+ "|";
				
			case ADJ:
				return "ADJ-" + idp+ "|";
				
			//LES MESSAGES ECHANGES POUR L'INITIALISATION D'UNE PARTIE CREER ET COMPLETE :	
				
			case ILP:
				return "ILP-" + listej + "-" + listec + "-" + idp+ "|";
				
			case RTC:
				return "RTC-" + lcarte + "-" + idp+ "|";
				
			//LES MESSAGES ECHANGES DURANT UNE PARTIE :	
				
			case ILM:
				return "ILM-" + lobjectif + "-" + idp + "-" + nm+ "|";
				
			case IDT:
				return "IDT-" + couleur + "-" + idp + "-" + nm+ "|";
				
			case JCI:
				return "JCI-" + ci + "-" + co + "-" + idp + "-" + nm + "-" + idj+ "|";
				
			case ICJ:
				return "ICJ-" + couleur + "-" + co + "-" + cr + "-" + idp + "-" + nm+ "|";
				
			case CCI:
				return "CCI-" + co + "-" + idp + "-" + nm+ "|";
				
			case JCC:
				return "JCC-" + ci + "-" + idp + "-" + nm + "-" + idj+ "|";
				
			case RMC:
				return "RMC-" + nc + "-" + idp + "-" + nm+ "|";
			
			case ECT:
				return "ECT-" + objectif + "-" + co + "-" + idp + "-" + nm+ "|";
				
			case JCT:
				return "JCT-" + co + "-" + idp + "-" + nm + "-" + idj+ "|";
				
			//Message OJECTO
				
			case ICR:
				return "ICR-" + co + "-" + cr + "-" + cs + "-" + or + "-" + idp + "-" + nm+ "|"; 
				
			case RMJ:
				return "RMJ-" + nc + "-" + idp + "-" + nm+ "|";
				
			case RRJ:
				return "RRJ-" + couleur + "-" + idp + "-" + nm+ "|";
			
			case FDM:
				return "FDM-" + nc + "-" + idp + "-" + nm+ "|";
				
			case ROM:
				return "ROM-" + lobjectif + "-" + listec + "-" + idp + "-" + nm+ "|";
				
			case FDP:
				return "FDP-" + couleur + "-" + listej + "-" + listes + "-" + idp+ "|"; // NT? Voir protocole.
				
			//LES MESSAGES ECHANGES APRES LA FIN D'UNE PARTIE:	
			
			case TLP:
				return "TLP-" + idp+ "|";	
			
			case RNP:
				return "RNP-" + idp + "-" + idnp+ "|";
				
			//LES MESSAGES POUR LA RESTAURATION D'UNE PARTIE PREALABLEMENT SAUVEGARDEE:	
				
			case RLP:
				return "RLP-" + idp+ "|";
				
			case DRP:
				return "DRP-" + nbm + "-" + idp+ "|";
				
			case TME:
				return "TME-" + nme + "-" + message+ "|";
				
			case FTM:
				return "FTM-" + idp+ "|";
			
			//METTRE EN PAUSE UNE PARTIE:	
				
			case CCP:
				return "CCP-" + idp+ "|";
				
			case ARP:
				return "CCP-" + idp+ "|";
			
			default:
				return "Unknwon message";
		}
	}


	/**
	 * 
	 * Getter permettant de récupérer le type de message.
	 * 
	 * @return Le type de message
	 */

	public TypeDeMessage getType() {
		return type;
	}

	/**
	 * 
	 * Setter permettant d'initialiser le type de message
	 * 
	 * @param type Le type du message
	 * 
	 */

	public void setType(TypeDeMessage type) {
		this.type = type;
	}

	/**
	 * 
	 * Getter permettant de récupérer l'identifiant du joueur.
	 * 
	 * @return L'identifiant du joueur
	 */
	

	public String getIdp() {
		return idp;
	}

	/**
	 * 
	 * Setter permettant d'initialiser l'identifiant du joueur
	 * 
	 * @param idj L'identifiant du joueur
	 * 
	 */

	public void setIdp(String idp) {
		this.idp = idp;
	}


	/**
	 * 
	 * Getter permettant de récupérer l'IP.
	 * 
	 * @return L'IP
	 */
	

	public String getIp() {
		return ip;
	}


	/**
	 * 
	 * Setter permettant d'initialiser l'IP
	 * 
	 * @param ip L'IP
	 * 
	 */
	

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 
	 * Getter permettant de récupérer le port de la partie.
	 * 
	 * @return Le port.
	 */


	public int getPort() {
		return port;
	}

	/**
	 * 
	 * Setter permettant d'initialiser le port de la partie.
	 * 
	 * @param port Le port
	 */
	


	public void setPort(int port) {
		this.port = port;
	}


	/**
	 * 
	 * Getter permettant de récupérer le nom de la partie.
	 * 
	 * @return Le nom de la partie
	 */
	

	public String getNom() {
		return nom;
	}


	/**
	 * 
	 * Setter permettant d'initialiser le nom de la partie.
	 * 
	 * @param nom Le nom
	 */
	

	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * 
	 * Getter permettant de récuperer le nombre de joueurs d'une partie.
	 * 
	 * @return Le nombre de joueurs de la partie.
	 */
	

	public int getNbj() {
		return nbj;
	}


	/**
	 * 
	 * Setter permettant d'initialiser le nombre de joueurs d'une partie.
	 * 
	 * @param nbj Le nombre de joueurs d'une partie.
	 */
	

	public void setNbj(int nbj) {
		this.nbj = nbj;
	}


	/**
	 * 
	 * Getter permettant de récupérer le nombre max de joueurs réels souhaités sur une partie.
	 * 
	 * @return Le nombre max de joueurs réels souhaités sur une partie.
	 */
	
	

	public int getNbjrm() {
		return nbjrm;
	}


	/**
	 * 
	 * Setter permettant d'initialiser le nombre max de joueurs réels souhaités sur une partie.
	 * 
	 * @param nbjrm Le nombre max de joueurs réels souhaités sur une partie.
	 */
	
	
	public void setNbjrm(int nbjrm) {
		this.nbjrm = nbjrm;
	}


	/**
	 * 
	 * Getter permettant de récupérer le nombre max de joueurs virtuels (BOT) souhaités sur une partie.
	 * 
	 * @return Le nombre max de joueurs virtuels (BOT) souhaités sur une partie.
	 */
	

	public int getNbjvm() {
		return nbjvm;
	}


	/**
	 * 
	 * Setter permettant d'initialiser le nombre max de joueurs virtuels (BOT) souhaités sur une partie.
	 * 
	 * @param nbjvm Le nombre max de joueurs virtuels (BOT) souhaités sur une partie.
	 */
	

	public void setNbjvm(int nbjvm) {
		this.nbjvm = nbjvm;
	}


	/**
	 * 
	 * Getter permettant de récuperer le statut de la partie.
	 * 
	 * @return Le statut de la partie.
	 */
	

	public String getStatut() {
		return statut;
	}

	
	/**
	 * 
	 * Setter permettant d'initialiser le statut de la partie.
	 * 
	 * @param statut Le statut de la partie.
	 */


	public void setStatut(String statut) {
		this.statut = statut;
	}


	/**
	 * 
	 * Getter permettant de récupérer le nombre de joueurs réels actuellement connectés
	 * à la partie.
	 * 
	 * 
	 * @return Le nombre de joueurs réels actuellement connectées à la partie.
	 */
	

	public int getNbjrc() {
		return nbjrc;
	}


	/**
	 * 
	 * Setter permettant d'initialiser le nombre de joueurs réels actuellement connectés
	 * à la partie.
	 * @param nbjrc Le nombre de joueurs réels actuellement connectées à la partie.
	 */
	

	public void setNbjrc(int nbjrc) {
		this.nbjrc = nbjrc;
	}

	/**
	 * 
	 * Getter permettant de récupérer le nombre de joueurs virtuels (BOT) actuellement
	 * connectés à la partie.
	 * 
	 * @return Le nombre de joueurs virtuels (BOT) actuellement connectés à la partie.
	 */
	

	public int getNbjvc() {
		return nbjvc;
	}

	/**
	 * 
	 * Setter permettant d'intialiser le nombre de joueurs virtuels (BOT) actuellement
	 * connectés à la partie.
	 * 
	 * @param nbjvc Le nombre de joueurs virtuels (BOT) actuellement connectés à la partie.
	 */


	public void setNbjvc(int nbjvc) {
		this.nbjvc = nbjvc;
	}


	/**
	 * 
	 * Getter permettant de récupérer le type de partie recherchée.
	 * 
	 * @return Le type de partie recherchée.
	 */
	
	
	public String getTypep() {
		return typep;
	}


	/**
	 * 
	 * Setter permettant d'initialiser le type de partie recherchée.
	 * 
	 * @param typep Le type de partie recherchée.
	 */
	

	public void setTypep(String typep) {
		this.typep = typep;
	}


	/**
	 * 
	 * Getter permettant de récupérer le nombre maximum de joueurs dans 
	 * la partie recherchée.
	 * 
	 * @return Le nombre maximum de joueurs dans la partie recherchée.
	 */
	

	public int getTaillep() {
		return taillep;
	}


	/**
	 * 
	 * Setter permettant d'initialiser le nombre maximum de joueurs dans 
	 * la partie recherchée.
	 * 
	 * @param taillep Le nombre maximum de joueurs dans la partie recherchée. 
	 */
	

	public void setTaillep(int taillep) {
		this.taillep = taillep;
	}
	
	
	/**
	 * 
	 * Getter permettant de récupérer le nom du joueur.
	 * 
	 * @return Le nom du joueur.
	 */
	
	
	public String getNomj() {
		return nomj;
	}

	
	/**
	 * 
	 * Setter permettant d'initialiser le nom du joueur.
	 * 
	 * @param nomj Le nom du joueur.
	 */


	public void setNomj(String nomj) {
		this.nomj = nomj;
	}

	/**
	 * 
	 * Getter permettant de récupérer le type du joueur (JR ou BOT).
	 * 
	 * @return Le type du joueur (JR ou BOT).
	 */
	

	public String getTypej() {
		return typej;
	}

	/**
	 * 
	 * Setter permettant d'initialiser le type du joueur (JR ou BOT).
	 * 
	 * @param typej Le type du joueur (JR ou BOT).
	 */

	public void setTypej(String typej) {
		this.typej = typej;
	}


	/**
	 * 
	 * Getter permettant de récupérer l'ID du joueur.
	 * 
	 * @return L'ID du joueur.
	 */
	

	public String getIdj() {
		return idj;
	}


	/**
	 * 
	 * Setter permettant d'initialiser l'ID du joueur.
	 * 
	 * @param idj L'ID du joueur.
	 */

	public void setIdj(String idj) {
		this.idj = idj;
	}


	/**
	 * 
	 * Getter permettant de récupérer la liste des joueurs.
	 * 
	 * @return La liste des joueurs.
	 */
	

	public String getListej() {
		return listej;
	}


	/**
	 * 
	 * Setter permettant d'initialiser la liste des joueurs.
	 * 
	 * @param listej La liste des joueurs.
	 */

	public void setListej(String listej) {
		this.listej = listej;
	}


	/**
	 * 
	 * Getter permettant de récupérer la liste des couleurs de chaque joueur.
	 * 
	 * @return La liste des couleurs de chaque joueur.
	 */

	public String getListec() {
		return listec;
	}

	/**
	 * 
	 * Setter permettant d'initialiser la liste des couleurs de chaque joueur.
	 * 
	 * @param listec La liste des couleurs de chaque joueur.
	 */

	public void setListec(String listec) {
		this.listec = listec;
	}

	/**
	 * 
	 * Getter permettant de récupérer les trois cartes de départ.
	 * 
	 * @return Les trois cartes de départ.
	 */

	public String getLcarte() {
		return lcarte;
	}


	/**
	 * 
	 * Setter permettant d'initialiser les trois cartes de départ.
	 * 
	 * @param lcarte Les trois cartes de départ.
	 */
	
	public void setLcarte(String lcarte) {
		this.lcarte = lcarte;
	}


	/**
	 * 
	 * Getter permettant de récupérer la liste des cartes objectifs de la manche.
	 * 
	 * @return La liste des cartes objectifs de la manche.
	 */
	

	public String getLobjectif() {
		return lobjectif;
	}


	/**
	 * 
	 * Setter permettant d'initialiser la liste des cartes objectifs de la manche.
	 * 
	 * @param lobjectif La liste des cartes objectifs de la manche.
	 */
	

	public void setLobjectif(String lobjectif) {
		this.lobjectif = lobjectif;
	}


	/**
	 * 
	 * Getter permettant de récupérer le n° de la manche courante.
	 * 
	 * @return Le n° de la manche courante.
	 */
	

	public int getNm() {
		return nm;
	}

	/**
	 * 
	 * Setter permettant d'initialiser le n° de la manche courante.
	 * 
	 * @param nm Le n° de la manche courante.
	 */


	public void setNm(int nm) {
		this.nm = nm;
	}


	/**
	 * 
	 * Getter permettant de récupérer la couleur du joueur courant.
	 * 
	 * @return La couleur du joueur courant.
	 */
	

	public String getCouleur() {
		return couleur;
	}


	/**
	 * 
	 * Setter permettant d'initialiser la couleur du joueur courant.
	 * 
	 * @param couleur La couleur du joueur courant.
	 */
	

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


	/**
	 * 
	 * Getter permettant de récupérer la carte choisie par le joueur.
	 * 
	 * @return La carte choisie par le joueur.
	 */
	

	public String getCi() {
		return ci;
	}

	/**
	 * 
	 * Setter permettant d'initialiser la carte choisie par le joueur.
	 * 
	 * @param ci La carte choisie par le joueur.
	 */
	

	public void setCi(String ci) {
		this.ci = ci;
	}


	/**
	 * 
	 * Getter permettant de récupérer le n° de la colonne objectif 
	 * où est jouée la carte.
	 * 
	 * @return Le n° de la colonne objectif où est jouée la carte.
	 */
	

	public int getCo() {
		return co;
	}

	/**
	 * 
	 * Setter permettant d'initialiser le n° de la colonne objectif 
	 * où est jouée la carte. 
	 * 
	 * @param co Le n° de la colonne objectif où est jouée la carte.
	 */


	public void setCo(int co) {
		this.co = co;
	}


	/**
	 * 
	 * Getter permettant de récupérer la carte retournée (ou non s'il
	 * n'y a pas de carte retournée) courante.
	 * 
	 * @return La carte retournée (ou non s'il n'y a pas de carte retournée) courante.
	 */

	public String getCr() {
		return cr;
	}

	/**
	 * 
	 * Setter permettant d'initialiser la carte retournée (ou non s'il
	 * n'y a pas de carte retournée) courante.
	 * 
	 * @param cr La carte retournée (ou non s'il n'y a pas de carte retournée) courante.
	 */
	

	public void setCr(String cr) {
		this.cr = cr;
	}

	/**
	 * 
	 * Getter permettant de récupérer la nouvelle carte ajoutée
	 * à la main du joueur.
	 * 
	 * @return La nouvelle carte ajoutée à la main du joueur.
	 */

	public String getNc() {
		return nc;
	}

	/**
	 * 
	 * Setter permettant d'intialiser la nouvelle carte
	 * ajoutée à la main du joueur
	 * 
	 * @param nc La nouvelle carte ajoutée à la main du joueur.
	 */

	public void setNc(String nc) {
		this.nc = nc;
	}


	/**
	 * 
	 * Getter permettant de récupérer la carte objectif de la 
	 * colonne courante.
	 * @return La carte objectif de la colonne courante.
	 * 
	 */
	

	public String getObjectif() {
		return objectif;
	}


	/**
	 * 
	 * Setter permettant d'initialiser la carte objectif de la 
	 * colonne courante.
	 * 
	 * @param objectif La carte objectif de la colonne courante.
	 */

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	/**
	 * 
	 * Getter permettant de récupérer la capacité spéciale immédiate
	 * de la carte courante (null si pas de capacité).
	 * 
	 * @return La capacité spéciale immédiate de la carte courante (null si pas de capacité).
	 */
	
	

	public String getCs() {
		return cs;
	}


	/**
	 * 
	 * Setter permettant d'initialiser la capacité spéciale immédiate
	 * de la carte courante (null si pas de capacité).
	 * @param cs La capacité spéciale immédiate de la carte courante (null si pas de capacité).
	 */
	

	public void setCs(String cs) {
		this.cs = cs;
	}


	//Message OJECTO
	
	
	/**
	 * 
	 * Getter permettant de récupérer la variable qui indique si
	 * un objectif est réalisé ou non.
	 * 
	 * @return La variable qui indique si un objectif est réalisé ou non.
	 * 
	 */
	
	public String getOr() {
		return or;
	}

	/**
	 * 
	 * Setter permettant d'initialiser la variable qui indique si
	 * un objectif est réalisé ou non.
	 * 
	 * @param or La variable qui indique si un objectif est réalisé ou non.
	 */
	

	public void setOr(String or) {
		this.or = or;
	}

	/**
	 * 
	 * Getter permettant de récupérer la liste des couleurs de 
	 * chaque joueur ayant remporté l'objectif.
	 * 
	 * @return La liste des couleurs de chaque joueur ayant remporté l'objectif.
	 */


	public String getListes() {
		return listec;
	}

	/**
	 * 
	 * Setter permettant d'initialiser la liste des couleurs de 
	 * chaque joueur ayant remporté l'objectif.
	 * 
	 * @param listes La liste des couleurs de chaque joueur ayant remporté l'objectif.
	 */
	

	public void setListes(String listes) {
		this.listes = listes;
	}

	/**
	 * 
	 * Getter permettant de récupérer l'identifiant de la nouvelle partie.
	 * 
	 * @return L'identifiant de la nouvelle partie.
	 */

	public String getIdnp() {
		return idnp;
	}


	/**
	 * 
	 * Setter permettant d'initialiser l'identifiant de la nouvelle partie.
	 * 
	 * @param idnp L'identifiant de la nouvelle partie.
	 */

	public void setIdnp(String idnp) {
		this.idnp = idnp;
	}


	/**
	 * 
	 * Getter permettant de récupérér le nombre de messages dans l’étape 
	 * de restauration pour le joueur courant.
	 * 
	 * @return Le nombre de messages dans l’étape de restauration pour le joueur courant.
	 */
	

	public int getNbm() {
		return nbm;
	}

	/**
	 * 
	 * Setter permettant d'initialiser le nombre de messages dans l’étape 
	 * de restauration pour le joueur courant.
	 * 
	 * @param nbm Le nombre de messages dans l’étape de restauration pour le joueur courant.
	 */
	

	public void setNbm(int nbm) {
		this.nbm = nbm;
	}

	/**
	 * 
	 * Getter permettant de récupérer le numéro du message dans l’étape de 
	 * restauration pour le joueur courant.
	 * 
	 * 
	 * @return Le numéro du message dans l’étape de restauration pour le joueur courant.
	 */

	public int getNme() {
		return nme;
	}

	/**
	 * 
	 * Setter permettant d'initialiser le numéro du message dans l’étape de 
	 * restauration pour le joueur courant.
	 * 
	 * @param nme Le numéro du message dans l’étape de restauration pour le joueur courant.
	 */
	

	public void setNme(int nme) {
		this.nme = nme;
	}

	/**
	 * 
	 * Getter permettant de récupérer le message tel que défini dans ce protocole.
	 * 
	 * @return Le message tel que défini dans ce protocole.
	 */


	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * Setter permettant d'initialiser le message tel que défini dans ce protocole.
	 * 
	 * @param message Le message tel que défini dans ce protocole.
	 */
	

	public void setMessage(String message) {
		this.message = message;
	}



}
