package javaFX_testZone;

import java.util.HashMap;

import javafx.scene.layout.Pane;

/**
 *  Classe qui affiche les colonnes
 * @author S3T - G1
 *
 */
public class DisplayColonne extends Pane {
	
	double posY = this.getTranslateY();
	
	int maxsize = 0;
	HashMap<Double,SpriteCarteInfluence> ListeCarte = new HashMap<Double,SpriteCarteInfluence>();
	SpriteCarteObjectif CarteObjectif = null;
	
	/**
	 *  constructeur de la classe qui affiche les colonnes
	 * @author S3T - G1
	 * 
	 * @param SCI affichage des cartes influences.
	 * @param SCO affichage des cartes objectifs.
	 * @param height hauteur des colonnes.
	 *
	 */
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
