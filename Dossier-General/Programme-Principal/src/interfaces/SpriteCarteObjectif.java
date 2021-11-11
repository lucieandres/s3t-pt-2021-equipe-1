package interfaces;


import cartes.CarteObjectif;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class SpriteCarteObjectif extends StackPane {
	public SpriteCarteObjectif(CarteObjectif carteObj) {

	    this.setPrefSize(100,50);
	    
	    Rectangle rec = new Rectangle();
	    rec.setWidth(100); 
		rec.setHeight(50);
		rec.setFill(Color.GREY);
		
		Label name = new Label(carteObj.getDomaine()+" "+carteObj.getValeur());
		this.getChildren().add(name);
		this.setAlignment(Pos.CENTER);
	
	}
}
