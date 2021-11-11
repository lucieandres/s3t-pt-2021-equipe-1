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

import javafx.scene.paint.Color;


class Tests {

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
		assertFalse(julBl.getEstVisible());
		//assertEquals(julBl.getValeur(), 14);
		julBl.setCouleur(Color.BLUE);
		julBl.setNom("Reine");
		julBl.setValeur(10);
		julBl.Reveler();
		assertTrue(julBl.getEstVisible());
		julBl.setEstVisible(false);
		
		//test de la classe Reine
		
		Reine reiBl = new Reine(Color.WHITE);
		assertEquals(reiBl.getNom(), "Reine");
		assertEquals(reiBl.getCouleur(), Color.WHITE);
		assertFalse(reiBl.getEstVisible());
		//assertEquals(reiBl.getValeur(), 16);
		reiBl.setCouleur(Color.BLUE);
		reiBl.setNom("Roi");
		reiBl.setValeur(10);
		reiBl.Reveler();
		assertTrue(reiBl.getEstVisible());
		reiBl.setEstVisible(false);
		
		//test de la classe Roi
		
		Roi roiBl = new Roi(Color.WHITE);
		assertEquals(roiBl.getNom(), "Roi");
		assertEquals(roiBl.getCouleur(), Color.WHITE);
		assertFalse(roiBl.getEstVisible());
		//assertEquals(roiBl.getValeur(), 20);
		roiBl.setCouleur(Color.BLUE);
		roiBl.setNom("Reine");
		roiBl.setValeur(10);
		roiBl.Reveler();
		assertTrue(roiBl.getEstVisible());
		roiBl.setEstVisible(false);
		
		//Toutes les carteInfluence ont été testées
		
	}

}
