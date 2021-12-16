package interfaces;
/**
 * Cette classe est une interface qui représente la page de recherche de partie.
 * 
 *   
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceRecherche extends InterfaceBase {

	public GestionnaireInterface GI = null;
	
	public InterfaceRecherche(GestionnaireInterface GI) {
		super();
		this.GI=GI;
		dessineInterface(GI);
	}
	
	public void dessineInterface(GestionnaireInterface GI) {
		
	}
}
