package interfaces;

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
 * Cette classe permet de créer une page lors d'un chargement (écran de chargement).
 * C'est sur cette interface qu'on va attendre que le jeu charge.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */

public class InterfaceChargement extends InterfaceBase {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	Button boutonAnnuler;
	
	/**
     *  Ce constructeur permet de créer tous les éléments de l'interface, c'est-à-dire le titre
     *  le bouton annuler pour arreter le chargement et revenir au menu precedent
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
	public InterfaceChargement(GestionnaireInterface gi){
		super();
		GI = gi;
		
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
        
        	
		VBox VBCentre = new VBox();
		VBCentre.setPrefSize(1520, 1080);
		VBCentre.setAlignment(Pos.TOP_CENTER);
		
		Label Titre = new Label("Chargement");
		Titre.setFont(Font.font("Pristina", FontWeight.BOLD, 200));
		Titre.setPadding(new Insets(200, 20, 0, 0));
		
		Label sousTitre = new Label("en cours ...");
		sousTitre.setFont(Font.font("Pristina", FontWeight.BOLD, 100));
		sousTitre.setPadding(new Insets(0, 20, 0, 0));
        
        VBCentre.getChildren().addAll(Titre, sousTitre);
        VBCentre.setSpacing(50);
        VBCentre.setPadding(new Insets(0,0,0,400));
        VBCentre.setAlignment(Pos.TOP_CENTER);
		
		// VBDroite qui va contenir le bouton Annuler		
		VBox VBDroite = new VBox();
		VBDroite.setPrefSize(400,0);
		VBDroite.setAlignment(Pos.TOP_RIGHT);
		VBDroite.setPadding(new Insets(50,50,0,0));

		
		// Bouton Annuler
		boutonAnnuler = new Button();
		boutonAnnuler.setText("Annuler");
		boutonAnnuler.setFont(Font.font("Comic sans MS", FontWeight.MEDIUM, 20));
		boutonAnnuler.setPrefSize(150, 50);
		
		VBDroite.getChildren().add(boutonAnnuler);
		
		// Mettre les VBox
		this.setCenter(VBCentre);
		this.setRight(VBDroite);
		
		
		boutonAnnuler.setOnAction(e -> {
			GI.afficherEcran(GI.InterfaceMap.get("menu"));
		});
		
		}
	
}