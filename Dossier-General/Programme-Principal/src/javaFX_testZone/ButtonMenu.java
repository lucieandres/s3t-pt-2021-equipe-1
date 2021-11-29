package javaFX_testZone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ButtonMenu extends StackPane {
	
	int spacing = 60;
	double bloomThreshold = 0.1;
	
	public ButtonMenu(String text) throws FileNotFoundException {
		
		// relative path java edition
	    String Rpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
	    Rpath = Rpath.substring(0, Rpath.length()-4); Rpath = Rpath.substring(6, Rpath.length());
	    Rpath = Rpath+"resources";
	    
	    Image arrow = new Image(new FileInputStream(Rpath+"/sprites/UI/menu_arrow.png"));
	    
	    this.setAlignment(Pos.CENTER);
	    
	    Button b = new Button();
		b.setText(text);
		b.setStyle("-fx-background-color: transparent;");
		b.setFont(Font.font("centaur", FontWeight.THIN, FontPosture.REGULAR, 40));
		b.setTextFill(new Color(1,1,1,1));
		
		Bloom bloom = new Bloom();
		bloom.setThreshold(bloomThreshold);
		
	    Text TextBloom = new Text();
	    TextBloom.setTranslateY(3);
	    TextBloom.setText(text);
	    //TextBloom.setOpacity(0);
	    TextBloom.setFont(Font.font("centaur", FontWeight.THIN, FontPosture.REGULAR, 40));
	    TextBloom.setFill(new Color(1,1,1,1));
	    TextBloom.setEffect(bloom);
	    
	    ImageView arrowView = new ImageView(arrow);
	    arrowView.setFitHeight(22);
	    arrowView.setFitWidth(22);
	    arrowView.setTranslateX(-spacing);
	    arrowView.setTranslateY(3);
	    arrowView.setEffect(bloom);
	    //arrowView.setOpacity(0);
	    
	    StackPane p = new StackPane(arrowView,TextBloom);
	    p.setOpacity(0);
	    
		this.getChildren().addAll(p,b);
		//this.setMaxHeight(0);
		//this.setMaxWidth(0);
		System.out.println(b.getBoundsInParent().getHeight());
		
		//this.setMaxHeight(b.getHeight());
	    //this.setMaxWidth(b.getWidth());
		FadeTransition ft = new FadeTransition();
		ft.setInterpolator(Interpolator.EASE_BOTH);
		ft.setDuration(Duration.millis(100));
		ft.setNode(p);
		
		//p.setStyle("-fx-background-color: #000000;");
		
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
            	
        	    ft.setFromValue(p.getOpacity());
        	    ft.setToValue(1);
        	    Duration time = ft.getCurrentTime();
                ft.setRate(1);
                ft.playFrom(time);
                
            }
        });
		b.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {

            	ft.setFromValue(p.getOpacity());
        	    ft.setToValue(0);
                ft.setRate(1);
                ft.playFrom(Duration.ZERO);
            }
        });
	}	
}
