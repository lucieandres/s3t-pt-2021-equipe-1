package interfaces;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Properties;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class InterfaceBase extends BorderPane {
	
	private int transitionTime = 1000;
	
	protected LinkedHashMap<Labeled,String> texteInterface = new LinkedHashMap<Labeled,String>();
	
	
	
	
	public void goToScene(GestionnaireInterface GI,InterfaceBase Destination,Transition TransitionType) {
		switch(TransitionType) {
		case FADEBOUNCE:
			FadeTransition ft = new FadeTransition();
		    ft.setFromValue(1);
		    ft.setToValue(0);
		    ft.setDuration(Duration.millis(transitionTime));
		    ft.setCycleCount(1);
		    ft.setNode(this);
		    ft.play();
	        ft.statusProperty().addListener(new ChangeListener<Status>() {
		        @Override
		        public void changed(ObservableValue<? extends Status> observableValue, Status oldValue, Status newValue) {
		              if(newValue==Status.STOPPED){
		            	  	GI.afficherEcran(Destination);
		            	  	FadeTransition ft = new FadeTransition();
		      		      	ft.setFromValue(0);
		      		      	ft.setToValue(1);
		      		    	ft.setDuration(Duration.millis(transitionTime));
		      		    	ft.setCycleCount(1);
		      		    	ft.setNode(Destination);
		      		    	ft.play();
		              }            
		        }
		    });
			break;
		case FADE:
			
			break;
		default:
			
			break;
		}
	}
	
	public void goToScene(GestionnaireInterface GI,InterfaceBase Destination,Transition TransitionType,InterfaceBase NouveauBackground) {
		switch(TransitionType) {
		case FADETOBLACK:
			
			break;
		default:
			
			break;
		}
	}
	

	
	/**
	 * Cette méthode permet de rafraichir le texte.
	 * 
	 * @param texte texte.
	 * 
	 * @param fichierTexte texte.
	 * 
	 * @since 1.0
	 */
	public void rafraichirTexte(Properties fichierTexte) {

		for (Entry<Labeled, String> element : texteInterface.entrySet()) {
			element.getKey().setText(fichierTexte.getProperty(element.getValue()));
        }
	}
	
	public LinkedHashMap<Labeled,String> getTexteInterface() {
		return texteInterface;
	}
	
	
}
