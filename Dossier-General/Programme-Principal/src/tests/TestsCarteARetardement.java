package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.Assassin;
import cartes.Dragon;
import cartes.Ecuyer;
import cartes.Ermite;
import cartes.Magicien;
import cartes.Mendiant;
import cartes.PetitGeant;
import cartes.Prince;
import cartes.Romeo;
import cartes.Sorciere;
import cartes.Sosie;
import cartes.TroisMousquetaires;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;

class TestsCarteARetardement {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		Joueur joueur1 = new Joueur(Color.RED, "Lucie");
		joueur1.initReserve();
		joueur1.initMainJoueur();
		Joueur joueur2 = new Joueur(Color.BLUE, "Julie");
		joueur2.initReserve();
		joueur2.initMainJoueur();
		
		Data data = new Data(joueur1, 2);
		data.addJoueur(joueur2);
		
		//test de la classe Dragon

		Dragon draR = new Dragon(Color.RED);
		assertEquals(draR.getNom(), "Dragon");
		assertTrue(draR.getEstVisible());
		assertEquals(draR.getValeur(), 11);
		assertEquals(draR.getCouleur(), Color.RED);
		assertFalse(draR.estDesactivee());
		data.getPlateau().ajouterColonnes(0, draR);
		try {
			draR.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe Ecuyer

		Ecuyer ecuB = new Ecuyer(Color.RED);
		assertEquals(ecuB.getNom(), "Ecuyer");
		assertTrue(ecuB.getEstVisible());
		assertEquals(ecuB.getValeur(), 2);
		assertEquals(ecuB.getCouleur(), Color.RED);
		assertFalse(ecuB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, ecuB);
		try {
			ecuB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe Ermite

		Ermite ermB = new Ermite(Color.RED);
		assertEquals(ermB.getNom(), "Ermite");
		assertTrue(ermB.getEstVisible());
		assertEquals(ermB.getValeur(), 12);
		assertEquals(ermB.getCouleur(), Color.RED);
		assertFalse(ermB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, ermB);
		try {
			ermB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe Magicien

		Magicien magB = new Magicien(Color.RED);
		assertEquals(magB.getNom(), "Magicien");
		assertTrue(magB.getEstVisible());
		assertEquals(magB.getValeur(), 7);
		assertEquals(magB.getCouleur(), Color.RED);
		assertFalse(magB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, magB);
		try {
			magB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe Mendiant

		Mendiant menB = new Mendiant(Color.RED);
		assertEquals(menB.getNom(), "Mendiant");
		assertTrue(menB.getEstVisible());
		assertEquals(menB.getValeur(), 4);
		assertEquals(menB.getCouleur(), Color.RED);
		assertFalse(menB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, menB);
		try {
			menB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe PetitGeant

		PetitGeant petB = new PetitGeant(Color.RED);
		assertEquals(petB.getNom(), "Petit Géant");
		assertTrue(petB.getEstVisible());
		assertEquals(petB.getValeur(), 2);
		assertEquals(petB.getCouleur(), Color.RED);
		assertFalse(petB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, petB);
		try {
			petB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe Prince

		Prince priB = new Prince(Color.RED);
		assertEquals(priB.getNom(), "Prince");
		assertTrue(priB.getEstVisible());
		assertEquals(priB.getValeur(), 14);
		assertEquals(priB.getCouleur(), Color.RED);
		assertFalse(priB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, priB);
		try {
			priB.activer(data);
			assertEquals(priB.combinaison(data, Color.RED), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe Romeo

		Romeo romB = new Romeo(Color.RED);
		assertEquals(romB.getNom(), "Roméo");
		assertTrue(romB.getEstVisible());
		assertEquals(romB.getValeur(), 5);
		assertEquals(romB.getCouleur(), Color.RED);
		assertFalse(romB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, romB);
		try {
			romB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe Sorciere

		Sorciere sorB = new Sorciere(Color.RED);
		assertEquals(sorB.getNom(), "Sorcière");
		assertTrue(sorB.getEstVisible());
		assertEquals(sorB.getValeur(), 1);
		assertEquals(sorB.getCouleur(), Color.RED);
		assertFalse(sorB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, sorB);
		try {
			sorB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe Sosie

		Sosie sosB = new Sosie(Color.RED);
		assertEquals(sosB.getNom(), "Sosie");
		assertTrue(sosB.getEstVisible());
		assertEquals(sosB.getValeur(), 0);
		assertEquals(sosB.getCouleur(), Color.RED);
		assertFalse(sosB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, sosB);
		try {
			sosB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//test de la classe TroisMousquetaires

		TroisMousquetaires troB = new TroisMousquetaires(Color.RED);
		assertEquals(troB.getNom(), "Trois Mousquetaires");
		assertTrue(troB.getEstVisible());
		assertEquals(troB.getValeur(), 11);
		assertEquals(troB.getCouleur(), Color.RED);
		assertFalse(troB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, troB);
		try {
			troB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
