package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import joueur.Joueur;

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
		
		//test get et set de couleur
		
		lucie.setCouleur(Color.WHITE);
		assertEquals(lucie.getCouleur(), Color.WHITE);

		//test get et set de pseudo
		
		lucie.setPseudo("lulu");
		assertEquals(lucie.getPseudo(), "lulu");		
		
		
		//test méthodes
		
		lucie.initMain();
		
	}

}
