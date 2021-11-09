package interfaces;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class InterfaceCreerPartie extends BorderPane implements UI {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	Slider slider;
	Label joueur;
	Button bRetour;
	Button bJouer;
	
	public InterfaceCreerPartie(GestionnaireInterface gi){
		super();
		GI = gi;
		
		//Création d'une bordure
		Border maBordure = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(10), new Insets(10)));
		
		//Création d'un VBox pour tout recueillir (slide, ...)
		VBox vb = new VBox();
		
		//Création d'un HBox pour le slider
		HBox hbSlide = new HBox();
		vb.setBorder(maBordure);
		vb.setAlignment(Pos.CENTER);
		
		Label joueur = new Label("Choisissez le nombre de joueur : ");
		joueur.setFont(Font.font("Libertina",FontWeight.MEDIUM, 12));
		
		//Paramétrage du slider
        Slider slider = new Slider();
        
        slider.setMin(1);
        slider.setMax(6);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setBlockIncrement(1);
        slider.setMinorTickCount(1);
        
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.valueProperty().addListener((obs, oldval, newVal) -> slider.setValue(newVal.intValue()));

        
        Button bjouer = new Button();
        bjouer.setText("Jouer !");
        bjouer.setPrefSize(100, 30);
        bjouer.setOnAction(e -> { 
        	GI.afficherEcran(GI.root.getChildren().get(3));
        });
        
        //On relie tout ça
        
        // bouton lancement de jeu
        
        // > creer une instance de data puis de partie avec en parametre la valeur du slider
        
        hbSlide.getChildren().add(joueur);
        hbSlide.getChildren().add(slider);
        hbSlide.getChildren().add(bjouer);
        vb.getChildren().add(hbSlide);
        
		this.setTop(vb);
		
		//Création du bouton retour
		
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
	
}
