package javaFX_testZone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.Timer;

import javafx.animation.FadeTransition;
import javafx.scene.CacheHint;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SpriteCard extends Pane {

	   public SpriteCard() throws FileNotFoundException {         
	      //Creating an image 
	       
	      String Rpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
	      Rpath = Rpath.substring(0, Rpath.length()-4); Rpath = Rpath.substring(6, Rpath.length());
	      Rpath = Rpath+"resources";
	   
	      
	      Image background = new Image(new FileInputStream(Rpath+"/sprites/carteInfluence/customisation/dragon_background.png"));
		  //Image background = new Image(new FileInputStream("C:\\Users\\alexc\\Pictures\\IUT\\breakdown\\0.png"));
		  Image light      = new Image(new FileInputStream(Rpath+"/sprites/carteInfluence/customisation/dragon_light.png"));
	      Image frame 	   = new Image(new FileInputStream(Rpath+"/sprites/carteInfluence/general/frame.png")); 
	      Image Pin   	   = new Image(new FileInputStream(Rpath+"/sprites/carteInfluence/general/icon_normal.png"));
	      Image Color      = new Image(new FileInputStream(Rpath+"/sprites/carteInfluence/general/color.png"));
	      Image PinShadow  = new Image(new FileInputStream(Rpath+"/sprites/carteInfluence/general/icon_normal_shadow.png"));
	      
	      //Setting the image view 
	      ImageView lightView = new ImageView(light); 
	      ImageView backgroundView = new ImageView(background); 
	      ImageView frameView = new ImageView(frame); 
	      ImageView PinView = new ImageView(Pin); 
	      ImageView ColorView = new ImageView(Color); 
	      ImageView PinShadowView = new ImageView(PinShadow); 
	      
	      backgroundView.setX(0);
	      backgroundView.setY(10);
	      
	      PinShadowView.setX(-3);
	      PinShadowView.setY(25);
	      PinShadowView.setClip(new ImageView(frame)); // draw only on the frame
	      // occultism intensifies
	      PinShadowView.setCache(true);
	      PinShadowView.setCacheHint(CacheHint.QUALITY);
	      
	      PinShadowView.setBlendMode(BlendMode.HARD_LIGHT);
	      
	      PinView.setX(5);
	      PinView.setY(5);

	      ColorView.setX(-38); 
	      ColorView.setY(-40); 
	      ColorView.setClip(new ImageView(frame));
	      ColorAdjust col = new ColorAdjust();
	      col.setHue(0);
	      col.setSaturation(0.7);
	      col.setBrightness(0.1);
	      ColorView.setEffect(col);
	      
	      //text gradient
	      //Stop[] stops = new Stop[] { new Stop(0, new Color(0,0,0,1)), new Stop(1,new Color(1,0,0,1))};
	      //LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
	      
	      Text Value = new Text();
	      Value.setText("15");
	      Value.setFill(new Color(0.9,0.9,0.9,1));
	      Value.setX(28);
	      Value.setY(70);
	      //Value.setStyle(".linear-grad-to-bottom-right{-fx-background-color: linear-gradient(to bottom right, #ff7f50, #6a5acd);}");
	      Value.setFont(Font.font("centaur", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 55));
	      
	      //lightAnimation
	      FadeTransition ft = new FadeTransition();
	      ft.setFromValue(1);
	      ft.setToValue(0.3);
	      ft.setDuration(Duration.millis(1000));
	      ft.setCycleCount(-1);
	      ft.setAutoReverse(true);
	      ft.setNode(lightView);
	      ft.play();
	      
	      //Pane resize
	      this.setMaxHeight(frame.getHeight());
	      this.setMaxWidth(frame.getWidth());
	      //this.setStyle("-fx-background-color: #000000");
	      
	      lightView.setBlendMode(BlendMode.COLOR_DODGE);
	      lightView.setCache(true);
	      lightView.setCacheHint(CacheHint.QUALITY);
	      lightView.setClip(new Pane(new ImageView(frame),new ImageView(Pin))); // clip made out of the frame and the pin
	      
	      //shadow
	      Pane shadow = new Pane(new ImageView(background),new ImageView(frame));
	      shadow.setScaleX(1.025); shadow.setScaleY(1.025);
	      //shadow.setTranslateX(50);
	      ColorAdjust c = new ColorAdjust();
	      c.setBrightness(-1);
	      shadow.setEffect(c);
	      shadow.setOpacity(0.4);
	      GaussianBlur gb = new GaussianBlur(); gb.setRadius(12); 
	      Pane dropShadow = new Pane(shadow); // stacking d'effet version wish
	      dropShadow.setEffect(gb);
	      
	      dropShadow.setTranslateX(15);
	      dropShadow.setTranslateY(15);
	      
	      this.getChildren().addAll(dropShadow,backgroundView,frameView,ColorView,PinShadowView,PinView,Value,lightView);
	      double scale = 1;
	      this.setScaleX(scale);
	      this.setScaleY(scale);
	      this.setRotate(0);
	      
	   }
}
