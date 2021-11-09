package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;
import joueur.Couleur;
import joueur.Joueur;
import moteur.Tour;

class TestsCouleur {

	@BeforeEach
	void setUp() throws Exception {
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {

		
		Joueur joueur1 = new Joueur();
		Joueur joueur2 = new Joueur();
		ArrayList<CarteInfluence> arrayListCarteInfluence = new ArrayList<CarteInfluence> ();
		Couleur couleur = new Couleur(joueur1, arrayListCarteInfluence, "Rouge");
		assertEquals(couleur.getJoueur(), joueur1);
		couleur.setJoueur(joueur2);
		assertEquals(couleur.getJoueur(), joueur2);
		couleur.setCarteInfluence(arrayListCarteInfluence);
		assertEquals(couleur.getCarteInfluence(), arrayListCarteInfluence);
		couleur.setNom("Bleu");
		assertEquals(couleur.getNom(), "Bleu");
		couleur.toJson();
		
	}

}
