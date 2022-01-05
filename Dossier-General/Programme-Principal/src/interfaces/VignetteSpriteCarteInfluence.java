package interfaces;

import cartes.CarteInfluence;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class VignetteSpriteCarteInfluence extends StackPane {
	
	private double coefScale = 3.0;
	
	public VignetteSpriteCarteInfluence() {
		
	}
	public void rafraichir(CarteInfluence carteInf, GestionnaireInterface GI) {
		
		if(carteInf != null && carteInf.getEstVisible()) {
		
		ImageView IView = new ImageView(GI.Cartes.get(carteInf.getNom()));
		IView.setFitWidth(GI.Cartes.get(carteInf.getNom()).getWidth()/coefScale);
		IView.setFitHeight(GI.Cartes.get(carteInf.getNom()).getHeight()/coefScale);
		
		Rectangle rec = new Rectangle(IView.getFitWidth()-30,IView.getFitHeight()-10);
		rec.setFill(carteInf.getCouleur());
		
		this.getChildren().addAll(rec,IView);
		}
	}
	public void flush() {
		this.getChildren().clear();
	}
}
