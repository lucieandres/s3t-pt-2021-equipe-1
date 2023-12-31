package pp;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Cette classe est une interface qui represente le menu principal.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceMenu extends InterfaceBase {
    
	public GestionnaireInterfacePP GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	Button boutonOption;
	Button boutonRegles;
	Button boutonJouer;
	Button boutonQuitter;
	Button boutonRejoindre;
	Button bontonCreerPartieEnLigne;
	
	Text titre;
	
	HBox HBHaut;
	VBox VBDroite;
	VBox VBgauche;
	
	/**
     *  Ce constructeur permet de creer tous les elements de l'interface, c'est-a-dire le titre du jeu,
     *  le bouton qui va rediriger vers les parametres, le bouton qui va rediriger vers les regles, le bouton qui va rediriger vers la creation d'une partie et pour finir le bouton "quitter" qui va permettre de sortir du jeu.
     * 
     * @param gi le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble
     * 
     * @since 1.0
     */
	public InterfaceMenu(GestionnaireInterfacePP gi) { // javaFX elements goes into the class constructor
		super();
		this.dessineInterface(gi);
	}

	@Override
	public void dessineInterface(GestionnaireInterfacePP GI) {
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));

//-----------------Créations des composants------------------------------------------------        

//-----------------Créations des composants------------------------------------------------  
		
		boutonJouer = new Button("Creer une partie");
		boutonJouer.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("CreerPartie"))); // switch Pane visibility
		boutonJouer.setPrefWidth(GI.screenBounds.getWidth()*0.26);
		boutonJouer.setStyle("-fx-font: normal 2.5em 'Comic Sans MS' ");
		
		boutonQuitter = new Button("Quitter");
		boutonQuitter.setOnAction(e -> Platform.exit());
		boutonQuitter.setPrefWidth(GI.screenBounds.getWidth()*0.08);
		boutonQuitter.setStyle("-fx-font: normal 2em 'Comic Sans MS' ");
		
		titre = new Text("De Cape et D'Epée");
		titre.setStyle("-fx-font: normal bold 10em 'Pristina' ");
		

//------------------Image------------------------------------------------------------------
	
//------------------Test d'image non-concluant------------------------------------------------------------------
		
//	      String Rpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
//	      Rpath = Rpath.substring(0, Rpath.length()-4); Rpath = Rpath.substring(6, Rpath.length());
//	      Rpath = Rpath+"resources";
//	      VBox VBFond = new VBox();
//	      
//	      try {
//			Image fond = new Image(new FileInputStream(Rpath+"/sprites/UI/menu_image.png"));
//			ImageView vueFond = new ImageView(fond);
//			VBFond.getChildren().add(vueFond);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
	      
//------------------Créations des conteneurs------------------------------------------------		
		
//------------------Conteneur titre---------------------------------------------------------
		VBox VBTopCentre = new VBox();
		VBTopCentre.getChildren().add(titre);
		VBTopCentre.setPadding(new Insets(0,0,0,GI.screenBounds.getWidth()*0.03));

//------------------Conteneur bouton quitter------------------------------------------------
		VBox VBTopDroite = new VBox(boutonQuitter);
		VBTopDroite.setPadding(new Insets(GI.screenBounds.getHeight()*0.04,GI.screenBounds.getWidth()*0.03,0,GI.screenBounds.getWidth()*0.49));

//------------------Conteneur bouton quitter------------------------------------------------
		HBox HBTop = new HBox(VBTopCentre, VBTopDroite);
		this.setTop(HBTop);

//------------------Conteneur boutons-------------------------------------------------------
		VBgauche = new VBox(boutonJouer);
		VBgauche.setMinSize(GI.screenBounds.getWidth()*0.18, GI.screenBounds.getHeight()*0.93);
		this.setLeft(VBgauche);
		VBgauche.setSpacing(GI.screenBounds.getHeight()*0.09);
		VBgauche.setPadding(new Insets(GI.screenBounds.getHeight()*0.14,0,0,GI.screenBounds.getWidth()*0.08));
	}
}
