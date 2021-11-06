package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteObjectif;
import elements.Colonne;
import elements.Plateau;

class TestsPlateau {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {

		
		Colonne[] colonne = new Colonne[2];
		Plateau plateau = new Plateau(6);
		assertEquals(plateau.getColonne().length, 4);
		plateau.setColonne(colonne);
		assertEquals(plateau.getColonne().length, 2);
		
		Colonne colonne0 = new Colonne(4);
		colonne[0] = colonne0;
		Colonne colonne1 = new Colonne(4);
		colonne[1] = colonne1;
		CarteObjectif carteObjectif = new CarteObjectif("Religion", 3);
		CarteObjectif carteObjectif2 = new CarteObjectif("Combat", 3);
		colonne[0].setCarteObjectif(carteObjectif);
		colonne[1].setCarteObjectif(carteObjectif2);
		plateau.setColonne(colonne);
		
		plateau.EnleverTous();
		Colonne[] colonneTest = plateau.getColonne();
		assertEquals(colonneTest[0].getCarteObjectif(), null);
		assertEquals(colonneTest[1].getCarteObjectif(), null);
		
		
	}

}
