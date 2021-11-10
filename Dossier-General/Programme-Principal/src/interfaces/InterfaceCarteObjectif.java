package interfaces;


import cartes.CarteObjectif;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class InterfaceCarteObjectif extends StackPane {
	public InterfaceCarteObjectif(CarteObjectif carteObj) {

		this.setStyle("-fx-background-color: blue;");
	    this.setPrefSize(100,100);
		
		Label name = new Label(carteObj.getDomaine());
		this.getChildren().add(name);
		this.setAlignment(Pos.CENTER);
	
	}
}
