package interfaces;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class InterfaceTransition extends InterfaceBase {
	public InterfaceTransition() {
		this.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,null)));
	}
}
