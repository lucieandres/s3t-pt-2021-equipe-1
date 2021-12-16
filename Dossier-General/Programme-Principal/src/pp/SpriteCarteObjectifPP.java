package pp;


import cartes.CarteObjectif;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Cette classe est une interface qui represente les cartes objectifs.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class SpriteCarteObjectifPP extends StackPane {
	
	private int sizeX = 150;
	private int sizeY = 75;
	
	/**
     *  Ce constructeur permet de creer graphiquement une carte objectif pour apres l'interger a l'interface du plateau de jeu dans la classe InterfaceJeu.
     * 
     * @param carteObj Carte objectif.
     * 
     * @since 1.0
     */
	
	public SpriteCarteObjectifPP(CarteObjectif carteObj) {
		
		if(carteObj != null) {
	    this.setPrefSize(sizeX,sizeY);
	    
	    Rectangle rec = new Rectangle();
	    rec.setWidth(sizeX); 
		rec.setHeight(sizeY);
		rec.setFill(Color.LIGHTGRAY);
		
		Label name = new Label(carteObj.getDomaine()+" "+carteObj.getValeur());
		this.getChildren().addAll(rec,name);
		this.setAlignment(Pos.CENTER);
		}
		
	}
}
