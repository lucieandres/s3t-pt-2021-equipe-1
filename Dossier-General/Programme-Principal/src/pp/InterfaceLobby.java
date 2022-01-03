package pp;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InterfaceLobby extends InterfaceBase{

	@Override
	public void dessineInterface(GestionnaireInterfacePP GI) {
		   //TODO Quand un joueur rejoind le lobby, on appelle dessineBoxJoueur
			
		
	}
	
	public HBox dessineBoxJoueur(GestionnaireInterfacePP GI) {
		HBox BoxJoueur = new HBox();
		BoxJoueur.setAlignment(Pos.CENTER);
		CheckBox[] couleur = new CheckBox[6];
		
		VBox VBBouton = new VBox();
		Button sup = new Button("^");
		sup.setPrefSize(15, 15);
		Button inf = new Button("v");
		inf.setPrefSize(15, 15);
		VBBouton.getChildren().addAll(sup, inf);
		
		TextField pseudo = new TextField("Pseudo");
//		pseudo.setPromptText(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.pseudo"));
        pseudo.setFont(Font.font("Comic Sans MS", 20));
        pseudo.setPrefSize(220, 50);
		
		for(int i=0; i<6; i++) {
			couleur[i] = new CheckBox();
			couleur[i].setPrefSize(5, 5);
		}
		
		couleur[0].setBackground(new Background(new BackgroundFill(Color.web("0xffff00ff"), null, null)));
		couleur[1].setBackground(new Background(new BackgroundFill(Color.web("0x800080ff"), null, null)));
		couleur[2].setBackground(new Background(new BackgroundFill(Color.web("0xfaebd7ff"), null, null)));
		couleur[3].setBackground(new Background(new BackgroundFill(Color.web("0x006400ff"), null, null)));
		couleur[4].setBackground(new Background(new BackgroundFill(Color.web("0xff0000ff"), null, null)));
		couleur[5].setBackground(new Background(new BackgroundFill(Color.web("0x0000ffff"), null, null)));
		
		GridPane tabCouleur = new GridPane();
		
		tabCouleur.add(couleur[0], 0, 0);
		tabCouleur.add(couleur[1], 1, 0);
		tabCouleur.add(couleur[2], 2, 0);
		tabCouleur.add(couleur[3], 0, 1);
		tabCouleur.add(couleur[4], 1, 1);
		tabCouleur.add(couleur[5], 2, 1);
		
		tabCouleur.setHgap(5);
		tabCouleur.setVgap(5);
		
		BoxJoueur.getChildren().addAll(VBBouton, pseudo, tabCouleur);
		
		return BoxJoueur;
	}
	
	public HBox dessineBoxBot(GestionnaireInterfacePP GI) {
		HBox BoxBot = new HBox();
		BoxBot.setAlignment(Pos.CENTER);
		CheckBox[] couleur = new CheckBox[6];
		
		VBox VBBouton = new VBox();
		Button sup = new Button("^");
		sup.setPrefSize(15, 15);
		Button inf = new Button("v");
		inf.setPrefSize(15, 15);
		VBBouton.getChildren().addAll(sup, inf);
		
		TextField pseudo = new TextField("Pseudo");
//		pseudo.setPromptText(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.pseudo"));
        pseudo.setFont(Font.font("Comic Sans MS", 20));
        pseudo.setPrefSize(220, 50);
		
		for(int i=0; i<6; i++) {
			couleur[i] = new CheckBox();
			couleur[i].setPrefSize(5, 5);
		}
		
		couleur[0].setBackground(new Background(new BackgroundFill(Color.web("0xffff00ff"), null, null)));
		couleur[1].setBackground(new Background(new BackgroundFill(Color.web("0x800080ff"), null, null)));
		couleur[2].setBackground(new Background(new BackgroundFill(Color.web("0xfaebd7ff"), null, null)));
		couleur[3].setBackground(new Background(new BackgroundFill(Color.web("0x006400ff"), null, null)));
		couleur[4].setBackground(new Background(new BackgroundFill(Color.web("0xff0000ff"), null, null)));
		couleur[5].setBackground(new Background(new BackgroundFill(Color.web("0x0000ffff"), null, null)));
		
		GridPane tabCouleur = new GridPane();
		
		tabCouleur.add(couleur[0], 0, 0);
		tabCouleur.add(couleur[1], 1, 0);
		tabCouleur.add(couleur[2], 2, 0);
		tabCouleur.add(couleur[3], 0, 1);
		tabCouleur.add(couleur[4], 1, 1);
		tabCouleur.add(couleur[5], 2, 1);
		
		tabCouleur.setHgap(5);
		tabCouleur.setVgap(5);
		
		BoxBot.getChildren().addAll(VBBouton, pseudo, tabCouleur);
		
		return BoxBot;
	}
	
	
	
}
