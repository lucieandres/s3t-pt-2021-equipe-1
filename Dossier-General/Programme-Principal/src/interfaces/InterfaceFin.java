package interfaces;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	Button boutonRetour;
	Button boutonRejouer;
	
	HBox HBHaut;
	HBox HBMilieu;
	
	VBox VBDroite;
	VBox VBGauche;
	
	VBox VBDetail;
	VBox VBJ1;
	VBox VBJ2;
	VBox VBJ3;
	VBox VBJ4;
	VBox VBJ5;
	VBox VBJ6;
	
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
		Data data =  GI.getData();
		
		titre = new Label("Score");
		titre.setFont(Font.font("Pristina", FontWeight.BOLD,120));
		
		HBHaut = new HBox();
		HBHaut.getChildren().add(titre);
		HBHaut.setAlignment(Pos.TOP_CENTER);
		HBHaut.setMinSize(0, GI.screenBounds.getWidth());
		this.setTop(HBHaut);
		
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(data.getMaster());
		for(int i = 0; i < data.getJoueurs().length; i++)
			joueurs.add(data.getJoueursAvecIndex(i));
		HBMilieu.getChildren().addAll(VBDetail, VBJ1, VBJ2, VBJ3, VBJ4, VBJ5, VBJ6);
		
		
		
	}
}
