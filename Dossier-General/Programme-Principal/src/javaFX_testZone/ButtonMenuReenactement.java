package javaFX_testZone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ButtonMenuReenactement extends Pane {

	
	
	public ButtonMenuReenactement(String text) throws FileNotFoundException {
		
		// relative path java edition ------------------------------------------------------------------------------------------
	    String Rpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
	    Rpath = Rpath.substring(0, Rpath.length()-4); Rpath = Rpath.substring(6, Rpath.length());
	    Rpath = Rpath+"resources";
	    
	    // load image (provisional) --------------------------------------------------------------------------------------------
	    Image arrow = new Image(new FileInputStream(Rpath+"/sprites/UI/menu_arrow.png"));
	    
	    // Bloom ---------------------------------------------------------------------------------------------------------------
	    
	    Bloom bloom = new Bloom();
		bloom.setThreshold(0.1);
		
		// text -----------------------------------------------------------------------------------------------------------------
		Text T = new Text(text);
		T.setStyle("-fx-background-color: transparent;");
		T.setFont(Font.font("centaur", FontWeight.THIN, FontPosture.REGULAR, 55));
		T.setFill(new Color(1,1,1,1));
		
		Text TBloom = new Text(text);
		TBloom.setStyle("-fx-background-color: transparent;");
		TBloom.setFont(Font.font("centaur", FontWeight.THIN, FontPosture.REGULAR, 55));
		TBloom.setFill(new Color(1,1,1,1));
		TBloom.setEffect(bloom);
		
		// cursor view ---------------------------------------------------------------------------------------------------------
	    ImageView arrowView = new ImageView(arrow);
	    arrowView.setFitHeight(22);
	    arrowView.setFitWidth(22);
	    arrowView.setTranslateX(-35);
	    arrowView.setTranslateY(-T.getBoundsInParent().getHeight()/3);
	    arrowView.setEffect(bloom);
		
		// Bloom Nodes ----------------------------------------------------------------------------------------------------------
		
		Pane p = new Pane(TBloom,arrowView);
		p.setOpacity(0);
		
		// fade animation -------------------------------------------------------------------------------------------------------
		
		int time = 100;
		
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
            	FadeTransition ft = new FadeTransition();
        		ft.setDuration(Duration.millis(time));
        		ft.setNode(p);
        	    ft.setFromValue(p.getOpacity());
        	    ft.setToValue(1);
        	    ft.play();
            }
        });
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
            	FadeTransition ft = new FadeTransition();
        		ft.setDuration(Duration.millis(time));
        		ft.setNode(p);
            	ft.setFromValue(p.getOpacity());
        	    ft.setToValue(0);
        	    ft.play();
            }
        });
		
		// combine everything (and setup size) ---------------------------------------------------------------------------------
		
		//this.setStyle("-fx-background-color: #000000;");
		
		this.setMaxHeight(T.getBoundsInParent().getHeight()-20);
		this.setMaxWidth(T.getBoundsInParent().getWidth());
		this.setMaxWidth(250);
		this.getChildren().addAll(p,T);
		
		ButtonMenuReenactement self = this;
		
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
            	
            }
        });
		
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
            	
            }
        });
		
	}
}
