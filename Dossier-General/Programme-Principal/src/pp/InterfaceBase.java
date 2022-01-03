package pp;

import interfaces.Transition;
import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * Classe abstraite représentant une interface modèle pour les autres interfaces.
 * @author S3T - G1
 *
 */
public abstract class InterfaceBase extends BorderPane {
	
	private int transitionTime = 1000;

	
	public void goToScene(GestionnaireInterfacePP GI,InterfaceBase Destination,Transition TransitionType) {
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
	
	public void goToScene(GestionnaireInterfacePP GI,InterfaceBase Destination,Transition TransitionType,InterfaceBase NouveauBackground) {
		switch(TransitionType) {
		case FADETOBLACK:
			
			break;
		default:
			
			break;
		}
	}
	
	public abstract void dessineInterface(GestionnaireInterfacePP GI);
}
