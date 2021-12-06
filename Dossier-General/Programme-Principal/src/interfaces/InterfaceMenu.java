package interfaces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Cette classe est une interface qui represente le menu principal.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceMenu extends InterfaceBase {
    
	public GestionnaireInterface GI = null; // link to the prime instance of GestionnaireInterface is required to go back
	
	Button boutonOption;
	Button boutonRegles;
	Button boutonJouer;
	Button boutonQuitter;
	Button boutonRejoindre;
	
	Label titre;
	
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
	public InterfaceMenu(GestionnaireInterface gi) { // javaFX elements goes into the class constructor
		super();
		GI = gi;
		
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));

//-----------------Créations des composants------------------------------------------------        

//-----------------Créations des composants------------------------------------------------  
		boutonOption = new Button("Paramètres"); 
		boutonOption.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("parametres"))); // switch Pane visibility
		boutonOption.setPrefWidth(500);
		boutonOption.setFont(Font.font("Comic Sans MS", 30));
		
		boutonRegles = new Button("Règles");
		boutonRegles.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("regles"))); // switch Pane visibility
		boutonRegles.setPrefWidth(500);
		boutonRegles.setFont(Font.font("Comic Sans MS", 30));
		
		boutonJouer = new Button("Jouer en Local");
		boutonJouer.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("creerPartie"))); // switch Pane visibility
		boutonJouer.setPrefWidth(500);
		boutonJouer.setFont(Font.font("Comic Sans MS", 30));
		
		boutonRejoindre = new Button("Rejoindre une partie");
		boutonRejoindre.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("rejoindre")));
		boutonRejoindre.setPrefWidth(500);
		boutonRejoindre.setFont(Font.font("Comic Sans MS", 30));
		
		boutonQuitter = new Button("Quitter");
		boutonQuitter.setOnAction(e -> Platform.exit());
		boutonQuitter.setPrefWidth(150);
		boutonQuitter.setFont(Font.font("Comic Sans MS", 20));
		
		titre = new Label("De Cape et D'Epée");
		titre.setFont(Font.font("Pristina", FontWeight.BOLD,120));
		

//------------------Image------------------------------------------------------------------
	
//------------------Test d'image non-concluant------------------------------------------------------------------
	/*
	  String Rpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
	  Rpath = Rpath.substring(0, Rpath.length()-4); Rpath = Rpath.substring(6, Rpath.length());
	  Rpath = Rpath+"resources";
	  VBox VBFond = new VBox();
	      
	  	try {
		Image fond = new Image(new FileInputStream(Rpath+"/sprites/UI/menu_image.png"));
		ImageView vueFond = new ImageView(fond);
		VBFond.getChildren().add(vueFond);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		*/
	      
//------------------Créations des conteneurs------------------------------------------------		
		
//------------------Conteneur titre---------------------------------------------------------
		VBox VBTopCentre = new VBox();
		VBTopCentre.getChildren().add(titre);
		//VBTopCentre.getChildren().add(VBFond);
		VBTopCentre.setPadding(new Insets(0,0,0,50));

//------------------Conteneur bouton quitter------------------------------------------------
		VBox VBTopDroite = new VBox(boutonQuitter);
		VBTopDroite.setPadding(new Insets(40,50,0,950));

//------------------Conteneur bouton quitter------------------------------------------------
		HBox HBTop = new HBox(VBTopCentre, VBTopDroite);
		this.setTop(HBTop);

//------------------Conteneur boutons-------------------------------------------------------
		VBgauche = new VBox(boutonRegles, boutonJouer, boutonRejoindre, boutonOption);
		VBgauche.setMinSize(350, 1000);
		this.setLeft(VBgauche);
		VBgauche.setSpacing(100);
		VBgauche.setPadding(new Insets(150,0,0,150));
		
	}
}
