package interfaces;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * Cette classe est la classe parente de toutes les interface, elle contient des fonction communes à toutes les interfaces.
 *  
 * @author S3T - G1
 * 
 * @since 1.0
 */
abstract class InterfaceBase extends BorderPane {
	
	private int transitionTime = 1000;

	/**
     * Cette méthode permet losqu'elle est appelé de faire une transition de scène, permettant un dégradé.
     * 
     * 
     * @param GI Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @param Destination Nouvelle interface à afficher.
     * 
     * @param TransitionType Type de trasition choisie
     * 
     * @since 1.0
     */
    
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
	
	/**
     * Cette méthode permet losqu'elle est appelé de faire une transition de scène, permettant un dégradé.
     * 
     * 
     * @param GI Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @param Destination Nouvelle interface à afficher.
     * 
     * @param TransitionType Type de trasition choisie.
     * 
     * @param NouveauBackground Nouveau fond.
     * 
     * @since 1.0
     */
	public void goToScene(GestionnaireInterface GI,InterfaceBase Destination,Transition TransitionType,InterfaceBase NouveauBackground) {
		switch(TransitionType) {
		case FADETOBLACK:
			
			break;
		default:
			
			break;
		}
	}
	
	/**
     *  Cette méthode permet de rafraichir l'interface.
     * 
     * @param GI le gestionnaire d'interface permettra de dessiner l'interface dans la langue séléctionné.
     * 
     * @since 1.0
     */
	
	public abstract void dessineInterface(GestionnaireInterface GI);
}
