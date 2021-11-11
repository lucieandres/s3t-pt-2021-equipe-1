//package tests;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import moteur.Manche;
//import moteur.Tour;
//
//class TestsManche {
//	
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void test() {
//		
//		ArrayList<Tour> arrayListTour = new ArrayList<Tour> ();
//		Manche mancheTest = new Manche(2);
//		Manche manche = new Manche(arrayListTour, 2, true);
//		assertEquals(manche.getTour(), arrayListTour);
//		manche.setTour(null);
//		assertEquals(manche.getTour(), null);
//		assertEquals(manche.getNumero(), 2);
//		manche.setNumero(3);
//		assertEquals(manche.getNumero(), 3);
//		assertTrue(manche.getEstFinie());
//		manche.setEstFinie(false);
//		assertFalse(manche.getEstFinie());
//		
//		
//	}
//
//}
