package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import cartes.CarteObjectif;
import elements.Colonne;
import elements.Plateau;

class TestsPlateau {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
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
		
		// test constructeur
		
		Plateau plateau = new Plateau(4);
		assertEquals(plateau.getColonnes().length, 4);
		
		//test get et set colonnes
		
		Colonne[] colonne = new Colonne[2];
		plateau.setColonnes(colonne);
		assertEquals(plateau.getColonnes().length, 2);
		
		//test get et set pioche
		
		ArrayList<CarteObjectif> pioche = new ArrayList<>();
		CarteObjectif alchimie = new CarteObjectif("Alchimie", 5);
		CarteObjectif religion = new CarteObjectif("Religion", 5);
		CarteObjectif combat = new CarteObjectif("Combat", 5);
		pioche.add(alchimie);
		pioche.add(religion);
		pioche.add(combat);
		plateau.setPioche(pioche);
		assertEquals(plateau.getPioche(), pioche);		
		
		//test methodes
		
//		Plateau plateau2 = new Plateau(4);
//		plateau2.initPioche(4);	
//		assertNotEquals(plateau2.getPioche(), null);
//		plateau2.setAllColonnes();
		
		
		
	}

}
