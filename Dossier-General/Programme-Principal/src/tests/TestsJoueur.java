package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
import cartes.Roi;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;

class TestsJoueur {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		//test constructeur
		
		Joueur lucie = new Joueur(Color.BLUE, "lucie");
		assertEquals(lucie.getCouleur(), Color.BLUE);
		assertEquals(lucie.getPseudo(), "lucie");
		
		Joueur julie = new Joueur(Color.RED, "Julie");
		assertEquals(julie.getCouleur(), Color.RED);
		assertEquals(julie.getPseudo(), "Julie");
		
		//test get et set de couleur
		
		lucie.setCouleur(Color.WHITE);
		assertEquals(lucie.getCouleur(), Color.WHITE);

		//test get et set de pseudo
		
		lucie.setPseudo("lulu");
		assertEquals(lucie.getPseudo(), "lulu");		
		
		//test methodes
		
		lucie.initReserve();
		lucie.initMainJoueur();
		julie.initReserve();
		julie.initMainJoueur();
		
		Data data = new Data(lucie, 2);
		data.addJoueur(julie);
		
		//test main
		
		CarteInfluence[] main = new CarteInfluence[3];
		main = lucie.getMain();
		lucie.setMain(main);
		assertEquals(lucie.getMain(), main);
		
		//test reserve
		
		CarteInfluence[] reserve = new CarteInfluence[25];
		reserve = lucie.getReserve();
		lucie.setReserve(reserve);
		assertEquals(lucie.getReserve(), reserve);
		
		//test defausse

		CarteInfluence[] defausse = new CarteInfluence[25];
		defausse = lucie.getDefausse();
		lucie.setDefausse(defausse);
		assertEquals(lucie.getDefausse(), defausse);
		
		//test carte selectionnee, ajouterCarteInfluence, setMain, ajouterDansLaDefausse
		
		lucie.setCarteSelectionnee(1);
		assertEquals(lucie.getCarteSelectionnee(), 1);
		CarteInfluence carte = lucie.getReserve()[6];
		main = new CarteInfluence[3];
		lucie.setMain(main);
		lucie.ajouterCarteInfluence(carte);
		lucie.setMain(0, carte);
		assertEquals(lucie.getMain()[0], carte);
		lucie.ajouterDansLaDefausse(carte);
		assertEquals(lucie.getDefausse()[0], carte);
		
		//test jouer
		
		try {
			julie.jouer(data, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//test cartes objectifs
		
		ArrayList<CarteObjectif> objectifs = new ArrayList<CarteObjectif>(); 
		lucie.setObjectif(objectifs);
		assertEquals(lucie.getObjectif(), objectifs);
		CarteObjectif objectif = new CarteObjectif("Alchimie", 3);
		lucie.addCarteObjectif(objectif);
		assertEquals(lucie.getObjectif().get(0), objectif);
		
		//test score
		
		assertEquals(lucie.getScore(), 3);	


		
	}

}
