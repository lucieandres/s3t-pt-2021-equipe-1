package interfaces;

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
