package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.Alchimiste;
import cartes.CarteInfluence;
import cartes.Juliette;
import cartes.Reine;
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
		Couleur bleu = new Couleur("bleu");
		
		Juliette julBl = new Juliette(blanc);
		assertEquals(julBl.getNom(), "Juliette");
		assertEquals(julBl.getCouleur(), blanc);
		assertEquals(julBl.getEstVisible(), false);
		julBl.setCouleur(bleu);
		julBl.setNom("Reine");
		julBl.setValeur(10);
		julBl.setEstVisible(true);

		Reine reiBl = new Reine(blanc);
		assertEquals(reiBl.getNom(), "Reine");
		assertEquals(reiBl.getCouleur(), blanc);
		assertEquals(reiBl.getEstVisible(), false);
		reiBl.setCouleur(bleu);
		reiBl.setNom("Roi");
		reiBl.setValeur(10);
		reiBl.setEstVisible(true);
		
		Roi roiBl = new Roi(blanc);
		assertEquals(roiBl.getNom(), "Roi");
		assertEquals(roiBl.getCouleur(), blanc);
		assertEquals(roiBl.getEstVisible(), false);
		roiBl.setCouleur(bleu);
		roiBl.setNom("Reine");
		roiBl.setValeur(10);
		roiBl.setEstVisible(true);
		
	}

}
