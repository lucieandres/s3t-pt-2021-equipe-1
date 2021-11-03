package interfaces;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class InterfaceCreerPartie extends Pane implements UI {
	
	public AnchorPane AP = null;
	
	public InterfaceCreerPartie() throws IOException {
		loadFXML();
	}
	
	private void loadFXML() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		//loader.setLocation(InterfaceCreerPartie.class.getResource("Creer_Partie.fxml"));
		//AP = loader.load();
		//System.out.println(AP);
	}
	
}
