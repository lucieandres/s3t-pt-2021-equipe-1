package network;

public class Message {

	private MessageType type;
	private String idp;
	private String ip;
	private int port;
	private String nom;
	private int nbj;
	private int nbjrm;
	private int nbjvm;
	private String status;
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
	private String or;
	private String listes;
	private String idnp;
	private int nbm;
	private int nme;
	private String message;
	
	
	//LA METHODE CI DESSOUS EST ENCORE INCOMPLÈTE, IL RESTE NOTAMMENT
	//BEAUCOUP D'EXCEPTIONS ET DE CAS PARTICULIERS A TRAITER.
	public Message(String msg) throws MessageException {
		if (!msg.substring(3,4).equals("-"))
			throw new MessageException(msg + " mal formate");
					
		String mtype = msg.substring(0,3);
		msg = msg.substring(4);
		String[] vars = msg.split("-");
		switch(mtype) {
		
			//LES MESSAGES ÉCHANGÉS POUR LA CRÉATION D'UNE PARTIE :
			
			case "ACP":
				// decode le message ACP
				
				if (vars == null || vars.length!=8)
					throw new MessageException(msg + " ACP : Nombre d'arguments invalides.");
				
				type = MessageType.ACP;
				idp = new String(vars[0]);
				ip = new String(vars[1]);
			    port = Integer.parseInt(vars[2]);
				nom = new String(vars[3]);
				// exception si non int
				nbj = Integer.parseInt(vars[4]);
				nbjrm = Integer.parseInt(vars[5]);
				nbjvm = Integer.parseInt(vars[6]);
				status = new String(vars[7]);
				
				break;
				
			case "AMP":	
				//decode le message AMP
				if (vars == null || vars.length!=10)
					throw new MessageException(msg + " AMP : Nombre d'arguments invalides.");
				
				type = MessageType.AMP;
				idp = new String(vars[0]);
				ip = new String(vars[1]);
			    port = Integer.parseInt(vars[2]);
				nom = new String(vars[3]);
				nbj = Integer.parseInt(vars[4]);
				nbjrm = Integer.parseInt(vars[5]);
				nbjvm = Integer.parseInt(vars[6]);
				nbjrc = Integer.parseInt(vars[7]);
				nbjvc = Integer.parseInt(vars[8]);
				status = new String(vars[9]);
				
				break;
				
			case "RUP":	
				//decode le message RUP
				if (vars == null || vars.length!=2)
					throw new MessageException(msg + " RUP : Nombre d'arguments invalides.");
				
				type = MessageType.RUP;
				typep = new String(vars[0]);
				taillep = Integer.parseInt(vars[1]);
			    
				break;
				
			case "DCP":
				//decode le message DCP
				if (vars == null || vars.length!=3)
					throw new MessageException(msg + " DCP : Nombre d'arguments invalides.");
				
				type = MessageType.DCP;
				nomj = new String(vars[0]);
				typej = new String(vars[1]);
				idp = new String(vars[2]);
			
				break;
				
			case "ADP":
				//decode le message ADP
				if (vars == null || vars.length!=2)
					throw new MessageException(msg + " ADP : Nombre d'arguments invalides.");
				
				type = MessageType.ADP;
				idp = new String(vars[0]);
				idj = new String(vars[1]);
				
				break;
				
			case "RDP":
				//decode le message RDP
				if (vars == null || vars.length!=1)
					throw new MessageException(msg + " RDP : Nombre d'arguments invalides.");
				
				type = MessageType.RDP;
				idp = new String(vars[0]);
				
				break;
				
			case "ADJ":
				//decode le message ADJ
				if (vars == null || vars.length!=1)
					throw new MessageException(msg + " ADJ : Nombre d'arguments invalides.");
				
				type = MessageType.ADJ;
				idp = new String(vars[0]);
				
				break;
			
			//LES MESSAGES ÉCHANGÉS POUR L'INITIALISATION D'UNE PARTIE CRÉÉR ET COMPLÈTE :
				
			case "ILP":
				//decode le message ILP
				if (vars == null || vars.length!=3)
					throw new MessageException(msg + " ILP : Nombre d'arguments invalides.");
				
				type = MessageType.ILP;
				listej = new String(vars[0]);
				listec = new String(vars[1]);
				idp = new String(vars[2]);
				
				break;
			
			case "RTC":
				//decode le message RTC
				if (vars == null || vars.length!=2)
					throw new MessageException(msg + " RTC : Nombre d'arguments invalides.");
				
				type = MessageType.RTC;
				lcarte = new String(vars[0]);
				idp = new String(vars[1]);
				
				break;
			
			//LES MESSAGES ÉCHANGÉS DURANT UNE PARTIE :	
				
			case "ILM":
				//decode le message ILM
				if (vars == null || vars.length!=3)
					throw new MessageException(msg + " ILM : Nombre d'arguments invalides.");
				
				type = MessageType.ILM;
				lobjectif = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "IDT":
				//decode le message IDT
				if (vars == null || vars.length!=3)
					throw new MessageException(msg + " IDT : Nombre d'arguments invalides.");
				
				type = MessageType.IDT;
				couleur = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
			
			case "JCI":
				//decode le message JCI
				if (vars == null || vars.length!=5)
					throw new MessageException(msg + " JCI : Nombre d'arguments invalides.");
				
				type = MessageType.JCI;
				ci = new String(vars[0]);
				co = Integer.parseInt(vars[1]);
				idp = new String(vars[2]);
				nm = Integer.parseInt(vars[3]);
				idj = new String(vars[4]);
				
				break;
				
			case "ICJ":
				//decode le message ICJ
				if (vars == null || vars.length!=5)
					throw new MessageException(msg + " ICJ : Nombre d'arguments invalides.");
				
				type = MessageType.ICJ;
				couleur = new String(vars[0]);
				co = Integer.parseInt(vars[1]);
				cr = new String(vars[2]);
				idp = new String(vars[3]);
				nm = Integer.parseInt(vars[4]);
				
				break;
				
			case "CCI":
				//decode le message CCI
				if (vars == null || vars.length!=3)
					throw new MessageException(msg + " CCI : Nombre d'arguments invalides.");
				
				type = MessageType.CCI;
				co = Integer.parseInt(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "JCC":
				//decode le message JCC
				if (vars == null || vars.length!=4)
					throw new MessageException(msg + "JCC : Nombre d'arguments invalides.");
				
				type = MessageType.JCC;
				ci = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				idj = new String(vars[3]);
				
				break;
				
			case "RMC":
				//decode le message RMC
				if (vars == null || vars.length!=3)
					throw new MessageException(msg + "RMC : Nombre d'arguments invalides.");
				
				type = MessageType.RMC;
				nc = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
						
				break;
				
			case "ECT":
				//decode le message ECT
				if (vars == null || vars.length!=4)
					throw new MessageException(msg + "ECT : Nombre d'arguments invalides.");
				
				type = MessageType.ECT;
				objectif = new String(vars[0]);
				co = Integer.parseInt(vars[1]);
				idp = new String(vars[2]);
				nm = Integer.parseInt(vars[3]);
						
				break;
				
			case "JCT":
				//decode le message JCT
				if (vars == null || vars.length!=4)
					throw new MessageException(msg + "JCT : Nombre d'arguments invalides.");
				
				type = MessageType.JCT;
				co = Integer.parseInt(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				idj = new String(vars[3]);
						
				break;

			case "ICR":
				//decode le message ICR
				if (vars == null || vars.length!=6)
					throw new MessageException(msg + "ICR : Nombre d'arguments invalides.");
				
				type = MessageType.ICR;
				co = Integer.parseInt(vars[0]);
				cr = new String(vars[1]);
				cs = new String(vars[2]); // --- devra etre traité spécifiquement pour plusieurs cas ---
				or = new String(vars[3]); // VRAI ou FAUX
				if (or == "VRAI") {}
				else if (or == "FAUX") {}
				else {} // à completer
				idp = new String(vars[4]);
				nm = Integer.parseInt(vars[5]);		
				
				break;
				
			case "RMJ":
				//decode le message RMJ
				if (vars == null || vars.length!=3)
					throw new MessageException(msg + "RMJ : Nombre d'arguments invalides.");
				
				type = MessageType.RMJ;
				nc = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);

			case "RRJ":
				//decode le message RRJ
				if (vars == null || vars.length!=3)
					throw new MessageException(msg + "RRJ : Nombre d'arguments invalides.");
				
				type = MessageType.RRJ;
				couleur = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
			case "FDM":
				//decode le message FDM
				if (vars == null || vars.length!=3)
					throw new MessageException(msg + "FDM : Nombre d'arguments invalides.");
				
				type = MessageType.FDM;
				nc = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
			case "ROM":
				//decode le message ROM
				if (vars == null || vars.length!=4)
					throw new MessageException(msg + "ROM : Nombre d'arguments invalides.");
				
				type = MessageType.ROM;
				lobjectif = new String(vars[0]);
				listec = new String(vars[1]);
				idp = new String(vars[2]);
				nm = Integer.parseInt(vars[3]);
				
			case "FDP":
				//decode le message FDP
				if (vars == null || vars.length!=4)
					throw new MessageException(msg + "FDP : Nombre d'arguments invalides.");
				
				type = MessageType.FDP;
				couleur = new String(vars[0]);
				listej = new String(vars[1]);
				listes = new String(vars[2]);
				idp = new String(vars[3]);
				//nt? voir protocole réseau.
				
				break;
			
			//LES MESSAGES ÉCHANGÉS APRÈS LA FIN D'UNE PARTIE:	
				
			case "TLP":
				//decode le message TLP
				if (vars == null || vars.length!=1)
					throw new MessageException(msg + "TLP : Nombre d'arguments invalides.");
				
				type = MessageType.TLP;
				idp = new String(vars[0]);
				
				break;
				
				
			case "RNP":
				//decode le message RNP
				if (vars == null || vars.length!=2)
					throw new MessageException(msg + " RNP : Nombre d'arguments invalides.");
				
				type = MessageType.RNP;
				idp = new String(vars[0]);
				idnp = new String(vars[1]);

				break;
			
			//LES MESSAGES POUR LA RESTAURATION D'UNE PARTIE PRÉALABLEMENT SAUVEGARDÉE:	
			
			case "RLP":
				//decode le message RLP
				if (vars == null || vars.length!=1)
					throw new MessageException(msg + " RLP : Nombre d'arguments invalides.");
				
				type = MessageType.RLP;
				idp = new String(vars[0]);

				break;
				
			case "DRP":
				//decode le message DRP
				if (vars == null || vars.length!=2)
					throw new MessageException(msg + " DRP : Nombre d'arguments invalides.");
				
				type = MessageType.DRP;
				nbm = Integer.parseInt(vars[0]);
				idp = new String(vars[1]);

				break;	
			
			case "TME":
				//decode le message TME
				if (vars == null || vars.length!=2)
					throw new MessageException(msg + " TME : Nombre d'arguments invalides.");
				
				type = MessageType.TME;
				nme = Integer.parseInt(vars[0]);
				message = new String(vars[1]);

				break;
				
			case "FTM":
				//decode le message FTM
				if (vars == null || vars.length!=1)
					throw new MessageException(msg + " FTM : Nombre d'arguments invalides.");
				
				type = MessageType.FTM;
				idp = new String(vars[0]);

				break;
				
			//METTRE EN PAUSE UNE PARTIE:
				
			case "CCP":
				//decode le message CCP
				if (vars == null || vars.length!=1)
					throw new MessageException(msg + " CCP : Nombre d'arguments invalides.");
				
				type = MessageType.CCP;
				idp = new String(vars[0]);

				break;
				
			case "ARP":
				//decode le message ARP
				if (vars == null || vars.length!=1)
					throw new MessageException(msg + " ARP : Nombre d'arguments invalides.");
				
				type = MessageType.ARP;
				idp = new String(vars[0]);

				break;
			
			default:			
				throw new MessageException(mtype + " type de message inconnu.");
		}
	}



	public Message(MessageType type) {
		this.type = type;
	}


	@Override
	public String toString() {
		switch (type) {
			
			//LES MESSAGES ÉCHANGÉS POUR LA CRÉATION D'UNE PARTIE :
		
			case ACP:
				return "ACP-" + idp + "-" + ip + "-" + port + "-" + nom + "-"
					+ nbj + "-" + nbjrm + "-" + nbjvm + "-" + status;
			
			case AMP:
				return "AMP-" + idp + "-" + ip + "-" + port + "-" + nom + "-"
					+ nbj + "-" + nbjrm + "-" + nbjvm + "-" + nbjrc + "-" + nbjvc + "-" + status;
				
			case RUP:
				return "RUP-" + typep + "-" + taillep;
			
			case DCP:
				return "DCP-" + nomj + "-" + typej + "-" + idp;
			
			case ADP:
				return "ADP-" + idp + "-" + idj;
				
			case RDP:
				return "RDP-" + idp;
				
			case ADJ:
				return "ADJ-" + idp;
				
			//LES MESSAGES ÉCHANGÉS POUR L'INITIALISATION D'UNE PARTIE CRÉÉR ET COMPLÈTE :	
				
			case ILP:
				return "ILP-" + listej + "-" + listec + "-" + idp;
				
			case RTC:
				return "RTC-" + lcarte + "-" + idp;
				
			//LES MESSAGES ÉCHANGÉS DURANT UNE PARTIE :	
				
			case ILM:
				return "ILM-" + lobjectif + "-" + idp + "-" + nm;
				
			case IDT:
				return "IDT-" + couleur + "-" + idp + "-" + nm;
				
			case JCI:
				return "JCI-" + ci + "-" + co + "-" + idp + "-" + nm + "-" + idj;
				
			case ICJ:
				return "ICJ-" + couleur + "-" + co + "-" + cr + "-" + idp + "-" + nm;
				
			case CCI:
				return "CCI-" + co + "-" + idp + "-" + nm;
				
			case JCC:
				return "JCC-" + ci + "-" + idp + "-" + nm + "-" + idj;
				
			case RMC:
				return "RMC-" + nc + "-" + idp + "-" + nm;
			
			case ECT:
				return "ECT-" + objectif + "-" + co + "-" + idp + "-" + nm;
				
			case JCT:
				return "JCT-" + co + "-" + idp + "-" + nm + "-" + idj;
				
			case ICR:
				return "ICR-" + co + "-" + cr + "-" + cs + "-" + or + "-" + idp + "-" + nm; 
				
			case RMJ:
				return "RMJ-" + nc + "-" + idp + "-" + nm;
				
			case RRJ:
				return "RRJ-" + couleur + "-" + idp + "-" + nm;
			
			case FDM:
				return "FDM-" + nc + "-" + idp + "-" + nm;
				
			case ROM:
				return "ROM-" + lobjectif + "-" + listec + "-" + idp + "-" + nm;
				
			case FDP:
				return "FDP-" + couleur + "-" + listej + "-" + listes + "-" + idp; // NT? Voir protocole.
				
			//LES MESSAGES ÉCHANGÉS APRÈS LA FIN D'UNE PARTIE:	
			
			case TLP:
				return "TLP-" + idp;	
			
			case RNP:
				return "RNP-" + idp + "-" + idnp;
				
			//LES MESSAGES POUR LA RESTAURATION D'UNE PARTIE PRÉALABLEMENT SAUVEGARDÉE:	
				
			case RLP:
				return "RLP-" + idp;
				
			case DRP:
				return "DRP-" + nbm + "-" + idp;
				
			case TME:
				return "TME-" + nme + "-" + message;
				
			case FTM:
				return "FTM-" + idp;
			
			//METTRE EN PAUSE UNE PARTIE:	
				
			case CCP:
				return "CCP-" + idp;
				
			case ARP:
				return "CCP-" + idp;
			
			default:
				return "Unknwon message";
		}
	}



	public MessageType getType() {
		return type;
	}



	public void setType(MessageType type) {
		this.type = type;
	}



	public String getIdp() {
		return idp;
	}



	public void setIdp(String idp) {
		this.idp = idp;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public int getNbj() {
		return nbj;
	}



	public void setNbj(int nbj) {
		this.nbj = nbj;
	}



	public int getNbjrm() {
		return nbjrm;
	}



	public void setNbjrm(int nbjrm) {
		this.nbjrm = nbjrm;
	}



	public int getNbjvm() {
		return nbjvm;
	}



	public void setNbjvm(int nbjvm) {
		this.nbjvm = nbjvm;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getNbjrc() {
		return nbjrc;
	}



	public void setNbjrc(int nbjrc) {
		this.nbjrc = nbjrc;
	}



	public int getNbjvc() {
		return nbjvc;
	}



	public void setNbjvc(int nbjvc) {
		this.nbjvc = nbjvc;
	}



	public String getTypep() {
		return typep;
	}



	public void setTypep(String typep) {
		this.typep = typep;
	}



	public int getTaillep() {
		return taillep;
	}



	public void setTaillep(int taillep) {
		this.taillep = taillep;
	}
	
	
	public String getNomj() {
		return nomj;
	}



	public void setNompj(String nomj) {
		this.nomj = nomj;
	}


	public String getTypej() {
		return typej;
	}



	public void setTypej(String typej) {
		this.typej = typej;
	}



	public String getIdj() {
		return idj;
	}



	public void setIdj(String idj) {
		this.idj = idj;
	}



	public String getListej() {
		return listej;
	}



	public void setListej(String listej) {
		this.listej = listej;
	}



	public String getListec() {
		return listec;
	}



	public void setListec(String listec) {
		this.listec = listec;
	}



	public String getLcarte() {
		return lcarte;
	}



	public void setLcarte(String lcarte) {
		this.lcarte = lcarte;
	}



	public String getLobjectif() {
		return lobjectif;
	}



	public void setLobjectif(String lobjectif) {
		this.lobjectif = lobjectif;
	}



	public int getNm() {
		return nm;
	}



	public void setNm(int nm) {
		this.nm = nm;
	}



	public String getCouleur() {
		return couleur;
	}



	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}



	public String getCi() {
		return ci;
	}



	public void setCi(String ci) {
		this.ci = ci;
	}



	public int getCo() {
		return co;
	}



	public void setCo(int co) {
		this.co = co;
	}



	public String getCr() {
		return cr;
	}



	public void setCr(String cr) {
		this.cr = cr;
	}



	public String getNc() {
		return nc;
	}



	public void setNc(String nc) {
		this.nc = nc;
	}



	public String getObjectif() {
		return objectif;
	}



	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}



	public String getCs() {
		return cs;
	}



	public void setCs(String cs) {
		this.cs = cs;
	}



	public String getOr() {
		return or;
	}



	public void setOr(String or) {
		this.or = or;
	}



	public String getListes() {
		return listes;
	}



	public void setListes(String listes) {
		this.listes = listes;
	}



	public String getIdnp() {
		return idnp;
	}



	public void setIdnp(String idnp) {
		this.idnp = idnp;
	}



	public int getNbm() {
		return nbm;
	}



	public void setNbm(int nbm) {
		this.nbm = nbm;
	}



	public int getNme() {
		return nme;
	}



	public void setNme(int nme) {
		this.nme = nme;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



}
