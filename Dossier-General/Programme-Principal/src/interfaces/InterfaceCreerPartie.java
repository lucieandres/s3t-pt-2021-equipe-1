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
		
		//Cr�tion d'une VBox pour tout recueillir (slide, ...)
		VBox VBMid = new VBox();
		
		//Cr�ation d'une HBox pour le slider
		HBox HBSlide = new HBox();
		VBMid.setBorder(maBordure);
		VBMid.setAlignment(Pos.CENTER);
		
		Label joueur = new Label("Choisissez le nombre de joueur : ");
		joueur.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
		
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
        pseudo.setFont(Font.font("Comic Sans MS", 20));
        pseudo.setPrefSize(200, 42);
        
        
        Button bjouer = new Button("Jouer !");
        bjouer.setFont(Font.font("Comic Sans MS", 20));
        bjouer.setPrefSize(100, 30);
        
        bjouer.setOnAction(e -> { 
        	this.creerPartie(pseudo.getText(), (int) slider.getValue()); // Temporaire (Jsp comment l'envoyer à interfaceJeu)
        	GI.afficherEcran(GI.InterfaceMap.get("jeu"));
//			GI.InterfaceMap.get("jeu"); //Inutile pour l'instant
        });
        
        HBSlide.getChildren().addAll(joueur,slider);
        
        VBMid.getChildren().add(HBSlide);
        
		this.setTop(VBMid);
		
		//Cr�ation du bouton retour
		
		VBox VBRight = new VBox();
		VBRight.setMinWidth(100);
		VBRight.setAlignment(Pos.BOTTOM_LEFT);
		VBRight.setBorder(maBordure);
						
					
		bRetour = new Button();
		bRetour.setText("Retour");
		bRetour.setPrefSize(100,  30);
						
		VBRight.getChildren().add(bRetour);
		this.setRight(VBRight);
		
		bRetour.setOnAction(e -> {
			GI.afficherEcran(GI.root.getChildren().get(0));
		});
		
		//DATA VAR
		
		
		}
	public void creerPartie(String pseudo, int nbjoueur) {
	        Joueur jinitiateur = new Joueur(Color.BLUE, pseudo);
	        GI.getData().initPartie(jinitiateur, nbjoueur);
	        GI.Jeux.drawMain(GI.getData());
	        Partie partie = new Partie(jinitiateur, GI.getData());
	    }
}
