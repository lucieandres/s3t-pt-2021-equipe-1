package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
import cartes.Reine;
import cartes.Roi;
import elements.Colonne;
import elements.Plateau;
import javafx.scene.paint.Color;

class TestsPlateau {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		// test constructeur
		
		Plateau plateau = new Plateau(4);
		assertEquals(plateau.getColonnes().length, 4);
		Colonne[] colonneP = plateau.getColonnes();
		assertNotEquals(colonneP[0].getCarteObjectif(), null);
		assertNotEquals(colonneP[1].getCarteObjectif(), null);
		
		//test get et set colonnes
		
		Colonne[] colonne = new Colonne[2];
		plateau.setColonnes(colonne);
		assertEquals(plateau.getColonnes().length, 2);
		
		//test pioche
		
		ArrayList<CarteObjectif> pioche = new ArrayList<>();
		CarteObjectif alchimie = new CarteObjectif("Alchimie", 5);
		CarteObjectif religion = new CarteObjectif("Religion", 5);
		CarteObjectif combat = new CarteObjectif("Combat", 5);
		pioche.add(alchimie);
		pioche.add(religion);
		pioche.add(combat);
		plateau.setPioche(pioche);
		assertEquals(plateau.getPioche(), pioche);	
		assertFalse(plateau.piocheEstVide());		
		
		//test methodes
		
		Plateau plateau2 = new Plateau(4);
		plateau2.setAllColonnes();
		Colonne[] colonne2 = plateau2.getColonnes();
		assertNotEquals(colonne2[0].getCarteObjectif(), null);
		assertNotEquals(colonne2[1].getCarteObjectif(), null);
		
		plateau2.enleverTous();
		colonne2 = plateau2.getColonnes();
		assertEquals(colonne2[0].getCarteObjectif(), null);
		assertEquals(colonne2[1].getCarteObjectif(), null);
		
		//test ajouterColonnes
		
		Plateau plateau3 = new Plateau(2);
		plateau3.setAllColonnes();
		Roi roi = new Roi(Color.WHITE);
		plateau3.ajouterColonnes(0, roi);
		Colonne[] colonne3 = plateau3.getColonnes();
		CarteInfluence[] cartes3 = colonne3[0].getCartesInfluences();
		assertEquals(cartes3[0], roi);
		assertFalse(cartes3[0].getEstVisible());
		
		//test setCarteInfluencesVisible et de getColonne
		
		plateau3.setCarteInfluencesVisible(0, 0);
		Colonne colonne4 = plateau3.getColonne(0);
		cartes3 = colonne4.getCartesInfluences();
		assertTrue(cartes3[0].getEstVisible());
		
		//test de enleverCarteInfluence
		
		plateau3.enleverCarteInfluence(0, 0);
		assertEquals(cartes3[0], null);

		//test de setNouvelleCarteObjectif et de setNouvelleCarteObjectifNull
		
		plateau3.getColonne(0).setCarteObjectif(combat);
		assertEquals(plateau3.getColonne(0).getCarteObjectif(), combat);
		plateau3.setNouvelleCarteObjectif(0);
		assertNotEquals(plateau3.getColonne(0).getCarteObjectif(), combat);
		plateau3.setNouvelleCarteObjectifNull(0);
		assertEquals(plateau3.getColonne(0).getCarteObjectif(), null);
		
		//test de getIndexColonneCarte
		
		plateau3.ajouterColonnes(1, roi);
		Reine reine = new Reine(Color.WHITE);
		try {
			assertEquals(plateau3.getIndexColonneCarte(roi), 1);
			assertNotEquals(plateau3.getIndexColonneCarte(reine), 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		Colonne[] colonne = new Colonne[2];
//		Plateau plateau = new Plateau(4);
//		assertEquals(plateau.getColonnes().length, 4);
//		plateau.setColonnes(colonne);
//		assertEquals(plateau.getColonnes().length, 2);
//		
//		Colonne colonne0 = new Colonne(4);
//		colonne[0] = colonne0;
//		Colonne colonne1 = new Colonne(4);
//		colonne[1] = colonne1;
//		CarteObjectif carteObjectif = new CarteObjectif("Religion", 3);
//		CarteObjectif[] pioche = new CarteObjectif[10];
//		CarteObjectif alchimie = new CarteObjectif("Alchimie", 5);
//		CarteObjectif religion = new CarteObjectif("Religion", 5);
//		CarteObjectif combat = new CarteObjectif("Combat", 5);
//		CarteObjectif commerce = new CarteObjectif("Commerce", 5);
//		CarteObjectif agriculture = new CarteObjectif("Agriculture", 5);
//		CarteObjectif musique = new CarteObjectif("Musique", 5);
//		pioche[0] = alchimie;
//		pioche[1] = religion;
//		pioche[2] = combat;
//		pioche[3] = commerce;
//		pioche[4] = agriculture;
//		pioche[5] = musique;
//		plateau.setPioche(pioche);
//		//assertEquals(plateau.getPioche(), pioche);
//		CarteObjectif carteObjectif2 = plateau.getOneCarteObjectifRandomInPioche();
//		colonne[0].setCarteObjectif(carteObjectif);
//		colonne[1].setCarteObjectif(carteObjectif2);
//		plateau.setColonnes(colonne);
//		
//		plateau.enleverTous();
//		Colonne[] colonneTest = plateau.getColonnes();
//		assertEquals(colonneTest[0].getCarteObjectif(), null);
//		assertEquals(colonneTest[1].getCarteObjectif(), null);
//		plateau.enleverTous();
//		
//		plateau.setAllColonnes();
//		assertNotEquals(colonneTest[0].getCarteObjectif(), null);
//		assertNotEquals(colonneTest[1].getCarteObjectif(), null);
//		
//		plateau.newManche();
//		assertNotEquals(colonneTest[0].getCarteObjectif(), null);
//		assertNotEquals(colonneTest[1].getCarteObjectif(), null);
		
		
	}

}
