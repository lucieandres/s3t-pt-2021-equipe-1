package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;
import cartes.CarteObjectif;

class Tests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		CarteInfluence carteInfluenceRoi = new CarteInfluence("Ro", true, 2, true);
		assertEquals(carteInfluenceRoi.getNom(), "Ro");
		assertEquals(carteInfluenceRoi.getCapacite_special(), true);
		assertEquals(carteInfluenceRoi.getValeur(), 2);
		assertEquals(carteInfluenceRoi.getVisible(), true);
		carteInfluenceRoi.setNom("Roi");
		carteInfluenceRoi.setCapacite_special(false);
		carteInfluenceRoi.setValeur(20);
		carteInfluenceRoi.setVisible(false);
		assertEquals(carteInfluenceRoi.getNom(), "Roi");
		assertEquals(carteInfluenceRoi.getCapacite_special(), false);
		assertEquals(carteInfluenceRoi.getValeur(), 20);
		assertEquals(carteInfluenceRoi.getVisible(), false);
		
		CarteObjectif carteObjectifRelgion2 = new CarteObjectif("Religio", 3, true);
		assertEquals(carteObjectifRelgion2.getDomaine(), "Religio");
		assertEquals(carteObjectifRelgion2.getValeur(), 3);
		assertEquals(carteObjectifRelgion2.getEstRealise(), true);
		carteObjectifRelgion2.setDomaine("Religion");
		carteObjectifRelgion2.setValeur(2);
		carteObjectifRelgion2.setEstRealise(false);
		assertEquals(carteObjectifRelgion2.getDomaine(), "Religion");
		assertEquals(carteObjectifRelgion2.getValeur(), 2);
		assertEquals(carteObjectifRelgion2.getEstRealise(), false);
		
		
	}

}
