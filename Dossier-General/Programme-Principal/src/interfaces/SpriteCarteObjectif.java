package interfaces;


import cartes.CarteObjectif;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class SpriteCarteObjectif extends StackPane {
	
	private int sizeX = 150;
	private int sizeY = 75;
	
	public SpriteCarteObjectif(CarteObjectif carteObj) {

	    this.setPrefSize(sizeX,sizeY);
	    
	    Rectangle rec = new Rectangle();
	    rec.setWidth(sizeX); 
		rec.setHeight(sizeY);
		rec.setFill(Color.GRAY);
		
		Label name = new Label(carteObj.getDomaine()+" "+carteObj.getValeur());
		this.getChildren().addAll(rec,name);
		this.setAlignment(Pos.CENTER);
	
	}
}
