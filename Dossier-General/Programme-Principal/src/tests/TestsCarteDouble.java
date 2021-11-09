package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.Alchimiste;
import joueur.Couleur;
import cartes.CarteDouble;

class TestsCarteDouble {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		Couleur blanc = new Couleur("blanc");
		Alchimiste alcBl = new Alchimiste(blanc);
		assertEquals(alcBl.getNom(), "Alchimiste");
		assertEquals(alcBl.getCouleur(), blanc);
		assertEquals(alcBl.getDomaine(), "Alchimie");
		assertEquals(alcBl.getEstVisible(), false);
		Couleur bleu = new Couleur("bleu");
		alcBl.setCouleur(bleu);
		alcBl.setNom("Reine");
		alcBl.setValeur(10);
		alcBl.setDomaine("Religion");
		alcBl.setEstVisible(true);
		
	}

}
