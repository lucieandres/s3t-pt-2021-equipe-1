package interfaces;

import cartes.CarteObjectif;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InterfaceCarteObjectif extends Rectangle {
	
	public InterfaceCarteObjectif(CarteObjectif CO) {
		this.setWidth(100);
		this.setHeight(100);
		this.setFill(Color.BLUE);
	}
}
