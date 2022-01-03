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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InterfaceLobby extends InterfaceBase{

	@Override
	public void dessineInterface(GestionnaireInterfacePP GI) {
		   
		
	}
	
	public HBox dessineBox(GestionnaireInterfacePP GI) {
		HBox BoxJoueur = new HBox();
		BoxJoueur.setAlignment(Pos.CENTER);
		CheckBox[] couleur = new CheckBox[6];
		
		TextField pseudo = new TextField("Pseudo");
//		pseudo.setPromptText(GI.texteLangue.get(GI.langueSelectionne).getProperty("texte.pseudo"));
        pseudo.setFont(Font.font("Comic Sans MS", 20));
        pseudo.setPrefSize(220, 50);
		
		for(int i=0; i<6; i++) {
			couleur[i] = new CheckBox();
		}
			
		couleur[0].setBackground(new Background(new BackgroundFill(Color.web("0xffff00ff"), null, null)));
		couleur[1].setBackground(new Background(new BackgroundFill(Color.web("0x800080ff"), null, null)));
		couleur[2].setBackground(new Background(new BackgroundFill(Color.web("0xfaebd7ff"), null, null)));
		couleur[3].setBackground(new Background(new BackgroundFill(Color.web("0x006400ff"), null, null)));
		couleur[4].setBackground(new Background(new BackgroundFill(Color.web("0xff0000ff"), null, null)));
		couleur[5].setBackground(new Background(new BackgroundFill(Color.web("0x0000ffff"), null, null)));
		
		GridPane tabCouleur = new GridPane();
		
		
		
		return BoxJoueur;
	}
	
	
}
