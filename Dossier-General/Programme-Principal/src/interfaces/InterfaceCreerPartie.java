package interfaces;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class InterfaceCreerPartie extends BorderPane implements UI {
	
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	public InterfaceCreerPartie(GestionnaireInterface gi){
		super();
	}
	
}
