package interfaces;

import cartes.CarteInfluence;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SpriteCarteInfluence extends StackPane {
	public SpriteCarteInfluence(CarteInfluence carteInf) {
		
		if(carteInf != null) {
			Rectangle rec = new Rectangle();
			rec.setWidth(100); 
			rec.setHeight(100);
			rec.setFill(carteInf.getCouleur());
			
		    this.setPrefSize(100,100);
		    this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println( e));
		    
			Label name = new Label(carteInf.getNom());
			this.getChildren().addAll(rec,name);
			this.setAlignment(Pos.CENTER);
		}
	}
}

