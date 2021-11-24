package javaFX_testZone;

import java.io.FileNotFoundException;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class testZoneMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("javaFX_testZone");
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
        root.getChildren().addAll(S1,SO);
        
        primaryStage.show();
    }
}