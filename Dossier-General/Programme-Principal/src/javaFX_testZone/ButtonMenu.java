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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ButtonMenu extends StackPane {
	
	int spacing = 100;
	String text = "Button";
	double animationProgress = 0.0;
	Button b = new Button();
	
	
	public ButtonMenu() throws FileNotFoundException {
		
		// relative path java edition
	    String Rpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
	    Rpath = Rpath.substring(0, Rpath.length()-4); Rpath = Rpath.substring(6, Rpath.length());
	    Rpath = Rpath+"resources";
	    
	    Image arrow = new Image(new FileInputStream(Rpath+"/sprites/UI/menu_arrow.png"));
	    
	    this.setAlignment(Pos.CENTER);
	    
		b.setText(text);
		b.setStyle("-fx-background-color: transparent;");
		b.setFont(Font.font("centaur", FontWeight.THIN, FontPosture.REGULAR, 55));
		b.setTextFill(new Color(1,1,1,1));
		
	    Text TextBloom = new Text();
	    TextBloom.setTranslateY(3);
	    TextBloom.setText(text);
	    TextBloom.setOpacity(0);
	    TextBloom.setFont(Font.font("centaur", FontWeight.THIN, FontPosture.REGULAR, 55));
	    TextBloom.setFill(new Color(1,1,1,1));
	    Bloom bloom = new Bloom();
		bloom.setThreshold(0.1);
	    TextBloom.setEffect(bloom);
	    
	    ImageView arrowView = new ImageView(arrow);
	    arrowView.setFitHeight(32);
	    arrowView.setFitWidth(32);
	    arrowView.setTranslateX(-90);
	    arrowView.setTranslateY(3);
	    arrowView.setOpacity(0);
	    
		this.getChildren().addAll(arrowView,TextBloom,b);
		
		//this.setMaxHeight(b.getHeight());
	    //this.setMaxWidth(b.getWidth());
		FadeTransition ft = new FadeTransition();
		ft.setInterpolator(Interpolator.EASE_BOTH);
		ft.setDuration(Duration.millis(100));
		ft.setNode(TextBloom);
		
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
            	
        	    ft.setFromValue(TextBloom.getOpacity());
        	    ft.setToValue(1);
        	    Duration time = ft.getCurrentTime();
                ft.setRate(1);
                ft.playFrom(time);
            }
        });
		b.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {

            	ft.setFromValue(TextBloom.getOpacity());
        	    ft.setToValue(0);
                ft.setRate(1);
                ft.playFrom(Duration.ZERO);
            }
        });
	}
	
	public ButtonMenu(String t, int s) {
		text = t;
		spacing = s;
	}
	
	
	
}
