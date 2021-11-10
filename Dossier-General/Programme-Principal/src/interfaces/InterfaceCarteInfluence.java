package interfaces;

import cartes.CarteInfluence;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InterfaceCarteInfluence extends Rectangle {
	public InterfaceCarteInfluence(CarteInfluence CI) {

		this.setWidth(100);
		this.setHeight(100);
		this.setFill(Color.RED);
	}
}
