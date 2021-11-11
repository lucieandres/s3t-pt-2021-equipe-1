package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteObjectif;

class TestsCarteObjectif {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {

		
		CarteObjectif carteObjectifRelgion2 = new CarteObjectif("Religio", 3);
		assertEquals(carteObjectifRelgion2.getDomaine(), "Religio");
		assertEquals(carteObjectifRelgion2.getValeur(), 3);
		carteObjectifRelgion2.setDomaine("Religion");
		carteObjectifRelgion2.setValeur(2);
		assertEquals(carteObjectifRelgion2.getDomaine(), "Religion");
		assertEquals(carteObjectifRelgion2.getValeur(), 2);
		
	}

}
