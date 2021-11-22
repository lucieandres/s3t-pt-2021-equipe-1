package javaFX_testZone;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
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
        S1.setTranslateX(100);
        S1.setTranslateY(50);
        /*SpriteCard S2 = new SpriteCard();
        S2.setTranslateX(50);
        S2.setTranslateY(150*S2.getScaleY());*/
        
        root.getChildren().add(S1);
        
        primaryStage.show();
    }
}