package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;

class Tests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		CarteInfluence carteInfluenceRoi = new CarteInfluence("Ro", "La valeur de l'Ermite diminue de 1 pour chaque autre carte présente dans la colonne", 2, true);
		assertEquals(carteInfluenceRoi.getNom(), "Ro");
		assertEquals(carteInfluenceRoi.getCapaciteSpeciale(), "La valeur de l'Ermite diminue de 1 pour chaque autre carte présente dans la colonne");
		assertEquals(carteInfluenceRoi.getValeur(), 2);
		assertEquals(carteInfluenceRoi.getVisible(), true);
		carteInfluenceRoi.setNom("Roi");
		carteInfluenceRoi.setCapaciteSpeciale("null");
		carteInfluenceRoi.setValeur(20);
		carteInfluenceRoi.setVisible(false);
		assertEquals(carteInfluenceRoi.getNom(), "Roi");
		assertEquals(carteInfluenceRoi.getCapaciteSpeciale(), "null");
		assertEquals(carteInfluenceRoi.getValeur(), 20);
		assertEquals(carteInfluenceRoi.getVisible(), false);
		
	}

}
