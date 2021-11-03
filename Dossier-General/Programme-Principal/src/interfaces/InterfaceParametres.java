package interfaces;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import moteur.Systeme;
import joueur.Joueur;

/**
* @generated
*/
public class InterfaceParametres extends Pane implements UI {
    
    private Systeme systeme;
    private Joueur joueur;
    public GestionnaireInterface GI;
    
    public InterfaceParametres(GestionnaireInterface gi) { 
		super();
		GI = gi;
		Button button1 = new Button("go back"); 
		button1.setOnAction(e -> GI.afficherEcran(GI.root.getChildren().get(0)));
		this.getChildren().addAll( button1);
    }
    
    
    /**
    * affiche systeme
    */
    public Systeme getSysteme() {
        return this.systeme;
    }
    
    /**
    * modifie systeme
    */
    public void setSysteme(Systeme systeme) {
        this.systeme = systeme;
    }
    
    /**
    * affiche joueur
    */
    public Joueur getJoueur() {
        return this.joueur;
    }
    
    /**
    * modifie joueur
    */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public activerSon() {
        //TODO
    }
    /**
    * @generated
    */
    public couperSon() {
        //TODO
    }
    /**
    * @generated
    */
    public activerMusique() {
        //TODO
    }
    /**
    * @generated
    */
    public couperMusique() {
        //TODO
    }
    /**
    * @generated
    */
    public changerLangue() {
        //TODO
    }
    /**
    * @generated
    */
    public changerTheme() {
        //TODO
    }
    
}
