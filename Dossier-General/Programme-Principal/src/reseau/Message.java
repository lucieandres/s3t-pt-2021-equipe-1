package reseau;

import java.util.ArrayList;
import java.util.List;

import cartes.CarteObjectif;

/**
 * 
 * Cette classe répertorie les différents messages réseaux utilisés lors du protocole. A partir de cette classe, un client
 * et un serveur sont capables de décoder chaque message réseau, ainsi que de récuperer les variables correspondantes.
 * 
 * @author S3T-G1
 *
 */

public class Message {

	private TypeDeMessage type;// définit le message réseau traité
	private String idp;//identifiant unique de partie. La lettre P suivie d’un entier entre 0 et 9 999 999 (exemple P258456).
	private String ip;//l’IP pour rejoindre la partie sous la forme xxx.xxx.xxx.xxx (où xxx pourra être sur 1, 2 ou 3 digits en fonction de la valeur)
	private int port;//le port pour rejoindre la partie sous la forme d’un entier entre 1024 et 65535
	private String nom;// le nom de la partie, sous la forme d’une chaine de caractères pouvant contenir des lettres majuscules et 
	//minuscule (accentuées ou non), des nombres et les caractères spéciaux apostrophe «’», espace « » et souligné bas «_».
	private int nbj;//Nombre de joueurs souhaités sur la partie
	private int nbjrm;//Nombre de joueurs réels maximum sur la partie (ne peut pas être supérieur à NBJ)
	private int nbjvm;// Nombre de joueurs virtuels (BOT) maximum sur la partie (ne peut pas être supérieur à NBJ)
	private String statut;//un des choix suivants : « ATTENTE » : en attente de membres pour débuter la partie ; « ANNULEE » : la partie a été annulée. On ne peut plus s’y connecter. ; « COMPLETE » : la partie est complète et a démarré. On ne peut plus s’y connecter. ; « TERMINEE » : la partie est finie depuis moins de 1 minute. Après 1 minute la partie n'existe plus.
	private int nbjrc;//Nombre de joueurs réels connectés à la partie (ne peut pas être supérieur à NBJRM)
	private int nbjvc;//Nombre de joueurs virtuels connectés à la partie (ne peut pas être supérieur à NBJVM)
	private String typep;//  le type de la partie recherchée. Peut prendre une des valeurs suivantes : « JRU » : Joueurs réels uniquement ; « BOTU » : Joueurs virtuels uniquement ; « MIXTE » : Joueurs réels ou virtuels
	private int taillep;//le nombre maximum de joueur dans la partie recherchée. Peut prendre une des quatre valeurs suivante 2,3,4,5 ou 6
	private String nomj;// le nom du joueur
	private String typej;// le type du joueur soit « JR » soit « BOT »
	private String idj;//un identifiant unique caractérisant le joueur. La lettre J suivie d’un entier entre 0 et 9999 (exemple P258).
	private String listej;//a liste des noms de joueurs séparés par des « , ». La liste est ordonnée en commençant par le joueur 1 
	//jusqu’au joueur n (2≤n≤6). Ici le joueur 1 désigne le premier joueur de la partie (donc de la manche 1). Et donc les 
	//joueurs sont déjà organisés dans l’ordre de jeu (« sens des aiguilles d’une montre »).
	private String listec;// la liste des couleurs de chaque joueur dans le même ordre que la liste précédente. Chaque couleur est 
	//identifiée par le code couleur (3 caractères) des cartes « influence » et les couleurs sont séparées par des « , ».
	private String lcarte;//les trois cartes « influence » du joueur séparées par des « , » et décrite selon le codage présenté précédemment.
	private List<CarteObjectif> lobjectif;//la liste des cartes « objectif » de la manche séparées par des « , » et décrite selon le codage présenté précédemment. Elles sont fournies dans l’ordre des colonnes du plateau.
	private int nm;// un entier dans l’intervalle [1 ; 6] servant d’identifiant de la manche courante
	private String couleur;// indique la couleur du joueur courant. La couleur est identifiée par le code couleur (3 caractères) des cartes « influence »
	private String ci;// la carte choisi par le joueur. La carte doit obligatoirement être une carte de la main du joueur
	private int co;// le numéro (entre 1 et 6) de la colonne « objectif » où est jouée la carte. La carte doit être jouable sur cette colonne
	private String cr;//si la colonne ne contenait aucune carte non retournée, on indiquera la valeur « NUL » aucun message supplémentaire n’est envoyé on passe à l’étape de pioche. 
	//Si une carte non retournée est présente, on indique l’identifiant de cette carte. Attention, pour le moment cette carte n’a pas d’effet, on va devoir gérer les capacité spéciale au cas par cas.
	//Les cartes suivantes non pas de capacité spéciale immédiate, on indique donc à tous qu’il n’y a pas d’effet : Alchimiste, Cardinal, Dragon, Ecuyer, Ermite, Juliette, Magicien, Maître d’armes, Marchand, Mendiant, Petit Géant, Prince, Reine, Roi, Roméo, Seigneur, Sorcière, Sosie, Traître, Trois Mousquetaires, Troubadour
	//Pour la carte Assassin, pas de requête nécessaire on informe directement de l’effet.
	//Pour la carte Cape d’invisibilité, une séquence de requêtes supplémentaire est nécessaire.
	//Pour la carte Explorateur, une séquence de requêtes supplémentaire est nécessaire
	//Pour la carte Tempête, pas de requête nécessaire on informe directement de l’effet.
	//Pour la carte Traître, une séquence de requêtes supplémentaire est nécessaire
	private String nc;//La nouvelle carte ajoutée à la main du joueur.
	private String objectif;// la carte objectif de la colonne courante
	private String cs;// La capacité spéciale immédiate de la carte (les capacités spéciales non immédiates sont considérés comme « NUL »
	//Pour les cartes suivante on indique « NUL » : Alchimiste, Cardinal, Dragon, Ecuyer, Ermite, Juliette, Magicien,Maître d’armes, Marchand, Mendiant, Petit Géant, Prince, Reine, Roi, Roméo, Seigneur, Sorcière, Sosie,Traître, Trois Mousquetaires, Troubadour.
	//Pour la carte Assassin on indiquera l’identifiant de la carte détruite.
	//Pour la carte Cape d’invisibilité « CARTE » si le joueur a ajouté une carte sous la cape d’invisibilité ou « VIDE » s’il n’a rien ajouté.
	// Pour la carte Explorateur, on indiquera le numéro de la colonne sur laquelle se déplace l’explorateur. Normalement, l’explorateur se déplace sur la colonne suivante mais attention au cycle (colonne n vers colonne 1) et aussi aux colonnes fermées. Attention, l’arrivé de l’explorateur dans une nouvelle colonneentrainera une nouvelle séquence de message à partir du point 4 (ICJ).
	//Pour la carte Tempête, on indiquera « FERMEE »
	//Pour la carte Traître, on indiquera le message suivant OJECTO:COL:OBJECTC:ORC
	//OJECTO : la carte objectif de la colonne actuelle (celle de CO),
	//COL : le numéro de la colonne choisie par le propriétaire de la carte traite (COL ≠ CO)
	//OBJECTC : la carte objectif de la colonne échangée (celle de COL)
	//ORC : « VRAI » si après si après l’échange des objectifs, le nouvel objectif de la colonne COL est réalisé, « FAUX » si ce n’est pas le cas.
	//
	//Message OJECTO
	private String or;// « VRAI » si après l’effet de la carte retournée l’objectif de la colonne est réalisé, « FAUX » si ce n’est pas le cas
	private String listes;// la liste des score séparés par des « , » dans le même ordre que la liste des joueurs.
	private String idnp;//l’identifiant de la nouvelle partie.
	private int nbm;//nombre de messages dans l’étape de restauration pour le joueur courant
	private int nme;//: numéro du message dans l’étape de restauration pour le joueur courant
	private String message;// le message tel que défini dans ce protocole. Attention, les IDJ et les IDP doivent être mise à jour pour correspondre à la partie courante
	
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
			
