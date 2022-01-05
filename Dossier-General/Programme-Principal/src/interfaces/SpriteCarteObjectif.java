package interfaces;


import cartes.CarteObjectif;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
	
	private boolean traitreSelection = false;

	
	/**
     *  Ce constructeur permet de creer graphiquement une carte objectif pour apres l'interger a l'interface du plateau de jeu dans la classe InterfaceJeu.
     * 
     * @param carteObj Carte objectif.
     * 
     * @since 1.0
     */
	
	public SpriteCarteObjectif(CarteObjectif carteObj, Color c, GestionnaireInterface GI) {
		double coefScale = 7;
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
		if (carteObj != null) {
			//System.out.println(carteObj.getDomaine()+"_"+carteObj.getValeur());
			Image I = GI.Cartes.get(carteObj.getDomaine()+"_"+carteObj.getValeur());
			ImageView IView = new ImageView(I);
			IView.setFitWidth(I.getWidth()/coefScale);
			IView.setFitHeight(I.getHeight()/coefScale);
			this.getChildren().add(IView);
			
			if(c != null) {
				Circle cercle = new Circle();
				cercle.setFill(c);
				cercle.setRadius(20);
				cercle.setTranslateX(50.0);
				cercle.setTranslateY(-25.0);
				this.getChildren().add(cercle);
			}
		}
	}
	/**
	 * définit la séléction du traitre
	 * @param TS le booléen à définir.
	 */
	public void setTraitreSelection(boolean TS) {
		traitreSelection = TS;
	}
	/**
	 * retourne la sélection du traitre.
	 * @return
	 */
	public boolean getTraitreSelection() {
		return traitreSelection;
	}
}
