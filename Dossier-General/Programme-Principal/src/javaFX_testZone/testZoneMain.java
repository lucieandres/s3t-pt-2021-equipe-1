package javaFX_testZone;

import java.io.FileNotFoundException;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class testZoneMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
    	
    	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    	
    	
    	//create a gradient ramp
    	Rectangle r = new Rectangle();
    	r.setTranslateX(-screenBounds.getWidth()/3);
    	r.setHeight(screenBounds.getHeight());
    	r.setWidth(screenBounds.getWidth()/4);
    	r.setOpacity(0.8);
    	double opacityRamp = 0.2;
    	
    	Stop[] stops = new Stop[] { 
    			new Stop(0, new Color(0,0,0,0)), 
    			new Stop(opacityRamp*0.3, new Color(0,0,0,0.5)), 
    			new Stop(opacityRamp*0.7, new Color(0,0,0,0.85)), 
    			new Stop(opacityRamp, Color.BLACK), 
    			new Stop(1-opacityRamp, Color.BLACK),
    			new Stop(1-opacityRamp + opacityRamp*0.3, new Color(0,0,0,0.85)), 
    			new Stop(1-opacityRamp + opacityRamp*0.7, new Color(0,0,0,0.5)), 
    			new Stop(1, new Color(0,0,0,0))
    	};  
    	
    	LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);  
    	r.setFill(linear);
    	
    	
    	
    	FadeTransition ft = new FadeTransition();
	    ft.setFromValue(r.getOpacity());
	    ft.setToValue(0);
	    ft.setDuration(Duration.millis(400));
	    ft.setCycleCount(2);
	    ft.setAutoReverse(true);
	    ft.setInterpolator(Interpolator.EASE_BOTH);
	    ft.setNode(r);
	    //ft.play();
	    System.out.println(ft.getStatus());
	    
	    // it just work
	    ft.statusProperty().addListener(new ChangeListener<Status>() {
	        @Override
	        public void changed(ObservableValue<? extends Status> observableValue, Status oldValue, Status newValue) {
	              if(newValue==Status.STOPPED){
	            	  System.out.println("my animation is finished, I must go home now");
	              }            
	        }
	    });
	    
    	
        primaryStage.setTitle("javaFX_testZone");
        primaryStage.setFullScreen(true);
        StackPane root = new StackPane();
        Scene S = new Scene(root, 700, 700);
        S.setFill(new Color(0,0,0,0.7));
        primaryStage.setScene(S);
        SpriteCarteInfluence S1 = new SpriteCarteInfluence();
        /*SpriteCard S2 = new SpriteCard();
        S2.setTranslateX(50);
        S2.setTranslateY(150*S2.getScaleY());*/
        
        // window icon
	    String Rpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
	    Rpath = Rpath.substring(0, Rpath.length()-4); Rpath = Rpath.substring(6, Rpath.length());
	    Rpath = Rpath+"resources";
        primaryStage.getIcons().add(new Image(Rpath+"/sprites/carteInfluence/general/icon_normal.png"));
        
        // rotatE
        RotateTransition rt = new RotateTransition();
        rt.setNode(S1);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setDuration(Duration.millis(10000));
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(-1);
        rt.play();
        
        
        
        /*
        S1.setOnMouseDragged(new EventHandler<MouseEvent>() {
        	@Override public void handle(MouseEvent mouseEvent) {
        		S1.setTranslateX(mouseEvent.getSceneX() - S1.getWidth());
        		S1.setTranslateY(mouseEvent.getSceneY() - S1.getHeight());
  		  		}
        	});*/
        SpriteCarteObjectif SO = new SpriteCarteObjectif();
        SO.setTranslateX(-500);
        SO.setTranslateY(-250);
        root.getChildren().addAll(S1,SO,r);
        
        primaryStage.show();
    }
}