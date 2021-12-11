package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.Assassin;
import cartes.CapeDInvisibilite;
import cartes.Explorateur;
import cartes.Tempete;
import cartes.Traitre;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;

class TestsCarteSpeciale {

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
		
		//test de la classe Assassin

		Assassin assB = new Assassin(Color.RED);
		assertEquals(assB.getNom(), "Assassin");
		assertTrue(assB.getEstVisible());
		assertEquals(assB.getValeur(), 9.5);
		assertEquals(assB.getCouleur(), Color.RED);
		assertFalse(assB.estDesactivee());
		data.getPlateau().ajouterColonnes(0, assB);
		try {
			assB.activer(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

      //test de la classe CapeDInvisibilite

      CapeDInvisibilite capB = new CapeDInvisibilite(Color.RED);
      assertEquals(capB.getNom(), "Cape d’invisibilité");
      assertTrue(capB.getEstVisible());
      assertEquals(capB.getValeur(), 0);
      assertEquals(capB.getCouleur(), Color.RED);
      data.getPlateau().ajouterColonnes(0, capB);
      try {
    	  capB.activer(data);
      } catch (Exception e) {
    	  e.printStackTrace();
      }
      
      //test de la classe Explorateur

      Explorateur expB = new Explorateur(Color.RED);
      assertEquals(expB.getNom(), "Explorateur");
      assertTrue(expB.getEstVisible());
      assertEquals(expB.getValeur(), 13);
      assertEquals(expB.getCouleur(), Color.RED);
      data.getPlateau().ajouterColonnes(0, expB);
      
      //test de la classe Tempete
      
      Tempete temB = new Tempete(Color.RED);
      assertEquals(temB.getNom(), "Tempête");
      assertTrue(temB.getEstVisible());
      assertEquals(temB.getValeur(), 9);
      assertEquals(temB.getCouleur(), Color.RED);
      data.getPlateau().ajouterColonnes(1, temB);
      try {
    	  temB.activer(data);
    	  expB.activer(data);
      } catch (Exception e) {
    	  e.printStackTrace();
      }
      
      //test de la classe Traitre

      Traitre traB = new Traitre(Color.RED);
      assertEquals(traB.getNom(), "Le Traitre");
      assertTrue(traB.getEstVisible());
      assertEquals(traB.getValeur(), 10);
      assertEquals(traB.getCouleur(), Color.RED);
      data.getPlateau().ajouterColonnes(0, traB);
      try {
    	  traB.activer(data, 1);
      } catch (Exception e) {
    	  e.printStackTrace();
      }
		
	}

}
