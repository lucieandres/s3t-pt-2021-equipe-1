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
		
		//tests de mancheSuivante, getCurrentManche, partieFinie
		
		assertEquals(data.getCurrentManche(),1);
		data.mancheSuivante();
		assertEquals(data.getCurrentManche(),2);
		assertFalse(data.partieFinie());
		data.mancheSuivante();
		assertEquals(data.getCurrentManche(),3);
		assertFalse(data.partieFinie());
		data.mancheSuivante();
		assertEquals(data.getCurrentManche(),4);
		assertFalse(data.partieFinie());
		data.mancheSuivante();
		assertEquals(data.getCurrentManche(),5);
		assertFalse(data.partieFinie());
		data.mancheSuivante();
		assertEquals(data.getCurrentManche(),6);
		assertFalse(data.partieFinie());
//		data.mancheSuivante();
//		assertTrue(data.partieFinie());
		
		try {
			Joueur master2 = new Joueur(Color.BLUE, "lucie");
			master2.initReserve();
			master2.initMainJoueur();
			Data data2 = new Data(master2, 2);
			Joueur joueur2 = new Joueur(Color.WHITE, "julie");
			joueur2.initReserve();
			joueur2.initMainJoueur();
			data2.addJoueur(joueur2);
			assertFalse(data2.getPlateau().getColonne(0).getComplete());

			//tests getJoueursAvecIndex, getIndexJoueurParCouleur, getIndexJoueur
			
			assertEquals(data2.getJoueursAvecIndex(0), master2);
//			assertEquals(data2.getJoueursAvecIndex(1), joueur2);
			
			assertEquals(data2.getIndexJoueurParCouleur(Color.BLUE), 0);
//			assertEquals(data2.getIndexJoueurParCouleur(Color.WHITE), 1);
			
			assertEquals(data2.getIndexJoueur(master2), 0);
			assertEquals(data2.getIndexJoueur(joueur2), -1);
			
			//tests de jouerCarte, deplacerCarteInfluenceMainVersColonne
			
			assertEquals(data2.getPlateau().getColonnes()[0].getCartesInfluences()[0], null);
			data2.jouerCarte(0, 0);
			assertNotEquals(master2.getMain()[0], null);
			assertNotEquals(data2.getPlateau().getColonnes()[0].getCartesInfluences()[0], null);
			
			assertEquals(data2.getPlateau().getColonnes()[0].getCartesInfluences()[1], null);
			data2.jouerCarte(0, 0);
			assertNotEquals(joueur2.getMain()[0], null);
			assertNotEquals(data2.getPlateau().getColonnes()[0].getCartesInfluences()[1], null);
			assertTrue(data2.getPlateau().getColonnes()[0].getCartesInfluences()[0].getEstVisible());
			
			//tests de retournerCarte
			
			assertFalse(data2.getPlateau().getColonnes()[0].getCartesInfluences()[1].getEstVisible());
			data2.retournerCarte();
			assertTrue(data2.getPlateau().getColonnes()[0].getCartesInfluences()[1].getEstVisible());
			
			//tests de activerCartesARetardement
			
			data2.activerCartesARetardement();
			
			//tests de getJoueurIntermediaire, setJoueurInterfmediaire
			
			assertEquals(data2.getJoueurIntermediaire(), -1);
			data2.setJoueurInterfmediaire(0);
			assertEquals(data2.getJoueurIntermediaire(), 0);
			
			//tests de getIndexProprietaireCarteInfluence
			
			assertEquals(data2.getIndexProprietaireCarteInfluence(0,0), 0);
			assertEquals(data2.getIndexProprietaireCarteInfluence(0,1), 1);
				
			//tests de estRealisee
			
			data2.estRealisee(0);
			assertTrue(data2.getPlateau().getColonne(0).getComplete());
			data2.getPlateau().getColonne(0).setComplete(false);
			
			//test de getTotale
			
			data2.getTotale(0, 0, 0);
			
			//test de possedeCarteLaPlusBasse
			
//			data2.possedeCarteLaPlusBasse(master2, joueur2, 0);
			
			//tests de finDeManche, resultatFinManche, regrouperCartesInfluencesDansReserve
			
			assertEquals(master2.getDefausse()[0], null);
			data2.finDeManche();
			assertEquals(data2.resultatFinManche(0), 0);
			assertFalse(data2.getPlateau().getColonne(0).getComplete());
			assertFalse(data2.getPlateau().getColonne(1).getComplete());
			assertEquals(data2.getPlateau().getColonnes()[0].getCartesInfluences()[0], null);
			assertEquals(data2.getPlateau().getColonnes()[0].getCartesInfluences()[1], null);
			assertNotEquals(master2.getDefausse()[0], null);
			
			//test de Data
			
			Data dataTest = new Data(2);
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
