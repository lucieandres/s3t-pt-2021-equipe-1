package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
import cartes.Reine;
import cartes.Roi;
import elements.Colonne;
import javafx.scene.paint.Color;

class TestsColonne {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {

		CarteObjectif religion = new CarteObjectif("Religion", 5);
		Roi roi = new Roi(Color.BLUE);
		Reine reine = new Reine(Color.BLUE);
		
		Colonne colonne = new Colonne(4);
		colonne.setCarteObjectif(religion);
		assertEquals(colonne.getCarteObjectif(), religion);
		
		CarteInfluence[] cartes = colonne.getCartesInfluences();
		colonne.setCartesInfluences(cartes);
		assertEquals(colonne.getCartesInfluences(), cartes);
		colonne.ajouterCarteInfluence(roi);
		colonne.ajouterCarteInfluence(reine);
		cartes = colonne.getCartesInfluences();
		assertEquals(colonne.getCarteInfluence(0), roi);
		assertEquals(colonne.getCarteInfluence(1), reine);
		
		colonne.tournerCarte(roi);
		assertTrue(roi.getEstVisible());
		colonne.setCarteInfluenceVisible(1);
		assertTrue(reine.getEstVisible());
		colonne.enleverCarteInfluence(1);
		assertEquals(colonne.getCarteInfluence(1), null);
		
		assertFalse(colonne.estPleine());
		assertFalse(colonne.estFiniEtreRempli());
		
		assertFalse(colonne.getComplete());
		colonne.setComplete(true);
		assertTrue(colonne.getComplete());
		
		try {
			assertEquals(colonne.getIndexCarteInfluence(roi), 0);
			assertNotEquals(colonne.getIndexCarteInfluence(reine), 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		colonne.vider();
		
		
	}

}