			case "ACP": // decode le message ACP
				//PP : Annoncer la création d’une partie (PP -> groupe multicast)
				//PP : rejoindre le groupe UDP multicast ayant pour adresse « 224.7.7.7 » et pour port « 7777 ».
				// : annoncer la création d’une nouvelle partie en notifiant les membres du groupe « ACP-IDP-IP-PORT-NOM-NBJ-NBJRMNBJVM-STATUT ». 

				
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
				
			case "AMP":	//decode le message AMP
				//PP : Annoncer la mise à jour d’une partie (PP -> groupe multicast)
				//PP : annoncer une mise à jour d’une partie déjà annoncée en notifiant les membres du groupe « AMP-IDP-IP-PORT-NOM-NBJ-NBJRM-NBJVM-NBJRC-NBJVC-STATUT ». 
				//Ce message peut être envoyé pour donner suite à une mise à jour des informations de la partie, ou en réponse à une recherche de partie.
			
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
				
			case "RUP":	//decode le message RUP
				//IDJR/BOT : Rechercher une partie (IDJR/BOT -> groupe multicast)
				//Pour connaitre les parties présentes, il faudra rejoindre le même groupe UDP multicast que le PP et envoyer le message de 
				//recherche « RUP-TYPEP-TAILLEP ». En réponse, les PP retourneront une annonce mise à jour de la partie. On ne doit plus 
				//rechercher de partie une fois qu’on a reçu une acceptation à une demande de partie.
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " RUP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RUP;
				typep = new String(vars[0]);
				taillep = Integer.parseInt(vars[1]);
			    
