package javaFX_testZone;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ButtonMenu extends Button {
	
	int spacing = 100;
	String text = "Button";
	double animationProgress = 0.0;
	
	public ButtonMenu() {
		
		this.setText(text);
		
		Bloom bloom = new Bloom(); 
		bloom.setThreshold(0.5); 
	    this.setEffect(bloom);      
		
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
            	System.out.println("e");
            }
        });
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
            	//this.setImage(selected);
            }
        });
	}
	
	
	public ButtonMenu(String t, int s) {
		text = t;
		spacing = s;
	}
	
	
	
	
	public void updateImages(final Image selected, final Image unselected) {
        final ImageView iv = new ImageView(selected);
        this.getChildren().add(iv);

        iv.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                iv.setImage(unselected);
            }
        });
        iv.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                iv.setImage(selected);
            }
        });

        super.setGraphic(iv);
    }
}
