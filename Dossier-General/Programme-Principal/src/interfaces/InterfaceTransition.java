package interfaces;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Cette classe permet d'avoire des transitions entre les interfaces.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceTransition extends InterfaceBase {
	public InterfaceTransition() {
		this.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,null)));
	}
	
	/**
     *  Dessine l'interface.
     * 
     * @param gi le gestionnaire d'interface permettra de dessiner l'interface dans la langue séléctionné.
     * 
     * @since 1.0
     */
	
	@Override
	public void dessineInterface(GestionnaireInterface GI) {
		// TODO Auto-generated method stub
		
	}
}