				break;
				
			case "DCP"://decode le message DCP
				// IDJR/BOT : Rejoindre à une partie (IDJR/BOT -> PP)
				//La connexion se fait en TCP sur l’IP et le PORT d’une partie. IDJR/BOT : demande connexion à une partie « DCP-NOM-TYPE-IDP »
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " DCP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.DCP;
				nomj = new String(vars[0]);
				typej = new String(vars[1]);
				idp = new String(vars[2]);
			
				break;
				
			case "ADP"://decode le message ADP
				// PP : Accepter ou refuser un joueur (PP -> le IDJR/BOT ayant fait la demande)
				//PP : Accepter dans la partie « ADP-IDP-IDJ »
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " ADP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ADP;
				idp = new String(vars[0]);
				idj = new String(vars[1]);
				
				break;
				
			case "RDP"://decode le message RDP
				//PP : Refuser dans la partie « RDP-IDP »
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " RDP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RDP;
				idp = new String(vars[0]);
				
				break;
				
			case "ADJ"://decode le message ADJ
				// PP : Annoncer d’une déconnexion (PP -> tous les IDJR/BOT sauf celui ayant disparu)
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " ADJ : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ADJ;
				idp = new String(vars[0]);
				
				break;
			
			//LES MESSAGES ECHANGES POUR L'INITIALISATION D'UNE PARTIE CREER ET COMPLETE :
				
			case "ILP"://decode le message ILP
				//PP : Initialiser la partie (PP -> tous les IDJR/BOT)
				//PP : initialiser la partie « ILP-LISTEJ-LISTEC-IDP » en fournissant la liste des joueurs et l’identifiant unique de partie
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " ILP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ILP;
				listej = new String(vars[0]);
				listec = new String(vars[1]);
				idp = new String(vars[2]);
				
				break;
			
			case "RTC"://decode le message RTC
				//PP : Recevoir les 3 cartes de sa main (PP -> chaque IDJR/BOT indépendamment)
				//PP : distribuer les 3 cartes de la main de départ à chaque joueur. Les cartes « influence » du joueur ne seront connues que du 
				//PP et du joueur à qui elles appartiennent « RTC-LCARTE-IDP »
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " RTC : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RTC;
				lcarte = new String(vars[0]);
				idp = new String(vars[1]);
				
				break;
			
			//LES MESSAGES ECHANGES DURANT UNE PARTIE :	
				
