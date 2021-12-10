package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.Roi;
import cartes.Reine;
import cartes.Explorateur;
import cartes.Tempete;
import elements.Colonne;
import elements.Plateau;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;


class TestsCarteSpéciale {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws Exception {
		Joueur joueur1 = new Joueur(Color.RED, "Jean");
		Joueur joueur2 = new Joueur(Color.BLUE, "Pierre");
		
		Data data = new Data(joueur1, 2);
		data.addJoueur(joueur2);
		
		Plateau plat = new Plateau(2);
		data.setPlateau(plat);
		
		Colonne colonne1 = new Colonne(2);
		Colonne colonne2 = new Colonne(2);
		Colonne[] colonnes = {colonne1, colonne2};
		plat.setColonnes(colonnes);
		
		
		//test de la classe Explorateur
		
		Roi roiB = new Roi(Color.BLUE);
		Explorateur expR = new Explorateur(Color.RED);
		Tempete temB = new Tempete(Color.BLUE);
		Reine reiR = new Reine(Color.RED);
		
		//sans carte Tempete
		colonne1.ajouterCarteInfluence(expR);
		assertFalse(expR.getEstVisible());
		assertEquals(colonne1.getCarteInfluence(0), expR);
		colonne1.ajouterCarteInfluence(roiB);
		assertTrue(expR.getEstVisible());
		assertEquals(colonne1.getCarteInfluence(0), expR);
		assertEquals(colonne1.getCarteInfluence(1), roiB);
		expR.activer(data);
		assertFalse(expR.getEstVisible());
		assertEquals(colonne1.getCarteInfluence(0), roiB);
		assertEquals(colonne2.getCarteInfluence(0), expR);
		
		//avec carte Tempete
		colonne1.ajouterCarteInfluence(temB);
		assertEquals(colonne1.getCarteInfluence(1), temB);
		colonne2.ajouterCarteInfluence(reiR);
		assertTrue(expR.getEstVisible());
		assertEquals(colonne2.getCarteInfluence(1), reiR);
		expR.activer(data);
		assertFalse(expR.getEstVisible());
		assertEquals(colonne1.getCarteInfluence(0), roiB);
		assertEquals(colonne1.getCarteInfluence(1), temB);
		assertEquals(colonne2.getCarteInfluence(0), reiR);
		assertEquals(colonne2.getCarteInfluence(1), expR);
		
		//test de la classe Tempete
		
		colonne1.vider();
		colonne2.vider();
		
		colonne1.ajouterCarteInfluence(temB);
		assertFalse(colonne1.getComplete());
		colonne1.ajouterCarteInfluence(reiR);
		temB.activer(data);
		assertTrue(colonne1.getComplete());
		
		
	}

}
