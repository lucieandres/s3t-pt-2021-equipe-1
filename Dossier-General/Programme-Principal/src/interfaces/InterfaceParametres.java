package interfaces;

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

/**
 * Cette classe est une interface qui représente les paramètres.
 * 
 *   
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceParametres extends InterfaceBase {
    
//    private Joueur joueur;
    public GestionnaireInterface GI;
    
    Button boutonRetour;
    Button boutonGraphique;
    Button boutonMusique;
    Button boutonSon;
    Button boutonTheme;
    Button boutonLangage;
    
    Label titre;
    
    HBox HBTop;
    VBox VBRight;
    VBox VBLeft;
    
	/**
     *  Ce constructeur permet de creer tous les élements de l'interface, c'est-a-dire le titre "Paramètres", le bouton "retour", 
     *  le bouton "Paramètre Graphiques", le bouton "Paramètre Musicaux", le bouton "Paramètres Sonores", le bouton "Theme", le bouton "Langue".
     *  
     * 
     * @param gi le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble
     * 
     * @since 1.0
     */
    public InterfaceParametres(GestionnaireInterface gi) { 
		super();
		GI = gi;
		
		// fond de jeu
        this.setBackground(new Background(new BackgroundFill(Color.MOCCASIN,CornerRadii.EMPTY,null)));
		
        HBTop = new HBox();
		VBRight = new VBox();
		VBLeft = new VBox();
		
		boutonRetour = new Button("Retour");
		boutonRetour.setOnAction(e -> GI.afficherEcran(GI.UIParentID));
		boutonRetour.setFont(Font.font("Comic Sans MS", 20));
		boutonRetour.setPrefWidth(150);
		
		boutonGraphique = new Button("Paramètres Graphiques");
		boutonGraphique.setFont(Font.font("Comic Sans MS", 20));
		boutonGraphique.setPrefWidth(300);
		
		boutonMusique = new Button("Paramètres Musicaux");
		boutonMusique.setFont(Font.font("Comic Sans MS", 20));
		boutonMusique.setPrefWidth(300);
		
		boutonSon = new Button("Paramètres Sonores");
		boutonSon.setFont(Font.font("Comic Sans MS", 20));
		boutonSon.setPrefWidth(300);
		
		boutonTheme = new Button("Thème");
		boutonTheme.setFont(Font.font("Comic Sans MS", 20));
		boutonTheme.setPrefWidth(300);
		
		boutonLangage = new Button("Langue");
		boutonLangage.setFont(Font.font("Comic Sans MS", 20));
		boutonLangage.setPrefWidth(300);
		
		titre = new Label("Paramètres");
		titre.setFont(Font.font("Comic Sans MS", 40));
		
		HBTop.getChildren().add(titre);
		HBTop.setAlignment(Pos.TOP_CENTER);
		HBTop.setMinSize(1920, 10);
		HBTop.setPadding(new Insets(20,0,0,0));
		this.setTop(HBTop);
		
		VBRight.setAlignment(Pos.TOP_RIGHT);
		VBRight.getChildren().add(boutonRetour);
		VBRight.setPadding(new Insets(-40,30,0,0));
		this.setRight(VBRight);
		
		VBLeft.getChildren().addAll(boutonGraphique, boutonMusique, boutonSon, boutonTheme, boutonLangage);
		VBLeft.setAlignment(Pos.TOP_LEFT);
		VBLeft.setMinSize(350, 1000);
		VBLeft.setSpacing(15);
		VBLeft.setPadding(new Insets(250,0,0,40));
		this.setLeft(VBLeft);
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
    
    

//    //                          Operations                                  
//    
//    /**
//    * @generated
//    */
//    public activerSon() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public couperSon() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public activerMusique() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public couperMusique() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public changerLangue() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public changerTheme() {
//        //TODO
//    }
    
}
