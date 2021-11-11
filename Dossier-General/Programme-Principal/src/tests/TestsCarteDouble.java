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
import cartes.Marchant;
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
		assertFalse(alcBl.getEstVisible());
		//assertEquals(alcBl.getValeurSpeciale(), 12);
		//assertEquals(alcBl.getValeur(), 8);
		alcBl.setCouleur(Color.BLUE);
		alcBl.setNom("Reine");
		alcBl.Reveler();
		assertTrue(alcBl.getEstVisible());
		alcBl.setEstVisible(false);
		alcBl.ValeurUtilisee(alchimie);
		//assertEquals(alcBl.getValeur(), 12);
		alcBl.setValeur(10);
		alcBl.setValeurSpeciale(2);
		alcBl.setDomaine("Religion");
		
		//test de la classe Cardinal
		
		Cardinal carBl = new Cardinal(Color.WHITE);
		assertEquals(carBl.getNom(), "Cardinal");
		assertEquals(carBl.getCouleur(), Color.WHITE);
		assertEquals(carBl.getDomaine(), "Religion");
		assertFalse(carBl.getEstVisible());
		//assertEquals(carBl.getValeurSpeciale(), 12);
		//assertEquals(carBl.getValeur(), 8);
		carBl.setCouleur(Color.BLUE);
		carBl.setNom("Reine");
		carBl.Reveler();
		assertTrue(carBl.getEstVisible());
		carBl.setEstVisible(false);
		carBl.ValeurUtilisee(religion);
		//assertEquals(carBl.getValeur(), 12);
		carBl.setValeur(10);
		carBl.setValeurSpeciale(2);
		carBl.setDomaine("Alchimie");
		
		//test de la classe MaitreDArme
		
		MaitreDArme maiBl = new MaitreDArme(Color.WHITE);
		assertEquals(maiBl.getNom(), "Maitre d'arme");
		assertEquals(maiBl.getCouleur(), Color.WHITE);
		assertEquals(maiBl.getDomaine(), "Combat");
		assertFalse(maiBl.getEstVisible());
		//assertEquals(maiBl.getValeurSpeciale(), 12);
		//assertEquals(maiBl.getValeur(), 8);
		maiBl.setCouleur(Color.BLUE);
		maiBl.setNom("Reine");
		maiBl.Reveler();
		assertTrue(maiBl.getEstVisible());
		maiBl.setEstVisible(false);
		maiBl.ValeurUtilisee(combat);
		//assertEquals(maiBl.getValeur(), 12);
		maiBl.setValeur(10);
		maiBl.setValeurSpeciale(2);
		maiBl.setDomaine("Alchimie");		
		
		//test de la classe Marchant
		
		Marchant marBl = new Marchant(Color.WHITE);
		assertEquals(marBl.getNom(), "Marchant");
		assertEquals(marBl.getCouleur(), Color.WHITE);
		assertEquals(marBl.getDomaine(), "Commerce");
		assertFalse(marBl.getEstVisible());
		//assertEquals(marBl.getValeurSpeciale(), 12);
		//assertEquals(marBl.getValeur(), 8);
		marBl.setCouleur(Color.BLUE);
		marBl.setNom("Reine");
		marBl.Reveler();
		assertTrue(marBl.getEstVisible());
		marBl.setEstVisible(false);
		marBl.ValeurUtilisee(commerce);
		//assertEquals(marBl.getValeur(), 12);
		marBl.setValeur(10);
		marBl.setValeurSpeciale(2);
		marBl.setDomaine("Alchimie");
		
		//test de la classe Seigneur
		
		Seigneur seiBl = new Seigneur(Color.WHITE);
		assertEquals(seiBl.getNom(), "Seigneur");
		assertEquals(seiBl.getCouleur(), Color.WHITE);
		assertEquals(seiBl.getDomaine(), "Agriculture");
		assertFalse(seiBl.getEstVisible());
		//assertEquals(seiBl.getValeurSpeciale(), 12);
		//assertEquals(seiBl.getValeur(), 8);
		seiBl.setCouleur(Color.BLUE);
		seiBl.setNom("Reine");
		seiBl.Reveler();
		assertTrue(seiBl.getEstVisible());
		seiBl.setEstVisible(false);
		seiBl.ValeurUtilisee(agriculture);
		//assertEquals(seiBl.getValeur(), 12);
		seiBl.setValeur(10);
		seiBl.setValeurSpeciale(2);
		seiBl.setDomaine("Alchimie");	
		
		//test de la classe Troubadour
		
		Troubadour troBl = new Troubadour(Color.WHITE);
		assertEquals(seiBl.getNom(), "Troubadour");
		assertEquals(seiBl.getCouleur(), Color.WHITE);
		assertEquals(seiBl.getDomaine(), "Musique");
		assertFalse(seiBl.getEstVisible());
		//assertEquals(seiBl.getValeurSpeciale(), 12);
		//assertEquals(seiBl.getValeur(), 8);
		seiBl.setCouleur(Color.BLUE);
		seiBl.setNom("Reine");
		seiBl.Reveler();
		assertTrue(seiBl.getEstVisible());
		seiBl.setEstVisible(false);
		seiBl.ValeurUtilisee(musique);
		//assertEquals(seiBl.getValeur(), 12);
		seiBl.setValeur(10);
		seiBl.setValeurSpeciale(2);
		seiBl.setDomaine("Alchimie");
		
		//Toutes les carteDouble ont ete testees
		
	}

}
