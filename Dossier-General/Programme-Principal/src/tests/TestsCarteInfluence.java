package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;
import cartes.Roi;
import joueur.Couleur;

class Tests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		Couleur blanc = new Couleur("blanc");
		Roi roiBl = new Roi(blanc);
		assertEquals(roiBl.getNom(), "Roi");
		assertEquals(roiBl.getCouleur(), blanc);
		assertEquals(roiBl.getValeur(), 20);
		assertEquals(roiBl.getEstVisible(), false);
		Couleur bleu = new Couleur("bleu");
		roiBl.setCouleur(bleu);
		roiBl.setNom("Reine");
		roiBl.setValeur(10);
		roiBl.setEstVisible(true);

		
	}

}
