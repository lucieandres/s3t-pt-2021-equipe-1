package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.Reine;
import cartes.Roi;
import cartes.Explorateur;
import cartes.Assassin;
import cartes.Tempete;
import cartes.Traitre;
import cartes.CapeDInvisibilite;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;

class TestsCarteSpeciale {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void test(){
		Joueur joueur1 = new Joueur(Color.RED, "Lucie");
		joueur1.initReserve();
		joueur1.initMainJoueur();
		Joueur joueur2 = new Joueur(Color.BLUE, "Julie");
		joueur2.initReserve();
		joueur2.initMainJoueur();
		
		Data data = new Data(joueur1, 2);
		data.addJoueur(joueur2);
		
		Roi roiB = new Roi(Color.BLUE);
		Reine reiB = new Reine(Color.BLUE);
		
	 
		//test de la classe Explorateur

		Explorateur expR = new Explorateur(Color.RED);
		assertEquals(expR.getNom(), "Explorateur");
		assertTrue(expR.getEstVisible());
		assertEquals(expR.getValeur(), 13);
		assertEquals(expR.getCouleur(), Color.RED);
		assertFalse(expR.estDesactivee());
		assertEquals(expR.getInfoReseau(), "NUL");
		
		//Capacité spéciale		
		data.getPlateau().ajouterColonnes(0, expR);
		data.getPlateau().ajouterColonnes(0, reiB);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(0), expR);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(1), reiB);
		try {
			expR.activer(data);
		} catch (Exception e1) {} 
		assertFalse(expR.estDesactivee());
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(0), reiB);
		assertEquals(data.getPlateau().getColonne(1).getCarteInfluence(0), expR);
		assertEquals(expR.getInfoReseau(), 1);
		//L'Explorateur s'est bien décalé d'une colonne vers la droite
		
		Tempete temB = new Tempete(Color.BLUE);
		data.getPlateau().ajouterColonnes(0, temB);
		data.getPlateau().ajouterColonnes(1, roiB);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(1), temB);
		assertEquals(data.getPlateau().getColonne(1).getCarteInfluence(1), roiB);
		try {
			expR.activer(data);
		} catch (Exception e1) {} 
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(0), reiB);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(1), temB);
		assertEquals(data.getPlateau().getColonne(1).getCarteInfluence(0), roiB);
		assertEquals(data.getPlateau().getColonne(1).getCarteInfluence(1), expR);
		assertEquals(expR.getInfoReseau(), 1);
		//L'Explorateur s'est bien décalé en évitant la colonne ou se trouve la carte Tempete
		
		data.getPlateau().enleverTous();
		
		
		//test de la classe Assassin

		Assassin assR = new Assassin(Color.RED);
		assertEquals(assR.getNom(), "Assassin");
		assertTrue(assR.getEstVisible());
		assertEquals(assR.getValeur(), 9.5);
		assertEquals(assR.getCouleur(), Color.RED);
		assertFalse(assR.estDesactivee());
		assertEquals(assR.getInfoReseau(), "NUL");
		
		//Capacité spéciale
		data.getPlateau().getColonne(0).ajouterCarteInfluence(assR);
		data.getPlateau().getColonne(0).ajouterCarteInfluence(roiB);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(0), assR);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(1), roiB);
		try {
			assR.activer(data);
		} catch (Exception e1) {}
		assertTrue(assR.estDesactivee());
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(0), assR);
		assertEquals(data.getPlateau().getColonne(0).getCarteInfluence(1), null);
		//L'Assassin a bien retirer la carte Roi qui l'a révélé
		
		data.getPlateau().enleverTous();
		

		//test de la classe CapeDInvisibilite

		CapeDInvisibilite capR = new CapeDInvisibilite(Color.RED);
		assertEquals(capR.getNom(), "Cape d’invisibilité");
		assertTrue(capR.getEstVisible());
		assertEquals(capR.getValeur(), 0);
		assertEquals(capR.getCouleur(), Color.RED);
		assertFalse(capR.estDesactivee());
		assertEquals(capR.getInfoReseau(), "NUL");
		
		data.getPlateau().ajouterColonnes(0, capR);
//		try {
//			capR.activer(data);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//TODO Capacité Cape d'Invisivilité incomplete
		
		data.getPlateau().enleverTous();
		
		
		//test de la classe Tempete
      
		Tempete temR = new Tempete(Color.RED);
		assertEquals(temR.getNom(), "Tempête");
		assertTrue(temR.getEstVisible());
		assertEquals(temR.getValeur(), 9);
		assertEquals(temR.getCouleur(), Color.RED);
		assertFalse(temR.estDesactivee());
		assertEquals(temR.getInfoReseau(), "NUL");
		
		//Capacité spéciale
      	assertFalse(data.getPlateau().getColonne(1).getComplete());
		data.getPlateau().ajouterColonnes(1, temR);
      	try {
      		temR.activer(data);
      	} catch (Exception e) {}
      	assertTrue(data.getPlateau().getColonne(1).getComplete());
		assertEquals(temR.getInfoReseau(), "FERMEE");
		//La Tempete a bien completé la colonne
		
		data.getPlateau().enleverTous();
		
      
      	//test de la classe Traitre

      	Traitre traB = new Traitre(Color.RED);
      	assertEquals(traB.getNom(), "Le Traitre");
      	assertTrue(traB.getEstVisible());
      	assertEquals(traB.getValeur(), 10);
      	assertEquals(traB.getCouleur(), Color.RED);
      	
      	data.getPlateau().ajouterColonnes(0, traB);
//      try {
//      	traB.activer(data, 1);
//      } catch (Exception e) {
//      	e.printStackTrace();
//      }
      	//TODO Capacité Traitre incomplete
		
		data.getPlateau().enleverTous();
		
		
		//Toutes les cartes spéciales ont été testée
		
	}

}
