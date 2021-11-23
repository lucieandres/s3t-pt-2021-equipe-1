package javaFX_testZone;

import java.io.FileNotFoundException;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
        SpriteCard S1 = new SpriteCard();
        /*SpriteCard S2 = new SpriteCard();
        S2.setTranslateX(50);
        S2.setTranslateY(150*S2.getScaleY());*/
        
        // rotatE
        /*
        RotateTransition rt = new RotateTransition();
        rt.setNode(S1);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setDuration(Duration.millis(100000));
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(-1);
        rt.play();*/
        
        /*
        S1.setOnMouseDragged(new EventHandler<MouseEvent>() {
        	@Override public void handle(MouseEvent mouseEvent) {
        		S1.setTranslateX(mouseEvent.getSceneX() - S1.getWidth());
        		S1.setTranslateY(mouseEvent.getSceneY() - S1.getHeight());
  		  		}
        	});*/

        for(int i=0;i<8;i++) {
        	SpriteCard d = new SpriteCard();
        	d.setTranslateY(i*80-300);
        	d.setTranslateX(300);
        	root.getChildren().add(d);
        }
        
        
        
        root.getChildren().add(S1);
        
        primaryStage.show();
    }
}