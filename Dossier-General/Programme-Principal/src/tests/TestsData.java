package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;
import cartes.Juliette;
import cartes.Reine;
import cartes.Roi;
import elements.Plateau;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;

class TestsData {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		Joueur master = new Joueur(Color.BLUE, "lucie");
		Data data = new Data(master, 4);
		assertEquals(data.getMaster(), master);
		assertEquals(data.getJoueurs().length, 4);
		
		
		CarteInfluence[] reserve = new CarteInfluence[10];
//		Roi roiBl = new Roi(Color.BLUE);
//		Reine reiBl = new Reine(Color.BLUE);
//		Juliette julBl = new Juliette(Color.BLUE);
//		reserve[0] = roiBl;
//		reserve[1] = reiBl;
//		reserve[2] = julBl;
		master.setReserve(reserve);
		data.remplirReserve(master);
		
		Joueur joueur = new Joueur(Color.BLUE, "julie");
		data.addJoueur(joueur);
		Joueur[] joueurs = new Joueur[3];
		joueurs = data.getJoueurs();
		data.setJoueurs(joueurs);
		assertEquals(joueurs[0], master);
		data.removeJoueur(joueur);
		data.setMaster(joueur);
		
		Plateau plateau = new Plateau(4);
		plateau = data.getPlateau();
		data.setPlateau(plateau);
		
		data.setCurrentManche(1);
		assertEquals(data.getCurrentManche(), 1);
		
		data.setCurrentTour(1);
		assertEquals(data.getCurrentTour(), 1);
		data.tourSuivant();
		assertEquals(data.getCurrentTour(), 2);
		
		data.setCurrentJoueur(0);
		assertEquals(data.getCurrentJoueur(), 0);
		data.joueurSuivant();
		assertEquals(data.getCurrentJoueur(), 1);
		data.joueurSuivant();
		assertEquals(data.getCurrentJoueur(), 2);
		data.joueurSuivant();
		assertEquals(data.getCurrentJoueur(), 3);
		data.joueurSuivant();
		assertEquals(data.getCurrentJoueur(), 0);
		assertEquals(data.getCurrentTour(), 3);
		
		data.deplacerCarteInfluenceMainVersColonne(1, 1);
	
		assertEquals(data.getCurrentManche(),1);
		data.mancheFinie();
		assertEquals(data.getCurrentManche(),2);
		assertFalse(data.partieFinie());
		data.mancheSuivante();
		assertFalse(data.partieFinie());
		data.mancheSuivante();
		assertFalse(data.partieFinie());
		data.mancheSuivante();
		assertFalse(data.partieFinie());
		data.mancheSuivante();
		assertFalse(data.partieFinie());
		data.mancheSuivante();
		assertTrue(data.partieFinie());
		
		
		
	}

}
