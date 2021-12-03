package javaFX_testZone;

import java.io.FileNotFoundException;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    	//System.out.println(screenBounds.toString());
    	
    	// ------------------------------ create a gradient ramp ---------------------------------------------------
    	Rectangle r = new Rectangle();
    	r.setTranslateX(-screenBounds.getWidth()/3);
    	r.setHeight(screenBounds.getHeight()+200);
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
	    
	    r.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> ft.play());
	    
	    // it just work
	    ft.statusProperty().addListener(new ChangeListener<Status>() {
	        @Override
	        public void changed(ObservableValue<? extends Status> observableValue, Status oldValue, Status newValue) {
	              if(newValue==Status.STOPPED){
	            	  System.out.println("my animation is finished, I must go home now");
	              }            
	        }
	    });
	    
	    
	    
	    //-------------------------------------------------------------------- easer --------------------------------------------------------------
	    /*
	    HBox containerCard = new HBox();
	    containerCard.setMaxWidth(100); containerCard.setMaxHeight(100);
	    Rectangle card = new Rectangle(); card.setHeight(100); card.setWidth(100);
	    Rectangle easer = new Rectangle();
	    easer.setHeight(100);
	    easer.setWidth(100);
	    easer.setFill(new Color(0,1,0,1));
	    ScaleTransition ST = new ScaleTransition();
	    ST.setDuration(Duration.millis(400));
	    ST.setFromX(1);
	    ST.setToX(0);
	    ST.setCycleCount(1);
	    //ST.setAutoRevesrse(true);
	    ST.setNode(easer);
	    easer.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> ST.play());
	    containerCard.getChildren().addAll(card,easer);
	    containerCard.setAlignment(Pos.CENTER);
	    */
	    // ----------------------------------------------------------------------------------------------------------------------------------------
    	/*
	    ButtonMenu B1 = new ButtonMenu("play");
	    ButtonMenu B2 = new ButtonMenu("rules");
	    ButtonMenu B3 = new ButtonMenu("help");
	    ButtonMenu B4 = new ButtonMenu("longstringtotestifeverythingisokay");
	    VBox MenuVBox = new VBox(B1,B2,B3,B4);
	    MenuVBox.setAlignment(Pos.CENTER_LEFT);
	    MenuVBox.setTranslateX(-550);
	    //MenuVBox.setTranslateY(500);
	    //System.out.println(MenuVBox.getBoundsInParent().getWidth()); // finally, size!
	    */
	    ButtonMenuReenactement B1 = new ButtonMenuReenactement("play");
	    ButtonMenuReenactement B2 = new ButtonMenuReenactement("rules");
	    ButtonMenuReenactement B3 = new ButtonMenuReenactement("test");
	    ButtonMenuReenactement B4 = new ButtonMenuReenactement("exit");
	    
	    /*
	    ButtonMenuReenactement B5 = new ButtonMenuReenactement("much");
	    ButtonMenuReenactement B6 = new ButtonMenuReenactement("time");
	    ButtonMenuReenactement B7 = new ButtonMenuReenactement("on");
	    ButtonMenuReenactement B8 = new ButtonMenuReenactement("this");
	    ButtonMenuReenactement B9 = new ButtonMenuReenactement("please");
	    ButtonMenuReenactement B10 = new ButtonMenuReenactement("help");
	    ButtonMenuReenactement B11 = new ButtonMenuReenactement("me");
	    */
	    
	    VBox MenuVBox = new VBox(B1,B2,B3,B4);
	    //MenuVBox.setStyle("-fx-background-color: #000000;");
	    MenuVBox.setMaxHeight(B1.getBoundsInParent().getHeight()*MenuVBox.getChildren().size());
	    MenuVBox.setMaxWidth(300);
	    //MenuVBox.setAlignment(Pos.CENTER_LEFT);
	    MenuVBox.setTranslateX(-500);
	    MenuVBox.setSpacing(20);
	    MenuVBox.setAlignment(Pos.CENTER_LEFT);
	    //MenuVBox.setTranslateY(500);
	    
	    // ----------------------------------------------------------------------------------------------------------------------------------------
        primaryStage.setTitle("javaFX_testZone");
        //primaryStage.setFullScreen(true);
        StackPane root = new StackPane();
        Scene S = new Scene(root, 1920, 1080);
        S.setFill(new Color(0,0,0,0.8));
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
        
        // --------------------------------------------------- rotatE ------------------------------------------------------------
        /*
        RotateTransition rt = new RotateTransition();
        rt.setNode(S1);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setDuration(Duration.millis(10000));
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
        SpriteCarteObjectif SO = new SpriteCarteObjectif();
        SO.setTranslateX(450);
        SO.setTranslateY(-250);
        root.getChildren().addAll(S1,SO,r,MenuVBox,new ButtonMenuReenactement("spend"));
        
        primaryStage.show();
    }
}