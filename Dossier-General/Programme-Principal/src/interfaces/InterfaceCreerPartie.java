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
import moteur.Partie;

public class InterfaceCreerPartie extends BorderPane implements UI {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	Slider slider;
	Label joueur;
	Button boutonRetour;
	Button bJouer;
	TextField pseudo;
	
	public InterfaceCreerPartie(GestionnaireInterface gi){
		super();
		GI = gi;
		
		//Creation d'une bordure
		Border maBordure = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(10), new Insets(10)));
		
		HBox HBJoueur = new HBox();
		HBJoueur.setAlignment(Pos.CENTER);
				
		Label joueur = new Label("Choisissez le nombre de joueur");
		joueur.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
		joueur.setPadding(new Insets(300,0,0,0));
		
		//Paramétrage du slider
        Slider slider = new Slider();
        
        slider.setMin(2);
        slider.setMax(6);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setPadding(new Insets(10,20,10,20));
        
        slider.setOrientation(Orientation.HORIZONTAL);

        TextField pseudo = new TextField();
        pseudo.setPromptText("Entrer un pseudo");
        pseudo.setFont(Font.font("Comic Sans MS", 20));
        pseudo.setPrefSize(200, 42);
        
        
        Button boutonJouer = new Button("Jouer !");
        boutonJouer.setFont(Font.font("Comic Sans MS", 20));
        boutonJouer.setPrefSize(100, 30);
        
        boutonJouer.setOnAction(e -> { 
        	this.creerPartie(pseudo.getText(), (int) slider.getValue()); // Temporaire (Jsp comment l'envoyer Ã  interfaceJeu)
        	GI.afficherEcran(GI.InterfaceMap.get("jeu"));
        });
        	
		VBox VBHaut = new VBox();
		VBHaut.setPrefSize(1400, 1080);
		VBHaut.setAlignment(Pos.TOP_CENTER);
		
		Label Titre = new Label("Créer Partie");
		Titre.setFont(Font.font("Comic sans MS", 40));
		Titre.setPadding(new Insets(20, 0, 0, 0));
        HBJoueur.getChildren().addAll(pseudo, boutonJouer);
        VBHaut.getChildren().addAll(Titre,joueur, slider, HBJoueur);
        VBHaut.setPrefSize(1500, 800);
        VBHaut.setSpacing(10);
        VBHaut.setPadding(new Insets(0,0,0,400));
        VBHaut.setAlignment(Pos.TOP_CENTER);
		this.setCenter(VBHaut);
				
		VBox VBDroite = new VBox();
		VBDroite.setPrefSize(430, 800);
		VBDroite.setAlignment(Pos.TOP_RIGHT);
		VBDroite.setPadding(new Insets(16,20,0,0));
		VBDroite.setBorder(maBordure);
		
		//Creation du bouton retour
		boutonRetour = new Button();
		boutonRetour.setText("Retour");
		boutonRetour.setFont(Font.font("Comic sans MS", 20));
		boutonRetour.setPrefSize(150,  30);
		
		VBDroite.getChildren().add(boutonRetour);
		this.setRight(VBDroite);
		
		
		boutonRetour.setOnAction(e -> {
			GI.afficherEcran(GI.InterfaceMap.get("menu"));
		});
		
		}
	
	public void creerPartie(String pseudo, int nbjoueur) {
	        Joueur jinitiateur = new Joueur(Color.BLUE, pseudo);
	        GI.getData().initPartie(jinitiateur, nbjoueur);
	        GI.Jeux.drawMain(GI.getData());
	        Partie partie = new Partie(jinitiateur, GI.getData());
	    }
}
