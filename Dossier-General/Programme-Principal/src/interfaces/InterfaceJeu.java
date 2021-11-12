package interfaces;

import cartes.CarteInfluence;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import moteur.Data;

/**
 * Cette classe est l'interface du jeu.
 * C'est sur cette interface que toutes actions entre les joueurs et le systeme vont se passer.
 * 
 * @author S3T - G1
 * 
 * @since 1.0
 */
public class InterfaceJeu extends BorderPane implements UI {
    
	/**
     *  Ce constructeur permet de creer tous les elements de l'interface, c'est-a-dire le bouton pour quitter, le bouton pour voir 
     *  les regles, le bouton pour aller le texte pour voir qui doit jouer, la grille des cartes du plateau et la main du joueur.
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble
     * 
     * @since 1.0
     */
    
    public InterfaceJeu(GestionnaireInterface GI) {
    	
    	//    bouton règle 
        
        Button BouttonRegle = new Button("Règles");        
        BouttonRegle.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("regles")));
        
        //    text joueur qui joue
        
        Label textJoueur = new Label("C'est le joueur x qui joue\nPochain joueur : joueur x");
                
        textJoueur.setMaxWidth(150);
        textJoueur.setWrapText(true);
        
        //    bouton quitté
        
        Button buttonQuit = new Button("Quitter");
        buttonQuit.setOnAction(e -> Platform.exit());
        
        //    Bouton option
        
        Button option = new Button("Option");
        option.setOnAction(e -> GI.afficherEcran(GI.InterfaceMap.get("parametres")));
        
        //Cr�ation d'un AnchorPane pour tout recueillir (regle,  ...)
        
        AnchorPane anchor= new AnchorPane(); 
        anchor.getChildren().addAll(BouttonRegle,textJoueur,buttonQuit,option);
    	
        // Position bouton r�gle
        BouttonRegle.setPadding(new Insets(50, 100, 50, 100));
        AnchorPane.setLeftAnchor(BouttonRegle, 20.0 );
        AnchorPane.setTopAnchor(BouttonRegle,900.0 );

        // Position text tour joueur
        AnchorPane.setLeftAnchor(textJoueur, 20.0 );
        AnchorPane.setTopAnchor(textJoueur,20.0 ); 

        // Position bouton quitter
        buttonQuit.setPadding(new Insets(25, 50, 25, 50));
        AnchorPane.setLeftAnchor(buttonQuit, 1750.0 );
        AnchorPane.setTopAnchor(buttonQuit,20.0 );

        // Position bouton option
        option.setPadding(new Insets(25, 50, 25, 50));
        AnchorPane.setLeftAnchor(option, 1600.0 );
        AnchorPane.setTopAnchor(option,20.0 );

        this.setBottom(anchor);
        
        
    	
    }
    
	/**
     * Cette methode permet de dessiner la grille de la partie pour jouer.
     * 
     * 
     * @param gi Le gestionnaire d'interface permettra de relier cette interface aux autres pour qu'elle puisse communiquer ensemble.
     * 
     * @since 1.0
     */
    
    public void drawPartie(GestionnaireInterface GI) {
    	AnchorPane v = new AnchorPane();
    	//v.setAlignment(Pos.TOP_CENTER);
    	v.setPrefSize(1920, 970);
    	
    	Rectangle2D screen = Screen.getPrimary().getBounds();
    	
    	HBox HC = drawColonne(GI);
    	HBox HM = drawMain(GI.getData());
    	
    	AnchorPane.setTopAnchor(HC,50.0 );
    	AnchorPane.setBottomAnchor(HM,100.0 );
    	//AnchorPane.setLeftAnchor(HC,screen.getWidth()/2.0);
    	//AnchorPane.setLeftAnchor(HM,screen.getWidth()/2.0);
    	
    	v.getChildren().add(HC);
    	v.getChildren().add(HM);
    	GI.Jeux.setCenter(v);   	
    }
    
    /**
     * Cette methode permet de dessiner la main du joueur
     * 
     * 
     * @param data Donn�e du jeu qui permettront de savoir o� en est le jeu.
     * 
     * @since 1.0
     */
    
    public HBox drawMain(Data data) { 
        HBox mainJoueur = new HBox();
        mainJoueur.setSpacing(10);
        mainJoueur.setAlignment(Pos.BOTTOM_CENTER);
        
        for(int i = 0; i < data.getMaster().getMain().length ;i++) {
        //for(CarteInfluence x: data.getMaster().getMain()) {
        	SpriteCarteInfluence SPI = new SpriteCarteInfluence(data.getMaster().getMain()[i]);
        	final int j = i;
        	SPI.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> data.getMaster().setCarteSelectionnee(j));
        	//SPI.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(data.getMaster().getMain()[i].getNom()));
        	mainJoueur.getChildren().add(SPI);
        }
    	return mainJoueur;
    }
    
    /**
     * Cette methode permet de dessiner les colonnes
     * 
     * 
     * @param data Donn�e du jeu qui permettront de savoir o� en est le jeu.
     * 
     * @since 1.0
     */
    
    public HBox drawColonne(GestionnaireInterface GI) { 
    	Data data = GI.getData();
    	HBox Colonnes = new HBox();
        Colonnes.setSpacing(10);
        Colonnes.setAlignment(Pos.CENTER);
        
        for(int i=0;i<data.getJoueurs().length;i++) {
        	VBox h = new VBox();
        	final int k = i;
        	h.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> data.jouerCarte(data.getMaster().getCarteSelectionnee(),k));
        	h.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> GI.rafraichir(GI));
        	h.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(data.getMaster().getMain()));
        	h.setSpacing(10);
        	h.getChildren().add(new SpriteCarteObjectif(data.getPlateau().getColonnes()[i].getCarteObjectif())); // carte objectif
        	for(int j=0;j < data.getPlateau().getColonnes()[i].getCartesInfluences().length;j++) { // carte influences
        		h.getChildren().add(new SpriteCarteInfluence(data.getPlateau().getColonnes()[i].getCartesInfluences()[j]));
        	}
        Colonnes.getChildren().add(h);
        }
        return Colonnes;
    }
    

    //                          Operations                                  
    

//    /**
//    * @generated
//    */
//    public void placerCarte() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public void defausserCarte() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public void piocherCarte() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public void renouvelerReserve() {
//        //TODO
//    }
//    /**
//    * @generated
//    */
//    public void quitterJeu() {
//        //TODO
//    }
    
}
