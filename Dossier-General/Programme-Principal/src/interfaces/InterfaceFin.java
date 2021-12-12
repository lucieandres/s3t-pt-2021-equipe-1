package interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

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
		HashMap<Integer, Joueur> scores = new HashMap<>();
		for(Joueur j : data.getJoueurs())
			scores.put(j.getScore(), j);
		TreeMap<Integer, Joueur> triScores = new TreeMap<>(scores);
		ArrayList<Joueur> triJoueur = new ArrayList<>(triScores.values());
		System.out.println(triScores);
	
		VBJoueur = new VBox[triScores.size()];
		HBClassement = new HBox[triScores.size()];
		HBPseudo = new HBox[triScores.size()];
		HBCouleur = new HBox[triScores.size()];
		HBNbCarte = new HBox[triScores.size()];		
		HBScore = new HBox[triScores.size()];
		
		for(int i = triJoueur.size()-1; i >= 0; i--) {
			VBJoueur[i] = new VBox();
			HBClassement[i] = new HBox();
			HBPseudo[i] = new HBox();
			HBCouleur[i] = new HBox();
			HBNbCarte[i] = new HBox();	
			HBScore[i] = new HBox();
			pseudo = new Label(triJoueur.get(i).getPseudo());
			pseudo.setFont(Font.font("Comic Sans MS", 16));
			HBClassement[i].getChildren().add(new Label("" + i));
			HBPseudo[i].getChildren().add(new Label(triJoueur.get(i).getPseudo(), pseudo));
			HBCouleur[i].setBackground(new Background(new BackgroundFill(triJoueur.get(i).getCouleur(), CornerRadii.EMPTY, null)));
			HBNbCarte[i].getChildren().add(new Label("" + triJoueur.get(i).getObjectif().size()));
			HBScore[i].getChildren().add(new Label("" + triJoueur.get(i).getScore()));
		}
		
		for(int i = 0; i <triJoueur.size(); i++) {
			HBClassement[i].setPrefSize(80, 150);
			HBPseudo[i].setPrefSize(80, 150);
			HBCouleur[i].setPrefSize(80, 60);
			HBNbCarte[i].setPrefSize(80, 150);
			HBScore[i].setPrefSize(80, 150);
			VBJoueur[i].setPrefSize(400, 150);
			
		}
		
		HBMilieu = new HBox();
		HBMilieu.setAlignment(Pos.CENTER);
		
		for(int i = 0; i < triJoueur.size(); i++) {
			VBJoueur[i].getChildren().addAll(HBClassement[i], HBPseudo[i], HBCouleur[i], HBNbCarte[i], HBScore[i]);
			HBMilieu.getChildren().add(VBJoueur[i]);
		}
		
		
		this.setCenter(HBMilieu);
	}
}
