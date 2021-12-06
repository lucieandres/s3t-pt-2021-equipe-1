package javaFX_testZone;

import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class DisplayMain extends AnchorPane {
	ArrayList<SpriteCarteInfluence> ListeCarte = new ArrayList<SpriteCarteInfluence>();
	
	public DisplayMain() {
		HBox hb = new HBox();
		hb.getChildren().addAll(ListeCarte);
		hb.setSpacing(20);
		this.getChildren().add(hb);
	}
}
