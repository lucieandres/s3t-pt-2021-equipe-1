package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;
import cartes.Juliette;
import cartes.Reine;
import cartes.Roi;

import javafx.scene.paint.Color;


class TestsCarteInfluence {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		//test de la classe Juliette
		
		Juliette julBl = new Juliette(Color.WHITE);
		assertEquals(julBl.getNom(), "Juliette");
		assertEquals(julBl.getCouleur(), Color.WHITE);
		assertTrue(julBl.getEstVisible());
		//assertEquals(julBl.getValeur(), 14);
		julBl.setCouleur(Color.BLUE);
		julBl.setNom("Reine");
		julBl.setValeur(10);
		julBl.setEstVisible(false);
		assertFalse(julBl.getEstVisible());
		julBl.reveler();
		assertTrue(julBl.getEstVisible());
		
		//test de la classe Reine
		
		Reine reiBl = new Reine(Color.WHITE);
		assertEquals(reiBl.getNom(), "Reine");
		assertEquals(reiBl.getCouleur(), Color.WHITE);
		assertTrue(reiBl.getEstVisible());
		//assertEquals(reiBl.getValeur(), 16);
		reiBl.setCouleur(Color.BLUE);
		reiBl.setNom("Roi");
		reiBl.setValeur(10);
		reiBl.setEstVisible(false);
		assertFalse(reiBl.getEstVisible());
		reiBl.reveler();
		assertTrue(reiBl.getEstVisible());
		
		//test de la classe Roi
		
		Roi roiBl = new Roi(Color.WHITE);
		assertEquals(roiBl.getNom(), "Roi");
		assertEquals(roiBl.getCouleur(), Color.WHITE);
		assertTrue(roiBl.getEstVisible());
		//assertEquals(roiBl.getValeur(), 20);
		roiBl.setCouleur(Color.BLUE);
		roiBl.setNom("Reine");
		roiBl.setValeur(10);
		roiBl.setEstVisible(false);
		assertFalse(roiBl.getEstVisible());
		roiBl.reveler();
		assertTrue(roiBl.getEstVisible());
		
		//Toutes les carteInfluence ont ete testees
		
	}

}
