package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
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
		
		Colonne colonne = new Colonne(4);
		colonne.setCarteObjectif(religion);
		assertEquals(colonne.getCarteObjectif(), religion);
		CarteInfluence[] cartes = colonne.getCartesInfluences();
		colonne.setCartesInfluences(cartes);
		assertEquals(colonne.getCartesInfluences(), cartes);
		colonne.tournerCarte(roi);
		assertTrue(roi.getEstVisible());
		assertFalse(colonne.estPleine());
		colonne.vider();
		
		
	}

}
