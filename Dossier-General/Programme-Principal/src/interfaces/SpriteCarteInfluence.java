package interfaces;

import cartes.CarteInfluence;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
	private boolean ombreActive = false;
	
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
	/**
	 * méthode qui retourne la carte source.
	 * @return
	 */
	public CarteInfluence getCarteSource() {
		return carteSource;
	}
	
	public void ombre(GestionnaireInterface GI) {
		
		if(!ombreActive) {
			ImageView IView2 = new ImageView(GI.Cartes.get(carteSource.getNom()));
			IView2.setFitWidth(GI.Cartes.get(carteSource.getNom()).getWidth()/coefScale);
			IView2.setFitHeight(GI.Cartes.get(carteSource.getNom()).getHeight()/coefScale);
			
			//shadow
		    Pane shadow = new Pane(IView2);
		    shadow.setScaleX(1.025); 
		    shadow.setScaleY(1.025);
		    ColorAdjust c = new ColorAdjust();
		    c.setBrightness(-1);
		    shadow.setEffect(c);
		    shadow.setOpacity(0.5);
		    GaussianBlur gb = new GaussianBlur(); gb.setRadius(12); 
		    Pane dropShadow = new Pane(shadow); // stacking d'effet version wish
		    dropShadow.setEffect(gb);
		     
		    dropShadow.setTranslateX(7);
		    dropShadow.setTranslateY(7);
		    
		    this.getChildren().add(0, dropShadow);
		}
	    ombreActive = true;
	}
	public void retireOmbre() {
		this.getChildren().remove(0);
		ombreActive = false;
	}
}

