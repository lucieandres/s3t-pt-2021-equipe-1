package interfaces;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import joueur.Joueur;
import moteur.Data;

/**
 * Cette classe permet de créer une partie.
 * C'est sur cette interface qu'on peut choisir le nombre de joueurs, rentrer son pseudo et lancer la partie.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class InterfaceFin extends InterfaceBase {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	Label titre;
	Label pseudo;
	Button boutonMenu;
	Button boutonRejouerLocal;
	Button boutonRejouerLigne;
	
	HBox HBHaut;
	HBox HBMilieu;
	HBox HBBas;
	
	VBox VBDroite;
	VBox VBGauche;

	VBox VBJoueur[];	
	HBox HBClassement[];
	HBox HBPseudo[];
	HBox HBCouleur[];
	HBox HBNbCarte[];
	HBox HBScore[];
	
	/**
     *  Ce constructeur permet de créer tous les éléments de l'interface, c'est-à-dire le titre
     *  la glissière, la zone d'insertion de texte, le bouton retour et le bouton pour lancer la partie.
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
	public InterfaceFin(GestionnaireInterface gi){
		super();
		Data data =  gi.getData();
		
		titre = new Label("Score");
		titre.setFont(Font.font("Pristina", FontWeight.BOLD,80));
		boutonMenu = new Button("Menu");
		boutonMenu.setOnAction(e -> gi.afficherEcran(GI.InterfaceMap.get("menu")));
		boutonMenu.setPrefWidth(gi.screenBounds.getWidth()*0.08);
		boutonMenu.setFont(Font.font("Comic Sans MS", 20));
	
		HBHaut = new HBox();
		HBHaut.getChildren().addAll(titre, boutonMenu);
		HBHaut.setAlignment(Pos.TOP_CENTER);
		HBHaut.setMinSize(0, gi.screenBounds.getWidth());
		this.setTop(HBHaut);
		
		boutonRejouerLocal = new Button("Rejouer en Local");
		boutonRejouerLocal.setOnAction(e -> gi.afficherEcran(GI.InterfaceMap.get("creerPartie")));
		boutonRejouerLocal.setPrefWidth(gi.screenBounds.getWidth()*0.08);
		boutonRejouerLocal.setFont(Font.font("Comic Sans MS", 20));
		
		boutonRejouerLigne = new Button("Rejouer en Ligne");
		boutonRejouerLigne.setOnAction(e -> gi.afficherEcran(GI.InterfaceMap.get("creerPartieEnLigne")));
		
		
		HBBas = new HBox();
		
		
	}
	
	
	public void afficherStats(Data data) {
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(data.getMaster());
		for(int i = 0; i < data.getJoueurs().length; i++)
			joueurs.add(data.getJoueursAvecIndex(i));
		
		ArrayList<Joueur> joueursTri = new ArrayList<Joueur>();
		
		for(int i = 0; i < joueurs.size(); i++) {
			int index = 0;
			for(int k = 0; k < joueurs.size(); i++) {
				int minScore = 1000;
				if (minScore > joueurs.get(k).getScore()) {
					minScore = joueurs.get(k).getScore();
					index = k;
					joueurs.remove(index);
				}
			}
			joueursTri.add(joueurs.get(index));
		}
		
		VBJoueur = new VBox[joueurs.size()];
		HBClassement = new HBox[joueurs.size()];
		HBPseudo = new HBox[joueurs.size()];
		HBNbCarte = new HBox[joueurs.size()];		
		HBScore = new HBox[joueurs.size()];
		
		pseudo.setFont(Font.font("Comic Sans MS", 16));
		
		for(int i = 0; i < joueursTri.size(); i++) {
			HBClassement[i].getChildren().add(new Label("" + i));
			HBPseudo[i].getChildren().add(new Label(joueursTri.get(i).getPseudo(), pseudo));
			HBCouleur[i].setBackground(new Background(new BackgroundFill(joueursTri.get(i).getCouleur(), CornerRadii.EMPTY, null)));
			HBNbCarte[i].getChildren().add(new Label("" + joueursTri.get(i).getObjectif().size()));
			HBScore[i].getChildren().add(new Label("" + joueursTri.get(i).getScore()));
		}
		
		for(int i = 0; i <joueursTri.size(); i++) {
			HBClassement[i].setPrefSize(80, 300);;
			HBPseudo[i].setPrefSize(80, 300);
			HBCouleur[i].setPrefSize(80, 120);
			HBNbCarte[i].setPrefSize(80, 300);
			HBScore[i].setPrefSize(80, 300);
			
		}
		
		HBMilieu = new HBox();
		HBMilieu.setAlignment(Pos.CENTER);
		
		for(int i = 0; i < joueursTri.size(); i++) {
			VBJoueur[i].getChildren().addAll(HBClassement[i], HBPseudo[i], HBCouleur[i], HBNbCarte[i], HBScore[i]);
			HBMilieu.getChildren().add(VBJoueur[i]);
		}
		
		
		this.setCenter(HBMilieu);
	}
}
