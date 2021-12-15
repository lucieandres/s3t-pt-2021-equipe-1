package interfaces;


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
public class SpriteCarteObjectif extends StackPane {
	
	private int sizeX = 150;
	private int sizeY = 75;
	
	/**
     *  Ce constructeur permet de creer graphiquement une carte objectif pour apres l'interger a l'interface du plateau de jeu dans la classe InterfaceJeu.
     * 
     * @param carteObj Carte objectif.
     * 
     * @since 1.0
     */
	
	public SpriteCarteObjectif(CarteObjectif carteObj, GestionnaireInterface GI) {
		
		/*
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
		*/
		System.out.println(carteObj.getDomaine()+"_"+carteObj.getValeur());
		Image I = GI.Cartes.get(carteObj.getDomaine()+"_"+carteObj.getValeur());
		ImageView IView = new ImageView(I);
		IView.setFitWidth(I.getWidth()/6);
		IView.setFitHeight(I.getHeight()/6);
		this.getChildren().add(IView);
	}
}
