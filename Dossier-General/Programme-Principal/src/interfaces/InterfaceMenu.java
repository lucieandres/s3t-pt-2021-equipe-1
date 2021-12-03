package interfaces;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class InterfaceMenu extends BorderPane implements UI {
    
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

		boutonOption = new Button("Paramètres"); 
		boutonOption.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("parametres"))); // switch Pane visibility
		boutonOption.setPrefWidth(150);
		boutonOption.setFont(Font.font("Comic Sans MS", 20));
		
		boutonRegles = new Button("Règles");
		boutonRegles.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("regles"))); // switch Pane visibility
		boutonRegles.setPrefWidth(150);
		boutonRegles.setFont(Font.font("Comic Sans MS", 20));
		
		boutonJouer = new Button("Jouer en Local");
		boutonJouer.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("creerPartie"))); // switch Pane visibility
		boutonJouer.setPrefWidth(150);
		boutonJouer.setFont(Font.font("Comic Sans MS", 20));
		
		boutonRejoindre = new Button("Rejoindre une partie");
		boutonRejoindre.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("rejoindre")));
		boutonRejoindre.setPrefWidth(150);
		boutonRejoindre.setFont(Font.font("Comic Sans MS", 20));
		
		boutonQuitter = new Button("Quitter");
		boutonQuitter.setOnAction(e -> Platform.exit());
		boutonQuitter.setPrefWidth(150);
		boutonQuitter.setFont(Font.font("Comic Sans MS", 20));
		
		titre = new Label("De Cape et D'Epée");
		titre.setFont(Font.font("Pristina", FontWeight.BOLD,120));

//------------------Créations des conteneurs------------------------------------------------		
		
		HBHaut = new HBox();
		HBHaut.setAlignment(Pos.TOP_CENTER);
		HBHaut.setMinSize(1920,0);
		HBHaut.setPadding(new Insets(20,0,0,0));
		HBHaut.getChildren().add(titre);
		this.setTop(HBHaut);
				
		VBDroite = new VBox();
		VBDroite.getChildren().add(boutonQuitter);
		VBDroite.setAlignment(Pos.TOP_RIGHT);
		VBDroite.setPadding(new Insets(-178,30,0,0));
		this.setRight(VBDroite);
		
		VBgauche = new VBox();
		VBgauche.getChildren().addAll(boutonRegles, boutonJouer, boutonRejoindre, boutonOption);
		VBgauche.setAlignment(Pos.TOP_LEFT);
		VBgauche.setMinSize(350, 1000);
		this.setLeft(VBgauche);
		VBgauche.setSpacing(25);
		VBgauche.setPadding(new Insets(250,0,0,80));
		
	}
}
