package pp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Cette classe est une interface qui représente la salle d'attente avant le lancement d'une partie.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceAttente extends InterfaceBase {
    
	public GestionnaireInterfacePP GI = null;
	
//    /**
//    * attribut joueur de la classe Joueur, package joueur
//    */
//    private Joueur joueur;
    
    /**
     * Attribut renseignant si la case "pret" est cochée ou non, 
     * si tous les joueurs d'une partie l'ont cochée, la partie peut commencer
     */
     private boolean estPret = false;
    
    
     public InterfaceAttente(GestionnaireInterfacePP gi) {
    	
    	super();
    	dessineInterface(gi);
    	 
     }


	@Override
	public void dessineInterface(GestionnaireInterfacePP GI) {
 
 		Button boutonRetour;
 		
 		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
         
         	
 		VBox VBCentre = new VBox();
 		VBCentre.setPrefSize(1520, 1080);
 		VBCentre.setAlignment(Pos.TOP_CENTER);
 		
 		Label Titre = new Label("Salon");
 		Titre.setFont(Font.font("Pristina", FontWeight.BOLD, 120));
 		Titre.setPadding(new Insets(20, 20, 0, 0));
 		
 		Label sousTitre = new Label("en cours ...");
 		sousTitre.setFont(Font.font("Pristina", FontWeight.BOLD, 100));
 		sousTitre.setPadding(new Insets(0, 20, 0, 0));
         
         VBCentre.getChildren().addAll(Titre, sousTitre);
         VBCentre.setSpacing(50);
         VBCentre.setPadding(new Insets(0,0,0,400));
         VBCentre.setAlignment(Pos.TOP_CENTER);
 		
 		// VBDroite qui va contenir le bouton Annuler		
 		VBox VBDroite = new VBox();
 		VBDroite.setPrefSize(400,0);
 		VBDroite.setAlignment(Pos.TOP_RIGHT);
 		VBDroite.setPadding(new Insets(50,50,0,0));

 		
 		// Bouton Annuler
 		boutonRetour = new Button();
 		boutonRetour.setText("Quittez");
 		boutonRetour.setFont(Font.font("Comic sans MS", FontWeight.MEDIUM, 20));
 		boutonRetour.setPrefSize(150, 50);
 		
 		VBDroite.getChildren().add(boutonRetour);
 		
 		// Mettre les VBox
 		this.setCenter(VBCentre);
 		this.setRight(VBDroite);
 		
 		
 		boutonRetour.setOnAction(e -> {
 			GI.afficherEcran(GI.InterfaceMap.get("Menu"));
 		});
		
	}
    
//    /**
//    * affiche joueur
//    */
//    public Joueur getJoueur() {
//        return this.joueur;
//    }
//    
//    /**
//    * modifie joueur
//    */
//    public void setJoueur(Joueur joueur) {
//        this.joueur = joueur;
//    }
//    
//    /**
//     * affiche estPret
//     */
//    public boolean getEstPret() {
//		return estPret;
//	}
//
//    /**
//     * modifie estPret
//     */
//	public void setEstPret(boolean estPret) {
//		this.estPret = estPret;
//	}
//	
//    
//
//    //                          Operations                                  
//    
//    /**
//    * si la case "pret" est cochee, l'attribut estPret devient true
//    */
//    public void estPret() {
//        estPret = true;
//    }
    
}
