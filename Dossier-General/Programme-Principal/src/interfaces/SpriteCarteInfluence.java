package interfaces;

import cartes.CarteInfluence;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Cette classe est une interface qui represente les cartes influences.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class SpriteCarteInfluence extends StackPane {
	
	double coefScale = 7;
	public boolean firstDragged = false;
	public double translateX = 0.0;
	public double translateY = 0.0;
	public double avancementTransition = 0.0;
	private CarteInfluence carteSource;
	
	/**
     *  Ce constructeur permet de creer une carte vide
     * 
     * 
     * 
     * @since 1.0
     */
	
	public SpriteCarteInfluence(Color c,GestionnaireInterface GI) {
		ImageView IView = new ImageView(GI.Cartes.get("back_"+c.toString()));
		IView.setFitWidth(IView.getFitWidth()/coefScale);
		IView.setFitHeight(IView.getFitHeight()/coefScale);
		this.getChildren().add(IView);
	}
	
	/**
     *  Ce constructeur permet de creer graphiquement une carte influence pour apres l'interger a l'interface du plateau de jeu dans la classe InterfaceJeu.
     * 
     * @param carteInf Carte influence.
     * 
     * @since 1.0
     */
	
	public SpriteCarteInfluence(CarteInfluence carteInf, GestionnaireInterface GI) {
		
		carteSource = carteInf;
		
		if(carteInf != null) {
			
			if(carteInf.getEstVisible()) {
				//Image I = GI.Cartes.get(carteInf.getNom());
				ImageView IView = new ImageView(GI.Cartes.get(carteInf.getNom()));
				IView.setFitWidth(GI.Cartes.get(carteInf.getNom()).getWidth()/coefScale);
				IView.setFitHeight(GI.Cartes.get(carteInf.getNom()).getHeight()/coefScale);
				
				Rectangle rec = new Rectangle(IView.getFitWidth()-20,IView.getFitHeight()-10);
				rec.setFill(carteInf.getCouleur());
				
				this.getChildren().addAll(rec,IView);
			} else {
				//System.out.println(carteInf.getCouleur().toString());
				//Image I = GI.Cartes.get("back_"+carteInf.getCouleur().toString());
				ImageView IView = new ImageView(GI.Cartes.get("back_"+carteInf.getCouleur().toString()));
				IView.setFitWidth(GI.Cartes.get(carteInf.getNom()).getWidth()/coefScale);
				IView.setFitHeight(GI.Cartes.get(carteInf.getNom()).getHeight()/coefScale);
				this.getChildren().add(IView);
			}
		}
	}
	public CarteInfluence getCarteSource() {
		return carteSource;
	}
}

