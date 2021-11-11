package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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
import moteur.Partie;

public class InterfaceCreerPartie extends BorderPane implements UI {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	Slider slider;
	Label joueur;
	Button bRetour;
	Button bJouer;
	TextField pseudo;
	
	public InterfaceCreerPartie(GestionnaireInterface gi){
		super();
		GI = gi;
		
		//Cr�ation d'une bordure
		Border maBordure = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(10), new Insets(10)));
		
		//Cr�ation d'un VBox pour tout recueillir (slide, ...)
		VBox vb = new VBox();
		
		//Cr�ation d'un HBox pour le slider
		HBox hbSlide = new HBox();
		vb.setBorder(maBordure);
		vb.setAlignment(Pos.CENTER);
		
		Label joueur = new Label("Choisissez le nombre de joueur : ");
		joueur.setFont(Font.font("Libertina",FontWeight.MEDIUM, 12));
		
		//Param�trage du slider
        Slider slider = new Slider();
        
        slider.setMin(2);
        slider.setMax(6);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        
        slider.setOrientation(Orientation.HORIZONTAL);

        TextField pseudo = new TextField();
        pseudo.setPromptText("Entrer un pseudo");
        
        
        Button bjouer = new Button();
        bjouer.setText("Jouer !");
        bjouer.setPrefSize(100, 30);
        
        bjouer.setOnAction(e -> { 
        	this.creerPartie(pseudo.getText(), (int) slider.getValue()); // Temporaire (Jsp comment l'envoyer à interfaceJeu)
        	GI.afficherEcran(GI.root.getChildren().get(3));
//			GI.InterfaceMap.get("jeu"); //Inutile pour l'instant
        });
        
        hbSlide.getChildren().addAll(joueur,slider,pseudo,bjouer);
        vb.getChildren().add(hbSlide);
        
		this.setTop(vb);
		
		//Cr�ation du bouton retour
		
		VBox vbGauche = new VBox();
		vbGauche.setMinWidth(100);
		vbGauche.setAlignment(Pos.BOTTOM_LEFT);
		vbGauche.setBorder(maBordure);
						
					
		bRetour = new Button();
		bRetour.setText("Retour");
		bRetour.setPrefSize(100,  30);
						
		vbGauche.getChildren().add(bRetour);
		this.setLeft(vbGauche);
		
		bRetour.setOnAction(e -> {
			GI.afficherEcran(GI.root.getChildren().get(0));
		});
		
		//DATA VAR
		
		
		}
	public Data creerPartie(String pseudo, int nbjoueur) {
	        Joueur jinitiateur = new Joueur(Color.BLUE, pseudo);
	        Data data = new Data();
	        data.setMaster(jinitiateur);
	        data.setJoueurs(new Joueur[nbjoueur]);
	        data.addJoueur(jinitiateur);
	        
	        Partie partie = new Partie(jinitiateur, data);
	        return data;
	    }
}
