package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.Alchimiste;
import cartes.Cardinal;
import cartes.CarteInfluence;
import cartes.CarteDouble;
import cartes.CarteObjectif;
import cartes.MaitreDArme;
import cartes.Marchand;
import cartes.Seigneur;
import cartes.Troubadour;
import javafx.scene.paint.Color;

class TestsCarteDouble {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		CarteObjectif alchimie = new CarteObjectif("Alchimie", 5);
		CarteObjectif religion = new CarteObjectif("Religion", 5);
		CarteObjectif combat = new CarteObjectif("Combat", 5);
		CarteObjectif commerce = new CarteObjectif("Commerce", 5);
		CarteObjectif agriculture = new CarteObjectif("Agriculture", 5);
		CarteObjectif musique = new CarteObjectif("Musique", 5);
		
		//test de la classe Alchimiste
		
		Alchimiste alcBl = new Alchimiste(Color.WHITE);
		assertEquals(alcBl.getNom(), "Alchimiste");
		assertEquals(alcBl.getCouleur(), Color.WHITE);
		assertEquals(alcBl.getDomaine(), "Alchimie");
		alcBl.setDomaine("Alchimie");
		assertTrue(alcBl.getEstVisible());
//		assertEquals(alcBl.getValeurSpeciale(), 12);
		alcBl.setValeurSpeciale(12);
//		assertEquals(alcBl.getValeur(), 8);
		alcBl.setCouleur(Color.BLUE);
		alcBl.setNom("Reine");
		alcBl.reveler();
		assertTrue(alcBl.getEstVisible());
		alcBl.setEstVisible(false);
		alcBl.valeurUtilisee("Alchi");
		alcBl.valeurUtilisee("Alchimie");
		alcBl.setValeur(10);
		alcBl.setValeurSpeciale(2);
		alcBl.setDomaine("Religion");
		
		//test de la classe Cardinal
		
		Cardinal carBl = new Cardinal(Color.WHITE);
		assertEquals(carBl.getNom(), "Cardinal");
		assertEquals(carBl.getCouleur(), Color.WHITE);
		assertEquals(carBl.getDomaine(), "Religion");
		assertTrue(carBl.getEstVisible());
		//assertEquals(carBl.getValeurSpeciale(), 12);
		//assertEquals(carBl.getValeur(), 8);
		carBl.setCouleur(Color.BLUE);
		carBl.setNom("Reine");
		carBl.reveler();
		assertTrue(carBl.getEstVisible());
		carBl.setEstVisible(false);
		carBl.valeurUtilisee("Relig");
		carBl.valeurUtilisee("Religion");
		//assertEquals(carBl.getValeur(), 12);
		carBl.setValeur(10);
		carBl.setValeurSpeciale(2);
		carBl.setDomaine("Alchimie");
		
		//test de la classe MaitreDArme
		
		MaitreDArme maiBl = new MaitreDArme(Color.WHITE);
		assertEquals(maiBl.getNom(), "Maître d’armes");
		assertEquals(maiBl.getCouleur(), Color.WHITE);
		assertEquals(maiBl.getDomaine(), "Combat");
		assertTrue(maiBl.getEstVisible());
		//assertEquals(maiBl.getValeurSpeciale(), 12);
		//assertEquals(maiBl.getValeur(), 8);
		maiBl.setCouleur(Color.BLUE);
		maiBl.setNom("Reine");
		maiBl.reveler();
		assertTrue(maiBl.getEstVisible());
		maiBl.setEstVisible(false);
		maiBl.valeurUtilisee("Comb");
		maiBl.valeurUtilisee("Combat");
		//assertEquals(maiBl.getValeur(), 12);
		maiBl.setValeur(10);
		maiBl.setValeurSpeciale(2);
		maiBl.setDomaine("Alchimie");		
		
		//test de la classe Marchand
		
		Marchand marBl = new Marchand(Color.WHITE);
		assertEquals(marBl.getNom(), "Marchand");
		assertEquals(marBl.getCouleur(), Color.WHITE);
		assertEquals(marBl.getDomaine(), "Commerce");
		assertTrue(marBl.getEstVisible());
		//assertEquals(marBl.getValeurSpeciale(), 12);
		//assertEquals(marBl.getValeur(), 8);
		marBl.setCouleur(Color.BLUE);
		marBl.setNom("Reine");
		marBl.reveler();
		assertTrue(marBl.getEstVisible());
		marBl.setEstVisible(false);
		marBl.valeurUtilisee("Comme");
		marBl.valeurUtilisee("Commerce");
		//assertEquals(marBl.getValeur(), 12);
		marBl.setValeur(10);
		marBl.setValeurSpeciale(2);
		marBl.setDomaine("Alchimie");
		
		//test de la classe Seigneur
		
		Seigneur seiBl = new Seigneur(Color.WHITE);
		assertEquals(seiBl.getNom(), "Seigneur");
		assertEquals(seiBl.getCouleur(), Color.WHITE);
		assertEquals(seiBl.getDomaine(), "Agriculture");
		assertTrue(seiBl.getEstVisible());
		//assertEquals(seiBl.getValeurSpeciale(), 12);
		//assertEquals(seiBl.getValeur(), 8);
		seiBl.setCouleur(Color.BLUE);
		seiBl.setNom("Reine");
		seiBl.reveler();
		assertTrue(seiBl.getEstVisible());
		seiBl.setEstVisible(false);

		seiBl.valeurUtilisee("Agricul");
		seiBl.valeurUtilisee("Agriculture");
		//assertEquals(seiBl.getValeur(), 12);
		seiBl.setValeur(10);
		seiBl.setValeurSpeciale(2);
		seiBl.setDomaine("Alchimie");	
		
		//test de la classe Troubadour
		
		Troubadour troBl = new Troubadour(Color.WHITE);
		assertEquals(troBl.getNom(), "Troubadour");
		assertEquals(troBl.getCouleur(), Color.WHITE);
		assertEquals(troBl.getDomaine(), "Musique");
		assertTrue(troBl.getEstVisible());
		//assertEquals(troBl.getValeurSpeciale(), 12);
		//assertEquals(troBl.getValeur(), 8);
		troBl.setCouleur(Color.BLUE);
		troBl.setNom("Reine");
		troBl.reveler();
		assertTrue(troBl.getEstVisible());
		troBl.setEstVisible(false);
		troBl.valeurUtilisee("Musi");
		troBl.valeurUtilisee("Musique");
		//assertEquals(troBl.getValeur(), 12);
		troBl.setValeur(10);
		troBl.setValeurSpeciale(2);
		troBl.setDomaine("Alchimie");
		
		//Toutes les carteDouble ont ete testees
		
	}

}
