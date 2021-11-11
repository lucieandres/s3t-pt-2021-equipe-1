package interfaces;


import cartes.CarteObjectif;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class SpriteCarteObjectif extends StackPane {
	public SpriteCarteObjectif(CarteObjectif carteObj) {

		this.setStyle("-fx-background-color: blue;");
	    this.setPrefSize(100,100);
		
		Label name = new Label(carteObj.getDomaine()+" "+carteObj.getValeur());
		this.getChildren().add(name);
		this.setAlignment(Pos.CENTER);
	
	}
}
