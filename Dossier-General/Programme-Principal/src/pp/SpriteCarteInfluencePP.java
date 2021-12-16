package pp;

import cartes.CarteInfluence;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * Cette classe est une interface qui represente les cartes influences.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class SpriteCarteInfluencePP extends StackPane {
	
	/**
     *  Ce constructeur permet de creer graphiquement une carte influence pour apres l'interger a l'interface du plateau de jeu dans la classe InterfaceJeu.
     * 
     * @param carteInf Carte influence.
     * 
     * @since 1.0
     */
	public SpriteCarteInfluencePP(CarteInfluence carteInf) {
		
		if(carteInf != null) {
			Rectangle rec = new Rectangle();
			rec.setWidth(100); 
			rec.setHeight(50);
			rec.setFill(carteInf.getCouleur());
			
		    this.setPrefSize(100,50);
		    //this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println( e));
		    
		    if(carteInf.getEstVisible()) {
		    	Label name = new Label(carteInf.getNom()+" "+(int)carteInf.getValeur());
		    	this.getChildren().addAll(rec,name);
		    } else {
		    	this.getChildren().addAll(rec);
		    }
			this.setAlignment(Pos.CENTER);
		}
		
		
	}
}

