package pp;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InterfaceLobby extends InterfaceBase{
	
	private VBox VBMilieu;
	int nbjoueur;
	
	public InterfaceLobby(GestionnaireInterfacePP GI){
	}
	
	public void dessineInterface(GestionnaireInterfacePP GI) {
		   //TODO Quand un joueur rejoind le lobby, on appelle dessineBoxJoueur
		nbjoueur = GI.data.getJoueurs().length;
		HBox[] HBJoueurs = new HBox[nbjoueur];
		Button[] ajouterJoueurs = new Button[nbjoueur];
		
		for(int i = 0; i < nbjoueur; i++) {
			ajouterJoueurs[i] = new Button("+");
			ajouterJoueurs[i].setPrefSize(GI.screenBounds.getHeight()*0.45, GI.screenBounds.getWidth()*0.06);
			ajouterJoueurs[i].setAlignment(Pos.CENTER);
		}
				
		VBMilieu = new VBox();
		
		for(int i = 0; i < nbjoueur; i++) {
			HBJoueurs[i] = new HBox();
			HBJoueurs[i].getChildren().add(ajouterJoueurs[i]);
			VBMilieu.getChildren().add(HBJoueurs[i]);
			final int k = i;
			ajouterJoueurs[i].setOnAction(e -> dessineBoxBot(GI,HBJoueurs[k]));
		}
		
		this.setCenter(VBMilieu);		
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
		
		couleur[0].setStyle(".check-box > .box {-fx-background-color: red;}");
		couleur[1].setBackground(new Background(new BackgroundFill(Color.web("0x800080ff"), CornerRadii.EMPTY, null)));
		couleur[2].setBackground(new Background(new BackgroundFill(Color.web("0xfaebd7ff"), CornerRadii.EMPTY, null)));
		couleur[3].setBackground(new Background(new BackgroundFill(Color.web("0x006400ff"), CornerRadii.EMPTY, null)));
		couleur[4].setBackground(new Background(new BackgroundFill(Color.web("0xff0000ff"), CornerRadii.EMPTY, null)));
		couleur[5].setBackground(new Background(new BackgroundFill(Color.web("0x0000ffff"), CornerRadii.EMPTY, null)));
		
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
	
	public void dessineBoxBot(GestionnaireInterfacePP GI, HBox HBJoueurs) {
        System.out.println("On appelle bien la fonction");
        HBox BoxBot = new HBox();
        BoxBot.setPrefSize(GI.screenBounds.getHeight()*0.45, GI.screenBounds.getWidth()*0.06);
        BoxBot.setAlignment(Pos.CENTER);

        VBox VBBouton = new VBox();
        Button sup = new Button("^");
        sup.setPrefSize(15, 15);
        Button inf = new Button("v");
        inf.setPrefSize(15, 15);
        VBBouton.getChildren().addAll(sup, inf);

        TextField pseudo = new TextField("Pseudo");
//        pseudo.setPromptText(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.pseudo"));
        pseudo.setFont(Font.font("Comic Sans MS", 20));
        pseudo.setPrefSize(220, 50);

        ChoiceBox<String> difficulteBot = new ChoiceBox<>();
        difficulteBot.getItems().add("Facile");
        difficulteBot.getItems().add("Moyen");
        difficulteBot.getItems().add("Difficile");

        ChoiceBox<String> choixCouleur = new ChoiceBox<>();
        String rouge = new String("Rouge");
        String violet = new String("Violet");
        String jaune = new String("Jaune");
        String blanc = new String("Blanc");
        String vert = new String("Vert");
        String bleu = new String("Bleu");
        choixCouleur.getItems().addAll(rouge, violet, jaune, blanc, vert, bleu);

        BoxBot.getChildren().addAll(VBBouton, pseudo, difficulteBot, choixCouleur);

        int k = 0;

        for(int i = 0; i<nbjoueur; i++)
            if(VBMilieu.getChildren().get(i) == HBJoueurs)
                k = i;

        VBMilieu.getChildren().remove(HBJoueurs);
        VBMilieu.getChildren().add(k, BoxBot);
        System.out.println("On finit la fonction");
    }

}
