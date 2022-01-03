package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteObjectif;
import cartes.Dragon;
import cartes.Ecuyer;
import cartes.Ermite;
import cartes.Magicien;
import cartes.Mendiant;
import cartes.PetitGeant;
import cartes.Prince;
import cartes.Roi;
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
//		joueur1.initReserve();
//		joueur1.initMainJoueur();
		Joueur joueur2 = new Joueur(Color.BLUE, "Julie");
//		joueur2.initReserve();
//		joueur2.initMainJoueur();
		
		Joueur joueurs[] = {joueur1, joueur2};
		
		Data data = new Data(joueur1, 2);
		data.setJoueurs(joueurs);
		
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
		
		data.getPlateau().enleverTous();
		
		//test de la classe Magicien

		Magicien magR = new Magicien(Color.RED);
		assertEquals(magR.getNom(), "Magicien");
		assertTrue(magR.getEstVisible());
		assertEquals(magR.getValeur(), 7);
		assertEquals(magR.getCouleur(), Color.RED);
		assertFalse(magR.estDesactivee());
				
		//Capacité spéciale
		Roi roiB = new Roi(Color.BLUE);
		Romeo romB = new Romeo(Color.BLUE); 
		data.getPlateau().ajouterColonnes(0, roiB);
		data.getPlateau().ajouterColonnes(0, romB);
		data.getPlateau().ajouterColonnes(0, magR);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(0), roiB);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(1), romB);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(2), magR);
		try {
			magR.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(1), magR);
		//Le Magicien a supprimé les cartes a plus de 10 de la colonne.
		
		data.getPlateau().enleverTous();
		
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

		Prince priR = new Prince(Color.RED);
		assertEquals(priR.getNom(), "Prince");
		assertTrue(priR.getEstVisible());
		assertEquals(priR.getValeur(), 14);
		assertEquals(priR.getCouleur(), Color.RED);
		assertFalse(priR.estDesactivee());
		
		//Capacité spéciale
		CarteObjectif reli2 = new CarteObjectif("Religion", 2);
		data.getPlateau().getColonne(0).setCarteObjectif(reli2);
		Ecuyer ecuR = new Ecuyer(Color.RED);
		data.getPlateau().ajouterColonnes(0, priR);
		data.getPlateau().ajouterColonnes(0, ecuR);
		try {
			priR.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(data.getJoueursAvecIndex(0).getObjectif().contains(reli2));
		
		//test de la classe Romeo

		Romeo romR = new Romeo(Color.RED);
		assertEquals(romR.getNom(), "Roméo");
		assertTrue(romR.getEstVisible());
		assertEquals(romR.getValeur(), 5);
		assertEquals(romR.getCouleur(), Color.RED);
		assertFalse(romR.estDesactivee());
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

		Sosie sosR = new Sosie(Color.RED);
		assertEquals(sosR.getNom(), "Sosie");
		assertTrue(sosR.getEstVisible());
		assertEquals(sosR.getValeur(), 0);
		assertEquals(sosR.getCouleur(), Color.RED);
		assertFalse(sosR.estDesactivee());
		
		//Capacité spéciale
		data.getPlateau().ajouterColonnes(0, sosR);
		try {
			sosR.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		data.getPlateau().enleverTous();
		
		
		//test de la classe TroisMousquetaires

		TroisMousquetaires troR = new TroisMousquetaires(Color.RED);
		assertEquals(troR.getNom(), "Trois Mousquetaires");
		assertTrue(troR.getEstVisible());
		assertEquals(troR.getValeur(), 11);
		assertEquals(troR.getCouleur(), Color.RED);
		assertFalse(troR.estDesactivee());
		
		//Capacité spéciale
		TroisMousquetaires troB1 = new TroisMousquetaires(Color.BLUE);
		TroisMousquetaires troB2 = new TroisMousquetaires(Color.BLUE);
		data.getPlateau().ajouterColonnes(0, troR);
		data.getPlateau().ajouterColonnes(0, troB1);
		data.getPlateau().ajouterColonnes(0, troB2);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(0), troR);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(1), troB1);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(2), troB2);
		try {
			troR.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(troB1.estDesactivee());
		assertTrue(troB2.estDesactivee());
		//Les Trois Mousquetaires ont bien désactivé les cartes de la colonne.
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