			case "ILM"://decode le message ILM
				// PP : Initialiser de la manche (PP -> tous les IDJR/BOT)
				//PP : informer l’ensemble des joueurs du début de la manche « ILM-LOBJECTIF-IDP-NM ». On fournit le numéro de la manche 
				//courante.
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " ILM : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ILM;
				lobjectif = new ArrayList<CarteObjectif>();
				String[] vars2 = vars[0].split(",");
				// A CHANGER
				for (int i=0; i<vars2.length;i++) {
					CarteObjectif carteObjectif = new CarteObjectif(vars2[i].substring(1,2),Integer.parseInt(vars2[i].substring(3)));
					lobjectif.add(carteObjectif);
				}
				
				//
				
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "IDT"://decode le message IDT
				//PP : Initialiser un tour (PP -> tous les IDJR/BOT)
				//PP : informer l’ensemble des joueurs du début du tour « IDT-COULEUR-IDP-NM »
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " IDT : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.IDT;
				couleur = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
			
			case "JCI"://decode le message JCI
				// IDJR/BOT : Jouer une carte « influence » (IDJR/BOT -> PP)
				//PP : Le joueur courant choisi la carte qu’il souhaite jouer et sa colonne de destination. « JCI-CI-CO-IDP-NM-IDJ » On fournit le 
				//numéro du tour courant.
				if (vars == null || vars.length!=5)
					throw new ExceptionMessage(msg + " JCI : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.JCI;
				ci = new String(vars[0]);
				co = Integer.parseInt(vars[1]);
				idp = new String(vars[2]);
				nm = Integer.parseInt(vars[3]);
				idj = new String(vars[4]);
				
				break;
				
			case "ICJ"://decode le message ICJ
				//PP : Informer l’ensemble des joueurs de la carte jouée (PP -> tous les IDJR/BOT)
				//PP : informer l’ensemble des joueurs de la carte qui a été jouée. « ICJ-COULEUR-CO-CR-IDP-NM ». Attention, même le joueur 
				//courant (celui qui a joué la carte reçoit l’information). Attention, ce message peut aussi être généré par le déplacement d’un 
				//explorateur.
				if (vars == null || vars.length!=5)
					throw new ExceptionMessage(msg + " ICJ : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ICJ;
				couleur = new String(vars[0]);
				co = Integer.parseInt(vars[1]);
				cr = new String(vars[2]);
				idp = new String(vars[3]);
				nm = Integer.parseInt(vars[4]);
				
				break;
				
			case "CCI"://decode le message CCI
				//PP : demander au joueur la carte qu’il souhaite mettre sous la cape d’invisibilité (PP -> l’IDJR/BOT)
				//PP : demander au joueur la carte qu’il souhaite mettre sous la cape d’invisibilité. « CCI-CO-IDP-NM ». Attention, le 
				//message est uniquement à destination du propriétaire de la carte cape d’invisibilité.
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + " CCI : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.CCI;
				co = Integer.parseInt(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "JCC"://decode le message JCC
				//IDJR/BOT : Jouer une carte « influence » cachée (IDJR/BOT -> PP)
				//PP : Le joueur courant choisi la carte qu’il souhaite jouer sous la carte cape d’invisibilité. « JCC-CI-IDP-NM-IDJ » On 
				//fournit le numéro du tour courant.
				if (vars == null || vars.length!=4)
					throw new ExceptionMessage(msg + "JCC : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.JCC;
				ci = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				idj = new String(vars[3]);
				
				break;
				
			case "RMC"://decode le message RMC
				//remplir la main du joueur (PP -> l’IDJR/BOT)
				//PP : remplir la main du joueur s’il a mis une carte sous la cape d’invisibilité. « RMC-NC-IDP-NM ». Attention, le 
				//message est uniquement à destination du propriétaire de la carte cape d’invisibilité. Ce message n’a pas lieu si le 
				//joueur n’a pas mis de carte sous sa cape d’invisibilité.
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + "RMC : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RMC;
				nc = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
						
				break;
				
			case "ECT"://decode le message ECT
				//PP : demander au joueur la colonne « Objectifs » (PP -> l’IDJR/BOT)
				//PP : demander au joueur la carte objectifs qu’il souhaite échanger avec la carte de sa colonne. « ECT-OBJECTIF-COCR-IDP-NM ». Attention, le message est uniquement à destination du propriétaire de la carte traite.
				if (vars == null || vars.length!=4)
					throw new ExceptionMessage(msg + "ECT : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ECT;
				objectif = new String(vars[0]);
				co = Integer.parseInt(vars[1]);
				idp = new String(vars[2]);
				nm = Integer.parseInt(vars[3]);
						
				break;
				
			case "JCT"://decode le message JCT
				//IDJR/BOT : Indiquer la colonne (IDJR/BOT -> PP)
				//PP : Le joueur courant choisi la colonne avec laquelle il inverse la carte objectif. « JCT-CI-IDP-NM-IDJ » On fournit le numéro 
				//du tour courant.
				if (vars == null || vars.length!=4)
					throw new ExceptionMessage(msg + "JCT : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.JCT;
				co = Integer.parseInt(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				idj = new String(vars[3]);
						
				break;

			case "ICR"://decode le message ICR
				//PP : Informer l’ensemble des joueurs des effets de la carte retournée (PP -> tous les IDJR/BOT)
				//PP : informer l’ensemble des joueurs des effets de la carte retournée. « ICR-CO-CR-CS-IDP-NM ». Attention, même le joueur 
				//courant (celui qui a joué la carte reçoit l’information).
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
				
			case "RMJ"://decode le message RMJ
				// PP : remplir la main du joueur (PP -> l’IDJR/BOT)
				//PP : remplir la main du joueur qui a joué durant ce tour. « RMJ-NC-IDP-NM ». Attention, le message est uniquement à 
				//destination du joueur qui a joué une carte durant ce tour.
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + "RMJ : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RMJ;
				nc = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);

				break;
				
			case "RRJ"://decode le message RRJ
				// PP : Informer l’ensemble des joueurs de la mise à jour de la réserve (PP -> tous les IDJR/BOT)
				//PP : La réserve du joueur courant est vide, celle-ci est alors rempli avec les cartes de sa défausse. « RRJ-COULEUR-IDP-NM ».
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + "RRJ : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RRJ;
				couleur = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "FDM"://decode le message FDM
				//PP : Informer l’ensemble des joueurs de la fin de la manche (PP -> tous les IDJR/BOT)
				//PP : Tous les objectifs sont réalisés. « FDM-NC-IDP-NM ». 
				if (vars == null || vars.length!=3)
					throw new ExceptionMessage(msg + "FDM : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.FDM;
				nc = new String(vars[0]);
				idp = new String(vars[1]);
				nm = Integer.parseInt(vars[2]);
				
				break;
				
			case "ROM"://decode le message ROM
				//PP : Informer l’ensemble des gains d’objectifs (PP -> tous les IDJR/BOT)
				//PP : informer l’ensemble des joueurs du résultat de la manche courante. « ROM-LOBJECTIF-LISTEC -IDP-NM ». Attention, 
				//même le joueur courant (celui qui a joué la carte reçoit l’information).
				if (vars == null || vars.length!=4)
					throw new ExceptionMessage(msg + "ROM : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.ROM;
				lobjectif = new String(vars[0]);
				listec = new String(vars[1]);
				idp = new String(vars[2]);
				nm = Integer.parseInt(vars[3]);
				
				break;
				
			case "FDP"://decode le message FDP
				//PP : Indiquer la fin de la partie (PP -> tous les IDJR/BOT)
				//PP : Indiquer la fin de la partie « FDP-COULEUR-LISTEJ-LISTES-IDP-NT 
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
				
			case "TLP"://decode le message TLP
				//PP : Terminer la partie (PP -> tous les IDJR/BOT)
				//PP : termine la partie « TLP-IDP ». Les joueurs seront déconnectés. 
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + "TLP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.TLP;
				idp = new String(vars[0]);
				
				break;
				
				
			case "RNP"://decode le message RNP
				//PP : Relancer une nouvelle partie avec les mêmes joueurs et mêmes paramètres (PP -> tous les IDJR/BOT) : 
				//optionnel
				//PP : Relance une partie « RNP-IDP-IDNP ». Les joueurs attendent l’initialisation d’une nouvelle partie. On occulte la phase de 
				//création de la partie (on garde les mêmes paramètres, même joueurs, …). Pensez changer le STATUT de l’ancienne partie et 
				//d’annoncer la nouvelle. Peut être utilisé uniquement si la partie est terminée (pas possible si la partie est annulée).
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " RNP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RNP;
				idp = new String(vars[0]);
				idnp = new String(vars[1]);

				break;
			
			//LES MESSAGES POUR LA RESTAURATION D'UNE PARTIE PREALABLEMENT SAUVEGARDEE:	
			
			case "RLP"://decode le message RLP
				//Informer les joueurs de la restauration d’une partie (PP -> tous les IDJR/BOT) « RLP-IDP »
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " RLP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.RLP;
				idp = new String(vars[0]);

				break;
				
			case "DRP"://decode le message DRP
				//nformer le joueur courant du début de la restauration d’une partie (PP -> l’IDJR/BOT courant) « DRPNBM-IDP »
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " DRP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.DRP;
				nbm = Integer.parseInt(vars[0]);
				idp = new String(vars[1]);

				break;	
			
			case "TME"://decode le message TME
				//Transmission des messages un par un (PP -> l’IDJR/BOT courant) « TME-NM-MESSAGE »
				//L’ensemble des messages sont ainsi transmis à chaque joueur. Le dernier message enregistré n’est pas transmis. Attention, 
				//la liste des messages ne contient que les messages de type « PP -> tous » ou « PP -> l’IDJR/BOT courant ». 
				
				if (vars == null || vars.length!=2)
					throw new ExceptionMessage(msg + " TME : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.TME;
				nme = Integer.parseInt(vars[0]);
				message = new String(vars[1]);

				break;
				
			case "FTM"://decode le message FTM
				//Fin de transmission des messages un par un (PP -> l’IDJR/BOT courant) « FTM-NBM-IDP »

				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " FTM : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.FTM;
				idp = new String(vars[0]);

				break;
				
			//METTRE EN PAUSE UNE PARTIE:
				
			case "CCP"://decode le message CCP
				//PP : Mettre en pause la partie (PP -> tous les IDJR/BOT)
				//Durant une partie, un joueur peut mettre en pause le jeu, les joueurs en sont notifiés et affectés “CCP-IDP”. La partie sera 
				//alors en “pause”. Ce qui veut dire que tant que le message de reprise n’est pas transmis, aucun joueurs/bot ne peut faire 
				//d’actions de jeu que ce soit via le plateau ou via leur propre interface. On notera que les actions d’aide ou de configuration
				//sont toujours possible. Uniquement la partie devra être neutralisée. Il n’est pas possible de mettre en pause une partie qui 
				//l’est déjà
				if (vars == null || vars.length!=1)
					throw new ExceptionMessage(msg + " CCP : Nombre d'arguments invalides.");
				
				type = TypeDeMessage.CCP;
				idp = new String(vars[0]);

				break;
				
			case "ARP"://decode le message ARP
				//PP : Reprendre la partie (PP -> tous les IDJR/BOT)
				//Durant une partie, un joueur peut reprendre le jeu, les joueurs en sont notifiés et affectés “ARP”. La partie reprend 
				//normalement dans l’état qu’elle était avant la mise en pause. Ce message n’est possible que si la partie est en pause.
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
				//METHODE QUI TRAITE LE LOBJECTIF
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
	

	public List<CarteObjectif> getLobjectif() {
		return lobjectif;
	}


	/**
	 * 
	 * Setter permettant d'initialiser la liste des cartes objectifs de la manche.
	 * 
	 * @param lobjectif La liste des cartes objectifs de la manche.
	 */
	

	public void setLobjectif(List<CarteObjectif> lobjectif) {
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
