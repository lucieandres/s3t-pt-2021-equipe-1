package javaFX_testZone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 *  Classe d'affichage des cartes objectif.
 * @author S3T - G1
 *
 */
public class SpriteCarteObjectif extends StackPane {
	
	int value = 3;
	
	public SpriteCarteObjectif() throws FileNotFoundException {
		// relative path java edition
	    String Rpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
	    Rpath = Rpath.substring(0, Rpath.length()-4); Rpath = Rpath.substring(6, Rpath.length());
	    Rpath = Rpath+"resources";
	 
	    Image frame = new Image(new FileInputStream(Rpath+"/sprites/carteObjectif/frame.png"));
	    Image icon = new Image(new FileInputStream(Rpath+"/sprites/carteObjectif/economy.png"));
	    ImageView frameView = new ImageView(frame);
	    ImageView iconView = new ImageView(icon);
	    
	    Text Tvalue = new Text();
	    Tvalue.setText(String.valueOf(value));
	    Tvalue.setFill(new Color(0.3,0.25,0.2,1));
	    Tvalue.setFont(Font.font("centaur", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 160));
	    HBox hb = new HBox(Tvalue,iconView);
	    hb.setAlignment(Pos.CENTER);
	    hb.setSpacing(50);
	    this.getChildren().addAll(frameView,hb);
	    
	    double scale = 1;
	    this.setScaleX(scale);
	    this.setScaleY(scale);
	}
}
