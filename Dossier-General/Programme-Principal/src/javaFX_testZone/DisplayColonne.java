package javaFX_testZone;

import java.util.HashMap;

import javafx.scene.layout.Pane;

public class DisplayColonne extends Pane {
	
	double posY = this.getTranslateY();
	
	int maxsize = 0;
	HashMap<Double,SpriteCarteInfluence> ListeCarte = new HashMap<Double,SpriteCarteInfluence>();
	SpriteCarteObjectif CarteObjectif = null;
	
	public DisplayColonne(SpriteCarteInfluence[] SCI, SpriteCarteObjectif SCO, int height) {
		maxsize = height;
		CarteObjectif = SCO;
		int i=0;
		for(SpriteCarteInfluence S : SCI) {
			ListeCarte.put((double) i*S.getHeight(), S);
			i++;
		}
		
		
		
	}
}
