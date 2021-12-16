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
	
	/**
     *  Dessine l'interface.
     * 
     * @param gi le gestionnaire d'interface permettra de dessiner l'interface dans la langue séléctionné.
     * 
     * @since 1.0
     */
	
	public void dessineInterface(GestionnaireInterface GI) {
		
	}
}
