package pp;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
	int nbBox = 0;
	int nbBot = 0;
	GestionnaireInterfacePP gi;
	
	public InterfaceLobby(GestionnaireInterfacePP GI){
		GI = gi;
	}
	
	public void dessineInterface(GestionnaireInterfacePP GI) {
		   //TODO Quand un joueur rejoind le lobby, on appelle dessineBoxJoueur
		nbjoueur = GI.data.getJoueurs().length;
		HBox[] HBJoueurs = new HBox[nbjoueur];
		Button[] ajouterJoueurs = new Button[nbjoueur];
		
		
		for(int i = 0; i < nbjoueur; i++) {
			ajouterJoueurs[i] = new Button("+");
			ajouterJoueurs[i].setPrefSize(GI.screenBounds.getHeight()*0.48, GI.screenBounds.getWidth()*0.06);
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
		
		VBox VBBouton = new VBox();
		Button sup = new Button("^");
		sup.setFont(Font.font("Comic Sans MS", 10));
		sup.setPrefSize(18, 30);
		sup.setOnAction(e -> hausserBox(GI, BoxJoueur));
		Button inf = new Button("v");
		inf.setPrefSize(18, 30);
		inf.setFont(Font.font("Comic Sans MS", 10));
		inf.setOnAction(e -> descendreBox(GI, BoxJoueur));
		VBBouton.getChildren().addAll(sup, inf);
		
		TextField pseudo = new TextField("Pseudo");
//		pseudo.setPromptText(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.pseudo"));
        pseudo.setFont(Font.font("Comic Sans MS", 20));
        pseudo.setPrefSize(220, 50);
        
        ChoiceBox<String> choixCouleur = new ChoiceBox<>();
        String rouge = new String("Rouge");
        String violet = new String("Violet");
        String jaune = new String("Jaune");
        String blanc = new String("Blanc");
        String vert = new String("Vert");
        String bleu = new String("Bleu");
        choixCouleur.getItems().addAll(rouge, violet, jaune, blanc, vert, bleu);
        
		BoxJoueur.getChildren().addAll(VBBouton, pseudo);
		
		return BoxJoueur;
	}
	
	public void dessineBoxBot(GestionnaireInterfacePP GI, HBox HBJoueurs) {
        HBox BoxBot = new HBox();
        BoxBot.setPrefSize(GI.screenBounds.getHeight()*0.48, GI.screenBounds.getWidth()*0.06);
        BoxBot.setAlignment(Pos.CENTER_LEFT);
        nbBot++;
        
        VBox VBBouton = new VBox();
        Button sup = new Button("^");
        sup.setPrefSize(38, 50);
        sup.setFont(Font.font("Comic Sans MS", 18));
        sup.setOnAction(e -> hausserBox(GI, BoxBot));
        Button inf = new Button("v");
        inf.setPrefSize(38, 50);
        inf.setFont(Font.font("Comic Sans MS", 18));
        inf.setOnAction(e -> descendreBox(GI, BoxBot));
        VBBouton.getChildren().addAll(sup, inf);
        VBBouton.setPadding(new Insets(8));
        
        TextField pseudo = new TextField();
        pseudo.setPromptText("bot" + nbBot);
//        pseudo.setPromptText(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.pseudo"));
        pseudo.setFont(Font.font("Comic Sans MS", 20));
        pseudo.setPrefSize(220, 40);

        ChoiceBox<String> difficulteBot = new ChoiceBox<>();
        difficulteBot.setPrefSize(100, 40);
        difficulteBot.getItems().addAll("Facile", "Moyen", "Difficile");
        difficulteBot.setValue("Facile");
        
        ChoiceBox<String> choixCouleur = new ChoiceBox<>();
        choixCouleur.setPrefSize(100, 40);
        String rouge = new String("Rouge");
        String violet = new String("Violet");
        String jaune = new String("Jaune");
        String blanc = new String("Blanc");
        String vert = new String("Vert");
        String bleu = new String("Bleu");
        choixCouleur.getItems().addAll(rouge, violet, jaune, blanc, vert, bleu);
        
        HBox boxMoins = new HBox();
        boxMoins.setPadding(new Insets(0,0,15,15));
        
        Button retirerBox = new Button("-");
        retirerBox.setFont(Font.font("Comic Sans MS", 15));
        retirerBox.setPrefSize(30, 30);
        retirerBox.setOnAction(e -> retirerBox(GI, BoxBot));
        
        boxMoins.getChildren().add(retirerBox);
        
        BoxBot.getChildren().addAll(VBBouton, pseudo, difficulteBot, choixCouleur, boxMoins);
        
        int k = 0;

        for(int i = 0; i<nbjoueur; i++)
            if(VBMilieu.getChildren().get(i) == HBJoueurs)
                k = i;

        VBMilieu.getChildren().remove(HBJoueurs);
        VBMilieu.getChildren().add(nbBox, BoxBot);
        nbBox++;
    }
	
	public void retirerBox(GestionnaireInterfacePP GI, HBox Box) {
		VBMilieu.getChildren().remove(Box);
		nbBox--;
		if(Box.getChildren().size() == 5)
			nbBot--;
			
		HBox HBPlus = new HBox();
		Button ajouterJoueur = new Button("+");
		ajouterJoueur.setPrefSize(GI.screenBounds.getHeight()*0.48, GI.screenBounds.getWidth()*0.06);
		ajouterJoueur.setAlignment(Pos.CENTER);
		ajouterJoueur.setOnAction(e -> dessineBoxBot(GI, HBPlus));

		HBPlus.getChildren().add(ajouterJoueur);
		
		VBMilieu.getChildren().add(HBPlus);
		
	}
	
	public void descendreBox(GestionnaireInterfacePP GI, HBox Box) {
		int indexBoxAMonter = VBMilieu.getChildren().indexOf(Box) + 1;
		int indexBoxADescendre = VBMilieu.getChildren().indexOf(Box);
		
		if(indexBoxAMonter >= nbjoueur) {
			
		}
		else {
			Node HBbas = VBMilieu.getChildren().get(indexBoxAMonter);
			VBMilieu.getChildren().removeAll(HBbas, Box);
			VBMilieu.getChildren().add(indexBoxADescendre, HBbas);
			VBMilieu.getChildren().add(indexBoxAMonter, Box);
		}
			
	}
	
	public void hausserBox(GestionnaireInterfacePP GI, HBox Box) {
		int indexBoxAMonter = VBMilieu.getChildren().indexOf(Box);
		int indexBoxADescendre = VBMilieu.getChildren().indexOf(Box) - 1;
		
		if(indexBoxADescendre < 0) {
			
		}
		else {
			Node HBhaut = VBMilieu.getChildren().get(indexBoxADescendre);
			VBMilieu.getChildren().removeAll(HBhaut, Box);
			VBMilieu.getChildren().add(indexBoxADescendre, Box);
			VBMilieu.getChildren().add(indexBoxAMonter, HBhaut);
		}
			
	}
}
