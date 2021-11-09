package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import moteur.Tour;

class TestsTour {
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {

		Tour tour = new Tour(2);
		assertEquals(tour.getNumero(), 2);
		tour.setNumero(3);
		assertEquals(tour.getNumero(), 3);
		Tour tourZero = new Tour();
		assertEquals(tourZero.getNumero(), 0);
		
		
	}

}
