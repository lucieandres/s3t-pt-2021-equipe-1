package interfaces;

import cartes.CarteInfluence;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class InterfaceCarteInfluence extends StackPane {
	public InterfaceCarteInfluence(CarteInfluence carteInf) {

		carteInf.getCouleur();
		this.setStyle("-fx-background-color: red;");
	    this.setPrefSize(100,100);
		
		Label name = new Label(carteInf.getNom());
		this.getChildren().add(name);
		this.setAlignment(Pos.CENTER);
	
	}
}
